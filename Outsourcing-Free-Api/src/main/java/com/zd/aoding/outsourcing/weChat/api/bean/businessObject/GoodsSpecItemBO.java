package com.zd.aoding.outsourcing.weChat.api.bean.businessObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.config.Config;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsSpecItemDO;

@ApiModel(value = "", description = "账号展示类")
public class GoodsSpecItemBO {

    /** id **/
    private Integer id;
    private Integer goodsSpecItemId;
    /** spec_Id **/
    @ApiModelProperty("产品配置id")
    private Integer specId;
    /** 配置名称 title **/
    @ApiModelProperty("产品配置明细名称")
    private String title;
    @ApiModelProperty("图片")
    private String img;
    @ApiModelProperty("完整路径图片")
    private String imgUrl;
    
	public GoodsSpecItemBO(GoodsSpecItemDO goodsSpecItemPo) {
	    StringBuilder sb = new StringBuilder(Config.IMG_SERVER).append(goodsSpecItemPo.getThumb());
	    this.imgUrl = sb.toString();
	    this.img = goodsSpecItemPo.getThumb();
	    this.id = goodsSpecItemPo.getId();
	    this.goodsSpecItemId = goodsSpecItemPo.getId();
	    this.specId = goodsSpecItemPo.getSpecId();
	    this.title = goodsSpecItemPo.getTitle();
	}

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSpecId() {
        return specId;
    }

    public void setSpecId(Integer specId) {
        this.specId = specId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getGoodsSpecItemId() {
		return goodsSpecItemId;
	}

	public void setGoodsSpecItemId(Integer goodsSpecItemId) {
		this.goodsSpecItemId = goodsSpecItemId;
	}

	@Override
    public String toString() {
        return "GoodsSpecItemVo [id=" + id + ", specId=" + specId + ", title="
                + title + ", img=" + img + ", imgUrl=" + imgUrl + "]";
    }

}
