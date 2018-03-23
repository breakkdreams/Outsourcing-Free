package com.zd.aoding.outsourcing.weChat.api.bean.businessObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.config.Config;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsCommentDO;

@ApiModel(value = "", description = "产品评价")
public class GoodsCommentBO {
	@ApiModelProperty("评价id")
	private Integer goodsCommentId;
	@ApiModelProperty("用户")
	private Integer userId;
	@ApiModelProperty("订单id")
	private Integer orderId;
	@ApiModelProperty("订单详情id")
	private Integer orderItemId;
	@ApiModelProperty("商品id")
	private Integer goodsId;
	@ApiModelProperty("评价内容")
	private String content;
	@ApiModelProperty("产品质量评分")
	private Double qualityScore;
	@ApiModelProperty("物流评分")
	private Double logisticsScore;
	@ApiModelProperty("产品名称")
	private String goodsName;
	@ApiModelProperty("回复内容")
	private String replyContent;
	@ApiModelProperty("回复状态0未回复.1已回复")
	private Integer replyState;

	public GoodsCommentBO() {
		super();
	}
	public GoodsCommentBO(GoodsCommentDO goodsCommentPo) {
		this.goodsCommentId = goodsCommentPo.getId();
		this.userId = goodsCommentPo.getUserId();
		this.orderId = goodsCommentPo.getOrderId();
		this.orderItemId = goodsCommentPo.getOrderItemId();
		this.goodsId = goodsCommentPo.getGoodsId();
		this.content = goodsCommentPo.getContent();
		this.qualityScore = goodsCommentPo.getQualityScore();
		this.logisticsScore = goodsCommentPo.getLogisticsScore();
		this.replyContent = goodsCommentPo.getReplyContent();
		this.replyState = goodsCommentPo.getReplyState();
	}
	public Integer getGoodsCommentId() {
		return goodsCommentId;
	}
	public void setGoodsCommentId(Integer goodsCommentId) {
		this.goodsCommentId = goodsCommentId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public Integer getReplyState() {
		return replyState;
	}
	public void setReplyState(Integer replyState) {
		this.replyState = replyState;
	}
}
