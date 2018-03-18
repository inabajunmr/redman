package xyz.dgz48.redman.domain.user;

import java.io.Serializable;
import lombok.Value;


/**
 * 各Idpから取得できるユーザ情報を正規化したユーザ情報.
 *
 * <p>Idpによって取得できるユーザ情報の構造は異なるため、本クラスで統一化する.<br>
 * 本クラスは画面データ用にmodelにセットして使用することを想定している.</p>
 *
 */
@Value
public class UserInfo implements Serializable {

	/**
	 * SuppressWarnings.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * このアプリケーション内に置けるUserの識別子.別のリソースとの外部参照のキーはこちらを使う.
	 */
	private String userId;

	/**
	 * メールアドレス.
	 */
	private String email;
}
