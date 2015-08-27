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

import com.processes.OrderDAOInf;
import com.processes.BeanClasses.Bean;
import com.processes.BeanClasses.OrderBean;

public class OrderDAO implements OrderDAOInf {
	public OrderBean view(int id) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:orcl", "verizon",
					"password");

			OrderBean obean = new OrderBean();
			Statement st = con.createStatement();
			ResultSet rs = st
					.executeQuery("select * from order_table where order_id="
							+ id);

			while (rs.next()) {
				obean.setOrderID(rs.getInt(2));
				obean.setCustomerID(rs.getInt(1));
				obean.setDateOfBooking(rs.getDate(3));
				obean.setDueDate(rs.getDate(4));
				obean.setOrderStatus(rs.getString(5));
				obean.setQuantity(rs.getInt(6));
				obean.setListOfServices(rs.getString(7));
				obean.setListOfProducts(rs.getString(8));
			}
			con.close();
			return obean;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public OrderBean[] viewByCustomerID(int id) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:orcl", "verizon",
					"password");
			Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st
					.executeQuery("select * from order_table where customer_id="
							+ id);
			int i = 0;
			while (rs.next()) {
				i++;
			}
			rs.beforeFirst();
			OrderBean[] obean = new OrderBean[i];
			for (int j = 0; j < i; j++)
				obean[j] = new OrderBean();
			i = 0;
			while (rs.next()) {
				obean[i].setCustomerID(rs.getInt(1));
				obean[i].setOrderID(rs.getInt(2));
				obean[i].setDateOfBooking(rs.getDate(3));
				obean[i].setDueDate(rs.getDate(4));
				obean[i].setOrderStatus(rs.getString(5));
				obean[i].setQuantity(rs.getInt(6));
				obean[i].setListOfServices(rs.getString(7));
				obean[i].setListOfProducts(rs.getString(8));
				//System.out.println(obean[i]);
				i++;
			}
			con.close();

			return obean;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int add(Bean record) {
		try {
			CallableStatement stmt;
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:orcl", "verizon",
					"password");
			stmt = con
					.prepareCall("{? = call verizon.insert_values.generate_orderid(?,?,?,?,?,?,?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setInt(2, ((OrderBean) record).getCustomerID());
			stmt.setDate(3, ((OrderBean) record).getDateOfBooking());
			stmt.setDate(4, ((OrderBean) record).getDueDate());
			stmt.setString(5, ((OrderBean) record).getOrderStatus());
			stmt.setInt(6, ((OrderBean) record).getQuantity());
			stmt.setString(7, ((OrderBean) record).getListOfServices());
			stmt.setString(8, ((OrderBean) record).getListOfProducts());
			stmt.execute();
			int output = stmt.getInt(1);
			return output;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}
	
	
	@Override
	public int update(String name,String value,int id) {
		try {
			System.out.println("in order dao");
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:orcl", "verizon",
					"password");

			PreparedStatement ps = con.prepareStatement("update order_table set "+name+"=? where order_id=?");
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
					"jdbc:oracle:thin:@localhost:1521:orcl", "verizon",
					"password");

			PreparedStatement ps = con.prepareStatement("update order_table set "+name+"=? where order_id=?");
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
					"jdbc:oracle:thin:@localhost:1521:orcl", "verizon",
					"password");

			PreparedStatement ps = con.prepareStatement("update order_table set "+name+"=? where order_id=?");
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