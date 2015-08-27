package com.processes.BeanClasses;

public class ProductBean extends Bean{
	int customerID;
	String ListOfProducts;
	
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public String getListOfProducts() {
		return ListOfProducts;
	}
	public void setListOfProducts(String listOfProducts) {
		ListOfProducts = listOfProducts;
	}
	@Override
	public String toString() {
		return "ProductBean [customerID=" + customerID + ", ListOfProducts="
				+ ListOfProducts + "]";
	}
	
	
}
