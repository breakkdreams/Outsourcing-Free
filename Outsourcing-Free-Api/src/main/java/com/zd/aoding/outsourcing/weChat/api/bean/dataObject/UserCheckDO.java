package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.base.DO.base.DOBase;

@ApiModel(value = "", description = "用户是否查看确认")
public class UserCheckDO extends DOBase {
	/**
	 * 是否查看NewsNoticePo（ 新闻资讯公告）
	 */
	public final static String TYPE_NewsNoticePo = "NewsNoticePo";

	@ApiModelProperty("协议id")
	private Integer id;
	@ApiModelProperty("用户id")
	private Integer userId;
	@ApiModelProperty("类型：新闻资讯公告=‘NewsNoticePo’")
	private Integer type;
	@ApiModelProperty("被查看对象id")
	private Integer checkPoId;

	public UserCheckDO() {
		super();
	}

	public UserCheckDO(Integer userId, Integer type, Integer checkPoId) {
		super();
		this.userId = userId;
		this.type = type;
		this.checkPoId = checkPoId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getCheckPoId() {
		return checkPoId;
	}

	public void setCheckPoId(Integer checkPoId) {
		this.checkPoId = checkPoId;
	}

	@Override
	public String toString() {
		return "UserCheckDO [id=" + id + ", userId=" + userId + ", type=" + type + ", checkPoId=" + checkPoId
				+ ", toString()=" + super.toString() + "]";
	}

}
