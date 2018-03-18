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
		userInfo.put("picture", "http://example.com/user.jpg");
	}

	/**
	 * Extract email by Google.
	 */
	@Test
	public void extractEmailByGoogle() {
		// set up
		UserInfoExtractor sut = new UserInfoExtractor(IdpType.GOOGLE);

		// exercise
		String actual = sut.getEmail(userInfo);

		// verify
		assertThat(actual).isEqualTo("test@example.com");
	}

	/**
	 * Extract email by GitHub.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void extractEmailByGitHub() {
		// set up
		UserInfoExtractor sut = new UserInfoExtractor(IdpType.GITHUB);

		// exercise
		String actual = sut.getEmail(userInfo);

		// verify
		assertThat(actual).isNull();
	}

	/**
	 * Extract by Other.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void extractEmailByOther() {
		// set up
		UserInfoExtractor sut = new UserInfoExtractor(null);

		// exercise
		String actual = sut.getEmail(userInfo);

		// verify
		assertThat(actual).isNull();
	}

	/**
	 * Extract picture url by Google.
	 */
	@Test
	public void extractPictureUrlByGoogle() {
		// set up
		UserInfoExtractor sut = new UserInfoExtractor(IdpType.GOOGLE);

		// exercise
		String actual = sut.getPictureUrl(userInfo);

		// verify
		assertThat(actual).isEqualTo("http://example.com/user.jpg");
	}

	/**
	 * Extract picture url by GitHub.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void extractPictureUrlByGitHub() {
		// set up
		UserInfoExtractor sut = new UserInfoExtractor(IdpType.GITHUB);

		// exercise
		String actual = sut.getPictureUrl(userInfo);

		// verify
		assertThat(actual).isNull();
	}

	/**
	 * Extract picture url by Other.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void extractPictureUrlByOther() {
		// set up
		UserInfoExtractor sut = new UserInfoExtractor(null);

		// exercise
		String actual = sut.getPictureUrl(userInfo);

		// verify
		assertThat(actual).isNull();
	}
}
