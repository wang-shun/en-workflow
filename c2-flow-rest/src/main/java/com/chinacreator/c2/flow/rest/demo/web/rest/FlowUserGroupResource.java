package com.chinacreator.c2.flow.rest.demo.web.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.springframework.stereotype.Service;

import com.chinacreator.c2.flow.detail.ChooseGroup;
import com.chinacreator.c2.flow.util.WfUtils;

@Service
@Path("v1/flow/demo/users")
public class FlowUserGroupResource {
	
	@GET
	@Path("/{userId}/groups")
	public List<ChooseGroup> getGroupByCurrentUser(@PathParam("userId") String userId){
		List<ChooseGroup> candidateGroupList=WfUtils.getGroupsByUserId(userId);
		return candidateGroupList;
	}
	
}
