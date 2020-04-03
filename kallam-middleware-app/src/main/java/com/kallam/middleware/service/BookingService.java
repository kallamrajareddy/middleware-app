package com.kallam.middleware.service;

import java.util.List;

import org.bson.Document;

import com.kallam.middleware.resultmodel.ResultBookings;


public interface BookingService {
	
	List<ResultBookings> getBookingService(String search);

}
