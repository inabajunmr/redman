package xyz.dgz48.redman.web;

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
 * Test for {@link ResourceViewController}.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ResourceViewControllerTest {

	/**
	 * MockMvc.
	 */
	@Autowired
	private MockMvc mockMvc;

	/**
	 * Test for resource list page.
	 *
	 * @throws Exception exception
	 */
	@Test
	@WithMockOAuth2User()
	public void testViewList() throws Exception { // NOPMD
		mockMvc.perform(get("/resource/list.html")).andExpect(status().isOk());
	}

	/**
	 * Test for resource detail page.
	 *
	 * @throws Exception exception
	 */
	@Test
	@WithMockOAuth2User()
	public void testViewDetail() throws Exception { // NOPMD
		mockMvc.perform(get("/resource/1/detail.html")).andExpect(status().isOk());
	}
}
