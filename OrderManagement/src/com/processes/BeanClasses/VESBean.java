package com.processes.BeanClasses;

import java.sql.Date;


public class VESBean extends Bean{
	
	int contractID;
	String modelType;
	int customerID;
	String classOfService;
	Date fromDate;
	Date toDate;
	int discountPercentage;
	int change;
	int current;
	int max;
	
	public int getContractID() {
		return contractID;
	}
	public void setContractID(int contractID) {
		this.contractID = contractID;
	}
	public String getModelType() {
		return modelType;
	}
	public void setModelType(String modelType) {
		this.modelType = modelType;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public String getClassOfService() {
		return classOfService;
	}
	public void setClassOfService(String classOfService) {
		this.classOfService = classOfService;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public int getDiscountPercentage() {
		return discountPercentage;
	}
	public void setDiscountPercentage(int discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	public int getChange() {
		return change;
	}
	public void setChange(int change) {
		this.change = change;
	}
	public int getCurrent() {
		return current;
	}
	public void setCurrent(int current) {
		this.current = current;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	@Override
	public String toString() {
		return "VESBean [contractID=" + contractID + ", modelType=" + modelType
				+ ", customerID=" + customerID + ", classOfService="
				+ classOfService + ", fromDate=" + fromDate + ", toDate="
				+ toDate + ", discountPercentage=" + discountPercentage
				+ ", change=" + change + ", current=" + current + ", max="
				+ max + "]";
	}

}
