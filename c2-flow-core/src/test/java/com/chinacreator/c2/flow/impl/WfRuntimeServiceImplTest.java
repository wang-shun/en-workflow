package com.chinacreator.c2.flow.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.task.IdentityLinkType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.chinacreator.c2.flow.api.WfRuntimeService;
import com.chinacreator.c2.flow.detail.WfActivity;
import com.chinacreator.c2.flow.detail.WfBusinessData;
import com.chinacreator.c2.flow.detail.WfConstants;
import com.chinacreator.c2.flow.detail.WfIdentityLink;
import com.chinacreator.c2.flow.detail.WfOperator;
import com.chinacreator.c2.flow.detail.WfPageList;
import com.chinacreator.c2.flow.detail.WfProcessInstance;
import com.chinacreator.c2.flow.detail.WfProcessInstanceParam;
import com.chinacreator.c2.flow.detail.WfResult;
import com.chinacreator.c2.flow.detail.WfTask;
import com.chinacreator.c2.flow.detail.WfTaskAction;
import com.chinacreator.c2.flow.detail.WfTaskParam;
import com.chinacreator.c2.flow.detail.WfVariable;
import com.chinacreator.c2.flow.detail.WfVariable.VariableScope;
import com.chinacreator.c2.flow.detail.WfVariable.VariableType;
import com.chinacreator.c2.flow.util.CommonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = false, transactionManager = "transactionManager")
@ContextConfiguration(locations = { "classpath:custom/c2-flow-core-test.xml" })
public class WfRuntimeServiceImplTest {

	@Autowired
	private WfRuntimeService wfRuntimeServiceLocal;

	/*
	 * @BeforeClass public static void setUpBeforeClass() throws Exception { }
	 * 
	 * @Before public void setUp() throws Exception { }
	 * 
	 * @After public void tearDown() throws Exception { }
	 */

