package com.kallam.middleware.service;

import org.bson.Document;


public interface BookingService {
	
	Document getBookingService(String search, String compCode);
	Document getBrokerBooking(String brokerNo, String compCode);
}
