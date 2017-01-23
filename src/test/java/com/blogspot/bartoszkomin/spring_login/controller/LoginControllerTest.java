package com.blogspot.bartoszkomin.spring_login.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.blogspot.bartoszkomin.spring_login.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class LoginControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext ctx;
	
	@Autowired
    private Filter springSecurityFilterChain;
	
	@Before
	public void setUp() {
		
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
				.addFilters(springSecurityFilterChain)
				.build();
	}
	
	@Test
	public void postLoginUnauthorized() throws Exception {

		ResultActions resultAction = this.mockMvc.perform(post("/login")
				.contentType(MediaType.APPLICATION_JSON).content(""))
				.andDo(print())
				.andExpect(status().isOk());
		
	}
	
//	@Test
//	@WithUserDetails(userDetailsServiceBeanName="myUserDetailsService")
//	public void postLoginAuthorized() throws Exception {
//
//		ResultActions resultAction = this.mockMvc.perform(post("/login")
//				.contentType(MediaType.APPLICATION_JSON).content(""))
//				.andDo(print())
//				.andExpect(status().isOk());
//		
//	}
}
