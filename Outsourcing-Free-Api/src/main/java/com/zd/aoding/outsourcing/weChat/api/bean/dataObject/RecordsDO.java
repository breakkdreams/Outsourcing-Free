package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import com.zd.aoding.base.DO.base.RecordBase;

/**
 * @ClassName: NormalRecordMapper
 * @Description: 通用一般记录
 * @author: Administrator
 * @date: 2016年12月19日 上午10:44:58
 */
public class RecordsDO extends RecordBase  {

	/*** 记录类型 ***/
	/**
	 * @fieldName: NORMALTYPE_MANAGER_UPDATEINFO 管理员更新一般信息
	 */
	public final static int RECORDTYPE_MANAGER_UPDATE = 8;
	/**
	 * @fieldName: NORMALTYPE_MANAGER_UPDATEINFO 管理员查看一般信息
	 */
	public final static int RECORDTYPE_MANAGER_VIEW = 9;
	/**
	 * @fieldName: NORMALTYPE_MANAGER_UPDATEINFO 管理员添加一般信息
	 */
	public final static int RECORDTYPE_MANAGER_ADD = 10;
	/**
	 * @fieldName: NORMALTYPE_MANAGER_UPDATEINFO 管理员删除一般信息
	 */
	public final static int RECORDTYPE_MANAGER_DELETED = 11;
	
	private Integer recordType;// 记录类型
	/** 描述总结 */
	private String description;// 概述
	/** 固有描述（最精简描述或统一描述描述） */
	private String descriptionStand;

	
	public RecordsDO() {
		super();
	}

	public RecordsDO(Integer lever, String cause, String result, Integer recordType, Integer dealType, Integer dealId,
			Integer beDealedId, String description, String descriptionStand) {
		super();
		this.lever = lever;
		this.cause = cause;
		this.result = result;
		this.dealType = dealType;
		this.dealId = dealId;
		this.beDealedId = beDealedId;
		this.recordType = recordType;
		this.description = description;
		this.descriptionStand = descriptionStand;

	}

	public Integer getRecordType() {
		return recordType;
	}

	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescriptionStand() {
		return descriptionStand;
	}

	public void setDescriptionStand(String descriptionStand) {
		this.descriptionStand = descriptionStand;
	}

	@Override
	public String toString() {
		return "NormalRecordDO [recordType=" + recordType + ", description=" + description + ", descriptionStand="
				+ descriptionStand + ", toString()=" + super.toString() + "]";
	}

}
