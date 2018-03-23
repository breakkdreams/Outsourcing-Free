package com.zd.aoding.outsourcing.weChat.api.facade;

import java.util.List;
import java.util.Map;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.CategoryBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.CategoryDO;

public interface CategoryFacade {

	/**
	 * @Title: insertCategoryPo
	 * @Description: 添加分类
	 * @param categoryPo
	 * @return
	 * @return: int
	 */
	int insertCategoryPo(CategoryDO categoryPo);
	
	/**
	 * @Title: getCategoryPoByPK 
	 * @Description: 根据分类id查询分类
	 * @param catagoryId
	 * @return
	 * @return: CategoryPo
	 */
	CategoryDO getCategoryPoByPK(String catagoryId);
	
	/**
	 * @Title: updateCategoryPo 
	 * @Description: 修改编辑分类
	 * @param categoryPo
	 * @return
	 * @return: int
	 */
	int updateCategoryPo(CategoryDO categoryPo);
	
	/**
	 * @Title: getPageCatagoryVo 
	 * @Description: 条件分页查询
	 * @param pageEntity
	 * @return
	 * @return: PageResult<CatagoryVo>
	 */
	PageResult<CategoryBO> getPageCategoryVo(PageEntity pageEntity);
	
	/**
	 * @Title: getCatagoryList 
	 * @Description: 查询全部分类
	 * @param param
	 * @return
	 * @return: List<CategoryPo>
	 */
	List<CategoryDO> getCategoryList(Map<String, Object> param);
	List<CategoryBO> getCategoryBOList(Map<String, Object> param);
	
	int countCategoryPo(Map<String, Object> param);
	
	List<CategoryBO> getCategoryListIndex(Map<String, Object> param);
}
