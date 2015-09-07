package com.workflow;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONException;
import org.json.JSONObject;

import com.customers.DAOFactory;
import com.customers.DAOLookup;
import com.processes.DAOClasses.RequestResponseDAO;

public class ProvisioningCompleted {
	Persisting persist;

	public String provisioned(JSONObject output) {
		persist = new Persisting();
		int custid;
		System.out.println("in provisioned "+output);
		try {
			custid = output.getInt("customerid");
			persist.persistServices(custid, output.toString());
			persist.persistProducts(custid, output.toString());
			DAOLookup.setcInfo("request");
			DAOFactory df1 = DAOLookup.getDAOObject();
			int requestid = ((RequestResponseDAO) df1).viewByOrderAndSync(
					output.getInt("orderid"), "prov");
			df1.update("status", "complete", requestid);
			DAOLookup.setcInfo("customer");
			DAOFactory df2 = DAOLookup.getDAOObject();
			df2.update("bill_start_date",output.getString("provisioningdate") , custid);
		} catch (JSONException e) {
			e.printStackTrace();
			return "false";
		}
		return "true";
	}
}
