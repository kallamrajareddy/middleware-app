package com.kallam.middleware.service;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;
import static org.springframework.data.mongodb.core.aggregation.ConditionalOperators.when;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.bson.BsonUndefined;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;
import org.springframework.data.mongodb.core.aggregation.AggregationOptions;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.stereotype.Service;

import com.kallam.middleware.MongoDateUtil;
import com.kallam.middleware.helper.MongoLocalDateTime;
import com.kallam.middleware.model.broker.Bookings;
import com.kallam.middleware.model.broker.Brokers;
import com.kallam.middleware.model.broker.Items;
import com.kallam.middleware.request.model.BookingRequest;
import com.mongodb.DBObject;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public Document getBookingService(String search, String compCode) {
		LocalDateTime now = LocalDateTime.now();
		Aggregation agg = newAggregation(
				match(where("")
						.orOperator(where("brokerNo").regex(".*" + search + ".*", "i"),
								where("brokerName").regex(".*" + search + ".*", "i"),
								where("mobileNo").regex(".*" + search + ".*", "i"),
								where("bookings.bookingNo").regex(".*" + search + ".*", "i"),
								where("bookings.bookingCode").regex(".*" + search + ".*", "i"))
						.andOperator(where("companyCode").is(compCode))),
				sort(Sort.Direction.DESC, "bookings.bookingDate"), unwind("bookings"),
				group("brokerNo", "brokerName", "defaulter", "bookings.bookingNo", "bookings.auctioned",
						"bookings.closed", "bookings.dueDate").count().as("count"),
				project("brokerNo", "brokerName", "defaulter", "bookingNo", "auctioned", "closed", "dueDate")
						.and(when(where("_id.auctioned").is(true)).thenValueOf("count").otherwise(0))
						.as("auctionedCount")
						.and(when(where("").andOperator(where("_id.auctioned").is(false), where("_id.closed").is(true)))
								.thenValueOf("count").otherwise(0))
						.as("closedCount")
						.and(when(where("").andOperator(where("_id.auctioned").is(false), where("_id.closed").is(false),
								where("_id.dueDate").gt(MongoLocalDateTime.of(now)))).thenValueOf("count").otherwise(0))
						.as("activeCount")
						.and(when(where("").andOperator(where("_id.auctioned").is(false), where("_id.closed").is(false),
								where("_id.dueDate").lte(MongoLocalDateTime.of(now)))).thenValueOf("count")
										.otherwise(0))
						.as("pendingCount"),
				group("brokerNo", "brokerName", "defaulter").sum("auctionedCount").as("auctionedCount")
						.sum("closedCount").as("closedCount").sum("activeCount").as("activeCount").sum("pendingCount")
						.as("pendingCount"),
				project("brokerNo", "brokerName", "defaulter", "auctionedCount", "closedCount", "activeCount",
						"pendingCount")
								.and(ArithmeticOperators.valueOf("auctionedCount").add("closedCount").add("activeCount")
										.add("pendingCount"))
								.as("total"));
		AggregationOptions options = AggregationOptions.builder().allowDiskUse(true).build();
		AggregationResults<DBObject> results = mongoTemplate.aggregate(agg.withOptions(options), Brokers.class, DBObject.class);
		List<DBObject> actualResults = results.getMappedResults();
		List<DBObject> emptyResults = getEmptyBookingBrokers(search, compCode);
		Document doc = new Document().append("actualResults", actualResults).append("emptyResults", emptyResults);

		return doc;
	}

	@Override
	public Document getBrokerBooking(String brokerNo, String compCode) {
		Brokers broker = mongoTemplate.findOne(
				query(where("brokerNo").is(brokerNo).andOperator(where("companyCode").is(compCode))), Brokers.class);
		if (broker!=null && broker.getBookings().size() < 1) {
			Document document = new Document()
					.append("brokerNo", brokerNo)
					.append("brokerName", broker.getBrokerName())
					.append("age", broker.getAge())
					.append("addr1", broker.getAddr1())
					.append("occupation", broker.getOccupation())
					.append("mobileNo", broker.getMobileNo())
					.append("contactPerson1", broker.getContactPerson1())
					.append("contactPerson2", broker.getContactPerson2())
					.append("auctionedBookings", new ArrayList<>())
					.append("closedBookings", new ArrayList<>())
					.append("activeBookings", new ArrayList<>())
					.append("pendingBookings", new ArrayList<>());
			List<Document> array = Arrays.asList(document);
			return  new Document().append("actualResults", array);
		}
		LocalDateTime now = LocalDateTime.now();
		Aggregation agg = newAggregation(
				match(where("").andOperator(where("brokerNo").is(brokerNo), where("companyCode")
						.is(compCode), where("bookings").exists(true))),
				sort(Sort.Direction.DESC, "bookings.bookingDate"), unwind("bookings"), 
				project("brokerNo", "brokerName","contactPerson2",
						"contactPerson1", "mobileNo", "occupation", "age", "addr1", "bookings.closedDate",
						"defaulter", "bookings.bookingNo", "bookings.auctioned", "bookings.closed", "bookings.dueDate",
						"bookings.amountTaken", "bookings.loanType", "bookings.tranType", "bookings.bookingDate", "bookings.bookingTrans"),
				unwind("bookingTrans", true),
				project("brokerNo", "brokerName","contactPerson2",
						"contactPerson1", "mobileNo", "occupation", "age", "addr1", "closedDate",
						"defaulter", "bookingNo", "auctioned", "closed", "dueDate", "loanType", "tranType",
						"amountTaken", "bookingDate", "bookingTrans.principle", "bookingTrans.intrest", "bookingTrans.rcvDate")
				.and(when(where("bookingTrans.transId").gt(0)).then(1).otherwise(0))
				.as("totalTrans"),
				group("brokerNo", "brokerName","contactPerson2", "contactPerson1", "closedDate", "mobileNo", "occupation", "age", "addr1", "defaulter",
						"bookingNo", "auctioned", "closed", "dueDate", "loanType", "tranType",
						"amountTaken", "bookingDate").sum("principle").as("totalPrinciplePaid")
								.sum("intrest").as("totalIntrestPaid")
								.sum("totalTrans").as("totalTrans")
								.last("rcvDate").as("lastTransDate"),
								//.push("rcvDate").as("lstTransDates"),
				group("brokerNo", "brokerName","contactPerson2", "contactPerson1", "mobileNo", "occupation", "age", "addr1", "defaulter")
				.push(when(where("_id.auctioned").is(true))
						.then(new Document().append("bookingNo", "$_id.bookingNo")
								.append("bookingDate", "$_id.bookingDate")
								.append("actualAmount", "$_id.amountTaken")
								.append("closedDate", "$_id.closedDate")
								.append("tranType", "$_id.tranType")
								.append("loanType", "$_id.loanType")
								.append("dueDate", "$_id.dueDate")
								.append("totalPrinciplePaid", "$totalPrinciplePaid")
								.append("lastTransDate", "$lastTransDate")
								.append("totalIntrestPaid", "$totalIntrestPaid")
								.append("totalTrans", "$totalTrans"))
						.otherwise("$$REMOVE")).as("auctionedBookings")
						.push(when(
								where("").andOperator(where("_id.auctioned").is(false), where("_id.closed").is(true)))
										.then(new Document().append("bookingNo", "$_id.bookingNo")
												.append("bookingDate", "$_id.bookingDate")
												.append("dueDate", "$_id.dueDate")
												.append("tranType", "$_id.tranType")
												.append("loanType", "$_id.loanType")
												.append("closedDate", "$_id.closedDate")
												.append("actualAmount", "$_id.amountTaken")
												.append("lastTransDate", "$lastTransDate")
												.append("totalPrinciplePaid", "$totalPrinciplePaid")
												.append("totalIntrestPaid", "$totalIntrestPaid")
												.append("totalTrans", "$totalTrans"))
										.otherwise("$$REMOVE"))
						.as("closedBookings")
						.push(when(where("").andOperator(where("_id.auctioned").is(false),
								where("_id.closed").is(false), where("_id.dueDate").gt(MongoLocalDateTime.of(now))))
										.then(new Document().append("bookingNo", "$_id.bookingNo")
												.append("bookingDate", "$_id.bookingDate")
												.append("dueDate", "$_id.dueDate")
												.append("tranType", "$_id.tranType")
												.append("loanType", "$_id.loanType")
												.append("lastTransDate", "$lastTransDate")
												.append("closedDate", "$_id.closedDate")
												.append("actualAmount", "$_id.amountTaken")
												.append("totalPrinciplePaid", "$totalPrinciplePaid")
												.append("totalIntrestPaid", "$totalIntrestPaid")
												.append("totalTrans", "$totalTrans"))
										.otherwise("$$REMOVE"))
						.as("activeBookings")
						.push(when(where("").andOperator(where("_id.auctioned").is(false),
								where("_id.closed").is(false), where("_id.dueDate").lte(MongoLocalDateTime.of(now))))
										.then(new Document().append("bookingNo", "$_id.bookingNo")
												.append("bookingDate", "$_id.bookingDate")
												.append("dueDate", "$_id.dueDate")
												.append("tranType", "$_id.tranType")
												.append("loanType", "$_id.loanType")
												.append("lastTransDate", "$lastTransDate")
												.append("closedDate", "$_id.closedDate")
												.append("actualAmount", "$_id.amountTaken")
												.append("totalPrinciplePaid", "$totalPrinciplePaid")
												.append("totalIntrestPaid", "$totalIntrestPaid")
												.append("totalTrans", "$totalTrans"))
										.otherwise("$$REMOVE"))
						.as("pendingBookings")

		);
		//.withOptions(AggregationOptions.builder().allowDiskUse(true).cursorBatchSize(10000).build())
		AggregationOptions options = AggregationOptions.builder().allowDiskUse(true).build();
		AggregationResults<DBObject> results = mongoTemplate.aggregate(agg.withOptions(options), Brokers.class, DBObject.class);
		List<DBObject> actualResults = results.getMappedResults();
		// List<DBObject> emptyResults = getEmptyBookingBrokers(search, compCode);
		Document doc = new Document().append("actualResults", actualResults);

		return doc;
	}

	public List<DBObject> getEmptyBookingBrokers(String search, String compCode) {
		LocalDateTime now = LocalDateTime.now();
		Aggregation agg = newAggregation(
				match(where("")
						.orOperator(where("brokerNo").regex(".*" + search + ".*", "i"),
								where("brokerName").regex(".*" + search + ".*", "i"),
								where("mobileNo").regex(".*" + search + ".*", "i"),
								where("bookings.bookingNo").regex(".*" + search + ".*", "i"),
								where("bookings.bookingCode").regex(".*" + search + ".*", "i"))
						.andOperator(where("companyCode").is(compCode), where("bookings").size(0))),
				sort(Sort.Direction.DESC, "bookings.bookingDate"),
				project("brokerNo", "brokerName", "defaulter").andExclude("_id")
						.and(when(where("brokerName").is("@#$%")).then(0).otherwise(0)).as("auctionedCount")
						.and(when(where("brokerName").is("@#$%")).then(0).otherwise(0)).as("closedCount")
						.and(when(where("brokerName").is("@#$%")).then(0).otherwise(0)).as("activeCount")
						.and(when(where("brokerName").is("@#$%")).then(0).otherwise(0)).as("pendingCount")
						.and(when(where("brokerName").is("@#$%")).then(0).otherwise(0)).as("total"));
		AggregationOptions options = AggregationOptions.builder().allowDiskUse(true).build();
		AggregationResults<DBObject> results = mongoTemplate.aggregate(agg.withOptions(options), Brokers.class, DBObject.class);
		return results.getMappedResults();
	}

	@Override
	public Brokers createBooking(BookingRequest req) {
		Brokers broker =mongoTemplate.findOne(query(where("brokerNo").is(req.getBrokerNo()).andOperator(where("companyCode").is(req.getCompanyCode()))), Brokers.class);
		Bookings booking  = new Bookings();
		booking.setAmountTaken(req.getAmountTaken());
		booking.setApproxAmt(req.getApproxAmt());
		booking.setAuctioned(req.getAuctioned());
		booking.setBookingCode(new Date().toGMTString());
		booking.setBookingNo(req.getBookingCode());
		booking.setBookingNo(req.getBookingNo());
		booking.setBookingDate(MongoDateUtil.toLocal(req.getBookingDate()));
		booking.setClosed(false);
		booking.setCreatedBy(req.getCreatedBy());
		booking.setCreatedDt(MongoDateUtil.toLocal(new Date()));
		booking.setDueDate(MongoDateUtil.toLocal(req.getDueDate()));
		booking.setGrossWeight(req.getGrossWeight());
		booking.setIntrestRate(req.getIntrestRate());
		booking.setIntrestType(req.getIntrestType());
		booking.setLoanType(req.getLoanType());
		booking.setNetWeight(req.getNetWeight());
		booking.setPurity(req.getPurity());
		booking.setRemarks(req.getRemarks());
		booking.setTranType(req.getTranType());
		booking.setUpdatedBy(req.getUpdatedBy());
		booking.setUpdatedDt(MongoDateUtil.toLocal(new Date()));
		booking.setValueDate(MongoDateUtil.toLocal(req.getValueDate()));
		if(req.getItems().size() >0) {
			for (int i = 0; i < req.getItems().size(); i++) {
				Items item = req.getItems().get(i);
				item.setCreatedBy(req.getCreatedBy());
				item.setUpdatedBy(req.getUpdatedBy());
				booking.getItems().add(item);
			}
		}
		broker.getBookings().add(booking);
		return mongoTemplate.save(broker);
	}

}
