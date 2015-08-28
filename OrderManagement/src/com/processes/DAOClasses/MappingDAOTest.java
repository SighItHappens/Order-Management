package com.processes.DAOClasses;

import static org.junit.Assert.*;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import oracle.sql.CLOB;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.processes.BeanClasses.MappingBean;


public class MappingDAOTest {
	
	MappingDAO map=new MappingDAO();
	MappingBean mbean=new MappingBean();
	
	@BeforeClass
	public static void setUp() throws Exception {
		
	}

	@AfterClass
	public static void tearDown() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:orcl", "verizon",
				"password");
		Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
	st.executeQuery("delete from service_product where service_id='bb'");
	

	}
	@Test
	public void testAdd() {
		MappingBean mbean=new MappingBean();
		mbean.setServiceID("bb");
		mbean.setListOfProducts("router");
		int a=map.add(mbean);
		assertEquals(1,a);
		
		
	}

	@Test
	public void testView1() {
		String servid="bb";
	MappingBean mResultBean=map.view1(servid);
		assertTrue(map.successflag==true);
	
	}
	@Test
	public void testUpdateStringStringInt() {
		int a=map.update1("list_of_products","stabilizer","bb");
		assertTrue(map.successflag==true);
		
		
	}
	
	
	

}

