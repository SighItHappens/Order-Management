package com.workflow;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.customers.DAOFactory;
import com.customers.DAOLookup;
import com.processes.BeanClasses.CustomerBean;
import com.processes.BeanClasses.OrderBean;
import com.processes.BeanClasses.ProductBean;
import com.processes.BeanClasses.ServiceBean;
import com.processes.BeanClasses.VESBean;
import com.processes.DAOClasses.*;

public class ProfilePull {
	public String profilePullByEmail(String email) {
		DAOLookup.setcInfo("customer");
		DAOFactory df = DAOLookup.getDAOObject();
		CustomerBean cbean = ((CustomerDAO) df).viewByEmail(email);
		if (cbean.getCustomerId() == 0) {
			return "null";
		}
		return profilePullById(cbean.getCustomerId());
	}

	public String profilePullById(int custid) {
		DAOLookup.setcInfo("customer");
		DAOFactory df = DAOLookup.getDAOObject();
		CustomerBean cbean = (CustomerBean) df.view(custid);
		if (cbean.getCustomerId() == 0) {
			return "null";
		}
		JSONObject profile = new JSONObject();
		JSONObject custdetails = new JSONObject();
		JSONObject temp = new JSONObject();
		JSONArray services = new JSONArray();
		JSONArray products = new JSONArray();
		JSONArray contracts = new JSONArray();
		JSONArray orders = new JSONArray();
		DAOLookup.setcInfo("service");
		DAOFactory df2 = DAOLookup.getDAOObject();
		ServiceBean sbean = (ServiceBean) df2.view(custid);
		DAOLookup.setcInfo("product");
		DAOFactory df3 = DAOLookup.getDAOObject();
		ProductBean pbean = (ProductBean) df3.view(custid);
		DAOLookup.setcInfo("ves");
		DAOFactory df4 = DAOLookup.getDAOObject();
		VESBean vbean[] = ((VESDAO) df4).viewByCustomerID(custid);
		DAOLookup.setcInfo("order");
		DAOFactory df5 = DAOLookup.getDAOObject();
		OrderBean obean[] = ((OrderDAO) df5).viewByCustomerID(custid);
		try {
			profile.put("lineofbusiness", cbean.getLineOfBusiness());
			custdetails.put("customerid", cbean.getCustomerId());
			custdetails.put("fname", cbean.getFirstName());
			custdetails.put("lname", cbean.getLastName());
			custdetails.put("customerstatus", cbean.getCustomerStatus());
			System.out.println(custdetails);
			custdetails.put("billstartdate", cbean.getBillStartDate());
			System.out.println(custdetails);
			custdetails.put("connectionaddress", new JSONObject(cbean.getConnectionAddress()));
			custdetails.put("billingaddress", new JSONObject(cbean.getBillingAddress()));
			custdetails.put("email", cbean.getEmailId());
			custdetails.put("contactnumber", Long.parseLong(cbean.getContactNumber()));
			if(cbean.getLineOfBusiness().equals("ves"))
				custdetails.put("dateofbirth", "null");
			else
			custdetails.put("dateofbirth", cbean.getDateOfBirth());
			profile.put("customerdetails", custdetails);
			//System.out.println(sbean + " " + pbean);
			if (sbean.getCustomerID() != 0 && pbean.getCustomerID() != 0) {
				services = new JSONArray(sbean.getListOfServices());
				products = new JSONArray(pbean.getListOfProducts());
				temp.put("services", services);
				temp.put("products", products);
			} else {
				temp.put("services", "null");
				temp.put("products", "null");
			}
			profile.put("existingsnp", temp);
			if (vbean != null) {
				for (VESBean b : vbean) {
					temp = new JSONObject();
					temp.put("contractid", b.getContractID());
					temp.put("modeltype", b.getModelType());
					temp.put("classofservice", b.getClassOfService());
					temp.put("fromdate", b.getFromDate().toString());
					temp.put("todate", b.getToDate().toString());
					temp.put("current", b.getCurrent());
					temp.put("max", b.getMax());
					temp.put("discountpercentage", b.getDiscountPercentage());
					contracts.put(temp);
				}
			} else {
				temp = new JSONObject();
				temp.put("contractid", "null");
				temp.put("modeltype", "null");
				temp.put("classofservice", "null");
				temp.put("fromdate", "null");
				temp.put("todate", "null");
				temp.put("current", 0);
				temp.put("max", 0);
				temp.put("discountpercentage", 0);
				contracts.put(temp);
			}
			profile.put("contractdetails", contracts);
			if (obean != null) {
				for (OrderBean b : obean) {
					temp = new JSONObject();
					temp.put("orderid", b.getOrderID());
					temp.put("dateoforder", b.getDateOfBooking());
					temp.put("duedate", b.getDueDate());
					temp.put("orderstatus", b.getOrderStatus());
					temp.put("services", new JSONArray(b.getListOfServices()));
					temp.put("products", new JSONArray(b.getListOfProducts()));
					orders.put(temp);
				}
			} else {
				temp = new JSONObject();
				temp.put("orderid", "null");
				temp.put("dateoforder", "null");
				temp.put("duedate", "null");
				temp.put("orderstatus", "null");
				temp.put("services", "null");
				temp.put("products", "null");
				orders.put(temp);
			}
			profile.put("orderhistory", orders);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return profile.toString();
	}
	public String orderPull(int orderid){
		DAOLookup.setcInfo("order");
		DAOFactory df5 = DAOLookup.getDAOObject();
		OrderBean b=(OrderBean) df5.view(orderid);
		if(b.getOrderID()==0)
			return "null";
		JSONObject temp = new JSONObject();
		try {
			temp.put("orderid", b.getOrderID());
			temp.put("dateoforder", b.getDateOfBooking());
			temp.put("duedate", b.getDueDate());
			temp.put("orderstatus", b.getOrderStatus());
			temp.put("services", new JSONArray(b.getListOfServices()));
			temp.put("products", new JSONArray(b.getListOfProducts()));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return temp.toString();
	}
	public String contractPull(int contractid){
		DAOLookup.setcInfo("ves");
		DAOFactory df5 = DAOLookup.getDAOObject();
		VESBean b=(VESBean) df5.view(contractid);
		if(b.getContractID()==0)
			return "null";
		JSONObject temp = new JSONObject();
		try {
			temp.put("contractid", b.getContractID());
			temp.put("modeltype", b.getModelType());
			temp.put("classofservice", b.getClassOfService());
			temp.put("fromdate", b.getFromDate().toString());
			temp.put("todate", b.getToDate().toString());
			temp.put("current", b.getCurrent());
			temp.put("max", b.getMax());
			temp.put("discountpercentage", b.getDiscountPercentage());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp.toString();
	}
}
