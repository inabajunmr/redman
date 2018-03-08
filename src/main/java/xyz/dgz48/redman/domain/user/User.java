package xyz.dgz48.redman.domain.user;

import javax.persistence.*;

import lombok.*;


/**
 * User.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
//@Getter
@Data
@Table(name = "user")
public class User {

	/**
	 * このアプリケーション内に置けるUserの識別子.別のリソースとの外部参照のキーはこちらを使う.
	 */
	@Id
	@Column(name = "user_id")
	private String userId;

	/**
	 * Spring securityにとってのUserの識別子.
	 */
	@Column(unique = true, name = "idp_user_name")
	private String idpUserName;

	/**
	 * Idpの種別.
	 */
	@Column(name = "idp_type")
	@Enumerated(EnumType.STRING)
	private IdpType idpType;
}
