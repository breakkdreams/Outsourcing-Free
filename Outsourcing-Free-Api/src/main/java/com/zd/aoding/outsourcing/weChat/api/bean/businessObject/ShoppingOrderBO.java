package com.zd.aoding.outsourcing.weChat.api.bean.businessObject;

import java.util.Date;
import java.util.List;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.common.StringDate.DateUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ShoppingOrderDO;

@ApiModel(value = "", description = "订单展示类")
public class ShoppingOrderBO {
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
	
	@ApiModelProperty("订单id")
	private Integer id;
	@ApiModelProperty("订单id")
	private Integer shoppingOrderId;
	@ApiModelProperty("订单状态")
	private Integer process;
	@ApiModelProperty("订单编号")
	private Long orderNum;
	@ApiModelProperty("下单用户")
	private String username;
	@ApiModelProperty("收件人")
	private String consignee;
	@ApiModelProperty("收件人手机号")
	private String consigneePhone;
	@ApiModelProperty("收件地址")
	private String address;
	@ApiModelProperty("下单时间")
	private String orderTime;
	@ApiModelProperty("下单平台")
	private String app;
	@ApiModelProperty("备注")
	private String message;
	private String expressNum;
	private String expressName;
	@ApiModelProperty("修改的订单地址（买家填错地址时修改用）")
	private String newAddress;
	@ApiModelProperty("shoppingOrderPo")
	private ShoppingOrderDO shoppingOrderPo;
	private Integer boughtUserId;
	private Integer boughtAddressId;
	private Double totalFee;
	private String updateDate;
	private String servicePhone;
	private Integer isPay;
	private String createTimeStr;
	private String orderTsStr;
	private Integer orderType;
	private Integer payType;
	private String expressNameView;
	/** 积分总价 **/
    private Integer scoreTotal;
    /** 积分兑换比例 **/
    private Double proportion;
    @ApiModelProperty("实际支付价格")
	private Double realTotalFee;
    private Long automaticTs;
    @ApiModelProperty(value ="",notes="订单产品明细（订单的产品信息）")
	private List<ShoppingOrderItemBO> shoppingOrderItemVoList;
    private Integer goodsNum;
    
    private String accountPhone;//用户名
    private String phone;//手机号
	
	public ShoppingOrderBO() {
		super();
	}
	
	public ShoppingOrderBO(ShoppingOrderDO shoppingOrderPo, String toWhat) {
		super();
		switch (toWhat) {
			case TO_PAGING:
				toPaging(shoppingOrderPo);
				break;
			case TO_ALL:
				toAll(shoppingOrderPo);
				break;
			default:
				toPaging(shoppingOrderPo);
		}
	}
	private void toPaging(ShoppingOrderDO shoppingOrderPo) {
		this.id = shoppingOrderPo.getId();
		this.shoppingOrderId = shoppingOrderPo.getId();
		this.boughtUserId = shoppingOrderPo.getBoughtUserId();
		this.process = shoppingOrderPo.getProcess();
		this.isPay = shoppingOrderPo.getIsPay();
		this.createTimeStr = DateUtil.Format("yyyy-MM-dd", shoppingOrderPo.getCreateTime());
		this.updateDate = DateUtil.Format("yyyy-MM-dd HH:mm:ss", shoppingOrderPo.getUpdateTime());
		this.totalFee = shoppingOrderPo.getTotalFee();
		this.orderNum = shoppingOrderPo.getOrderNum();
		this.orderTsStr = DateUtil.Format("yyyy-MM-dd HH:mm:ss", new Date(shoppingOrderPo.getOrderTs()));
		this.orderType = shoppingOrderPo.getOrderType();
		this.expressName = shoppingOrderPo.getExpressName();
		this.expressNum = shoppingOrderPo.getExpressNum();
		this.automaticTs = shoppingOrderPo.getAutomaticTs();
		this.boughtAddressId = shoppingOrderPo.getBoughtAddressId();
		this.payType = shoppingOrderPo.getPayType();
		this.expressNameView = shoppingOrderPo.getExpressNameView();
		this.scoreTotal = shoppingOrderPo.getScoreTotal();
		this.proportion = shoppingOrderPo.getProportion();
		this.realTotalFee = shoppingOrderPo.getRealTotalFee();
	}
	private void toAll(ShoppingOrderDO shoppingOrderPo) {
		this.id = shoppingOrderPo.getId();
		this.shoppingOrderId = shoppingOrderPo.getId();
		this.boughtUserId = shoppingOrderPo.getBoughtUserId();
		this.process = shoppingOrderPo.getProcess();
		this.isPay = shoppingOrderPo.getIsPay();
		this.createTimeStr = DateUtil.Format("yyyy-MM-dd", shoppingOrderPo.getCreateTime());
		this.updateDate = DateUtil.Format("yyyy-MM-dd HH:mm:ss", shoppingOrderPo.getUpdateTime());
		this.totalFee = shoppingOrderPo.getTotalFee();
		this.orderNum = shoppingOrderPo.getOrderNum();
		this.orderTsStr = DateUtil.Format("yyyy-MM-dd HH:mm:ss", new Date(shoppingOrderPo.getOrderTs()));
		this.orderType = shoppingOrderPo.getOrderType();
		this.expressName = shoppingOrderPo.getExpressName();
		this.expressNum = shoppingOrderPo.getExpressNum();
		this.automaticTs = shoppingOrderPo.getAutomaticTs();
		this.boughtAddressId = shoppingOrderPo.getBoughtAddressId();
		this.payType = shoppingOrderPo.getPayType();
		this.expressNameView = shoppingOrderPo.getExpressNameView();
		this.scoreTotal = shoppingOrderPo.getScoreTotal();
		this.proportion = shoppingOrderPo.getProportion();
		this.realTotalFee = shoppingOrderPo.getRealTotalFee();
	}
	
