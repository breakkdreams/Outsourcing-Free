package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import com.zd.aoding.base.DO.base.RecordBase;

/**
 * 
 * @ClassName: PurseRecordDO
 * @Description: 钱包操作记录
 * @author: zj
 * @date: 2017年12月1日 下午1:54:31
 */
public class RecordPursesDO extends RecordBase {

	/** 一般用户钱包 */
	public final static int PURSETYPE_USER = 1;
	/** 商家钱包 */
	public final static int PURSETYPE_DEALER = 2;
	/** 管理员钱包 */
	public final static int PURSETYPE_MANAGER = 3;
	/** 消费积分 */
	public final static int PURSETYPE_SCORE = 4;
	/** 返利 */
	public final static int PURSETYPE_REBATE = 5;
	/** 提现 */
	public final static int PURSETYPE_TAKE = 6;
	/** 消费奖金 */
	public final static int PURSETYPE_CASH = 7;
	
	/** 增加 */
	public final static int  TYPE_ADD = 1;
	/** 减少 */
	public final static int  TYPE_CUT = -1;

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

	public RecordPursesDO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @Title:PurseRecordDO
	 * @Description: 插入初始构造
	 * @param cause
	 *            操作起因、来源
	 * @param result
	 *            操作结果
	 * @param dealType
	 *            执行操作的实体（系统、管理员、用户）
	 * @param dealId
	 *            执行操作的实体id
	 * @param beDealedId
	 *            被执行操作的实体（结果对象）id
	 * @param purseType
	 *            钱包记录类型
	 * @param addOrCut
	 *            是增加还是减少
	 * @param changeNum
	 *            改变数量
	 * @param filedName
	 *            改变字段
	 * @param description
	 *            整体描述
	 */
	public RecordPursesDO(String cause, String result, Integer dealType, Integer dealId, Integer beDealedId,
			Integer purseType, Integer addOrCut, Integer changeNum, String filedName, String description) {
		super();
		this.lever = RecordBase.LEVER_PURSE;
		this.cause = cause;
		this.result = result;
		this.dealType = dealType;
		this.dealId = dealId;
		this.beDealedId = beDealedId;
		this.purseType = purseType;
		this.addOrCut = addOrCut;
		this.changeNum = changeNum;
		this.filedName = filedName;
		this.description = description;

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

	@Override
	public String toString() {
		return "PurseRecordDO [purseType=" + purseType + ", addOrCut=" + addOrCut + ", changeNum=" + changeNum
				+ ", filedName=" + filedName + ", description=" + description + ", toString()=" + super.toString()
				+ "]";
	}

}
