package com.chinacreator.c2.flow.impl;

import static org.junit.Assert.fail;

import java.util.UUID;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.chinacreator.c2.flow.api.WfFormService;
import com.chinacreator.c2.flow.detail.WfFormData;
import com.chinacreator.c2.flow.detail.WfOperator;
import com.chinacreator.c2.flow.detail.WfProcessInstance;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = false, transactionManager = "transactionManager")
@ContextConfiguration(locations = { "classpath:custom/c2-flow-core-test.xml" })
public class WfFormServiceImplTest {
	@Autowired
	WfFormService wfFormServiceLocal;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetStartFormData() {
		String processDefinitionId = "";
		try {
			wfFormServiceLocal.getStartFormData(processDefinitionId);
			Assert.assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void testGetTaskFormData() {
		String taskId = "";
		try {
			wfFormServiceLocal.getTaskFormData(taskId);
			Assert.assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void testSaveTaskFormData() {
		WfOperator wfOperator = new WfOperator();
		WfFormData data = new WfFormData();
		try {
			String result = wfFormServiceLocal.saveTaskFormData(wfOperator, data);
			Assert.assertEquals(result, "200");
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void testSaveStartFormData() {
		WfOperator wfOperator = new WfOperator();
		WfFormData data = new WfFormData();
		try {
			String businessKey = UUID.randomUUID().toString();
			WfProcessInstance result = wfFormServiceLocal.saveStartFormData(wfOperator, data, businessKey);
			Assert.assertNotNull(result);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

}
