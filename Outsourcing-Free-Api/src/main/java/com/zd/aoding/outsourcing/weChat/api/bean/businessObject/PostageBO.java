package com.zd.aoding.outsourcing.weChat.api.bean.businessObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.PostageDO;

@ApiModel(value = "", description = "邮费")
public class PostageBO {
	@ApiModelProperty("id")
	private Integer postageId;
	@ApiModelProperty("省名称")
	private String provinceName;
	@ApiModelProperty("省id")
	private Integer provinceId;
	@ApiModelProperty("邮费")
	private Double money;

	public PostageBO(PostageDO postageDO){
		super();
		this.postageId = postageDO.getId();
		this.provinceName = postageDO.getProvinceName();
		this.provinceId = postageDO.getProvinceId();
		this.money = postageDO.getMoney();
	}

	public Integer getPostageId() {
		return postageId;
	}

	public void setPostageId(Integer postageId) {
		this.postageId = postageId;
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
}
