package com.kallam.middleware.request.model;

import java.util.Date;

public class BookingTransRequest {
	public Integer transId;
	public Date rcvDate;
	public Double intrest;
	public Double principle;
	public String description;
	public String createdBy;
	public String updatedBy;
	public Date createdDt;
	public Date updatedDt;
	
	public BookingTransRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookingTransRequest(Integer transId, Date rcvDate, Double intrest, Double principle, String description,
			String createdBy, String updatedBy, Date createdDt, Date updatedDt) {
		super();
		this.transId = transId;
		this.rcvDate = rcvDate;
		this.intrest = intrest;
		this.principle = principle;
		this.description = description;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdDt = createdDt;
		this.updatedDt = updatedDt;
	}
	public Integer getTransId() {
		return transId;
	}
	public void setTransId(Integer transId) {
		this.transId = transId;
	}
	public Date getRcvDate() {
		return rcvDate;
	}
	public void setRcvDate(Date rcvDate) {
		this.rcvDate = rcvDate;
	}
	public Double getIntrest() {
		return intrest;
	}
	public void setIntrest(Double intrest) {
		this.intrest = intrest;
	}
	public Double getPrinciple() {
		return principle;
	}
	public void setPrinciple(Double principle) {
		this.principle = principle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	}
