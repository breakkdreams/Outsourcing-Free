package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.base.DO.base.DOBase;

@ApiModel(value = "", description = "通知")
public class NoticeDO extends DOBase {
	/**
	 * 下单通知
	 */
	public final static int type_xiadan = 1;
	/**
	 * 发货通知
	 */
	public final static int type_fahuo = 2;
	/**
	 * 收货通知
	 */
	public final static int type_shouhuo = 3;
	/**
	 * 付款通知
	 */
	public final static int type_fukuan = 4;
	/**
	 * 经销商公告
	 */
	public final static int type_distributor = 5;
	/**
	 * 商家公告
	 */
	public final static int type_business = 6;
	/**
	 * 系统通知
	 */
	public final static int type_system = 0;
	/**
	 * 已读
	 */
	public final static int status_read = 1;
	/**
     * 未读
     */
    public final static int status_default = 0;
    /** 公告id  id **/
	@ApiModelProperty(value = "通知id", hidden = true)
    private Integer id;
	@ApiModelProperty(value = "appCode")
	private String appCode;
	@ApiModelProperty(value = "userId")
	private Integer userId;

    /** 通知类型 type **/
	@ApiModelProperty(value = "通知类型 1下单通知 2发货通知 3收货通知  0系统通知")
    private Integer type;

    /** 文章标题  title **/
	@ApiModelProperty(value = "文章标题")
    private String title;
	
	/** 新闻图片  logo **/
	@ApiModelProperty(value = "新闻logo")
	private String logo;

    /** 概述内容  summary **/
	@ApiModelProperty(value = "概述内容")
    private String summary;

    /** 内容详情（html）  content **/
	@ApiModelProperty(value = "内容详情")
    private String content;
	@ApiModelProperty(value = "通知状态0未读1已读")
	private Integer status;
	
	
    public NoticeDO(Integer type, String title, String summary, String content ,String logo, String appCode) {
		super();
		this.type = type;
		this.title = title;
		this.summary = summary;
		this.content = content;
		this.logo = logo;
		this.appCode = appCode;
	}
    
	public NoticeDO() {
		super();
	}

	/**   公告id  id   **/
    public Integer getId() {
        return id;
    }

    /**   公告id  id   **/
    public void setId(Integer id) {
        this.id = id;
    }

    /**   公告类型（保留）  type   **/
    public Integer getType() {
        return type;
    }

    /**   公告类型（保留）  type   **/
    public void setType(Integer type) {
        this.type = type;
    }

    /**   文章标题  title   **/
    public String getTitle() {
        return title;
    }

    /**   文章标题  title   **/
    public void setTitle(String title) {
        this.title = title;
    }
    /**   新闻图片  logo   **/
    public String getLogo() {
    	return logo;
    }
    
    /**   新闻图片  logo   **/
    public void setLogo(String logo) {
    	this.logo = logo;
    }

    /**   概述内容  summary   **/
    public String getSummary() {
        return summary;
    }

    /**   概述内容  summary   **/
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**   内容详情（html）  content   **/
    public String getContent() {
        return content;
    }

    /**   内容详情（html）  content   **/
    public void setContent(String content) {
        this.content = content;
    }
	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
	public String toString() {
		return "NoticeDO [id=" + id + ", type=" + type + ", title=" + title + ", logo=" + logo + ", summary=" + summary
				+ ", content=" + content + ", toString()=" + super.toString() + "]";
	}
}