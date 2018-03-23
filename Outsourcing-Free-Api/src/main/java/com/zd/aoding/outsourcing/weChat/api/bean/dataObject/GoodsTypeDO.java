package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.base.DO.base.DOBase;

@ApiModel(value = "", description = "商品类型")
public class GoodsTypeDO extends DOBase {
	@ApiModelProperty("类型表id")
	private Integer id;
	@ApiModelProperty("类型名称")
	private String typeName;
	@ApiModelProperty("返利比例")
	private Integer rebateRatio;
	@ApiModelProperty("是否售后")
	private Integer isService;
	
	public GoodsTypeDO() {
		super();
	}
	public GoodsTypeDO(String typeName, Integer rebateRatio, Integer isService) {
		super();
		this.typeName = typeName;
		this.rebateRatio = rebateRatio;
		this.isService = isService;
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
	public Integer getRebateRatio() {
		return rebateRatio;
	}
	public void setRebateRatio(Integer rebateRatio) {
		this.rebateRatio = rebateRatio;
	}
	public Integer getIsService() {
		return isService;
	}
	public void setIsService(Integer isService) {
		this.isService = isService;
	}
	@Override
	public String toString() {
		return "GoodsTypeDO [id=" + id + ", typeName=" + typeName + ", rebateRatio=" + rebateRatio + ", isService="
				+ isService + ", toString()=" + super.toString() + "]";
	}
}
