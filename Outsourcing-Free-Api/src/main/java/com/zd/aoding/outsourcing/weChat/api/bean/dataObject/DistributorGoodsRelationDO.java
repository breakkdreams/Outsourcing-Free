package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.base.DO.base.DOBase;

@ApiModel(value = "", description = "配货表")

public class DistributorGoodsRelationDO extends DOBase {

	@ApiModelProperty("id")
	private Integer id;
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

	public DistributorGoodsRelationDO() {super();}

	public DistributorGoodsRelationDO(Integer distributorId, Integer goodsId, Integer optionId, Double price, Integer haveOption, Integer firstCatagory, Integer secondCatagory, Integer thirdCatagory, Integer stock) {
		this.distributorId = distributorId;
		this.goodsId = goodsId;
		this.optionId = optionId;
		this.price = price;
		this.haveOption = haveOption;
		this.firstCatagory = firstCatagory;
		this.secondCatagory = secondCatagory;
		this.thirdCatagory = thirdCatagory;
		this.stock = stock;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "DistributorGoodsRelationDO{" +
				"id=" + id +
				", distributorId=" + distributorId +
				", goodsId=" + goodsId +
				", optionId=" + optionId +
				", price=" + price +
				", haveOption=" + haveOption +
				", firstCatagory=" + firstCatagory +
				", secondCatagory=" + secondCatagory +
				", thirdCatagory=" + thirdCatagory +
				", stock=" + stock +
				'}';
	}
}
