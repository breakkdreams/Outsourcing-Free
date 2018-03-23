package com.zd.aoding.outsourcing.weChat.api.bean.businessObject;

import java.text.SimpleDateFormat;

import com.wordnik.swagger.annotations.ApiModel;
import com.zd.aoding.base.DO.base.RecordBase;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.RecordsDO;

@ApiModel(value = "", description = "系统消息展示类")
public class RecordBO {
	private Integer recordType;// 记录类型
	private String description;// 概述
	private String descriptionStand;
	private Integer lever;
	private String leverStr;
	private String cause;
	private String result;
	private Integer dealType;//
	private String dealTypeStr;
	private Integer dealId;// 处理人员id(操作的id)
	private Integer beDealedId;
	private String createTime;
	private String dealName;
	
	
	
	public RecordBO(RecordsDO recordDO) {
		super();
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		this.recordType = recordDO.getRecordType();
		this.description = recordDO.getDescription();
		this.descriptionStand = recordDO.getDescriptionStand();
		Integer lever = recordDO.getLever();
		switch (lever) {
		case RecordBase.LEVER_NORMAL:
			this.leverStr = "一般记录";
			break;
		case RecordBase.LEVER_IMPORTANT:
			this.leverStr = "重要记录";
			break;
		case RecordBase.LEVER_PURSE:
			this.leverStr = "钱包记录";
			break;
		default:
			this.leverStr = "";
			break;
		}
		this.cause = recordDO.getCause();
		this.result = recordDO.getResult();
		Integer dealType = recordDO.getDealType();
		switch (dealType) {
		case RecordBase.DEALTYPE_MANAGER:
			this.dealTypeStr = "管理员";
			break;
		case RecordBase.DEALTYPE_OTHER:
			this.dealTypeStr = "其他对象";
			break;
		case RecordBase.DEALTYPE_SYSTEM:
			this.dealTypeStr = "系统";
			break;
		case RecordBase.DEALTYPE_USER:
			this.dealTypeStr = "用户";
			break;
		default:
			this.dealTypeStr = "";
			break;
		}
		this.dealId = recordDO.getDealId();
		this.beDealedId = recordDO.getBeDealedId();
		this.createTime = format.format(recordDO.getCreateTime());
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



	public Integer getLever() {
		return lever;
	}



	public void setLever(Integer lever) {
		this.lever = lever;
	}



	public String getCause() {
		return cause;
	}



	public void setCause(String cause) {
		this.cause = cause;
	}



	public String getResult() {
		return result;
	}



	public void setResult(String result) {
		this.result = result;
	}



	public Integer getDealType() {
		return dealType;
	}



	public void setDealType(Integer dealType) {
		this.dealType = dealType;
	}



	public Integer getDealId() {
		return dealId;
	}



	public void setDealId(Integer dealId) {
		this.dealId = dealId;
	}



	public Integer getBeDealedId() {
		return beDealedId;
	}



	public void setBeDealedId(Integer beDealedId) {
		this.beDealedId = beDealedId;
	}



	public String getCreateTime() {
		return createTime;
	}



	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}



	public String getLeverStr() {
		return leverStr;
	}



	public void setLeverStr(String leverStr) {
		this.leverStr = leverStr;
	}



	public String getDealTypeStr() {
		return dealTypeStr;
	}



	public void setDealTypeStr(String dealTypeStr) {
		this.dealTypeStr = dealTypeStr;
	}



	public String getDealName() {
		return dealName;
	}



	public void setDealName(String dealName) {
		this.dealName = dealName;
	}
	
}
