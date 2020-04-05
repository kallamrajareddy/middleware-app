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
	public List<Brokers> getBrokers(String search, String compCode) {
		List<Brokers> brokers = mongoTemplate.find(query(where("").orOperator(where("brokerName").regex(".*"+search+".*", "i")
				,where("addr1").regex(".*"+search+".*", "i")
				,where("addr2").regex(".*"+search+".*", "i")
				,where("addr3").regex(".*"+search+".*", "i")
				,where("aadharNo").regex(".*"+search+".*", "i")
				,where("mobileNo").regex(".*"+search+".*", "i")).andOperator(where("companyCode").is(compCode))), Brokers.class);
		//return brokereBetweenDob(new Date(), new Date());
		return brokers;
	}


	@Override
	public List<Brokers> brokereBetweenDob(Date from, Date to) {
		List<Brokers> brokers = mongoTemplate.find(query(where("dob").lte(from)), Brokers.class);
		return brokers;
	}
	

	@Override
	public Boolean checkBrokerExists(String name, String compCode) {
		return  mongoTemplate.exists(query(where("brokerName").is(name).andOperator(where("companyCode").is(compCode))), Brokers.class);
	}

	@Override
	public Long getBrokersCount(String compCode) {
		 
		return mongoTemplate.count(query(where("").andOperator(where("companyCode").is(compCode))), Brokers.class);
	}

}
