package com.workflow;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.customers.DAOFactory;
import com.customers.DAOLookup;
import com.processes.BeanClasses.CustomerBean;
import com.processes.BeanClasses.OrderBean;
import com.processes.BeanClasses.ProductBean;
import com.processes.BeanClasses.RequestResponseBean;
import com.processes.BeanClasses.ServiceBean;
import com.processes.BeanClasses.VESBean;

public class Persisting {
	protected int persistCustomer(JSONObject provisioning)
	{
		System.out.println("inside persist customer "+provisioning);
		CustomerBean cbean = new CustomerBean();
		try {
			System.out.println("in pers");
			cbean.setLineOfBusiness(provisioning.get("lineofbusiness").toString());

			//cbean.setCustomerType("new");
			cbean.setFirstName((String) ((JSONObject) provisioning.get("customerdetails"))
					.get("fname"));
			cbean.setLastName((String) ((JSONObject) provisioning.get("customerdetails"))
					.get("lname"));
			cbean.setConnectionAddress(((JSONObject) provisioning.get("customerdetails"))
					.get("connectionaddress").toString());
			cbean.setBillingAddress(((JSONObject) provisioning.get("customerdetails"))
					.get("billingaddress").toString());
			cbean.setEmailId((String) ((JSONObject) provisioning.get("customerdetails"))
					.get("email"));
			cbean.setContactNumber(((JSONObject) provisioning.get("customerdetails")).get("contactnumber").toString());

			if(!cbean.getLineOfBusiness().equals("ves"))
			{
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
			java.util.Date utilDate = formatter
					.parse((String) ((JSONObject) provisioning.get("customerdetails"))
							.get("dateofbirth"));

			Date date = new java.sql.Date(utilDate.getTime());
			cbean.setDateOfBirth(date);
			}
			else
				cbean.setDateOfBirth(null);
			cbean.setBillStartDate(null);
			cbean.setCustomerStatus("active");
		System.out.println(provisioning.get("lineofbusiness").toString()+"Customer added");
		} catch (JSONException | ParseException e) {
			e.printStackTrace();
		}
		DAOLookup.setcInfo("customer");
		DAOFactory df=DAOLookup.getDAOObject();
		return df.add(cbean);
	}
	private JSONArray concatenate(JSONArray temp1,JSONArray temp2){
		for(int i=0;i<temp2.length();i++){
			try {
				temp1.put(temp2.get(i));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return temp1;
	}
	protected int persistOrder(int customerid, JSONArray equips, JSONObject provisioning)
	{
		OrderBean obean=new OrderBean();
		try {
			obean.setCustomerID( ((JSONObject)provisioning.get("customerdetails")).getInt("customerid") );
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
			java.util.Date utilDate = formatter.parse((String) ((JSONObject) provisioning.get("orderdetails")).get("dateofbooking"));

			Date date = new java.sql.Date(utilDate.getTime());
			obean.setDateOfBooking(date);
			utilDate = formatter.parse((String)((JSONObject) provisioning.get("orderdetails")).get("duedate"));

			date = new java.sql.Date(utilDate.getTime());
			obean.setOrderStatus("new");
			obean.setDueDate(date);
			obean.setListOfProducts(equips.toString());
			obean.setCustomerID(customerid);
			obean.setListOfServices(provisioning.getJSONObject("orderdetails").getJSONArray("services").toString());
			
			//obean.setListOfServices(((JSONObject)provisioning.get("orderdetails")).get("services").toString());
			
		} catch (JSONException | ParseException e) {
			e.printStackTrace();
		}
		DAOLookup.setcInfo("order");
		DAOFactory df=DAOLookup.getDAOObject();
		return df.add(obean);
	}
	protected int persistContract(int custid, JSONObject provisioning)
	{
		VESBean vbean=new VESBean();
		try {
			vbean.setCustomerID(custid);
			vbean.setClassOfService(provisioning.getJSONArray("contractdetails").getJSONObject(0).get("classofservice").toString());
			vbean.setDiscountPercentage(Integer.parseInt(provisioning.getJSONArray("contractdetails").getJSONObject(0).get("discountpercentage").toString()));
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
			java.util.Date utilDate = formatter.parse(provisioning.getJSONArray("contractdetails").getJSONObject(0).getString("fromdate"));

			Date date = new java.sql.Date(utilDate.getTime());
			vbean.setFromDate(date);
			utilDate = formatter.parse(provisioning.getJSONArray("contractdetails").getJSONObject(0).getString("todate"));
			date = new java.sql.Date(utilDate.getTime());
			vbean.setToDate(date);
			vbean.setModelType(provisioning.getJSONArray("contractdetails").getJSONObject(0).getString("modeltype"));
			if(!(provisioning.getJSONArray("contractdetails").getJSONObject(0).getString("change").equals("null")))
			vbean.setChange(Integer.parseInt(provisioning.getJSONArray("contractdetails").getJSONObject(0).getString("change")));
		} catch (JSONException | ParseException e) {
			e.printStackTrace();
		}
		DAOLookup.setcInfo("ves");
		DAOFactory df=DAOLookup.getDAOObject();
		return df.add(vbean);
	}
	protected boolean persistServices(int custid,String prov)
	{
		JSONObject provisioned=null;
		boolean flag=true;
		try {
			provisioned = new JSONObject(prov);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		DAOLookup.setcInfo("service");
		DAOFactory df=DAOLookup.getDAOObject();
		ServiceBean sb=(ServiceBean) df.view(custid);
		if(sb==null)
			flag=false;
		ServiceBean sbean=new ServiceBean();
		try {
		JSONArray newlist=concatenate(provisioned.getJSONArray("listofservices"),new JSONArray(sb.getListOfServices()));
		sbean.setCustomerID(custid);
			sbean.setListOfServices(newlist.toString());
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
		if(!flag){
			df.add(sbean);
		}
		else
			df.update("list_of_services",sbean.getListOfServices(),custid);
		return true;
	}
	protected boolean persistProducts(int custid,String prov)
	{
		JSONObject provisioned=null;
		boolean flag=true;
		try {
			provisioned = new JSONObject(prov);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		ProductBean pbean=new ProductBean();
		pbean.setCustomerID(custid);
		DAOLookup.setcInfo("product");
		DAOFactory df=DAOLookup.getDAOObject();
		ProductBean sb=(ProductBean) df.view(custid);
		if(sb==null)
			flag=false;
		try {
		JSONArray newlist=concatenate(provisioned.getJSONArray("listofproducts"),new JSONArray(sb.getListOfProducts()));
			pbean.setListOfProducts(newlist.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		if(!flag){
			df.add(pbean);
		}
		else
			df.update("list_of_products",pbean.getListOfProducts(),custid);
		return true;
	}
	protected int persistRequest(long threadId,int orderid,String source,String sink,String status){
		RequestResponseBean rbean=new RequestResponseBean();
		rbean.setSourc(source);
		rbean.setSync(sink);
		rbean.setStatus(status);
		rbean.setThreadObject(new Long(threadId).toString());
		rbean.setOrderID(orderid);
		DAOLookup.setcInfo("request");
		DAOFactory df=DAOLookup.getDAOObject();
		System.out.println(rbean);
		int requestId=df.add(rbean);
		return requestId;
	}
}
