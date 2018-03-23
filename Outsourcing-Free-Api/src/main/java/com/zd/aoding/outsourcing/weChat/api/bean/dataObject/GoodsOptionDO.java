package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import java.math.BigDecimal;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.base.DO.base.DOBase;

@ApiModel(value = "", description = "产品配置组合")
public class GoodsOptionDO extends DOBase {
	/** id **/
	private Integer id;
	/** goods_Id **/
	@ApiModelProperty("产品id")
	private Integer goodsId;
	/** 配置组合名称 title **/
	@ApiModelProperty("产品实际配置名称")
	private String title;
	/** 展示图 thumb **/
	@ApiModelProperty("图片")
	private String thumb;
	/** 配置价格（即实际价格） market_price **/
	@ApiModelProperty("产品该配置价格")
	private Double marketPrice;
	@ApiModelProperty("产品进货价")
	private Double purchasePrice;
	@ApiModelProperty("产品该配置积分价格")
	private Double optionScorePrice;
	@ApiModelProperty("产品该配置奖金价格")
	private Double optionBonusPrice;
	/** 库存 stock **/
	@ApiModelProperty("产品该配置库存")
	private Integer stock;
	@ApiModelProperty("产品该配置显示库存")
	private Integer showStock;
	/** spec_Ids **/
	@ApiModelProperty("产品配置组合来源（配置明细id以'_'拼接，正常前小后大（例：3_5））")
	private String specIds;
	@ApiModelProperty("条形码")
	private String barCode;

	public GoodsOptionDO(Integer goodsId, String title, String specIds) {
		super();
		this.goodsId = goodsId;
		this.title = title;
		this.marketPrice = 0D;
		this.stock = 0;
		this.specIds = specIds;
	}
	public GoodsOptionDO() {
		super();
	}

	/** goodssn **/
	private String goodssn;
	/** productsn **/
	private String productsn;
	/** virtual **/
	private Integer virtual;
	/** costprice **/
	private BigDecimal costprice;
	/** displayorder **/
	private Integer displayorder;
	/** weight **/
	private BigDecimal weight;

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
	/** 配置组合名称 title **/
	public String getTitle() {
		return title;
	}
	/** 配置组合名称 title **/
	public void setTitle(String title) {
		this.title = title;
	}
	/** 展示图 thumb **/
	public String getThumb() {
		return thumb;
	}
	/** 展示图 thumb **/
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	/** 配置价格（即实际价格） market_price **/
	public Double getMarketPrice() {
		return marketPrice;
	}
	/** 配置价格（即实际价格） market_price **/
	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}
	/** 库存 stock **/
	public Integer getStock() {
		return stock;
	}
	/** 库存 stock **/
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	/** spec_Ids **/
	public String getSpecIds() {
		return specIds;
	}
	/** spec_Ids **/
	public void setSpecIds(String specIds) {
		this.specIds = specIds;
	}
	/** goodssn **/
	public String getGoodssn() {
		return goodssn;
	}
	/** goodssn **/
	public void setGoodssn(String goodssn) {
		this.goodssn = goodssn;
	}
	/** productsn **/
	public String getProductsn() {
		return productsn;
	}
	/** productsn **/
	public void setProductsn(String productsn) {
		this.productsn = productsn;
	}
	/** virtual **/
	public Integer getVirtual() {
		return virtual;
	}
	/** virtual **/
	public void setVirtual(Integer virtual) {
		this.virtual = virtual;
	}
	/** costprice **/
	public BigDecimal getCostprice() {
		return costprice;
	}
	/** costprice **/
	public void setCostprice(BigDecimal costprice) {
		this.costprice = costprice;
	}
	/** displayorder **/
	public Integer getDisplayorder() {
		return displayorder;
	}
	/** displayorder **/
	public void setDisplayorder(Integer displayorder) {
		this.displayorder = displayorder;
	}
	/** weight **/
	public BigDecimal getWeight() {
		return weight;
	}
	/** weight **/
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	public Double getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	@Override
	public String toString() {
		return "GoodsOptionDO [id=" + id + ", goodsId=" + goodsId + ", title="
				+ title + ", thumb=" + thumb + ", marketPrice=" + marketPrice
				+ ", purchasePrice=" + purchasePrice + ", optionScorePrice="
				+ optionScorePrice + ", optionBonusPrice=" + optionBonusPrice
				+ ", stock=" + stock + ", showStock=" + showStock
				+ ", specIds=" + specIds + ", barCode=" + barCode
				+ ", goodssn=" + goodssn + ", productsn=" + productsn
				+ ", virtual=" + virtual + ", costprice=" + costprice
				+ ", displayorder=" + displayorder + ", weight=" + weight + "]";
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
    public Integer getShowStock() {
        return showStock;
    }
    public void setShowStock(Integer showStock) {
        this.showStock = showStock;
    }
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
}
