package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import java.util.Date;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.base.DO.base.DOBase;

/**
 * @author zj
 */
@ApiModel(value = "", description = "用户信息")
public class UserDO extends DOBase {
	@ApiModelProperty("pk")
	private Integer id;
	@ApiModelProperty("appCode")
	private String appCode;
	@ApiModelProperty("帐号id")
	private Integer accountId;
	@ApiModelProperty("推荐code")
	private String refreeCode;
	@ApiModelProperty("头像")
	private String portrait;
	@ApiModelProperty("易键启头像")
	private String yjqPortrait;
	@ApiModelProperty("性别1男2女")
	private Integer sex;
	@ApiModelProperty("昵称")
	private String nickName;
	@ApiModelProperty("真实姓名")
	private String realName;
	@ApiModelProperty("身份证号")
	private String IDCard;
	@ApiModelProperty("生日")
	private Date birthday;
	@ApiModelProperty("会员等级")
	private Integer gradeSort;// 1、2、数字等级
	@ApiModelProperty("会员积分")
	private Integer vipScore;
	@ApiModelProperty("电话")
	private String phone;
	@ApiModelProperty("邮箱")
	private Integer email;
	@ApiModelProperty(value = "省id")
	private Integer provinceId;// 省
	@ApiModelProperty(value = "市id")
	private Integer cityId;// 市
	@ApiModelProperty(value = "区id")
	private Integer districtId;// 区
	@ApiModelProperty("地址")
	private String address;
	 

	/**
	 * 插入构造
	 * @param accountId
	 * @param phone
	 */
	public UserDO(Integer accountId, String phone, String appCode) {
		super();
		this.accountId = accountId;
		this.gradeSort = 0;
		this.phone = phone;
		this.vipScore = 0;
		this.appCode = appCode;
		//性别默认男
		this.sex = 1;
	}
	public UserDO() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public String getRefreeCode() {
		return refreeCode;
	}
	public void setRefreeCode(String refreeCode) {
		this.refreeCode = refreeCode;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Integer getGradeSort() {
		return gradeSort;
	}
	public void setGradeSort(Integer gradeSort) {
		this.gradeSort = gradeSort;
	}
	public Integer getVipScore() {
		return vipScore;
	}
	public void setVipScore(Integer vipScore) {
		this.vipScore = vipScore;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getEmail() {
		return email;
	}
	public void setEmail(Integer email) {
		this.email = email;
	}
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public Integer getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getIDCard() {
		return IDCard;
	}
	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	public String getPortrait() {
		return portrait;
	}
	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getYjqPortrait() {
		return yjqPortrait;
	}
	public void setYjqPortrait(String yjqPortrait) {
		this.yjqPortrait = yjqPortrait;
	}
	@Override
	public String toString() {
		return "UserDO [id=" + id + ", appCode=" + appCode + ", accountId="
				+ accountId + ", refreeCode=" + refreeCode + ", portrait="
				+ portrait + ", yjqPortrait=" + yjqPortrait + ", sex=" + sex
				+ ", nickName=" + nickName + ", realName=" + realName
				+ ", IDCard=" + IDCard + ", birthday=" + birthday
				+ ", gradeSort=" + gradeSort + ", vipScore=" + vipScore
				+ ", phone=" + phone + ", email=" + email + ", provinceId="
				+ provinceId + ", cityId=" + cityId + ", districtId="
				+ districtId + ", address=" + address + ", toString()="
				+ super.toString() + "]";
	}
}
