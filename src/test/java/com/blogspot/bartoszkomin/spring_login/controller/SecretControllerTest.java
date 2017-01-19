package com.blogspot.bartoszkomin.spring_login.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import javax.servlet.Filter;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.blogspot.bartoszkomin.spring_login.Application;
import com.blogspot.bartoszkomin.spring_login.model.User;
import com.blogspot.bartoszkomin.spring_login.repository.UserRepository;
import com.blogspot.bartoszkomin.spring_login.service.CustomUserDetailsService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	@Test
//	@WithMockUser
//	@WithUserDetails(value="customUsername", userDetailsServiceBeanName="myUserDetailsService")
	public void getSecretLoggedIn() throws Exception {

		User user = new User();
		user.setName("user");
		user.setPassword(new BCryptPasswordEncoder(16).encode("password"));
		
		userRepository.save(user);
		
		userRepository.save(user);
		
		HttpSession session  = mockMvc.perform(post("/j_spring_security_check")
                .param("j_username", "user")
                .param("j_password", "password"))
                .andDo(print())
                .andExpect(status().isMovedTemporarily())
//                .andExpect(redirectedUrl("/index"))
                .andReturn()
                .getRequest()
                .getSession();

		request.setSession(session);

		SecurityContext securityContext = (SecurityContext)   session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);

		SecurityContextHolder.setContext(securityContext);
		
		ResultActions resultAction = this.mockMvc.perform(get("/secret"))
				.andDo(print())
				.andExpect(status().isOk());
		
	}
	
//	@Test
//	public void getSecretLoggedIn() throws Exception {
//
//		//add user to database
//		User user = new User();
//		user.setName("user");
//		user.setPassword(new BCryptPasswordEncoder(16).encode("password"));
//		
//		userRepository.save(user);
//		
//		ResultActions resultAction = this.mockMvc.perform(get("/secret").with(httpBasic("user","password")))
//				.andDo(print())
//				.andExpect(status().isUnauthorized());
//		
//	}
//	
//	@Configuration
//    @EnableWebMvcSecurity
//    @EnableWebMvc
//    static class Config extends WebSecurityConfigurerAdapter {
//        @Autowired
//        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        	auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder(16));
//        }
//    }
}
