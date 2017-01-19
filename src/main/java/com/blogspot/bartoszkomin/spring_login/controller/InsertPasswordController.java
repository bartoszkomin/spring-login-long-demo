package com.blogspot.bartoszkomin.spring_login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blogspot.bartoszkomin.spring_login.model.User;
import com.blogspot.bartoszkomin.spring_login.repository.UserRepository;

/**
 * @author Bartosz Komin
 * Controller for secret resources entry points
 */
@RestController
public class InsertPasswordController {

	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
    public String insertPass() {
		User user = new User();
		user.setName("user");
		user.setPassword(encryptPassword("password"));
		
		userRepository.save(user);
		
        return "inserted";
    }
	
	/**
     * Method to encrypt user password
     * @param password - readable password
     * @return String - encrypted password
     */
    private String encryptPassword(String password) {

        String encryptedPassword = null;

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(16);
        encryptedPassword = bCryptPasswordEncoder.encode(password);

        return encryptedPassword;
    }
}
