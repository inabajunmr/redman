package xyz.dgz48.redman.domain.user;

import javax.persistence.*;

import lombok.*;


/**
 * UserEntity.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "user", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"idp_user_name", "idp_type"})})
@EqualsAndHashCode
public class UserEntity {

	/**
	 * このアプリケーション内に置けるUserの識別子.別のリソースとの外部参照のキーはこちらを使う.
	 */
	@Id
	@Column(name = "user_id")
	private String userId;

	/**
	 * Spring securityにとってのUserの識別子.
	 */
	@Column(name = "idp_user_name")
	private String idpUserName;

	/**
	 * メールアドレス.
	 */
	@Column(name = "email")
	private String email;

	/**
	 * Idpの種別.
	 */
	@Column(name = "idp_type")
	@Enumerated(EnumType.STRING)
	private IdpType idpType;

}
