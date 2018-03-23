package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import java.math.BigDecimal;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.base.DO.base.DOBase;

@ApiModel(value = "", description = "产品")
public class GoodsDO extends DOBase {
	// 特殊展品数量
	public static int specile_Num = 4;
	/**
	 * @fieldName: STATUS_DEFAULT
	 * @fieldType: int
	 * @Description:默认
	 */
	public final static int STATUS_DEFAULT = 0;
	/**
	 * @fieldName: STATUS_SHALL
	 * @fieldType: int
	 * @Description: 上架
	 */
	public final static int STATUS_SHALL = 1;
	/**
	 * @fieldName: STATUS_XIAJIA
	 * @fieldType: int
	 * @Description: 已下架
	 */
	public final static int STATUS_XIAJIA = -1;
	/** 默认位置 */
	public final static int position_default = 0;
	/** 推荐到首页位置 */
	public final static int position_home = 1;
	/** 积分产品 */
	public final static int type_score = 1;
	/** 现金产品 */
	public final static int type_cash = 2;
	/** 现金+积分产品 */
	public final static int type_scoreAndCash = 3;
	/** 不在积分商城出售 */
	public final static int scoreOpen_no = 0;
	/** 在积分商城出售 */
	public final static int scoreOpen_yes = 1;
	/** 不在奖金商城出售 */
	public final static int bonusOpen_no = 0;
	/** 在奖金商城出售 */
	public final static int bonusOpen_yes = 1;
	/** 精选否 */
	public final static int chosen_false = 0;
	/** 精选是 */
	public final static int chosen_true = 1;
	
	@ApiModelProperty("设置位置0默认位置 1首页")
	private Integer position;
	@ApiModelProperty("设置精选0不是 1是")
	private Integer chosen;
	/** id **/
	private Integer id;
	/** supplier_Id **/
	@ApiModelProperty("商户id  0为总后台（自营）")
	private Integer dealerId;
	@ApiModelProperty("供货商id 暂无")
	private Integer supplierId;
	/** 第一级分类 first_Catagory **/
	private Integer firstCatagory;
	/** 第二级分类 second_Catagory **/
	private Integer secondCatagory;
	/** 第三级分类 third_Catagory **/
	private Integer thirdCatagory;
	/** 1  积分产品，2 奖金产品 **/
	private Integer type;
	/** 0默认待审核 1审核通过待上架（下架后可再次上架） 2 已上架 -1审核拒绝  **/
	@ApiModelProperty("0默认 1上架  -1下架 ")
	private Integer status;
	@ApiModelProperty("0默认 1上架  -1下架 ")
	private Integer statusAdmin;
	/** displayorder **/
	private Integer displayorder;
	/** 产品名称 title **/
	@ApiModelProperty("产品名称")
	private String title;
	/** 主图片显示图片 cover_img_url **/
	@ApiModelProperty("主图片显示图片（展示小图标）")
	private String coverImgUrl;
	/** 轮播展示图 imgs_url **/
	@ApiModelProperty("轮播展示图")
	private String imgsUrl;
	/** 概要描述 description **/
	@ApiModelProperty("概要描述")
	private String description;
	/** 标价展示价格(当没有配置时即为实际价格) market_price **/
	@ApiModelProperty("无配置实际售卖价格")
	private Double marketPrice;
	/** 原价(用于展示划掉） original_price **/
	@ApiModelProperty("原价(用于展示划掉）")
	private Double originalPrice;
	/** html描述 content **/
	@ApiModelProperty("html描述")
	private String content;
	/** 总库存 total_stock **/
	@ApiModelProperty("总库存 （实际库存）")
	private Integer totalStock;
	/** 销量 sales **/
	@ApiModelProperty("销量")
	private Integer sales;
	@ApiModelProperty("拒绝理由")
	private String rejectreason;
	@ApiModelProperty("模块id,0为无模块")
	private String modelId;
	@ApiModelProperty("产品进货价")
	private Double purchasePrice;
	@ApiModelProperty("无配置积分价")
	private Double scorePrice;
	@ApiModelProperty("无配置奖金价")
	private Double bonusPrice;
	@ApiModelProperty("产品质量评分")
	private Double qualityScore;
	@ApiModelProperty("产品物流评分")
	private Double logisticsScore;
	@ApiModelProperty("是否在积分商城出售  0 否  1 是")
	private Integer scoreOpen;
	@ApiModelProperty("是否在奖金商城出售  0 否  1 是")
	private Integer bonusOpen;
	@ApiModelProperty("展示库存")
	private Integer showStock;
	@ApiModelProperty("代发货数量")
	private Integer waitNum;
	@ApiModelProperty("货币价 仅总商城可用")
	private Double xnbPrice;
	@ApiModelProperty("条形码")
	private String barCode;
	@ApiModelProperty("参加返利")
	private Integer rebate;
	@ApiModelProperty("类型id")
	private Integer typeId;
	
