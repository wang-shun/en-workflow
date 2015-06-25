package com.chinacreator.c2.flow.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.InputStream;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.chinacreator.c2.flow.api.WfRepositoryService;
import com.chinacreator.c2.flow.detail.WfConstants;
import com.chinacreator.c2.flow.detail.WfDeployment;
import com.chinacreator.c2.flow.detail.WfDeploymentParam;
import com.chinacreator.c2.flow.detail.WfIdentityLink;
import com.chinacreator.c2.flow.detail.WfOperator;
import com.chinacreator.c2.flow.detail.WfPageList;
import com.chinacreator.c2.flow.detail.WfProcessDefinition;
import com.chinacreator.c2.flow.detail.WfProcessDefinitionParam;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = false, transactionManager = "transactionManager")
@ContextConfiguration(locations = { "classpath:custom/c2-flow-core-test.xml" })
public class WfRepositoryServiceImplTest {
	/*
	 * static ApplicationContext applicationContext; WfRepositoryService
	 * wfRepositoryServiceLocal;
	 */

	/*
	 * @BeforeClass public static void setUpBeforeClass() throws Exception {
	 * String[] configFile = { "custom/c2-flow-core-application.xml",
	 * "custom/c2-flow-core-context.xml" }; applicationContext = new
	 * ClassPathXmlApplicationContext(configFile); }
	 * 
	 * @Before public void setUp() throws Exception { wfRepositoryServiceLocal =
	 * (WfRepositoryService) applicationContext
	 * .getBean("WfRepositoryServiceLocal"); }
	 * 
	 * @After public void tearDown() throws Exception { wfRepositoryServiceLocal
	 * = null; }
	 */

	@Autowired
	private WfRepositoryService wfRepositoryServiceLocal;

	@Test
	public void testInit() {
		Assert.assertNotNull(wfRepositoryServiceLocal);
	}

	@Test
	public void testDeployDiagramClassPath() {
		WfOperator wfOperator = new WfOperator("1", "admin", "系统管理员",
				"127.0.0.1", null);

		try {
			wfRepositoryServiceLocal.deployDiagramClassPath(wfOperator,
					"hndssh", "test", "diagram/hndssh.bpmn");
		} catch (Exception e) {
			e.printStackTrace();
			fail("testDeployDiagramClassPath方法发生报错");
		}
	}

	@Test
	public void testDeployDiagramContent() {
		WfOperator wfOperator = new WfOperator("1", "admin", "系统管理员",
				"127.0.0.1", null);
		InputStream in = ClassLoader
				.getSystemResourceAsStream("diagram/hndssh.bpmn");
		String resourceContent = inputStreamToString(in, "utf-8");
		try {
			wfRepositoryServiceLocal.deployDiagramContent(wfOperator, "hndssh",
					"test", "hndssh", resourceContent);
		} catch (Exception e) {
			e.printStackTrace();
			fail("testDeployDiagramContent方法发生报错");
		}
	}

	private String inputStreamToString(InputStream is, String encoding) {
		try {
			byte[] b = new byte[1024];
			String res = "";
			if (is == null) {
				return "";
			}

			int bytesRead = 0;
			while (true) {
				bytesRead = is.read(b, 0, 1024); // return final read bytes
													// counts
				if (bytesRead == -1) {// end of InputStream
					return res;
				}
				res += new String(b, 0, bytesRead, encoding); // convert to
																// string using
																// bytes
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("Exception: " + e);
			return "";
		}
	}

	@Test
	public void testDeployDiagramZip() {
		WfOperator wfOperator = new WfOperator("1", "admin", "系统管理员",
				"127.0.0.1", null);
		InputStream in = ClassLoader
				.getSystemResourceAsStream("diagram/leave.bar");

		try {
			wfRepositoryServiceLocal.deployDiagramZip(wfOperator, "leave",
					"testLeave", in);
		} catch (Exception e) {
			e.printStackTrace();
			fail("testDeployDiagramZip方法发生报错");
		}
	}

	@Test
	public void testQueryDeployments() {
		try {
			WfDeploymentParam queryParams = new WfDeploymentParam();
			queryParams.setNameLike("%leave%");
			WfPageList<WfDeployment, WfDeploymentParam> wfPageList = wfRepositoryServiceLocal
					.queryDeployments(queryParams);
			assertTrue(wfPageList.getDatas().size() > 0);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void testGetDeploymentById() {
		// fail("Not yet implemented");
		try {
			WfDeployment wfDeployment = wfRepositoryServiceLocal
					.getDeploymentById("0EE3C2D0167746E18D852FC187FFC1B2");
			assertTrue("leave".equals(wfDeployment.getName()));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void testQueryResourcesByDeploymentId() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDeploymentResourceById() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryProcessDefinitions() {
		WfProcessDefinitionParam wfProcessDefinitionParam = new WfProcessDefinitionParam();
		wfProcessDefinitionParam.setKey("testProcess1");
		try {
			WfPageList<WfProcessDefinition, WfProcessDefinitionParam> page = wfRepositoryServiceLocal.queryProcessDefinitions(wfProcessDefinitionParam);
			assertTrue(page.getDatas().size()>0);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void testGetProcessDefinition() {
		try {
			WfProcessDefinition wfp = wfRepositoryServiceLocal
					.getProcessDefinition("leave:1:12954BCBF55247E298E616B0FA78977E");
			assertTrue("leave".equals(wfp.getKey()));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void testGetProcessDefinitionBPMN() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteDeploymentsById() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateProcessDefinitionCategory() {
		fail("Not yet implemented");
	}

	@Test
	public void testSuspendProcessDefinition() {
		WfOperator wfOperator = new WfOperator("1", "admin", "系统管理员",
				"127.0.0.1", null);
		try {
			String success = wfRepositoryServiceLocal.suspendProcessDefinition(
					wfOperator, "leave:1:12954BCBF55247E298E616B0FA78977E");
			WfProcessDefinition wfp = wfRepositoryServiceLocal
					.getProcessDefinition("leave:1:12954BCBF55247E298E616B0FA78977E");
			boolean suspended = wfp.isSuspended();
			assertTrue(WfConstants.WF_CONTROL_EXE_SUCCESS.equals(success)
					&& suspended);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void testActivateProcessDefinition() {
		WfOperator wfOperator = new WfOperator("1", "admin", "系统管理员",
				"127.0.0.1", null);
		try {
			String success = wfRepositoryServiceLocal
					.activateProcessDefinition(wfOperator,
							"leave:1:12954BCBF55247E298E616B0FA78977E");
			WfProcessDefinition wfp = wfRepositoryServiceLocal
					.getProcessDefinition("leave:1:12954BCBF55247E298E616B0FA78977E");
			boolean suspended = wfp.isSuspended();
			assertTrue(WfConstants.WF_CONTROL_EXE_SUCCESS.equals(success)
					&& !suspended);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void testGetProcessDefinitionCandidates() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteProcessDefinitionCandidate() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryModels() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetModelById() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddModel() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateModel() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteModelsById() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetModelEditorSource() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveModelEditorSource() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetModelEditorSourceExtra() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveModelEditorSourceExtra() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddProcessDefinitionCandidate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNextActivityId(){
		String processDefinitionId = "testlc1:1:5F2E25E755C449EE8CB6CD88D3DBC474";
		String currenTaskDefId = "task1";
		try {
			String nextActivityId = wfRepositoryServiceLocal.getNextActivityId(processDefinitionId, currenTaskDefId);
			System.out.println("nextActivityId==============================" + nextActivityId);
			Assert.assertNotNull(nextActivityId);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}
}
