package xyz.dgz48.redman.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
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
	 * {@link User} factory.
	 */
	@Spy
	private UserFactory userFactory;

	/**
	 * {@link UserEntity} factory.
	 */
	@Spy
	private UserEntityFactory userEntityFactory; // NOPMD

	/**
	 * Mock repository for {@User}.
	 */
	@Mock
	private UserRepository userRepository;

	/**
	 * {@link UserEntity} for test.
	 */
	private final UserEntity testUserEntity = new UserEntity("id", "name", "test@example.com", IdpType.GOOGLE);


	/**
	 * Test for findUserByIdpUserName.(Exist entity).
	 */
	@Test
	public void findUserByIdpUserNameExist() {
		// set up
		when(userRepository.findByIdpUserNameAndIdpType(anyString(), any())).thenReturn(Optional.of(testUserEntity));

		// exercise
		Optional<User> actual = sut.findUserByIdpUserName(testUserEntity.getIdpUserName(), testUserEntity.getIdpType());

		// verify
		assertThat(actual.get()).isEqualTo(userFactory.create(Optional.of(testUserEntity)).get());
	}

	/**
	 * Test for findUserByIdpUserName.(Not exist entity).
	 */
	@Test
	public void findUserByIdpUserNameNotExist() {
		// set up
		when(userRepository.findByIdpUserNameAndIdpType(anyString(), any())).thenReturn(Optional.empty());

		// exercise
		Optional<User> actual = sut.findUserByIdpUserName(testUserEntity.getIdpUserName(), testUserEntity.getIdpType());

		// verify
		assertThat(actual).isEmpty();
	}

	/**
	 * Test for upsertUser.(Not exist entity).
	 */
	@Test
	public void upsertUserInsert() {
		// set up
		when(userRepository.save(testUserEntity)).thenReturn(testUserEntity);

		// exercise
		User actual = sut.saveUser(userFactory.create(testUserEntity));

		// verify
		verify(userRepository, times(1)).save(testUserEntity);
		assertThat(actual).isEqualTo(userFactory.create(testUserEntity));
	}
}
