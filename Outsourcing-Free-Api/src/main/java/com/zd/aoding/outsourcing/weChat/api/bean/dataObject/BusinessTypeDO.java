package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.base.DO.base.DOBase;

@ApiModel(value = "", description = "商家类型")
public class BusinessTypeDO extends DOBase {
	@ApiModelProperty("类型表id")
	private Integer id;
	@ApiModelProperty("类型名称")
	private String typeName;
	@ApiModelProperty("排序")
	private Integer sort;
	
	public BusinessTypeDO() {
		super();
	}
	public BusinessTypeDO(String typeName, Integer sort) {
		super();
		this.typeName = typeName;
		this.sort = sort;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	@Override
	public String toString() {
		return "BusinessTypeDO [id=" + id + ", typeName=" + typeName + ", sort=" + sort + ", toString()="
				+ super.toString() + "]";
	}
}
