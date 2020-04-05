package com.kallam.middleware.service;

import java.util.Date;
import java.util.List;

import com.kallam.middleware.model.broker.Brokers;

public interface BrokerService {
	List<Brokers> getBrokers(String search, String compCode);
	List<Brokers> brokereBetweenDob(Date from, Date to);
	
	Boolean checkBrokerExists(String name, String compCode);
	
	Long getBrokersCount(String compCode);
}
