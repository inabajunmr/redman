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
		User expect = new User("test-id", "test-user-name", IdpType.GOOGLE);
		this.sut.save(expect);

		// exercise
		Optional<User> actual = this.sut.findByIdpUserName("test-user-name");

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
		Optional<User> actual = this.sut.findByIdpUserName("test-user-name");

		// verify
		assertThat(actual).isEmpty();
	}
}
