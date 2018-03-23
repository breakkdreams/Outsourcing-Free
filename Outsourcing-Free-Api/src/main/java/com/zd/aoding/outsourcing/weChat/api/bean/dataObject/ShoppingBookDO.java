package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import com.zd.aoding.base.DO.base.DOBase;

public class ShoppingBookDO extends DOBase {
	/** b2c订单簿（实际记录订单每次下单生成订单簿（可由多个、单个订单生成））自增id id **/
	public final static Integer type_Shopping = 1;
	/**
	 * @fieldName: type_B
	 * @fieldType: int
	 * @Description: 商城币充值
	 */
	public final static Integer type_change_money = 2;
	// 默认未支付（下单）
	public final static int status_default = 0;
	// 完成支付
	public final static int status_Paid = 1;
	private Integer id;
	/** 订单提交参数 param **/
	private String param;
	/** appCode **/
	private String appCode;
	/** bought_Account_Id **/
	private Integer boughtUserId;
	/** bought_Address_Id **/
	private Integer boughtAddressId;
	/** 总价 total_Fee **/
	private Double totalFee;
	/** 订单摘要 summary **/
	private String summary;
	/** type **/
	private Integer type;
	/** status **/
	private Integer status;

	public ShoppingBookDO(String param, String appCode, Integer boughtUserId, Integer boughtAddressId, Double totalFee,
			String summary, Integer type) {
		super();
		this.param = param;
		this.appCode = appCode;
		this.boughtUserId = boughtUserId;
		this.boughtAddressId = boughtAddressId;
		this.totalFee = totalFee;
		this.summary = summary;
		this.status = status_default;
		this.type = type;
	}

	public ShoppingBookDO() {
		super();
	}

	/** b2c订单簿（实际记录订单每次下单生成订单簿（可由多个、单个订单生成））自增id id **/
	public Integer getId() {
		return id;
	}

	/** b2c订单簿（实际记录订单每次下单生成订单簿（可由多个、单个订单生成））自增id id **/
	public void setId(Integer id) {
		this.id = id;
	}

	/** 订单提交参数 param **/
	public String getParam() {
		return param;
	}

	/** 订单提交参数 param **/
	public void setParam(String param) {
		this.param = param;
	}

	/** bought_Account_Id **/
	public Integer getboughtUserId() {
		return boughtUserId;
	}

	/** bought_Account_Id **/
	public void setboughtUserId(Integer boughtUserId) {
		this.boughtUserId = boughtUserId;
	}

	/** bought_Address_Id **/
	public Integer getBoughtAddressId() {
		return boughtAddressId;
	}

	/** bought_Address_Id **/
	public void setBoughtAddressId(Integer boughtAddressId) {
		this.boughtAddressId = boughtAddressId;
	}

	/** 总价 total_Fee **/
	public Double getTotalFee() {
		return totalFee;
	}

	/** 总价 total_Fee **/
	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}

	/** 订单摘要 summary **/
	public String getSummary() {
		return summary;
	}

	/** 订单摘要 summary **/
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/** type **/
	public Integer getType() {
		return type;
	}

	/** type **/
	public void setType(Integer type) {
		this.type = type;
	}

	/** status **/
	public Integer getStatus() {
		return status;
	}

	/** status **/
	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	@Override
	public String toString() {
		return "ShoppingBookDO [id=" + id + ", param=" + param + ", boughtUserId=" + boughtUserId + ", boughtAddressId="
				+ boughtAddressId + ", totalFee=" + totalFee + ", summary=" + summary + ", type=" + type + ", status="
				+ status + ", toString()=" + super.toString() + "]";
	}
}
