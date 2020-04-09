package com.kallam.middleware.service;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;
import static org.springframework.data.mongodb.core.aggregation.ConditionalOperators.when;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.stereotype.Service;

import com.kallam.middleware.helper.MongoLocalDateTime;
import com.kallam.middleware.model.broker.Brokers;
import com.mongodb.DBObject;

@Service
public class BookingServiceImpl implements BookingService{

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public Document getBookingService(String search, String compCode) {
		LocalDateTime now = LocalDateTime.now();
		Aggregation agg = newAggregation(
				match(where("").orOperator(where("brokerNo").regex(".*"+search+".*", "i"), where("brokerName").regex(".*"+search+".*", "i"),
						where("mobileNo").regex(".*"+search+".*", "i"),
						where("bookings.bookingNo").regex(".*"+search+".*", "i")
						,where("bookings.bookingCode").regex(".*"+search+".*", "i")).andOperator(where("companyCode").is(compCode))),
				sort(Sort.Direction.DESC, "bookings.bookingDate"),
				unwind("bookings"),
				group("brokerNo","brokerName", "defaulter","bookings.bookingNo", "bookings.auctioned", "bookings.closed", "bookings.dueDate").count().as("count"),
				project("brokerNo","brokerName", "defaulter","bookingNo", "auctioned", "closed", "dueDate").
						and(when(where("_id.auctioned").is(true)).thenValueOf("count").otherwise(0)).as("auctionedCount").
						and(when(where("").andOperator(where("_id.auctioned").is(false), where("_id.closed").is(true))).thenValueOf("count").otherwise(0)).as("closedCount").
						and(when(where("").andOperator(where("_id.auctioned").is(false), where("_id.closed").is(false),
								where("_id.dueDate").gt(MongoLocalDateTime.of(now)))).thenValueOf("count").otherwise(0)).as("activeCount").
						and(when(where("").andOperator(where("_id.auctioned").is(false), where("_id.closed").is(false),
								where("_id.dueDate").lte(MongoLocalDateTime.of(now)))).thenValueOf("count").otherwise(0)).as("pendingCount"),
				group("brokerNo", "brokerName", "defaulter")
						.sum("auctionedCount").as("auctionedCount")
						.sum("closedCount").as("closedCount")
						.sum("activeCount").as("activeCount")
						.sum("pendingCount").as("pendingCount"),
				project("brokerNo","brokerName", "defaulter", "auctionedCount", "closedCount", "activeCount", "pendingCount")
					.and(ArithmeticOperators.valueOf("auctionedCount").add("closedCount").add("activeCount").add("pendingCount")).as("total")
				);
		AggregationResults<DBObject> results = mongoTemplate.aggregate(agg, Brokers.class, DBObject.class);
		List<DBObject>  actualResults = results.getMappedResults();
		List<DBObject>  emptyResults = getEmptyBookingBrokers(search, compCode);
		Document doc = new Document().append("actualResults", actualResults).append("emptyResults", emptyResults);
		
		return doc;
	}
	
	public List<DBObject> getEmptyBookingBrokers(String search, String compCode) {LocalDateTime now = LocalDateTime.now();
	Aggregation agg = newAggregation(
			match(where("").orOperator(where("brokerNo").regex(".*"+search+".*", "i"), where("brokerName").regex(".*"+search+".*", "i"),
					where("mobileNo").regex(".*"+search+".*", "i"),
					where("bookings.bookingNo").regex(".*"+search+".*", "i")
					,where("bookings.bookingCode").regex(".*"+search+".*", "i")).andOperator(where("companyCode").is(compCode), where("bookings").size(0))),
			sort(Sort.Direction.DESC, "bookings.bookingDate"),
	project("brokerNo","brokerName", "defaulter").andExclude("_id")
	.and(when(where("brokerName").is("@#$%")).then(0).otherwise(0)).as("auctionedCount")
	.and(when(where("brokerName").is("@#$%")).then(0).otherwise(0)).as("closedCount")
	.and(when(where("brokerName").is("@#$%")).then(0).otherwise(0)).as("activeCount")
	.and(when(where("brokerName").is("@#$%")).then(0).otherwise(0)).as("pendingCount")
	.and(when(where("brokerName").is("@#$%")).then(0).otherwise(0)).as("total")
			);
	AggregationResults<DBObject> results = mongoTemplate.aggregate(agg, Brokers.class, DBObject.class);
			return results.getMappedResults();
	}

}