	@Test
	public void testGetProcessInstanceById() {
		String pinstid = "1884FC935A894F18930ABAC0FE6C340C";
		try {
			WfProcessInstance wfProcessInstance = wfRuntimeServiceLocal
					.getProcessInstanceById(pinstid);
			Assert.assertEquals("testBusinessId",
					wfProcessInstance.getBusinessKey());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testDeleteProcessInstancesById() {
		WfOperator wfOperator = new WfOperator("1", "admin", "系统管理员",
				"127.0.0.1", null);
		try {
			String processInstanceId = "2485BFCB3A3443F693CF683BE4A1B8D3";
			String result = wfRuntimeServiceLocal.deleteProcessInstancesById(
					wfOperator, "测试用例删除流程实例", processInstanceId);
			assertTrue("200".equals(result));
			// wfRuntimeServiceLocal.getProcessInstanceById(processInstanceId);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testStartProcessInstanceById() {
		WfOperator wfOperator = new WfOperator("1", "admin", "系统管理员",
				"127.0.0.1", null);
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("usertask3Assignee", "E9E447507B7542E79C3ACB6F756D1FC9");
		try {
			wfRuntimeServiceLocal.startProcessInstanceById(wfOperator,
					"testBusinessId",
					"hndssh:2:FC119D8D881C437DAB92B80E4BE32FFA", variables);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetCurrentActiveTaskIds() {
		try {
			String taskId = wfRuntimeServiceLocal
					.getCurrentActiveTaskIds("F4A42A746CDB4B21A38F0503C2FEDD2E");
			Assert.assertNotNull(taskId);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testStartProcessInstanceByKey() {
		WfOperator wfOperator = new WfOperator("1", "admin", "系统管理员",
				"127.0.0.1", null);
		Map<String, Object> variables = new HashMap<String, Object>();
		/*
		 * variables .put("usertask3CandidateGroup", "1");
		 */
		try {
			WfBusinessData wfBusinessData = new WfBusinessData("", "app1",
					"70F30BF81F094FC08E557F196B9975C7", "测试模块", "remark1",
					"remark2", "remark3", "remark4", "remark5", "remark6",
					"remark7", "remark8", "remark9", "remark10");
			wfOperator.setBusinessData(wfBusinessData);
			WfResult wfResult = wfRuntimeServiceLocal
					.startProcessInstanceByKey(wfOperator, "testBusinessId",
							"hndssh1", variables);

			wfOperator.setUserId("1");
			variables.put("usertask2Candidate", "1");
			wfRuntimeServiceLocal.operateTask(wfOperator,
					wfResult.getNextTaskId(), WfTaskAction.CLAIM_COMPLETE,
					null, variables);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testSuspendProcessInstance() {
		WfOperator wfOperator = new WfOperator(
				"E9E447507B7542E79C3ACB6F756D1FC9", "test", "test",
				"127.0.0.1", null);
		try {
			WfResult wfResult = wfRuntimeServiceLocal.suspendProcessInstance(
					wfOperator, "13A0BD1EFBD4425C80DA943171B8B2F9");
			Assert.assertTrue(WfConstants.WF_CONTROL_EXE_SUCCESS
					.equals(wfResult.getResult()));
			WfProcessInstance wfProcessInstance = wfRuntimeServiceLocal
					.getProcessInstanceById("13A0BD1EFBD4425C80DA943171B8B2F9");
			Assert.assertTrue(wfProcessInstance.isSuspended());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testActivateProcessInstance() {
		WfOperator wfOperator = new WfOperator(
				"E9E447507B7542E79C3ACB6F756D1FC9", "test", "test",
				"127.0.0.1", null);
		try {
			WfResult wfResult = wfRuntimeServiceLocal.activateProcessInstance(
					wfOperator, "E56635CE314345E086F67F90F1D6E7D2");
			Assert.assertTrue(WfConstants.WF_CONTROL_EXE_SUCCESS
					.equals(wfResult.getResult()));
			WfProcessInstance wfProcessInstance = wfRuntimeServiceLocal
					.getProcessInstanceById("E56635CE314345E086F67F90F1D6E7D2");
			Assert.assertTrue(!wfProcessInstance.isSuspended());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testQueryProcessInstances() {
		WfProcessInstanceParam wfProcessInstanceParam = new WfProcessInstanceParam();
		wfProcessInstanceParam.setId("FC4CF0109CAC42F4B7938ABE1CCDF190");
		try {
			WfPageList<WfProcessInstance, WfProcessInstanceParam> page = wfRuntimeServiceLocal
					.queryProcessInstances(wfProcessInstanceParam);
			assertTrue(page.getDatas().size() > 0);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetProcessInstanceDiagram() {
		try {
			InputStream is = wfRuntimeServiceLocal
					.getProcessInstanceDiagram("FC4CF0109CAC42F4B7938ABE1CCDF190");
			byte[] bt = CommonUtil.inputStreamToByte(is);
			assertTrue(bt != null && bt.length > 0);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetProcessInstanceCandidates() {
		//0AB11143B4E74097A64254796D1537DB
		try {
			 List<WfIdentityLink> is = wfRuntimeServiceLocal.getProcessInstanceCandidates("0AB11143B4E74097A64254796D1537DB");
			 if(is!=null&&is.size()>0){
				 for(WfIdentityLink wf:is){
					 System.out.println("参与用户："+wf.getUserId()+" 参与组："+wf.getGroupId());
				 }
			 }
			 assertTrue(is.size()>0);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testAddProcessInstanceCandidate() {
		//TODO
		try {
			WfOperator wfOperator = new WfOperator(
					"E9E447507B7542E79C3ACB6F756D1FC9", "test", "test",
					"127.0.0.1", null);
			 String is = wfRuntimeServiceLocal.addProcessInstanceCandidate(wfOperator, "0AB11143B4E74097A64254796D1537DB", WfConstants.WF_IDENTITYLINKTYPE_USERS, "");//.getProcessInstanceCandidates("0AB11143B4E74097A64254796D1537DB");
			 assertTrue("200".equals(is));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testDeleteProcessInstanceCandidate() {
		//TODO
		WfOperator wfOperator = new WfOperator(
				"E9E447507B7542E79C3ACB6F756D1FC9", "test", "test",
				"127.0.0.1", null);
		try {
			String result = wfRuntimeServiceLocal.deleteProcessInstanceCandidate(wfOperator, "0AB11143B4E74097A64254796D1537DB", WfConstants.WF_IDENTITYLINKTYPE_USERS, "");
			assertTrue("200".equals(result));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetProcessInstanceVariables() {
		try {
			Map<String, Object> map = wfRuntimeServiceLocal.getProcessInstanceVariables("0AB11143B4E74097A64254796D1537DB");
			System.out.println(map);
			WfBusinessData data = (WfBusinessData)map.get(WfConstants.WF_BUSINESS_DATA_KEY);
			assertTrue("70F30BF81F094FC08E557F196B9975C7".equals(data.getModuleId()));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetProcessInstanceVariableByName() {
		try {
			WfVariable v = wfRuntimeServiceLocal.getProcessInstanceVariableByName("0AB11143B4E74097A64254796D1537DB", WfConstants.WF_BUSINESS_DATA_KEY);
			System.out.println("111--"+v.getValue());
			Object o = v.getValue();
			assertTrue(o instanceof WfBusinessData);
		} catch (Exception e) {
			e.printStackTrace();

			fail(e.getMessage());
		}
		
	}

	@Test
	public void testAddVariableForProcessInstance() {
		WfOperator wfOperator = new WfOperator(
				"E9E447507B7542E79C3ACB6F756D1FC9", "test", "test",
				"127.0.0.1", null);
		WfVariable wfVariable = new WfVariable();
		wfVariable.setName("test");
		wfVariable.setType(VariableType.STRING.name());
		wfVariable.setValue("11");
		try {
			String result = wfRuntimeServiceLocal.addVariableForProcessInstance(wfOperator, "0AB11143B4E74097A64254796D1537DB", wfVariable);
			assertTrue("200".equals(result));
		} catch (Exception e) {
			e.printStackTrace();

			fail(e.getMessage());
		}
	}

	@Test
	public void testGetTaskById() {
		try {
			WfTask wfTask = wfRuntimeServiceLocal.getTaskById("203085420E0648E181CDDE3C04D47348");
			assertNotNull(wfTask);
		} catch (Exception e) {
			e.printStackTrace();

			fail(e.getMessage());
		}
	}

	@Test
	public void testQueryTasks() {
		try {
			WfTaskParam params = new WfTaskParam();
			params.setProcessInstanceId("0AB11143B4E74097A64254796D1537DB");
			WfPageList<WfTask, WfTaskParam> wfTaskPageList = wfRuntimeServiceLocal.queryTasks(params);
			assertTrue(wfTaskPageList.getDatas().size()>0);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testUpdateTask() {
		WfTask wfTask = new WfTask();
		wfTask.setTaskId("203085420E0648E181CDDE3C04D47348");
		wfTask.setDueDate(new Date());
		try {
			String result = wfRuntimeServiceLocal.updateTask(wfTask);
			assertTrue("200".equals(result));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	// 测试处理任务接口-完成
	@Test
	public void testOperateTaskCaseComplete() {
		WfOperator wfOperator = new WfOperator("1", "aa", "aa", "127.0.0.1",
				null);
		String taskId = "CE23E1D173F04672B829C12B343E5BEA";
		WfTaskAction action = WfTaskAction.COMPLETE;
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("task3User", "1");
		try {
			WfResult wfResult = wfRuntimeServiceLocal.operateTask(wfOperator,
					taskId, action, null, variables);
			Assert.assertEquals(wfResult.getResult(),
					WfConstants.WF_CONTROL_EXE_SUCCESS);
			System.out.println(wfResult.getNextTaskId() + "---"
					+ wfResult.getProcessInstanceId());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	// 测试处理任务接口-签收并完成
	@Test
	public void testOperateTaskCaseClaimComplete() {
		WfOperator wfOperator = new WfOperator("1", "cc", "cc", "127.0.0.1",
				null);
		String taskId = "295F174C9EE24E79B13479881415297C";
		WfTaskAction action = WfTaskAction.CLAIM_COMPLETE;
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("usertask3Candidate", '1');
		variables.put("audited", false);
		try {
			WfResult wfResult = wfRuntimeServiceLocal.operateTask(wfOperator,
					taskId, action, null, variables);
			Assert.assertEquals(wfResult.getResult(),
					WfConstants.WF_CONTROL_EXE_SUCCESS);
			System.out.println(wfResult.getNextTaskId() + "---"
					+ wfResult.getProcessInstanceId());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	// 测试处理任务接口-签收
	@Test
	public void testOperateTaskCaseClaim() {
		WfOperator wfOperator = new WfOperator(
				"E9E447507B7542E79C3ACB6F756D1FC9", "11", "111", "127.0.0.1",
				null);
		String taskId = "B18331ECC17A404AA5D98D2657A2B9C2";
		WfTaskAction action = WfTaskAction.CLAIM;
		Map<String, Object> variables = new HashMap<String, Object>();
		// variables.put("usertask2Candidate",
		// "C6DD42017E4C4B2A9BDE4133B2E2A208");
		try {
			WfResult wfResult = wfRuntimeServiceLocal.operateTask(wfOperator,
					taskId, action, null, variables);
			Assert.assertEquals(wfResult.getResult(),
					WfConstants.WF_CONTROL_EXE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	// 测试处理任务接口-委托
	@Test
	public void testOperateTaskCaseDelegate() {
		WfOperator wfOperator = new WfOperator(
				"E9E447507B7542E79C3ACB6F756D1FC9", "aa", "aa", "127.0.0.1",
				null);
		String taskId = "64EB855C44294A289CD19B9D8643EB34";
		WfTaskAction action = WfTaskAction.DELEGATE;
		String userToDelegateTo = "C6DD42017E4C4B2A9BDE4133B2E2A208";
		Map<String, Object> variables = new HashMap<String, Object>();
		try {
			WfResult wfResult = wfRuntimeServiceLocal.operateTask(wfOperator,
					taskId, action, userToDelegateTo, variables);
			Assert.assertEquals(wfResult.getResult(),
					WfConstants.WF_CONTROL_EXE_SUCCESS);

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	// 测试处理任务接口-回绝委托
	@Test
	public void testOperateTaskCaseResolve() {
		WfOperator wfOperator = new WfOperator(
				"C6DD42017E4C4B2A9BDE4133B2E2A208", "ss", "ss", "127.0.0.1",
				null);
		String taskId = "64EB855C44294A289CD19B9D8643EB34";
		WfTaskAction action = WfTaskAction.RESOLVE;
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("usertask3Candidate", "E9E447507B7542E79C3ACB6F756D1FC9");
		try {
			WfResult wfResult = wfRuntimeServiceLocal.operateTask(wfOperator,
					taskId, action, null, variables);
			Assert.assertEquals(wfResult.getResult(),
					WfConstants.WF_CONTROL_EXE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetTaskVariables() {
		try {
			Map<String, Object> map = wfRuntimeServiceLocal.getTaskVariables("203085420E0648E181CDDE3C04D47348", VariableScope.LOCAL);
			System.out.println(map);
			assertNotNull(map);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetTaskVariableByName() {
		try {
			Map<String, Object> map = wfRuntimeServiceLocal.getTaskVariableByName("203085420E0648E181CDDE3C04D47348", "task3User", VariableScope.GLOBAL);
			assertNotNull(map);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testAddTaskVariable() {
		WfOperator wfOperator = new WfOperator(
				"E9E447507B7542E79C3ACB6F756D1FC9", "test", "test",
				"127.0.0.1", null);
		WfVariable wfVariable = new WfVariable();
		wfVariable.setName("test1");
		wfVariable.setType(VariableType.STRING.name());
		wfVariable.setValue("112");
		wfVariable.setVariableScope(VariableScope.LOCAL);
		
		try {
			String result = wfRuntimeServiceLocal.addTaskVariable(wfOperator, "203085420E0648E181CDDE3C04D47348", wfVariable);
			assertTrue("200".equals(result));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testUpdateTaskVariable() {
		WfOperator wfOperator = new WfOperator(
				"E9E447507B7542E79C3ACB6F756D1FC9", "test", "test",
				"127.0.0.1", null);
		WfVariable wfVariable = new WfVariable();
		wfVariable.setName("test1");
		wfVariable.setType(VariableType.STRING.name());
		wfVariable.setValue("114");
		
		try {
			String result = wfRuntimeServiceLocal.updateTaskVariable(wfOperator, "203085420E0648E181CDDE3C04D47348", wfVariable);
			assertTrue("200".equals(result));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testDeleteTaskVariable() {
		WfOperator wfOperator = new WfOperator(
				"E9E447507B7542E79C3ACB6F756D1FC9", "test", "test",
				"127.0.0.1", null);
		try {
			String result = wfRuntimeServiceLocal.deleteTaskVariable(wfOperator, "203085420E0648E181CDDE3C04D47348", "test1", VariableScope.GLOBAL);
			assertTrue("200".equals(result));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testDeleteLocalTaskVariable() {
		WfOperator wfOperator = new WfOperator(
				"E9E447507B7542E79C3ACB6F756D1FC9", "test", "test",
				"127.0.0.1", null);
		try {
			String result = wfRuntimeServiceLocal.deleteLocalTaskVariable(wfOperator, "203085420E0648E181CDDE3C04D47348");
			assertTrue("200".equals(result));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetTaskCandidates() {
		try {
			List<WfIdentityLink> list = wfRuntimeServiceLocal.getTaskCandidates("203085420E0648E181CDDE3C04D47348");
			assertTrue(list.size()>0);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetTaskCandidatesByType() {
		try {
			List<WfIdentityLink> list = wfRuntimeServiceLocal.getTaskCandidatesByType("203085420E0648E181CDDE3C04D47348", WfConstants.WF_IDENTITYLINKTYPE_USERS);
			assertTrue(list.size()>0);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testAddTaskCandidate() {
		WfOperator wfOperator = new WfOperator(
				"E9E447507B7542E79C3ACB6F756D1FC9", "test", "test",
				"127.0.0.1", null);
		try {
			String result = wfRuntimeServiceLocal.addTaskCandidate(wfOperator, "203085420E0648E181CDDE3C04D47348", WfConstants.WF_IDENTITYLINKTYPE_USERS , "FE242CEF6D8943A48ADE5311CC4D84BA");
			assertTrue("200".equals(result));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testDeleteTaskCandidate() {
		WfOperator wfOperator = new WfOperator(
				"E9E447507B7542E79C3ACB6F756D1FC9", "test", "test",
				"127.0.0.1", null);
		try {
			String result = wfRuntimeServiceLocal.deleteTaskCandidate(wfOperator, "203085420E0648E181CDDE3C04D47348", WfConstants.WF_IDENTITYLINKTYPE_USERS, "FE242CEF6D8943A48ADE5311CC4D84BA");
			assertTrue("200".equals(result));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testAddTaskComment() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTaskComments() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTaskCommentById() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteTaskComment() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTaskEvents() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTaskEventById() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddTaskAttachment() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTaskAttachments() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTaskAttachmentById() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteTaskAttachmentById() {
		fail("Not yet implemented");
	}

	@Test
	public void testReject() {
		WfOperator wfOperator = new WfOperator("1", "admin", "admin",
				"127.0.0.1", null);
		String currenTaskId = "6C3E4CA063124AF88732F9AE1BEDA82D";
		String rejectMessage = "测试回退";
		Map<String, Object> variables = new HashMap<String, Object>();
		try {
			WfResult wfResult = wfRuntimeServiceLocal.reject(wfOperator,
					currenTaskId, rejectMessage, variables);
			Assert.assertEquals(wfResult.getResult(),
					WfConstants.WF_CONTROL_EXE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testGoAnyWhere() {
		WfOperator wfOperator = new WfOperator("1", "admin", "系统管理员",
				"127.0.0.1", null);
		boolean isStart = false;
		String bussinessKey = "";
		String processDefinitionId = null;
		String currenTaskId = "7322263C1F904383AA990A2A56E38418";
		String destTaskDefinitionKey = "cpTask";
		boolean useHisAssignee = true;
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("task1User", "1");
		try {
			WfResult wfResult = wfRuntimeServiceLocal.goAnyWhere(wfOperator,
					isStart, bussinessKey, processDefinitionId, currenTaskId,
					destTaskDefinitionKey, useHisAssignee, variables);
			Assert.assertEquals(wfResult.getResult(),
					WfConstants.WF_CONTROL_EXE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void teststartAnyWhere() {
		WfOperator wfOperator = new WfOperator("1", "admin", "系统管理员",
				"127.0.0.1", null);
		boolean isStart = true;
		String bussinessKey = "testBusinessKey";
		String processDefinitionId = "hndsdjpd:9:32703F2BB6904B2391371D1A9893C7F3";
		String currenTaskId = null;
		String destTaskDefinitionKey = "qxslTask";
		boolean useHisAssignee = true;
		Map<String, Object> variables = new HashMap<String, Object>();
		try {
			variables.put("task3User", "1");
			WfResult wfResult = wfRuntimeServiceLocal.goAnyWhere(wfOperator,
					isStart, bussinessKey, processDefinitionId, currenTaskId,
					destTaskDefinitionKey, useHisAssignee, variables);
			Assert.assertEquals(wfResult.getResult(),
					WfConstants.WF_CONTROL_EXE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetNextUserTask() {
		try {
			List<WfActivity> wfas = wfRuntimeServiceLocal
					.getNextUserTaskDefByTaskId("6BB0B8FA1A614D14A191BDCFF4A28958");
			System.out.println(wfas.size());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
