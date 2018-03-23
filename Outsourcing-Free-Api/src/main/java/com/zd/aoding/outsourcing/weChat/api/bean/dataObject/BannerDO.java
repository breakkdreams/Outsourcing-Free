package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.base.DO.base.DOBase;

@ApiModel(value = "", description = "banner")
public class BannerDO extends DOBase {
	/**
	 * 纯图片
	 */
	public final static String TYPE_Default = "None";
	/**
	 * 资讯
	 */
	public final static String TYPE_NewsNoticePo_inside = "inside";// 内部广告
	public final static String TYPE_NewsNoticePo_goods = "goods";// 产品广告
	public final static String TYPE_NewsNoticePo_category = "category";// 分类广告
	/** position */
	public final static Integer position_index = 1;//首页
	public final static Integer position_score = 2;// 积分区
	public final static Integer position_bonus = 3;// 奖金区
	/**
	 * 超链接
	 */
	public final static String TYPE_Hyperlink = "Hyperlink";
	/**
	 * 预售币种
	 */
	/*public final static String TYPE_PreSale = "PreSale";*/
	/**
	 * 位置首页
	 *//*
	public final static String POSITION_INDEX = "INDEX";
	*//**
	 * 位置-货币交易
	 *//*
	public final static String POSITION_XNBSale = "XNBSale";*/
	@ApiModelProperty("id")
	private Integer id;
	@ApiModelProperty("appCode")
	private String appCode;
	@ApiModelProperty("类型：纯图片==‘None’；内部广告==‘inside’；产品==‘goods’；分类==‘category’；超链接==‘Hyperlink’")
	private String type;
	// @ApiModelProperty("类型：1媒体报道；2官方公告；3市场动态")
	// private Integer secondType;
	@ApiModelProperty("归属对象id（暂时为资讯id）")
	private Integer owerId;

	@ApiModelProperty("展示图片")
	private String coverImgUrl;
	/*@ApiModelProperty("位置（首页==INDEX；货币交易==XNBSale）")
	private String position;*/
	@ApiModelProperty("超链接")
	private String hyperlink;
	@ApiModelProperty("内容")
	private String content;
	@ApiModelProperty("1首页 2积分区 3奖金区")
	private Integer position;

	public BannerDO() {
		super();
	}

	public BannerDO(String type, Integer owerId, String coverImgUrl, String hyperlink, String appCode) {
		super();
		this.type = type;
		this.owerId = owerId;
		this.coverImgUrl = coverImgUrl;
		this.hyperlink = hyperlink;
		this.appCode = appCode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getOwerId() {
		return owerId;
	}

	public void setOwerId(Integer owerId) {
		this.owerId = owerId;
	}

	public String getCoverImgUrl() {
		return coverImgUrl;
	}

	public void setCoverImgUrl(String coverImgUrl) {
		this.coverImgUrl = coverImgUrl;
	}

	public String getHyperlink() {
		return hyperlink;
	}

	public void setHyperlink(String hyperlink) {
		this.hyperlink = hyperlink;
	}
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "BannerDO [id=" + id + ", appCode=" + appCode + ", type=" + type
				+ ", owerId=" + owerId + ", coverImgUrl=" + coverImgUrl
				+ ", hyperlink=" + hyperlink + ", content=" + content
				+ ", position=" + position + "]";
	}


}
