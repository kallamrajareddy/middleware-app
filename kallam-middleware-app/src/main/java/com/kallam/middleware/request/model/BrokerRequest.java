package com.kallam.middleware.request.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kallam.middleware.model.broker.Bookings;

public class BrokerRequest {
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
	private String mobileNo;
	private String otherPhones1;
	private String otherPhones2;
	private String email;
	private Date dob;
	private Date dow;
	private String occupation;
	private Integer age;
	private String gender;
	private String remarks;
	private boolean ownrent;
	private boolean defaulter;
	private String contactPerson1;
	private String contact1Mobile;
	private String contact1PersonId;
	private String contact1Relation;
	private String contactPerson2;
	private String contact2Mobile;
	private String contact2PersonId;
	private String contact2Relation;
	private String createdBy;
	private String updatedBy;
	private Date createdDt;
	private Date updatedDt;
	private List<BookingRequest> bookings = new ArrayList<BookingRequest>();

	public BrokerRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BrokerRequest(String id, String companyCode, String brokerNo, String brokerName, String aadharNo,
			String addr1, String addr2, String addr3, String town, String district, String area, String zipCode,
			String mobileNo, String otherPhones1, String otherPhones2, String email, Date dob, Date dow,
			String occupation, Integer age, String gender, String remarks, boolean ownrent, boolean defaulter,
			String contactPerson1, String contact1Mobile, String contact1PersonId, String contact1Relation,
			String contactPerson2, String contact2Mobile, String contact2PersonId, String contact2Relation,
			String createdBy, String updatedBy, Date createdDt, Date updatedDt, List<BookingRequest> bookings) {
		super();
		this.id = id;
		this.companyCode = companyCode;
		this.brokerNo = brokerNo;
		this.brokerName = brokerName;
		this.aadharNo = aadharNo;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.addr3 = addr3;
		this.town = town;
		this.district = district;
		this.area = area;
		this.zipCode = zipCode;
		this.mobileNo = mobileNo;
		this.otherPhones1 = otherPhones1;
		this.otherPhones2 = otherPhones2;
		this.email = email;
		this.dob = dob;
		this.dow = dow;
		this.occupation = occupation;
		this.age = age;
		this.gender = gender;
		this.remarks = remarks;
		this.ownrent = ownrent;
		this.defaulter = defaulter;
		this.contactPerson1 = contactPerson1;
		this.contact1Mobile = contact1Mobile;
		this.contact1PersonId = contact1PersonId;
		this.contact1Relation = contact1Relation;
		this.contactPerson2 = contactPerson2;
		this.contact2Mobile = contact2Mobile;
		this.contact2PersonId = contact2PersonId;
		this.contact2Relation = contact2Relation;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdDt = createdDt;
		this.updatedDt = updatedDt;
		this.bookings = bookings;
	}

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

	public String getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
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

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getOtherPhones1() {
		return otherPhones1;
	}

	public void setOtherPhones1(String otherPhones1) {
		this.otherPhones1 = otherPhones1;
	}

	public String getOtherPhones2() {
		return otherPhones2;
	}

	public void setOtherPhones2(String otherPhones2) {
		this.otherPhones2 = otherPhones2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getDow() {
		return dow;
	}

	public void setDow(Date dow) {
		this.dow = dow;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public boolean isOwnrent() {
		return ownrent;
	}

	public void setOwnrent(boolean ownrent) {
		this.ownrent = ownrent;
	}

	public boolean isDefaulter() {
		return defaulter;
	}

	public void setDefaulter(boolean defaulter) {
		this.defaulter = defaulter;
	}

	public String getContactPerson1() {
		return contactPerson1;
	}

	public void setContactPerson1(String contactPerson1) {
		this.contactPerson1 = contactPerson1;
	}

	public String getContact1Mobile() {
		return contact1Mobile;
	}

	public void setContact1Mobile(String contact1Mobile) {
		this.contact1Mobile = contact1Mobile;
	}

	public String getContact1PersonId() {
		return contact1PersonId;
	}

	public void setContact1PersonId(String contact1PersonId) {
		this.contact1PersonId = contact1PersonId;
	}

	public String getContact1Relation() {
		return contact1Relation;
	}

	public void setContact1Relation(String contact1Relation) {
		this.contact1Relation = contact1Relation;
	}

	public String getContactPerson2() {
		return contactPerson2;
	}

	public void setContactPerson2(String contactPerson2) {
		this.contactPerson2 = contactPerson2;
	}

	public String getContact2Mobile() {
		return contact2Mobile;
	}

	public void setContact2Mobile(String contact2Mobile) {
		this.contact2Mobile = contact2Mobile;
	}

	public String getContact2PersonId() {
		return contact2PersonId;
	}

	public void setContact2PersonId(String contact2PersonId) {
		this.contact2PersonId = contact2PersonId;
	}

	public String getContact2Relation() {
		return contact2Relation;
	}

	public void setContact2Relation(String contact2Relation) {
		this.contact2Relation = contact2Relation;
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

	public Date getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
	}

	public Date getUpdatedDt() {
		return updatedDt;
	}

	public void setUpdatedDt(Date updatedDt) {
		this.updatedDt = updatedDt;
	}

	public List<BookingRequest> getBookings() {
		return bookings;
	}

	public void setBookings(List<BookingRequest> bookings) {
		this.bookings = bookings;
	}
}
