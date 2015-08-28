package com.processes.DAOClasses;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.processes.BeanClasses.MDNBean;

public class MdnDAOTest {
	MdnDAO mdn=new MdnDAO();

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	

	@Test
	public void testView() {
		MDNBean mbean=new MDNBean();
		int id=0;
		mbean=(MDNBean) mdn.view(id);
		assertTrue(mdn.successflag==true);
	}

	

}

