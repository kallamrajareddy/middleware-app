package com.kallam.middleware.controller;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kallam.middleware.helper.MongoLocalDateTime;
import com.kallam.middleware.model.broker.Brokers;
import com.kallam.middleware.repositry.broker.BrokerRepositry;
import com.kallam.middleware.service.BrokerService;

@RestController
@RequestMapping("/api/secured/")
@PreAuthorize("hasAuthority('ROLE_SUPER') or hasAuthority('ROLE_ADMIN')")
public class AccountController {
	
	@Autowired
	private BrokerService brokerService;
	
	@Autowired
	private BrokerRepositry brokerRepositry;

	@RequestMapping(value = "/get-broker-Lst/{searchValue}", method=RequestMethod.GET)
    public List<Brokers> getBrokers(@PathVariable String searchValue) {
		MongoLocalDateTime start = MongoLocalDateTime.of(1960, 8, 5, 0, 0, 0);
		//return brokerService.getBrokers(searchValue);
		return brokerRepositry.findByDobLessThan(new Date());
    }
	public LocalDateTime convertToLocalDateTimeViaMilisecond(Date dateToConvert) {
		return Instant.ofEpochMilli(dateToConvert.getTime())
		.atZone(ZoneId.systemDefault())
		.toLocalDateTime();
		}
}
