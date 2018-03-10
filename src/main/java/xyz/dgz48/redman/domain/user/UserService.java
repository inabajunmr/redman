package xyz.dgz48.redman.domain.user;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service for {@link User}.
 */
@Service
@Transactional
public class UserService {

	/**
	 * Repository for {@link User}.
	 */
	@Autowired
	private UserRepository userRepository;

	/**
	 * {@link User} factory.
	 */
	@Autowired
	private UserFactory userFactory;

	/**
	 * Find UserEntity by idpUserName.
	 *
	 * @param idpUserName key
	 * @param idpType key
	 * @return user
	 */
	public Optional<User> findUserByIdpUserName(final String idpUserName, final IdpType idpType) {
		return userFactory.create(userRepository.findByIdpUserNameAndIdpType(idpUserName, idpType));
	}

	/**
	 * Upsert new {@link UserEntity}.
	 * @param user register target
	 * @return register user
	 */
	public User saveUser(final User user) {
		return userFactory.create(userRepository.save(new UserEntity(user.getUserId(), user.getIdpUserName(), user.getIdpType())));
	}

}
