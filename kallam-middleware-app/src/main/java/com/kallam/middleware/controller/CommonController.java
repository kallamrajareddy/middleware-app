package com.kallam.middleware.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kallam.middleware.model.MasterData;
import com.kallam.middleware.model.PostalData;
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
	
	@RequestMapping(value = "/existing-broker-check/{name}/{compCode}", method=RequestMethod.GET)
    public Boolean checkBrokerExists(@PathVariable String name, @PathVariable String compCode) {
		return brokerService.checkBrokerExists(name, compCode);
	}
	
	@RequestMapping(value = "/broker-account-code/{compCode}", method=RequestMethod.GET)
    public String getBrokersCount(@PathVariable String compCode) {
		return brokerService.getBrokersCount(compCode);
	}
	
	@RequestMapping(value = "/get-masterdata", method=RequestMethod.GET)
    public MasterData getMasterData() {
		return masterService.getMasterData();
	}
	@RequestMapping(value = "/get-postal/{code}", method=RequestMethod.GET)
	public List<PostalData> getPostalData(@PathVariable String code) {
		return masterService.getPostalCode(code);
	}

}
