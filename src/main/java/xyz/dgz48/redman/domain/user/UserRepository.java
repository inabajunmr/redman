package xyz.dgz48.redman.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for user.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

	/**
	 * Find {@link User} resource by idpUserName
	 * @param idpUserName
	 * @return
	 */
	public User findByIdpUserName(String idpUserName);
}
