package com.zd.aoding.outsourcing.weChat.provider.facade.impl;

import com.zd.aoding.common.log.LogUtil;
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.ManagerBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ManagerDO;
import com.zd.aoding.outsourcing.weChat.api.facade.ManagerFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ManagerFacadeImpl implements ManagerFacade {
	@Autowired
	private ManagerService managerService;

	@Override
	public int updateManager(ManagerDO managerPo) {
		return managerService.update(managerPo);
	}
	@Override
	public ManagerDO getPoByAccountId(Integer accountId) {
		Map<String, Object> param = new HashMap<>();
		param.put("accountId", accountId);
		param.put("deleted", 0);
		List<ManagerDO> list = managerService.getList(param);
		if (list == null || list.size() == 0) {
			return null;
		}
		if (list.size() == 1) {
			return list.get(0);
		}
		if (list.size() > 1) {
			LogUtil.dataError("多个实名信息" + list, getClass());
			return list.get(0);
		}
		return null;
	}
	@Override
	public ManagerBO getBoByAccountId(Integer accountId) {
		Map<String, Object> param = new HashMap<>();
		param.put("accountId", accountId);
		param.put("deleted", 0);
		List<ManagerDO> list = managerService.getList(param);
		if (list == null || list.size() == 0) {
			return null;
		}
		if (list.size() == 1) {
			return new ManagerBO(list.get(0));
		}
		if (list.size() > 1) {
			LogUtil.dataError("多个实名信息" + list, getClass());
			return new ManagerBO(list.get(0));
		}
		return null;
	}

	@Override
	public PageResult<ManagerBO> getPageManagerVo(PageEntity pageEntity) {
		PageResult<ManagerBO> pageResult = new PageResult<ManagerBO>();
		List<ManagerDO> list = managerService.getPagination(pageEntity);
		List<ManagerBO> listVo = new ArrayList<>();
		if (list != null && list.size() > 0) {
			for (ManagerDO managerDO : list) {
				listVo.add(new ManagerBO(managerDO));
			}
		}
		pageResult.setResultList(listVo);
		pageResult.setCurrentPage(pageEntity.getPage());
		pageResult.setPageSize(pageEntity.getSize());
		pageResult.setTotalSize(managerService.count(pageEntity.getParams()));
		return pageResult;
	}


}
