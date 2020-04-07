package com.kallam.middleware.controller;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kallam.middleware.helper.MongoLocalDateTime;
import com.kallam.middleware.model.broker.Brokers;
import com.kallam.middleware.repositry.broker.BrokerRepositry;
import com.kallam.middleware.request.model.BrokerRequest;
import com.kallam.middleware.service.BrokerService;

@RestController
@RequestMapping("/api/secured/")
@PreAuthorize("hasAuthority('ROLE_SUPER') or hasAuthority('ROLE_ADMIN')")
public class AccountController {
	
	@Autowired
	private BrokerService brokerService;
	
	@Autowired
	private BrokerRepositry brokerRepositry;

	@RequestMapping(value = "/get-broker-Lst/{searchValue}/{compCode}", method=RequestMethod.GET)
    public List<Brokers> getBrokers(@PathVariable String searchValue, @PathVariable String compCode) {
		MongoLocalDateTime start = MongoLocalDateTime.of(1960, 8, 5, 0, 0, 0);
		return brokerService.getBrokers(searchValue, compCode);
		//return brokerRepositry.findByDobLessThan(new Date());
    }
	
	@RequestMapping(value = "/save-broker", method=RequestMethod.POST)
    public Brokers saveBrokers(@RequestParam("form") String form, @RequestParam("file") MultipartFile custImage) {
		try {
			BrokerRequest brokerRequest = new ObjectMapper().readValue(form, BrokerRequest.class);
			 return brokerService.createBroker(brokerRequest, custImage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
    }
	
	public LocalDateTime convertToLocalDateTimeViaMilisecond(Date dateToConvert) {
		return Instant.ofEpochMilli(dateToConvert.getTime())
		.atZone(ZoneId.systemDefault())
		.toLocalDateTime();
		}
}
