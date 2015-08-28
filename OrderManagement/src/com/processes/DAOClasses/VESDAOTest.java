package com.processes.DAOClasses;


import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.processes.BeanClasses.CustomerBean;
import com.processes.BeanClasses.VESBean;

public class VESDAOTest {
	VESDAO ves=new VESDAO();
	static int check,z;


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
		cbeanexpectedd1.setEmailId("test99@gmail.com");
		cbeanexpectedd1.setBillStartDate(d2);
		cbeanexpectedd1.setBillingAddress("Chennai");
		cbeanexpectedd1.setCustomerStatus("active");
		cbeanexpectedd1.setConnectionAddress("Hyderabad");
		cbeanexpectedd1.setContactNumber("9566237055");
		cbeanexpectedd1.setLastName("aaj");
		z=cust.add(cbeanexpectedd1);
		
		
		
		
	}

	@AfterClass
	public static void tearDown() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:orcl", "verizon",
				"password");
		Statement st = con.createStatement();
		int id=check;
	st.executeQuery("delete from ves where contract_id="+id);
	Statement st1= con.createStatement();
		
		int id1=z;
		st1.executeQuery("delete from customer_table where customer_id="+id1);
	}
	
	@Test
	public void testAdd() throws ParseException {
		VESBean vbean = new VESBean();
		java.util.Date dob;
		java.util.Date dob1;
		String pattern="dd-MMM-yyyy";
		SimpleDateFormat format=new SimpleDateFormat(pattern);
		dob=format.parse("02-AUG-2015");
		dob1=format.parse("03-AUG-2015");
		java.sql.Date d1=new java.sql.Date(dob1.getTime());
		java.sql.Date d2=new java.sql.Date(dob.getTime());
		vbean.setModelType("rtb");
		vbean.setCustomerID(z);
		vbean.setClassOfService("gold");
		vbean.setFromDate(d2);
		vbean.setToDate(d1);
		vbean.setDiscountPercentage(5);
		vbean.setChange(10);
		vbean.setCurrent(5);
		vbean.setMax(20);
		check=ves.add(vbean);
		assertTrue(ves.successflag==true);
	}
	


	@Test
	public void testView() throws ParseException {
	VESBean vbean=new VESBean();
		java.util.Date dob;
		java.util.Date dob1;
		String pattern="dd-MMM-yyyy";
		SimpleDateFormat format=new SimpleDateFormat(pattern);
		dob=format.parse("02-AUG-2015");
		dob1=format.parse("03-AUG-2015");
		java.sql.Date d1=new java.sql.Date(dob1.getTime());
		java.sql.Date d2=new java.sql.Date(dob.getTime());
		vbean.setModelType("rtb");
		vbean.setCustomerID(z);
		vbean.setClassOfService("gold");
		vbean.setFromDate(d2);
		vbean.setToDate(d1);
		vbean.setDiscountPercentage(5);
		vbean.setChange(10);
		vbean.setCurrent(5);
		vbean.setMax(20);
		VESBean vresultbean1=ves.view(check);
		assertEquals(vbean.getModelType(),vresultbean1.getModelType());
		assertEquals(vbean.getClassOfService(),vresultbean1.getClassOfService());
		assertEquals(vbean.getFromDate(),vresultbean1.getFromDate());
		assertEquals(vbean.getToDate(),vresultbean1.getToDate());
		assertEquals(vbean.getDiscountPercentage(),vresultbean1.getDiscountPercentage());
		assertEquals(vbean.getChange(),vresultbean1.getChange());
		assertEquals(vbean.getCurrent(),vresultbean1.getCurrent());
		assertEquals(vbean.getMax(),vresultbean1.getMax());
		
	}

	
	@Test
	public void testViewByCustomerID() throws ParseException {
		ves.viewByCustomerID(z);
		assertTrue(ves.successflag1==true);
	}
	@Test
	public void testDisplayByEmail() throws ParseException {
		String str="test99@gmail.com";
		ves.displayByEmail(str);
		assertTrue(ves.successflag2==true);
	}

	@Test
	public void testUpdateStringStringInt() {
		int a=ves.update("class_of_service","platinum",check);
		assertEquals(1,a);
		}

	@Test
	public void testUpdateStringIntInt() {
		int a=ves.update("discount_percentage",15,check);
		assertEquals(1,a);
		}

	@Test
	public void testUpdateStringDateInt() throws ParseException {
		
		java.util.Date dob3;
		
		String pattern="dd-MMM-yyyy";
		SimpleDateFormat format=new SimpleDateFormat(pattern);
		dob3=format.parse("10-AUG-2015");
		java.sql.Date d3=new java.sql.Date(dob3.getTime());
		int a=ves.update("from_date",d3,check);
		assertEquals(1,a);
}
}
