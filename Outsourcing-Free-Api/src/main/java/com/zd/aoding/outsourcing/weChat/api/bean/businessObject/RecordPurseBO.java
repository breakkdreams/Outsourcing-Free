package com.zd.aoding.outsourcing.weChat.api.bean.businessObject;

import java.text.SimpleDateFormat;

import com.wordnik.swagger.annotations.ApiModel;
import com.zd.aoding.base.DO.record.RecordPurseDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.RecordPursesDO;

@ApiModel(value = "", description = "系统消息展示类")
public class RecordPurseBO {
	private Integer beDealedId;
	private Integer dealId;// 处理人员id(操作的id)
	private String result;
	private Integer dealType;//
	private String cause;
	private Integer lever;
	/** 钱包操作类型 */
	private Integer purseType;//记录类型(4.积分 5.返利 6.提现)
	/** 操作增加or减少 */
	private Integer addOrCut;// 增加还是减少
	/** 改变数量 */
	private Integer changeNum;// 改变数量
	/** 改变字段 */
	private String filedName;// 修改字段
	/** 描述 */
	private String description;// 概述
	private String createTime;// 概述
	
	
	
	public RecordPurseBO(RecordPursesDO recordPo) {
		super();
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		this.purseType = recordPo.getPurseType();
		this.description = recordPo.getDescription();
		this.description = recordPo.getDescription();
		this.lever = recordPo.getLever();
		this.cause = recordPo.getCause();
		this.result = recordPo.getResult();
		this.dealType = recordPo.getDealType();
		this.dealId = recordPo.getDealId();
		this.beDealedId = recordPo.getBeDealedId();
		this.createTime = format.format(recordPo.getCreateTime());
	}



	public Integer getBeDealedId() {
		return beDealedId;
	}



	public void setBeDealedId(Integer beDealedId) {
		this.beDealedId = beDealedId;
	}



	public Integer getDealId() {
		return dealId;
	}



	public void setDealId(Integer dealId) {
		this.dealId = dealId;
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



	public String getCause() {
		return cause;
	}



	public void setCause(String cause) {
		this.cause = cause;
	}



	public Integer getLever() {
		return lever;
	}



	public void setLever(Integer lever) {
		this.lever = lever;
	}



	public Integer getPurseType() {
		return purseType;
	}



	public void setPurseType(Integer purseType) {
		this.purseType = purseType;
	}



	public Integer getAddOrCut() {
		return addOrCut;
	}



	public void setAddOrCut(Integer addOrCut) {
		this.addOrCut = addOrCut;
	}



	public Integer getChangeNum() {
		return changeNum;
	}



	public void setChangeNum(Integer changeNum) {
		this.changeNum = changeNum;
	}



	public String getFiledName() {
		return filedName;
	}



	public void setFiledName(String filedName) {
		this.filedName = filedName;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getCreateTime() {
		return createTime;
	}



	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	
}
