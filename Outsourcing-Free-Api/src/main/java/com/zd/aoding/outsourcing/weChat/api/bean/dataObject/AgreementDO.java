package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.base.DO.base.DOBase;

/**
 * @author zj
 *
 */
@ApiModel(value = "", description = "协议、说明性文本或前台展示文本")
public class AgreementDO extends DOBase {
	/**
	 * 注册风险提示
	 */
	public final static int TYPE_ZHUCEFENGXIANTISHI = 1;
	/**
	 * 注册协议
	 */
	public final static int TYPE_ZHUCEXIEYI = 2;
	/**
	 * 常见问题解答
	 */
	public final static int TYPE_CHANGJIANWENTIJIEDA = 3;

	/**
	 * 新用户注册
	 */
	public final static int TYPE_XINYONGHUZHUCE = 4;

	@ApiModelProperty("协议id")
	private Integer id;
	@ApiModelProperty("类型：1-风险提示；2-注册协议；3 常见问题解答；")
	private Integer type;
	@ApiModelProperty("标题")
	private String title;// type对应的文字（例：type==1；title==风险提示）
	@ApiModelProperty("内容")
	private String content;// kingEdit
	@ApiModelProperty("appCode")
	private String appCode;// kingEdit

	public AgreementDO() {
		super();
	}

	public AgreementDO(Integer type, String title, String content) {
		super();
		this.type = type;
		this.title = title;
		this.content = content;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    @Override
    public String toString() {
        return "AgreementDO [id=" + id + ", type=" + type + ", title=" + title
                + ", content=" + content + ", appCode=" + appCode + "]";
    }

}
