package com.profilepull;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.json.JSONException;
import org.json.JSONObject;


@Path("/as")
public class HelloWorld {

	
	String str, str1;
	@GET
    @Path("/{id}")
	
    public String getOrder(@PathParam("id") String id) throws JSONException {
	//	JSONObject obj = new JSONObject(id);
		JSONObject obj1 = new JSONObject();
		
	//	str1 = (String) obj.get("cust_id");
		obj1=data(id);
		if(obj1!=null)
		str = obj1.toString();
		else str= "null";
		
		return str;
         
        
	}
	public JSONObject data(String str1) throws JSONException{
		jdbc_new jb=new jdbc_new();
		bean_json b2j = new bean_json();
		return b2j.bean2json(jdbc_new.select(str1));
		
		
		}
	@GET
    @Path("/email/{email}")
	
    public String getOrder1(@PathParam("email") String email) throws JSONException {
	//	JSONObject obj = new JSONObject(email);
		JSONObject obj1 = new JSONObject();
		
	//	str1 = (String) obj.get("email_id");
		obj1=data1(email);
		if(obj1!=null)
		str = obj1.toString();
		else str= "null";
		
		return str;
         
        
	}
	
	
	
	
	public JSONObject data1(String str1) throws JSONException{
		jdbc_new jb=new jdbc_new();
		bean_json b2j = new bean_json();
		
		return b2j.bean2json(jb.select_email(str1));
		
		
		}
	
	
	
	
	
	@GET
    @Path("/order/{order_id}")
	
    public String getOrder_or(@PathParam("order_id") String order_id) throws JSONException {
	//	JSONObject obj = new JSONObject(email);
		JSONObject obj1 = new JSONObject();
		
	//	str1 = (String) obj.get("email_id");
		obj1=data_or(order_id);
		if(obj1!=null)
		str = obj1.toString();
		else str= "null";
         
		return str;
	}
	
	
	
	
	public JSONObject data_or(String str1){
		jdbc_new jb=new jdbc_new();
		bean_json b2j = new bean_json();
		
		return b2j.bean2json_or(jb.select_or(str1));
		
		
		}
	
	
	@GET
    @Path("/contract/{contract_id}")
	
    public String getOrder_con(@PathParam("contract_id") String contract_id) throws JSONException {
	//	JSONObject obj = new JSONObject(email);
		JSONObject obj1 = new JSONObject();
		
	//	str1 = (String) obj.get("email_id");
		obj1=data_con(contract_id);
		
		if(obj1!=null)
		str = obj1.toString();
		else str= "null";
         
		return str;
         
        
	}
	
	
	
	
	public JSONObject data_con(String str1){
		jdbc_new jb=new jdbc_new();
		bean_json b2j = new bean_json();
		
		return b2j.bean2json_ves(jb.select_con(str1));
		
		
		}
	
}


