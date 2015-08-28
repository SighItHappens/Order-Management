package com.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//asd
import org.json.JSONException;
import org.json.JSONObject;

import com.workflow.CancelOrderWorkflow;
import com.workflow.ChangeDuedateWorkflow;
import com.workflow.FinanceWorkflow;
import com.workflow.OrderWorkflow;
import com.workflow.ProfilePull;
import com.workflow.ProvisioningCompleted;

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
		String result = BusinessLogic.getAccounts(billDate, portfolio);
		return result;
	}

	@GET
	@Path("/financeConnectionStatus/{account_no}/{status}")
	@Produces(MediaType.TEXT_PLAIN)
	public String financeRequest(@PathParam("account_no") int account_no,
			@PathParam("status") int status) {
		FinanceWorkflow financeworkflow = new FinanceWorkflow(status,
				account_no);
		if(financeworkflow.flag)
			return "true";
		else 
			return "false";
	}

	// checked
	@GET
	@Path("/cancelOrder/{orderID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String cancelOrderBySelfService(@PathParam("orderID") int orderID) {
		CancelOrderWorkflow cancelworkflow = new CancelOrderWorkflow(orderID);
		if(cancelworkflow.flag)
			return "true";
		else
			return "false";
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

	@POST
	@Path("/provisioningComplete")
	@Consumes(MediaType.TEXT_PLAIN)
	public String provisioningCompleteStatus(String product) {
		ProvisioningCompleted pc = new ProvisioningCompleted();
		String result=null;
		try {
			result=pc.provisioned(new JSONObject(product));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		if(result.equals("true"))
			return "acknowledge";
		else
			return "failed";
	}

	@POST
	@Path("/submitorder")
	public Response submitOrder(String product) {
		JSONObject jobj = null;
		try {
			System.out.println("Inside Submit order: "+product);
			jobj = new JSONObject(product);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		OrderWorkflow ord = new OrderWorkflow(jobj);
		return Response.status(201).entity(ord.response.toString()).build();
	}

	String str, str1;

	@GET
	@Path("/profilePull/{id}")
	public String profilePull(@PathParam("id") String id) throws JSONException {
		ProfilePull pf = new ProfilePull();
		String result = pf.profilePullById(Integer.parseInt(id));
		return result;
	}

	@GET
	@Path("/profile")
	@Produces(MediaType.TEXT_PLAIN)
	public String sendProfile(
			@DefaultValue("null") @QueryParam("accno") String accno) {
		ProfilePull pf = new ProfilePull();
		String result=null;
		try{
		result = pf.profilePullById(Integer.parseInt(accno));
		}catch(Exception E){
			return "null";
		}
		return result;
	}

	@GET
	@Path("/profilePull/email/{email}")
	public String getOrder1(@PathParam("email") String email)
			throws JSONException {
		ProfilePull pf = new ProfilePull();
		String result = pf.profilePullByEmail(email);
		return result;

	}

	@GET
	@Path("/order/{order_id}")
	public String getOrder_or(@PathParam("order_id") String order_id)
			throws JSONException {
		ProfilePull pf = new ProfilePull();
		String result = pf.orderPull(Integer.parseInt(order_id));
		return result;
	}

	@GET
	@Path("/contract/{contract_id}")
	public String getOrder_con(@PathParam("contract_id") String contract_id)
			throws JSONException {
		ProfilePull pf = new ProfilePull();
		String result = pf.contractPull(Integer.parseInt(contract_id));
		return result;

	}

}