package com.chinacreator.c2.flow.util;

import java.util.List;

import com.chinacreator.c2.flow.api.GroupType;

public class WfConfig {
	
	private List<GroupType> groupTypes; //自定义群组类型
	
	private GroupType defaultGroupType; //默认组类型，主要兼容act_id_group视图旧数据处理组的来源是角色或岗位等

	public List<GroupType> getGroupTypes() {
		return groupTypes;
	}

	public void setGroupTypes(List<GroupType> groupTypes) {
		this.groupTypes = groupTypes;
	}

	public GroupType getDefaultGroupType() {
		return defaultGroupType;
	}

	public void setDefaultGroupType(GroupType defaultGroupType) {
		this.defaultGroupType = defaultGroupType;
	}

	
}
