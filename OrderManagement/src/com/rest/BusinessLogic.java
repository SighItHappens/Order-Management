package com.rest;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Map.Entry;

import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;
import javax.json.JsonValue.ValueType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.customers.DAOFactory;
import com.customers.DAOLookup;
import com.processes.DAOClasses.*;

public class BusinessLogic {
	public static String getAccounts(String billDate, String portfolio) {
		DAOLookup.setcInfo("customer");
		DAOFactory df = (CustomerDAO) DAOLookup.getDAOObject();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");

		java.util.Date utilDate;
		try {
			utilDate = formatter.parse(billDate);

			Date date = new java.sql.Date(utilDate.getTime());
			String customerids = ((CustomerDAO) df).billing(date, portfolio);
			if(customerids.equals(""))
				return "null";
			StringTokenizer st = new StringTokenizer(customerids, ",");
			JSONObject result = new JSONObject();
			int count = st.countTokens();
			System.out.println(count);
			String[] array = new String[count];
			int i = 0;
			while (i < count) {
				array[i] = (String) st.nextElement();
				i++;
			}
			result.put("accountNo", array);
			result.put("totalNumOfAcc", new Integer(count).toString());
		
		System.out.println(result);
		return result.toString();
		} catch (JSONException|ParseException  e) {

			e.printStackTrace();
			return null;
		} 
	}
}