	@ApiModelProperty("积分抵扣价")
	private Integer integralDeductible;
	@ApiModelProperty("积分抵扣百分比")
	private Double integralPercent;

	public GoodsDO(Integer dealerId, Integer firstCatagory, Integer secondCatagory, Integer thirdCatagory, String title, String coverImgUrl,
			String imgsUrl, String description, String content, Integer totalStock, Double purchasePrice, Integer goodsType, Double scorePrice,Double cashPrice, Integer showStock, Integer supplierId,Integer typeId) {
		super();
		this.dealerId = dealerId;
		this.firstCatagory = firstCatagory;
		this.secondCatagory = secondCatagory;
		this.thirdCatagory = thirdCatagory;
		this.status = STATUS_DEFAULT;
		this.statusAdmin = STATUS_DEFAULT;
		this.title = title;
		this.coverImgUrl = coverImgUrl;
		this.imgsUrl = imgsUrl;
		this.description = description;
		this.content = content;
		this.typeId = typeId;
		this.totalStock = totalStock;
		this.sales = 0;
		this.purchasePrice = purchasePrice;
		if(goodsType == type_score){
		    this.scorePrice = scorePrice;
	        this.bonusPrice = 0.00;
	        this.type = 1;
		}else if(goodsType == type_cash){
			this.scorePrice = 0.00;
            this.bonusPrice = cashPrice;
            this.type = 2;
		}else{
		    this.scorePrice = scorePrice;
            this.bonusPrice = cashPrice;
            this.type = 3;
		}
		this.supplierId = supplierId;
		this.showStock = showStock;
		this.waitNum = 0;
	}
	public GoodsDO() {
		super();
	}

