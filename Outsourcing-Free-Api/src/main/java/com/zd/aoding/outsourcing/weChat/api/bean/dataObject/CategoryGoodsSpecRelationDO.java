package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.base.DO.base.DOBase;


public class CategoryGoodsSpecRelationDO extends DOBase {
	
	@ApiModelProperty("分类下固定产品配置表id")
    private Integer id;
	@ApiModelProperty("分类id")
    private Integer categoryId;
	@ApiModelProperty("配置名称")
    private String specName;
	
	
	public CategoryGoodsSpecRelationDO() {
		super();
	}

	public CategoryGoodsSpecRelationDO(Integer categoryId, String specName) {
		super();
		this.categoryId = categoryId;
		this.specName = specName;
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}


	public String getSpecName() {
		return specName;
	}


	public void setSpecName(String specName) {
		this.specName = specName;
	}


	@Override
	public String toString() {
		return "CategoryGoodsSpecRelationDO [id=" + id + ", categoryId="
				+ categoryId + ", specName=" + specName + ", toString()="
				+ super.toString() + "]";
	}
}