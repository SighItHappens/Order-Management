package com.customers;

import com.processes.BeanClasses.Bean;
import java.sql.Date;

public interface DAOFactory {
//	public boolean add(Bean addRecord,JSONObject obj);
	public int update(String name,String value,int id);
	public int update(String name,int value,int id);
	public int update(String name,Date value,int id);
	public Bean view(int id);
	public int add(Bean record);
}
