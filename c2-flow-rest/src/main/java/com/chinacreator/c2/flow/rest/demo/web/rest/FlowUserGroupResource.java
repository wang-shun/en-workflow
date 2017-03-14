package com.chinacreator.c2.flow.rest.demo.web.rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import org.activiti.engine.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.chinacreator.c2.flow.detail.ChooseGroup;
import com.chinacreator.c2.flow.util.WfUtils;

@Controller
@Path("v1/flow/demo/users")
public class FlowUserGroupResource {
	
	@Autowired
	private ManagementService managementService;
	
	@GET
	@Path("/{userId}/groups")
	public List<ChooseGroup> getGroupByCurrentUser(@PathParam("userId") String userId){
		List<ChooseGroup> candidateGroupList=WfUtils.getGroupsByUserId(userId);
		return candidateGroupList;
	}
	
	@GET
	@Path("/ttt")
	public void getGroupByCurrentUser(@Context ServletContext context){
		
		System.out.println(context.getClass().getName());
		System.out.println(context.getClass());
	}
	
	
	@GET
	@Path("/insertTest")
	public void insertWfTaskTest(){
//		final IdGenerator idGenerator=new PKGenerator();
//		for(int i=0;i<50;i++){
//			final int num=i;
//			new Thread() {
//				public void run() {
//					try{
//						WfUniteRunTaskEntity wfUniteRunTask=new WfUniteRunTaskEntity();
//						wfUniteRunTask.setId(idGenerator.getNextId());
//						wfUniteRunTask.setTaskId("ok");
//						wfUniteRunTask.setAssignee("aaaaaaa");
//						wfUniteRunTask.setDescription(Thread.currentThread().getName());
//						WfUniteRunTaskEntity wfUniteRunTaskEntity= managementService.executeCommand(new InsertWfUniteRunTaskCmd(wfUniteRunTask));
//					}catch(Exception e){
//						e.printStackTrace();
//					}
//				}
//			}.start();
//			
//		}

	}
	
}