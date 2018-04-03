package xyz.dgz48.redman.domain.user;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
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
			OAuth2AuthorizedClient client =
					oAuth2AuthorizedClientService.loadAuthorizedClient(
							oAuth2AuthenticationToken.getAuthorizedClientRegistrationId(),
							oAuth2AuthenticationToken.getName());

			String accessToken = client.getAccessToken().getTokenValue();
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getInterceptors()
					.add(getBearerTokenInterceptor(accessToken));
			System.out.println("GET EMAIL FROM GITHUB");


//			ResponseEntity<List<GitHubEmail>> emails =
//					restTemplate.exchange("https://api.github.com/user/emails",
//							HttpMethod.GET, null, new ParameterizedTypeReference<List<GitHubEmail>>() {
//							})
			Object[] forObject = restTemplate.getForObject("https://api.github.com/user/emails", Object[].class);
			Arrays.stream(forObject).forEach(o -> {
				try {
					System.out.println(new ObjectMapper().writeValueAsString(o));
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
			});

//			emails.getBody().forEach(System.out::println);

			return null;
		}

		throw new UnsupportedOperationException("null is not implemented idp.");
	}

//	@Data
//	@AllArgsConstructor
//	class GitHubEmail{
//
//		String email;
//		boolean verified;
//		boolean primary;
//		String visibility;
//
//	}

	private ClientHttpRequestInterceptor
	getBearerTokenInterceptor(String accessToken) {
		ClientHttpRequestInterceptor interceptor =
				new ClientHttpRequestInterceptor() {
					@Override
					public ClientHttpResponse intercept(HttpRequest request, byte[] bytes,
														ClientHttpRequestExecution execution) throws IOException {
						request.getHeaders().add("Authorization", "Bearer " + accessToken);
						return execution.execute(request, bytes);
					}
				};
		return interceptor;
	}

	private ClientHttpRequestInterceptor getNoTokenInterceptor() {
		return new ClientHttpRequestInterceptor() {
			@Override
			public ClientHttpResponse intercept(HttpRequest request, byte[] bytes,
												ClientHttpRequestExecution execution) throws IOException {
				throw new IllegalStateException(
						"Can't access the API without an access token");
			}
		};
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
