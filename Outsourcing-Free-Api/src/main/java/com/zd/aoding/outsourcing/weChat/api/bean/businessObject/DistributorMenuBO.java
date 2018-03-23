package com.zd.aoding.outsourcing.weChat.api.bean.businessObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.DistributorMenuDO;

@ApiModel(value = "", description = "菜单类")
public class DistributorMenuBO {
	@ApiModelProperty("pId")
	private Integer pId;
	@ApiModelProperty("pName")
	private String pName;
	@ApiModelProperty("erId")
	private Integer erId;
	@ApiModelProperty("url")
	private String url;
	@ApiModelProperty("falg")
	private Integer falg;

	public DistributorMenuBO(DistributorMenuDO distributorMenuDO) {
		this.pId = distributorMenuDO.getPid();
		this.pName = distributorMenuDO.getpName();
		this.erId = distributorMenuDO.getErId();
		this.url = distributorMenuDO.getUrl();
		this.falg = distributorMenuDO.getFalg();
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public Integer getErId() {
		return erId;
	}

	public void setErId(Integer erId) {
		this.erId = erId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getFalg() {
		return falg;
	}

	public void setFalg(Integer falg) {
		this.falg = falg;
	}
}
