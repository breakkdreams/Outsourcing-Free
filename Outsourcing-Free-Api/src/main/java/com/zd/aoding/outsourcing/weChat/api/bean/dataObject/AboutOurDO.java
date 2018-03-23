package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.base.DO.base.DOBase;

@ApiModel(value = "", description = "本公司信息")

public class AboutOurDO extends DOBase {

	@ApiModelProperty("id")
	private Integer id;
	@ApiModelProperty("公司名称（最长80个字符）")
	private String companyName;
	@ApiModelProperty("公司名称简称（最长15个字符）")
	private String companySimpleName;
	@ApiModelProperty("英文名称（100个字符）")
	private String englishName;
	@ApiModelProperty("域名(即网址200个字符)")
	private String domainName;
	@ApiModelProperty("邮箱")
	private String email;
	@ApiModelProperty("移动电话 ")
	private String mobilePhone;
	@ApiModelProperty("固定电话 ")
	private String telephone;
	@ApiModelProperty("开户行")
	private String bank;
	@ApiModelProperty("开户银行支行")
	private String bankBranch;
	@ApiModelProperty("开户银行卡号")
	private String bankCardNum;
	@ApiModelProperty("详情")
	private String content;
	@ApiModelProperty("appCode")
	private String appCode;

	
	
	public AboutOurDO(String companyName, String companySimpleName, String englishName, String domainName, String email,
			String mobilePhone, String telephone, String bank, String bankBranch, String bankCardNum, String appCode, String content) {
		super();
		this.companyName = companyName;
		this.companySimpleName = companySimpleName;
		this.englishName = englishName;
		this.domainName = domainName;
		this.email = email;
		this.mobilePhone = mobilePhone;
		this.telephone = telephone;
		this.bank = bank;
		this.bankBranch = bankBranch;
		this.bankCardNum = bankCardNum;
		this.appCode = appCode;
	}

	public AboutOurDO() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanySimpleName() {
		return companySimpleName;
	}

	public void setCompanySimpleName(String companySimpleName) {
		this.companySimpleName = companySimpleName;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	public String getBankCardNum() {
		return bankCardNum;
	}

	public void setBankCardNum(String bankCardNum) {
		this.bankCardNum = bankCardNum;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
	public String toString() {
		return "AboutOurDO [id=" + id + ", companyName=" + companyName + ", companySimpleName=" + companySimpleName
				+ ", englishName=" + englishName + ", domainName=" + domainName + ", email=" + email + ", mobilePhone="
				+ mobilePhone + ", telephone=" + telephone + ", bank=" + bank + ", bankBranch=" + bankBranch
				+ ", bankCardNum=" + bankCardNum + ", toString()=" + super.toString() + "]";
	}

}
