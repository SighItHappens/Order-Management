package com.processes;

import com.customers.DAOFactory;
import com.processes.BeanClasses.Bean;
import com.processes.BeanClasses.CustomerBean;

public interface CustomerDAOInf extends DAOFactory {

	CustomerBean viewByEmail(String str);
}
