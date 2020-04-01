package com.kallam.middleware;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import com.kallam.middleware.model.broker.Brokers;
import com.kallam.middleware.model.security.User;
import com.kallam.middleware.repositry.broker.BrokerRepositry;
import com.kallam.middleware.repositry.security.UserRepositry;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class KallamMiddlewareAppApplication implements CommandLineRunner {
	
	@Autowired
	private UserRepositry userRepositry;
	
	@Autowired
	private BrokerRepositry brokerRepositry;

	public static void main(String[] args) {
		SpringApplication.run(KallamMiddlewareAppApplication.class, args);
	}
	
	@Override
    public void run(String... args) throws Exception {
		
        List<User> users = this.userRepositry.findAll();
        System.out.println(users.size());
        List<Brokers> brokers = this.brokerRepositry.findAll();
        System.out.println(brokers.size());
    }

}
