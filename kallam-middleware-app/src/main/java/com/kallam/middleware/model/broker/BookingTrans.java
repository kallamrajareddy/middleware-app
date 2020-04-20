package com.kallam.middleware.model.broker;

import com.kallam.middleware.helper.MongoLocalDateTime;

public class BookingTrans {
	public Integer transId;
	public MongoLocalDateTime rcvDate;
	public Double intrest;
	public Double principle;
	public String remarks;
	public MongoLocalDateTime dueDate;
	public MongoLocalDateTime valueDate;
	public String createdBy;
	public String updatedBy;
	public MongoLocalDateTime createdDt;
	public MongoLocalDateTime updatedDt;
	public BookingTrans() {
		super();
	}
	public BookingTrans(Integer transId, MongoLocalDateTime rcvDate, Double intrest, Double principle, String remarks,
			MongoLocalDateTime dueDate, MongoLocalDateTime valueDate, String createdBy, String updatedBy,
			MongoLocalDateTime createdDt, MongoLocalDateTime updatedDt) {
		super();
		this.transId = transId;
		this.rcvDate = rcvDate;
		this.intrest = intrest;
		this.principle = principle;
		this.remarks = remarks;
		this.dueDate = dueDate;
		this.valueDate = valueDate;
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
	public MongoLocalDateTime getRcvDate() {
		return rcvDate;
	}
	public void setRcvDate(MongoLocalDateTime rcvDate) {
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public MongoLocalDateTime getDueDate() {
		return dueDate;
	}
	public void setDueDate(MongoLocalDateTime dueDate) {
		this.dueDate = dueDate;
	}
	public MongoLocalDateTime getValueDate() {
		return valueDate;
	}
	public void setValueDate(MongoLocalDateTime valueDate) {
		this.valueDate = valueDate;
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
	}
