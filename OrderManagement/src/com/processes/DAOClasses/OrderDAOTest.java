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


public class OrderDAOTest {
	static int y;
	static int z,x;
	
	OrderDAO order=new OrderDAO();
	OrderBean obean=new OrderBean();
	OrderBean obean1=new OrderBean();
	
	
	String pattern="dd-MMM-yyyy";
	
	

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
		cbeanexpectedd1.setEmailId("sr@gmail.com");
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
	
	Statement st1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
			ResultSet.CONCUR_UPDATABLE);
		st.executeQuery("delete from order_table where order_id=" +y);
		st1.executeQuery("delete from customer_table where customer_id=" +z);
		
		}
	@Test
	public void testAdd() throws ParseException {
		
		String pattern="dd-MMM-yyyy";
		SimpleDateFormat format=new SimpleDateFormat(pattern);
		
		java.util.Date dob=format.parse("27-Aug-2015");
		java.util.Date dob1=format.parse("01-Sep-2015");
		java.sql.Date d1=new java.sql.Date(dob1.getTime());
		java.sql.Date d2=new java.sql.Date(dob.getTime());
		
		
		
		obean.setCustomerID(z);
		obean.setDateOfBooking(d2);
		obean.setDueDate(d1);
		obean.setOrderStatus("new");
		obean.setQuantity(1);
		obean.setListOfServices("Data");
		obean.setListOfProducts("Modem");
		y=order.add(obean);
		assertTrue(order.successflag==true);
		
		
	}


	@Test
	public void testView() throws ParseException {
		
		int id=y;
		java.util.Date dob;
		java.util.Date dob1;
		SimpleDateFormat format=new SimpleDateFormat(pattern);
		dob=format.parse("27-Aug-2015");
		dob1=format.parse("01-Sep-2015");
		java.sql.Date d1=new java.sql.Date(dob1.getTime());
		java.sql.Date d2=new java.sql.Date(dob.getTime());
		obean.setOrderID(id);
		obean.setCustomerID(z);
		obean.setDateOfBooking(d2);
		obean.setDueDate(d1);
		obean.setOrderStatus("new");
		obean.setQuantity(1);
		obean.setListOfServices("Data");
		obean.setListOfProducts("Modem");
		
		
		OrderBean oResultBean=order.view(id);
		assertEquals(obean.getOrderID(), oResultBean.getOrderID());
		assertEquals(obean.getCustomerID(), oResultBean.getCustomerID());
		assertEquals(obean.getDateOfBooking(), oResultBean.getDateOfBooking());
		assertEquals(obean.getDueDate(), oResultBean.getDueDate());
		assertEquals(obean.getOrderStatus(), oResultBean.getOrderStatus());
		assertEquals(obean.getQuantity(), oResultBean.getQuantity());
		assertEquals(obean.getListOfServices(), oResultBean.getListOfServices());
		assertEquals(obean.getListOfProducts(), oResultBean.getListOfProducts());
	}

	@Test
	public void testViewByCustomerID() throws ParseException {
	
		order.viewByCustomerID(z);
		assertTrue(order.successflag == true);
 
}

	

	@Test
	public void testUpdateStringStringInt() {
		int a=order.update("List_of_services","Audio",y);
		assertEquals(1,a);
		}

	@Test
	public void testUpdateStringIntInt() {
		int a=order.update("Quantity",2,y);
		assertEquals(1,a);
		
	}

	@Test
	public void testUpdateStringDateInt() throws ParseException {
		java.util.Date dob;
		SimpleDateFormat format=new SimpleDateFormat(pattern);
		dob=format.parse("05-Sep-2015");
		java.sql.Date d3=new java.sql.Date(dob.getTime());
		int a=order.update("Due_Date",d3,y);
		assertEquals(1,a);
		
	}

}
