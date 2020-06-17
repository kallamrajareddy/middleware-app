package com.kallam.middleware.service;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.facet;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;
import static org.springframework.data.mongodb.core.aggregation.ConditionalOperators.when;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOptions;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.data.mongodb.core.aggregation.DateOperators;
import org.springframework.data.mongodb.core.aggregation.DateOperators.DateFromString;
import org.springframework.stereotype.Service;

import com.kallam.middleware.helper.MongoLocalDateTime;
import com.kallam.middleware.model.broker.Brokers;
import com.mongodb.DBObject;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<DBObject> getDailyReport(LocalDate date, String compCode) {
		
		LocalDateTime now = date.atStartOfDay();
		Aggregation agg = newAggregation(
				match(where("companyCode").is(compCode)),
				 unwind("bookings"),
				 project("brokerNo","bookings.bookingNo", "brokerName", "addr1", "mobileNo",
							"bookings.amountTaken", "bookings.tranType", "bookings.bookingDate", "bookings.bookingTrans"),
				facet()
				.and(
						unwind("bookingTrans", true),
						project("brokerNo", "bookingNo", "bookingDate", "tranType", "bookingTrans.rcvDate")
						.and(when(where("bookingDate").lt(MongoLocalDateTime.of(now))).thenValueOf("amountTaken").otherwise(0))
						.as("bookingAmount")
						.and(when(where("bookingTrans.rcvDate").lt(MongoLocalDateTime.of(now))).thenValueOf("bookingTrans.principle").otherwise(0))
						.as("transPrincipe")
						.and(when(where("bookingTrans.rcvDate").lt(MongoLocalDateTime.of(now))).thenValueOf("bookingTrans.intrest").otherwise(0))
						.as("transIntrest"),
						group("brokerNo","bookingNo","tranType",
								"bookingAmount").sum("transPrincipe").as("totalPrinciple")
										.sum("transIntrest").as("totalIntrest"),
						group("tranType").sum("totalPrinciple").as("totalPrinciple")
														.sum("totalIntrest").as("totalIntrest")
														.sum("_id.bookingAmount").as("totalAmount"),
						project("totalPrinciple", "totalIntrest", "totalAmount").and("_id").as("tranType").andExclude("_id")
						)
				.as("openningBalance")
				.and(
						match(where("tranType").is("Off Exp")),
						project("brokerNo", "bookingDate", "bookingNo", "brokerName", "addr1", "mobileNo")
						.and("amountTaken").as("bookingAmount")
						//.and("bookingNo").as("bookingNo")
						.and(context -> {

						    Document doc = DateFromString.fromStringOf("bookingDate").toDocument(context);
						    doc.get("$dateFromString", Document.class);
						    return doc;
						}).as("bookingBDate"),
						project("brokerNo", "bookingDate", "bookingAmount","bookingNo", "brokerName", "addr1", "mobileNo")//.and(DateOperators.dateFromString(MongoLocalDateTime.of(now).toString())).as("bookingBDate")						
						.and(DateOperators.dateOf("bookingBDate").month()).as("bookingMonth")
						.and(DateOperators.dateOf("bookingBDate").year()).as("bookingYear")
						.and(DateOperators.dateOf("bookingBDate").dayOfMonth()).as("bookingDay"),
						match(where("").andOperator(where("bookingDay").is(now.getDayOfMonth()), where("bookingMonth").is(now.getMonthValue()), where("bookingYear").is(now.getYear()))),
						group("bookingDate", "bookingMonth", "bookingYear", "bookingNo", "brokerName", "addr1", "mobileNo").sum("bookingAmount").as("totalOffAmount"),
						project("totalOffAmount").and("_id.bookingNo").as("bookingNo").and("_id.brokerName").as("brokerName").and("_id.addr1").as("addr1").and("_id.mobileNo").as("mobileNo")
						.and("_id.bookingDate").as("selDate").and("_id.bookingYear").as("year").and("_id.bookingMonth").as("month").andExclude("_id"),
						sort(Direction.ASC, "selDate")
						)
					.as("totalOffAmount")
					.and(
							match(where("tranType").is("Res-Exp")),
							project("brokerNo", "bookingDate", "bookingNo", "brokerName", "addr1", "mobileNo")
							.and("amountTaken").as("bookingAmount")
							//.and("bookingNo").as("bookingNo")
							.and(context -> {

							    Document doc = DateFromString.fromStringOf("bookingDate").toDocument(context);
							    doc.get("$dateFromString", Document.class);
							    return doc;
							}).as("bookingBDate"),
							project("brokerNo", "bookingDate", "bookingAmount","bookingNo", "brokerName", "addr1", "mobileNo")//.and(DateOperators.dateFromString(MongoLocalDateTime.of(now).toString())).as("bookingBDate")						
							.and(DateOperators.dateOf("bookingBDate").month()).as("bookingMonth")
							.and(DateOperators.dateOf("bookingBDate").year()).as("bookingYear")
							.and(DateOperators.dateOf("bookingBDate").dayOfMonth()).as("bookingDay"),
							match(where("").andOperator(where("bookingDay").is(now.getDayOfMonth()), where("bookingMonth").is(now.getMonthValue()), where("bookingYear").is(now.getYear()))),
							group("bookingDate", "bookingMonth", "bookingYear", "bookingNo", "brokerName", "addr1", "mobileNo").sum("bookingAmount").as("totalRessAmount"),
							project("totalRessAmount").and("_id.bookingNo").as("bookingNo").and("_id.brokerName").as("brokerName").and("_id.addr1").as("addr1").and("_id.mobileNo").as("mobileNo")
							.and("_id.bookingDate").as("selDate").and("_id.bookingYear").as("year").and("_id.bookingMonth").as("month").andExclude("_id"),
							sort(Direction.ASC, "selDate")
							)
						.as("totalRessAmount")
					.and(
							match(where("tranType").is("Capital")),
							project("brokerNo", "bookingDate", "bookingNo", "brokerName", "addr1", "mobileNo")
							.and("amountTaken").as("bookingAmount")
							//.and("bookingNo").as("bookingNo")
							.and(context -> {

							    Document doc = DateFromString.fromStringOf("bookingDate").toDocument(context);
							    doc.get("$dateFromString", Document.class);
							    return doc;
							}).as("bookingBDate"),
							project("brokerNo", "bookingDate", "bookingAmount","bookingNo", "brokerName", "addr1", "mobileNo")//.and(DateOperators.dateFromString(MongoLocalDateTime.of(now).toString())).as("bookingBDate")						
							.and(DateOperators.dateOf("bookingBDate").month()).as("bookingMonth")
							.and(DateOperators.dateOf("bookingBDate").year()).as("bookingYear")
							.and(DateOperators.dateOf("bookingBDate").dayOfMonth()).as("bookingDay"),
							match(where("").andOperator(where("bookingDay").is(now.getDayOfMonth()), where("bookingMonth").is(now.getMonthValue()), where("bookingYear").is(now.getYear()))),
							group("bookingDate", "bookingMonth", "bookingYear", "bookingNo", "brokerName", "addr1", "mobileNo").sum("bookingAmount").as("totalCapitalAmount"),
							project("totalCapitalAmount").and("_id.bookingNo").as("bookingNo").and("_id.brokerName").as("brokerName").and("_id.addr1").as("addr1").and("_id.mobileNo").as("mobileNo")
							.and("_id.bookingDate").as("selDate").and("_id.bookingYear").as("year").and("_id.bookingMonth").as("month").andExclude("_id"),
							sort(Direction.ASC, "selDate")
							)
						.as("totalInvestmentAmount")
						.and(
								match(where("tranType").is("Capital")),
								unwind("bookingTrans", true),
								project("brokerNo", "bookingNo", "brokerName", "addr1", "mobileNo", "bookingDate", "tranType", "bookingTrans.rcvDate", "bookingTrans.principle", "bookingTrans.intrest"),
								project("brokerNo", "bookingNo", "brokerName", "addr1", "mobileNo", "bookingDate", "tranType", "rcvDate", "principle", "intrest")
								.and(context -> {

								    Document doc = DateFromString.fromStringOf("rcvDate").toDocument(context);
								    doc.get("$dateFromString", Document.class);
								    return doc;
								}).as("transBDate"),
								project("brokerNo", "bookingNo", "brokerName", "addr1", "mobileNo", "bookingDate", "tranType", "rcvDate", "principle", "intrest")
								.and(DateOperators.dateOf("transBDate").month()).as("transMonth")
								.and(DateOperators.dateOf("transBDate").year()).as("transYear")
								.and(DateOperators.dateOf("transBDate").dayOfMonth()).as("transDay"),
								match(where("").andOperator(where("transDay").is(now.getDayOfMonth()), where("transMonth").is(now.getMonthValue()), where("transYear").is(now.getYear()))),
								group("bookingNo", "brokerName", "addr1", "mobileNo", "rcvDate", "transDay", "transMonth", "transYear").sum("principle").as("investTransPrincipe").sum("intrest").as("investTransIntrest"),
								project("investTransPrincipe", "investTransIntrest")
								.and("_id.bookingNo").as("bookingNo").and("_id.brokerName").as("brokerName").and("_id.addr1").as("addr1").and("_id.mobileNo").as("mobileNo")
								.and("_id.rcvDate").as("selDate").and("_id.transYear").as("year").and("_id.transMonth").as("month").andExclude("_id"),
								sort(Direction.ASC, "selDate")
								)
						.as("totalReturnInvestmentAmount")
					.and(
							match(where("tranType").is("Issue")),
							project("brokerNo", "bookingDate", "bookingNo", "brokerName", "addr1", "mobileNo")
							.and("amountTaken").as("bookingAmount")
							//.and("bookingNo").as("bookingNo")
							.and(context -> {

							    Document doc = DateFromString.fromStringOf("bookingDate").toDocument(context);
							    doc.get("$dateFromString", Document.class);
							    return doc;
							}).as("bookingBDate"),
							project("brokerNo", "bookingDate", "bookingAmount","bookingNo", "brokerName", "addr1", "mobileNo")//.and(DateOperators.dateFromString(MongoLocalDateTime.of(now).toString())).as("bookingBDate")						
							.and(DateOperators.dateOf("bookingBDate").month()).as("bookingMonth")
							.and(DateOperators.dateOf("bookingBDate").year()).as("bookingYear")
							.and(DateOperators.dateOf("bookingBDate").dayOfMonth()).as("bookingDay"),
							match(where("").andOperator(where("bookingDay").is(now.getDayOfMonth()), where("bookingMonth").is(now.getMonthValue()), where("bookingYear").is(now.getYear()))),
							group("bookingDate", "bookingMonth", "bookingYear", "bookingNo", "brokerName", "addr1", "mobileNo").sum("bookingAmount").as("totalBookingAmount"),
							project("totalBookingAmount").and("_id.bookingNo").as("bookingNo").and("_id.brokerName").as("brokerName").and("_id.addr1").as("addr1").and("_id.mobileNo").as("mobileNo")
							.and("_id.bookingDate").as("selDate").and("_id.bookingYear").as("year").and("_id.bookingMonth").as("month").andExclude("_id"),
							sort(Direction.ASC, "selDate")
							)
						.as("totalGivenAmount")
						.and(
								match(where("tranType").is("Issue")),
								unwind("bookingTrans", true),
								project("brokerNo", "bookingNo", "brokerName", "addr1", "mobileNo", "bookingDate", "tranType", "bookingTrans.rcvDate", "bookingTrans.principle", "bookingTrans.intrest"),
								project("brokerNo", "bookingNo", "brokerName", "addr1", "mobileNo", "bookingDate", "tranType", "rcvDate", "principle", "intrest")
								.and(context -> {

								    Document doc = DateFromString.fromStringOf("rcvDate").toDocument(context);
								    doc.get("$dateFromString", Document.class);
								    return doc;
								}).as("transBDate"),
								project("brokerNo", "bookingNo", "brokerName", "addr1", "mobileNo", "bookingDate", "tranType", "rcvDate", "principle", "intrest")
								.and(DateOperators.dateOf("transBDate").month()).as("transMonth")
								.and(DateOperators.dateOf("transBDate").year()).as("transYear")
								.and(DateOperators.dateOf("transBDate").dayOfMonth()).as("transDay"),
								match(where("").andOperator(where("transDay").is(now.getDayOfMonth()), where("transMonth").is(now.getMonthValue()), where("transYear").is(now.getYear()))),
								group("bookingNo", "brokerName", "addr1", "mobileNo", "rcvDate", "transDay", "transMonth", "transYear").sum("principle").as("transPrincipe").sum("intrest").as("transIntrest"),
								project("transPrincipe", "transIntrest")
								.and("_id.bookingNo").as("bookingNo").and("_id.brokerName").as("brokerName").and("_id.addr1").as("addr1").and("_id.mobileNo").as("mobileNo")
								.and("_id.rcvDate").as("selDate").and("_id.transYear").as("year").and("_id.transMonth").as("month").andExclude("_id"),
								sort(Direction.ASC, "selDate")
								)
						.as("totalReturnsAmount")
						.and(
								unwind("bookingTrans", true),
								project("brokerNo", "bookingNo", "bookingDate", "tranType", "bookingTrans.rcvDate")
								.and(when(where("bookingDate").lte(MongoLocalDateTime.of(now))).thenValueOf("amountTaken").otherwise(0))
								.as("bookingAmount")
								.and(when(where("bookingTrans.rcvDate").lte(MongoLocalDateTime.of(now))).thenValueOf("bookingTrans.principle").otherwise(0))
								.as("transPrincipe")
								.and(when(where("bookingTrans.rcvDate").lte(MongoLocalDateTime.of(now))).thenValueOf("bookingTrans.intrest").otherwise(0))
								.as("transIntrest"),
								group("brokerNo","bookingNo","tranType",
										"bookingAmount").sum("transPrincipe").as("totalPrinciple")
												.sum("transIntrest").as("totalIntrest"),
								group("tranType").sum("totalPrinciple").as("totalPrinciple")
																.sum("totalIntrest").as("totalIntrest")
																.sum("_id.bookingAmount").as("totalAmount"),
								project("totalPrinciple", "totalIntrest", "totalAmount").and("_id").as("tranType").andExclude("_id")
								)
						.as("closingBalance")
				);
		AggregationOptions options = AggregationOptions.builder().allowDiskUse(true).build();
		AggregationResults<DBObject> results = mongoTemplate.aggregate(agg.withOptions(options), Brokers.class, DBObject.class);
		List<DBObject> actualResults = results.getMappedResults();

		return actualResults;
	}
	
	@Override
	public List<DBObject> getMonthReport(LocalDate date, String compCode) {
		Integer day = date.getDayOfMonth();
		LocalDateTime previous = date.minusDays((day-1)).atStartOfDay();
		//LocalDateTime previous = LocalDateTime.of((year), 1, 1, 0, 0);
		 LocalDate lastDayofCurrentMonth = date.with(TemporalAdjusters.lastDayOfMonth());
		LocalDateTime now = date.atStartOfDay();
		Aggregation agg = newAggregation(
				match(where("companyCode").is(compCode)),
				 unwind("bookings"),
				 project("brokerNo","bookings.bookingNo",
							"bookings.amountTaken", "bookings.tranType", "bookings.bookingDate", "bookings.bookingTrans"),
				facet()
				.and(
						unwind("bookingTrans", true),
						project("brokerNo", "bookingNo", "bookingDate", "tranType", "bookingTrans.rcvDate")
						.and(when(where("bookingDate").lt(MongoLocalDateTime.of(previous))).thenValueOf("amountTaken").otherwise(0))
						.as("bookingAmount")
						.and(when(where("bookingTrans.rcvDate").lt(MongoLocalDateTime.of(previous))).thenValueOf("bookingTrans.principle").otherwise(0))
						.as("transPrincipe")
						.and(when(where("bookingTrans.rcvDate").lt(MongoLocalDateTime.of(previous))).thenValueOf("bookingTrans.intrest").otherwise(0))
						.as("transIntrest"),
						group("brokerNo","bookingNo","tranType",
								"bookingAmount").sum("transPrincipe").as("totalPrinciple")
										.sum("transIntrest").as("totalIntrest"),
						group("tranType").sum("totalPrinciple").as("totalPrinciple")
														.sum("totalIntrest").as("totalIntrest")
														.sum("_id.bookingAmount").as("totalAmount"),
						project("totalPrinciple", "totalIntrest", "totalAmount").and("_id").as("tranType").andExclude("_id")
						)
				.as("openningBalance")
				.and(
						match(where("tranType").is("Off Exp")),
						project("brokerNo", "bookingDate")
						.and("amountTaken").as("bookingAmount")
						.and("bookingNo").as("bookingNo")
						.and(context -> {

						    Document doc = DateFromString.fromStringOf("bookingDate").toDocument(context);
						    doc.get("$dateFromString", Document.class);
						    return doc;
						}).as("bookingBDate"),
						project("brokerNo", "bookingDate", "bookingAmount","bookingNo")//.and(DateOperators.dateFromString(MongoLocalDateTime.of(now).toString())).as("bookingBDate")						
						.and(DateOperators.dateOf("bookingBDate").month()).as("bookingMonth")
						.and(DateOperators.dateOf("bookingBDate").year()).as("bookingYear"),
						match(where("").andOperator(where("bookingMonth").is(now.getMonthValue()), where("bookingYear").is(now.getYear()))),
						group("bookingMonth", "bookingYear", "bookingDate").sum("bookingAmount").as("totalOffAmount"),
						project("totalOffAmount").and("_id.bookingDate").as("selDate").and("_id.bookingYear").as("year").and("_id.bookingMonth").as("month").andExclude("_id"),
						sort(Direction.ASC, "month")
						)
					.as("totalOffAmount")
					.and(
							match(where("tranType").is("Res-Exp")),
							project("brokerNo", "bookingDate")
							.and("amountTaken").as("bookingAmount")
							.and("bookingNo").as("bookingNo")
							.and(context -> {

							    Document doc = DateFromString.fromStringOf("bookingDate").toDocument(context);
							    doc.get("$dateFromString", Document.class);
							    return doc;
							}).as("bookingBDate"),
							project("brokerNo", "bookingDate", "bookingAmount","bookingNo")//.and(DateOperators.dateFromString(MongoLocalDateTime.of(now).toString())).as("bookingBDate")						
							.and(DateOperators.dateOf("bookingBDate").month()).as("bookingMonth")
							.and(DateOperators.dateOf("bookingBDate").year()).as("bookingYear"),
							match(where("").andOperator(where("bookingMonth").is(now.getMonthValue()), where("bookingYear").is(now.getYear()))),
							group("bookingMonth", "bookingYear", "bookingDate").sum("bookingAmount").as("totalRessAmount"),
							project("totalRessAmount").and("_id.bookingDate").as("selDate").and("_id.bookingYear").as("year").and("_id.bookingMonth").as("month").andExclude("_id"),
							sort(Direction.ASC, "month")
							)
						.as("totalRessAmount")
					.and(
							match(where("tranType").is("Capital")),
							project("brokerNo", "bookingDate")
							.and("amountTaken").as("bookingAmount")
							.and("bookingNo").as("bookingNo")
							.and(context -> {

							    Document doc = DateFromString.fromStringOf("bookingDate").toDocument(context);
							    doc.get("$dateFromString", Document.class);
							    return doc;
							}).as("bookingBDate"),
							project("brokerNo", "bookingDate", "bookingAmount","bookingNo")//.and(DateOperators.dateFromString(MongoLocalDateTime.of(now).toString())).as("bookingBDate")						
							.and(DateOperators.dateOf("bookingBDate").month()).as("bookingMonth")
							.and(DateOperators.dateOf("bookingBDate").year()).as("bookingYear"),
							match(where("").andOperator(where("bookingMonth").is(now.getMonthValue()), where("bookingYear").is(now.getYear()))),
							group("bookingMonth", "bookingYear", "bookingDate").sum("bookingAmount").as("totalCapitalAmount"),
							project("totalCapitalAmount").and("_id.bookingDate").as("selDate").and("_id.bookingYear").as("year").and("_id.bookingMonth").as("month").andExclude("_id"),
							sort(Direction.ASC, "selDate")
							)
						.as("totalInvestmentAmount")
						.and(
								match(where("tranType").is("Capital")),
								unwind("bookingTrans", true),
								project("brokerNo", "bookingNo", "bookingDate", "tranType", "bookingTrans.rcvDate", "bookingTrans.principle", "bookingTrans.intrest"),
								project("brokerNo", "bookingNo", "bookingDate", "tranType", "rcvDate", "principle", "intrest")
								.and(context -> {

								    Document doc = DateFromString.fromStringOf("rcvDate").toDocument(context);
								    doc.get("$dateFromString", Document.class);
								    return doc;
								}).as("transBDate"),
								project("brokerNo", "bookingNo", "bookingDate", "tranType", "rcvDate", "principle", "intrest")
								.and(DateOperators.dateOf("transBDate").month()).as("transMonth")
								.and(DateOperators.dateOf("transBDate").year()).as("transYear"),
								match(where("").andOperator(where("transMonth").is(now.getMonthValue()), where("transYear").is(now.getYear()))),
								group("transMonth", "transYear", "rcvDate").sum("principle").as("investTransPrincipe").sum("intrest").as("investTransIntrest"),
								project("investTransPrincipe", "investTransIntrest").and("_id.rcvDate").as("selDate").and("_id.transYear").as("year").and("_id.transMonth").as("month").andExclude("_id"),
								sort(Direction.ASC, "selDate")
								)
						.as("totalReturnInvestmentAmount")
					.and(
							match(where("tranType").is("Issue")),
							project("brokerNo", "bookingDate")
							.and("amountTaken").as("bookingAmount")
							.and("bookingNo").as("bookingNo")
							.and(context -> {

							    Document doc = DateFromString.fromStringOf("bookingDate").toDocument(context);
							    doc.get("$dateFromString", Document.class);
							    return doc;
							}).as("bookingBDate"),
							project("brokerNo", "bookingDate", "bookingAmount","bookingNo")//.and(DateOperators.dateFromString(MongoLocalDateTime.of(now).toString())).as("bookingBDate")						
							.and(DateOperators.dateOf("bookingBDate").month()).as("bookingMonth")
							.and(DateOperators.dateOf("bookingBDate").year()).as("bookingYear"),
							match(where("").andOperator(where("bookingMonth").is(now.getMonthValue()), where("bookingYear").is(now.getYear()))),
							group("bookingMonth", "bookingYear", "bookingDate").sum("bookingAmount").as("totalBookingAmount"),
							project("totalBookingAmount").and("_id.bookingDate").as("selDate").and("_id.bookingYear").as("year").and("_id.bookingMonth").as("month").andExclude("_id"),
							sort(Direction.ASC, "selDate")
							)
						.as("totalGivenAmount")
						.and(
								match(where("tranType").is("Issue")),
								unwind("bookingTrans", true),
								project("brokerNo", "bookingNo", "bookingDate", "tranType", "bookingTrans.rcvDate", "bookingTrans.principle", "bookingTrans.intrest"),
								project("brokerNo", "bookingNo", "bookingDate", "tranType", "rcvDate", "principle", "intrest")
								.and(context -> {

								    Document doc = DateFromString.fromStringOf("rcvDate").toDocument(context);
								    doc.get("$dateFromString", Document.class);
								    return doc;
								}).as("transBDate"),
								project("brokerNo", "bookingNo", "bookingDate", "tranType", "rcvDate", "principle", "intrest")
								.and(DateOperators.dateOf("transBDate").month()).as("transMonth")
								.and(DateOperators.dateOf("transBDate").year()).as("transYear"),
								match(where("").andOperator(where("transMonth").is(now.getMonthValue()), where("transYear").is(now.getYear()))),
								group("transMonth", "transYear", "rcvDate").sum("principle").as("transPrincipe").sum("intrest").as("transIntrest"),
								project("transPrincipe", "transIntrest").and("_id.rcvDate").as("selDate").and("_id.transYear").as("year").and("_id.transMonth").as("month").andExclude("_id"),
								sort(Direction.ASC, "selDate")
								)
						.as("totalReturnsAmount")
						.and(
								unwind("bookingTrans", true),
								project("brokerNo", "bookingNo", "bookingDate", "tranType", "bookingTrans.rcvDate")
								.and(when(where("bookingDate").lte(MongoLocalDateTime.of(lastDayofCurrentMonth.atStartOfDay()))).thenValueOf("amountTaken").otherwise(0))
								.as("bookingAmount")
								.and(when(where("bookingTrans.rcvDate").lte(MongoLocalDateTime.of(lastDayofCurrentMonth.atStartOfDay()))).thenValueOf("bookingTrans.principle").otherwise(0))
								.as("transPrincipe")
								.and(when(where("bookingTrans.rcvDate").lte(MongoLocalDateTime.of(lastDayofCurrentMonth.atStartOfDay()))).thenValueOf("bookingTrans.intrest").otherwise(0))
								.as("transIntrest"),
								group("brokerNo","bookingNo","tranType",
										"bookingAmount").sum("transPrincipe").as("totalPrinciple")
												.sum("transIntrest").as("totalIntrest"),
								group("tranType").sum("totalPrinciple").as("totalPrinciple")
																.sum("totalIntrest").as("totalIntrest")
																.sum("_id.bookingAmount").as("totalAmount"),
								project("totalPrinciple", "totalIntrest", "totalAmount").and("_id").as("tranType").andExclude("_id")
								)
						.as("closingBalance")
				);
		AggregationOptions options = AggregationOptions.builder().allowDiskUse(true).build();
		AggregationResults<DBObject> results = mongoTemplate.aggregate(agg.withOptions(options), Brokers.class, DBObject.class);
		List<DBObject> actualResults = results.getMappedResults();

		return actualResults;
	}
	
	
	@Override
	public List<DBObject> getYearReport(LocalDate date, String compCode) {
		
		Integer year = date.getYear();
		LocalDateTime previous = LocalDateTime.of((year), 1, 1, 0, 0);
		LocalDateTime current = LocalDateTime.of((year), 12, 31, 0, 0);
		//LocalDateTime previous = date.minusYears(1).atStartOfDay();
		
		LocalDateTime now = date.atStartOfDay();
		Aggregation agg = newAggregation(
				match(where("companyCode").is(compCode)),
				unwind("bookings"),
				project("brokerNo","bookings.bookingNo",
						"bookings.amountTaken", "bookings.tranType", "bookings.bookingDate", "bookings.bookingTrans"),
				facet()
				.and(
						unwind("bookingTrans", true),
						project("brokerNo", "bookingNo", "bookingDate", "tranType", "bookingTrans.rcvDate")
						.and(when(where("bookingDate").lt(MongoLocalDateTime.of(previous))).thenValueOf("amountTaken").otherwise(0))
						.as("bookingAmount")
						.and(when(where("bookingTrans.rcvDate").lt(MongoLocalDateTime.of(previous))).thenValueOf("bookingTrans.principle").otherwise(0))
						.as("transPrincipe")
						.and(when(where("bookingTrans.rcvDate").lt(MongoLocalDateTime.of(previous))).thenValueOf("bookingTrans.intrest").otherwise(0))
						.as("transIntrest"),
						group("brokerNo","bookingNo","tranType",
								"bookingAmount").sum("transPrincipe").as("totalPrinciple")
						.sum("transIntrest").as("totalIntrest"),
						group("tranType").sum("totalPrinciple").as("totalPrinciple")
						.sum("totalIntrest").as("totalIntrest")
						.sum("_id.bookingAmount").as("totalAmount"),
						project("totalPrinciple", "totalIntrest", "totalAmount").and("_id").as("tranType").andExclude("_id")
						)
				.as("openningBalance")
				.and(
						match(where("tranType").is("Off Exp")),
						project("brokerNo", "bookingDate")
						.and("amountTaken").as("bookingAmount")
						.and("bookingNo").as("bookingNo")
						.and(context -> {
							
							Document doc = DateFromString.fromStringOf("bookingDate").toDocument(context);
							doc.get("$dateFromString", Document.class);
							return doc;
						}).as("bookingBDate"),
						project("brokerNo", "bookingDate", "bookingAmount","bookingNo")//.and(DateOperators.dateFromString(MongoLocalDateTime.of(now).toString())).as("bookingBDate")						
						.and(DateOperators.dateOf("bookingBDate").month()).as("bookingMonth")
						.and(DateOperators.dateOf("bookingBDate").year()).as("bookingYear"),
						match(where("bookingYear").is(now.getYear())),
						group("bookingMonth", "bookingYear").sum("bookingAmount").as("totalOffAmount"),
						project("totalOffAmount").and("_id.bookingYear").as("year").and("_id.bookingMonth").as("month").andExclude("_id"),
						sort(Direction.ASC, "month")
						)
				.as("totalOffAmount")
				.and(
						match(where("tranType").is("Res-Exp")),
						project("brokerNo", "bookingDate")
						.and("amountTaken").as("bookingAmount")
						.and("bookingNo").as("bookingNo")
						.and(context -> {
							
							Document doc = DateFromString.fromStringOf("bookingDate").toDocument(context);
							doc.get("$dateFromString", Document.class);
							return doc;
						}).as("bookingBDate"),
						project("brokerNo", "bookingDate", "bookingAmount","bookingNo")//.and(DateOperators.dateFromString(MongoLocalDateTime.of(now).toString())).as("bookingBDate")						
						.and(DateOperators.dateOf("bookingBDate").month()).as("bookingMonth")
						.and(DateOperators.dateOf("bookingBDate").year()).as("bookingYear"),
						match(where("bookingYear").is(now.getYear())),
						group("bookingMonth", "bookingYear").sum("bookingAmount").as("totalRessAmount"),
						project("totalRessAmount").and("_id.bookingYear").as("year").and("_id.bookingMonth").as("month").andExclude("_id"),
						sort(Direction.ASC, "month")
						)
				.as("totalRessAmount")
				.and(
						match(where("tranType").is("Capital")),
						project("brokerNo", "bookingDate")
						.and("amountTaken").as("bookingAmount")
						.and("bookingNo").as("bookingNo")
						.and(context -> {
							
							Document doc = DateFromString.fromStringOf("bookingDate").toDocument(context);
							doc.get("$dateFromString", Document.class);
							return doc;
						}).as("bookingBDate"),
						project("brokerNo", "bookingDate", "bookingAmount","bookingNo")//.and(DateOperators.dateFromString(MongoLocalDateTime.of(now).toString())).as("bookingBDate")						
						.and(DateOperators.dateOf("bookingBDate").month()).as("bookingMonth")
						.and(DateOperators.dateOf("bookingBDate").year()).as("bookingYear"),
						match(where("bookingYear").is(now.getYear())),
						group("bookingMonth", "bookingYear").sum("bookingAmount").as("totalCapitalAmount"),
						project("totalCapitalAmount").and("_id.bookingYear").as("year").and("_id.bookingMonth").as("month").andExclude("_id"),
						sort(Direction.ASC, "month")
						)
				.as("totalInvestmentAmount")
				.and(
						match(where("tranType").is("Capital")),
						unwind("bookingTrans", true),
						project("brokerNo", "bookingNo", "bookingDate", "tranType", "bookingTrans.rcvDate", "bookingTrans.principle", "bookingTrans.intrest"),
						project("brokerNo", "bookingNo", "bookingDate", "tranType", "rcvDate", "principle", "intrest")
						.and(context -> {
							
							Document doc = DateFromString.fromStringOf("rcvDate").toDocument(context);
							doc.get("$dateFromString", Document.class);
							return doc;
						}).as("transBDate"),
						project("brokerNo", "bookingNo", "bookingDate", "tranType", "rcvDate", "principle", "intrest")
						.and(DateOperators.dateOf("transBDate").month()).as("transMonth")
						.and(DateOperators.dateOf("transBDate").year()).as("transYear"),
						match(where("transYear").is(now.getYear())),
						group("transMonth", "transYear").sum("principle").as("investTransPrincipe").sum("intrest").as("investTransIntrest"),
						project("investTransPrincipe", "investTransIntrest").and("_id.transYear").as("year").and("_id.transMonth").as("month").andExclude("_id"),
						sort(Direction.ASC, "month")
						)
				.as("totalReturnInvestmentAmount")
				.and(
						match(where("tranType").is("Issue")),
						project("brokerNo", "bookingDate")
						.and("amountTaken").as("bookingAmount")
						.and("bookingNo").as("bookingNo")
						.and(context -> {
							
							Document doc = DateFromString.fromStringOf("bookingDate").toDocument(context);
							doc.get("$dateFromString", Document.class);
							return doc;
						}).as("bookingBDate"),
						project("brokerNo", "bookingDate", "bookingAmount","bookingNo")//.and(DateOperators.dateFromString(MongoLocalDateTime.of(now).toString())).as("bookingBDate")						
						.and(DateOperators.dateOf("bookingBDate").month()).as("bookingMonth")
						.and(DateOperators.dateOf("bookingBDate").year()).as("bookingYear"),
						match(where("bookingYear").is(now.getYear())),
						group("bookingMonth", "bookingYear").sum("bookingAmount").as("totalBookingAmount"),
						project("totalBookingAmount").and("_id.bookingYear").as("year").and("_id.bookingMonth").as("month").andExclude("_id"),
						sort(Direction.ASC, "month")
						)
				.as("totalGivenAmount")
				.and(
						match(where("tranType").is("Issue")),
						unwind("bookingTrans", true),
						project("brokerNo", "bookingNo", "bookingDate", "tranType", "bookingTrans.rcvDate", "bookingTrans.principle", "bookingTrans.intrest"),
						project("brokerNo", "bookingNo", "bookingDate", "tranType", "rcvDate", "principle", "intrest")
						.and(context -> {
							
							Document doc = DateFromString.fromStringOf("rcvDate").toDocument(context);
							doc.get("$dateFromString", Document.class);
							return doc;
						}).as("transBDate"),
						project("brokerNo", "bookingNo", "bookingDate", "tranType", "rcvDate", "principle", "intrest")
						.and(DateOperators.dateOf("transBDate").month()).as("transMonth")
						.and(DateOperators.dateOf("transBDate").year()).as("transYear"),
						match(where("transYear").is(now.getYear())),
						group("transMonth", "transYear").sum("principle").as("transPrincipe").sum("intrest").as("transIntrest"),
						project("transPrincipe", "transIntrest").and("_id.transYear").as("year").and("_id.transMonth").as("month").andExclude("_id"),
						sort(Direction.ASC, "month")
						)
				.as("totalReturnsAmount")
				.and(
						unwind("bookingTrans", true),
						project("brokerNo", "bookingNo", "bookingDate", "tranType", "bookingTrans.rcvDate")
						.and(when(where("bookingDate").lte(MongoLocalDateTime.of(current))).thenValueOf("amountTaken").otherwise(0))
						.as("bookingAmount")
						.and(when(where("bookingTrans.rcvDate").lte(MongoLocalDateTime.of(current))).thenValueOf("bookingTrans.principle").otherwise(0))
						.as("transPrincipe")
						.and(when(where("bookingTrans.rcvDate").lte(MongoLocalDateTime.of(current))).thenValueOf("bookingTrans.intrest").otherwise(0))
						.as("transIntrest"),
						group("brokerNo","bookingNo","tranType",
								"bookingAmount").sum("transPrincipe").as("totalPrinciple")
										.sum("transIntrest").as("totalIntrest"),
						group("tranType").sum("totalPrinciple").as("totalPrinciple")
														.sum("totalIntrest").as("totalIntrest")
														.sum("_id.bookingAmount").as("totalAmount"),
						project("totalPrinciple", "totalIntrest", "totalAmount").and("_id").as("tranType").andExclude("_id")
						)
				.as("closingBalance")
				);
		AggregationOptions options = AggregationOptions.builder().allowDiskUse(true).build();
		AggregationResults<DBObject> results = mongoTemplate.aggregate(agg.withOptions(options), Brokers.class, DBObject.class);
		List<DBObject> actualResults = results.getMappedResults();
		
		return actualResults;
	}
	
	@Override
	public List<DBObject> getFullReport(LocalDate date, String compCode) {
		
		Integer year = date.getYear();
		LocalDateTime previous = LocalDateTime.of((year), 1, 1, 0, 0);
		//LocalDateTime previous = date.minusYears(1).atStartOfDay();
		
		LocalDateTime now = LocalDateTime.now();
		Aggregation agg = newAggregation(
				match(where("companyCode").is(compCode)),
				unwind("bookings"),
				project("brokerNo","bookings.bookingNo",
						"bookings.amountTaken", "bookings.tranType", "bookings.bookingDate", "bookings.bookingTrans"),
				facet()
				.and(
						unwind("bookingTrans", true),
						project("brokerNo", "bookingNo", "bookingDate", "tranType", "bookingTrans.rcvDate")
						.and(when(where("bookingDate").lt(MongoLocalDateTime.of(now))).thenValueOf("amountTaken").otherwise(0))
						.as("bookingAmount")
						.and(when(where("bookingTrans.rcvDate").lt(MongoLocalDateTime.of(now))).thenValueOf("bookingTrans.principle").otherwise(0))
						.as("transPrincipe")
						.and(when(where("bookingTrans.rcvDate").lt(MongoLocalDateTime.of(now))).thenValueOf("bookingTrans.intrest").otherwise(0))
						.as("transIntrest"),
						group("brokerNo","bookingNo","tranType",
								"bookingAmount").sum("transPrincipe").as("totalPrinciple")
						.sum("transIntrest").as("totalIntrest"),
						group("tranType").sum("totalPrinciple").as("totalPrinciple")
						.sum("totalIntrest").as("totalIntrest")
						.sum("_id.bookingAmount").as("totalAmount"),
						project("totalPrinciple", "totalIntrest", "totalAmount").and("_id").as("tranType").andExclude("_id")
						)
				.as("closingBalance")
				.and(
						match(where("tranType").is("Off Exp")),
						project("brokerNo", "bookingDate")
						.and("amountTaken").as("bookingAmount")
						.and("bookingNo").as("bookingNo")
						.and(context -> {
							
							Document doc = DateFromString.fromStringOf("bookingDate").toDocument(context);
							doc.get("$dateFromString", Document.class);
							return doc;
						}).as("bookingBDate"),
						project("brokerNo", "bookingDate", "bookingAmount","bookingNo")//.and(DateOperators.dateFromString(MongoLocalDateTime.of(now).toString())).as("bookingBDate")						
						.and(DateOperators.dateOf("bookingBDate").month()).as("bookingMonth")
						.and(DateOperators.dateOf("bookingBDate").year()).as("bookingYear"),
						//match(where("bookingYear").is(now.getYear())),
						//group("bookingMonth", "bookingYear").sum("bookingAmount").as("totalBookingAmount"),
						group("bookingYear").sum("bookingAmount").as("totalOffAmount"),
						project("totalOffAmount").and("_id").as("year").andExclude("_id"),
						sort(Direction.ASC, "year")
						)
				.as("totalOffAmount")
				.and(
						match(where("tranType").is("Res-Exp")),
						project("brokerNo", "bookingDate")
						.and("amountTaken").as("bookingAmount")
						.and("bookingNo").as("bookingNo")
						.and(context -> {
							
							Document doc = DateFromString.fromStringOf("bookingDate").toDocument(context);
							doc.get("$dateFromString", Document.class);
							return doc;
						}).as("bookingBDate"),
						project("brokerNo", "bookingDate", "bookingAmount","bookingNo")//.and(DateOperators.dateFromString(MongoLocalDateTime.of(now).toString())).as("bookingBDate")						
						.and(DateOperators.dateOf("bookingBDate").month()).as("bookingMonth")
						.and(DateOperators.dateOf("bookingBDate").year()).as("bookingYear"),
						//match(where("bookingYear").is(now.getYear())),
						//group("bookingMonth", "bookingYear").sum("bookingAmount").as("totalBookingAmount"),
						group("bookingYear").sum("bookingAmount").as("totalRessAmount"),
						project("totalRessAmount").and("_id").as("year").andExclude("_id"),
						sort(Direction.ASC, "year")
						)
				.as("totalRessAmount")
				.and(
						match(where("tranType").is("Capital")),
						project("brokerNo", "bookingDate")
						.and("amountTaken").as("bookingAmount")
						.and("bookingNo").as("bookingNo")
						.and(context -> {
							
							Document doc = DateFromString.fromStringOf("bookingDate").toDocument(context);
							doc.get("$dateFromString", Document.class);
							return doc;
						}).as("bookingBDate"),
						project("brokerNo", "bookingDate", "bookingAmount","bookingNo")//.and(DateOperators.dateFromString(MongoLocalDateTime.of(now).toString())).as("bookingBDate")						
						.and(DateOperators.dateOf("bookingBDate").month()).as("bookingMonth")
						.and(DateOperators.dateOf("bookingBDate").year()).as("bookingYear"),
						//match(where("bookingYear").is(now.getYear())),
						//group("bookingMonth", "bookingYear").sum("bookingAmount").as("totalCapitalAmount"),
						group("bookingYear").sum("bookingAmount").as("totalCapitalAmount"),
						project("totalCapitalAmount").and("_id").as("year").andExclude("_id"),
						sort(Direction.ASC, "year")
						)
				.as("totalInvestmentAmount")
				.and(
						match(where("tranType").is("Capital")),
						unwind("bookingTrans", true),
						project("brokerNo", "bookingNo", "bookingDate", "tranType", "bookingTrans.rcvDate", "bookingTrans.principle", "bookingTrans.intrest"),
						match(where("rcvDate").ne(null)),
						project("brokerNo", "bookingNo", "bookingDate", "tranType", "rcvDate", "principle", "intrest")
						.and(context -> {
							
							Document doc = DateFromString.fromStringOf("rcvDate").toDocument(context);
							doc.get("$dateFromString", Document.class);
							return doc;
						}).as("transBDate"),
						project("brokerNo", "bookingNo", "bookingDate", "tranType", "rcvDate", "principle", "intrest")
						.and(DateOperators.dateOf("transBDate").month()).as("transMonth")
						.and(DateOperators.dateOf("transBDate").year()).as("transYear"),
						//match(where("transYear").is(now.getYear())),
						//group("transMonth", "transYear").sum("principle").as("transPrincipe").sum("intrest").as("transIntrest"),
						group( "transYear").sum("principle").as("investTransPrincipe").sum("intrest").as("investTransIntrest"),
						project("investTransPrincipe", "investTransIntrest").and("_id").as("year").andExclude("_id"),
						sort(Direction.ASC, "year")
						)
				.as("totalReturnInvestmentAmount")
				.and(
						match(where("tranType").is("Issue")),
						project("brokerNo", "bookingDate")
						.and("amountTaken").as("bookingAmount")
						.and("bookingNo").as("bookingNo")
						.and(context -> {
							
							Document doc = DateFromString.fromStringOf("bookingDate").toDocument(context);
							doc.get("$dateFromString", Document.class);
							return doc;
						}).as("bookingBDate"),
						project("brokerNo", "bookingDate", "bookingAmount","bookingNo")//.and(DateOperators.dateFromString(MongoLocalDateTime.of(now).toString())).as("bookingBDate")						
						.and(DateOperators.dateOf("bookingBDate").month()).as("bookingMonth")
						.and(DateOperators.dateOf("bookingBDate").year()).as("bookingYear"),
						//match(where("bookingYear").is(now.getYear())),
						//group("bookingMonth", "bookingYear").sum("bookingAmount").as("totalBookingAmount"),
						group("bookingYear").sum("bookingAmount").as("totalBookingAmount"),
						project("totalBookingAmount").and("_id").as("year").andExclude("_id"),
						sort(Direction.ASC, "year")
						)
				.as("totalGivenAmount")
				.and(
						match(where("tranType").is("Issue")),
						unwind("bookingTrans", true),
						project("brokerNo", "bookingNo", "bookingDate", "tranType", "bookingTrans.rcvDate", "bookingTrans.principle", "bookingTrans.intrest"),
						match(where("rcvDate").ne(null)),
						project("brokerNo", "bookingNo", "bookingDate", "tranType", "rcvDate", "principle", "intrest")
						.and(context -> {
							
							Document doc = DateFromString.fromStringOf("rcvDate").toDocument(context);
							doc.get("$dateFromString", Document.class);
							return doc;
						}).as("transBDate"),
						project("brokerNo", "bookingNo", "bookingDate", "tranType", "rcvDate", "principle", "intrest")
						.and(DateOperators.dateOf("transBDate").month()).as("transMonth")
						.and(DateOperators.dateOf("transBDate").year()).as("transYear"),
						//match(where("transYear").is(now.getYear())),
						//group("transMonth", "transYear").sum("principle").as("transPrincipe").sum("intrest").as("transIntrest"),
						group("transYear").sum("principle").as("transPrincipe").sum("intrest").as("transIntrest"),
						project("transPrincipe", "transIntrest").and("_id").as("year").andExclude("_id"),
						sort(Direction.ASC, "year")
						)
				.as("totalReturnsAmount")
				);
		AggregationOptions options = AggregationOptions.builder().allowDiskUse(true).build();
		AggregationResults<DBObject> results = mongoTemplate.aggregate(agg.withOptions(options), Brokers.class, DBObject.class);
		List<DBObject> actualResults = results.getMappedResults();
		
		return actualResults;
	}
}
