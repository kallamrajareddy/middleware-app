package com.kallam.middleware.service;

import java.util.List;

import com.kallam.middleware.resultmodel.TransBrokers;

public interface TransactionsService {
	
	List<TransBrokers> getTransactions(String search);

}
