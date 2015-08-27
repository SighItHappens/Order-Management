package com.processes.BeanClasses;


public class ServiceBean extends Bean{
	int customerID;
	String listOfServices;
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	
	public String getListOfServices() {
		return listOfServices;
	}
	public void setListOfServices(String listOfServices) {
		this.listOfServices = listOfServices;
	}
	@Override
	public String toString() {
		return "ServiceBean [customerID=" + customerID + ", listOfProducts="
				+ listOfServices + "]";
	}
	
	
	
}
