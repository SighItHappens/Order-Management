package com.workflow;

import org.json.JSONArray;
import org.json.JSONException;

public class sample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s="[{\"services\":123},{\"services\":124}";
		String s1="[{\"services\":125},{\"services\":126}";
		try {
			JSONArray a1=new JSONArray(s);
			JSONArray a2=new JSONArray(s);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
