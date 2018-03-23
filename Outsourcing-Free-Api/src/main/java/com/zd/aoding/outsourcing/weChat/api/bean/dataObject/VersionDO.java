package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.base.DO.base.DOBase;

public class VersionDO extends DOBase{
	@ApiModelProperty("id")
    private Integer versionId;
	@ApiModelProperty("版本号")
    private String versionNum;
	@ApiModelProperty("标示")
    private Integer biaoShi;
	
	public Integer getVersionId() {
		return versionId;
	}
	public void setVersionId(Integer versionId) {
		this.versionId = versionId;
	}
	public String getVersionNum() {
		return versionNum;
	}
	public void setVersionNum(String versionNum) {
		this.versionNum = versionNum;
	}
	public Integer getBiaoShi() {
		return biaoShi;
	}
	public void setBiaoShi(Integer biaoShi) {
		this.biaoShi = biaoShi;
	}
}