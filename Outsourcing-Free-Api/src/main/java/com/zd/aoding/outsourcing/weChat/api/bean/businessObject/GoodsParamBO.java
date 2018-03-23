package com.zd.aoding.outsourcing.weChat.api.bean.businessObject;


import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsParamDO;

@ApiModel(value = "", description = "产品展示类")
public class GoodsParamBO {
	private Integer goodsParamId;
	private GoodsParamDO goodsParamPo;
	@ApiModelProperty("list当前第几个")
	private Integer sort;

	public GoodsParamBO(GoodsParamDO goodsParamPo) {
		super();
		this.goodsParamId = goodsParamPo.getId();
		this.goodsParamPo = goodsParamPo;
	}
	public GoodsParamBO() {
		super();
	}
	public Integer getGoodsParamId() {
        return goodsParamId;
    }
    public void setGoodsParamId(Integer goodsParamId) {
        this.goodsParamId = goodsParamId;
    }
    public GoodsParamDO getGoodsParamPo() {
        return goodsParamPo;
    }
    public void setGoodsParamPo(GoodsParamDO goodsParamPo) {
        this.goodsParamPo = goodsParamPo;
    }
    public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
    @Override
    public String toString() {
        return "GoodsParamVo [goodsParamId=" + goodsParamId + ", goodsParamPo="
                + goodsParamPo + ", sort=" + sort + "]";
    }
	
}
