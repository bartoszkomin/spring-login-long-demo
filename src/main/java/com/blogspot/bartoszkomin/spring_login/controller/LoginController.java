package com.blogspot.bartoszkomin.spring_login.controller;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.blogspot.bartoszkomin.spring_login.repository.UserRepository;

/**
 * @author Bartosz Komin
 * Controller for login points
 */
@RestController
public class LoginController {
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> login(HttpSession session) {

        return Collections.singletonMap("token", session.getId());
	}
}
