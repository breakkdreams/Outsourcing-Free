package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.base.DO.base.DOBase;

@ApiModel(value = "", description = "地址类")
public class UserAddressDO extends DOBase {
	@ApiModelProperty(value = "账号（收货）地址 id")
	/** 账号（收货）地址 id **/
	private Integer id;
	@ApiModelProperty(hidden = true)
	private Integer userId;// 已登录账号id，不用填
	@ApiModelProperty(value = "省id", required = true)
	private Integer provinceId;// 省
	@ApiModelProperty(value = "市id", required = true)
	private Integer cityId;// 市
	@ApiModelProperty(value = "区id", required = true)
	private Integer districtId;// 区
	@ApiModelProperty(value = "收件人姓名", required = true)
	private String consignee;// 收件人姓名
	@ApiModelProperty(value = "电话号码", required = true)
	private String phone;// 电话
	@ApiModelProperty(value = "省市区之后的详细地址", required = true)
	private String detailedAddress;// 详细地址
	@ApiModelProperty(value = "完整地址")
	private String fullAddress;// 详细地址
	@ApiModelProperty(value = "是否默认地址（0否1是）")
	private Integer isDefault;// 是否默认地址
	@ApiModelProperty(value = "经度", hidden = true)
	private Double lng;// 经度
	@ApiModelProperty(value = "纬度", hidden = true)
	private Double lat;// 纬度
	private String appCode;
	/**
	 * 保留字段
	 */
	@ApiModelProperty(value = "街道，保留字段", hidden = true)
	private String street;// 街道
	@ApiModelProperty(value = "邮编")
	private String postalcode;// 邮编

	/**
	 * @Title:AccountAddressDo
	 * @Description: 插入构造
	 * @param accountId
	 * @param provinceId
	 * @param cityId
	 * @param districtId
	 * @param consignee
	 * @param detailedAddress
	 * @param fullAddress
	 * @param phone
	 * @param isDefault
	 * @param lng
	 * @param lat
	 */
	public UserAddressDO(Integer userId, Integer provinceId, Integer cityId, Integer districtId, String detailedAddress,
			String consignee, String phone, Integer isDefault, Double lng, Double lat, String appCode,String postalcode) {
		super();
		this.userId = userId;
		this.provinceId = provinceId;
		this.cityId = cityId;
		this.districtId = districtId;
		this.detailedAddress = detailedAddress;
		this.consignee = consignee;
		this.phone = phone;
		this.isDefault = isDefault;
		this.appCode = appCode;
		this.postalcode = postalcode;
	}

	public UserAddressDO() {
		super();
	}

	/** 账号（收货）地址 id **/
	public Integer getId() {
		return id;
	}

	/** 账号（收货）地址 id **/
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/** 省id province_Id **/
	public Integer getProvinceId() {
		return provinceId;
	}

	/** 省id province_Id **/
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	/** 市id city_Id **/
	public Integer getCityId() {
		return cityId;
	}

	/** 市id city_Id **/
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	/** 区id district_Id **/
	public Integer getDistrictId() {
		return districtId;
	}

	/** 区id district_Id **/
	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	/** 收件人姓名 consignee **/
	public String getConsignee() {
		return consignee;
	}

	/** 收件人姓名 consignee **/
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	/** 详细地址 detailed_address **/
	public String getDetailedAddress() {
		return detailedAddress;
	}

	/** 详细地址 detailed_address **/
	public void setDetailedAddress(String detailedAddress) {
		this.detailedAddress = detailedAddress;
	}

	/** 完整地址 full_address **/
	public String getFullAddress() {
		return fullAddress;
	}

	/** 完整地址 full_address **/
	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	/** 联系电话 phone **/
	public String getPhone() {
		return phone;
	}

	/** 联系电话 phone **/
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/** 是否默认地址 1是,0不是 is_default **/
	public Integer getIsDefault() {
		return isDefault;
	}

	/** 是否默认地址 1是,0不是 is_default **/
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	/** 经度 lng **/
	public Double getLng() {
		return lng;
	}

	/** 经度 lng **/
	public void setLng(Double lng) {
		this.lng = lng;
	}

	/** 纬度 lat **/
	public Double getLat() {
		return lat;
	}

	/** 纬度 lat **/
	public void setLat(Double lat) {
		this.lat = lat;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	@Override
	public String toString() {
		return "UserAddressDO [id=" + id + ", userId=" + userId + ", provinceId=" + provinceId + ", cityId=" + cityId
				+ ", districtId=" + districtId + ", consignee=" + consignee + ", phone=" + phone + ", detailedAddress="
				+ detailedAddress + ", fullAddress=" + fullAddress + ", isDefault=" + isDefault + ", lng=" + lng
				+ ", lat=" + lat + ", street=" + street + ", postalcode=" + postalcode + ", toString()=" + super.toString() + "]";
	}
}
