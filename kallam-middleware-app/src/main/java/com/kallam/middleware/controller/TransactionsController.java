package com.kallam.middleware.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kallam.middleware.model.broker.Brokers;
import com.kallam.middleware.request.model.NewRecipt;
import com.kallam.middleware.service.BookingService;

@RestController
@RequestMapping("/api/secured/")
@PreAuthorize("hasAuthority('ROLE_SUPER') or hasAuthority('ROLE_ADMIN')")
public class TransactionsController {
	
	@Autowired
	private BookingService bookingService;
	
	@RequestMapping(value = "/add-recipt", method=RequestMethod.POST)
    public Brokers addRecipt(@RequestParam("form") String form) {
		try {
			NewRecipt req = new ObjectMapper().readValue(form, NewRecipt.class);
			 return bookingService.addRecipt(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
    }
	@RequestMapping(value = "/delete-recipt", method=RequestMethod.POST)
	public Brokers deleteRecipt(@RequestParam("form") String form) {
		try {
			NewRecipt req = new ObjectMapper().readValue(form, NewRecipt.class);
			return bookingService.deleteRecipt(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
