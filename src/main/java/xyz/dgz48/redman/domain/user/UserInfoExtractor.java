package xyz.dgz48.redman.domain.user;

import java.util.Arrays;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

/**
 * 各Idpから取得できるユーザ情報を正規化するクラス.
 */
@Component
@Slf4j
public class UserInfoExtractor {

	@Autowired
	private OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

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

			GitHubEmail[] forObject = restTemplate.getForObject("https://api.github.com/user/emails", GitHubEmail[].class);
			return Arrays.stream(forObject).filter(o -> o.isPrimary()).findFirst().get().getEmail();
		}

		throw new UnsupportedOperationException("null is not implemented idp.");
	}

	/**
	 * Response for https://api.github.com/user/emails.
	 */
	@Value
	static class GitHubEmail{

		/**_
		 * email.
		 */
		private String email;

		/**_
		 * verified.
		 */
		private boolean verified;

		/**_
		 * primary.
		 */
		private boolean primary;

		/**_
		 * visibility.
		 */
		private String visibility;

	}

	private ClientHttpRequestInterceptor
	getBearerTokenInterceptor(String accessToken) {
		ClientHttpRequestInterceptor interceptor =
				(request, bytes, execution) -> {
					request.getHeaders().add("Authorization", "Bearer " + accessToken);
					return execution.execute(request, bytes);
				};
		return interceptor;
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
			return String.valueOf(oAuth2AuthenticationToken.getPrincipal().getAttributes().get("avatar_url"));
		}

		throw new UnsupportedOperationException("null is not implemented idp.");
	}

}
