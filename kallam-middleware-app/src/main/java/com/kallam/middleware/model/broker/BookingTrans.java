package com.kallam.middleware.model.broker;

public class BookingTrans {
	public Integer transId;
	public String rcvDate;
	public Double intrest;
	public Double principle;
	public String description;
	public String createdBy;
	public String updatedBy;
	public String createdDt;
	public String updatedDt;
	public Integer getTransId() {
		return transId;
	}
	public void setTransId(Integer transId) {
		this.transId = transId;
	}
	public String getRcvDate() {
		return rcvDate;
	}
	public void setRcvDate(String rcvDate) {
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
	public String getCreatedDt() {
		return createdDt;
	}
	public void setCreatedDt(String createdDt) {
		this.createdDt = createdDt;
	}
	public String getUpdatedDt() {
		return updatedDt;
	}
	public void setUpdatedDt(String updatedDt) {
		this.updatedDt = updatedDt;
	}
}
