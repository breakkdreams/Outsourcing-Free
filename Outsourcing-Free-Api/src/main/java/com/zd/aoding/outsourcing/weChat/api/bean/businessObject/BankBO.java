package com.zd.aoding.outsourcing.weChat.api.bean.businessObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.config.Config;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.BankDO;

@ApiModel(value = "", description = "BankCard类")
public class BankBO {
	@ApiModelProperty("id")
	private Integer bankId;
	@ApiModelProperty("银行名称")
	private String bankName;
	@ApiModelProperty("银行logo")
	private String bankImgUrl;
	@ApiModelProperty("银行logo")
	private String bankImgUrlStr;
	@ApiModelProperty("颜色")
	private String color;
	@ApiModelProperty("显示")
	private Integer shows;
	
	public BankBO(BankDO bankDO){
		super();
		this.bankId = bankDO.getId();
		this.bankName = bankDO.getBankName();
		this.bankImgUrl = Config.IMG_SERVER+bankDO.getBankImgUrl();
		this.bankImgUrlStr = bankDO.getBankImgUrl();
		this.color = bankDO.getColor();
		this.shows = bankDO.getShows();
	}

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
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

	public String getBankImgUrlStr() {
		return bankImgUrlStr;
	}

	public void setBankImgUrlStr(String bankImgUrlStr) {
		this.bankImgUrlStr = bankImgUrlStr;
	}

	public Integer getShows() {
		return shows;
	}

	public void setShows(Integer shows) {
		this.shows = shows;
	}
}
