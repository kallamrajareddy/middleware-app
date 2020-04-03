package com.kallam.middleware.controller;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kallam.middleware.resultmodel.ResultBookings;
import com.kallam.middleware.service.BookingService;

@RestController
@RequestMapping("/api/secured/")
@PreAuthorize("hasAuthority('ROLE_SUPER') or hasAuthority('ROLE_ADMIN')")
public class BookingController {
	

	@Autowired
	private BookingService bookingService;

	@RequestMapping(value = "/get-booking-Lst/{searchValue}", method=RequestMethod.GET)
    public List<ResultBookings> getBrokers(@PathVariable String searchValue) {
		return bookingService.getBookingService(searchValue);
    }

}
