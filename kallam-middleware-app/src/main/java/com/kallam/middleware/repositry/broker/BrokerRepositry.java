package com.kallam.middleware.repositry.broker;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.kallam.middleware.helper.MongoLocalDateTime;
import com.kallam.middleware.model.broker.Brokers;

public interface BrokerRepositry extends MongoRepository<Brokers, String>{
	
	Brokers findByBrokerName(String brokerName);
	
	@Query("{ 'dob' : { $gt: ?0  } }")
	List<Brokers> findByDobLessThan(Date from);
	
	/*@Query(value = "{'brokerName': {$regex : ?0, $options: 'i'}}")
	List<Brokers> findByBrokerNameLike(String brokerName);
*/
}
