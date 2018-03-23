package com.zd.aoding.outsourcing.weChat.api.bean.businessObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.common.StringDate.StringUtil;
import com.zd.aoding.config.Config;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.CategoryDO;

@ApiModel(value = "", description = "分类展示类")
public class CategoryBO {
	private Integer categoryId;
	private int returnFlag;//1.一级分类跳转2.二级分类跳转3.三级分类跳转
	private String showUrl;
	private String name;
	private Integer parentCategoryId;
	private Integer status;
	@ApiModelProperty(value = "层级")
    private Integer lever;
	@ApiModelProperty(value = "一级name")
	private String firstName;
	@ApiModelProperty(value = "二级name")
	private String secondName;
	
    @ApiModelProperty(value = "排序")
    private Integer sort;
    @ApiModelProperty(value = "0默认位置  1首页")
    private Integer position;
    @ApiModelProperty(value = "首页logo")
    private String indexLogo;
    
    @ApiModelProperty(value = "分类Logo图")
	private String logoImgUrl;

	public CategoryBO(CategoryDO categoryPo) {
		super();
		this.categoryId = categoryPo.getId();
		this.logoImgUrl = Config.IMG_SERVER + categoryPo.getLogoImgUrl();
		if(!StringUtil.isNULL(categoryPo.getName())){
			this.name = categoryPo.getName();
		}else{
			this.name = "";
		}
		this.parentCategoryId = categoryPo.getParentCategoryId();
		this.showUrl = Config.IMG_SERVER + categoryPo.getLogoImgUrl();
		this.status = categoryPo.getStatus();
		this.lever = categoryPo.getLever();
		this.sort = categoryPo.getSort();
		this.position = categoryPo.getPosition();
		this.indexLogo = categoryPo.getIndexLogo();
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public int getReturnFlag() {
		return returnFlag;
	}

	public void setReturnFlag(int returnFlag) {
		this.returnFlag = returnFlag;
	}

	public String getShowUrl() {
		return showUrl;
	}

	public void setShowUrl(String showUrl) {
		this.showUrl = showUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(Integer parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getLever() {
		return lever;
	}

	public void setLever(Integer lever) {
		this.lever = lever;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public String getIndexLogo() {
		return indexLogo;
	}

	public void setIndexLogo(String indexLogo) {
		this.indexLogo = indexLogo;
	}

	public String getLogoImgUrl() {
		return logoImgUrl;
	}

	public void setLogoImgUrl(String logoImgUrl) {
		this.logoImgUrl = logoImgUrl;
	}
}
