package com.blogspot.bartoszkomin.spring_login.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogspot.bartoszkomin.spring_login.model.User;

/**
 * @author Bartosz Komin
 * ???
 */
@Repository
public interface UserRepository extends JpaRepository<User, Serializable> {
	
	User findByName(String name);

}
