package com.kallam.middleware.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kallam.middleware.model.MasterData;
import com.kallam.middleware.service.BrokerService;
import com.kallam.middleware.service.MasterService;

@RestController
@RequestMapping("/api/secured/")
@PreAuthorize("hasAuthority('ROLE_SUPER') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
public class CommonController {
	
	@Autowired
	private BrokerService brokerService;
	
	@Autowired
	private MasterService masterService;
	
	@RequestMapping(value = "/existing-broker-check/{name}", method=RequestMethod.GET)
    public Boolean checkBrokerExists(@PathVariable String name) {
		return brokerService.checkBrokerExists(name);
	}
	
	@RequestMapping(value = "/broker-count", method=RequestMethod.GET)
    public Long getBrokersCount() {
		return brokerService.getBrokersCount();
	}
	
	@RequestMapping(value = "/get-masterdata", method=RequestMethod.GET)
    public MasterData getMasterData() {
		return masterService.getMasterData();
	}

}
