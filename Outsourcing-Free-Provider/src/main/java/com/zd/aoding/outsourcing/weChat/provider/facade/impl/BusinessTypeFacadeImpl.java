package com.zd.aoding.outsourcing.weChat.provider.facade.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.BusinessTypeBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.BusinessTypeDO;
import com.zd.aoding.outsourcing.weChat.api.facade.BusinessTypeFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.BusinessTypeService;


@Service
public class BusinessTypeFacadeImpl implements BusinessTypeFacade {
	@Autowired
	private BusinessTypeService businessTypeService;

	@Override
	public int insertBusinessTypePo(BusinessTypeDO businessTypePo) {
		businessTypePo.insertInit();
		return businessTypeService.insert(businessTypePo);
	}

	@Override
	public BusinessTypeDO getBusinessTypePoByPK(String businessTypeId) {
		BusinessTypeDO businessTypePo = businessTypeService.get(Integer.parseInt(businessTypeId));
		if (businessTypePo != null) {
			return businessTypePo;
		}
		return null;
	}

	@Override
	public BusinessTypeBO getBusinessTypeVoByPK(Integer businessTypeId) {
		BusinessTypeDO businessTypePo = businessTypeService.get(businessTypeId);
		if (businessTypePo != null) {
			BusinessTypeBO businessTypeVo = new BusinessTypeBO(businessTypePo);
			return businessTypeVo;
		}
		return null;
	}

	@Override
	public int updateBusinessTypePo(BusinessTypeDO businessTypePo) {
		return businessTypeService.update(businessTypePo);
	}

	@Override
	public PageResult<BusinessTypeBO> getPageBusinessTypeVo(PageEntity pageEntity) {
		PageResult<BusinessTypeBO> pageResult = new PageResult<BusinessTypeBO>();
		List<BusinessTypeDO> list = businessTypeService.getPagination(pageEntity);
		List<BusinessTypeBO> listVo = new ArrayList<>();
		if (list != null && list.size() > 0) {
			for (BusinessTypeDO businessTypePo : list) {
				listVo.add(new BusinessTypeBO(businessTypePo));
			}
		}
		pageResult.setResultList(listVo);
		pageResult.setCurrentPage(pageEntity.getPage());
		pageResult.setPageSize(pageEntity.getSize());
		pageResult.setTotalSize(businessTypeService.count(pageEntity.getParams()));
		return pageResult;
	}

	@Override
	public int countBusinessType(Map<String, Object> param) {
		try {
			return businessTypeService.count(param);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public List<BusinessTypeDO> getBusinessTypePoList(Map<String, Object> param) {
		List<BusinessTypeDO> list = businessTypeService.getList(param);
		if(list != null){
			return list;
		}
		return null;
	}
	
	
	@Override
	public List<BusinessTypeBO> getBusinessTypeList(Map<String, Object> param) {
		try {
			List<BusinessTypeBO> voList = new ArrayList<BusinessTypeBO>();
			List<BusinessTypeDO> poList = businessTypeService.getList(param);
			if(poList != null && poList.size() > 0){
				for(BusinessTypeDO gmPo : poList){
					voList.add(new BusinessTypeBO(gmPo));
				}
			}
			return voList;
		} catch (Exception e) {
			return null;
		}
	}
}
