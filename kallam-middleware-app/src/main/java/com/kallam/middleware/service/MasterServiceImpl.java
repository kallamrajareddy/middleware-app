package com.kallam.middleware.service;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.kallam.middleware.model.MasterData;
import com.kallam.middleware.model.PostalData;

@Service
public class MasterServiceImpl implements MasterService{
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public MasterData getMasterData() {
		return mongoTemplate.findOne(query(where("")), MasterData.class);
	}

	@Override
	public MasterData saveMasterData(MasterData master) {
		MasterData masterData = mongoTemplate.save(master);
		return masterData;
	}

	@Override
	public List<PostalData> getPostalCode(String code) {
		return mongoTemplate.find(query(where("pincode").is(Integer.valueOf(code))), PostalData.class);
	}

}
