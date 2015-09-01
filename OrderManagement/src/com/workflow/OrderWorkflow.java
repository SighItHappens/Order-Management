package com.workflow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.customers.DAOFactory;
import com.customers.DAOLookup;
import com.processes.BeanClasses.MDNBean;
import com.processes.BeanClasses.MappingBean;
import com.processes.BeanClasses.VESBean;
import com.processes.DAOClasses.MappingDAO;

public class OrderWorkflow extends Thread {
	JSONObject provisioning;
	int custid, orderid, contractid;
	String equiplist = null;
	Persisting persist;
	public JSONObject response;
	String arr1[] = { "1gb per 2c", "2gb per 2c", "3gb per 2c", "3 gb per3c",
			"4 gb per2c", "4 gb per3c", "6 gb per3c", "6 gb per 4c",
			"1gb per 30$", "3gb per45$", "6gbper60$", "iphone 5_1gb_30$",
			"iphone6_3gb_45$", "iphone6_6gb_60$", "samsunggalaxy_s6_3gb/45$",
			"samsunggalaxy_s6_6gb/60$", "iphone6_12gb_80$", "iphone5_6gb-60$" };
	ArrayList<String> listformdn = new ArrayList<String>(Arrays.asList(arr1));
	public boolean flag;
	
	public OrderWorkflow(JSONObject order) {
		System.out.println(order);
		flag=true;
		persist = new Persisting();
		provisioning = order;
		response = new JSONObject();
		try {
			JSONArray equips = null;
			if (((JSONObject) provisioning.get("customerdetails"))
					.get("customertype").toString().equals("new")) {
				custid = persist.persistCustomer(provisioning);
				System.out.println("Customer id" + custid);
				equips = getList();
				if (provisioning.get("lineofbusiness").toString().equals("ves"))
					contractid = persist.persistContract(custid, provisioning);
				orderid = persist.persistOrder(custid, equips, provisioning);
				System.out.println(orderid+"inworkflow"+contractid);
			} else if (((JSONObject) provisioning.get("customerdetails"))
					.get("customertype").toString().equals("registered")
					&& provisioning.getJSONArray("contractdetails")
							.getJSONObject(0).getString("change")
							.equals("null")) {
				custid =provisioning.getInt("customerid");
				equips = getList();
				if (provisioning.get("lineofbusiness").toString().equals("ves"))
					contractid = persist.persistContract(custid, provisioning);
				orderid = persist.persistOrder(custid, equips, provisioning);
			} else if(((JSONObject) provisioning.get("customerdetails"))
					.get("customertype").toString().equals("registered")
					&& !(provisioning.getJSONArray("contractdetails")
							.getJSONObject(0).getString("change")
							.equals("null"))){
				DAOLookup.setcInfo("ves");
				DAOFactory df=DAOLookup.getDAOObject();
				VESBean vbean=(VESBean) df.view(provisioning.getJSONObject("contractdetails").getInt("contractid"));
				vbean.setCurrent(vbean.getCurrent()+Integer.parseInt(provisioning.getJSONObject("contractdetails").getString("change")));
			} else
				flag=false;
			if (provisioning.get("lineofbusiness").toString().equals("cmb")) {
				JSONArray arr = provisioning.getJSONObject("orderdetails")
						.getJSONArray("services");
				for (int i = 0; i < arr.length(); i++) {
					((JSONObject) arr.get(i)).put("quantity", 1);
				}
			} else if (provisioning.get("lineofbusiness").toString()
					.equals("ves")
					&& !(provisioning.getJSONArray("contractdetails")
							.getJSONObject(0).getString("change")
							.equals("null"))) {
				JSONArray arr = provisioning.getJSONObject("orderdetails")
						.getJSONArray("services");
				int quant = provisioning.getJSONArray("contractdetails")
						.getJSONObject(0).getInt("change");
				for (int i = 0; i < arr.length(); i++) {
					((JSONObject) arr.get(i)).put("quantity", quant);
				}
			} else if (provisioning.get("lineofbusiness").toString()
					.equals("ves")) {
				JSONArray arr = provisioning.getJSONObject("orderdetails")
						.getJSONArray("services");
				for (int i = 0; i < arr.length(); i++) {
					int quant = Integer.parseInt(arr.getJSONObject(i)
							.getJSONObject("quantity").get("current")
							.toString());
					arr.getJSONObject(i).put("quantity", quant);
				}
			} else {
				JSONArray arr = provisioning.getJSONObject("orderdetails")
						.getJSONArray("services");
				for (int i = 0; i < arr.length(); i++) {
					int quant = Integer.parseInt(arr.getJSONObject(i)
							.getJSONObject("quantity").get("current")
							.toString());
					arr.getJSONObject(i).put("quantity", quant);
				}
			}
			String fname = ((JSONObject) provisioning.get("customerdetails"))
					.getString("fname");
			String lname = ((JSONObject) provisioning.get("customerdetails"))
					.getString("lname");
			((JSONObject) provisioning.get("customerdetails")).remove("fname");
			((JSONObject) provisioning.get("customerdetails")).remove("lname");
			((JSONObject) provisioning.get("customerdetails")).put(
					"customername", fname + " " + lname);
			((JSONObject) provisioning.get("customerdetails")).put(
					"customerid", custid);
			((JSONObject) provisioning.get("orderdetails")).put("orderid",
					orderid);
			((JSONObject) provisioning.get("customerdetails"))
					.remove("billingaddress");
			((JSONObject) provisioning.get("customerdetails")).remove("email");
			((JSONObject) provisioning.get("customerdetails"))
					.remove("dateofbirth");
			provisioning.remove("contractdetails");
			((JSONObject) provisioning.get("orderdetails")).put("products",
					equips);
			JSONArray arr;
			arr = ((JSONObject) provisioning.get("orderdetails"))
					.getJSONArray("services");
			for (int i = 0; i < arr.length(); i++) {
				if (listformdn.contains(arr.getJSONObject(i).getString(
						"servicename").toLowerCase())) {
					arr.getJSONObject(i).put("mdn", new JSONArray(generatemdn(arr.getJSONObject(i).getString("servicename"))));
				} else
					arr.getJSONObject(i).put("mdn", "null");
			}
			response.put("customerid", custid);
			response.put("orderid", orderid);
			if(provisioning.getString("lineofbusiness").equals("ves"))
				response.put("contractid", contractid);
			this.start();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private ArrayList<Long> generatemdn(String servicename) {
		ArrayList<Long> mdn=new ArrayList<Long>();
		int quant=1;
		if(servicename.equals("1gb per 2c") || servicename.equals("2gb per 2c")||servicename.equals("3gb per 2c")||servicename.equals("4 gb per2c"))
			quant=2;
		else if(servicename.equals("3 gb per3c")||servicename.equals("4 gb per3c")||servicename.equals("6 gb per3c"))
			quant=3;
		else if(servicename.equals("6 gb per 4c"))
			quant=4;
		DAOLookup.setcInfo("mdn");
		DAOFactory df = DAOLookup.getDAOObject();
		MDNBean mbean;
		for(int i=0;i<quant;i++)
		{
			mbean= (MDNBean) df.view(0);
			mdn.add(mbean.getCurrentMdn());
		}
		return mdn;
	}

	private JSONArray getList() {
		DAOLookup.setcInfo("mapping");
		DAOFactory df = DAOLookup.getDAOObject();
		JSONArray arr;
		try {
			arr = ((JSONObject) provisioning.get("orderdetails"))
					.getJSONArray("services");
			JSONArray prods = new JSONArray();
			JSONArray temp;
			for (int i = 0; i < arr.length(); i++) {
				System.out.println(((JSONObject) arr
						.get(i)).getString("servicecode"));
				MappingBean mbean = (MappingBean)((MappingDAO) df).view1(((JSONObject) arr
						.get(i)).getString("servicecode").toLowerCase());
				String prodlist = mbean.getListOfProducts();
				temp = new JSONArray(prodlist);
				prods = concatenate(prods, temp);
			}
			return prods;
		} catch (JSONException e) {

			e.printStackTrace();
			return null;
		}
	}

	private JSONArray concatenate(JSONArray prods, JSONArray temp) {
		for (int i = 0; i < temp.length(); i++) {
			try {
				prods.put(temp.get(i));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return prods;
	}

	@Override
	public void run() {
		String output="";
		DAOLookup.setcInfo("order");
		DAOFactory df = DAOLookup.getDAOObject();
		df.update("order_status", "in_provision", orderid);
		int requestId = persist.persistRequest(this.currentThread().getId(),
				orderid, "ordering", "prov", "in prov");
		String urlStr = "http://192.168.1.65:7562/ProvisionPorts/rest/prov/acceptRequest";
		URL urlToRequest;
		try {
			urlToRequest = new URL(urlStr);
			HttpURLConnection httpConnection = (HttpURLConnection) urlToRequest
					.openConnection();
			httpConnection.setDoOutput(true);
			httpConnection.setRequestMethod("POST");
			httpConnection.setRequestProperty("Content-Type",
					MediaType.TEXT_PLAIN);
			OutputStream outputStream = httpConnection.getOutputStream();
			outputStream.write(provisioning.toString().getBytes());
			outputStream.flush();

			if (httpConnection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ httpConnection.getResponseCode());
			}
			BufferedReader responseBuffer = new BufferedReader(
					new InputStreamReader((httpConnection.getInputStream())));
			System.out.println("OUTPUT: ");
			while ((output = responseBuffer.readLine()) != null) {
				System.out.println(output);
			}
			httpConnection.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(output.equals("true"))
		{
			System.out.println("provisioning accepted");
			df.update("order_status", "out_provision", orderid);
		}
	}
}
