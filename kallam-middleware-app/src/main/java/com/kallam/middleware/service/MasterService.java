package com.kallam.middleware.service;

import com.kallam.middleware.model.MasterData;

public interface MasterService {
	MasterData getMasterData();
	MasterData saveMasterData(MasterData master);

}
