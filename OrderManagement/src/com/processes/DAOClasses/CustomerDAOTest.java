package com.processes.DAOClasses;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.processes.BeanClasses.CustomerBean;

public class CustomerDAOTest {
	static int z,x;
	 
	 CustomerDAO cust=new CustomerDAO();
	@BeforeClass
public  static void setUp() throws Exception {
		 
		 
	}
	

	@AfterClass
	public static void tearDown() throws Exception {	
		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:xe", "verizon",
				"password");

		
		
		Statement st1= con.createStatement();
		
		int id=x;
		st1.executeQuery("delete from customer_table where customer_id="+id);
		
		
		
		
		
	}
	@Test
	public void testAdd() throws ParseException {
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
		cbeanexpectedd1.setEmailId("abcd123@gmail.com");
		cbeanexpectedd1.setBillStartDate(d2);
		cbeanexpectedd1.setBillingAddress("Chennai");
		cbeanexpectedd1.setCustomerStatus("active");
		cbeanexpectedd1.setConnectionAddress("Hyderabad");
		cbeanexpectedd1.setContactNumber("9566237055");
		cbeanexpectedd1.setLastName("aaj");
		z=cust.add(cbeanexpectedd1);
		//System.out.println(z);
		 
		assertTrue(cust.successflag==true);

	
	
}

	@Test
	public void testViewByEmail() throws ParseException {

		
		String pattern="dd-MMM-yyyy";
		SimpleDateFormat format=new SimpleDateFormat(pattern);
		
		java.util.Date dob=format.parse("17-Aug-2015");
		java.util.Date dob1=format.parse("17-Oct-1993");
		java.sql.Date d1=new java.sql.Date(dob1.getTime());
		java.sql.Date d2=new java.sql.Date(dob.getTime());
		
		
		
		CustomerBean cbeanexpected=new CustomerBean();
		cbeanexpected.setCustomerId(z);
		cbeanexpected.setLineOfBusiness("ves");
		cbeanexpected.setFirstName("danielvibind");
		cbeanexpected.setDateOfBirth(d1);
		cbeanexpected.setEmailId("abcd123@gmail.com");
		cbeanexpected.setBillStartDate(d2);
		cbeanexpected.setBillingAddress("Chennai");
		cbeanexpected.setCustomerStatus("active");
		cbeanexpected.setConnectionAddress("Hyderabad");
		cbeanexpected.setContactNumber("9566237055");
		cbeanexpected.setLastName("aaj");
		String str="abcd123@gmail.com";
		
		CustomerBean ResultBean=cust.viewByEmail(str);
		assertEquals(cbeanexpected.getFirstName(),ResultBean.getFirstName());
		assertEquals(cbeanexpected.getCustomerId(),ResultBean.getCustomerId());
		assertEquals(cbeanexpected.getLineOfBusiness(),ResultBean.getLineOfBusiness());
		assertEquals(cbeanexpected.getDateOfBirth(), ResultBean.getDateOfBirth());
		assertEquals(cbeanexpected.getBillStartDate(), ResultBean.getBillStartDate());
		assertEquals(cbeanexpected.getBillingAddress(), ResultBean.getBillingAddress());
		assertEquals(cbeanexpected.getCustomerStatus(), ResultBean.getCustomerStatus());
		assertEquals(cbeanexpected.getConnectionAddress(), ResultBean.getConnectionAddress());
		assertEquals(cbeanexpected.getContactNumber(), ResultBean.getContactNumber());
		assertEquals(cbeanexpected.getLastName(), ResultBean.getLastName());
	}

	@Test
	public void testView() throws ParseException {
		
		String pattern="dd-MMM-yyyy";
		SimpleDateFormat format=new SimpleDateFormat(pattern);
		
		java.util.Date dob=format.parse("17-Aug-2015");
		java.util.Date dob1=format.parse("17-Oct-1993");
		java.sql.Date d1=new java.sql.Date(dob1.getTime());
		java.sql.Date d2=new java.sql.Date(dob.getTime());
		
		
		
		CustomerBean cbeanexpectedd=new CustomerBean();
		cbeanexpectedd.setCustomerId(z);
		cbeanexpectedd.setLineOfBusiness("ves");
		cbeanexpectedd.setFirstName("danielvibind");
		cbeanexpectedd.setDateOfBirth(d1);
		cbeanexpectedd.setEmailId("dannad123@gmail.com");
		cbeanexpectedd.setBillStartDate(d2);
		cbeanexpectedd.setBillingAddress("Chennai");
		cbeanexpectedd.setCustomerStatus("active");
		cbeanexpectedd.setConnectionAddress("Hyderabad");
		cbeanexpectedd.setContactNumber("9566237055");
		cbeanexpectedd.setLastName("aaj");
	
		CustomerBean ResultBean1=cust.view(z);
		assertEquals(cbeanexpectedd.getFirstName(),ResultBean1.getFirstName());
		
		assertEquals(cbeanexpectedd.getCustomerId(),ResultBean1.getCustomerId());

		assertEquals(cbeanexpectedd.getLineOfBusiness(),ResultBean1.getLineOfBusiness());
		assertEquals(cbeanexpectedd.getDateOfBirth(), ResultBean1.getDateOfBirth());
		assertEquals(cbeanexpectedd.getBillStartDate(), ResultBean1.getBillStartDate());
		assertEquals(cbeanexpectedd.getBillingAddress(), ResultBean1.getBillingAddress());
		assertEquals(cbeanexpectedd.getCustomerStatus(), ResultBean1.getCustomerStatus());
		assertEquals(cbeanexpectedd.getConnectionAddress(), ResultBean1.getConnectionAddress());
		assertEquals(cbeanexpectedd.getContactNumber(), ResultBean1.getContactNumber());
	assertEquals(cbeanexpectedd.getLastName(), ResultBean1.getLastName());
	}
	
	@Test
	public void testBilling() throws ParseException {
		String pattern="dd-MMM-yyyy";
		SimpleDateFormat format=new SimpleDateFormat(pattern);
		java.util.Date dob=format.parse("17-Aug-2015");
		java.sql.Date d2=new java.sql.Date(dob.getTime());
		cust.billing(d2,"ves");
		assertTrue(cust.successflag1==true);
		
	}
	
	

@Test
	public void testUpdateStringStringInt() throws ParseException {
	
	
  int a=cust.update("last_name","noname",z);
 assertEquals(1,a);

	 
	
	}

	@Test
	public void testUpdateStringIntInt() throws ParseException {
		x=8888888;
		int b=cust.update("customer_id",x,z);
		assertEquals(1,b);
	}

	@Test
	public void testUpdateStringDateInt() throws ParseException {
		
		
		String pattern1="dd-MMM-yyyy";
		SimpleDateFormat format1=new SimpleDateFormat(pattern1);
		java.util.Date dob12=format1.parse("15-Dec-2015");
		java.sql.Date d21=new java.sql.Date(dob12.getTime());
		int c=cust.update("Bill_start_date",d21,z);
		assertEquals(1,c);
	}

}
