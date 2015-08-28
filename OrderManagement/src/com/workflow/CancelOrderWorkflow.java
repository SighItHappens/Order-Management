package com.workflow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import com.customers.DAOFactory;
import com.customers.DAOLookup;

public class CancelOrderWorkflow extends Thread{
	int orderid;
	Persisting persist;
	public boolean flag;
	public CancelOrderWorkflow(int orderid){
		persist=new Persisting();
		flag=true;
		this.orderid=orderid;
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
		else{
			flag=false;
		}
		if(response.getString("provisioningstatus").equals("cancelled"))
		{
			DAOLookup.setcInfo("request");
			DAOFactory df=DAOLookup.getDAOObject();
			df.update("status","complete",requestid);
			DAOLookup.setcInfo("order");
			DAOFactory df2=DAOLookup.getDAOObject();
			df.update("order_status","cancelled",orderid);
		}
		} catch (IOException e) {
			e.printStackTrace();
			flag=false;
		} catch (JSONException e) {
			e.printStackTrace();
			flag=false;
		}
	}
}
