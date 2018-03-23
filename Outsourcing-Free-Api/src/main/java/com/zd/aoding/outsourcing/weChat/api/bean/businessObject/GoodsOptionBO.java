package com.zd.aoding.outsourcing.weChat.api.bean.businessObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.config.Config;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsOptionDO;

@ApiModel(value = "", description = "产品配置组合展示类")
public class GoodsOptionBO {
	private Integer goodsOptionId;
	@ApiModelProperty("产品id")
	private Integer goodsId;
	@ApiModelProperty("产品实际配置名称")
	private String title;
	@ApiModelProperty("图片")
	private String thumb;
	@ApiModelProperty("产品该配置价格")
	private Double marketPrice;
	@ApiModelProperty("产品该配置积分价格")
	private Double optionScorePrice;
	@ApiModelProperty("产品该配置奖金价格")
	private Double optionBonusPrice;
	@ApiModelProperty("产品进货价")
	private Double purchasePrice;
	@ApiModelProperty("产品该配置库存")
	private Integer stock;
	@ApiModelProperty("产品配置组合来源（配置明细id以'_'拼接，正常前小后大（例：3_5））")
	private String specIds;
	@ApiModelProperty("产品该配置显示库存")
	private Integer showStock;
	@ApiModelProperty("产品该配置条形码")
	private String barCode;
	@ApiModelProperty("完整路径图片")
	private String imgStr;
	@ApiModelProperty("产品该配置积分价格")
	private Double scorePrice;
	@ApiModelProperty("产品该配置奖金价格")
	private Double bonusPrice;
	
	
	public GoodsOptionBO() {
		super();
	}
	public GoodsOptionBO(GoodsOptionDO goodsOptionPo) {
		super();
		this.goodsOptionId = goodsOptionPo.getId();
		this.goodsId = goodsOptionPo.getGoodsId();
		this.title = goodsOptionPo.getTitle();
		this.thumb = Config.IMG_SERVER+goodsOptionPo.getThumb();
		this.imgStr = Config.IMG_SERVER+goodsOptionPo.getThumb();
		this.marketPrice = goodsOptionPo.getMarketPrice();
		this.stock = goodsOptionPo.getStock();
		this.specIds = goodsOptionPo.getSpecIds();
		this.showStock = goodsOptionPo.getShowStock();
		this.optionScorePrice = goodsOptionPo.getOptionScorePrice();
		this.optionBonusPrice = goodsOptionPo.getOptionBonusPrice();
		this.purchasePrice = goodsOptionPo.getPurchasePrice();
		this.barCode = goodsOptionPo.getBarCode();
		this.scorePrice = goodsOptionPo.getOptionScorePrice();
		this.bonusPrice = goodsOptionPo.getOptionBonusPrice();
	}
	public Integer getGoodsOptionId() {
		return goodsOptionId;
	}
	public void setGoodsOptionId(Integer goodsOptionId) {
		this.goodsOptionId = goodsOptionId;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public Double getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public String getSpecIds() {
		return specIds;
	}
	public void setSpecIds(String specIds) {
		this.specIds = specIds;
	}
	public Integer getShowStock() {
		return showStock;
	}
	public void setShowStock(Integer showStock) {
		this.showStock = showStock;
	}
	public Double getOptionScorePrice() {
		return optionScorePrice;
	}
	public void setOptionScorePrice(Double optionScorePrice) {
		this.optionScorePrice = optionScorePrice;
	}
	public Double getOptionBonusPrice() {
		return optionBonusPrice;
	}
	public void setOptionBonusPrice(Double optionBonusPrice) {
		this.optionBonusPrice = optionBonusPrice;
	}
	public Double getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public String getImgStr() {
		return imgStr;
	}
	public void setImgStr(String imgStr) {
		this.imgStr = imgStr;
	}
	public Double getScorePrice() {
		return scorePrice;
	}
	public void setScorePrice(Double scorePrice) {
		this.scorePrice = scorePrice;
	}
	public Double getBonusPrice() {
		return bonusPrice;
	}
	public void setBonusPrice(Double bonusPrice) {
		this.bonusPrice = bonusPrice;
	}
}
