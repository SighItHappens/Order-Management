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
import com.processes.BeanClasses.ProductBean;

public class ProductDAOTest {
	ProductDAO product=new ProductDAO();;
	ProductBean pbean1=new ProductBean();
	static int z;

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
		cbeanexpectedd1.setEmailId("rgguru123@gmail.com");
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
		Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		int id=z;
		st.executeQuery("delete from products  where list_of_products= 'modem'");
		Statement st1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		st1.executeQuery("delete from customer_table where customer_id=" +z);
		}
	@Test
	public void testAdd() {
		ProductBean pbean1=new ProductBean();
		pbean1.setCustomerID(z);
		pbean1.setListOfProducts("router");
		int a=product.add(pbean1);
		assertEquals(1,a);
	}
	@Test
	public void testView() {
		ProductBean pbean = new ProductBean();
		int id=z;

		pbean=product.view(id);
	assertEquals(id,pbean.getCustomerID());
	
	}


	@Test
	public void testUpdateStringStringInt() {
		int a=product.update("List_of_products","modem",z);
		assertEquals(1,a);
		}

}
