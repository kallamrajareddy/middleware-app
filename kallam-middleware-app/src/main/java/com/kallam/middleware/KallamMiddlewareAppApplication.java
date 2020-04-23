package com.kallam.middleware;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import com.kallam.middleware.model.broker.Brokers;
import com.kallam.middleware.repositry.broker.BrokerRepositry;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class KallamMiddlewareAppApplication implements CommandLineRunner {

	@Autowired
	BrokerRepositry brokerRepositry;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(KallamMiddlewareAppApplication.class, args);
	}
	
	@Override
    public void run(String... args) throws Exception {
		String search ="9704427864";
		
//        //List<User> users = this.userRepositry.findAll();
//        List<User> users = securityTemplate.find(query(where("username").regex(".*nisT.*","i")),User.class);
//        //User user = securityTemplate.findOne(query(where("username").is("KallamRajaReddy")), User.class);
//        System.out.println(users.size());
        //List<Brokers> brokers = this.brokerRepositry.findByBrokerNameLike("ABDUN");//regex("/.*ABDU.*/")
//        List<Brokers> brokers = mongoTemplate.find(query(where("").orOperator(where("brokerName").regex(".*a.*", "i"),where("addr2").regex(".*15.*", "i"))).limit(400), Brokers.class);
        //List<Brokers> brokers = this.brokerRepositry.findAll();
		//List<Brokers> brokers = mongoTemplate.find(query((where("brokerName").regex(".*a.*", "i"))).limit(400), Brokers.class);
		/*Brokers brokers = mongoTemplate.findOne(query(where("").orOperator(where("brokerName").regex(".*"+search+".*", "i")
				,where("mobileNo").regex(".*"+search+".*", "i"))), Brokers.class);
		brokers.setCreatedDt(convertToLocalDateTimeViaMilisecond(new Date()));
		brokers.setUpdatedDt(convertToLocalDateTimeViaMilisecond(new Date()));
		mongoTemplate.save(brokers);*/
		//System.out.println(brokers.size());
		
    }

}
