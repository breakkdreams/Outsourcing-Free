package com.zd.aoding.outsourcing.weChat.provider.facade.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.CategoryGoodsSpecRelationBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.CategoryDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.CategoryGoodsSpecRelationDO;
import com.zd.aoding.outsourcing.weChat.api.facade.CategoryFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.CategoryGoodsSpecRelationFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.CategoryGoodsSpecRelationService;

@Service
public class CategoryGoodsSpecRelationFacadeImpl implements CategoryGoodsSpecRelationFacade {
	@Autowired
	private CategoryGoodsSpecRelationService categoryGoodsSpecRelationService;
	@Autowired
	private CategoryFacade categoryFacade;

	@Override
	public int insertCategoryGoodsSpecRelationPo(CategoryGoodsSpecRelationDO categoryGoodsSpecRelationPo) {
		try {
			int i = categoryGoodsSpecRelationService.insert(categoryGoodsSpecRelationPo);
			if(i != 1){
				i = categoryGoodsSpecRelationService.insert(categoryGoodsSpecRelationPo);
			}
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<CategoryGoodsSpecRelationBO> getListByCategoryId(
			Integer categoryId) {
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("deleted", 0);
			param.put("categoryId", categoryId);
			List<CategoryGoodsSpecRelationDO> list = categoryGoodsSpecRelationService.getList(param);
			List<CategoryGoodsSpecRelationBO> listVo = new ArrayList<CategoryGoodsSpecRelationBO>();
			if(list != null){
				for(CategoryGoodsSpecRelationDO categoryGoodsSpecRelationPo : list){
					listVo.add(view(categoryGoodsSpecRelationPo));
				}
			}
			return listVo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private CategoryGoodsSpecRelationBO view(CategoryGoodsSpecRelationDO categoryGoodsSpecRelationPo){
		CategoryGoodsSpecRelationBO categoryGoodsSpecRelationVo = new CategoryGoodsSpecRelationBO(categoryGoodsSpecRelationPo);
		CategoryDO category = categoryFacade.getCategoryPoByPK(categoryGoodsSpecRelationPo.getCategoryId()+"");
		String categoryName = "";
		if(category != null){
			categoryName = category.getName();
		}
		categoryGoodsSpecRelationVo.setCategoryName(categoryName);
		return categoryGoodsSpecRelationVo;
	}

	@Override
	public int updateCategoryGoodsSpecRelationPo(
			CategoryGoodsSpecRelationDO categoryGoodsSpecRelationPo) {
		int i = categoryGoodsSpecRelationService.update(categoryGoodsSpecRelationPo);
		if(i == 0){
			i = categoryGoodsSpecRelationService.update(categoryGoodsSpecRelationPo);
		}
		return i;
	}

		@Override
		public List<String> getSpecNameByCategoryId(Integer categoryId) {
			// TODO Auto-generated method stub
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("deleted", 0);
			map.put("categoryId", categoryId);
			List<CategoryGoodsSpecRelationDO> list = categoryGoodsSpecRelationService.getList(map);
			List<String> stringList = new ArrayList<String>();
			if(list != null && list.size() > 0){
				int size = list.size();
				for(int i = 0; i < size; i++){
					CategoryGoodsSpecRelationDO relationPo = list.get(i);
					stringList.add(relationPo.getSpecName());
				}
			}
			return stringList;
		}

}
