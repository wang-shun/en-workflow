package com.chinacreator.c2.flow.rest.deploy;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.repository.Deployment;

import com.chinacreator.c2.flow.rest.common.WfAbstractPaginateList;
import com.chinacreator.c2.flow.rest.common.vo.WfDeploymentResponse;

public class DeploymentsPaginateList extends WfAbstractPaginateList<WfDeploymentResponse>  {

  
  @SuppressWarnings("rawtypes")
  @Override
  protected List<WfDeploymentResponse> processList(List list) {
    List<WfDeploymentResponse> responseList = new ArrayList<WfDeploymentResponse>();
    for (Object deployment : list) {
    	Deployment dep=(Deployment) deployment;
    	responseList.add(new WfDeploymentResponse(dep));
    }
    return responseList;
  }
}
