package xyz.dgz48.redman.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Repository for user.
 */
interface UserRepository extends JpaRepository<User, String> {

	/**
	 * Find {@link User} resource by idpUserName(Spring security username).
	 * @param idpUserName idpUserName
	 * @param idpType idpType
	 * @return user
	 */
	Optional<User> findByIdpUserNameAndIdpType(String idpUserName, IdpType idpType);
}
