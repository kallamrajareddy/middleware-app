package com.kallam.middleware.controller;

import java.util.regex.Matcher;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kallam.middleware.service.BookingService;

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

}
