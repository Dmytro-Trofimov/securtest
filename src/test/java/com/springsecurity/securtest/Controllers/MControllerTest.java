package com.springsecurity.securtest.Controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class MControllerTest {

	@Autowired
	private MockMvc api;
	
	
	@Test
	void anyOneCanViewPublicEndpoint() throws Exception {
		api.perform(get("/")).
		andExpect(status().isOk())
		.andExpect(content().string(containsStringIgnoringCase("bla")));
	}
	
	@Test
	void notLogedInshouldnotseesecuredEndpoint() throws Exception {
		api.perform(get("/secured")).
		andExpect(status().is4xxClientError());
	}
	
	@Test
	@WithMockUser
	void logedInshouldSeeSecuredEndpoint() throws Exception {
		api.perform(get("/secured")).
		andExpect(status().isOk())
		.andExpect(content().string(containsStringIgnoringCase("secuder")));
	}
	
	
	@Test
	@WithMockUser
	void loged_In_should_See_His_ID_In_Secured_Endpoint() throws Exception {
		api.perform(get("/secured")).
		andExpect(status().isOk())
		.andExpect(content().string(containsStringIgnoringCase("ID: 1")));
	}
	
	@Test
	void anyOneCanNotViewAdminEndpoint() throws Exception {
		api.perform(get("/admin")).
		andExpect(status().is4xxClientError());
	}
	
	@WithMockUser
	@Test
	void SimpleUserCanNotViewAdminEndpoint() throws Exception {
		api.perform(get("/admin")).
		andExpect(status().is4xxClientError());
	}
	
	@WithMockUser(auth = "ROLE_ADMIN")
	@Test
	void AdminCanViewAdminEndpoint() throws Exception {
		api.perform(get("/admin")).
		andExpect(status().isOk())
		.andExpect(content().string(containsStringIgnoringCase("you are an admin")));
	}

}
