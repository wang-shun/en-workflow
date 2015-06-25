package com.chinacreator.c2.flow.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.chinacreator.c2.flow.api.WfManagerService;
import com.chinacreator.c2.flow.detail.WfHoliday;
import com.chinacreator.c2.flow.detail.WfPageList;
import com.chinacreator.c2.flow.detail.WfProcessDefinition;
import com.chinacreator.c2.flow.detail.WfWorkDate;
import com.chinacreator.c2.flow.detail.WfWorkDateParam;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = false, transactionManager = "transactionManager")
@ContextConfiguration(locations = { "classpath:custom/c2-flow-core-test.xml" })
public class WfManageServiceImplTest {
	
	@Autowired
	private WfManagerService wfManagerService;
	
	

	@Test
	public void testInit() {
		Assert.assertNotNull(wfManagerService);
	}
	
	@Test
	public void testGetBindProcessByModuleId(){
		String a = "70F30BF81F094FC08E557F196B9975C7";
		WfProcessDefinition wpd = null;
		try {
			wpd = wfManagerService.getBindProcessByModuleId(a);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		Assert.assertNotNull(wpd);
	}
	
	@Test
	public void testBindProcessToModule(){
		String moduleId= "111";
		WfProcessDefinition wpd = new WfProcessDefinition();
		wpd.setId("22");
		wpd.setKey("22");
		wpd.setName("2233");
		try {
			wfManagerService.bindProcessToModule(moduleId, wpd);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testAddHoliday(){
		WfHoliday h = new WfHoliday();
		h.setHoliday("2");
		h.setMHoliday("2");
		h.setMHoliday("2");
		h.setTenantId("2");
		try {
			wfManagerService.addHoliday(h);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}
	
	@Test
	public void testDeleteHoliday(){
		WfHoliday h = new WfHoliday();
		h.setHoliday("1");
		h.setMHoliday("1");
		h.setMHoliday("1");
		h.setTenantId("1");
		try {
			wfManagerService.deleteHoliday(h);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}
	
	@Test
	public void testGetHolidayList(){
		WfHoliday h = new WfHoliday();
		h.setTenantId("1");
		try {
			List<WfHoliday> wfHList = wfManagerService.getHolidayList(h);
			assertTrue(wfHList.size()>0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddWorkDate(){
		WfWorkDate wfWorkDate = new WfWorkDate();
		wfWorkDate.setAmBeginTime("8:30");
		wfWorkDate.setAmEndTime("12:00");
		wfWorkDate.setBeginDate("2016-01-01");
		wfWorkDate.setEndDate("2016-06-30");
		wfWorkDate.setLastModifyTime(new Date());
		wfWorkDate.setPmBeginTime("13:30");
		wfWorkDate.setPmEndTime("17:30");
		wfWorkDate.setRemark("yangyicheng test");
		wfWorkDate.setTenantId("1");
		try {
			wfManagerService.addWorkDate(wfWorkDate);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}
	
	@Test
	public void testSetWorkDate(){
		WfWorkDate wfWorkDate = new WfWorkDate();
		wfWorkDate.setAmBeginTime("8:30");
		wfWorkDate.setAmEndTime("12:00");
		wfWorkDate.setBeginDate("2016-01-01");
		wfWorkDate.setEndDate("2016-06-30");
		wfWorkDate.setLastModifyTime(new Date());
		wfWorkDate.setPmBeginTime("13:30");
		wfWorkDate.setPmEndTime("17:30");
		wfWorkDate.setRemark("yangyicheng test");
		wfWorkDate.setTenantId("1");
		wfWorkDate.setWorkId("A081D967B53840DEB66B509624651C6F");
		try {
			wfManagerService.setWorkDate(wfWorkDate);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testDeleteWorkDate(){
		WfWorkDate wfWorkDate = new WfWorkDate();
		wfWorkDate.setWorkId("A081D967B53840DEB66B509624651C6F");
		try {
			wfManagerService.deleteWorkDate(wfWorkDate);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetWorkDateList(){
		WfWorkDateParam wfWorkDateParam = new WfWorkDateParam();
		wfWorkDateParam.setPaged(true);
		wfWorkDateParam.setSize(2);
		
		try {
			WfPageList<WfWorkDate, WfWorkDateParam> wfPageList = wfManagerService.getWorkDateList(wfWorkDateParam);
			assertTrue(wfPageList.getWfQuery().getTotal()>0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetDueDateAfterExecute(){
		try {
			long s = System.currentTimeMillis();
			Date date = wfManagerService.getDueDateAfterExecute(new Date(), 30, "D", null);
			long de = System.currentTimeMillis();
			System.out.println("feishi -->"+(de-s));
			System.out.println(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetDueDateAfterExecute2(){
		try {
			long s = System.currentTimeMillis();
			Date date = wfManagerService.getDueDateAfterExecute(new Date(), 2, "h", null);
			long de = System.currentTimeMillis();
			System.out.println("feishi -->"+(de-s));
			System.out.println(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}