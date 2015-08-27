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

import com.processes.MappingDAOInf;
import com.processes.BeanClasses.Bean;
import com.processes.BeanClasses.MappingBean;

public class MappingDAO implements MappingDAOInf {

	public MappingBean view(int id) {
		MappingBean mbean = new MappingBean();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:orcl", "verizon",
					"password");
			Statement st = con.createStatement();
			ResultSet rs = st
					.executeQuery("select * from service_product where service_id="
							+ id);
			while (rs.next()) {
				mbean.setServiceID(rs.getInt(1));
				mbean.setListOfProducts(rs.getString(2));
			}
			con.close();
			return mbean;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int add(Bean record) {
		try {
			CallableStatement stmt;
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:orcl", "verizon",
					"password");
			System.out.println("connected1");
			stmt = con
					.prepareCall("{? = call verizon.insert_values.serv_prod(?,?)}");
			System.out.println("connected2");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setInt(2, ((MappingBean) record).getServiceID());
			stmt.setString(3, ((MappingBean) record).getListOfProducts());

			System.out.println();
			System.out.println("conn3");
			System.out.println(stmt.execute());
			int output = stmt.getInt(1);
			System.out.println(output);

			// TODO Auto-generated method stub

			return output;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		// TODO Auto-generated method stub

	}

	@Override
	public int update(String name, String value, int id) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:orcl", "verizon",
					"password");

			PreparedStatement ps = con
					.prepareStatement("update sevice_product set " + name
							+ "=? where service_id=?");
			ps.setString(1, value);
			ps.setInt(2, id);
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

			PreparedStatement ps = con
					.prepareStatement("update service_product set " + name
							+ "=? where service_id=?");
			ps.setInt(1, value);
			ps.setInt(2, id);
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

			PreparedStatement ps = con
					.prepareStatement("update service_product set " + name
							+ "=? where service_id=?");
			ps.setDate(1, value);
			ps.setInt(2, id);
			int i = ps.executeUpdate();
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
