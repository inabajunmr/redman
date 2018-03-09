package xyz.dgz48.redman.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


/**
 * Test for {@link ResourceNewController}.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ResourceNewControllerTest {

	/**
	 * MockMvc.
	 */
	@Autowired
	private MockMvc mockMvc;

	/**
	 * Test for resource registration input page.
	 *
	 * @throws Exception exception
	 */
	@Test
	@WithMockOAuth2User()
	public void testViewInput() throws Exception { // NOPMD
		mockMvc.perform(get("/resource/new/input.html")).andExpect(status().isOk());
	}

	/**
	 * Test for resource registration input validation endpoint.
	 *
	 * @throws Exception exception
	 */
	@Test
	@WithMockOAuth2User()
	public void testValidate() throws Exception { // NOPMD
		mockMvc.perform(post("/resource/new/input.html").with(csrf())).andExpect(status().is3xxRedirection());
	}

	/**
	 * Test for resource registration confirm page.
	 *
	 * @throws Exception exception
	 */
	@Test
	@WithMockOAuth2User()
	public void testViewConfirm() throws Exception { // NOPMD
		mockMvc.perform(get("/resource/new/confirm.html")).andExpect(status().isOk());
	}

	/**
	 * Test for resource registration endpoint.
	 *
	 * @throws Exception exception
	 */
	@Test
	@WithMockOAuth2User()
	public void testRegister() throws Exception { // NOPMD
		mockMvc.perform(post("/resource/new/confirm.html").with(csrf())).andExpect(status().is3xxRedirection());
	}

	/**
	 * Test for resource registration complete page.
	 *
	 * @throws Exception exception
	 */
	@Test
	@WithMockOAuth2User()
	public void testViewComplete() throws Exception { // NOPMD
		mockMvc.perform(get("/resource/new/complete.html")).andExpect(status().isOk());
	}

}
