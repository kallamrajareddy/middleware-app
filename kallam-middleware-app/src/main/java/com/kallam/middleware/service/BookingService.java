package com.kallam.middleware.service;

import java.util.List;

import org.bson.Document;

import com.kallam.middleware.model.broker.Brokers;
import com.kallam.middleware.request.model.BookingRequest;
import com.kallam.middleware.request.model.DetailBookingRequest;
import com.mongodb.DBObject;


public interface BookingService {
	
	Document getBookingService(String search, String compCode);
	Document getBrokerBooking(String brokerNo, String compCode);
	Brokers createBooking(BookingRequest req);
	DBObject getBookingDetails(DetailBookingRequest req);
	List<DBObject> getBookingsDetails(DetailBookingRequest req);
	DBObject getBookingRecipts(DetailBookingRequest req);
}
