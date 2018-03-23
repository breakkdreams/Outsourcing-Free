package com.zd.aoding.outsourcing.weChat.api.bean.businessObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.BankCardDO;

@ApiModel(value = "", description = "BankCard类")
public class BankCardBO {
	@ApiModelProperty("id")
	private Integer bankCardId;
	@ApiModelProperty("1 总后台银行卡信息 2供货商银行卡信息 3客户银行卡信息")
	private Integer type;
	@ApiModelProperty("对象id type=1时为0 type=2时为供货商id type=3时为客户id")
	private Integer ownerId;
	@ApiModelProperty("银行名称")
	private String bankName;
	@ApiModelProperty("开户行")
	private String bankKaihu;
	@ApiModelProperty("银行卡号")
	private String bankNum;
	@ApiModelProperty("公司名称")
	private String companyName;
	@ApiModelProperty("持卡人姓名")
	private String cardName;
	@ApiModelProperty("手机号")
	private String phone;
	@ApiModelProperty("logo")
	private String imgLogo;
	@ApiModelProperty("color")
	private String color;
	@ApiModelProperty("银行卡id")
	private Integer bankid;
	
	public BankCardBO(BankCardDO bankCardDO){
		super();
		this.bankCardId = bankCardDO.getId();
		this.type = bankCardDO.getType();
		this.ownerId = bankCardDO.getOwnerId();
		this.bankName = bankCardDO.getBankName();
		this.bankKaihu = bankCardDO.getBankKaihu();
		this.bankNum = bankCardDO.getBankNum();
		this.companyName = bankCardDO.getCompanyName();
		this.cardName = bankCardDO.getCardName();
		this.phone = bankCardDO.getPhone();
		this.bankid = bankCardDO.getBankId();
	}

	public Integer getBankCardId() {
		return bankCardId;
	}

	public void setBankCardId(Integer bankCardId) {
		this.bankCardId = bankCardId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankKaihu() {
		return bankKaihu;
	}

	public void setBankKaihu(String bankKaihu) {
		this.bankKaihu = bankKaihu;
	}

	public String getBankNum() {
		return bankNum;
	}

	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getImgLogo() {
		return imgLogo;
	}

	public void setImgLogo(String imgLogo) {
		this.imgLogo = imgLogo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getBankid() {
		return bankid;
	}

	public void setBankid(Integer bankid) {
		this.bankid = bankid;
	}
	
}
