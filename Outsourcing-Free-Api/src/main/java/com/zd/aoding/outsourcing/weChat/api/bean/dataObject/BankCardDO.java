package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.base.DO.base.DOBase;

@ApiModel(value = "", description = "后台银行卡信息")
public class BankCardDO extends DOBase {
	/** 总后台银行卡信息 */
	public final static int type_admin = 1;
	/** 供货商银行卡信息 */
	public final static int type_supplier = 2;
	/** 客户银行卡信息 */
	public final static int type_company = 3;
	/** 商户银行卡信息 */
	public final static int type_dealer = 4;
	@ApiModelProperty("id")
	private Integer id;
	@ApiModelProperty("bankid")
	private Integer bankId;
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
	
	public BankCardDO() {
		super();
	}
	public BankCardDO(Integer type, Integer ownerId, String bankName,
			String bankKaihu, String bankNum, String companyName,
			String cardName, String phone,Integer bankId) {
		super();
		this.type = type;
		this.ownerId = ownerId;
		this.bankName = bankName;
		this.bankKaihu = bankKaihu;
		this.bankNum = bankNum;
		this.companyName = companyName;
		this.cardName = cardName;
		this.phone = phone;
		this.bankId = bankId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getBankId() {
		return bankId;
	}
	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}
	@Override
	public String toString() {
		return "BankCardDO [id=" + id + ", type=" + type + ", ownerId="
				+ ownerId + ", bankName=" + bankName + ", bankKaihu="
				+ bankKaihu + ", bankNum=" + bankNum + ", companyName="
				+ companyName + ", cardName=" + cardName + ", phone=" + phone
				+ ", toString()=" + super.toString() + "]";
	}
	
}
