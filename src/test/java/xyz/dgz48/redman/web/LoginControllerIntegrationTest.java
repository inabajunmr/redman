package xyz.dgz48.redman.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import xyz.dgz48.redman.domain.user.*;



/**
 * Test for {@link LoginController}.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerIntegrationTest {

	/**
	 * MockMvc.
	 */
	@Autowired
	private MockMvc mockMvc;

	/**
	 * {@link UserService} for assertion.
	 */
	@Autowired
	private UserService userService;

	/**
	 * {@link UserRepository} for delete test data.
	 */
	@Autowired
	private UserRepository userRepository;

	/**
	 * Test for index page before login (redirect to login page).
	 *
	 * @throws Exception exception
	 */
	@Test
	public void indexBeforeLogin() throws Exception { // NOPMD
		mockMvc.perform(get("/")).andExpect(status().is3xxRedirection());
	}

	/**
	 * Test for index page after login(registered user).
	 *
	 * @throws Exception exception
	 */
	@Test
	@WithMockOAuth2User()
	public void indexAfterLogin() throws Exception { // NOPMD

		Optional<User> userBeforeFirstLogin = userService.findUserByIdpUserName("testsub", IdpType.GOOGLE);
		if (userBeforeFirstLogin.isPresent()) {
			userRepository.deleteById(userBeforeFirstLogin.get().getUserId());
		}

		mockMvc.perform(get("/")).andExpect(status().isOk());

		// ログイン後ユーザが登録済みとなること
		Optional<User> userAfterFirstLogin = userService.findUserByIdpUserName("testsub", IdpType.GOOGLE);
		assertThat(userAfterFirstLogin).isNotEmpty();
	}

	/**
	 * Test for login page.
	 *
	 * @throws Exception exception
	 */
	@Test
	public void viewLogin() throws Exception { // NOPMD
		mockMvc.perform(get("/login")).andExpect(status().isOk());
	}
}
