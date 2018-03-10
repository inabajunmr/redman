package xyz.dgz48.redman.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.Test;

/**
 * Test for {@UserFactory}.
 */
public class UserFactoryTest {

	/**
	 * {@User} for test.
	 */
	private final User testUser = new User("id", "name", IdpType.GOOGLE);

	/**
	 * {@UserEntity} for test.
	 */
	private final UserEntity testUserEntity = new UserEntity("id", "name", IdpType.GOOGLE);

	/**
	 * Test for contractor.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void contractor() {
		new UserFactory();
	}

	/**
	 * create.
	 */
	@Test
	public void create() {
		// exercise
		User actual = UserFactory.create(testUserEntity);

		// verify
		assertThat(actual).isEqualTo(testUser);
	}

	/**
	 * create by optional(exist).
	 */
	@Test
	public void createByOptionalExist() {
		// exercise
		Optional<User> actual = UserFactory.create(Optional.of(testUserEntity));

		// verify
		assertThat(actual.get()).isEqualTo(testUser);
	}

	/**
	 * create by optional(empty).
	 */
	@Test
	public void createByOptionalEmpty() {
		// exercise
		Optional<User> actual = UserFactory.create(Optional.empty());

		// verify
		assertThat(actual).isEmpty();

	}

}
