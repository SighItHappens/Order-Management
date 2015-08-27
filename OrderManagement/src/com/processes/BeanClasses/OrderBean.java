package com.processes.BeanClasses;

import java.sql.Date;

public class OrderBean extends Bean{
	
	int orderID;
	int customerID;
	Date dateOfBooking;
	Date dueDate;
	String orderStatus;
	int quantity;
	String listOfServices;
	String listOfProducts;
	
	
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public Date getDateOfBooking() {
		return dateOfBooking;
	}
	public void setDateOfBooking(Date dateOfBooking) {
		this.dateOfBooking = dateOfBooking;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getListOfServices() {
		return listOfServices;
	}
	public void setListOfServices(String listOfServices) {
		this.listOfServices = listOfServices;
	}
	public String getListOfProducts() {
		return listOfProducts;
	}
	public void setListOfProducts(String listOfProducts) {
		this.listOfProducts = listOfProducts;
	}
	@Override
	public String toString() {
		return "OrderBean [orderID=" + orderID + ", customerID=" + customerID
				+ ", dateOfBooking=" + dateOfBooking + ", dueDate=" + dueDate
				+ ", orderStatus=" + orderStatus + ", quantity=" + quantity + ", listOfServices="
				+ listOfServices + ", listOfProducts=" + listOfProducts + "]";
	}
	
	
}
