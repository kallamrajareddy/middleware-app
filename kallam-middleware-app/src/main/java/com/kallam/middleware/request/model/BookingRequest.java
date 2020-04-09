package com.kallam.middleware.request.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kallam.middleware.model.broker.BookingTrans;
import com.kallam.middleware.model.broker.Items;

public class BookingRequest {
	public Date bookingDate;
	public String bookingNo;
	public String bookingCode;
	public String remarks;
	public Integer expDays;
	public Double grossWeight;
	public Double netWeight;
	public Double purity;
	public String approxAmt;
	public Double amountTaken;
	public Date dueDate;
	public Date repayDate;
	public Double repayAmount;
	public String closeType;
	public String tranType;
	public String loanType;
	public Boolean closed;
	public Boolean auctioned;
	public Date valueDate;
	public Double intrestRate;
	public Date closedDate;
	public List<Items> items = new ArrayList<Items>();
	public List<BookingTransRequest> bookingTrans = new ArrayList<BookingTransRequest>();
	public String createdBy;
	public String updatedBy;
	public Date createdDt;
	public Date updatedDt;
	public BookingRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookingRequest(Date bookingDate, String bookingNo, String bookingCode, String remarks,
			Integer expDays, Double grossWeight, Double netWeight, Double purity, String approxAmt, Double amountTaken,
			Date dueDate, Date repayDate, Double repayAmount, String closeType,
			String tranType, String loanType, Boolean closed, Boolean auctioned, Date valueDate,
			Double intrestRate, Date closedDate, List<Items> items, List<BookingTransRequest> bookingTrans,
			String createdBy, String updatedBy, Date createdDt, Date updatedDt) {
		super();
		this.bookingDate = bookingDate;
		this.bookingNo = bookingNo;
		this.bookingCode = bookingCode;
		this.remarks = remarks;
		this.expDays = expDays;
		this.grossWeight = grossWeight;
		this.netWeight = netWeight;
		this.purity = purity;
		this.approxAmt = approxAmt;
		this.amountTaken = amountTaken;
		this.dueDate = dueDate;
		this.repayDate = repayDate;
		this.repayAmount = repayAmount;
		this.closeType = closeType;
		this.tranType = tranType;
		this.loanType = loanType;
		this.closed = closed;
		this.auctioned = auctioned;
		this.valueDate = valueDate;
		this.intrestRate = intrestRate;
		this.closedDate = closedDate;
		this.items = items;
		this.bookingTrans = bookingTrans;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdDt = createdDt;
		this.updatedDt = updatedDt;
	}
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
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
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Date getRepayDate() {
		return repayDate;
	}
	public void setRepayDate(Date repayDate) {
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
	public Date getValueDate() {
		return valueDate;
	}
	public void setValueDate(Date valueDate) {
		this.valueDate = valueDate;
	}
	public Double getIntrestRate() {
		return intrestRate;
	}
	public void setIntrestRate(Double intrestRate) {
		this.intrestRate = intrestRate;
	}
	public Date getClosedDate() {
		return closedDate;
	}
	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}
	public List<Items> getItems() {
		return items;
	}
	public void setItems(List<Items> items) {
		this.items = items;
	}
	public List<BookingTransRequest> getBookingTrans() {
		return bookingTrans;
	}
	public void setBookingTrans(List<BookingTransRequest> bookingTrans) {
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
