package com.workflow;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.customers.DAOFactory;
import com.customers.DAOLookup;
import com.processes.BeanClasses.MappingBean;

public class OrderWorkflow extends Thread{
	JSONObject provisioning;
	int custid=1234666,orderid=2345678,contractid=13425;
	String equiplist=null;
	Persisting persist;
	String arr1[]={"1gb per 2c","2gb per 2c","3gb per 2c","3 gb per3c","4 gb per2c","4 gb per3c","6 gb per3c","6 gb per 4c","1gb per 30$","3gb per45$","6gbper60$","iphone 5_1gb_30$","iphone6_3gb_45$","iphone6_6gb_60$","samsunggalaxy_s6_3gb/45$","samsunggalaxy_s6_6gb/60$","iphone6_12gb_80$","iphone5_6gb-60$"};
	ArrayList<String> listformdn=new ArrayList<String>(Arrays.asList(arr1));
	public OrderWorkflow(JSONObject order) {
		persist=new Persisting();
		provisioning=order;
		//System.out.println(order);
		String equiplist="[\"123\",\"124\"]";
		try {
			//provisioning=new JSONObject(json);
			JSONArray equips=new JSONArray(equiplist);
			if(((JSONObject)provisioning.get("customerdetails")).get("customertype").toString().equals("new"))
			{
				custid=persist.persistCustomer(provisioning);
				System.out.println("Customer id"+custid);
				//equips=getList();
				if(provisioning.get("lineofbusiness").toString().equals("ves"))
					contractid=persist.persistContract(custid,provisioning);
				orderid=persist.persistOrder(custid,equips,provisioning);
			}
			else if(((JSONObject)provisioning.get("customerdetails")).get("customertype").toString().equals("registered") && provisioning.getJSONArray("contractdetails").getJSONObject(0).getString("change").equals("null"))
			{
				System.out.println("in customer");
				custid=persist.persistCustomer(provisioning);
				//equips=getList();
				if(provisioning.get("lineofbusiness").toString().equals("ves"))
					contractid=persist.persistContract(custid,provisioning);
				orderid=persist.persistOrder(custid,equips,provisioning);
			}
			if(provisioning.get("lineofbusiness").toString().equals("cmb"))
			{
				JSONArray arr=provisioning.getJSONObject("orderdetails").getJSONArray("services");
				for(int i=0;i<arr.length();i++)
				{
					((JSONObject)arr.get(i)).put("quantity",1);
				}
			}
			else if(provisioning.get("lineofbusiness").toString().equals("ves") && !(provisioning.getJSONArray("contractdetails").getJSONObject(0).getString("change").equals("null")))
			{
				JSONArray arr=provisioning.getJSONObject("orderdetails").getJSONArray("services");
				int quant=provisioning.getJSONArray("contractdetails").getJSONObject(0).getInt("change");
				for(int i=0;i<arr.length();i++)
				{
					((JSONObject)arr.get(i)).put("quantity",quant);
				}
			}
			else if(provisioning.get("lineofbusiness").toString().equals("ves"))
			{
				JSONArray arr=provisioning.getJSONObject("orderdetails").getJSONArray("services");
				for(int i=0;i<arr.length();i++)
				{
				int quant=Integer.parseInt(arr.getJSONObject(i).getJSONObject("quantity").get("current").toString());
				arr.getJSONObject(i).put("quantity",quant);
				}
			}
			else 
			{
				JSONArray arr=provisioning.getJSONObject("orderdetails").getJSONArray("services");
				for(int i=0;i<arr.length();i++)
				{
				int quant=Integer.parseInt(arr.getJSONObject(i).getJSONObject("quantity").get("current").toString());
				arr.getJSONObject(i).put("quantity",quant);
				}
			}
			String fname=((JSONObject)provisioning.get("customerdetails")).getString("fname");
			String lname=((JSONObject)provisioning.get("customerdetails")).getString("lname");
			((JSONObject)provisioning.get("customerdetails")).remove("fname");
			((JSONObject)provisioning.get("customerdetails")).remove("lname");
			((JSONObject)provisioning.get("customerdetails")).put("customername",fname+" "+lname);
			((JSONObject)provisioning.get("customerdetails")).put("customerid",custid);
			((JSONObject)provisioning.get("orderdetails")).put("orderid",orderid);
			((JSONObject)provisioning.get("customerdetails")).remove("billingaddress");
			((JSONObject)provisioning.get("customerdetails")).remove("email");
			((JSONObject)provisioning.get("customerdetails")).remove("dateofbirth");
			provisioning.remove("contractdetails");
			((JSONObject)provisioning.get("orderdetails")).put("products", equips);
			System.out.println(provisioning);
			DAOLookup.setcInfo("mapping");
			DAOFactory df=DAOLookup.getDAOObject();
			JSONArray arr;
				arr = ((JSONObject)provisioning.get("orderdetails")).getJSONArray("services");
			JSONArray prods=new JSONArray();
			JSONArray temp;
			for(int i=0;i<arr.length();i++)
			{
				if(listformdn.contains(arr.getJSONObject(i).getString("servicecode"))){
					arr.getJSONObject(i).put("mdn", generatemdn());
				}
				else
					arr.getJSONObject(i).put("mdn", "null");
			}
			this.start();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	private int generatemdn()
	{
		
		return 0;
	}
	private JSONArray getList(){
		DAOLookup.setcInfo("mapping");
		DAOFactory df=DAOLookup.getDAOObject();
		JSONArray arr;
		try {
			arr = ((JSONObject)provisioning.get("orderdetails")).getJSONArray("services");
		JSONArray prods=new JSONArray();
		JSONArray temp;
		for(int i=0;i<arr.length();i++)
		{
			MappingBean mbean=(MappingBean) df.view(((JSONObject)arr.get(i)).getInt("servicecode"));
			String prodlist=mbean.getListOfProducts();
			temp=new JSONArray(prodlist);
			prods=concatenate(prods,temp);
		}
		return prods;
		} catch (JSONException e) {
			
			e.printStackTrace();
			return null;
		}
	}
	private JSONArray concatenate(JSONArray prods,JSONArray temp){
		for(int i=0;i<temp.length();i++){
			try {
				prods.put(temp.get(i));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return prods;
	}
	
	@Override
	public void run()
	{
		String output;
		DAOLookup.setcInfo("order");
		DAOFactory df=DAOLookup.getDAOObject();
		df.update("order_status","in_provision",orderid);
		int requestId=persist.persistRequest(this.currentThread().getId(),orderid,"ordering","prov","in prov");
		String urlStr = "http://localhost:8080/TestRestServ/rest/om/placeOrder";
		URL urlToRequest;
		try {
			urlToRequest = new URL(urlStr);
		HttpURLConnection httpConnection = (HttpURLConnection) urlToRequest.openConnection();
		httpConnection.setDoOutput(true);
		httpConnection.setRequestMethod("POST");
		httpConnection.setRequestProperty("Content-Type",
				"application/json");
		OutputStream outputStream = httpConnection.getOutputStream();
		outputStream.write(provisioning.toString().getBytes());
		outputStream.flush();

		if (httpConnection.getResponseCode() != 201) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ httpConnection.getResponseCode());
		}
		df.update("order_status","out_provision",orderid);
		BufferedReader responseBuffer = new BufferedReader(
				new InputStreamReader((httpConnection.getInputStream())));
		
		while ((output = responseBuffer.readLine()) != null) {
			System.out.println(output);
		}
		httpConnection.disconnect();
		} catch ( IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[])
	{
		OrderWorkflow od=new OrderWorkflow(null);
	}
}
