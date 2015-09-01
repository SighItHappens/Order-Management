package com.processes.DAOClasses;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import com.customers.DAOFactory;
import com.processes.BeanClasses.Bean;
import com.processes.BeanClasses.MDNBean;

public class MdnDAO implements DAOFactory{
	boolean successflag;
	@Override
	public int update(String name, String value, int id) {
		return 0;
	}

	@Override
	public int update(String name, int value, int id) {
		return 0;
	}

	@Override
	public int update(String name, Date value, int id) {
		return 0;
	}

	@Override
	public Bean view(int id) {
		MDNBean mdnbean=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			CallableStatement stmt=null;
			Connection con;
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "verizon",
					"password");
			stmt = con
					.prepareCall("{? = call verizon.package_mdn.create_mdn()}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.execute();
			mdnbean=new MDNBean();
			mdnbean.setCurrentMdn(stmt.getInt(1));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			successflag=false;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			successflag=false;
		}
		successflag=true;
		return mdnbean;
	}

	@Override
	public int add(Bean record) {
		return 0;
	}
 
}
