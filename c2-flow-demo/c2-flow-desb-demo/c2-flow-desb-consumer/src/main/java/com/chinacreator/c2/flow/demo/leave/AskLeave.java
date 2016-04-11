package com.chinacreator.c2.flow.demo.leave;

import java.io.Serializable;

import com.chinacreator.c2.annotation.Column;
import com.chinacreator.c2.annotation.ColumnType;
import com.chinacreator.c2.annotation.Entity;

/**
 * 请假
 * @author 
 * @generated
 */
@Entity(id = "entity:com.chinacreator.c2.flow.demo.leave.AskLeave", table = "wf_demo_ask_leave", ds = "c2-flow-demo1")
public class AskLeave implements Serializable {
	private static final long serialVersionUID = 1615295579521024L;
	/**
	 *
	 */
	@Column(id = "id", type = ColumnType.uuid, datatype = "string128")
	private java.lang.String id;

	/**
	 *用户
	 */
	@Column(id = "ask_user_id", datatype = "string128")
	private java.lang.String askUserId;

	/**
	 *申请时间
	 */
	@Column(id = "create_time", datatype = "timestamp")
	private java.sql.Timestamp createTime;

	/**
	 *开始时间
	 */
	@Column(id = "ask_start_time", datatype = "timestamp")
	private java.sql.Timestamp askStartTime;

	/**
	 *结束时间
	 */
	@Column(id = "ask_end_time", datatype = "timestamp")
	private java.sql.Timestamp askEndTime;

	/**
	 *请假类型
	 */
	@Column(id = "ask_type", datatype = "string1024")
	private java.lang.String askType;

	/**
	 *指派给
	 */
	@Column(id = "assign_to", datatype = "string128")
	private java.lang.String assignTo;

	/**
	 *请假天数
	 */
	@Column(id = "ask_days", datatype = "int")
	private java.lang.Integer askDays;

	/**
	 *请假原因
	 */
	@Column(id = "ask_reasons", datatype = "string2000")
	private java.lang.String askReasons;

	/**
	 *状态
	 */
	@Column(id = "status", datatype = "int")
	private java.lang.Integer status;

	/**
	 * 设置
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}

	/**
	 * 获取
	 */
	public java.lang.String getId() {
		return id;
	}

	/**
	 * 设置用户
	 */
	public void setAskUserId(java.lang.String askUserId) {
		this.askUserId = askUserId;
	}

	/**
	 * 获取用户
	 */
	public java.lang.String getAskUserId() {
		return askUserId;
	}

	/**
	 * 设置申请时间
	 */
	public void setCreateTime(java.sql.Timestamp createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取申请时间
	 */
	public java.sql.Timestamp getCreateTime() {
		return createTime;
	}

	/**
	 * 设置开始时间
	 */
	public void setAskStartTime(java.sql.Timestamp askStartTime) {
		this.askStartTime = askStartTime;
	}

	/**
	 * 获取开始时间
	 */
	public java.sql.Timestamp getAskStartTime() {
		return askStartTime;
	}

	/**
	 * 设置结束时间
	 */
	public void setAskEndTime(java.sql.Timestamp askEndTime) {
		this.askEndTime = askEndTime;
	}

	/**
	 * 获取结束时间
	 */
	public java.sql.Timestamp getAskEndTime() {
		return askEndTime;
	}

	/**
	 * 设置请假类型
	 */
	public void setAskType(java.lang.String askType) {
		this.askType = askType;
	}

	/**
	 * 获取请假类型
	 */
	public java.lang.String getAskType() {
		return askType;
	}

	/**
	 * 设置指派给
	 */
	public void setAssignTo(java.lang.String assignTo) {
		this.assignTo = assignTo;
	}

	/**
	 * 获取指派给
	 */
	public java.lang.String getAssignTo() {
		return assignTo;
	}

	/**
	 * 设置请假天数
	 */
	public void setAskDays(java.lang.Integer askDays) {
		this.askDays = askDays;
	}

	/**
	 * 获取请假天数
	 */
	public java.lang.Integer getAskDays() {
		return askDays;
	}

	/**
	 * 设置请假原因
	 */
	public void setAskReasons(java.lang.String askReasons) {
		this.askReasons = askReasons;
	}

	/**
	 * 获取请假原因
	 */
	public java.lang.String getAskReasons() {
		return askReasons;
	}

	/**
	 * 设置状态
	 */
	public void setStatus(java.lang.Integer status) {
		this.status = status;
	}

	/**
	 * 获取状态
	 */
	public java.lang.Integer getStatus() {
		return status;
	}
}
