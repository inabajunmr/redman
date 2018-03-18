package xyz.dgz48.redman.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Test for {@link UserInfoExtractor}.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserInfoExtractorTest {

	/**
	 * userInfo.
	 */
	private final Map<String, Object> userInfo = new HashMap<>();

	/**
	 * Init.
	 */
	@Before
	public void init() {
		userInfo.put("email", "test@example.com");
	}

	/**
	 * Normalize by Google.
	 */
	@Test
	public void normalizeByGoogle() {
		// set up
		UserInfoExtractor sut = new UserInfoExtractor(IdpType.GOOGLE);

		// exercise
		String actual = sut.getEmail(userInfo);

		// verify
		assertThat(actual).isEqualTo("test@example.com");
	}

	/**
	 * Normalize by GitHub.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void normalizeByGitHub() {
		// set up
		UserInfoExtractor sut = new UserInfoExtractor(IdpType.GITHUB);

		// exercise
		String actual = sut.getEmail(userInfo);

		// verify
		assertThat(actual).isNull();
	}

	/**
	 * Normalize by GitHub.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void normalizeByOther() {
		// set up
		UserInfoExtractor sut = new UserInfoExtractor(null);

		// exercise
		String actual = sut.getEmail(userInfo);

		// verify
		assertThat(actual).isNull();
	}
}
