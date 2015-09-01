package com.processes.DAOClasses;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.processes.BeanClasses.CustomerBean;
import com.processes.BeanClasses.ServiceBean;

public class ServiceDAOTest {
	static int z;
	ServiceDAO service=new ServiceDAO();
	ServiceBean sbean1=new ServiceBean();
	 

	@BeforeClass
	public static void setUp() throws Exception {
		CustomerDAO cust=new CustomerDAO();
		String pattern="dd-MMM-yyyy";
		SimpleDateFormat format=new SimpleDateFormat(pattern);
		
		java.util.Date dob=format.parse("17-Aug-2015");
		java.util.Date dob1=format.parse("17-Oct-1993");
		java.sql.Date d1=new java.sql.Date(dob1.getTime());
		java.sql.Date d2=new java.sql.Date(dob.getTime());
		
		
		
		CustomerBean cbeanexpectedd1=new CustomerBean();
		
		cbeanexpectedd1.setLineOfBusiness("ves");
		cbeanexpectedd1.setFirstName("danielvibind");
		cbeanexpectedd1.setDateOfBirth(d1);
		cbeanexpectedd1.setEmailId("danirt123@gmail.com");
		cbeanexpectedd1.setBillStartDate(d2);
		cbeanexpectedd1.setBillingAddress("Chennai");
		cbeanexpectedd1.setCustomerStatus("active");
		cbeanexpectedd1.setConnectionAddress("Hyderabad");
		cbeanexpectedd1.setContactNumber("9566237055");
		cbeanexpectedd1.setLastName("aaj");
		z=cust.add(cbeanexpectedd1);
		//System.out.println(z);
		
	}

	@AfterClass
	public static void tearDown() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:xe", "verizon",
				"password");
		Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		int id=z;
		st.executeQuery("delete from  services where customer_id="+id);
		
		
		Statement st1= con.createStatement();
		
		
		st1.executeQuery("delete from customer_table where customer_id="+id);
	}
	@Test
	public void testAdd() {
		sbean1.setCustomerID(z);
		sbean1.setListOfServices("cable");
		int check=service.add(sbean1);
		assertEquals(1,check);
		//assertTrue(service.successflag==true);
		
	}

	@Test
	public void testView() {
		ServiceBean sbean = new ServiceBean();
		sbean=(ServiceBean) service.view(z);
	assertEquals(z,sbean.getCustomerID());
		
		
	}

	

	@Test
	public void testUpdateStringStringInt() {
		
		int a=service.update("List_of_services","Data",z);
		assertEquals(1,a);
	
	}

	

}
