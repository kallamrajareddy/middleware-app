package com.kallam.middleware.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/secured/")
@PreAuthorize("hasAuthority('ROLE_SUPER') or hasAuthority('ROLE_ADMIN')")
public class CustomerController {
	
	 @RequestMapping(value = "/getName", method=RequestMethod.GET)
	    public Map<String, String> getClaimsUrl() {
	    	Map<String, String> urls = new HashMap<>();
	    	urls.put("name", "Kallam");
	        return urls;
	    }

}
