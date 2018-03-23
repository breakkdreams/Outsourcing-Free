package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.base.DO.base.DOBase;

@ApiModel(value = "", description = "产品配置详情描述")
public class GoodsSpecItemDO extends DOBase {
	/** id **/
	private Integer id;
	/** spec_Id **/
	@ApiModelProperty("产品配置id")
	private Integer specId;
	/** 配置名称 title **/
	@ApiModelProperty("产品配置明细名称")
	private String title;
	
	@ApiModelProperty("产品配置明是否可选展示（1是0否）")
	private Integer isShow;
	/** 保留 **/
	private String thumb;
	/** 保留 **/
	private Integer displayorder;
	/** 保留 **/
	private Integer virtual;
	/** 保留 **/
	private String valueId;

	
	
	
	

	public GoodsSpecItemDO(Integer specId, String title) {
		super();
		this.specId = specId;
		this.title = title;
		this.displayorder = 0;
	}
	public GoodsSpecItemDO() {
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
	/** spec_Id **/
	public Integer getSpecId() {
		return specId;
	}
	/** spec_Id **/
	public void setSpecId(Integer specId) {
		this.specId = specId;
	}
	/** 配置名称 title **/
	public String getTitle() {
		return title;
	}
	/** 配置名称 title **/
	public void setTitle(String title) {
		this.title = title;
	}
	/** thumb **/
	public String getThumb() {
		return thumb;
	}
	/** thumb **/
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	/** displayorder **/
	public Integer getDisplayorder() {
		return displayorder;
	}
	/** displayorder **/
	public void setDisplayorder(Integer displayorder) {
		this.displayorder = displayorder;
	}
	/** virtual **/
	public Integer getVirtual() {
		return virtual;
	}
	/** virtual **/
	public void setVirtual(Integer virtual) {
		this.virtual = virtual;
	}
	/** value_Id **/
	public String getValueId() {
		return valueId;
	}
	/** value_Id **/
	public void setValueId(String valueId) {
		this.valueId = valueId;
	}
	/** show **/
	public Integer getIsShow() {
		return isShow;
	}
	/** show **/
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}
}
