package com.workflow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import com.customers.DAOFactory;
import com.customers.DAOLookup;

public class ChangeDuedateWorkflow extends Thread{
	int orderid;
	String date;
	Persisting persist;
	public ChangeDuedateWorkflow(int orderid,String date){
		persist=new Persisting();
		this.orderid = orderid;
		this.date=date;
		this.start();	
	}
	@Override
	public void run()
	{
		int requestid=persist.persistRequest(this.currentThread().getId(),orderid, "selfservice", "prov", "in prov");
		String api = "http://localhost:8086/TestRestServ/rest/om/billingAccountPull/ves";//updatestring
		int responseCode = 0;	
		JSONObject response=null;
		URL url;
		try {
			url = new URL(api);
			HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.connect();
			responseCode = httpConnection.getResponseCode();
		if (responseCode == 200) {
			BufferedReader br = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
			String str = "";
			StringBuilder responseJson = new StringBuilder();
			while ((str = br.readLine()) != null) {
				responseJson.append(str);
			}
			response=new JSONObject(new String(responseJson));
		}
		if(response.getString("provisioningstatus").equals("changed"))
		{
			DAOLookup.setcInfo("request");
			DAOFactory df=DAOLookup.getDAOObject();
			df.update("status","complete",requestid);
			DAOLookup.setcInfo("order");
			DAOFactory df2=DAOLookup.getDAOObject();
			SimpleDateFormat format = new SimpleDateFormat("dd-MMM-YYYY");
	        Date newdate = null;
			try {
				newdate = format.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        java.sql.Date sqldate = new java.sql.Date(newdate.getTime());
			df.update("due_date",sqldate,orderid);
		}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
