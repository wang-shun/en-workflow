package com.chinacreator.c2.flow.detail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 统一任务结果集，包括需要显示的字段信息，分页信息，查询结果等
 * 
 * @author minghua.guo
 * 
 */
public class WfUniteTaskResult implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 应用ID
	 */
	private String appId;
	/**
	 * 租户ID
	 */
	private String tenantId;
	/**
	 * 引擎名称
	 */
	private String engineName;
	/**
	 * 任务列表类型
	 */
	private String taskList;

	/**
	 * 是否只显示标题
	 */
	private boolean onlyTitle = false;

	/**
	 * 分页：起始记录数
	 */
	private int firstResult = 0;

	/**
	 * 分页：每页最大记录数
	 */
	private int maxResults = 10;

	/**
	 * 分页：总记录数
	 */
	private int totalResults = 0;

	/**
	 * 字段描述信息
	 */
	private List<WfUniteColumn> columns = new ArrayList<WfUniteColumn>();

	/**
	 * 显示列
	 */
	private List<String> showColumnIds = new ArrayList<String>();

	/**
	 * 隐藏列
	 */
	private List<String> hideColumnIds = new ArrayList<String>();

	/**
	 * 列排序
	 */
	private List<Integer> remapColumns = new ArrayList<Integer>();

	/**
	 * 数据集
	 */
	private List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getEngineName() {
		return engineName;
	}

	public void setEngineName(String engineName) {
		this.engineName = engineName;
	}

	public String getTaskList() {
		return taskList;
	}

	public void setTaskList(String taskList) {
		this.taskList = taskList;
	}

	public boolean isOnlyTitle() {
		return onlyTitle;
	}

	public void setOnlyTitle(boolean onlyTitle) {
		this.onlyTitle = onlyTitle;
	}

	public int getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}

	public int getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

	public int getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}

	public List<WfUniteColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<WfUniteColumn> columns) {
		this.columns = columns;
	}

	public List<Map<String, Object>> getDatas() {
		return datas;
	}

	public void setDatas(List<Map<String, Object>> datas) {
		this.datas = datas;
	}

	public List<String> getShowColumnIds() {
		if (columns != null) {
			for (WfUniteColumn column : columns) {
				if (column.getIsShow() == 1) {
					showColumnIds.add(column.columnId);
				}
			}
		}
		return showColumnIds;
	}

	public void setShowColumnIds(List<String> showColumnIds) {
		this.showColumnIds = showColumnIds;
	}

	public List<String> getHideColumnIds() {
		if (columns != null) {
			for (WfUniteColumn column : columns) {
				if (column.getIsShow() != 1) {
					hideColumnIds.add(column.columnId);
				}
			}
		}
		return hideColumnIds;
	}

	public void setHideColumnIds(List<String> hideColumnIds) {
		this.hideColumnIds = hideColumnIds;
	}

	public List<Integer> getRemapColumns() {
		if(getShowColumnIds() != null){
			//操作
			remapColumns.add(0);
//			int i = 1;
			for(String showColumId : showColumnIds){
				remapColumns.add(columnDefaultSN.get(showColumId));
//				columnDefaultSN.put(showColumId, i);
//				i++;
			}
			for(String hideColumId : hideColumnIds){
				remapColumns.add(columnDefaultSN.get(hideColumId));
//				columnDefaultSN.put(hideColumId, i);
//				i++;
			}
			//标题
			remapColumns.add(27);
		}
		
		return remapColumns;
	}
	
	private static Map<String, Integer> columnDefaultSN = new HashMap<String, Integer>(){
		private static final long serialVersionUID = 1L;

		{
			put("id", 1);
			put("taskid", 2);
			put("businesskey", 3);
			put("executionid", 4);
			put("procinstid", 5);
			put("procdefid", 6);
			put("name", 7);
			put("parenttaskid", 8);
			put("description", 9);
			put("taskdefkey", 10);
			put("owner", 11);
			put("assignee", 12);
			put("candidate", 13);
			put("delegation", 14);
			put("priority", 15);
			put("createtime", 16);
			put("endtime", 17);
			put("duedate", 18);
			put("category", 19);
			put("taskstate", 20);
			put("tenantid", 21);
			put("appid", 22);
			put("moduleid", 23);
			put("modulename", 24);
			put("deletereason", 25);
			put("formkey", 26);
			put("revision", 28);
			put("remark1", 29);
			put("remark2", 30);
			put("remark3", 31);
			put("remark4", 32);
			put("remark5", 33);
			put("remark6", 34);
			put("remark7", 35);
			put("remark8", 36);
			put("remark9", 37);
			put("remark10", 38);
		}
	};

	public void setRemapColumns(List<Integer> remapColumns) {
		this.remapColumns = remapColumns;
	}
}
