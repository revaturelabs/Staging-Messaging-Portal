package com.revature.smp.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


/**
 *
 * @author Carlos Rubiano
 *
 */

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@WithMockUser(username="user",password="user")
@SpringBootTest
public class RegistrationControllerTest {

	@Autowired
	private MockMvc mockMvc; // To be able to mock HTTP calls to controller
	

	@Test
	public void test() throws Exception {
		mockMvc
			.perform(post(RegistrationController.REGISTER_USER_URL)
					.content("{\"x\":0}")
					.contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk());
			
	}


}
