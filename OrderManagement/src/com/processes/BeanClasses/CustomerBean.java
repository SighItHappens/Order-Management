package com.processes.BeanClasses;

import java.sql.Date;

public class CustomerBean extends Bean{
	
	int customerId;
	String lineOfBusiness;
	String firstName;
	String lastName;
	String emailId; 
	Date dateOfBirth;
	Date billStartDate;
	String billingAddress;	
	String customerStatus;
	String connectionAddress;
	String contactNumber;
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getLineOfBusiness() {
		return lineOfBusiness;
	}
	public void setLineOfBusiness(String lineOfBusiness) {
		this.lineOfBusiness = lineOfBusiness;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Date getBillStartDate() {
		return billStartDate;
	}
	public void setBillStartDate(Date billStartDate) {
		this.billStartDate = billStartDate;
	}
	public String getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}
	public String getCustomerStatus() {
		return customerStatus;
	}
	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}
	public String getConnectionAddress() {
		return connectionAddress;
	}
	public void setConnectionAddress(String connectionAddress) {
		this.connectionAddress = connectionAddress;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	@Override
	public String toString() {
		return "CustomerBean [customerId=" + customerId + ", lineOfBusiness="
				+ lineOfBusiness + ", firstName=" + firstName + ", lastName="
				+ lastName + ", emailId=" + emailId + ", dateOfBirth="
				+ dateOfBirth + ", billStartDate=" + billStartDate
				+ ", billingAddress=" + billingAddress + ", customerStatus="
				+ customerStatus + ", connectionAddress=" + connectionAddress
				+ ", contactNumber=" + contactNumber + "]";
	}
	
	

	

}
