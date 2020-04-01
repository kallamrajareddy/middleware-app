package com.kallam.middleware.repositry.broker;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kallam.middleware.model.broker.Brokers;

public interface BrokerRepositry extends MongoRepository<Brokers, String>{
	
	Brokers findByBrokerName(String brokerName);

}
