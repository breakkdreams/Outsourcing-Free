package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.base.DO.base.DOBase;

public class SystemparamDO extends DOBase{
	@ApiModelProperty("id")
    private Integer systemParamId;
	@ApiModelProperty("参数名称")
    private String name;
	@ApiModelProperty("参数类型")
    private Integer type;
	@ApiModelProperty("参数code")
    private String code;
	@ApiModelProperty("一般参数取值（或者默认值）")
    private Integer intVale;
	@ApiModelProperty("一般参数取值（或者默认值）")
    private String stringVale;
	
	public Integer getSystemParamId() {
		return systemParamId;
	}
	public void setSystemParamId(Integer systemParamId) {
		this.systemParamId = systemParamId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getIntVale() {
		return intVale;
	}
	public void setIntVale(Integer intVale) {
		this.intVale = intVale;
	}
	public String getStringVale() {
		return stringVale;
	}
	public void setStringVale(String stringVale) {
		this.stringVale = stringVale;
	}
	@Override
	public String toString() {
		return "SystemparamDO [systemParamId=" + systemParamId + ", name="
				+ name + ", type=" + type + ", code=" + code + ", intVale="
				+ intVale + ", stringVale=" + stringVale + ", toString()="
				+ super.toString() + "]";
	}
}