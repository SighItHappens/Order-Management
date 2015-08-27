package com.processes.BeanClasses;

public class MappingBean extends Bean {
	
	int serviceID;
	String listOfProducts;
	
	public int getServiceID() {
		return serviceID;
	}
	public void setServiceID(int serviceID) {
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
