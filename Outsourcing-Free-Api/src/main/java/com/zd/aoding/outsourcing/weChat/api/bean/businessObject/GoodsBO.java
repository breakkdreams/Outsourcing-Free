package com.zd.aoding.outsourcing.weChat.api.bean.businessObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.common.StringDate.DateUtil;
import com.zd.aoding.common.StringDate.StringUtil;
import com.zd.aoding.config.Config;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsDO;

@ApiModel(value = "", description = "产品展示类")
public class GoodsBO {
	/**
	 * @fieldName: TO_PAGING
	 * @fieldType: String
	 * @Description: 分页
	 */
	public static final String TO_PAGING = "paging";
	/**
	 * @fieldName: TO_ALL
	 * @fieldType: String
	 * @Description: 全部
	 */
	public static final String TO_ALL = "all";
	private Integer goodsId;
	@ApiModelProperty("展示logo小图片")
	private String coverImgUrl;
	@ApiModelProperty("是否最新（0否1是）")
	private Integer isNew;
	@ApiModelProperty("商户")
	private Integer dealerId;
	@ApiModelProperty("供货商")
	private Integer supplierId;
	@ApiModelProperty("第一级分类")
	private Integer firstCatagory;
	@ApiModelProperty("第二级分类")
	private Integer secondCatagory;
	@ApiModelProperty("第三级分类")
	private Integer thirdCatagory;
	@ApiModelProperty("产品名称）")
	private String title;
	@ApiModelProperty("描述（暂时没有数据）")
	private String description;
	@ApiModelProperty("实际售价")
	private Double marketPrice;
	@ApiModelProperty("原价")
	private Double originalPrice;
	@ApiModelProperty("产品组图")
	private List<String> imgList;
	@ApiModelProperty("位置0默认 1首页")
	private Integer position;
	@ApiModelProperty("设置精选0不是 1是")
	private Integer chosen;
	@ApiModelProperty("0默认 1上架 -1下架")
	private Integer status;
	@ApiModelProperty("0默认 1上架 -1下架")
	private Integer statusAdmin;
	@ApiModelProperty("库存")
	private Integer totalStock;
	@ApiModelProperty("客服电话")
	private String phone;
	@ApiModelProperty("商户")
	private String dealerName;
	@ApiModelProperty("供货商")
	private String supplierName;
	@ApiModelProperty("分类")
	private String categoryName;
	@ApiModelProperty("模块")
	private String modelName;
	@ApiModelProperty("进货价")
	private Double purchasePrice;
	@ApiModelProperty("积分价")
	private Double scorePrice;
	@ApiModelProperty("奖金价")
	private Double bonusPrice;
	@ApiModelProperty("产品类型")
	private String typeName;
	@ApiModelProperty("状态")
	private  String statusStr;
	@ApiModelProperty("展示库存")
	private Integer showStock;
	@ApiModelProperty("轮播展示图")
	private String imgsUrl;
	@ApiModelProperty("图片组")
	private  List<String> imgUrls;
	@ApiModelProperty("详细介绍")
	private  String content;
	@ApiModelProperty("logo图片")
	private  String logoImg;
	@ApiModelProperty("类型")
	private Integer type;
	@ApiModelProperty("模块id,0为无模块")
	private String modelId;
	@ApiModelProperty("销售数量")
	private Integer saleCount;
	@ApiModelProperty("返利")
	private Integer rebate;
	@ApiModelProperty("类型id")
	private Integer typeId;
	
	@ApiModelProperty("销量")
	private Integer sales;
	@ApiModelProperty("真实销量")
	private Integer salesreal;
	
	@ApiModelProperty("客服电话")
	private String servicePhone;
	
	@ApiModelProperty("积分抵扣价")
	private Integer integralDeductible;
	@ApiModelProperty("积分抵扣百分比")
	private Double integralPercent;

