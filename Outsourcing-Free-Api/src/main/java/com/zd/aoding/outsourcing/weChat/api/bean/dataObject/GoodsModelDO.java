package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.base.DO.base.DOBase;

@ApiModel(value = "", description = "产品模块")
public class GoodsModelDO extends DOBase {
	@ApiModelProperty("模块表id")
	private Integer id;
	@ApiModelProperty("appCode")
	private String appCode;
	@ApiModelProperty("模块名称")
	private String modelName;
	@ApiModelProperty("排序值")
	private Integer sort;
	@ApiModelProperty("描述")
	private String summary;
	@ApiModelProperty("图片1")
	private String imgOne;
	@ApiModelProperty("图片2")
	private String imgTwo;
	
	
	public GoodsModelDO() {
		super();
	}
	public GoodsModelDO(String modelName, Integer sort, String appCode) {
		super();
		this.modelName = modelName;
		this.sort = sort;
		this.appCode = appCode;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getImgOne() {
		return imgOne;
	}
	public void setImgOne(String imgOne) {
		this.imgOne = imgOne;
	}
	public String getImgTwo() {
		return imgTwo;
	}
	public void setImgTwo(String imgTwo) {
		this.imgTwo = imgTwo;
	}
	@Override
	public String toString() {
		return "GoodsModelDO [id=" + id + ", appCode=" + appCode
				+ ", modelName=" + modelName + ", sort=" + sort + ", summary="
				+ summary + ", imgOne=" + imgOne + ", imgTwo=" + imgTwo
				+ ", toString()=" + super.toString() + "]";
	}

}
