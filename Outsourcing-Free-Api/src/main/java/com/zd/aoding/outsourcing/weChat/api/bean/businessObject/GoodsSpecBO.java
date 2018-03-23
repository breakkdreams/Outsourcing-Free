package com.zd.aoding.outsourcing.weChat.api.bean.businessObject;

import java.util.List;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsSpecDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsSpecItemDO;

@ApiModel(value = "", description = "产品展示类")
public class GoodsSpecBO {
	private Integer goodsSpecId;
	private GoodsSpecDO goodsSpecPo;
	@ApiModelProperty("list当前第几个")
	private Integer sort;
	@ApiModelProperty("产品配置明细")
	private List<GoodsSpecItemBO> listGoodsSpecItem;

	public GoodsSpecBO(GoodsSpecDO goodsSpecPo) {
		super();
		this.goodsSpecId = goodsSpecPo.getId();
		this.goodsSpecPo = goodsSpecPo;
	}
	public GoodsSpecBO() {
		super();
	}
	public Integer getGoodsSpecId() {
		return goodsSpecId;
	}
	public void setGoodsSpecId(Integer goodsSpecId) {
		this.goodsSpecId = goodsSpecId;
	}
	public GoodsSpecDO getGoodsSpecPo() {
		return goodsSpecPo;
	}
	public void setGoodsSpecPo(GoodsSpecDO goodsSpecPo) {
		this.goodsSpecPo = goodsSpecPo;
	}
	public List<GoodsSpecItemBO> getListGoodsSpecItem() {
		return listGoodsSpecItem;
	}
	public void setListGoodsSpecItem(List<GoodsSpecItemBO> listGoodsSpecItem) {
		this.listGoodsSpecItem = listGoodsSpecItem;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
}
