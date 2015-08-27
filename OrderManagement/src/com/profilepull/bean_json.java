package com.profilepull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.processes.BeanClasses.OrderBean;
import com.processes.BeanClasses.VESBean;

public class bean_json
{
	static String vesstr= null;
	static JSONArray ja=new JSONArray();
	static JSONObject obj = new JSONObject();
	//static JSONObject obj1 = new JSONObject();
	static JSONObject obj_order = new JSONObject();
	static JSONObject obj_contract = new JSONObject();
	
	
	
	public static JSONObject bean2json(DataBean beanobject) throws JSONException{
		JSONObject temp=new JSONObject();
		if(beanobject==null){
			System.out.println("Bean object is null");
			return null;
		}
			
		cust(beanobject);
		pro(beanobject);
		ser(beanobject);
		order_array(beanobject);
		
		
			
vesstr =beanobject.cus.getLineOfBusiness();
	
		
		if(vesstr.equalsIgnoreCase("ves") )
		{ 
			
			//System.out.println("VES BEAN IS PRINING");
			ves(beanobject);
		}
		else
			temp.put("contractid","null");
		temp.put("modeltype","null");
		temp.put("classofservice","null");
		temp.put("fromdate","null");
		temp.put("todate","null");
		temp.put("current","null");
		temp.put("max","null");
		temp.put("discountpercentage","null");
		obj.put("contractdetails", temp);
			
			
		
	
		
		return obj;
	}
	
	public static void cust(DataBean beanobject)
	{
		
		
		try
		{
			JSONObject temp=new JSONObject();

			temp.put("customerid",beanobject.cus.getCustomerId());
			obj.put("lineofbusiness",beanobject.cus.getLineOfBusiness());
		
			temp.put("fname",beanobject.cus.getFirstName());
			temp.put("lname",beanobject.cus.getLastName());
			temp.put("email",beanobject.cus.getEmailId());
			temp.put("dateofbirth",beanobject.cus.getDateOfBirth());
			temp.put("billstartdate",beanobject.cus.getBillStartDate());
			temp.put("billingaddress",beanobject.cus.getBillingAddress());
			temp.put("customerstatus", beanobject.cus.getCustomerStatus());
			temp.put("connectionaddress",beanobject.cus.getConnectionAddress());
			temp.put("contactnumber", beanobject.cus.getContactNumber());
			obj.put("customerdetails", temp);

				
		}
		catch( JSONException e)
		{
			e.printStackTrace();
			System.out.println("Failure");
		}
			
		}
		
	
	
	
	public static void pro(DataBean beanobject)
	{
		JSONObject temp=new JSONObject();
		try
		{
			
			temp.put("products",beanobject.pb.getListOfProducts());
			obj.append("existingsnp", temp);

				
		}
		catch( JSONException e)
		{
			e.printStackTrace();
			System.out.println("Failure");
		}
			
		}
		
		
		public static void order_array(DataBean bean1) throws JSONException{
			OrderBean[] bean = bean1.or;
			//JSONArray ja=new JSONArray();
			Integer i=0;
			for(OrderBean b:bean){
				JSONObject temp=new JSONObject();
				temp=ord(b);
//				obj.put(, obj1.toString());
				//System.out.println("counter: "+i);
				//System.out.print("json: "+temp);
				ja.put(temp);
				//System.out.println("After insert "+ja);
				//obj.append(i.toString(), ja);
				//System.out.println(i);
				i++;
			}
			obj.append("orderhistory",ja);
			
			//System.out.println(ja);
			
			/*int len = bean.length;
			for(int i=0 ; i<len ; i++)
			{
				ord(bean[i]);
				System.out.println(bean[i]);
				System.out.println(i);
				ja.put(i,obj1);
				
			}*/
			//obj.put("orrderarray",ja.toString());
			
		}

		
		public static JSONObject ord(OrderBean beanobject)
		{
			JSONObject obj1=new JSONObject();
			try
			{
				obj1.put("orderid",beanobject.getOrderID());
				obj1.put("dateoforder",beanobject.getDateOfBooking());
				obj1.put("duedate",beanobject.getDueDate());
				obj1.put("orderstatus",beanobject.getOrderStatus());
				obj1.put("products",beanobject.getListOfProducts());
				obj1.put("services",beanobject.getListOfServices());
				//obj.put("delinquent",beanobject.or.getDelinquent());
				//System.out.println( "this is new " +obj1);
				
			}
			catch( JSONException e)
			{
				e.printStackTrace();
				System.out.println("Failure");
			}
			return obj1;
			
		}
	public static void ser(DataBean beanobject)
	{
		
		JSONObject temp=new JSONObject();
		try
		{
			
			temp.put("services",beanobject.sb.getListOfServices());
			obj.append("existingsnp", temp);

				
		}
		catch( JSONException e)
		{
			e.printStackTrace();
			System.out.println("Failure");
		}
	}
	public static void ves(DataBean beanobject)
	{JSONObject temp=new JSONObject();
		try
		{
			
			temp.put("contractid",beanobject.vb.getContractID());
			temp.put("modeltype",beanobject.vb.getModelType());
			temp.put("classofservice",beanobject.vb.getClassOfService());
			temp.put("fromdate",beanobject.vb.getFromDate());
			temp.put("todate",beanobject.vb.getToDate());
			temp.put("current",beanobject.vb.getCurrent());
			temp.put("max",beanobject.vb.getMax());
			temp.put("discountpercentage",beanobject.vb.getDiscountPercentage());
			obj.put("contractdetails", temp);

				
		}
		catch( JSONException e)
		{
			e.printStackTrace();
			System.out.println("Failure");
		}
	}
	
	
	
	
public static JSONObject bean2json_or(OrderBean beanobject){
		
	if(beanobject==null)
		return null;
	try
	{
		obj_order.put("orderid",beanobject.getOrderID());
		obj_order.put("customerid",beanobject.getCustomerID());
		obj_order.put("dateoforder",beanobject.getDateOfBooking());
		obj_order.put("duedate",beanobject.getDueDate());
		obj_order.put("orderstatus",beanobject.getOrderStatus());
		obj_order.put("products",beanobject.getListOfProducts());
		obj_order.put("services",beanobject.getListOfServices());
		//obj.put("delnquent",beanobject.or.getDelinquent());
	}
	catch( JSONException e)
	{
		e.printStackTrace();
		System.out.println("Failure");
	}
	
		
		
	
	
	return obj_order;
	
	
		
	
	
	
}

public JSONObject bean2json_ves(VESBean beanobject) {
	// TODO Auto-generated method stub
	if(beanobject==null)
		return null;
	try
	{
		obj_contract.put("contractid",beanobject.getContractID());
		obj_contract.put("modeltype",beanobject.getModelType());
		obj_contract.put("classofservice",beanobject.getClassOfService());
		obj_contract.put("fromdate",beanobject.getFromDate());
		obj_contract.put("todate",beanobject.getFromDate());
		obj_contract.put("current",beanobject.getCurrent());
		obj_contract.put("max",beanobject.getMax());
		obj_contract.put("discountpercentage",beanobject.getDiscountPercentage());
		obj_contract.put("customerid", beanobject.getCustomerID());
		//obj.put("delnquent",beanobject.or.getDelinquent());
	}
	catch( JSONException e)
	{
		e.printStackTrace();
		System.out.println("Failure");
	}
	return obj_contract;
	
}


}