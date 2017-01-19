package com.blogspot.bartoszkomin.spring_login.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blogspot.bartoszkomin.spring_login.repository.UserRepository;


@Service
public class CustomUserDetailsService  implements UserDetailsService {

	@Autowired
	private UserRepository repository;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = null;

		if (username == null || username.isEmpty()) {

			throw new UsernameNotFoundException("User not found");
		}
		
		com.blogspot.bartoszkomin.spring_login.model.User u = repository.findByName(username);
		
		if (u == null) {

			throw new UsernameNotFoundException("User " + username + " not found");
		}

		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		user = new User(u.getName(), u.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, getAuthorities(null));

		return user;
	}

	private List<SimpleGrantedAuthority> getAuthorities(String role) {
        List<SimpleGrantedAuthority> authList = new ArrayList<SimpleGrantedAuthority>();
        authList.add(new SimpleGrantedAuthority("ROLE_USER"));

        return authList;
    }
}
