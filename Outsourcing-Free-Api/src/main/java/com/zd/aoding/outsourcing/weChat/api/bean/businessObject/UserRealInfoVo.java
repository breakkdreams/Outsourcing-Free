package com.zd.aoding.outsourcing.weChat.api.bean.businessObject;
//package com.zd.model.admin.vo;
//
//import com.wordnik.swagger.annotations.ApiModelProperty;
//import com.zd.common.config.Config;
//
//public class UserRealInfoVo {
//	@ApiModelProperty("pk")
//	private Integer userRealInfoId;
//	@ApiModelProperty("pk")
//	private Integer userId;
//	@ApiModelProperty("实名认证（1是0否）")
//	private String realNameCheck;
//	@ApiModelProperty("真实姓名")
//	private String realName;
//	@ApiModelProperty("身份证号")
//	private String idCard;
//	@ApiModelProperty("身份证号图片")
//	private String idCardFrontUrl;
//	@ApiModelProperty("身份证号图片")
//	private String idCardBackUrl;
//	@ApiModelProperty("手持身份证身份证图片")
//	private String idCardAndPersonUrl;
//	@ApiModelProperty("身份证号图片")
//	private String showIdCardFrontUrl;
//	@ApiModelProperty("身份证号图片")
//	private String showIdCardBackUrl;
//	@ApiModelProperty("手持身份证身份证图片")
//	private String showIdCardAndPersonUrl;
//
//	public UserRealInfoVo(UserRealInfoPo userRealInfoPo) {
//		super();
//		this.userRealInfoId = userRealInfoPo.getId();
//		this.userId = userRealInfoPo.getUserId();
//		if (userRealInfoPo.getRealNameCheck() != null && userRealInfoPo.getRealNameCheck() == 1) {
//			this.realNameCheck = "已实名";
//		} else {
//			this.realNameCheck = "未实名";
//		}
//		this.realName = userRealInfoPo.getRealName();
//		this.idCard = userRealInfoPo.getIdCard();
//		this.idCardFrontUrl = userRealInfoPo.getIdCardFrontUrl();
//		this.idCardBackUrl = userRealInfoPo.getIdCardBackUrl();
//		this.idCardAndPersonUrl = userRealInfoPo.getIdCardAndPersonUrl();
//		this.showIdCardFrontUrl = userRealInfoPo.getIdCardFrontUrl() != null ? Config.ImgSever + userRealInfoPo.getIdCardFrontUrl() : "";
//		this.showIdCardBackUrl = userRealInfoPo.getIdCardBackUrl() != null ? Config.ImgSever + userRealInfoPo.getIdCardBackUrl() : "";
//		this.showIdCardAndPersonUrl = userRealInfoPo.getIdCardAndPersonUrl() != null ? Config.ImgSever + userRealInfoPo.getIdCardAndPersonUrl() : "";
//	}
//
//	public UserRealInfoVo() {
//
//	}
//
//	public Integer getUserRealInfoId() {
//		return userRealInfoId;
//	}
//	public void setUserRealInfoId(Integer userRealInfoId) {
//		this.userRealInfoId = userRealInfoId;
//	}
//	public Integer getUserId() {
//		return userId;
//	}
//	public void setUserId(Integer userId) {
//		this.userId = userId;
//	}
//	public String getRealNameCheck() {
//		return realNameCheck;
//	}
//	public void setRealNameCheck(String realNameCheck) {
//		this.realNameCheck = realNameCheck;
//	}
//	public String getRealName() {
//		return realName;
//	}
//	public void setRealName(String realName) {
//		this.realName = realName;
//	}
//	public String getIdCard() {
//		return idCard;
//	}
//	public void setIdCard(String idCard) {
//		this.idCard = idCard;
//	}
//	public String getIdCardFrontUrl() {
//		return idCardFrontUrl;
//	}
//	public void setIdCardFrontUrl(String idCardFrontUrl) {
//		this.idCardFrontUrl = idCardFrontUrl;
//	}
//	public String getIdCardBackUrl() {
//		return idCardBackUrl;
//	}
//	public void setIdCardBackUrl(String idCardBackUrl) {
//		this.idCardBackUrl = idCardBackUrl;
//	}
//	public String getIdCardAndPersonUrl() {
//		return idCardAndPersonUrl;
//	}
//	public void setIdCardAndPersonUrl(String idCardAndPersonUrl) {
//		this.idCardAndPersonUrl = idCardAndPersonUrl;
//	}
//	public String getShowIdCardFrontUrl() {
//		return showIdCardFrontUrl;
//	}
//	public void setShowIdCardFrontUrl(String showIdCardFrontUrl) {
//		this.showIdCardFrontUrl = showIdCardFrontUrl;
//	}
//	public String getShowIdCardBackUrl() {
//		return showIdCardBackUrl;
//	}
//	public void setShowIdCardBackUrl(String showIdCardBackUrl) {
//		this.showIdCardBackUrl = showIdCardBackUrl;
//	}
//	public String getShowIdCardAndPersonUrl() {
//		return showIdCardAndPersonUrl;
//	}
//	public void setShowIdCardAndPersonUrl(String showIdCardAndPersonUrl) {
//		this.showIdCardAndPersonUrl = showIdCardAndPersonUrl;
//	}
//	@Override
//	public String toString() {
//		return "UserRealInfoVo [userRealInfoId=" + userRealInfoId + ", userId=" + userId + ", realNameCheck=" + realNameCheck + ", realName="
//				+ realName + ", idCard=" + idCard + ", idCardFrontUrl=" + idCardFrontUrl + ", idCardBackUrl=" + idCardBackUrl
//				+ ", idCardAndPersonUrl=" + idCardAndPersonUrl + ", showIdCardFrontUrl=" + showIdCardFrontUrl + ", showIdCardBackUrl="
//				+ showIdCardBackUrl + ", showIdCardAndPersonUrl=" + showIdCardAndPersonUrl + ", toString()=" + super.toString() + "]";
//	}
//}
