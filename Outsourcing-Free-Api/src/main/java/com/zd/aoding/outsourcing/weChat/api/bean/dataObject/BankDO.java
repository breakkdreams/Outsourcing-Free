package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.base.DO.base.DOBase;

@ApiModel(value = "", description = "后台银行卡信息")
public class BankDO extends DOBase {
	@ApiModelProperty("id")
	private Integer id;
	@ApiModelProperty("银行名称")
	private String bankName;
	@ApiModelProperty("银行logo")
	private String bankImgUrl;
	@ApiModelProperty("颜色")
	private String color;
	@ApiModelProperty("1显示 -1隐藏")
	private Integer shows;
	
	public BankDO() {
		super();
	}

	public BankDO(String bankName, String bankImgUrl, String color) {
		super();
		this.bankName = bankName;
		this.bankImgUrl = bankImgUrl;
		this.color = color;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankImgUrl() {
		return bankImgUrl;
	}

	public void setBankImgUrl(String bankImgUrl) {
		this.bankImgUrl = bankImgUrl;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getShows() {
		return shows;
	}

	public void setShows(Integer shows) {
		this.shows = shows;
	}

	@Override
	public String toString() {
		return "BankDO [id=" + id + ", bankName=" + bankName + ", bankImgUrl=" + bankImgUrl + ", color=" + color
				+ ", toString()=" + super.toString() + "]";
	}
}
