package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.base.DO.base.DOBase;

@ApiModel(value = "", description = "产品评价")
public class GoodsCommentDO extends DOBase {
	
	public final static Integer REPLY_STATE_NO = 0;//为回复
	public final static Integer REPLY_STATE_YES = 1;//已回复
	
	@ApiModelProperty("评价表id")
	private Integer id;
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
	@ApiModelProperty("回复内容")
	private String replyContent;
	@ApiModelProperty("回复状态0未回复.1已回复")
	private Integer replyState;
	
	public GoodsCommentDO() {
		super();
	}

	public GoodsCommentDO(Integer userId, Integer orderId, Integer orderItemId, Integer goodsId, String content,
			Double qualityScore, Double logisticsScore,Integer replyState) {
		super();
		this.userId = userId;
		this.orderId = orderId;
		this.orderItemId = orderItemId;
		this.goodsId = goodsId;
		this.content = content;
		this.qualityScore = qualityScore;
		this.logisticsScore = logisticsScore;
		this.replyState = 0;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "GoodsCommentDO [id=" + id + ", userId=" + userId + ", orderId=" + orderId + ", orderItemId="
				+ orderItemId + ", goodsId=" + goodsId + ", content=" + content + ", qualityScore=" + qualityScore
				+ ", logisticsScore=" + logisticsScore + ", toString()=" + super.toString() + "]";
	}
}
