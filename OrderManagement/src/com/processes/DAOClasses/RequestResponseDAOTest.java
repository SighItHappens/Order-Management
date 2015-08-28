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
import com.processes.BeanClasses.OrderBean;
import com.processes.BeanClasses.RequestResponseBean;

public class RequestResponseDAOTest {
	 static int x, z,y;
	 
	RequestResponseDAO request=new RequestResponseDAO();
	@BeforeClass
	public static void setUp() throws Exception {
		OrderBean obean=new OrderBean();
		OrderDAO order=new OrderDAO();
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
		cbeanexpectedd1.setEmailId("rrr123@gmail.com");
		cbeanexpectedd1.setBillStartDate(d2);
		cbeanexpectedd1.setBillingAddress("Chennai");
		cbeanexpectedd1.setCustomerStatus("active");
		cbeanexpectedd1.setConnectionAddress("Hyderabad");
		cbeanexpectedd1.setContactNumber("9566237055");
		cbeanexpectedd1.setLastName("aaj");
		x=cust.add(cbeanexpectedd1);
		
		
		SimpleDateFormat format1=new SimpleDateFormat(pattern);
		
		java.util.Date dob3=format1.parse("27-Aug-2015");
		java.util.Date dob4=format1.parse("01-Sep-2015");
		java.sql.Date d3=new java.sql.Date(dob4.getTime());
		java.sql.Date d4=new java.sql.Date(dob3.getTime());
		
		
		
		obean.setCustomerID(x);
		obean.setDateOfBooking(d4);
		obean.setDueDate(d3);
		obean.setOrderStatus("new");
		obean.setQuantity(1);
		obean.setListOfServices("Data");
		obean.setListOfProducts("Modem");
		z=order.add(obean);
	}

	@AfterClass
	public static void tearDown() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:orcl", "verizon",
				"password");
		Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		st.executeQuery("delete from request_response_table where request_id="+y);
		Statement st1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		st1.executeQuery("delete from order_table where order_id="+667);
		Statement st3 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		st3.executeQuery("delete from customer_table where customer_id="+x);
		
		
	
		
	}
	@Test
	public void testAdd() {
		RequestResponseBean rbean=new RequestResponseBean();
		rbean.setOrderID(z);
		rbean.setSourc("dani");
		rbean.setStatus("active");
		rbean.setSync("yes");
		rbean.setThreadObject("noobject");
		y=request.add(rbean);
		
		assertTrue(request.successflag==true);
		
		

	}

	@Test
	public void testViewByOrderAndSync() {
		//RequestResponseBean rbean=new RequestResponseBean();

		//int check=request.viewByOrderAndSync(z,"yes");
		//assertEquals(y,check);
		request.viewByOrderAndSync(z,"yes");
		assertTrue(request.successflag==true);
		
		
	}

@Test
	public void testView() {
		int id=y;
		RequestResponseBean rbean=new RequestResponseBean();
		rbean=(RequestResponseBean) request.view(id);
		
		assertEquals(id,rbean.getRequestID());
	}


	@Test
	public void testUpdateStringStringInt() {
		
		int check=request.update("sync","no",y);
		assertEquals(1,check);
		
	}

@Test
	public void testUpdateStringIntInt() {
	int check=request.update("order_id",667,y);
	assertEquals(1,check);
	}

	
		
		

	

}
