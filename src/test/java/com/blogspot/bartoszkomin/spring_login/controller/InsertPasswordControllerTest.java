package com.blogspot.bartoszkomin.spring_login.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.Filter;
import javax.validation.constraints.AssertFalse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.blogspot.bartoszkomin.spring_login.Application;
import com.blogspot.bartoszkomin.spring_login.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class InsertPasswordControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext ctx;
	
	@Autowired
    private Filter springSecurityFilterChain;
	
	@Autowired
	UserRepository userRepository;
	
	@Before
	public void setUp() {
		
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
				.addFilters(springSecurityFilterChain)
				.build();
	}
	
	@Test
	public void getInsertOperationNotLoggedIn() throws Exception {

		ResultActions resultAction = this.mockMvc.perform(get("/insert"))
				.andDo(print())
				.andExpect(status().isOk());
	
		assertTrue(userRepository.count() > 0);
		
	}
}
