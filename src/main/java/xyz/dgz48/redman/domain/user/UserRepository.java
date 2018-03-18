package xyz.dgz48.redman.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Repository for user.
 */
public interface UserRepository extends JpaRepository<UserEntity, String> {

	/**
	 * Find {@link UserEntity} resource by idpUserName(Spring security username).
	 * @param idpUserName idpUserName
	 * @param idpType idpType
	 * @return user
	 */
	Optional<UserEntity> findByIdpUserNameAndIdpType(String idpUserName, IdpType idpType);
}
