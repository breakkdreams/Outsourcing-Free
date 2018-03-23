package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.base.DO.base.DOBase;

@ApiModel(value = "", description = "邮费信息")
public class PostageDO extends DOBase {
	@ApiModelProperty("id")
	private Integer id;
	@ApiModelProperty("省名称")
	private String provinceName;
	@ApiModelProperty("省id")
	private Integer provinceId;
	@ApiModelProperty("邮费")
	private Double money;

	public PostageDO() {
		super();
	}

	public PostageDO(String provinceName, Integer provinceId, Double money) {
		super();
		this.provinceName = provinceName;
		this.provinceId = provinceId;
		this.money = money;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	@Override
	public String toString() {
		return "PostageDO{" +
				"id=" + id +
				", provinceName='" + provinceName + '\'' +
				", provinceId=" + provinceId +
				", money=" + money +
				'}';
	}
}
