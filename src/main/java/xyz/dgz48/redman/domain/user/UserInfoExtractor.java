package xyz.dgz48.redman.domain.user;

import java.net.URI;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

/**
 * 各Idpから取得できるユーザ情報を正規化するクラス.
 */
@Component
@Slf4j
public class UserInfoExtractor {

	private final RestOperations restOperations = new RestTemplate();

	@Autowired
	private OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

	@Autowired
	ClientRegistrationRepository clientRegistrationRepository;

	/**
	 * {@link IdpType}に合わせた方法でメールアドレスを抽出する.
	 * @param oAuth2AuthenticationToken token
	 * @return メールアドレス
	 */
	public String getEmail(final OAuth2AuthenticationToken oAuth2AuthenticationToken) {

		IdpType idpType = IdpType.findByClientRegistrationId(oAuth2AuthenticationToken.getAuthorizedClientRegistrationId());

		if (idpType == IdpType.GOOGLE) {
			return String.valueOf(oAuth2AuthenticationToken.getPrincipal().getAttributes().get("email"));
		}

		if (idpType == IdpType.GITHUB) {
			OAuth2AuthorizedClient authorizedClient = getAuthorizedClient(oAuth2AuthenticationToken);
			// TODO こいつが取ってこれない
			String userInfoUri = authorizedClient.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUri() + "/email";
			RequestEntity<Void> requestEntity = RequestEntity.get(URI.create(userInfoUri))
					.header(HttpHeaders.AUTHORIZATION, "Bearer " + authorizedClient.getAccessToken().getTokenValue())
					.build();
			Map body = restOperations.exchange(requestEntity, Map.class).getBody();
			ObjectMapper mapper = new ObjectMapper();
			try {
				log.info("★★★★★★");
				log.info(mapper.writeValueAsString(body));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}

		throw new UnsupportedOperationException("null is not implemented idp.");
	}

	private OAuth2AuthorizedClient getAuthorizedClient(OAuth2AuthenticationToken authentication) {
		ClientRegistration registration = clientRegistrationRepository.findByRegistrationId(authentication.getAuthorizedClientRegistrationId());
//		new OAuth2AuthorizedClient(registration, authentication.getName());
//		OAuth2LoginAuthenticationFilter/
		return oAuth2AuthorizedClientService.loadAuthorizedClient(
				authentication.getAuthorizedClientRegistrationId(), authentication.getName());
	}

	/**
	 * {@link IdpType}に合わせた方法でユーザの画像URLを抽出する.
	 * @param oAuth2AuthenticationToken token
	 * @return 画像のURL
	 */
	public String getPictureUrl(final OAuth2AuthenticationToken oAuth2AuthenticationToken) {

		IdpType idpType = IdpType.findByClientRegistrationId(oAuth2AuthenticationToken.getAuthorizedClientRegistrationId());

		if (idpType == IdpType.GOOGLE) {
			return String.valueOf(oAuth2AuthenticationToken.getPrincipal().getAttributes().get("picture"));
		}

		if (idpType == IdpType.GITHUB) {
			throw new UnsupportedOperationException(String.format("%s is not implemented idp.", idpType.name()));
		}

		throw new UnsupportedOperationException("null is not implemented idp.");
	}

}
