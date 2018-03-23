package com.zd.aoding.outsourcing.weChat.provider.facade.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.RoleBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.RoleDO;
import com.zd.aoding.outsourcing.weChat.api.facade.RoleFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.RoleService;

@Service
public class RoleFacadeImpl implements RoleFacade {
	@Autowired
	private RoleService roleService;
	@Override
	public int insertRoles( RoleDO rolePo) {

		return insertRole(rolePo);
	}

	private int insertRole(RoleDO rolePo) {
		rolePo.insertInit();
		return roleService.insert(rolePo);
	}
	@Override
	public RoleDO getPoByPK(Integer i) {
		return roleService.get(i);
	}

	@Override
	public List<RoleDO> getList(Map<String, Object> param) {
		return roleService.getList(param);
	}

	@Override
	public int updateRole(RoleDO rolePo) {
		return roleService.update(rolePo);
	}

	@Override
	public PageResult<RoleBO> getPageRoleVo(PageEntity pageEntity) {
		PageResult<RoleBO> pageResult = new PageResult<RoleBO>();
		List<RoleDO> list = roleService.getPagination(pageEntity);
		List<RoleBO> listVo = new ArrayList<>();
		if (list != null && list.size() > 0) {
			for (RoleDO rolePo : list) {
				listVo.add(new RoleBO(rolePo));
			}
		}
		pageResult.setResultList(listVo);
		pageResult.setCurrentPage(pageEntity.getPage());
		pageResult.setPageSize(pageEntity.getSize());
		pageResult.setTotalSize(roleService.count(pageEntity.getParams()));
		return pageResult;
	}

	@Override
	public int countRole(Map<String, Object> param) {
		try {
			return roleService.count(param);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
