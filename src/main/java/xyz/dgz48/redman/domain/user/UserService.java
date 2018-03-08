package xyz.dgz48.redman.domain.user;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Service for {@User}.
 */
@Service
public class UserService {

	/**
	 * Repository for {@User}.
	 */
	@Autowired
	private UserRepository userRepository;

	/**
	 * Find User by idpUserName.
	 *
	 * @param idpUserName key
	 * @return user
	 */
	public Optional<User> findUserByIdpUserName(final String idpUserName) {
		return userRepository.findByIdpUserName(idpUserName);
	}

	/**
	 * Upsert new {@link User}.
	 * @param user register target
	 * @return register user
	 */
	public User saveUser(final User user) {
		return userRepository.save(user);
	}

}
