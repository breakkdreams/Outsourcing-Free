package com.zd.aoding.outsourcing.weChat.api.facade;

import java.util.List;

import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.CategoryGoodsSpecRelationBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.CategoryGoodsSpecRelationDO;

public interface CategoryGoodsSpecRelationFacade {
	/**
	 * @Title: insertCategoryGoodsSpecRelationPo
	 * @Description: 插入分类下默认产品配置
	 * @param categoryGoodsSpecRelationPo 
	 * @return
	 * @return: int 1成功
	 */
	int insertCategoryGoodsSpecRelationPo(CategoryGoodsSpecRelationDO categoryGoodsSpecRelationPo);
	/**
	 * 
	* @Title: updateCategoryGoodsSpecRelationPo
	* @Description: 更改分类下的默认配置
	* @param @param categoryGoodsSpecRelationPo
	* @param @return
	* @return int
	 */
	int updateCategoryGoodsSpecRelationPo(CategoryGoodsSpecRelationDO categoryGoodsSpecRelationPo);
	/**
	 * @Title: getListByCategoryId
	 * @Description: 根据categoryId查询
	 * @param categoryId
	 * @return
	 * @return: List<CategoryGoodsSpecRelationVo>
	 */
	List<CategoryGoodsSpecRelationBO> getListByCategoryId(Integer categoryId);
	
    /**
     * 
    * @Title: insertGoodsPo  供货商添加产品
    * @Description: TODO  
    * @param @param gpo  产品
    * @param @return
    * @return int
     */
    List<String> getSpecNameByCategoryId(Integer categoryId);
	
}
