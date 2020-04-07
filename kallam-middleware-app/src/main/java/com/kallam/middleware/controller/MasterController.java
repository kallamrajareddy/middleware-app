package com.kallam.middleware.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kallam.middleware.model.MasterData;
import com.kallam.middleware.service.MasterService;

@RestController
@RequestMapping("/api/secured/")
@PreAuthorize("hasAuthority('ROLE_SUPER') or hasAuthority('ROLE_ADMIN')")
public class MasterController {
	
	@Autowired
	private MasterService masterService;
	
	@RequestMapping(value = "/update-masterdata", method=RequestMethod.POST)
    public MasterData updateData(@RequestBody MasterData master) {
		 return  masterService.saveMasterData(master);
	}
}
