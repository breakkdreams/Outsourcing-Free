package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import com.zd.aoding.base.DO.base.DOBase;

public class ManagerDO extends DOBase{
    /**
     * 后台管理员
     */
    public static final int TYPE_MANAGER = 1;
    /**
     * 经销商
     */
	public static final int TYPE_DISTRIBUTOR = 2;
    /**
     * 商家
     */
    public static final int TYPE_BUSINESS = 3;
    /**
     * 营业员
     */
    public static final int TYPE_SALESMAN = 4;
    /**
     * 递推人员
     */
    public static final int TYPE_RECURSIVE = 5;
    
    
    
    //管理员信息
    private Integer id;

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
    //内容
    private String content;
    //返点
    private Double rebate;

    
    
    
    public ManagerDO() {
		super();
	}

	public ManagerDO(String name, String phone, Integer accountId) {
		super();
		this.name = name;
		this.phone = phone;
		this.accountId = accountId;
        this.rebate = 0.0;
	}

	/**
     * 获取管理员信息
     *
     * @return id - 管理员信息
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置管理员信息
     *
     * @param id 管理员信息
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取用户id
     *
     * @return account_Id - 用户id
     */
    public Integer getAccountId() {
        return accountId;
    }

    /**
     * 设置用户id
     *
     * @param accountId 用户id
     */
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

    @Override
    public String toString() {
        return "ManagerDO [id=" + id + ", name=" + name + ", phone=" + phone
                + ", accountId=" + accountId + ", type=" + type + ", ownerId="
                + ownerId + "]";
    }
    
}
