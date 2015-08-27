package com.rest;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.profilepull.bean_json;
import com.profilepull.jdbc_new;
import com.workflow.CancelOrderWorkflow;
import com.workflow.ChangeDuedateWorkflow;
import com.workflow.FinanceWorkflow;
import com.workflow.OrderWorkflow;
import com.workflow.ProfilePull;

// Plain old Java Object it does not extend as class or implements 
// an interface

// The class registers its methods for the HTTP GET request using the @GET annotation. 
// Using the @Produces annotation, it defines that it can deliver several MIME types,
// text, XML and HTML. 

// The browser requests per default the HTML MIME type.

//Sets the path to base URL + /hello
@Path("/om")
public class OMRestReciever {

	@GET
	@Path("/trial")
	@Produces(MediaType.APPLICATION_JSON)
	public String trial() {
		return "hey";
	}

	// checked and working
	@GET
	@Path("/billingAccountPull")
	@Produces(MediaType.TEXT_PLAIN)
	public String billingAccountPullRequest(
			@DefaultValue("null") @QueryParam("billingDate") String billDate,
			@DefaultValue("null") @QueryParam("portfolio") String portfolio) {
		System.out.println(billDate);

		JSONObject result = BusinessLogic.getAccounts(billDate, portfolio);
		return result.toString();
	}

	// checking
	// @GET
	// @Path("/changeDueDate/{orderID}&{newDate}")
	// @Produces(MediaType.APPLICATION_JSON)
	// public String changeDueDateBySelfService(@PathParam("orderID")int
	// orderID, @PathParam("newDate")String newDate) {

	@GET
	@Path("/financeConnectionStatus/{account_no}/{status}")
	@Produces(MediaType.TEXT_PLAIN)
	public String financeRequest(@PathParam("account_no") int account_no,
			@PathParam("status") int status) {
		FinanceWorkflow financeworkflow = new FinanceWorkflow(status,
				account_no);
		return "true";
	}

	// checked
	@GET
	@Path("/cancelOrder/{orderID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String cancelOrderBySelfService(@PathParam("orderID") int orderID) {
		System.out.println(orderID);
		CancelOrderWorkflow cancelworkflow = new CancelOrderWorkflow(orderID);
		return "true";
	}

	@GET
	@Path("/orderPull/{orderID}")
	@Produces(MediaType.TEXT_PLAIN)
	public String orderPull(@PathParam("orderID") int orderID) {
		JSONObject result = BusinessLogic.getOrderPull(orderID);
		return "true";
	}

	// checked
	@GET
	@Path("/changeDueDate/{orderID}&{newDate}")
	@Produces(MediaType.APPLICATION_JSON)
	public String changeDueDateBySelfService(@PathParam("orderID") int orderID,
			@PathParam("newDate") String newDate) {

		ChangeDuedateWorkflow changeduedateworkflow = new ChangeDuedateWorkflow(
				orderID, newDate);
		return "true";
	}

	// @POST
	// @Path("/provisioningComplete")
	// @Consumes("application/json")
	// public int provisioningCompleteStatus(JSONObject product) {
	// int result=provisioningStatus(product);
	// return result;
	// }

	// cheching
	@POST
	@Path("/submitorder")
	// @Consumes("application/json")
	public Response submitOrder(String product) {
		System.out.println(product);
		JSONObject jobj = null;
		try {
			jobj = new JSONObject(product);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		OrderWorkflow ord = new OrderWorkflow(jobj);

		return Response.status(201).entity("true").build();
	}

	String str, str1;

	@GET
	@Path("profilePull/{id}")
	public String getOrder(@PathParam("id") String id) throws JSONException {
		// // JSONObject obj = new JSONObject(id);
		// JSONObject obj1 = new JSONObject();
		//
		// // str1 = (String) obj.get("cust_id");
		// obj1=data(id);
		// if(obj1!=null)
		// str = obj1.toString();
		// else str= "null";
		//
		// return str;
		ProfilePull pf = new ProfilePull();
		String result = pf.profilePullById(Integer.parseInt(id));
		return result;
	}

	public JSONObject data(String str1) throws JSONException {
		jdbc_new jb = new jdbc_new();
		bean_json b2j = new bean_json();
		return b2j.bean2json(jdbc_new.select(str1));

	}

	@GET
	@Path("profilePull/email/{email}")
	public String getOrder1(@PathParam("email") String email)
			throws JSONException {
		ProfilePull pf = new ProfilePull();
		String result = pf.profilePullByEmail(email);
		return result;

	}

	public JSONObject data1(String str1) throws JSONException {
		jdbc_new jb = new jdbc_new();
		bean_json b2j = new bean_json();

		return b2j.bean2json(jb.select_email(str1));

	}

	@GET
	@Path("/order/{order_id}")
	public String getOrder_or(@PathParam("order_id") String order_id)
			throws JSONException {
		// JSONObject obj = new JSONObject(email);
		JSONObject obj1 = new JSONObject();

		// str1 = (String) obj.get("email_id");
		obj1 = data_or(order_id);
		if (obj1 != null)
			str = obj1.toString();
		else
			str = "null";

		return str;
	}

	public JSONObject data_or(String str1) {
		jdbc_new jb = new jdbc_new();
		bean_json b2j = new bean_json();

		return b2j.bean2json_or(jb.select_or(str1));

	}

	@GET
	@Path("/contract/{contract_id}")
	public String getOrder_con(@PathParam("contract_id") String contract_id)
			throws JSONException {
		// JSONObject obj = new JSONObject(email);
		JSONObject obj1 = new JSONObject();

		// str1 = (String) obj.get("email_id");
		obj1 = data_con(contract_id);

		if (obj1 != null)
			str = obj1.toString();
		else
			str = "null";

		return str;

	}

	public JSONObject data_con(String str1) {
		jdbc_new jb = new jdbc_new();
		bean_json b2j = new bean_json();

		return b2j.bean2json_ves(jb.select_con(str1));

	}

}