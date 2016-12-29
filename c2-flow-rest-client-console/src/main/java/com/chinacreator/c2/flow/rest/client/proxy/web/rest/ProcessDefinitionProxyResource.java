package com.chinacreator.c2.flow.rest.client.proxy.web.rest;

import io.swagger.annotations.ApiParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.chinacreator.c2.config.ConfigManager;
import com.chinacreator.c2.flow.rest.ApiClient;
import com.chinacreator.c2.flow.rest.ApiException;
import com.chinacreator.c2.flow.rest.api.RepositoryDefinitionApi;
import com.chinacreator.c2.web.exception.ResourceNotFoundException;
import com.chinacreator.c2.web.exception.UnkownException;

/**
 * 流程定义
 * 
 * @author hushow
 * 
 */
@Service
@Path("workflow/service/process-definition")
public class ProcessDefinitionProxyResource {
	
	public ProcessDefinitionProxyResource() {
		// TODO Auto-generated constructor stub
		System.out.println("ttttttttttt");
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("{processDefinitionId}/model")
	  public Object  getModelResource(@ApiParam(value = "流程定义id") @PathParam("processDefinitionId") String processDefinitionId) throws Exception{
		try{
			String bathPath=ConfigManager.getInstance().getConfig("c2.flow.console.basePath");
			ApiClient apiClient=new ApiClient();
			apiClient.setBasePath(bathPath);
			RepositoryDefinitionApi repositoryDefinitionApi=new RepositoryDefinitionApi(apiClient);
			return repositoryDefinitionApi.getModelResource(processDefinitionId);
		} catch (ApiException e) {
			if(404==e.getCode()){
				throw new ResourceNotFoundException(e.getMessage());
			}else{
				e.printStackTrace();
				throw new UnkownException("动态路由api调用异常："+e.getMessage());
			}
		}catch (Exception e) {
			throw new UnkownException(e.getMessage(), e);
		}
	}
	
	@GET
	@Produces({"text/javascript"})
	@Path("{processDefinitionId}/diagram-layout")
	  public String  getDiagramLayout(@ApiParam(value = "流程定义id") @PathParam("processDefinitionId") String processDefinitionId,@QueryParam("callback") String callback) throws Exception{
		
		try{
			
			String bathPath=ConfigManager.getInstance().getConfig("c2.flow.console.basePath");
			ApiClient apiClient=new ApiClient();
			apiClient.setBasePath(bathPath);
			RepositoryDefinitionApi repositoryDefinitionApi=new RepositoryDefinitionApi(apiClient);
			String diagramLayoutStr=repositoryDefinitionApi.getDiagramLayout(processDefinitionId);
			return ClientUtils.filterSuccessJsonp(callback, diagramLayoutStr);
		} catch (ApiException e) {
			if(404==e.getCode()){
				return ClientUtils.filterErrorJsonp(callback,e.getCode(),"找不到流程定义："+processDefinitionId);
			}else{
				e.printStackTrace();
				return ClientUtils.filterErrorJsonp(callback,e.getCode(),"远程获取流程定义信息失败，请联系管理员");
			}
		}catch (Exception e) {
			e.printStackTrace();
			return ClientUtils.filterErrorJsonp(callback,500,"获取流程定义信息异常["+e.getMessage()+"]，请联系管理员");
		}

	}
}
