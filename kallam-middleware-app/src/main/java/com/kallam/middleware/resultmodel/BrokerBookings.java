package com.kallam.middleware.resultmodel;

import org.springframework.data.mongodb.core.mapping.Field;

import com.kallam.middleware.helper.MongoLocalDateTime;
import com.kallam.middleware.model.broker.Bookings;

public class BrokerBookings {
	
	@Field("_id")
	private String id;
	private String companyCode;
	private String brokerNo;
	private String brokerName;
	private String aadharNo;
	private String addr1;
	private String addr2;
	private String addr3;
	private String town;
	private String district;
	private String area;
	private String zipCode;
	private String contactPerson;
	private String contactPersonId;
	private String otherPhones;
	private String relationPhones;
	private String mobileNo;
	private String email;
	private MongoLocalDateTime dob;
	private MongoLocalDateTime dow;
	private String discountCategory;
	private Integer creditLimit;
	private String remarks;
	private String invType;
	private String adjustBills;
	private String contactRelation;
	private String occupation;
	private Integer age;
	private String caste;
	private String gender;
	private String createdBy;
	private String updatedBy;
	private MongoLocalDateTime createdDt;
	private MongoLocalDateTime updatedDt;
	private String ownrent;
	private String simbrand;
	private Bookings bookings;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getBrokerNo() {
		return brokerNo;
	}
	public void setBrokerNo(String brokerNo) {
		this.brokerNo = brokerNo;
	}
	public String getBrokerName() {
		return brokerName;
	}
	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getAddr3() {
		return addr3;
	}
	public void setAddr3(String addr3) {
		this.addr3 = addr3;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getOtherPhones() {
		return otherPhones;
	}
	public void setOtherPhones(String otherPhones) {
		this.otherPhones = otherPhones;
	}
	public String getRelationPhones() {
		return relationPhones;
	}
	public void setRelationPhones(String relationPhones) {
		this.relationPhones = relationPhones;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public MongoLocalDateTime getDob() {
		return dob;
	}
	public void setDob(MongoLocalDateTime dob) {
		this.dob = dob;
	}
	public MongoLocalDateTime getDow() {
		return dow;
	}
	public void setDow(MongoLocalDateTime dow) {
		this.dow = dow;
	}
	public String getDiscountCategory() {
		return discountCategory;
	}
	public void setDiscountCategory(String discountCategory) {
		this.discountCategory = discountCategory;
	}
	public Integer getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(Integer creditLimit) {
		this.creditLimit = creditLimit;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getInvType() {
		return invType;
	}
	public void setInvType(String invType) {
		this.invType = invType;
	}
	public String getAdjustBills() {
		return adjustBills;
	}
	public void setAdjustBills(String adjustBills) {
		this.adjustBills = adjustBills;
	}
	public String getContactRelation() {
		return contactRelation;
	}
	public void setContactRelation(String contactRelation) {
		this.contactRelation = contactRelation;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getCaste() {
		return caste;
	}
	public void setCaste(String caste) {
		this.caste = caste;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public MongoLocalDateTime getCreatedDt() {
		return createdDt;
	}
	public void setCreatedDt(MongoLocalDateTime createdDt) {
		this.createdDt = createdDt;
	}
	public MongoLocalDateTime getUpdatedDt() {
		return updatedDt;
	}
	public void setUpdatedDt(MongoLocalDateTime updatedDt) {
		this.updatedDt = updatedDt;
	}
	public String getOwnrent() {
		return ownrent;
	}
	public void setOwnrent(String ownrent) {
		this.ownrent = ownrent;
	}
	public String getAadharNo() {
		return aadharNo;
	}
	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}
	public String getContactPersonId() {
		return contactPersonId;
	}
	public void setContactPersonId(String contactPersonId) {
		this.contactPersonId = contactPersonId;
	}
	public String getSimbrand() {
		return simbrand;
	}
	public void setSimbrand(String simbrand) {
		this.simbrand = simbrand;
	}
	public Bookings getBookings() {
		return bookings;
	}
	public void setBookings(Bookings bookings) {
		this.bookings = bookings;
	}
	@Override
	public String toString() {
		return "Brokers [id=" + id + ", companyCode=" + companyCode + ", brokerNo=" + brokerNo + ", brokerName="
				+ brokerName + ", aadharNo=" + aadharNo + ", addr1=" + addr1 + ", addr2=" + addr2 + ", addr3=" + addr3
				+ ", town=" + town + ", district=" + district + ", area=" + area + ", zipCode=" + zipCode
				+ ", contactPerson=" + contactPerson + ", contactPersonId=" + contactPersonId + ", otherPhones="
				+ otherPhones + ", relationPhones=" + relationPhones + ", mobileNo=" + mobileNo + ", email=" + email
				+ ", dob=" + dob + ", dow=" + dow + ", discountCategory=" + discountCategory + ", creditLimit="
				+ creditLimit + ", remarks=" + remarks + ", invType=" + invType + ", adjustBills=" + adjustBills
				+ ", contactRelation=" + contactRelation + ", occupation=" + occupation + ", age=" + age + ", caste="
				+ caste + ", gender=" + gender + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy
				+ ", createdDt=" + createdDt + ", updatedDt=" + updatedDt + ", ownrent=" + ownrent + ", simbrand="
				+ simbrand + ", bookings=" + bookings + "]";
	}
}
