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

import com.processes.VESDAOInf;
import com.processes.BeanClasses.Bean;
import com.processes.BeanClasses.VESBean;

public class VESDAO implements VESDAOInf {
	boolean successflag,successflag1,successflag2;

	@Override
	public VESBean displayByEmail(String str) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:orcl", "verizon", "password");

			VESBean vbean = new VESBean();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from ves where customer_id = (select customer_id from customer_table where email_id='"
					+ str + "')");
			while (rs.next()) {
				vbean.setContractID(rs.getInt(7));
				vbean.setModelType(rs.getString(1));
				vbean.setCustomerID(rs.getInt(2));
				vbean.setClassOfService(rs.getString(3));
				vbean.setFromDate(rs.getDate(4));
				vbean.setToDate(rs.getDate(5));
				vbean.setDiscountPercentage(rs.getInt(6));
				vbean.setChange(rs.getInt(8));
				vbean.setCurrent(rs.getInt(9));
				vbean.setMax(rs.getInt(10));
			}
			con.close();
			successflag2=true;
			return vbean;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			successflag2=false;
			return null;
		}
	}
	public VESBean[] viewByCustomerID(int id) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:orcl", "verizon", "password");

			Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st
					.executeQuery("select * from ves where customer_id="
							+ id);
			int i = 0;
			while (rs.next()) {
				i++;
			}
			rs.beforeFirst();
			VESBean[] vbean = new VESBean[i];
			for (int j = 0; j < i; j++)
				vbean[j] = new VESBean();
			i = 0;
			while (rs.next()) {
				vbean[i].setContractID(rs.getInt(7));
				vbean[i].setModelType(rs.getString(1));
				vbean[i].setCustomerID(rs.getInt(2));
				vbean[i].setClassOfService(rs.getString(3));
				vbean[i].setFromDate(rs.getDate(4));
				vbean[i].setToDate(rs.getDate(5));
				vbean[i].setDiscountPercentage(rs.getInt(6));
				vbean[i].setChange(rs.getInt(8));
				vbean[i].setCurrent(rs.getInt(9));
				vbean[i].setMax(rs.getInt(10));
				i++;
			}
			con.close();
			successflag1=true;
			return vbean;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			successflag1=false;
			return null;
		}
	}
	
	@Override
	public VESBean view(int id) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:orcl", "verizon", "password");

			VESBean vbean = new VESBean();
			Statement st = con.createStatement();
			ResultSet rs = st
					.executeQuery("select * from ves where contract_id=" + id);
			while (rs.next()) {
				vbean.setContractID(rs.getInt(7));
				vbean.setModelType(rs.getString(1));
				vbean.setCustomerID(rs.getInt(2));
				vbean.setClassOfService(rs.getString(3));
				vbean.setFromDate(rs.getDate(4));
				vbean.setToDate(rs.getDate(5));
				vbean.setDiscountPercentage(rs.getInt(6));
				vbean.setChange(rs.getInt(8));
				vbean.setCurrent(rs.getInt(9));
				vbean.setMax(rs.getInt(10));

			}

			con.close();
			
			return vbean;
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
					"jdbc:oracle:thin:@localhost:1521:orcl", "verizon", "password");
			stmt = con
					.prepareCall("{? = call insert_values.generate_contractid(?,?,?,?,?,?,?,?,?)}");
			stmt.registerOutParameter(1, Types.INTEGER);

			stmt.setString(2, ((VESBean) record).getModelType());
			stmt.setInt(3, ((VESBean) record).getCustomerID());
			stmt.setString(4, ((VESBean) record).getClassOfService());
			stmt.setDate(5, ((VESBean) record).getFromDate());
			stmt.setDate(6, ((VESBean) record).getToDate());
			stmt.setInt(7, ((VESBean) record).getDiscountPercentage());
			stmt.setInt(8, ((VESBean) record).getChange());
			stmt.setInt(9, ((VESBean)record).getCurrent());
			stmt.setInt(10, ((VESBean) record).getMax());
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
	@Override
	public int update(String name,String value,int id) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:orcl", "verizon",
					"password");

			PreparedStatement ps = con.prepareStatement("update ves set "+name+"=? where contract_id=?");
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

			PreparedStatement ps = con.prepareStatement("update ves set "+name+"=? where contract_id=?");
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

			PreparedStatement ps = con.prepareStatement("update ves set "+name+"=? where contract_id=?");
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
