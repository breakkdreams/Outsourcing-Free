package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.base.DO.base.DOBase;

@ApiModel(value = "", description = "产品配置名称")
public class GoodsSpecDO extends DOBase {
	/** id **/
	private Integer id;
	/** goods_Id **/
	@ApiModelProperty("产品id")
	private Integer goodsId;
	/** 配置名称 title **/
	@ApiModelProperty("产品名称")
	private String title;
	/** 保留 **/
	private Integer displaytype;
	/** 保留 **/
	private Integer isShow;
	/** 保留 **/
	private String content;
	/** 保留 **/
	private String description;
	/** 保留 **/
	private Integer displayorder;

	public GoodsSpecDO(Integer goodsId, String title) {
		super();
		this.goodsId = goodsId;
		this.title = title;
		this.displaytype = 0;
	}
	public GoodsSpecDO() {
		super();
	}
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
	/** 配置名称 title **/
	public String getTitle() {
		return title;
	}
	/** 配置名称 title **/
	public void setTitle(String title) {
		this.title = title;
	}
	/** displaytype **/
	public Integer getDisplaytype() {
		return displaytype;
	}
	/** displaytype **/
	public void setDisplaytype(Integer displaytype) {
		this.displaytype = displaytype;
	}
	/** content **/
	public String getContent() {
		return content;
	}
	/** content **/
	public void setContent(String content) {
		this.content = content;
	}
	/** description **/
	public String getDescription() {
		return description;
	}
	/** description **/
	public void setDescription(String description) {
		this.description = description;
	}
	/** displayorder **/
	public Integer getDisplayorder() {
		return displayorder;
	}
	/** displayorder **/
	public void setDisplayorder(Integer displayorder) {
		this.displayorder = displayorder;
	}
    public Integer getIsShow() {
        return isShow;
    }
    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }
    @Override
    public String toString() {
        return "GoodsSpecDO [id=" + id + ", goodsId=" + goodsId + ", title="
                + title + ", displaytype=" + displaytype + ", isShow=" + isShow
                + ", content=" + content + ", description=" + description
                + ", displayorder=" + displayorder + "]";
    }
}
