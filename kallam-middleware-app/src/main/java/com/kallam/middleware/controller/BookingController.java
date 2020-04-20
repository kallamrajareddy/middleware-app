package com.kallam.middleware.controller;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;

import org.bson.Document;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kallam.middleware.model.broker.Brokers;
import com.kallam.middleware.request.model.BookingRequest;
import com.kallam.middleware.request.model.DetailBookingRequest;
import com.kallam.middleware.service.BookingService;
import com.mongodb.DBObject;

@RestController
@RequestMapping("/api/secured/")
@PreAuthorize("hasAuthority('ROLE_SUPER') or hasAuthority('ROLE_ADMIN')")
public class BookingController {
	

	@Autowired
	private BookingService bookingService;

	@RequestMapping(value = "/get-booking-Lst/{searchValue}/{compCode}", method=RequestMethod.GET)
    public Document getBrokers(@PathVariable String searchValue, @PathVariable String compCode) {
		searchValue = searchValue.replaceAll("[+]", Matcher.quoteReplacement("\\/"));;
		return bookingService.getBookingService(searchValue, compCode);
    }
	
	@RequestMapping(value = "/get-broker-booking-Lst/{brokerNo}/{compCode}", method=RequestMethod.GET)
	public Document getBrokerBooking(@PathVariable String brokerNo, @PathVariable String compCode) {
		return bookingService.getBrokerBooking(brokerNo, compCode);
	}
	
	@RequestMapping(value = "/get-broker-booking-details", method=RequestMethod.POST)
	public DBObject getBookingDetails(@RequestParam("form") String form) {
		DetailBookingRequest req;
		try {
			req = new ObjectMapper().readValue(form, DetailBookingRequest.class);
			return bookingService.getBookingDetails(req);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/get-booking-recipts", method=RequestMethod.POST)
	public DBObject getBookingRecipts(@RequestParam("form") String form) {
		DetailBookingRequest req;
		try {
			req = new ObjectMapper().readValue(form, DetailBookingRequest.class);
			return bookingService.getBookingRecipts(req);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(value = "/get-broker-bookings-details", method=RequestMethod.POST)
	public List<DBObject> getBookingsDetails(@RequestParam("form") String form) {
		DetailBookingRequest req;
		try {
			req = new ObjectMapper().readValue(form, DetailBookingRequest.class);
			return bookingService.getBookingsDetails(req);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/create-booking", method=RequestMethod.POST)
    public Brokers saveBrokers(@RequestParam("form") String form) {
		try {
			BookingRequest req = new ObjectMapper().readValue(form, BookingRequest.class);
			 return bookingService.createBooking(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
    }

}
