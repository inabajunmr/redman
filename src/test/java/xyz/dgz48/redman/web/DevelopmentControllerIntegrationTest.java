package xyz.dgz48.redman.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


/**
 * Test for {@link DevelopmentController}.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Profile("debug")
public class DevelopmentControllerIntegrationTest {

	/**
	 * MockMvc.
	 */
	@Autowired
	private MockMvc mockMvc;

	/**
	 * Test for /starter.html.
	 *
	 * @throws Exception exception
	 */
	@Test
	@WithMockOAuth2User()
	public void testViewStarter() throws Exception { // NOPMD
		mockMvc.perform(get("/starter.html")).andExpect(status().isOk());
	}

}
