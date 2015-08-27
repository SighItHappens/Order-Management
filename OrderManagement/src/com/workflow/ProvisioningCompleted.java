package com.workflow;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONObject;

import com.customers.DAOFactory;
import com.customers.DAOLookup;

public class ProvisioningCompleted {
	Persisting persist;
protected void provisioned(JSONObject output)
{
	persist=new Persisting();
	persist.persistServices(custid,output);//update table
	persist.persistProducts(custid,output);//update table
	DAOLookup.setcInfo("request");
	DAOFactory df1=DAOLookup.getDAOObject();
	df1.update(requestId,"status","complete");
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
	Calendar cal=Calendar.getInstance();
	DAOLookup.setcInfo("customer");
	DAOFactory df2=DAOLookup.getDAOObject();
	df2.update(custid,"bill_start_date",formatter.format(cal));
}
}
