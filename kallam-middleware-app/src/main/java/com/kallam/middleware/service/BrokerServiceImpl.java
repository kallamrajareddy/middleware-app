package com.kallam.middleware.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import com.kallam.middleware.model.broker.Brokers;

@Service
public class BrokerServiceImpl implements BrokerService{
	
	@Autowired
	private MongoTemplate mongoTemplate;
	

	@Override
	public List<Brokers> getBrokers(String search) {
		List<Brokers> brokers = mongoTemplate.find(query(where("").orOperator(where("brokerName").regex(".*"+search+".*", "i")
				,where("addr1").regex(".*"+search+".*", "i")
				,where("addr2").regex(".*"+search+".*", "i")
				,where("addr3").regex(".*"+search+".*", "i")
				,where("aadharNo").regex(".*"+search+".*", "i")
				,where("mobileNo").regex(".*"+search+".*", "i"))), Brokers.class);
		return brokereBetweenDob(new Date(), new Date());
	}


	@Override
	public List<Brokers> brokereBetweenDob(Date from, Date to) {
		List<Brokers> brokers = mongoTemplate.find(query(where("dob").lte(from)), Brokers.class);
		return brokers;
	}
	

	@Override
	public Boolean checkBrokerExists(String name) {
		return  mongoTemplate.exists(query(where("brokerName").is(name)), Brokers.class);
	}

	@Override
	public Long getBrokersCount() {
		 
		return mongoTemplate.count(query(where("")), Brokers.class);
	}

}
