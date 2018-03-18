package xyz.dgz48.redman.domain.user;

import org.springframework.stereotype.Component;

/**
 * {@link UserEntity} factory.
 */
@Component
public class UserEntityFactory {

	/**
	 * Create {@link UserEntity} by {@link User}.
	 * @param user user
	 * @return {@link User}
	 */
	public UserEntity create(final User user) {
		return new UserEntity(user.getUserId(), user.getIdpUserName(), user.getEmail(), user.getIdpType());
	}

}
