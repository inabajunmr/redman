package xyz.dgz48.redman.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test for {@UserFactory}.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserFactoryTest {

	/**
	 * Test target.
	 */
	@Autowired
	private UserFactory sut;

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
		// exercise
		User actual = sut.create(testUserEntity);

		// verify
		assertThat(actual).isEqualTo(testUser);
	}

	/**
	 * create by optional(exist).
	 */
	@Test
	public void createByOptionalExist() {
		// exercise
		Optional<User> actual = sut.create(Optional.of(testUserEntity));

		// verify
		assertThat(actual.get()).isEqualTo(testUser);
	}

	/**
	 * create by optional(empty).
	 */
	@Test
	public void createByOptionalEmpty() {
		// exercise
		Optional<User> actual = sut.create(Optional.empty());

		// verify
		assertThat(actual).isEmpty();

	}

	/**
	 * createWithRandomId by authentication.
	 */
	@Test
	public void createWithRandomId() {
		// set up
		String name = "testName";

		// exercise
		User actual = sut.createWithRandomId(name, "test@example.com", IdpType.GOOGLE);

		// verify
		assertThat(actual.getUserId()).isNotNull();
		assertThat(actual.getIdpUserName()).isEqualTo(name);
		assertThat(actual.getIdpType()).isEqualTo(IdpType.GOOGLE);

	}
}
