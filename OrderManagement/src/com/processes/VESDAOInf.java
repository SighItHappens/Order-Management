package com.processes;

import com.customers.DAOFactory;
import com.processes.BeanClasses.VESBean;

public interface VESDAOInf extends DAOFactory {
	public VESBean displayByEmail(String str);
}