	/** 下面全是保留字段 goodssn **/
	private String goodssn;
	/** productsn **/
	private String productsn;
	/** productPrice **/
	private Double productPrice;
	/** costPrice **/
	private Double costPrice;
	/** 0 拍下减库存 1 付款减库存 2 永久不减 totalcnf **/
	private Integer totalcnf;
	/** salesreal **/
	private Integer salesreal;
	/** spec **/
	private String spec;
	/** createtime **/
	private Integer createtime;
	/** weight **/
	private BigDecimal weight;
	/** credit **/
	private String credit;
	/** maxbuy **/
	private Integer maxbuy;
	/** unit **/
	private String unit;
	/** usermaxbuy **/
	private Integer usermaxbuy;
	/** hasoption **/
	private Integer hasoption;
	/** dispatch **/
	private Integer dispatch;
	/** isnew **/
	private Boolean isnew;
	/** ishot **/
	private Boolean ishot;
	/** isdiscount **/
	private Boolean isdiscount;
	/** isrecommand **/
	private Boolean isrecommand;
	/** issendfree **/
	private Boolean issendfree;
	/** istime **/
	private Boolean istime;
	/** iscomment **/
	private Boolean iscomment;
	/** timestart **/
	private Integer timestart;
	/** timeend **/
	private Integer timeend;
	/** viewcount **/
	private Integer viewcount;
	/** hascommission **/
	private Byte hascommission;
	/** commission1_rate **/
	private BigDecimal commission1Rate;
	/** commission1_pay **/
	private BigDecimal commission1Pay;
	/** commission2_rate **/
	private BigDecimal commission2Rate;
	/** commission2_pay **/
	private BigDecimal commission2Pay;
	/** commission3_rate **/
	private BigDecimal commission3Rate;
	/** commission3_pay **/
	private BigDecimal commission3Pay;
	/** score **/
	private BigDecimal score;
	/** taobaoid **/
	private String taobaoid;
	/** taotaoid **/
	private String taotaoid;
	/** taobaourl **/
	private String taobaourl;
	/** updatetime **/
	private Integer updatetime;
	/** share_title **/
	private String shareTitle;
	/** share_icon **/
	private String shareIcon;
	/** cash **/
	private Byte cash;
	/** commission_thumb **/
	private String commissionThumb;
	/** isnodiscount **/
	private Byte isnodiscount;
	/** isverify **/
	private Byte isverify;
	/** tcate **/
	private Integer tcate;

	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}

	/** needfollow **/
	private Byte needfollow;
	/** followtip **/
	private String followtip;
	/** followurl **/
	private String followurl;
	/** deduct **/
	private BigDecimal deduct;
	/** virtual **/
	private Integer virtual;
	/** nocommission **/
	private Byte nocommission;
	/** hidecommission **/
	private Byte hidecommission;
	/** artid **/
	private Integer artid;
	/** detail_logo **/
	private String detailLogo;
	/** detail_shopname **/
	private String detailShopname;
	/** detail_btntext1 **/
	private String detailBtntext1;
	/** detail_btnurl1 **/
	private String detailBtnurl1;
	/** detail_btntext2 **/
	private String detailBtntext2;
	/** detail_btnurl2 **/
	private String detailBtnurl2;
	/** detail_totaltitle **/
	private String detailTotaltitle;
	/** deduct2 **/
	private BigDecimal deduct2;
	/** ednum **/
	private Integer ednum;
	/** edmoney **/
	private BigDecimal edmoney;
	/** saleupdate **/
	private Byte saleupdate;
	/** diyformtype **/
	private Byte diyformtype;
	/** diyformid **/
	private Integer diyformid;
	/** diymode **/
	private Byte diymode;
	/** dispatchtype **/
	private Boolean dispatchtype;
	/** dispatchid **/
	private Integer dispatchid;
	/** dispatchprice **/
	private BigDecimal dispatchprice;
	/** manydeduct **/
	private Boolean manydeduct;
	/** shorttitle **/
	private String shorttitle;
	/** supplierid **/
	private Integer supplierid;
	private String showlevels;
	private String showgroups;
	private String buygroups;
	private String storeids;
	private String noticeopenid;
	private String noticetype;
	private String ccates;
	private String discounts;
	private String pcates;
	private String tcates;
	private String edareas;
	private String cates;

	/** id **/
	public Integer getId() {
		return id;
	}
	/** id **/
	public void setId(Integer id) {
		this.id = id;
	}
	/** dealerId **/
	public Integer getDealerId() {
		return dealerId;
	}
	/** dealerId **/
	public void setDealerId(Integer dealerId) {
		this.dealerId = dealerId;
	}
	/** 第一级分类 first_Catagory **/
	public Integer getFirstCatagory() {
		return firstCatagory;
	}
	/** 第一级分类 first_Catagory **/
	public void setFirstCatagory(Integer firstCatagory) {
		this.firstCatagory = firstCatagory;
	}
	/** 第二级分类 second_Catagory **/
	public Integer getSecondCatagory() {
		return secondCatagory;
	}
	/** 第二级分类 second_Catagory **/
	public void setSecondCatagory(Integer secondCatagory) {
		this.secondCatagory = secondCatagory;
	}
	/** 第三级分类 third_Catagory **/
	public Integer getThirdCatagory() {
		return thirdCatagory;
	}
	/** 第三级分类 third_Catagory **/
	public void setThirdCatagory(Integer thirdCatagory) {
		this.thirdCatagory = thirdCatagory;
	}
	/** 1为实体，2为虚拟 type **/
	public Integer getType() {
		return type;
	}
	/** 1为实体，2为虚拟 type **/
	public void setType(Integer type) {
		this.type = type;
	}
	/** 0默认1上架2展示到特殊展位 status **/
	public Integer getStatus() {
		return status;
	}
	/** 0默认1上架2展示到特殊展位 status **/
	public void setStatus(Integer status) {
		this.status = status;
	}
	/** displayorder **/
	public Integer getDisplayorder() {
		return displayorder;
	}
	/** displayorder **/
	public void setDisplayorder(Integer displayorder) {
		this.displayorder = displayorder;
	}
	/** 产品名称 title **/
	public String getTitle() {
		return title;
	}
	/** 产品名称 title **/
	public void setTitle(String title) {
		this.title = title;
	}
	/** 主图片显示图片 cover_img_url **/
	public String getCoverImgUrl() {
		return coverImgUrl;
	}
	/** 主图片显示图片 cover_img_url **/
	public void setCoverImgUrl(String coverImgUrl) {
		this.coverImgUrl = coverImgUrl;
	}
	/** 轮播展示图 imgs_url **/
	public String getImgsUrl() {
		return imgsUrl;
	}
	/** 轮播展示图 imgs_url **/
	public void setImgsUrl(String imgsUrl) {
		this.imgsUrl = imgsUrl;
	}
	/** 概要描述 description **/
	public String getDescription() {
		return description;
	}
	/** 概要描述 description **/
	public void setDescription(String description) {
		this.description = description;
	}
	/** 标价展示价格(当没有配置时即为实际价格) market_price **/
	public Double getMarketPrice() {
		return marketPrice;
	}
	/** 标价展示价格(当没有配置时即为实际价格) market_price **/
	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}
	/** 原价(用于展示划掉） original_price **/
	public Double getOriginalPrice() {
		return originalPrice;
	}
	/** 原价(用于展示划掉） original_price **/
	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}
	/** html描述 content **/
	public String getContent() {
		return content;
	}
	/** html描述 content **/
	public void setContent(String content) {
		this.content = content;
	}
	/** 总库存 total_stock **/
	public Integer getTotalStock() {
		return totalStock;
	}
	/** 总库存 total_stock **/
	public void setTotalStock(Integer totalStock) {
		this.totalStock = totalStock;
	}
	/** 销量 sales **/
	public Integer getSales() {
		return sales;
	}
	/** 销量 sales **/
	public void setSales(Integer sales) {
		this.sales = sales;
	}
	/** 下面全是保留字段 goodssn **/
	public String getGoodssn() {
		return goodssn;
	}
	/** 下面全是保留字段 goodssn **/
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
	/** productPrice **/
	public Double getProductPrice() {
		return productPrice;
	}
	/** productPrice **/
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	/** costPrice **/
	public Double getCostPrice() {
		return costPrice;
	}
	/** costPrice **/
	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}
	/** 0 拍下减库存 1 付款减库存 2 永久不减 totalcnf **/
	public Integer getTotalcnf() {
		return totalcnf;
	}
	/** 0 拍下减库存 1 付款减库存 2 永久不减 totalcnf **/
	public void setTotalcnf(Integer totalcnf) {
		this.totalcnf = totalcnf;
	}
	/** salesreal **/
	public Integer getSalesreal() {
		return salesreal;
	}
	/** salesreal **/
	public void setSalesreal(Integer salesreal) {
		this.salesreal = salesreal;
	}
	/** spec **/
	public String getSpec() {
		return spec;
	}
	/** spec **/
	public void setSpec(String spec) {
		this.spec = spec;
	}
	/** createtime **/
	public Integer getCreatetime() {
		return createtime;
	}
	/** createtime **/
	public void setCreatetime(Integer createtime) {
		this.createtime = createtime;
	}
	/** weight **/
	public BigDecimal getWeight() {
		return weight;
	}
	/** weight **/
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	/** credit **/
	public String getCredit() {
		return credit;
	}
	/** credit **/
	public void setCredit(String credit) {
		this.credit = credit;
	}
	/** maxbuy **/
	public Integer getMaxbuy() {
		return maxbuy;
	}
	/** maxbuy **/
	public void setMaxbuy(Integer maxbuy) {
		this.maxbuy = maxbuy;
	}
	/** unit **/
	public String getUnit() {
		return unit;
	}
	/** unit **/
	public void setUnit(String unit) {
		this.unit = unit;
	}
	/** usermaxbuy **/
	public Integer getUsermaxbuy() {
		return usermaxbuy;
	}
	/** usermaxbuy **/
	public void setUsermaxbuy(Integer usermaxbuy) {
		this.usermaxbuy = usermaxbuy;
	}
	/** hasoption **/
	public Integer getHasoption() {
		return hasoption;
	}
	/** hasoption **/
	public void setHasoption(Integer hasoption) {
		this.hasoption = hasoption;
	}
	/** dispatch **/
	public Integer getDispatch() {
		return dispatch;
	}
	/** dispatch **/
	public void setDispatch(Integer dispatch) {
		this.dispatch = dispatch;
	}
	/** isnew **/
	public Boolean getIsnew() {
		return isnew;
	}
	/** isnew **/
	public void setIsnew(Boolean isnew) {
		this.isnew = isnew;
	}
	/** ishot **/
	public Boolean getIshot() {
		return ishot;
	}
	/** ishot **/
	public void setIshot(Boolean ishot) {
		this.ishot = ishot;
	}
	/** isdiscount **/
	public Boolean getIsdiscount() {
		return isdiscount;
	}
	/** isdiscount **/
	public void setIsdiscount(Boolean isdiscount) {
		this.isdiscount = isdiscount;
	}
	/** isrecommand **/
	public Boolean getIsrecommand() {
		return isrecommand;
	}
	/** isrecommand **/
	public void setIsrecommand(Boolean isrecommand) {
		this.isrecommand = isrecommand;
	}
	/** issendfree **/
	public Boolean getIssendfree() {
		return issendfree;
	}
	/** issendfree **/
	public void setIssendfree(Boolean issendfree) {
		this.issendfree = issendfree;
	}
	/** istime **/
	public Boolean getIstime() {
		return istime;
	}
	/** istime **/
	public void setIstime(Boolean istime) {
		this.istime = istime;
	}
	/** iscomment **/
	public Boolean getIscomment() {
		return iscomment;
	}
	/** iscomment **/
	public void setIscomment(Boolean iscomment) {
		this.iscomment = iscomment;
	}
	/** timestart **/
	public Integer getTimestart() {
		return timestart;
	}
	/** timestart **/
	public void setTimestart(Integer timestart) {
		this.timestart = timestart;
	}
	/** timeend **/
	public Integer getTimeend() {
		return timeend;
	}
	/** timeend **/
	public void setTimeend(Integer timeend) {
		this.timeend = timeend;
	}
	/** viewcount **/
	public Integer getViewcount() {
		return viewcount;
	}
	/** viewcount **/
	public void setViewcount(Integer viewcount) {
		this.viewcount = viewcount;
	}
	/** hascommission **/
	public Byte getHascommission() {
		return hascommission;
	}
	/** hascommission **/
	public void setHascommission(Byte hascommission) {
		this.hascommission = hascommission;
	}
	/** commission1_rate **/
	public BigDecimal getCommission1Rate() {
		return commission1Rate;
	}
	/** commission1_rate **/
	public void setCommission1Rate(BigDecimal commission1Rate) {
		this.commission1Rate = commission1Rate;
	}
	/** commission1_pay **/
	public BigDecimal getCommission1Pay() {
		return commission1Pay;
	}
	/** commission1_pay **/
	public void setCommission1Pay(BigDecimal commission1Pay) {
		this.commission1Pay = commission1Pay;
	}
	/** commission2_rate **/
	public BigDecimal getCommission2Rate() {
		return commission2Rate;
	}
	/** commission2_rate **/
	public void setCommission2Rate(BigDecimal commission2Rate) {
		this.commission2Rate = commission2Rate;
	}
	/** commission2_pay **/
	public BigDecimal getCommission2Pay() {
		return commission2Pay;
	}
	/** commission2_pay **/
	public void setCommission2Pay(BigDecimal commission2Pay) {
		this.commission2Pay = commission2Pay;
	}
	/** commission3_rate **/
	public BigDecimal getCommission3Rate() {
		return commission3Rate;
	}
	/** commission3_rate **/
	public void setCommission3Rate(BigDecimal commission3Rate) {
		this.commission3Rate = commission3Rate;
	}
	/** commission3_pay **/
	public BigDecimal getCommission3Pay() {
		return commission3Pay;
	}
	/** commission3_pay **/
	public void setCommission3Pay(BigDecimal commission3Pay) {
		this.commission3Pay = commission3Pay;
	}
	/** score **/
	public BigDecimal getScore() {
		return score;
	}
	/** score **/
	public void setScore(BigDecimal score) {
		this.score = score;
	}
	/** taobaoid **/
	public String getTaobaoid() {
		return taobaoid;
	}
	/** taobaoid **/
	public void setTaobaoid(String taobaoid) {
		this.taobaoid = taobaoid;
	}
	/** taotaoid **/
	public String getTaotaoid() {
		return taotaoid;
	}
	/** taotaoid **/
	public void setTaotaoid(String taotaoid) {
		this.taotaoid = taotaoid;
	}
	/** taobaourl **/
	public String getTaobaourl() {
		return taobaourl;
	}
	/** taobaourl **/
	public void setTaobaourl(String taobaourl) {
		this.taobaourl = taobaourl;
	}
	/** updatetime **/
	public Integer getUpdatetime() {
		return updatetime;
	}
	/** updatetime **/
	public void setUpdatetime(Integer updatetime) {
		this.updatetime = updatetime;
	}
	/** share_title **/
	public String getShareTitle() {
		return shareTitle;
	}
	/** share_title **/
	public void setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
	}
	/** share_icon **/
	public String getShareIcon() {
		return shareIcon;
	}
	/** share_icon **/
	public void setShareIcon(String shareIcon) {
		this.shareIcon = shareIcon;
	}
	/** cash **/
	public Byte getCash() {
		return cash;
	}
	/** cash **/
	public void setCash(Byte cash) {
		this.cash = cash;
	}
	/** commission_thumb **/
	public String getCommissionThumb() {
		return commissionThumb;
	}
	/** commission_thumb **/
	public void setCommissionThumb(String commissionThumb) {
		this.commissionThumb = commissionThumb;
	}
	/** isnodiscount **/
	public Byte getIsnodiscount() {
		return isnodiscount;
	}
	/** isnodiscount **/
	public void setIsnodiscount(Byte isnodiscount) {
		this.isnodiscount = isnodiscount;
	}
	/** isverify **/
	public Byte getIsverify() {
		return isverify;
	}
	/** isverify **/
	public void setIsverify(Byte isverify) {
		this.isverify = isverify;
	}
	/** tcate **/
	public Integer getTcate() {
		return tcate;
	}
	/** tcate **/
	public void setTcate(Integer tcate) {
		this.tcate = tcate;
	}
	/** needfollow **/
	public Byte getNeedfollow() {
		return needfollow;
	}
	/** needfollow **/
	public void setNeedfollow(Byte needfollow) {
		this.needfollow = needfollow;
	}
	/** followtip **/
	public String getFollowtip() {
		return followtip;
	}
	/** followtip **/
	public void setFollowtip(String followtip) {
		this.followtip = followtip;
	}
	/** followurl **/
	public String getFollowurl() {
		return followurl;
	}
	/** followurl **/
	public void setFollowurl(String followurl) {
		this.followurl = followurl;
	}
	/** deduct **/
	public BigDecimal getDeduct() {
		return deduct;
	}
	/** deduct **/
	public void setDeduct(BigDecimal deduct) {
		this.deduct = deduct;
	}
	/** virtual **/
	public Integer getVirtual() {
		return virtual;
	}
	/** virtual **/
	public void setVirtual(Integer virtual) {
		this.virtual = virtual;
	}
	/** nocommission **/
	public Byte getNocommission() {
		return nocommission;
	}
	/** nocommission **/
	public void setNocommission(Byte nocommission) {
		this.nocommission = nocommission;
	}
	/** hidecommission **/
	public Byte getHidecommission() {
		return hidecommission;
	}
	/** hidecommission **/
	public void setHidecommission(Byte hidecommission) {
		this.hidecommission = hidecommission;
	}
	/** artid **/
	public Integer getArtid() {
		return artid;
	}
	/** artid **/
	public void setArtid(Integer artid) {
		this.artid = artid;
	}
	/** detail_logo **/
	public String getDetailLogo() {
		return detailLogo;
	}
	/** detail_logo **/
	public void setDetailLogo(String detailLogo) {
		this.detailLogo = detailLogo;
	}
	/** detail_shopname **/
	public String getDetailShopname() {
		return detailShopname;
	}
	/** detail_shopname **/
	public void setDetailShopname(String detailShopname) {
		this.detailShopname = detailShopname;
	}
	/** detail_btntext1 **/
	public String getDetailBtntext1() {
		return detailBtntext1;
	}
	/** detail_btntext1 **/
	public void setDetailBtntext1(String detailBtntext1) {
		this.detailBtntext1 = detailBtntext1;
	}
	/** detail_btnurl1 **/
	public String getDetailBtnurl1() {
		return detailBtnurl1;
	}
	/** detail_btnurl1 **/
	public void setDetailBtnurl1(String detailBtnurl1) {
		this.detailBtnurl1 = detailBtnurl1;
	}
	/** detail_btntext2 **/
	public String getDetailBtntext2() {
		return detailBtntext2;
	}
	/** detail_btntext2 **/
	public void setDetailBtntext2(String detailBtntext2) {
		this.detailBtntext2 = detailBtntext2;
	}
	/** detail_btnurl2 **/
	public String getDetailBtnurl2() {
		return detailBtnurl2;
	}
	/** detail_btnurl2 **/
	public void setDetailBtnurl2(String detailBtnurl2) {
		this.detailBtnurl2 = detailBtnurl2;
	}
	/** detail_totaltitle **/
	public String getDetailTotaltitle() {
		return detailTotaltitle;
	}
	/** detail_totaltitle **/
	public void setDetailTotaltitle(String detailTotaltitle) {
		this.detailTotaltitle = detailTotaltitle;
	}
	/** deduct2 **/
	public BigDecimal getDeduct2() {
		return deduct2;
	}
	/** deduct2 **/
	public void setDeduct2(BigDecimal deduct2) {
		this.deduct2 = deduct2;
	}
	/** ednum **/
	public Integer getEdnum() {
		return ednum;
	}
	/** ednum **/
	public void setEdnum(Integer ednum) {
		this.ednum = ednum;
	}
	/** edmoney **/
	public BigDecimal getEdmoney() {
		return edmoney;
	}
	/** edmoney **/
	public void setEdmoney(BigDecimal edmoney) {
		this.edmoney = edmoney;
	}
	/** saleupdate **/
	public Byte getSaleupdate() {
		return saleupdate;
	}
	/** saleupdate **/
	public void setSaleupdate(Byte saleupdate) {
		this.saleupdate = saleupdate;
	}
	/** diyformtype **/
	public Byte getDiyformtype() {
		return diyformtype;
	}
	/** diyformtype **/
	public void setDiyformtype(Byte diyformtype) {
		this.diyformtype = diyformtype;
	}
	/** diyformid **/
	public Integer getDiyformid() {
		return diyformid;
	}
	/** diyformid **/
	public void setDiyformid(Integer diyformid) {
		this.diyformid = diyformid;
	}
	/** diymode **/
	public Byte getDiymode() {
		return diymode;
	}
	/** diymode **/
	public void setDiymode(Byte diymode) {
		this.diymode = diymode;
	}
	/** dispatchtype **/
	public Boolean getDispatchtype() {
		return dispatchtype;
	}
	/** dispatchtype **/
	public void setDispatchtype(Boolean dispatchtype) {
		this.dispatchtype = dispatchtype;
	}
	/** dispatchid **/
	public Integer getDispatchid() {
		return dispatchid;
	}
	/** dispatchid **/
	public void setDispatchid(Integer dispatchid) {
		this.dispatchid = dispatchid;
	}
	/** dispatchprice **/
	public BigDecimal getDispatchprice() {
		return dispatchprice;
	}
	/** dispatchprice **/
	public void setDispatchprice(BigDecimal dispatchprice) {
		this.dispatchprice = dispatchprice;
	}
	/** manydeduct **/
	public Boolean getManydeduct() {
		return manydeduct;
	}
	/** manydeduct **/
	public void setManydeduct(Boolean manydeduct) {
		this.manydeduct = manydeduct;
	}
	/** shorttitle **/
	public String getShorttitle() {
		return shorttitle;
	}
	/** shorttitle **/
	public void setShorttitle(String shorttitle) {
		this.shorttitle = shorttitle;
	}
	/** supplierid **/
	public Integer getSupplierid() {
		return supplierid;
	}
	/** supplierid **/
	public void setSupplierid(Integer supplierid) {
		this.supplierid = supplierid;
	}
	public String getRejectreason() {
		return rejectreason;
	}
	public void setRejectreason(String rejectreason) {
		this.rejectreason = rejectreason;
	}
	public String getModelId() {
		return modelId;
	}
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	
	public String getShowlevels() {
		return showlevels;
	}
	public void setShowlevels(String showlevels) {
		this.showlevels = showlevels;
	}
	public String getShowgroups() {
		return showgroups;
	}
	public void setShowgroups(String showgroups) {
		this.showgroups = showgroups;
	}
	public String getBuygroups() {
		return buygroups;
	}
	public void setBuygroups(String buygroups) {
		this.buygroups = buygroups;
	}
	public String getStoreids() {
		return storeids;
	}
	public void setStoreids(String storeids) {
		this.storeids = storeids;
	}
	public String getNoticeopenid() {
		return noticeopenid;
	}
	public void setNoticeopenid(String noticeopenid) {
		this.noticeopenid = noticeopenid;
	}
	public String getNoticetype() {
		return noticetype;
	}
	public void setNoticetype(String noticetype) {
		this.noticetype = noticetype;
	}
	public String getCcates() {
		return ccates;
	}
	public void setCcates(String ccates) {
		this.ccates = ccates;
	}
	public String getDiscounts() {
		return discounts;
	}
	public void setDiscounts(String discounts) {
		this.discounts = discounts;
	}
	public String getPcates() {
		return pcates;
	}
	public void setPcates(String pcates) {
		this.pcates = pcates;
	}
	public String getTcates() {
		return tcates;
	}
	public void setTcates(String tcates) {
		this.tcates = tcates;
	}
	public String getEdareas() {
		return edareas;
	}
	public void setEdareas(String edareas) {
		this.edareas = edareas;
	}
	public String getCates() {
		return cates;
	}
	public void setCates(String cates) {
		this.cates = cates;
	}
	public Double getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public Double getQualityScore() {
		return qualityScore;
	}
	public void setQualityScore(Double qualityScore) {
		this.qualityScore = qualityScore;
	}
	public Double getLogisticsScore() {
		return logisticsScore;
	}
	public void setLogisticsScore(Double logisticsScore) {
		this.logisticsScore = logisticsScore;
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
	public Integer getScoreOpen() {
		return scoreOpen;
	}
	public void setScoreOpen(Integer scoreOpen) {
		this.scoreOpen = scoreOpen;
	}
	public Integer getBonusOpen() {
		return bonusOpen;
	}
	public void setBonusOpen(Integer bonusOpen) {
		this.bonusOpen = bonusOpen;
	}
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	public Integer getStatusAdmin() {
		return statusAdmin;
	}
	public void setStatusAdmin(Integer statusAdmin) {
		this.statusAdmin = statusAdmin;
	}
	public Integer getShowStock() {
		return showStock;
	}
	public void setShowStock(Integer showStock) {
		this.showStock = showStock;
	}
	public Double getXnbPrice() {
		return xnbPrice;
	}
	public void setXnbPrice(Double xnbPrice) {
		this.xnbPrice = xnbPrice;
	}
	public Integer getWaitNum() {
		return waitNum;
	}
	public void setWaitNum(Integer waitNum) {
		this.waitNum = waitNum;
	}
	public String getBarCode() {
        return barCode;
    }
    public void setBarCode(String barCode) {
        this.barCode = barCode;
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
	public Integer getRebate() {
		return rebate;
	}
	public void setRebate(Integer rebate) {
		this.rebate = rebate;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	@Override
    public String toString() {
        return "GoodsDO [integralDeductible=" + integralDeductible + ",integralPercent=" + integralPercent + ",chosen=" + chosen + ", position=" + position + ", id=" + id + ", dealerId="
                + dealerId + ", supplierId=" + supplierId + ", firstCatagory="
                + firstCatagory + ", secondCatagory=" + secondCatagory
                + ", thirdCatagory=" + thirdCatagory + ", type=" + type
                + ", status=" + status + ", statusAdmin=" + statusAdmin
                + ", displayorder=" + displayorder + ", title=" + title
                + ", coverImgUrl=" + coverImgUrl + ", imgsUrl=" + imgsUrl
                + ", description=" + description + ", marketPrice="
                + marketPrice + ", originalPrice=" + originalPrice
                + ", content=" + content + ", totalStock=" + totalStock
                + ", sales=" + sales + ", rejectreason=" + rejectreason
                + ", modelId=" + modelId + ", purchasePrice=" + purchasePrice
                + ", scorePrice=" + scorePrice + ", bonusPrice=" + bonusPrice
                + ", qualityScore=" + qualityScore + ", logisticsScore="
                + logisticsScore + ", scoreOpen=" + scoreOpen + ", bonusOpen="
                + bonusOpen + ", showStock=" + showStock + ", waitNum="
                + waitNum + ", xnbPrice=" + xnbPrice + ", barCode=" + barCode
                + ", goodssn=" + goodssn + ", productsn=" + productsn
                + ", productPrice=" + productPrice + ", costPrice=" + costPrice
                + ", totalcnf=" + totalcnf + ", salesreal=" + salesreal
                + ", spec=" + spec + ", createtime=" + createtime + ", weight="
                + weight + ", credit=" + credit + ", maxbuy=" + maxbuy
                + ", unit=" + unit + ", usermaxbuy=" + usermaxbuy
                + ", hasoption=" + hasoption + ", dispatch=" + dispatch
                + ", isnew=" + isnew + ", ishot=" + ishot + ", isdiscount="
                + isdiscount + ", isrecommand=" + isrecommand + ", issendfree="
                + issendfree + ", istime=" + istime + ", iscomment="
                + iscomment + ", timestart=" + timestart + ", timeend="
                + timeend + ", viewcount=" + viewcount + ", hascommission="
                + hascommission + ", commission1Rate=" + commission1Rate
                + ", commission1Pay=" + commission1Pay + ", commission2Rate="
                + commission2Rate + ", commission2Pay=" + commission2Pay
                + ", commission3Rate=" + commission3Rate + ", commission3Pay="
                + commission3Pay + ", score=" + score + ", taobaoid="
                + taobaoid + ", taotaoid=" + taotaoid + ", taobaourl="
                + taobaourl + ", updatetime=" + updatetime + ", shareTitle="
                + shareTitle + ", shareIcon=" + shareIcon + ", cash=" + cash
                + ", commissionThumb=" + commissionThumb + ", isnodiscount="
                + isnodiscount + ", isverify=" + isverify + ", tcate=" + tcate
                + ", needfollow=" + needfollow + ", followtip=" + followtip
                + ", followurl=" + followurl + ", deduct=" + deduct
                + ", virtual=" + virtual + ", nocommission=" + nocommission
                + ", hidecommission=" + hidecommission + ", artid=" + artid
                + ", detailLogo=" + detailLogo + ", detailShopname="
                + detailShopname + ", detailBtntext1=" + detailBtntext1
                + ", detailBtnurl1=" + detailBtnurl1 + ", detailBtntext2="
                + detailBtntext2 + ", detailBtnurl2=" + detailBtnurl2
                + ", detailTotaltitle=" + detailTotaltitle + ", deduct2="
                + deduct2 + ", ednum=" + ednum + ", edmoney=" + edmoney
                + ", saleupdate=" + saleupdate + ", diyformtype=" + diyformtype
                + ", diyformid=" + diyformid + ", diymode=" + diymode
                + ", dispatchtype=" + dispatchtype + ", dispatchid="
                + dispatchid + ", dispatchprice=" + dispatchprice
                + ", manydeduct=" + manydeduct + ", shorttitle=" + shorttitle
                + ", supplierid=" + supplierid + ", showlevels=" + showlevels
                + ", showgroups=" + showgroups + ", buygroups=" + buygroups
                + ", storeids=" + storeids + ", noticeopenid=" + noticeopenid
                + ", noticetype=" + noticetype + ", ccates=" + ccates
                + ", discounts=" + discounts + ", pcates=" + pcates
                + ", tcates=" + tcates + ", edareas=" + edareas + ", rebate=" + rebate + ", cates="
                + cates + "]";
    }
	
}
