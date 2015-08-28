package com.processes.BeanClasses;

public class MappingBean extends Bean {
	
	String serviceID;
	String listOfProducts;
	
	public String getServiceID() {
		return serviceID;
	}
	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}
	public String getListOfProducts() {
		return listOfProducts;
	}
	public void setListOfProducts(String listOfProducts) {
		this.listOfProducts = listOfProducts;
	}
	@Override
	public String toString() {
		return "MappingBean [serviceID=" + serviceID + ", listOfProducts="
				+ listOfProducts + "]";
	}
	
	
}
