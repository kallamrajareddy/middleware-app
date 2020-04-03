package com.kallam.middleware.service;

import java.util.Date;
import java.util.List;

import com.kallam.middleware.model.broker.Brokers;

public interface BrokerService {
	List<Brokers> getBrokers(String search);
	List<Brokers> brokereBetweenDob(Date from, Date to);
}
