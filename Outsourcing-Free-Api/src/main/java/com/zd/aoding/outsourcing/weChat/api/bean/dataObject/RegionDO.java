package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.base.DO.base.DOBase;

public class RegionDO extends DOBase {
	@ApiModelProperty("id")
    private Integer regionId;
	@ApiModelProperty("上级id ")
    private Integer parentId;
	@ApiModelProperty("地区的名字 ")
    private String regionName;
	@ApiModelProperty("类型")
    private Integer regionType;
	public Integer getRegionId() {
		return regionId;
	}
	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public Integer getRegionType() {
		return regionType;
	}
	public void setRegionType(Integer regionType) {
		this.regionType = regionType;
	}
	@Override
	public String toString() {
		return "RegionDO [regionId=" + regionId + ", parentId=" + parentId
				+ ", regionName=" + regionName + ", regionType=" + regionType
				+ ", toString()=" + super.toString() + "]";
	}

}