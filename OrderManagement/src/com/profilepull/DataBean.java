package com.profilepull;

import java.util.Arrays;

import com.processes.BeanClasses.*;

public class DataBean {
	
	
	 @Override
	public String toString() {
		return "DataBean [cus=" + cus + ", or=" + Arrays.toString(or) + ", pb="
				+ pb + ", sb=" + sb + ", vb=" + vb + "]";
	}
	CustomerBean cus =new CustomerBean();
	 OrderBean []or =new OrderBean[50];
	 ProductBean pb = new ProductBean();
     ServiceBean sb = new ServiceBean();
     VESBean vb = new VESBean();

}
