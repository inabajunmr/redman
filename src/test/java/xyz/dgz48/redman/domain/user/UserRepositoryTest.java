package xyz.dgz48.redman.domain.user;

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
	public void testFindByIdpUserName() throws Exception {
		this.sut.save(new User("test-id", "test-user-name", IdpType.GOOGLE));
	}
}
