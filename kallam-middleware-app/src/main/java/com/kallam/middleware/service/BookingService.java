package com.kallam.middleware.service;

import org.bson.Document;


public interface BookingService {
	
	Document getBookingService(String search, String compCode);
}
