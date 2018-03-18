package xyz.dgz48.redman.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Test for {@UserRepository}.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

	/**
	 * Test data.
	 */
	private final UserEntity expect = new UserEntity("test-id", "test-user-name", "test@example.com", IdpType.GOOGLE);

	/**
	 * Test target.
	 */
	@Autowired
	private UserRepository sut;

	/**
	 * Test for findByIdpUserName.
	 *
	 * @throws Exception exception
	 */
	@Test
	public void testFindByIdpUserNameExist() throws Exception {
		// set up
		this.sut.save(expect);

		// exercise
		Optional<UserEntity> actual = this.sut.findByIdpUserNameAndIdpType(expect.getIdpUserName(), expect.getIdpType());

		// verify
		assertThat(actual.get()).isEqualTo(expect);
	}

	/**
	 * Test for findByIdpUserName.
	 *
	 * @throws Exception exception
	 */
	@Test
	public void testFindByIdpUserNameNotExist() throws Exception {
		// exercise
		Optional<UserEntity> actual = this.sut.findByIdpUserNameAndIdpType("test-user-name-diff", IdpType.GITHUB);

		// verify
		assertThat(actual).isEmpty();
	}

	/**
	 * Test for findByIdpUserName.
	 *
	 * @throws Exception exception
	 */
	@Test
	public void testFindByIdpUserNameNotExistMatchOnlyIdpUserName() throws Exception {
		// exercise
		this.sut.save(expect);

		// exercise
		Optional<UserEntity> actual = this.sut.findByIdpUserNameAndIdpType(expect.getIdpUserName(), IdpType.GITHUB);

		// verify
		assertThat(actual).isEmpty();
	}

	/**
	 * Test for findByIdpUserName.
	 *
	 * @throws Exception exception
	 */
	@Test
	public void testFindByIdpUserNameNotExistMatchOnlyIdpType() throws Exception {
		// exercise
		this.sut.save(expect);

		// exercise
		Optional<UserEntity> actual = this.sut.findByIdpUserNameAndIdpType("test-user-name-diff", expect.getIdpType());

		// verify
		assertThat(actual).isEmpty();
	}
}
