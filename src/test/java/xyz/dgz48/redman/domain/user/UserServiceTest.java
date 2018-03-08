package xyz.dgz48.redman.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * Test for {@link UserService}.
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	/**
	 * Test target.
	 */
	@InjectMocks
	private UserService sut;

	/**
	 * Mock repositry for {@User}.
	 */
	@Mock
	private UserRepository userRepository;

	/**
	 * {@link User} for test.
	 */
	private final User testUser = new User("id", "name", IdpType.GOOGLE);


	/**
	 * Test for findUserByIdpUserName.(Exist entity).
	 */
	@Test
	public void findUserByIdpUserNameExist() {
		// set up
		when(userRepository.findByIdpUserName(anyString())).thenReturn(Optional.of(testUser));

		// exercise
		Optional<User> actual = sut.findUserByIdpUserName(testUser.getIdpUserName());

		// verify
		assertThat(actual.get()).isEqualTo(testUser);
	}

	/**
	 * Test for findUserByIdpUserName.(Not exist entity).
	 */
	@Test
	public void findUserByIdpUserNameNotExist() {
		// set up
		when(userRepository.findByIdpUserName(anyString())).thenReturn(Optional.empty());

		// exercise
		Optional<User> actual = sut.findUserByIdpUserName(testUser.getIdpUserName());

		// verify
		assertThat(actual).isEmpty();
	}

	/**
	 * Test for upsertUser.(Not exist entity).
	 */
	@Test
	public void upsertUserInsert() {
		// set up
		when(userRepository.save(testUser)).thenReturn(testUser);

		// exercise
		User actual = sut.saveUser(testUser);

		// verify
		verify(userRepository, times(1)).save(testUser);
		assertThat(actual).isEqualTo(testUser);
	}
}
