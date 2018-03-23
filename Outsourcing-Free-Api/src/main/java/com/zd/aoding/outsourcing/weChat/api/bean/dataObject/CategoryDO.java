package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.base.DO.base.DOBase;

@ApiModel(value = "", description = "分类")
public class CategoryDO extends DOBase {
	/**
	 * @fieldName: lever_first
	 * @Description: 一级分类
	 */
	public final static Integer LEVER_FIRST = 1;
	/**
	 * @fieldName: lever_second
	 * @Description: 二级分类
	 */
	public final static Integer LEVER_SECOND = 2;
	/**
	 * @fieldName: lever_third
	 * @Description: 三级分类
	 */
	public final static Integer LEVER_THIRD = 3;
	/**
	 * 状态
	 */
	public final static Integer STATUS_USE = 1;
	public final static Integer STATUS_UNUSE = 0;
	
	@ApiModelProperty(value = "分类id", hidden = true)
    private Integer id;
    /** 分类Logo图  logo_Img_Url **/
    @ApiModelProperty(value = "分类Logo图")
    private String logoImgUrl;
    /** 分类名字  name **/
    @ApiModelProperty(value = "分类名字")
    private String name;
    /** 上级分类id  parent_Category_Id **/
    @ApiModelProperty(value = "上级分类id")
    private Integer parentCategoryId;
    /** 层级  lever **/
    @ApiModelProperty(value = "层级")
    private Integer lever;
    @ApiModelProperty(value = "状态")
    private Integer status;
    @ApiModelProperty(value = "排序")
    private Integer sort;
    @ApiModelProperty(value = "0默认位置  1首页")
    private Integer position;
    @ApiModelProperty(value = "首页logo")
    private String indexLogo;


    public CategoryDO(String logoImgUrl, String name, Integer parentCategoryId,
			Integer lever) {
		super();
		this.logoImgUrl = logoImgUrl;
		this.name = name;
		this.parentCategoryId = parentCategoryId;
		this.lever = lever;
		this.status = STATUS_USE;
	}
    
    public CategoryDO(){
    	super();
    }

	/**     id   **/
    public Integer getId() {
        return id;
    }

    /**     id   **/
    public void setId(Integer id) {
        this.id = id;
    }


    /**   分类Logo图  logo_Img_Url   **/
    public String getLogoImgUrl() {
        return logoImgUrl;
    }

    /**   分类Logo图  logo_Img_Url   **/
    public void setLogoImgUrl(String logoImgUrl) {
        this.logoImgUrl = logoImgUrl;
    }

    /**   分类名字  name   **/
    public String getName() {
        return name;
    }

    /**   分类名字  name   **/
    public void setName(String name) {
        this.name = name;
    }

    /**   上级分类id  parent_Category_Id   **/
    public Integer getParentCategoryId() {
        return parentCategoryId;
    }

    /**   上级分类id  parent_Category_Id   **/
    public void setParentCategoryId(Integer parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    /**   层级  lever   **/
    public Integer getLever() {
        return lever;
    }

    /**   层级  lever   **/
    public void setLever(Integer lever) {
        this.lever = lever;
    }

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	@Override
	public String toString() {
		return "CatagoryDO [id=" + id + ", logoImgUrl=" + logoImgUrl + ", name=" + name + ", parentCategoryId="
				+ parentCategoryId + ", lever=" + lever + ", sort=" + sort + ", position=" + position + ", indexLogo=" + indexLogo + ", status=" + status + ", toString()=" + super.toString()
				+ "]";
	}
}