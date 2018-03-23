package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import java.util.Date;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.common.uuid.UUIDUtils;

/**
 * @ClassName: BaceBean
 * @Description: 所有实体类的基类
 * @author zhangj
 * @date 2016年8月15日 下午1:42:40
 */
@ApiModel(value = "", description = "所有实体类的基类")
public class BasePo {
	@ApiModelProperty(value = "对象id", hidden = true)
	private String objectId;
	@ApiModelProperty("创建时间")
	private Date createTime;
	@ApiModelProperty("最近一次更新时间")
	private Date updateTime;
	@ApiModelProperty("删除标示符（0默认1删除）")
	private Integer deleted;

	/**
	 * @Title: insertInit
	 * @Description:插入初始化
	 * @return: void
	 */
	public void insertInit() {
		this.setCreateTime(new Date());
		this.setDeleted(0);
		this.setObjectId(UUIDUtils.getUUID());
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "BaseBean [ deleted=" + deleted + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}
}
