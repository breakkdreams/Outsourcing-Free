package com.zd.aoding.outsourcing.weChat.api.facade;

import java.util.List;
import java.util.Map;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.BusinessTypeBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.BusinessTypeDO;


public interface BusinessTypeFacade {

	/**
	 * 添加
	 * @param businessTypePo
	 * @return
	 */
	int insertBusinessTypePo(BusinessTypeDO businessTypePo);

	/**
	 * 根据主键查询
	 * @param businessTypeId
	 * @return
	 */
	BusinessTypeDO getBusinessTypePoByPK(String businessTypeId);
	BusinessTypeBO getBusinessTypeVoByPK(Integer businessTypeId);

	/**
	 * 修改
	 * @param businessTypePo
	 * @return
	 */
	int updateBusinessTypePo(BusinessTypeDO businessTypePo);

	/**
	 * 分页查询
	 * @param pageEntity
	 * @return
	 */
	PageResult<BusinessTypeBO> getPageBusinessTypeVo(PageEntity pageEntity);

	/**
	 * 统计
	 * @param param
	 * @return
	 */
	int countBusinessType(Map<String, Object> param);

	/**
	 * 查询全部
	 * @param param
	 * @return
	 */
	List<BusinessTypeDO> getBusinessTypePoList(Map<String, Object> param);
	
	List<BusinessTypeBO> getBusinessTypeList(Map<String, Object> param);
}
