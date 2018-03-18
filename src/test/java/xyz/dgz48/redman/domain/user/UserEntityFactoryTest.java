package xyz.dgz48.redman.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test for {@UserEntityFactory}.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserEntityFactoryTest {

	/**
	 * Test target.
	 */
	@Autowired
	private UserEntityFactory sut;


	/**
	 * {@User} for test.
	 */
	private final User testUser = new User("id", "name", "test@example.com", IdpType.GOOGLE);

	/**
	 * {@UserEntity} for test.
	 */
	private final UserEntity testUserEntity = new UserEntity("id", "name", "test@example.com", IdpType.GOOGLE);


	/**
	 * create.
	 */
	@Test
	public void create() {
		UserEntity actual = sut.create(testUser);

		// verify
		assertThat(actual).isEqualTo(testUserEntity);
	}

}
