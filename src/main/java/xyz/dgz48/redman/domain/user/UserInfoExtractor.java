package xyz.dgz48.redman.domain.user;

import java.util.Map;

import lombok.RequiredArgsConstructor;

/**
 * 各Idpから取得できるユーザ情報を正規化するクラス.
 */
@RequiredArgsConstructor
public class UserInfoExtractor {

	/**
	 * Idpの種別.
	 */
	private final IdpType idpType;

	/**
	 * {@link IdpType}に合わせた方法でメールアドレスを抽出する.
	 * @param userInfoAttributes userInfo
	 * @return メールアドレス
	 */
	public String getEmail(final Map<String, Object> userInfoAttributes) {

		if (idpType == IdpType.GOOGLE) {
			return String.valueOf(userInfoAttributes.get("email"));
		}

		if (idpType == IdpType.GITHUB) {
			throw new UnsupportedOperationException(String.format("%s is not implemented idp.", idpType.name()));
		}

		throw new UnsupportedOperationException("null is not implemented idp.");
	}

	/**
	 * {@link IdpType}に合わせた方法でユーザの画像URLを抽出する.
	 * @param userInfoAttributes userInfo
	 * @return 画像のURL
	 */
	public String getPictureUrl(final Map<String, Object> userInfoAttributes) {

		if (idpType == IdpType.GOOGLE) {
			return String.valueOf(userInfoAttributes.get("picture"));
		}

		if (idpType == IdpType.GITHUB) {
			throw new UnsupportedOperationException(String.format("%s is not implemented idp.", idpType.name()));
		}

		throw new UnsupportedOperationException("null is not implemented idp.");
	}

}
