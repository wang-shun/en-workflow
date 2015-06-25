package com.chinacreator.c2.flow.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.chinacreator.c2.flow.api.WfHistoryService;
import com.chinacreator.c2.flow.detail.WfComment;
import com.chinacreator.c2.flow.detail.WfDetailParam;
import com.chinacreator.c2.flow.detail.WfHistoricProcessInstanceParam;
import com.chinacreator.c2.flow.detail.WfHistoricTaskParam;
import com.chinacreator.c2.flow.detail.WfOperator;
import com.chinacreator.c2.flow.detail.WfVariable.VariableScope;
import com.chinacreator.c2.flow.detail.WfVariableParam;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = false, transactionManager = "transactionManager")
@ContextConfiguration(locations = { "classpath:custom/c2-flow-core-test.xml" })
public class WfHistoryServiceImplTest {
	@Autowired
	WfHistoryService wfHistoryService;

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
	public void testGetHistoricProcessInstanceById() {
		String processInstanceId = "";
		try {
			wfHistoryService.getHistoricProcessInstanceById(processInstanceId);
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void testQueryHistoricProcessInstances() {
		WfHistoricProcessInstanceParam params = new WfHistoricProcessInstanceParam();
		try {
			wfHistoryService.queryHistoricProcessInstances(params);
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void testDeleteHistoricProcessInstance() {
		String processInstanceId = "";
		try {
			wfHistoryService.deleteHistoricProcessInstance(processInstanceId);
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void testGetHistoricProcessInstanceCandidates() {
		String processInstanceId = "";
		try {
			wfHistoryService
					.getHistoricProcessInstanceCandidates(processInstanceId);
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void testGetHistoricProcessInstanceVariables() {
		String processInstanceId = "";
		try {
			wfHistoryService
					.getHistoricProcessInstanceVariables(processInstanceId);
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void testAddHistoricProcessInstanceComment() {
		WfOperator wfOperator = new WfOperator();
		String processInstanceId = "";
		WfComment wfComment = new WfComment();
		try {
			wfHistoryService.addHistoricProcessInstanceComment(wfOperator,
					processInstanceId, wfComment);
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void testGetAllHistoricProcessInstanceComments() {
		String processInstanceId = "";
		try {
			wfHistoryService
					.getAllHistoricProcessInstanceComments(processInstanceId);
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void testGetHistoricProcessInstanceComment() {
		String processInstanceId = "";
		String commentId = "";
		try {
			wfHistoryService.getHistoricProcessInstanceComment(
					processInstanceId, commentId);
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void testDeleteHistoricProcessInstanceComment() {
		WfOperator wfOperator = new WfOperator();
		String processInstanceId = "";
		String commentId = "";
		try {
			String result = wfHistoryService
					.deleteHistoricProcessInstanceComment(wfOperator,
							processInstanceId, commentId);
			assertEquals(result, "200");
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void testGetHistoricTaskById() {
		String taskId = "";
		try {
			wfHistoryService.getHistoricTaskById(taskId);
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void testQueryHistoricTasks() {
		WfHistoricTaskParam params = new WfHistoricTaskParam();
		try {
			wfHistoryService.queryHistoricTasks(params);
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void testDeleteHistoricTask() {
		WfOperator wfOperator = new WfOperator();
		String taskId = "";
		try {
			wfHistoryService.deleteHistoricTask(wfOperator, taskId);
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void testGetHistoricTaskCandidates() {
		String taskId = "";
		try {
			wfHistoryService.getHistoricTaskCandidates(taskId);
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void testGetHistoricTaskVariables() {
		String taskId = "";
		try {
			wfHistoryService.getHistoricTaskVariables(taskId,
					VariableScope.GLOBAL);
			wfHistoryService.getHistoricTaskVariables(taskId,
					VariableScope.LOCAL);
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void testGetHistoricTaskVariableByName() {
		String taskId = "";
		String variableName = "";
		try {
			wfHistoryService.getHistoricTaskVariableByName(taskId,
					variableName, VariableScope.GLOBAL);
			wfHistoryService.getHistoricTaskVariableByName(taskId,
					variableName, VariableScope.LOCAL);
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void testQueryHistoricVariables() {
		WfVariableParam params = new WfVariableParam();
		try {
			wfHistoryService.queryHistoricVariables(params);
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void testQueryHistoricDetails() {
		WfDetailParam params = new WfDetailParam();
		try {
			wfHistoryService.queryHistoricDetails(params);
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

}
