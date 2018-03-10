package xyz.dgz48.redman.domain.user;

import java.util.Optional;

/**
 * {@User} factory.
 */
public final class UserFactory {

	/**
	 * can not create instance.
	 */
	protected UserFactory() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Create {@User} by {@UserEntity}.
	 * @param entity user entity
	 * @return {@User}
	 */
	public static Optional<User> create(final Optional<UserEntity> entity) {
		return entity.map(e -> new User(e.getUserId(), e.getIdpUserName(), e.getIdpType()));
	}

	/**
	 * Create {@User} by {@UserEntity}.
	 * @param entity user entity
	 * @return {@User}
	 */
	public static User create(final UserEntity entity) {
		return new User(entity.getUserId(), entity.getIdpUserName(), entity.getIdpType());
	}

}
