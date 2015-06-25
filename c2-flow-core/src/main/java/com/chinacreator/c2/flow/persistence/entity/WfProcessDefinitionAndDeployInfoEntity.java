package com.chinacreator.c2.flow.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.impl.db.PersistentObject;

public class WfProcessDefinitionAndDeployInfoEntity implements PersistentObject, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	  
	private String id;

	private Integer rev;
	private String category;
	private String name;
	private String key;
	private Integer version;
	private String deploymentId;
	private String resourceName;
	private String dgrmResourceName;
	private String description;
	private Boolean hasStartFormKey;
	private Boolean suspensionState;
	private String tenantId;
	private Date deployTime;
	
	public Integer getRev() {
		return rev;
	}

	public void setRev(Integer rev) {
		this.rev = rev;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getDgrmResourceName() {
		return dgrmResourceName;
	}

	public void setDgrmResourceName(String dgrmResourceName) {
		this.dgrmResourceName = dgrmResourceName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	public Boolean getHasStartFormKey() {
		return hasStartFormKey;
	}

	public void setHasStartFormKey(Boolean hasStartFormKey) {
		this.hasStartFormKey = hasStartFormKey;
	}


	public Boolean getSuspensionState() {
		return suspensionState;
	}

	public void setSuspensionState(Boolean suspensionState) {
		this.suspensionState = suspensionState;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public Date getDeployTime() {
		return deployTime;
	}

	public void setDeployTime(Date deployTime) {
		this.deployTime = deployTime;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public Object getPersistentState() {
		Map<String, Object> persistentState = new HashMap<String, Object>();

		persistentState.put("id", id);
		persistentState.put("rev", rev);
		persistentState.put("category", category);
		persistentState.put("name", name);
		persistentState.put("key", key);
		persistentState.put("version", version);
		persistentState.put("deploymentId", deploymentId);
		persistentState.put("resourceName", resourceName);
		persistentState.put("dgrmResourceName", dgrmResourceName);
		persistentState.put("description", description);
		persistentState.put("hasStartFormKey", hasStartFormKey);
		persistentState.put("suspensionState", suspensionState);
		persistentState.put("tenantId", tenantId);
		persistentState.put("deployTime", deployTime);
		
		
		return persistentState;
	}

}