	public ShoppingOrderBO(Integer id, Integer process, Long orderNum,
			String username, String consignee, String consigneePhone,
			String address, String orderTime, String app, String message,
			String expressNum, String expressName, String newAddress,ShoppingOrderDO shoppingOrderPo) {
		super();
		this.id = id;
		this.process = process;
		this.orderNum = orderNum;
		this.username = username;
		this.consignee = consignee;
		this.consigneePhone = consigneePhone;
		this.address = address;
		this.orderTime = orderTime;
		this.app = app;
		this.message = message;
		this.expressNum = expressNum;
		this.expressName = expressName;
		this.newAddress = newAddress;
		this.shoppingOrderPo = shoppingOrderPo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProcess() {
		return process;
	}
	public void setProcess(Integer process) {
		this.process = process;
	}
	public Long getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Long orderNum) {
		this.orderNum = orderNum;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getConsigneePhone() {
		return consigneePhone;
	}
	public void setConsigneePhone(String consigneePhone) {
		this.consigneePhone = consigneePhone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getApp() {
		return app;
	}
	public void setApp(String app) {
		this.app = app;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getExpressNum() {
		return expressNum;
	}
	public void setExpressNum(String expressNum) {
		this.expressNum = expressNum;
	}
	public String getExpressName() {
		return expressName;
	}
	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}
	public String getNewAddress() {
		return newAddress;
	}
	public void setNewAddress(String newAddress) {
		this.newAddress = newAddress;
	}
	public ShoppingOrderDO getShoppingOrderPo() {
		return shoppingOrderPo;
	}
	public void setShoppingOrderPo(ShoppingOrderDO shoppingOrderPo) {
		this.shoppingOrderPo = shoppingOrderPo;
	}

	public Integer getBoughtUserId() {
		return boughtUserId;
	}

	public void setBoughtUserId(Integer boughtUserId) {
		this.boughtUserId = boughtUserId;
	}

	public Integer getBoughtAddressId() {
		return boughtAddressId;
	}

	public void setBoughtAddressId(Integer boughtAddressId) {
		this.boughtAddressId = boughtAddressId;
	}

	public Double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getIsPay() {
		return isPay;
	}

	public void setIsPay(Integer isPay) {
		this.isPay = isPay;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getOrderTsStr() {
		return orderTsStr;
	}

	public void setOrderTsStr(String orderTsStr) {
		this.orderTsStr = orderTsStr;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public String getExpressNameView() {
		return expressNameView;
	}

	public void setExpressNameView(String expressNameView) {
		this.expressNameView = expressNameView;
	}

	public Integer getScoreTotal() {
		return scoreTotal;
	}

	public void setScoreTotal(Integer scoreTotal) {
		this.scoreTotal = scoreTotal;
	}

	public Double getProportion() {
		return proportion;
	}

	public void setProportion(Double proportion) {
		this.proportion = proportion;
	}

	public Double getRealTotalFee() {
		return realTotalFee;
	}

	public void setRealTotalFee(Double realTotalFee) {
		this.realTotalFee = realTotalFee;
	}

	public Long getAutomaticTs() {
		return automaticTs;
	}

	public void setAutomaticTs(Long automaticTs) {
		this.automaticTs = automaticTs;
	}

	public List<ShoppingOrderItemBO> getShoppingOrderItemVoList() {
		return shoppingOrderItemVoList;
	}

	public void setShoppingOrderItemVoList(List<ShoppingOrderItemBO> shoppingOrderItemVoList) {
		this.shoppingOrderItemVoList = shoppingOrderItemVoList;
	}

	public String getServicePhone() {
		return servicePhone;
	}

	public void setServicePhone(String servicePhone) {
		this.servicePhone = servicePhone;
	}

	public Integer getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}

	public Integer getShoppingOrderId() {
		return shoppingOrderId;
	}

	public void setShoppingOrderId(Integer shoppingOrderId) {
		this.shoppingOrderId = shoppingOrderId;
	}

	public String getAccountPhone() {
		return accountPhone;
	}

	public void setAccountPhone(String accountPhone) {
		this.accountPhone = accountPhone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
