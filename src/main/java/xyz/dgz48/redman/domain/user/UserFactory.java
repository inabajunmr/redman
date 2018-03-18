package xyz.dgz48.redman.domain.user;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;


/**
 * {@link User} factory.
 */
@Component
public class UserFactory {

	/**
	 * Create {@link User} by {@link UserEntity}.
	 * @param entity user entity
	 * @return {@link User}
	 */
	public Optional<User> create(final Optional<UserEntity> entity) {
		return entity.map(e -> new User(e.getUserId(), e.getIdpUserName(), e.getEmail(), e.getIdpType()));
	}

	/**
	 * Create {@link User} by {@link UserEntity}.
	 * @param entity user entity
	 * @return {@link User}
	 */
	public User create(final UserEntity entity) {
		return new User(entity.getUserId(), entity.getIdpUserName(), entity.getEmail(), entity.getIdpType());
	}

	/**
	 * Create {@link User} with random UUID.
	 * @param idpUserName user identity from idp
	 * @param email email
	 * @param idpType idpType
	 * @return {@link User}
	 */
	public User createWithRandomId(final String idpUserName, final String email, final IdpType idpType) {
		return new User(UUID.randomUUID().toString(), idpUserName, email, idpType);

	}
}
