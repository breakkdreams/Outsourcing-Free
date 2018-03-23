package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.base.DO.base.DOBase;

@ApiModel(value = "", description = "账号推荐关系表")
public class AccountRefereeDO extends DOBase {

	@ApiModelProperty("分享关系表 A（refereeAccountId推荐人id）分享B（beRefereeAccountId本人（当前注册人id））,分享id id")
	private Integer id;

	@ApiModelProperty("被推荐人账号id")
	private Integer beRefereeAccountId;
	@ApiModelProperty("推荐人账号id")
	private Integer refereeAccountId;
	@ApiModelProperty("返利累计")
	private Double fanliTotal;
	@ApiModelProperty("返利累计二级")
	private Double fanliTotalTwo;

	/** 推荐链id（弃） lever_accountIds **/
	@ApiModelProperty(value = "推荐人账号id", hidden = true)
	private String leverAccountIds;
	/** 推荐链中位子（基于admin初始账号id的推荐代数）(弃） lever_num **/
	@ApiModelProperty(value = "推荐链中位子（基于admin初始账号id的推荐代数）(弃） lever_num", hidden = true)
	private Integer leverNum;

	public AccountRefereeDO() {
		super();
	}

	public AccountRefereeDO(Integer beRefereeAccountId, Integer refereeAccountId) {
		super();
		this.beRefereeAccountId = beRefereeAccountId;
		this.refereeAccountId = refereeAccountId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBeRefereeAccountId() {
		return beRefereeAccountId;
	}

	public void setBeRefereeAccountId(Integer beRefereeAccountId) {
		this.beRefereeAccountId = beRefereeAccountId;
	}

	public Integer getRefereeAccountId() {
		return refereeAccountId;
	}

	public void setRefereeAccountId(Integer refereeAccountId) {
		this.refereeAccountId = refereeAccountId;
	}

	public String getLeverAccountIds() {
		return leverAccountIds;
	}

	public void setLeverAccountIds(String leverAccountIds) {
		this.leverAccountIds = leverAccountIds;
	}

	public Integer getLeverNum() {
		return leverNum;
	}

	public void setLeverNum(Integer leverNum) {
		this.leverNum = leverNum;
	}

	public Double getFanliTotal() {
		return fanliTotal;
	}

	public void setFanliTotal(Double fanliTotal) {
		this.fanliTotal = fanliTotal;
	}

	public Double getFanliTotalTwo() {
		return fanliTotalTwo;
	}

	public void setFanliTotalTwo(Double fanliTotalTwo) {
		this.fanliTotalTwo = fanliTotalTwo;
	}

	@Override
	public String toString() {
		return "AccountRefereeDO [id=" + id + ", beRefereeAccountId=" + beRefereeAccountId + ", refereeAccountId="
				+ refereeAccountId + ", leverAccountIds=" + leverAccountIds + ", leverNum=" + leverNum
				+ ", toString()=" + super.toString() + "]";
	}

}
