package com.zd.aoding.outsourcing.weChat.api.bean.businessObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.CategoryGoodsSpecRelationDO;

@ApiModel(value = "", description = "分类展示类")
public class CategoryGoodsSpecRelationBO {
	@ApiModelProperty("分类下固定产品配置表id")
    private Integer id;
	@ApiModelProperty("分类id")
    private Integer categoryId;
	@ApiModelProperty("分类名称")
	private String categoryName;
	@ApiModelProperty("配置名称")
    private String specName;
	
	
	public CategoryGoodsSpecRelationBO() {
		super();
	}
	public CategoryGoodsSpecRelationBO(CategoryGoodsSpecRelationDO categoryGoodsSpecRelationPo) {
		super();
		this.id = categoryGoodsSpecRelationPo.getId();
		this.categoryId = categoryGoodsSpecRelationPo.getCategoryId();
		this.specName = categoryGoodsSpecRelationPo.getSpecName();
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
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getSpecName() {
		return specName;
	}
	public void setSpecName(String specName) {
		this.specName = specName;
	}
	
}
