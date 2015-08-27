package com.profilepull;

import com.processes.BeanClasses.*;
import com.processes.DAOClasses.*;
import com.customers.*;

public class jdbc_new {
	static String vesstr= null;
	static String str1 =null;
	static String email_cust=null;
	static DataBean  beanobject =new DataBean();
	static OrderBean order_bean=new OrderBean();
	static VESBean con_bean=new VESBean();
	public static DataBean select(String que){
		
		//JSONObject obj = new JSONObject(que);
		
	//	str1 = (String) obj.get("cust_id");
		
		int e_id=Integer.parseInt(que);

		DAOLookup.setcInfo("customer");
		DAOFactory df=DAOLookup.getDAOObject();
		CustomerDAO cdao=(CustomerDAO)df;
		if(cdao.view(e_id)==null){
			//System.out.println("customer bean returned is null");
			return null;}
		else{
		beanobject.cus= cdao.view(e_id);
		
	System.out.println(beanobject.cus.getLineOfBusiness());
		
		DAOLookup.setcInfo("order");
		DAOFactory df1=DAOLookup.getDAOObject();
		OrderDAO cdao1 = (OrderDAO)df1;
		System.out.println("order.viewbycustid"+e_id);
		beanobject.or=cdao1.viewByCustomerID(e_id);
		for(OrderBean b:beanobject.or){
			System.out.println("hello"+b);
		}
		DAOLookup.setcInfo("service");
		DAOFactory df2=DAOLookup.getDAOObject();
		ServiceDAO cdao2=(ServiceDAO)df2;
		beanobject.sb=cdao2.view(e_id);
		
		DAOLookup.setcInfo("product");
		DAOFactory df3=DAOLookup.getDAOObject();
		ProductDAO cdao3=(ProductDAO)df3;
		beanobject.pb=cdao3.view(e_id);
		vesstr =beanobject.cus.getLineOfBusiness();
	
		
		if(vesstr.equalsIgnoreCase("ves") )
		{
			//cust_order_pro_ser
			
		//	System.out.println("In contract bean");
			DAOLookup.setcInfo("ves");
			DAOFactory df4=DAOLookup.getDAOObject();
			VESDAO cdao4=(VESDAO)df4;
			
			beanobject.vb=cdao4.view(e_id);
		//	System.out.println(beanobject.vb);
		}
		
			
		
	System.out.println(beanobject.or);
	return beanobject;}
	
	
	}
	
	
public static DataBean select_email(String que){
		
		

		DAOLookup.setcInfo("customer");
		DAOFactory df=DAOLookup.getDAOObject();
		CustomerDAO cdao = (CustomerDAO)df;
		if(cdao.viewByEmail(que)==null)
			return null;
		beanobject.cus=cdao.viewByEmail(que);
		
		int e_id;
		
		e_id=beanobject.cus.getCustomerId();
		DAOLookup.setcInfo("order");
		DAOFactory df1=DAOLookup.getDAOObject();
		OrderDAO cdao1 = (OrderDAO)df1;
		beanobject.or=cdao1.viewByCustomerID(e_id);
		
		DAOLookup.setcInfo("service");
		DAOFactory df2=DAOLookup.getDAOObject();
		ServiceDAO cdao2=(ServiceDAO)df2;
		beanobject.sb=cdao2.view(e_id);
		
		DAOLookup.setcInfo("product");
		DAOFactory df3=DAOLookup.getDAOObject();
		ProductDAO cdao3=(ProductDAO)df3;
		beanobject.pb=cdao3.view(e_id);
    vesstr =beanobject.cus.getLineOfBusiness();
	
		
		if(vesstr.equalsIgnoreCase("ves") )
		{
			//cust_order_pro_ser
			DAOLookup.setcInfo("ves");
			DAOFactory df4=DAOLookup.getDAOObject();
			VESDAO cdao4=(VESDAO)df4;
			beanobject.vb=cdao4.view(e_id);
		}
		
	
	return beanobject;
	
	
	}



public static OrderBean select_or(String que){
	
	//JSONObject obj = new JSONObject(que);
	
//	str1 = (String) obj.get("cust_id");
	
	

	
	
	DAOLookup.setcInfo("order");
	DAOFactory df1=DAOLookup.getDAOObject();
	OrderDAO cd=(OrderDAO)df1;
	if(cd.view(Integer.parseInt(que))==null)
		return null;
	order_bean=cd.view(Integer.parseInt(que));
	

	


return order_bean;


}


public static VESBean select_con(String que){
	
	//JSONObject obj = new JSONObject(que);
	
//	str1 = (String) obj.get("cust_id");
	
	

	
	
	DAOLookup.setcInfo("ves");
	DAOFactory df1=DAOLookup.getDAOObject();
	VESDAO cd=(VESDAO)df1;
	if(cd.view(Integer.parseInt(que))==null)
		return null;
	con_bean=cd.view(Integer.parseInt(que));
	

	


return con_bean;


}

}
