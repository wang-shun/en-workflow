package com.chinacreator.c2.flow.rest.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="WfBaseRequest")
public class WfBaseRequest {
	
	//当前操作用户入参，建议在没sso环境联调时使用
	@ApiModelProperty(value="模拟当前登陆用户，需要后台启用开关(当流程独立部署且未集成sso环境时调试使用)",required=false)
	private String currentLoginUserId;
	
	public String getCurrentLoginUserId() {
		return currentLoginUserId;
	}

	public void setCurrentLoginUserId(String currentLoginUserId) {
		this.currentLoginUserId = currentLoginUserId;
	}
	
	
}
