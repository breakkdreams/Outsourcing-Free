package com.zd.aoding.outsourcing.weChat.provider.facade.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.CategoryBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.CategoryDO;
import com.zd.aoding.outsourcing.weChat.api.facade.CategoryFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.CategoryService;



@Service
public class CategoryFacadeImpl implements CategoryFacade {
	@Autowired
	private CategoryService categoryService;

	@Override
	public int insertCategoryPo(CategoryDO categoryPo) {
		categoryPo.insertInit();
		return categoryService.insert(categoryPo);
	}

	@Override
	public CategoryDO getCategoryPoByPK(String categoryId) {
		Map<String, Object> param = new HashMap<>();
		param.put("id", categoryId);
		param.put("deleted", 0);
		CategoryDO categoryPo = categoryService.get(Integer.parseInt(categoryId));
		if (categoryPo != null) {
			return categoryPo;
		}
		return null;
	}

	@Override
	public int updateCategoryPo(CategoryDO categoryPo) {
		return categoryService.update(categoryPo);
	}

	@Override
	public PageResult<CategoryBO> getPageCategoryVo(PageEntity pageEntity) {
		PageResult<CategoryBO> pageResult = new PageResult<CategoryBO>();
		List<CategoryDO> list = categoryService.getPagination(pageEntity);
		List<CategoryBO> listVo = new ArrayList<>();
		if (list != null && list.size() > 0) {
			for (CategoryDO categoryPo : list) {
				
				listVo.add(view(categoryPo));
			}
		}
		pageResult.setResultList(listVo);
		pageResult.setCurrentPage(pageEntity.getPage());
		pageResult.setPageSize(pageEntity.getSize());
		pageResult.setTotalSize(categoryService.count(pageEntity.getParams()));
		return pageResult;
	}

	@Override
	public List<CategoryDO> getCategoryList(Map<String, Object> param) {
		List<CategoryDO> list = categoryService.getList(param);
		if(list != null){
			return list;
		}
		return null;
	}
	@Override
	public List<CategoryBO> getCategoryBOList(Map<String, Object> param) {
		List<CategoryDO> list = categoryService.getList(param);
		List<CategoryBO> listVo = new ArrayList<CategoryBO>();
		if (list != null && list.size() > 0) {
			for (CategoryDO categoryPo : list) {
				listVo.add(new CategoryBO(categoryPo));
			}
		}
		return listVo;
	}

	private CategoryBO view(CategoryDO categoryPo){
		CategoryBO categoryVo = new CategoryBO(categoryPo);
		if(CategoryDO.LEVER_SECOND.equals(categoryVo.getLever())){
			String firstName = "";
			CategoryDO first = categoryService.get(categoryVo.getParentCategoryId());
			if(first != null){
				if(first.getDeleted() == 1){
					firstName = first.getName() + "(已删除)";
				}else{
					firstName = first.getName();
				}
			}
			categoryVo.setFirstName(firstName);
		}
		if(CategoryDO.LEVER_THIRD.equals(categoryVo.getLever())){
			String firstName = "";
			String secondName = "";
			CategoryDO second = categoryService.get(categoryVo.getParentCategoryId());
			if(second != null){
				if(second.getDeleted() == 1){
					secondName = second.getName() + "(已删除)";
				}else{
					secondName = second.getName();
				}
				CategoryDO first = categoryService.get(second.getParentCategoryId());
				if(first != null){
					if(first.getDeleted() == 1){
						firstName = first.getName() + "(已删除)";
					}else{
						firstName = first.getName();
					}
				}
			}
			categoryVo.setFirstName(firstName);
			categoryVo.setSecondName(secondName);
		}
		return categoryVo;
	}

	@Override
	public int countCategoryPo(Map<String, Object> param) {
		try {
			return categoryService.count(param);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public List<CategoryBO> getCategoryListIndex(Map<String, Object> param) {
		List<CategoryDO> list = categoryService.getList(param);
		List<CategoryBO> listVo = new ArrayList<CategoryBO>();
		if (list != null && list.size() > 0) {
			for (CategoryDO categoryPo : list) {
				if(listVo.size() < 9){
					listVo.add(new CategoryBO(categoryPo));
				}
			}
		}
		return listVo;
	}
}
