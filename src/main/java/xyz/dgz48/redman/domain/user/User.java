package xyz.dgz48.redman.domain.user;

import lombok.Value;

/**
 * User Dommain Object.
 */
@Value
public class User {

	/**
	 * このアプリケーション内に置けるUserの識別子.別のリソースとの外部参照のキーはこちらを使う.
	 */
	private String userId;

	/**
	 * Spring securityにとってのUserの識別子.
	 */
	private String idpUserName;

	/**
	 * メールアドレス.
	 */
	private String email;

	/**
	 * Idpの種別.
	 */
	private IdpType idpType;

}
