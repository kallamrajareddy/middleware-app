package com.kallam.middleware.service;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

import com.kallam.middleware.MongoDateUtil;
import com.kallam.middleware.helper.MongoLocalDateTime;
import com.kallam.middleware.model.broker.Brokers;
import com.kallam.middleware.resultmodel.ResultBookings;

@Service
public class BookingServiceImpl implements BookingService{

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public List<ResultBookings> getBookingService(String search) {
		
		Date dt = new Date();
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime previous = LocalDateTime.of(2018, 9, 1, 0, 0);
		Aggregation agg = newAggregation(
				unwind("bookings"),
				//match(where("").orOperator(where("bookings.bookingNo").regex(".*"+search+".*", "i")
				//,where("bookings.bookingCode").regex(".*"+search+".*", "i").andOperator(where("bookings.bookingDate").gt(now))))
				match(where("bookings.bookingDate").gte(MongoLocalDateTime.of(previous)).lt(MongoLocalDateTime.of(now)))
				);
		AggregationResults<ResultBookings> results = mongoTemplate.aggregate(agg, Brokers.class, ResultBookings.class);
		return results.getMappedResults();
	}

}
