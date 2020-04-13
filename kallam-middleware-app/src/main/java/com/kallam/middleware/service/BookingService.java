package com.kallam.middleware.service;

import org.bson.Document;

import com.kallam.middleware.model.broker.Brokers;
import com.kallam.middleware.request.model.BookingRequest;


public interface BookingService {
	
	Document getBookingService(String search, String compCode);
	Document getBrokerBooking(String brokerNo, String compCode);
	Brokers createBooking(BookingRequest req);
}
