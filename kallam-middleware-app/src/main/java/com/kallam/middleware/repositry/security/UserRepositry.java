package com.kallam.middleware.repositry.security;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kallam.middleware.model.security.User;

public interface UserRepositry  extends MongoRepository<User, String>{
	User findByUsername(String username);
}
