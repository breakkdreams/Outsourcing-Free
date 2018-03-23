package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.base.DO.base.DOBase;

@ApiModel(value = "", description = "产品属性描述")
public class GoodsParamDO extends DOBase {
	/** id **/
	private Integer id;
	/** goods_Id **/
	@ApiModelProperty("产品id")
	private Integer goodsId;
	/** 属性名 title **/
	@ApiModelProperty("产品属性名")
	private String title;
	/** 属性描述 value **/
	@ApiModelProperty("产品属性描述")
	private String value;
	/** displayorder **/
	private Integer displayorder;

	/** id **/
	public Integer getId() {
		return id;
	}
	/** id **/
	public void setId(Integer id) {
		this.id = id;
	}
	/** goods_Id **/
	public Integer getGoodsId() {
		return goodsId;
	}
	/** goods_Id **/
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	/** 属性名 title **/
	public String getTitle() {
		return title;
	}
	/** 属性名 title **/
	public void setTitle(String title) {
		this.title = title;
	}
	/** 属性描述 value **/
	public String getValue() {
		return value;
	}
	/** 属性描述 value **/
	public void setValue(String value) {
		this.value = value;
	}
	/** displayorder **/
	public Integer getDisplayorder() {
		return displayorder;
	}
	/** displayorder **/
	public void setDisplayorder(Integer displayorder) {
		this.displayorder = displayorder;
	}
}
