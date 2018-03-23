package com.zd.aoding.outsourcing.weChat.api.bean.businessObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.UserAddressDO;

@ApiModel(value = "", description = "地址展示类")
public class UserAddressBO {
	private Integer addressId;
	private Integer userId;
	@ApiModelProperty(value = "省id")
	private Integer provinceId;
	@ApiModelProperty(value = "市id")
	private Integer cityId;// 市
	@ApiModelProperty(value = "区id")
	private Integer districtId;// 区
	@ApiModelProperty(value = "收件人姓名")
	private String consignee;// 收件人姓名
	@ApiModelProperty(value = "电话号码")
	private String phone;// 电话
	@ApiModelProperty(value = "省市区之后的详细地址")
	private String detailedAddress;// 详细地址
	@ApiModelProperty(value = "完整地址")
	private String fullAddress;// 详细地址
	@ApiModelProperty(value = "是否默认地址（0否1是）")
	private Integer isDefault;// 是否默认地址
	@ApiModelProperty(value = "经度")
	private Double lng;// 经度
	@ApiModelProperty(value = "纬度")
	private Double lat;// 纬度
	@ApiModelProperty(value = "省名称")
	private String provinceName;
	@ApiModelProperty(value = "市名称")
	private String cityName;
	@ApiModelProperty(value = "区名称")
	private String districtName;
	

	public UserAddressBO() {
		super();
	}
	public UserAddressBO(UserAddressDO userAddressPo) {
		super();
		this.addressId = userAddressPo.getId();
		this.userId = userAddressPo.getUserId();
		this.provinceId = userAddressPo.getProvinceId();
		this.cityId = userAddressPo.getCityId();
		this.districtId = userAddressPo.getDistrictId();
		this.consignee = userAddressPo.getConsignee();
		this.phone = userAddressPo.getPhone();
		this.detailedAddress = userAddressPo.getDetailedAddress();
		this.fullAddress = userAddressPo.getFullAddress();
		this.isDefault = userAddressPo.getIsDefault();
		this.lng = userAddressPo.getLng();
		this.lat = userAddressPo.getLat();
	}
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDetailedAddress() {
		return detailedAddress;
	}
	public void setDetailedAddress(String detailedAddress) {
		this.detailedAddress = detailedAddress;
	}
	public String getFullAddress() {
		return fullAddress;
	}
	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}
	public Integer getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
}
