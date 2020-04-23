package com.kallam.middleware.request.model;

import java.util.Date;

public class NewRecipt {
	
	private Long transId;
	private String brokerNo;
	private String bookingNo;
	private String companyCode;
	private Date rcvDate;
	private Double principle;
	private Double intrest;
    private String remarks;
    private Date dueDate;
    private Date valueDate;
    private String createdBy;
    public NewRecipt() {
		super();
	}
	public NewRecipt(Long transId, String brokerNo, String bookingNo, String companyCode, Date rcvDate, Double principle,
			Double intrest, String remarks, Date dueDate, Date valueDate, String createdBy) {
		super();
		this.brokerNo = brokerNo;
		this.transId = transId;
		this.bookingNo = bookingNo;
		this.companyCode = companyCode;
		this.rcvDate = rcvDate;
		this.principle = principle;
		this.intrest = intrest;
		this.remarks = remarks;
		this.dueDate = dueDate;
		this.valueDate = valueDate;
		this.createdBy = createdBy;
	}
	public Long getTransId() {
		return transId;
	}
	public void setTransId(Long transId) {
		this.transId = transId;
	}
	public String getBrokerNo() {
		return brokerNo;
	}
	public void setBrokerNo(String brokerNo) {
		this.brokerNo = brokerNo;
	}
	public String getBookingNo() {
		return bookingNo;
	}
	public void setBookingNo(String bookingNo) {
		this.bookingNo = bookingNo;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public Date getRcvDate() {
		return rcvDate;
	}
	public void setRcvDate(Date rcvDate) {
		this.rcvDate = rcvDate;
	}
	public Double getPrinciple() {
		return principle;
	}
	public void setPrinciple(Double principle) {
		this.principle = principle;
	}
	public Double getIntrest() {
		return intrest;
	}
	public void setIntrest(Double intrest) {
		this.intrest = intrest;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Date getValueDate() {
		return valueDate;
	}
	public void setValueDate(Date valueDate) {
		this.valueDate = valueDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}
