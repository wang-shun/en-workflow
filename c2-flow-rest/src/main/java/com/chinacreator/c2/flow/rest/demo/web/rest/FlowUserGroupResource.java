package com.chinacreator.c2.flow.rest.demo.web.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.activiti.engine.ManagementService;
import org.activiti.engine.impl.cfg.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinacreator.c2.flow.cmd.unitetask.InsertWfUniteRunTaskCmd;
import com.chinacreator.c2.flow.detail.ChooseGroup;
import com.chinacreator.c2.flow.persistence.entity.WfUniteRunTaskEntity;
import com.chinacreator.c2.flow.util.PKGenerator;
import com.chinacreator.c2.flow.util.WfUtils;

@Service
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
	@Path("/insertTest")
	public void insertWfTaskTest(){
		final IdGenerator idGenerator=new PKGenerator();
		for(int i=0;i<50;i++){
			final int num=i;
			new Thread() {
				public void run() {
					try{
						WfUniteRunTaskEntity wfUniteRunTask=new WfUniteRunTaskEntity();
						wfUniteRunTask.setId(idGenerator.getNextId());
						wfUniteRunTask.setTaskId("ok");
						wfUniteRunTask.setAssignee("aaaaaaa");
						wfUniteRunTask.setDescription(Thread.currentThread().getName());
						WfUniteRunTaskEntity wfUniteRunTaskEntity= managementService.executeCommand(new InsertWfUniteRunTaskCmd(wfUniteRunTask));
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}.start();
			
		}

	}
	
}
