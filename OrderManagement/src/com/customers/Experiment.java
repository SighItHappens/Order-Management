package com.customers;

import java.text.ParseException;

import org.json.JSONException;
import org.json.JSONObject;
import com.processes.BeanClasses.Bean;
import com.processes.BeanClasses.CustomerBean;
import com.processes.BeanClasses.OrderBean;
import com.processes.BeanClasses.ProductBean;
import com.processes.BeanClasses.RequestResponseBean;
import com.processes.DAOClasses.CustomerDAO;
import com.processes.DAOClasses.OrderDAO;
import com.processes.DAOClasses.ProductDAO;
import com.processes.DAOClasses.RequestResponseDAO;

public class Experiment {

	// public static String profilePullByEmail(String str)
	// {
	// CustomerDAO cDAO = new CustomerDAO();
	// String returnString= "";
	// CustomerBean cbean = cDAO.displayByEmail(str);
	// if (cbean.getLineOfBusiness().equals("ves"))
	// {
	// VESDAO vDAO = new VESDAO();
	// VESBean vbean = vDAO.displayByEmail(str);
	// if(vbean!=null)
	// returnString +=vbean.toString();
	// return returnString;
	// }
	// if(cbean!=null)
	// returnString+=cbean.toString();
	// return returnString;
	// }
	public static void main(String args[]) throws ParseException {
//		CustomerBean addRecord = new CustomerBean();
//		addRecord.setLineOfBusiness("cmb");
//		addRecord.setFirstName("anirudh");
//		addRecord.setLastName("rama");
//		addRecord.setEmailId("aa@gmail.com");
//
//		String pattern = "dd-MMM-yyyy";
//		SimpleDateFormat format = new SimpleDateFormat(pattern);
//		java.util.Date dob = format.parse("23-Jan-2001");
//		java.sql.Date d1 = new java.sql.Date(dob.getTime());
//		addRecord.setDateOfBirth(d1);
//		addRecord.setBillStartDate(d1);
//		addRecord.setCustomerStatus("active");
//		addRecord.setConnectionAddress("chennai");
//		addRecord.setContactNumber("1234567890");
//		// Date d=new Date("23-Dec-2002");
//		addRecord.setDateOfBirth(null);
		CustomerDAO d = new CustomerDAO();
//		System.out.println(d.add(addRecord));
//		System.out.println("added");
		CustomerBean cbean = d.view(1000003);
		CustomerBean newbean = d.viewByEmail("aa@gmail.com");
		System.out.println(cbean);
		System.out.println(newbean);
		
//		OrderBean obean = new OrderBean();
//		obean.setCustomerID(1000003);
//		String pattern = "dd-MMM-yyyy";
//		SimpleDateFormat format = new SimpleDateFormat(pattern);
//		java.util.Date dob = format.parse("23-Jan-2001");
//		java.sql.Date d1 = new java.sql.Date(dob.getTime());
//		obean.setDateOfBooking(d1);
//		obean.setDueDate(d1);
//		obean.setOrderStatus("cancelled");
//		obean.setQuantity(415);
//		obean.setListOfServices("services");
//		obean.setListOfProducts("products");
		OrderDAO odao = new OrderDAO();
//		System.out.println(odao.add(obean));
		OrderBean oo = odao.view(10020);
		System.out.println(oo);
		OrderBean[] ooo = odao.viewByCustomerID(1000003);
		System.out.println(ooo[0]);
		
		ProductBean pbean = new ProductBean();
		pbean.setCustomerID(100003);
		pbean.setListOfProducts("products");
		ProductDAO pdao = new ProductDAO();
		System.out.println(pdao.add(pbean));
		
		RequestResponseDAO dao = new RequestResponseDAO();
		RequestResponseBean rbean = new RequestResponseBean();
		rbean.setSourc("source");
		rbean.setStatus("active");
		rbean.setSync("illai");
		rbean.setThreadObject("ooo");
		System.out.println(dao.add(rbean));
	}

