package com.kallam.middleware.service;

import java.util.List;

import com.kallam.middleware.model.MasterData;
import com.kallam.middleware.model.PostalData;

public interface MasterService {
	MasterData getMasterData();
	MasterData saveMasterData(MasterData master);
	List<PostalData> getPostalCode(String code);

}
