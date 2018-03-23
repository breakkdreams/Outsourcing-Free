package com.zd.aoding.outsourcing.weChat.api.bean.businessObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.config.Config;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.BannerDO;

@ApiModel(value = "", description = "banner类")
public class BannerBO {
	@ApiModelProperty("bannerid")
	private Integer bannerId;
	@ApiModelProperty("完整路径图片")
	private String imgUrl;
	@ApiModelProperty("所属分区")
	private String positionStr;
	@ApiModelProperty("所属分区id")
	private Integer position;
	@ApiModelProperty("轮播图类型")
	private String bannerType;
	@ApiModelProperty("轮播图内容")
	private String content;
	@ApiModelProperty("超链接")
	private String hyperlink;
	@ApiModelProperty("对象id")
	private Integer owerId;
	@ApiModelProperty("类型")
	private String type;
	private String coverImgUrl;

	public BannerBO(BannerDO bannerPo) {
		this.bannerId = bannerPo.getId();
		this.position = bannerPo.getPosition();
		this.type = bannerPo.getType();
		this.imgUrl = Config.IMG_SERVER + bannerPo.getCoverImgUrl();
		this.coverImgUrl = Config.IMG_SERVER + bannerPo.getCoverImgUrl();
		switch (bannerPo.getType()) {
        case BannerDO.TYPE_Default:
            this.bannerType = "纯图片";
            break;
        case BannerDO.TYPE_Hyperlink:
            this.bannerType = "超链接";
            this.hyperlink = bannerPo.getHyperlink();
            break;
        case BannerDO.TYPE_NewsNoticePo_category:
            this.bannerType = "分类广告";
            this.owerId = bannerPo.getOwerId();
            break;
        case BannerDO.TYPE_NewsNoticePo_goods:
            this.bannerType = "产品广告";
            this.owerId = bannerPo.getOwerId();
            break;
        case BannerDO.TYPE_NewsNoticePo_inside:
        	this.bannerType = "内部广告";
        	this.content = bannerPo.getContent();
        	break;
        default:
            break;
        }
		if(bannerPo.getPosition() == BannerDO.position_index){
		    this.positionStr = "首页";
		}else if(bannerPo.getPosition() == BannerDO.position_score){
		    this.positionStr = "积分";
		}else{
		    this.positionStr = "奖金";
		}
		
	}

	public Integer getBannerId() {
		return bannerId;
	}

	public void setBannerId(Integer bannerId) {
		this.bannerId = bannerId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getPositionStr() {
		return positionStr;
	}

	public void setPositionStr(String positionStr) {
		this.positionStr = positionStr;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public String getBannerType() {
		return bannerType;
	}

	public void setBannerType(String bannerType) {
		this.bannerType = bannerType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getHyperlink() {
		return hyperlink;
	}

	public void setHyperlink(String hyperlink) {
		this.hyperlink = hyperlink;
	}

	public Integer getOwerId() {
		return owerId;
	}

	public void setOwerId(Integer owerId) {
		this.owerId = owerId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCoverImgUrl() {
		return coverImgUrl;
	}

	public void setCoverImgUrl(String coverImgUrl) {
		this.coverImgUrl = coverImgUrl;
	}
	
}
