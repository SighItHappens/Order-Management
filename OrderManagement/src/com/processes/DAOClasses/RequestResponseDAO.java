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

import com.processes.RequestResponseDAOInf;
import com.processes.BeanClasses.Bean;
import com.processes.BeanClasses.RequestResponseBean;

public class RequestResponseDAO implements RequestResponseDAOInf {

	public int viewByOrderAndSync(int orderID,String sync)
	{
		int responseID = -1;
		try {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:orcl", "verizon",
				"password");
		Statement st = con.createStatement();
		ResultSet rs = st
				.executeQuery("select request_id from request_response_table where order_id="+ orderID+"and sync='"+sync+"'");

		while (rs.next()) {
			
			responseID = rs.getInt(1);
		}
		con.close();
		return responseID;
	} catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
		return responseID;
	}

		
	}
	
	@Override
	public Bean view(int id) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:orcl", "verizon",
					"password");

			RequestResponseBean rbean = new RequestResponseBean();
			Statement st = con.createStatement();
			ResultSet rs = st
					.executeQuery("select * from request_response_table where request_id="
							+ id);

			while (rs.next()) {

				rbean.setRequestID(rs.getInt(1));
				rbean.setSourc(rs.getString(2));
				rbean.setSync(rs.getString(3));
				rbean.setThreadObject(rs.getString(4));
				rbean.setStatus(rs.getString(5));
				rbean.setOrderID(rs.getInt(6));

			}
			con.close();
			return rbean;
		} catch (ClassNotFoundException | SQLException e) {
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

			System.out.println(((RequestResponseBean) record).getSourc());
			System.out.println(((RequestResponseBean) record).getSync());
			System.out.println(((RequestResponseBean) record).getThreadObject());
			System.out.println(((RequestResponseBean) record).getStatus());
			System.out.println(((RequestResponseBean) record).getOrderID());
			stmt = con
					.prepareCall("{? = call verizon.insert_values.generate_requestid(?,?,?,?,?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, ((RequestResponseBean) record).getSourc());
			stmt.setString(3, ((RequestResponseBean) record).getSync());
			stmt.setString(4, ((RequestResponseBean) record).getThreadObject());
			stmt.setString(5, ((RequestResponseBean) record).getStatus());
			stmt.setInt(6, ((RequestResponseBean) record).getOrderID());
			
			stmt.execute();
			int output = stmt.getInt(1);

			return output;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}

	@Override
	public int update(String name, String value, int id) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:orcl", "verizon",
					"password");

			PreparedStatement ps = con.prepareStatement("update request_response_table set "+name+"=? where request_id=?");
			ps.setString(1, value);
			ps.setInt(2,id);
			int i = ps.executeUpdate();
			return i;
		} catch (Exception e) {
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

			PreparedStatement ps = con.prepareStatement("update request_response_table set "+name+"=? where request_id=?");
			ps.setInt(1, value);
			ps.setInt(2,id);
			int i = ps.executeUpdate();
			return i;
		} catch (Exception e) {
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

			PreparedStatement ps = con.prepareStatement("update request_response_table set "+name+"=? where request_id=?");
			ps.setDate(1, value);
			ps.setInt(2,id);
			int i = ps.executeUpdate();
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}