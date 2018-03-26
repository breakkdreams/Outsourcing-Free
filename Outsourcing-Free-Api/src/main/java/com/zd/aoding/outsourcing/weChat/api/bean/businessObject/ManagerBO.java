package com.zd.aoding.outsourcing.weChat.api.bean.businessObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.config.Config;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ManagerDO;

@ApiModel(value = "", description = "管理员")
public class ManagerBO {
	@ApiModelProperty("id")
	private Integer managerId;
	 //姓名
    private String name;

    private String phone;
    //用户id
    private Integer accountId;
    //添加者类型
    private Integer type;
    //添加者id
    private Integer ownerId;
    //省id
    private Integer provinceId;
    //市id
    private Integer cityId;
    //区id
    private Integer districtId;
    //经度
    private Double lng;
    //纬度
    private Double lat;
    //推荐码
    private String refereeCode;
    //商家类型
    private Integer businessType;
	//联系人
	private String contacts;
	//图片
	private String images;
	//图片
	private String imagesStr;
	//内容
	private String content;
	//返点
	private Double rebate;
	//访问量
	private Integer totalVisit;
    
	public ManagerBO() {
		super();
	}
	public ManagerBO(ManagerDO managerPo) {
		super();
		this.managerId = managerPo.getId();
		this.name = managerPo.getName();
		this.phone = managerPo.getPhone();
		this.accountId = managerPo.getAccountId();
		this.type = managerPo.getType();
		this.ownerId = managerPo.getOwnerId();
		this.provinceId = managerPo.getProvinceId();
		this.cityId = managerPo.getCityId();
		this.districtId = managerPo.getDistrictId();
		this.lng = managerPo.getLng();
		this.lat = managerPo.getLat();
		this.refereeCode = managerPo.getRefereeCode();
		this.businessType = managerPo.getBusinessType();
		this.contacts = managerPo.getContacts();
		this.images = managerPo.getImages();
		this.imagesStr = Config.IMG_SERVER + managerPo.getImages();
		this.content = managerPo.getContent();
		this.rebate = managerPo.getRebate();
		this.totalVisit = managerPo.getTotalVisit();
	}
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
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
	public String getRefereeCode() {
		return refereeCode;
	}
	public void setRefereeCode(String refereeCode) {
		this.refereeCode = refereeCode;
	}

	public Integer getBusinessType() {
		return businessType;
	}

	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Double getRebate() {
		return rebate;
	}

	public void setRebate(Double rebate) {
		this.rebate = rebate;
	}

	public String getImagesStr() {
		return imagesStr;
	}

	public void setImagesStr(String imagesStr) {
		this.imagesStr = imagesStr;
	}

	public Integer getTotalVisit() {
		return totalVisit;
	}

	public void setTotalVisit(Integer totalVisit) {
		this.totalVisit = totalVisit;
	}
}
