package com.kallam.middleware.service;

import java.time.LocalDate;
import java.util.List;

import com.mongodb.DBObject;

public interface ReportService {
	
	List<DBObject> getDailyReport(LocalDate date, String compCode);
	List<DBObject> getMonthReport(LocalDate date, String compCode);
	List<DBObject> getYearReport(LocalDate date, String compCode);
	List<DBObject> getFullReport(LocalDate date, String compCode);

}
