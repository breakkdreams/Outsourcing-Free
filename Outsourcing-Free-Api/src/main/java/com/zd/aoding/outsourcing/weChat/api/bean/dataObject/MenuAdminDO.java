package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "", description = "按钮，菜单")
public class MenuAdminDO extends BasePo {
  @ApiModelProperty("按钮，菜单id,主菜单为0")
  private Integer pid;
  @ApiModelProperty("按钮名")
  private String pName;
  @ApiModelProperty("二级ID")
  private Integer erId;
  @ApiModelProperty("链接")
  private String url;
  @ApiModelProperty("级别标识")
  private Integer falg;
  @ApiModelProperty("图标")
  private String tubiao;
  @ApiModelProperty("排序值")
  private Integer sort;

	public MenuAdminDO() {
		super();
	}
	public String getTubiao() {
		return tubiao;
	}
	public void setTubiao(String tubiao) {
		this.tubiao = tubiao;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
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
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
public MenuAdminDO( String pName, Integer erId, String url, Integer falg, String tubiao) {
	super();
	this.pName = pName;
	this.erId = erId;
	this.url = url;
	this.falg = falg;
	this.tubiao = tubiao;
}
@Override
public String toString() {
	return "DistributorMenuDO [pid=" + pid + ", pName=" + pName + ", erId=" + erId + ", url=" + url + ", falg=" + falg
			+ ", tubiao=" + tubiao + "]";
}
}
