package com.zd.aoding.outsourcing.weChat.api.bean.businessObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.DistributorGoodsRelationDO;

@ApiModel(value = "", description = "关于我们展示类")
public class DistributorGoodsRelationBO {
	@ApiModelProperty("id")
	private Integer distributorGoodsRelationId;
	@ApiModelProperty("经销商id")
	private Integer distributorId;
	@ApiModelProperty("商品id")
	private Integer goodsId;
	@ApiModelProperty("配置id")
	private Integer optionId;
	@ApiModelProperty("价格")
	private Double price;
	@ApiModelProperty("是否有配置.0无1有")
	private Integer haveOption;
	@ApiModelProperty("一级分类 ")
	private Integer firstCatagory;
	@ApiModelProperty("二级分类 ")
	private Integer secondCatagory;
	@ApiModelProperty("三级分类")
	private Integer thirdCatagory;
	@ApiModelProperty("库存")
	private Integer stock;


	public DistributorGoodsRelationBO(DistributorGoodsRelationDO distributorGoodsRelationDO) {
		super();
		this.distributorGoodsRelationId = distributorGoodsRelationDO.getId();
		this.distributorId = distributorGoodsRelationDO.getDistributorId();
		this.goodsId = distributorGoodsRelationDO.getGoodsId();
		this.optionId = distributorGoodsRelationDO.getOptionId();
		this.price = distributorGoodsRelationDO.getPrice();
		this.haveOption = distributorGoodsRelationDO.getHaveOption();
		this.firstCatagory = distributorGoodsRelationDO.getFirstCatagory();
		this.secondCatagory = distributorGoodsRelationDO.getSecondCatagory();
		this.thirdCatagory = distributorGoodsRelationDO.getThirdCatagory();
		this.stock = distributorGoodsRelationDO.getStock();
	}

	public Integer getDistributorGoodsRelationId() {
		return distributorGoodsRelationId;
	}

	public void setDistributorGoodsRelationId(Integer distributorGoodsRelationId) {
		this.distributorGoodsRelationId = distributorGoodsRelationId;
	}

	public Integer getDistributorId() {
		return distributorId;
	}

	public void setDistributorId(Integer distributorId) {
		this.distributorId = distributorId;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getOptionId() {
		return optionId;
	}

	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getHaveOption() {
		return haveOption;
	}

	public void setHaveOption(Integer haveOption) {
		this.haveOption = haveOption;
	}

	public Integer getFirstCatagory() {
		return firstCatagory;
	}

	public void setFirstCatagory(Integer firstCatagory) {
		this.firstCatagory = firstCatagory;
	}

	public Integer getSecondCatagory() {
		return secondCatagory;
	}

	public void setSecondCatagory(Integer secondCatagory) {
		this.secondCatagory = secondCatagory;
	}

	public Integer getThirdCatagory() {
		return thirdCatagory;
	}

	public void setThirdCatagory(Integer thirdCatagory) {
		this.thirdCatagory = thirdCatagory;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
}
