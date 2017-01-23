package com.blogspot.bartoszkomin.spring_login.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.blogspot.bartoszkomin.spring_login.Application;
import com.blogspot.bartoszkomin.spring_login.repository.UserRepository;
import com.blogspot.bartoszkomin.spring_login.service.CustomUserDetailsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class SecretControllerTest {
	
	@Autowired
	private WebApplicationContext ctx;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private static CustomUserDetailsService userDetailsService;
	
	@Autowired
    private Filter springSecurityFilterChain;
	
	@Autowired
    private MockHttpServletRequest request;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
				.addFilters(springSecurityFilterChain)
				.build();
	}
	
	@Test
	public void getSecretNotLoggedIn() throws Exception {

		ResultActions resultAction = this.mockMvc.perform(get("/secret"))
				.andDo(print())
				.andExpect(status().isUnauthorized());
		
	}
	
}
