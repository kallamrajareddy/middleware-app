package com.kallam.middleware.service;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kallam.middleware.model.broker.Brokers;
import com.kallam.middleware.request.model.BrokerRequest;
import com.mongodb.DBObject;

public interface BrokerService {
	List<Brokers> getBrokers(String search, String compCode);
	
	List<Brokers> brokereBetweenDob(Date from, Date to);
	
	Boolean checkBrokerExists(String name, String compCode);
	
	String getBrokersCount(String compCode);
	
	Brokers createBroker(BrokerRequest brokerRequest, MultipartFile custImage);

	Brokers getBroker(String brokerNo, String compCode);

	Brokers defaultuerStatusUpdate(String brokerNo, String compCode, Boolean status, String updatedBy);

	Brokers updateBroker(BrokerRequest brokerRequest, MultipartFile custImage);
}