	public GoodsBO(GoodsDO goodsPo, String toWhat) {
		super();
		switch (toWhat) {
			case TO_PAGING:
				toPaging(goodsPo);
				break;
			case TO_ALL:
				toAll(goodsPo);
				break;
			default:
				toPaging(goodsPo);
		}
	}
	private void toPaging(GoodsDO goodsPo) {
		this.goodsId = goodsPo.getId();
		this.integralDeductible = goodsPo.getIntegralDeductible();
		this.integralPercent = goodsPo.getIntegralPercent()*100;
		this.coverImgUrl = Config.IMG_SERVER + goodsPo.getCoverImgUrl();
		this.title = goodsPo.getTitle();
		this.sales = goodsPo.getSales();
		this.salesreal = goodsPo.getSalesreal();
		this.description = goodsPo.getDescription();
		this.marketPrice = goodsPo.getMarketPrice().doubleValue();
		this.originalPrice = goodsPo.getOriginalPrice().doubleValue();
		this.position = goodsPo.getPosition();
		this.typeId = goodsPo.getTypeId();
		this.modelId = goodsPo.getModelId();
		if (!StringUtil.isNULL(goodsPo.getImgsUrl())) {
			this.imgList = getImgUrlList(goodsPo.getImgsUrl());
		}
		this.status = goodsPo.getStatus();
		this.rebate = goodsPo.getRebate();
		this.chosen = goodsPo.getChosen();
		this.statusAdmin = goodsPo.getStatusAdmin();
		this.content = goodsPo.getContent();
		if (goodsPo.getCreateTime() != null && DateUtil.compareDay(goodsPo.getCreateTime()) < 3) {
			this.isNew = 1;
		} else {
			this.isNew = 0;
		}
		this.supplierId = goodsPo.getSupplierId();
		this.showStock = goodsPo.getShowStock();
		this.imgsUrl = goodsPo.getImgsUrl();
		this.type = goodsPo.getType();
		this.dealerId = goodsPo.getDealerId();
		this.firstCatagory = goodsPo.getFirstCatagory();
		this.secondCatagory = goodsPo.getSecondCatagory();
		this.thirdCatagory = goodsPo.getThirdCatagory();
		this.totalStock = goodsPo.getTotalStock();
		this.purchasePrice = goodsPo.getPurchasePrice();
		this.scorePrice = goodsPo.getScorePrice();
		this.bonusPrice = goodsPo.getBonusPrice();
		if((GoodsDO.type_score+"").equals(goodsPo.getType()+"")){
			this.typeName = "积分产品";
		}else if((GoodsDO.type_cash+"").equals(goodsPo.getType()+"")){
			this.typeName = "现金产品";
		}else{
			this.typeName = "现金+积分产品";
		}
		switch (goodsPo.getStatus()) {
        case GoodsDO.STATUS_DEFAULT:
            this.statusStr = "刚添加";
            break;
        case GoodsDO.STATUS_SHALL:
            this.statusStr = "已上架";
            break;
        case GoodsDO.STATUS_XIAJIA:
            this.statusStr = "已下架";
            break;
        default:
            this.statusStr = "刚添加";
            break;
        }
	}
	private void toAll(GoodsDO goodsPo) {
		this.goodsId = goodsPo.getId();
		this.coverImgUrl = Config.IMG_SERVER + goodsPo.getCoverImgUrl();
		if (!StringUtil.isNULL(goodsPo.getImgsUrl())) {
			this.imgList = getImgUrlList(goodsPo.getImgsUrl());
		}
		this.title = goodsPo.getTitle();
		this.integralDeductible = goodsPo.getIntegralDeductible();
		this.integralPercent = goodsPo.getIntegralPercent()*100;
		this.content = goodsPo.getContent();
		this.chosen = goodsPo.getChosen();
		this.showStock = goodsPo.getShowStock();
		this.rebate = goodsPo.getRebate();
		this.imgsUrl = goodsPo.getImgsUrl();
		this.type = goodsPo.getType();
		this.typeId = goodsPo.getTypeId();
		this.sales = goodsPo.getSales();
		this.salesreal = goodsPo.getSalesreal();
		this.description = goodsPo.getDescription();
		this.marketPrice = goodsPo.getMarketPrice().doubleValue();
		this.originalPrice = goodsPo.getOriginalPrice().doubleValue();
		this.modelId = goodsPo.getModelId();
		if (goodsPo.getCreateTime() != null && DateUtil.compareDay(goodsPo.getCreateTime()) < 3) {
			this.isNew = 1;
		} else {
			this.isNew = 0;
		}
		this.supplierId = goodsPo.getSupplierId();
		this.dealerId = goodsPo.getDealerId();
		this.firstCatagory = goodsPo.getFirstCatagory();
		this.secondCatagory = goodsPo.getSecondCatagory();
		this.thirdCatagory = goodsPo.getThirdCatagory();
		this.totalStock = goodsPo.getTotalStock();
		this.purchasePrice = goodsPo.getPurchasePrice();
		this.scorePrice = goodsPo.getScorePrice();
		this.bonusPrice = goodsPo.getBonusPrice();
		switch (goodsPo.getStatus()) {
        case GoodsDO.STATUS_DEFAULT:
            this.statusStr = "刚添加";
            break;
        case GoodsDO.STATUS_SHALL:
            this.statusStr = "已上架";
            break;
        case GoodsDO.STATUS_XIAJIA:
            this.statusStr = "已下架";
            break;
        default:
            this.statusStr = "刚添加";
            break;
        }
	}
	private List<String> getImgUrlList(String images) {
		List<String> list = Arrays.asList(images.split(","));
		List<String> newList = new ArrayList<>();
		for (String s : list) {
			s = Config.IMG_SERVER + s;
			newList.add(s);
		}
		return newList;
	}
	public String getCoverImgUrl() {
		return coverImgUrl;
	}
	public void setCoverImgUrl(String coverImgUrl) {
		this.coverImgUrl = coverImgUrl;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}
	public Double getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public List<String> getImgList() {
		return imgList;
	}
	public void setImgList(List<String> imgList) {
		this.imgList = imgList;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getIsNew() {
		return isNew;
	}
	public void setIsNew(Integer isNew) {
		this.isNew = isNew;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public Integer getFirstCatagory() {
		return firstCatagory;
	}

	public void setFirstCatagory(Integer firstCatagory) {
		this.firstCatagory = firstCatagory;
	}
	public Integer getTotalStock() {
		return totalStock;
	}
	public void setTotalStock(Integer totalStock) {
		this.totalStock = totalStock;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Integer getDealerId() {
		return dealerId;
	}
	public void setDealerId(Integer dealerId) {
		this.dealerId = dealerId;
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	public Integer getStatusAdmin() {
		return statusAdmin;
	}
	public void setStatusAdmin(Integer statusAdmin) {
		this.statusAdmin = statusAdmin;
	}
	public Double getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
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
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getStatusStr() {
		return statusStr;
	}
	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
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
	public Integer getShowStock() {
		return showStock;
	}
	public void setShowStock(Integer showStock) {
		this.showStock = showStock;
	}
	public String getImgsUrl() {
		return imgsUrl;
	}
	public void setImgsUrl(String imgsUrl) {
		this.imgsUrl = imgsUrl;
	}
	public List<String> getImgUrls() {
		return imgUrls;
	}
	public void setImgUrls(List<String> imgUrls) {
		this.imgUrls = imgUrls;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getLogoImg() {
		return logoImg;
	}
	public void setLogoImg(String logoImg) {
		this.logoImg = logoImg;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getModelId() {
		return modelId;
	}
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	public Integer getSaleCount() {
		return saleCount;
	}
	public void setSaleCount(Integer saleCount) {
		this.saleCount = saleCount;
	}
	public Integer getChosen() {
		return chosen;
	}
	public void setChosen(Integer chosen) {
		this.chosen = chosen;
	}
	public Integer getIntegralDeductible() {
		return integralDeductible;
	}
	public void setIntegralDeductible(Integer integralDeductible) {
		this.integralDeductible = integralDeductible;
	}
	public Double getIntegralPercent() {
		return integralPercent;
	}
	public void setIntegralPercent(Double integralPercent) {
		this.integralPercent = integralPercent;
	}
	public String getServicePhone() {
		return servicePhone;
	}
	public void setServicePhone(String servicePhone) {
		this.servicePhone = servicePhone;
	}
	public Integer getRebate() {
		return rebate;
	}
	public void setRebate(Integer rebate) {
		this.rebate = rebate;
	}
	public Integer getSales() {
		return sales;
	}
	public void setSales(Integer sales) {
		this.sales = sales;
	}
	public Integer getSalesreal() {
		return salesreal;
	}
	public void setSalesreal(Integer salesreal) {
		this.salesreal = salesreal;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
}