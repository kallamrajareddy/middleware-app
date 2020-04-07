package com.kallam.middleware.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.domain.Sort.sort;
import static org.springframework.data.domain.Sort.by;

import com.kallam.middleware.MongoDateUtil;
import com.kallam.middleware.model.broker.Brokers;
import com.kallam.middleware.request.model.BrokerRequest;

@Service
public class BrokerServiceImpl implements BrokerService{
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	private Path rootLocation;
	
	@Value("${app.upload.path}")
	private String uploadPath;
	

	@Override
	public List<Brokers> getBrokers(String search, String compCode) {
		Sort sort = Sort.by("booking.closed").ascending();
		Query query = new Query();
		query.with(sort);
		List<Brokers> brokers = mongoTemplate.find(query(where("").orOperator(where("brokerName").regex(".*"+search+".*", "i")
				,where("addr1").regex(".*"+search+".*", "i")
				,where("addr2").regex(".*"+search+".*", "i")
				,where("addr3").regex(".*"+search+".*", "i")
				,where("brokerNo").regex(".*"+search+".*", "i")
				,where("mobileNo").regex(".*"+search+".*", "i")).andOperator(where("companyCode").is(compCode))).with(Sort.by(Sort.Direction.DESC, "booking.closed")), Brokers.class);
		//return brokereBetweenDob(new Date(), new Date());
		return brokers;
	}


	@Override
	public List<Brokers> brokereBetweenDob(Date from, Date to) {
		List<Brokers> brokers = mongoTemplate.find(query(where("dob").lte(from)), Brokers.class);
		return brokers;
	}
	

	@Override
	public Boolean checkBrokerExists(String name, String compCode) {
		return  mongoTemplate.exists(query(where("brokerName").is(name).andOperator(where("companyCode").is(compCode))), Brokers.class);
	}

	@Override
	public String getBrokersCount(String compCode) {
		long count = (mongoTemplate.count(query(where("").andOperator(where("companyCode").is(compCode))), Brokers.class)+1);
		String code = compCode + "-"+String.format ("%04d", count);
		return  code;
	}


	@Override
	public Brokers createBroker(BrokerRequest brokerRequest, MultipartFile custImage) {
		
		Brokers broker = new Brokers();
		broker.setAadharNo(brokerRequest.getAadharNo());
		broker.setAddr1(brokerRequest.getAddr1());
		broker.setAddr2(brokerRequest.getAddr2());
		broker.setAddr3(brokerRequest.getAddr3());
		broker.setAge(brokerRequest.getAge());
		broker.setArea(brokerRequest.getArea());
		broker.setBrokerName(brokerRequest.getBrokerName());
		broker.setBrokerNo(brokerRequest.getBrokerNo());
		broker.setCompanyCode(brokerRequest.getCompanyCode());
		broker.setContact1Mobile(brokerRequest.getContact1Mobile());
		broker.setContact1PersonId(brokerRequest.getContact1PersonId());
		broker.setContact2Relation(brokerRequest.getContact2Relation());
		broker.setContact2Mobile(brokerRequest.getContact2Mobile());
		broker.setContact2PersonId(brokerRequest.getContact2PersonId());
		broker.setContact2Relation(brokerRequest.getContact2Relation());
		broker.setContactPerson1(brokerRequest.getContactPerson1());
		broker.setContactPerson2(brokerRequest.getContactPerson2());
		broker.setCreatedBy(brokerRequest.getCreatedBy());
		broker.setCreatedDt(MongoDateUtil.toLocal(new Date()));
		broker.setDefaulter(false);
		broker.setDistrict(brokerRequest.getDistrict());
		if(brokerRequest.getDob() != null) {
			broker.setDob(MongoDateUtil.toLocal(brokerRequest.getDob()));
		}
		if(brokerRequest.getDow() != null) {
			broker.setDow(MongoDateUtil.toLocal(brokerRequest.getDow()));
		}
		broker.setEmail(brokerRequest.getEmail());
		broker.setGender(brokerRequest.getGender());
		broker.setMobileNo(brokerRequest.getMobileNo());
		broker.setOccupation(brokerRequest.getOccupation());
		broker.setOtherPhones1(brokerRequest.getOtherPhones1());
		broker.setOtherPhones2(brokerRequest.getOtherPhones2());
		broker.setOwnrent(brokerRequest.isOwnrent());
		broker.setRemarks(brokerRequest.getRemarks());
		broker.setTown(brokerRequest.getTown());
		broker.setUpdatedBy(brokerRequest.getUpdatedBy());
		broker.setUpdatedDt(MongoDateUtil.toLocal(new Date()));
		broker.setZipCode(brokerRequest.getZipCode());
		copyImage(custImage, brokerRequest.getBrokerNo());
		return mongoTemplate.save(broker);
		//return null;
	}
	private void copyImage(MultipartFile custImage, String brokerNo) {
		rootLocation = Paths.get(uploadPath);
		
		String filename = StringUtils.cleanPath(custImage.getOriginalFilename());
	    filename = filename.toLowerCase().replaceAll(" ", "-");
	    filename = brokerNo+".jpg";
	    System.out.println(filename);
		
		try {
			Files.copy(custImage.getInputStream(), this.rootLocation.resolve(filename),
			        StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
