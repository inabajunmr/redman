package xyz.dgz48.redman.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for user.
 */
public interface UserRepository extends JpaRepository<User, String> {

	/**
	 * Find {@link User} resource by idpUserName(Spring security username).
	 * @param idpUserName idpUserName
	 * @return user
	 */
	public User findByIdpUserName(String idpUserName);
}
