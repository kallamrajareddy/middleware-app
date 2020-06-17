package com.kallam.middleware.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kallam.middleware.request.model.DetailBookingRequest;
import com.kallam.middleware.service.BookingService;
import com.kallam.middleware.service.ReportService;
import com.mongodb.DBObject;

@RestController
@RequestMapping("/api/secured/")
@PreAuthorize("hasAuthority('ROLE_SUPER') or hasAuthority('ROLE_ADMIN')")
public class ReportsController {
	
	@Autowired
	private ReportService reportService;
	
	@RequestMapping(value = "/get-daily-report/{date}/{compCode}", method=RequestMethod.GET)
	public List<DBObject> getDailyReport(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, @PathVariable String compCode) {
		DetailBookingRequest req;
		try {
			//req = new ObjectMapper().readValue(form, DetailBookingRequest.class);
			return reportService.getDailyReport(date, compCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/get-month-report/{date}/{compCode}", method=RequestMethod.GET)
	public List<DBObject> getMonthReport(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, @PathVariable String compCode) {
		DetailBookingRequest req;
		try {
			//req = new ObjectMapper().readValue(form, DetailBookingRequest.class);
			return reportService.getMonthReport(date, compCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/get-year-report/{date}/{compCode}", method=RequestMethod.GET)
	public List<DBObject> getYearReport(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, @PathVariable String compCode) {
		DetailBookingRequest req;
		try {
			//req = new ObjectMapper().readValue(form, DetailBookingRequest.class);
			return reportService.getYearReport(date, compCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/get-full-report/{date}/{compCode}", method=RequestMethod.GET)
	public List<DBObject> getFullReport(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, @PathVariable String compCode) {
		DetailBookingRequest req;
		try {
			//req = new ObjectMapper().readValue(form, DetailBookingRequest.class);
			return reportService.getFullReport(date, compCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
