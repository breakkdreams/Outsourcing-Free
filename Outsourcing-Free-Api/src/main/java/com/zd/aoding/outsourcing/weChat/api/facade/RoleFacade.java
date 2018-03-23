package com.zd.aoding.outsourcing.weChat.api.facade;

import java.util.List;
import java.util.Map;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.RoleBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.RoleDO;

public interface RoleFacade {

	RoleDO getPoByPK(Integer i);

	List<RoleDO> getList(Map<String, Object> param);
	
	int insertRoles(RoleDO rolePo);
	
	int updateRole(RoleDO rolePo);

	/**
	 * 分页查询
	 * @param pageEntity
	 * @return
	 */
	PageResult<RoleBO> getPageRoleVo(PageEntity pageEntity);

	/**
	 * 统计总数
	 * @param param
	 * @return
	 */
	int countRole(Map<String, Object> param);

}