	// public static void main(String args[]) {
	// try{
	// CustomerBean cbean = new CustomerBean();
	// cbean.setLineOfBusiness("cmb");
	// cbean.setFirstName("Vishnu");
	// cbean.setLastName("Shankar");
	// cbean.setEmailId("vish045@gmail.com");
	// String pattern="dd-MMM-yyyy";
	// SimpleDateFormat format=new SimpleDateFormat(pattern);
	// java.util.Date dob=format.parse("23-Jan-2001");
	// java.sql.Date d1=new java.sql.Date(dob.getTime());
	// cbean.setDateOfBirth(d1);
	// cbean.setBillingAddress("Chennai");
	// cbean.setCustomerStatus("active");
	// cbean.setConnectionAddress("Ranchi");
	// cbean.setContactNumber("7418389774");
	// CustomerDAO cdao= new CustomerDAO();
	// int i= cdao.add(cbean);
	// System.out.println(i);
	// System.out.println(cbean);
	// CustomerBean cc= cdao.view(1000001);
	// System.out.println(cc);
	// }
	// catch(Exception e)
	// {
	//
	// }
	// try {
	// VESBean vbean = new VESBean();
	// // vbean.setContractID(100);
	// vbean.setModelType("Moddel");
	// vbean.setCustomerID(1000001);
	// vbean.setClassOfService("service class");
	// String pattern = "dd-MMM-yyyy";
	// SimpleDateFormat format = new SimpleDateFormat(pattern);
	//
	// java.util.Date dob = format.parse("23-Jan-2001");
	// java.sql.Date d1 = new java.sql.Date(dob.getTime());
	// vbean.setFromDate(d1);
	//
	// vbean.setToDate(d1);
	// vbean.setDiscountPercentage(10);
	// vbean.setChange(25);
	// VESDAO d = new VESDAO();
	// System.out.println(d.add(vbean));
	// // VESBean vvbean = (VESBean) d.view(10001);
	// // System.out.println();
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// //
	// // try{
	// OrderBean addRecord=new OrderBean();
	// addRecord.setCustomerID(34);
	// String pattern="dd-MMM-yyyy";
	// SimpleDateFormat format=new SimpleDateFormat(pattern);
	//
	// java.util.Date dob=format.parse("23-Jan-2001");
	// java.sql.Date d1=new java.sql.Date(dob.getTime());
	// addRecord.setDateOfBooking(d1);
	// java.util.Date dd=format.parse("2-Apr-2001");
	// java.sql.Date d2=new java.sql.Date(dob.getTime());
	// addRecord.setDueDate(d2);
	// addRecord.setOrderStatus("new");
	// addRecord.setQuantity(233);
	// addRecord.setListOfServices("listOfServices");
	// addRecord.setListOfProducts("listOfProducts");
	// // Date d=new Date("23-Dec-2002");
	// // addRecord.setDateOfBirth(null);
	//
	// //check1
	// OrderDAO d=new OrderDAO();
	// System.out.println(d.add(addRecord));
	// System.out.println("added");
	//
	// OrderBean bean = (OrderBean) d.view(10001);
	// System.out.println(bean);
	// System.out.println("  ");
	// System.out.println("  ");
	// //checkbycustomerid
	// OrderBean[] obean = d.viewByCustomerID(34);
	// for(int i=0;i<obean.length;i++)
	// System.out.println(obean[i]);
	// }
	// catch(Exception e)
	// {
	// e.printStackTrace();
	// }
	// }

	public static void main1(String ar) {

		// String ar = "{\"lineofbusiness\": \"ves\",\"customerdetails\": {"
		// + "\"customertype\": \"new\",\"customerid\": \"1234567\","
		// + "\"fname\": \"suresh\"," + "\"lname\": \"siddharth\","
		// + "\"connectionaddress\": {"
		// + " \"streetname\": \"10, mg road\"," + "\"zipcode\": 560102,"
		// + "\"city\": \"bangalore\"," + "\"state\": \"karnataka\","
		// + "\"country\": \"india\"" + "}," + "\"billingaddress\": {"
		// + "\"streetname\": \"10, mg road\"," + "\"zipcode\": 560102,"
		// + "\"city\": \"bangalore\"," + "\"state\": \"karnataka\","
		// + "\"country\": \"india\"" + "},"
		// + "\"email\": \"suresh.siddharth@gmail.com\","
		// + "\"contactnumber\":\"9500689870\","
		// + "\"dateofbirth\": \"24-mar-1993\"" + "},"
		// + "contractdetails:{" + "\"modeltype\":\"transactional|rtb\","
		// + "\"classofservice\":\"platinum|gold|silver|bronze|normal\","
		// + "\"fromdate\": \"18-aug-2015\","
		// + "\"todate\":\"18-aug-2016\"," + "\"discountpercentage\":10"
		// + "}}";
		System.out.println(ar);

		JSONObject obj;
		try {
			obj = new JSONObject(ar);

			System.out.println(obj.get("lineofbusiness").toString());

			DAOLookup.setcInfo(obj.get("lineofbusiness").toString());

			DAOFactory df = DAOLookup.getDAOObject();
			Bean bean = DAOLookup.getBeanObject();

			// df.add(bean, obj);
			// df.view(bean);
//			// System.out.println(JDBC.insert(bean));
//			JDBC.viewvesdb();
//			JDBC.viewcustomerdb();
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}
}
