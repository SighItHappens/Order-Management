package com.processes.BeanClasses;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MDNBean extends Bean {
	long currentMdn;

	public long getCurrentMdn() {
		return currentMdn;
	}

	public void setCurrentMdn(long currentMdn) {
		this.currentMdn = currentMdn;
		JSONArray arr=new JSONArray();
		try {
			JSONObject a=new JSONObject("{\"services\":123}");
			JSONObject b=new JSONObject("{\"services\":124}");
			arr.put(a);
			arr.put(b);
			System.out.println(arr);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	public static void main(String args[])
	{
		MDNBean m=new MDNBean();
		m.setCurrentMdn(0);
	}
}
