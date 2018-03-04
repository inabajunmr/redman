package xyz.dgz48.redman.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


/**
 * Test for {@link LoginController}.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

	/**
	 * MockMvc.
	 */
	@Autowired
	private MockMvc mockMvc;

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
	 * Test for index page after login.
	 *
	 * @throws Exception exception
	 */
	@Test
	@WithMockOAuth2User()
	public void indexAfterLogin() throws Exception { // NOPMD
		mockMvc.perform(get("/")).andExpect(status().isOk());
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
