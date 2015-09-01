package com.processes.DAOClasses;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import com.processes.CustomerDAOInf;
import com.processes.BeanClasses.Bean;
import com.processes.BeanClasses.CustomerBean;

public class CustomerDAO implements CustomerDAOInf {
	boolean successflag,successflag1;
	

	@Override
	public CustomerBean viewByEmail(String str) {
		CustomerBean cbean = new CustomerBean();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "verizon",
					"password");

			Statement st = con.createStatement();
			ResultSet rs = st
					.executeQuery("select * from customer_table where email_id='"
							+ str + "'");
			while (rs.next()) {
				cbean.setCustomerId(rs.getInt(1));
				cbean.setLineOfBusiness(rs.getString(2));
				cbean.setFirstName(rs.getString(3));
				cbean.setEmailId(rs.getString(4));
				cbean.setDateOfBirth(rs.getDate(5));
				cbean.setBillStartDate(rs.getDate(6));
				cbean.setBillingAddress(rs.getString(7));
				cbean.setCustomerStatus(rs.getString(8));
				cbean.setConnectionAddress(rs.getString(9));
				cbean.setContactNumber(rs.getString(10));
				cbean.setLastName(rs.getString(11));
			}
			con.close();
			return cbean;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public CustomerBean view(int id) {
		CustomerBean cbean = new CustomerBean();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "verizon",
					"password");

			Statement st = con.createStatement();
			ResultSet rs = st
					.executeQuery("select * from customer_table where customer_id="
							+ id);
			while (rs.next()) {
				cbean.setCustomerId(rs.getInt(1));
				cbean.setLineOfBusiness(rs.getString(2));
				cbean.setFirstName(rs.getString(3));
				cbean.setEmailId(rs.getString(4));
				cbean.setDateOfBirth(rs.getDate(5));
				cbean.setBillStartDate(rs.getDate(6));
				cbean.setBillingAddress(rs.getString(7));
				cbean.setCustomerStatus(rs.getString(8));
				cbean.setConnectionAddress(rs.getString(9));
				cbean.setContactNumber(rs.getString(10));
				cbean.setLastName(rs.getString(11));
			}
			con.close();
			return cbean;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public int add(Bean addRecord) {

		try {
			CallableStatement stmt;
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "verizon",
					"password");
			stmt = con
					.prepareCall("{? = call verizon.insert_values.generate_custid(?,?,?,?,?,?,?,?,?,?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, ((CustomerBean) addRecord).getLineOfBusiness());
			stmt.setString(3, ((CustomerBean) addRecord).getFirstName());
			stmt.setString(4, ((CustomerBean) addRecord).getLastName());
			stmt.setString(5, ((CustomerBean) addRecord).getEmailId());
			stmt.setDate(6, ((CustomerBean) addRecord).getDateOfBirth());
			stmt.setString(7, ((CustomerBean) addRecord).getBillingAddress());
			stmt.setDate(8, ((CustomerBean) addRecord).getBillStartDate());
			stmt.setString(9, ((CustomerBean) addRecord).getConnectionAddress());

			stmt.setString(10, ((CustomerBean) addRecord).getContactNumber());
			stmt.setString(11, ((CustomerBean) addRecord).getCustomerStatus());

			stmt.execute();
			int output = stmt.getInt(1);
			successflag=true;
			return output;
		} catch (Exception e) {
			e.printStackTrace();
			successflag=false;
			return 0;
		}
	}

	public String billing(Date billingDate, String Portfolio) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "verizon", "password");

			PreparedStatement st = con.prepareStatement("select customer_id from customer_table where bill_start_date=? and line_of_business='"
							+ Portfolio + "'");
			st.setDate(1, billingDate);
			ResultSet rs = st
					.executeQuery();

			String customerids = "";
			int i = 0;
			while (rs.next()) {
				System.out.println(rs.getString(1)+"AAA");
				customerids += rs.getString(1) + ",";

			}

			con.close();
			successflag1=true;
			return customerids;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			successflag1=false;
		} catch (SQLException e) {
			successflag1=false;
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public int update(String name,String value,int id) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "verizon",
					"password");

		PreparedStatement ps = con.prepareStatement("update customer_table set "+name+"=? where customer_id=?");
		ps.setString(1, value);
		ps.setInt(2,id);
		int i = ps.executeUpdate();
		return i;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}

	}

	@Override
	public int update(String name, int value, int id) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "verizon",
					"password");

			PreparedStatement ps = con.prepareStatement("update customer_table set "+name+"=? where customer_id=?");
			ps.setInt(1, value);
			ps.setInt(2,id);
		int i = ps.executeUpdate();
		return i;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int update(String name, Date value, int id) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "verizon",
					"password");

			PreparedStatement ps = con.prepareStatement("update customer_table set "+name+"=? where customer_id=?");
			ps.setDate(1, value);
			ps.setInt(2,id);
		int i = ps.executeUpdate();
		return i;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	


}
