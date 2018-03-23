package com.zd.aoding.outsourcing.weChat.api.bean.businessObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.AboutOurDO;

@ApiModel(value = "", description = "关于我们展示类")
public class AboutOurBO {
	@ApiModelProperty("id")
	private Integer aboutOurId;
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
	@ApiModelProperty("详情 ")
	private String content;
	
	
	public AboutOurBO() {
		super();
	}

	public AboutOurBO(AboutOurDO aboutPo) {
		super();
		this.aboutOurId = aboutPo.getId();
		this.companyName = aboutPo.getCompanyName();
		this.companySimpleName = aboutPo.getCompanySimpleName();
		this.englishName = aboutPo.getEnglishName();
		this.domainName = aboutPo.getDomainName();
		this.email = aboutPo.getEmail();
		this.mobilePhone = aboutPo.getMobilePhone();
		this.telephone = aboutPo.getTelephone();
		this.content = aboutPo.getContent();
	}
	
	public Integer getAboutOurId() {
		return aboutOurId;
	}
	public void setAboutOurId(Integer aboutOurId) {
		this.aboutOurId = aboutOurId;
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
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
