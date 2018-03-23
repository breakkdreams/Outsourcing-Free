package com.zd.aoding.outsourcing.weChat.api.bean.businessObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsTypeDO;

@ApiModel(value = "", description = "产品类型")
public class GoodsTypeBO {
	@ApiModelProperty("类型id")
	private Integer goodsTypeId;
	@ApiModelProperty("类型名称")
	private String typeName;
	@ApiModelProperty("返利比例")
	private Integer rebateRatio;
	@ApiModelProperty("是否售后")
	private Integer isService;

	public GoodsTypeBO() {
		super();
	}
	public GoodsTypeBO(GoodsTypeDO goodsTypePo) {
		this.goodsTypeId = goodsTypePo.getId();
		this.typeName = goodsTypePo.getTypeName();
		this.rebateRatio = goodsTypePo.getRebateRatio();
		this.isService = goodsTypePo.getIsService();
	}
	public Integer getGoodsTypeId() {
		return goodsTypeId;
	}
	public void setGoodsTypeId(Integer goodsTypeId) {
		this.goodsTypeId = goodsTypeId;
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
}
