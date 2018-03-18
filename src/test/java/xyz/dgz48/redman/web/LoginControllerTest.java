package xyz.dgz48.redman.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import xyz.dgz48.redman.domain.user.IdpType;
import xyz.dgz48.redman.domain.user.UserFactory;
import xyz.dgz48.redman.domain.user.UserService;

/**
 * Test for {@link LoginController}.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

	/**
	 * Test target.
	 */
	@InjectMocks
	private LoginController loginController; // NOPMD

	/**
	 * {@link UserService} for assertion.
	 */
	@MockBean
	private UserService userService; // NOPMD

	/**
	 * MockMVC.
	 */
	@Autowired
	private MockMvc mockMvc;

	/**
	 * Factory for user.
	 *
	 */
	@Autowired
	private UserFactory userFactory; // NOPMD

	/**
	 * Test for index page after login(first time user).
	 *
	 * @throws Exception exception
	 */
	@Test
	@WithMockOAuth2User()
	public void indexAfterLoginFirstTime() throws Exception { // NOPMD

		// setup
		when(userService.findUserByIdpUserName("testsub", IdpType.GOOGLE)).thenReturn(Optional.empty());

		when(userService.saveUser(any()))
				.thenReturn(userFactory.createWithRandomId("testSub", "test@example.com", IdpType.GOOGLE));

		// exercise
		mockMvc.perform(get("/")).andExpect(status().isOk());

		// verify
		verify(userService, times(1)).saveUser(any());
	}

	/**
	 * Test for index page after login(registered user).
	 *
	 * @throws Exception exception
	 */
	@Test
	@WithMockOAuth2User()
	public void indexAfterLoginRegisterdUser() throws Exception { // NOPMD

		// setup
		when(userService.findUserByIdpUserName("testsub", IdpType.GOOGLE))
				.thenReturn(Optional.of(userFactory.createWithRandomId("testSub", "test@example.com", IdpType.GOOGLE)));

		// exercise
		mockMvc.perform(get("/")).andExpect(status().isOk());

		// verify
		verify(userService, times(0)).saveUser(any());
	}

}
