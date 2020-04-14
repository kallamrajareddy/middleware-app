package com.kallam.middleware.request.model;

public class DetailBookingRequest {
	private String brokerNo;
	private String companyCode;
	private String bookingNo;
	public DetailBookingRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DetailBookingRequest(String brokerNo, String companyCode, String bookingNo) {
		super();
		this.brokerNo = brokerNo;
		this.companyCode = companyCode;
		this.bookingNo = bookingNo;
	}
	public String getBrokerNo() {
		return brokerNo;
	}
	public void setBrokerNo(String brokerNo) {
		this.brokerNo = brokerNo;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getBookingNo() {
		return bookingNo;
	}
	public void setBookingNo(String bookingNo) {
		this.bookingNo = bookingNo;
	}
}
