package xyz.dgz48.redman.domain.user;

import java.util.Optional;

import org.springframework.stereotype.Component;


/**
 * {@User} factory.
 */
@Component
public class UserFactory {

	/**
	 * Create {@User} by {@UserEntity}.
	 * @param entity user entity
	 * @return {@User}
	 */
	public Optional<User> create(final Optional<UserEntity> entity) {
		return entity.map(e -> new User(e.getUserId(), e.getIdpUserName(), e.getIdpType()));
	}

	/**
	 * Create {@User} by {@UserEntity}.
	 * @param entity user entity
	 * @return {@User}
	 */
	public User create(final UserEntity entity) {
		return new User(entity.getUserId(), entity.getIdpUserName(), entity.getIdpType());
	}

}
