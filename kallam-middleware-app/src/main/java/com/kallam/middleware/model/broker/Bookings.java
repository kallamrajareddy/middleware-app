package com.kallam.middleware.model.broker;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "brokers")
public class Bookings {
	@Id
	private String id;
	public String bookingDate;
	public String bookingNo;
	public String bookingCode;
	public String remarks;
	public Integer expDays;
	public Double grossWeight;
	public Double netWeight;
	public Double purity;
	public String approxAmt;
	public Double amountTaken;
	public String dueDate;
	public String repayDate;
	public Double repayAmount;
	public String closeType;
	public String tranType;
	public String loanType;
	public Boolean closed;
	public Boolean auctioned;
	public String valueDate;
	public Double intrestRate;
	public String closedDate;
	public List<Items> items = new ArrayList<Items>();
	public List<BookingTrans> bookingTrans = new ArrayList<BookingTrans>();
	public String createdBy;
	public String updatedBy;
	public String createdDt;
	public String updatedDt;
	public String getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}
	public String getBookingNo() {
		return bookingNo;
	}
	public void setBookingNo(String bookingNo) {
		this.bookingNo = bookingNo;
	}
	public String getBookingCode() {
		return bookingCode;
	}
	public void setBookingCode(String bookingCode) {
		this.bookingCode = bookingCode;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getExpDays() {
		return expDays;
	}
	public void setExpDays(Integer expDays) {
		this.expDays = expDays;
	}
	public Double getGrossWeight() {
		return grossWeight;
	}
	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
	}
	public Double getNetWeight() {
		return netWeight;
	}
	public void setNetWeight(Double netWeight) {
		this.netWeight = netWeight;
	}
	public Double getPurity() {
		return purity;
	}
	public void setPurity(Double purity) {
		this.purity = purity;
	}
	public String getApproxAmt() {
		return approxAmt;
	}
	public void setApproxAmt(String approxAmt) {
		this.approxAmt = approxAmt;
	}
	public Double getAmountTaken() {
		return amountTaken;
	}
	public void setAmountTaken(Double amountTaken) {
		this.amountTaken = amountTaken;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getRepayDate() {
		return repayDate;
	}
	public void setRepayDate(String repayDate) {
		this.repayDate = repayDate;
	}
	public Double getRepayAmount() {
		return repayAmount;
	}
	public void setRepayAmount(Double repayAmount) {
		this.repayAmount = repayAmount;
	}
	public String getCloseType() {
		return closeType;
	}
	public void setCloseType(String closeType) {
		this.closeType = closeType;
	}
	public String getTranType() {
		return tranType;
	}
	public void setTranType(String tranType) {
		this.tranType = tranType;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public Boolean getClosed() {
		return closed;
	}
	public void setClosed(Boolean closed) {
		this.closed = closed;
	}
	public Boolean getAuctioned() {
		return auctioned;
	}
	public void setAuctioned(Boolean auctioned) {
		this.auctioned = auctioned;
	}
	public String getValueDate() {
		return valueDate;
	}
	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}
	public Double getIntrestRate() {
		return intrestRate;
	}
	public void setIntrestRate(Double intrestRate) {
		this.intrestRate = intrestRate;
	}
	public String getClosedDate() {
		return closedDate;
	}
	public void setClosedDate(String closedDate) {
		this.closedDate = closedDate;
	}
	public List<Items> getItems() {
		return items;
	}
	public void setItems(List<Items> items) {
		this.items = items;
	}
	public List<BookingTrans> getBookingTrans() {
		return bookingTrans;
	}
	public void setBookingTrans(List<BookingTrans> bookingTrans) {
		this.bookingTrans = bookingTrans;
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
