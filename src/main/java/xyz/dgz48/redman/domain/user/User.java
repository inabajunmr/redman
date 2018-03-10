package xyz.dgz48.redman.domain.user;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * User Dommain Object.
 */
@AllArgsConstructor
@Getter
@EqualsAndHashCode
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
	 * Idpの種別.
	 */
	private IdpType idpType;

}
