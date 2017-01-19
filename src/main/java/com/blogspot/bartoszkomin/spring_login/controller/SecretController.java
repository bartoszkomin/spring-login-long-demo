package com.blogspot.bartoszkomin.spring_login.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bartosz Komin
 * Controller for secret resources entry points
 */
@RestController
public class SecretController {

	@RequestMapping(value = "/secret", method = RequestMethod.GET)
    public String viewSecret() {
        return "secret content";
    }
}
