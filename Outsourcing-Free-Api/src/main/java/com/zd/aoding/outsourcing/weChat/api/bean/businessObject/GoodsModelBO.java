package com.zd.aoding.outsourcing.weChat.api.bean.businessObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.config.Config;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsModelDO;

@ApiModel(value = "", description = "产品模块类")
public class GoodsModelBO {
	@ApiModelProperty("模块id")
	private Integer goodsModelId;
	@ApiModelProperty("模块名称")
	private String modelName;
	@ApiModelProperty("描述")
	private String summary;
	@ApiModelProperty("模块图片1")
	private String imgOne;
	@ApiModelProperty("模块图片2")
	private String imgTwo;
	@ApiModelProperty("排序")
	private Integer sort;
	@ApiModelProperty("模块内产品数")
	private Integer goodsNum;
	@ApiModelProperty("模块图片1(原)")
	private String imgRealOne;
	@ApiModelProperty("模块图片2(原)")
	private String imgRealTwo;

	public GoodsModelBO() {
		super();
	}
	public GoodsModelBO(GoodsModelDO goodsModelPo) {
		this.goodsModelId = goodsModelPo.getId();
		this.sort = goodsModelPo.getSort();
		this.modelName = goodsModelPo.getModelName();
		this.summary = goodsModelPo.getSummary();
		this.imgOne = Config.IMG_SERVER + goodsModelPo.getImgOne();
		this.imgTwo = Config.IMG_SERVER + goodsModelPo.getImgTwo();
		this.imgRealOne = goodsModelPo.getImgOne();
		this.imgRealTwo = goodsModelPo.getImgTwo();
	}

	public Integer getGoodsModelId() {
		return goodsModelId;
	}

	public void setGoodsModelId(Integer goodsModelId) {
		this.goodsModelId = goodsModelId;
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

	public Integer getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
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
	public String getImgRealOne() {
		return imgRealOne;
	}
	public void setImgRealOne(String imgRealOne) {
		this.imgRealOne = imgRealOne;
	}
	public String getImgRealTwo() {
		return imgRealTwo;
	}
	public void setImgRealTwo(String imgRealTwo) {
		this.imgRealTwo = imgRealTwo;
	}
}
