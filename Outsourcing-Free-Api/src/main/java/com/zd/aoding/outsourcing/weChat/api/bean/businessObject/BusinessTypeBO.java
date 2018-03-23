package com.zd.aoding.outsourcing.weChat.api.bean.businessObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.BusinessTypeDO;

@ApiModel(value = "", description = "商家类型")
public class BusinessTypeBO {
	@ApiModelProperty("类型id")
	private Integer businessTypeId;
	@ApiModelProperty("类型名称")
	private String typeName;
	@ApiModelProperty("排序")
	private Integer sort;

	public BusinessTypeBO() {
		super();
	}
	public BusinessTypeBO(BusinessTypeDO businessTypePo) {
		this.businessTypeId = businessTypePo.getId();
		this.typeName = businessTypePo.getTypeName();
		this.sort = businessTypePo.getSort();
	}
	public Integer getBusinessTypeId() {
		return businessTypeId;
	}
	public void setBusinessTypeId(Integer businessTypeId) {
		this.businessTypeId = businessTypeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
}
