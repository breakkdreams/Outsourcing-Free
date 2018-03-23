package com.zd.aoding.outsourcing.weChat.api.bean.businessObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.AccountRefereeDO;

@ApiModel(value = "", description = "推荐关系展示类")
public class AccountRefereeBO{
	@ApiModelProperty("id")
	private Integer id;
	@ApiModelProperty("被推荐人账号id")
	private Integer beRefereeAccountId;
	@ApiModelProperty("推荐人账号id")
	private Integer refereeAccountId;
	@ApiModelProperty("返利累计")
	private Double fanliTotal;
	@ApiModelProperty("返利累计二级")
	private Double fanliTotalTwo;
	@ApiModelProperty("被推荐人昵称")
	private String nickName;
	@ApiModelProperty("被推荐人手机号")
	private String phone;
	
	
	public AccountRefereeBO() {
		super();
	}
	public AccountRefereeBO(AccountRefereeDO accountRefereePo) {
		super();
		this.id = accountRefereePo.getId();
		this.beRefereeAccountId = accountRefereePo.getBeRefereeAccountId();
		this.refereeAccountId = accountRefereePo.getRefereeAccountId();
		this.fanliTotal = accountRefereePo.getFanliTotal();
		this.fanliTotalTwo = accountRefereePo.getFanliTotalTwo();
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
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	

}
