package com.customers;

import com.processes.BeanClasses.*;
import com.processes.DAOClasses.*;

public class DAOLookup {
	
	static String cInfo;
	
	
	public static String getcInfo() {
		return cInfo;
	}

	public static void setcInfo(String cInfo) {
		DAOLookup.cInfo = cInfo;
	}
	

	public static DAOFactory getDAOObject() {
		DAOFactory returnObject;
		if(cInfo.equals("customer"))
		{
			returnObject = new CustomerDAO();
		}
		else if(cInfo.equals("ves"))
		{
			returnObject = new VESDAO();
		}
		else if(cInfo.equals("mapping"))
		{
			returnObject = new MappingDAO();
		}
		else if(cInfo.equals("order"))
		{
			returnObject = new OrderDAO();
		}
		else if(cInfo.equals("product"))
		{
			returnObject = new ProductDAO();
		}
		else if(cInfo.equals("request"))
		{
			returnObject = new RequestResponseDAO();
		}
		else if(cInfo.equals("service"))
		{
			returnObject = new ServiceDAO();
		}
	 else {
			returnObject = null;
		}
		return returnObject;
	}
	
	public static Bean getBeanObject() {
		Bean returnObject;
		if(cInfo.equals("customer"))
		{
			returnObject = new CustomerBean();
		}
		else if(cInfo.equals("ves"))
		{
			returnObject = new VESBean();
		}
		else if(cInfo.equals("mapping"))
		{
			returnObject = new MappingBean();
		}
		else if(cInfo.equals("order"))
		{
			returnObject = new OrderBean();
		}
		else if(cInfo.equals("product"))
		{
			returnObject = new ProductBean();
		}
		else if(cInfo.equals("request"))
		{
			returnObject = new RequestResponseBean();
		}
		else if(cInfo.equals("service"))
		{
			returnObject = new ServiceBean();
		}
		else
		{
			returnObject = null;
		}
		return returnObject;
	}
}
