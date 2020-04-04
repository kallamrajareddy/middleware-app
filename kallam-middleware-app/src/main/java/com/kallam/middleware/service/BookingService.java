package com.kallam.middleware.service;

import java.util.List;

import com.kallam.middleware.resultmodel.BrokerBookings;


public interface BookingService {
	
	List<BrokerBookings> getBookingService(String search);

}
