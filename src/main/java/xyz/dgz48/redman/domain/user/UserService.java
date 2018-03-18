package xyz.dgz48.redman.domain.user;


import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service for {@link User}.
 */
@Service
@Transactional
@Slf4j
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
	 * {@link UserEntity} factory.
	 */
	@Autowired
	private UserEntityFactory userEntityFactory;

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
		log.info("Save user:{}", user.getIdpUserName(), user.getIdpUserName());
		return userFactory.create(userRepository.save(userEntityFactory.create(user)));
	}

}
