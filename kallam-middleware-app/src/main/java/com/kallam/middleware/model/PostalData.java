package com.kallam.middleware.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "zips")
public class PostalData {

	@Id
	private String id;
	private String officeName;
	private Integer pincode;
	private String taluk;
	private String districtName;
	private String stateName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOfficeName() {
		return officeName;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	public Integer getPincode() {
		return pincode;
	}
	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}
	public String getTaluk() {
		return taluk;
	}
	public void setTaluk(String taluk) {
		this.taluk = taluk;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public PostalData(String id, String officeName, Integer pincode, String taluk, String districtName,
			String stateName) {
		super();
		this.id = id;
		this.officeName = officeName;
		this.pincode = pincode;
		this.taluk = taluk;
		this.districtName = districtName;
		this.stateName = stateName;
	}

}
