package com.zd.aoding.outsourcing.weChat.provider.facade.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.RoleAccountDO;
import com.zd.aoding.outsourcing.weChat.api.facade.RoleAccountFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.RoleAccountService;

@Service
public class RoleAccountFacadeImpl implements RoleAccountFacade {
	@Autowired
	private RoleAccountService roleAccountService;

	@Override
	public int insertRoleAccountPo(RoleAccountDO roleAccountPo) {
		roleAccountPo.insertInit();
		return roleAccountService.insert(roleAccountPo);
	}

	@Override
	public RoleAccountDO getRoleAccountPoByPK(String accountId) {
		RoleAccountDO roleAccountPo = roleAccountService.get(Integer.parseInt(accountId));
		if (roleAccountPo != null) {
			return roleAccountPo;
		}
		return null;
	}

	@Override
	public int updateRoleAccountPo(RoleAccountDO roleAccountPo) {
		return roleAccountService.update(roleAccountPo);
	}

	@Override
	public RoleAccountDO getRoleAccountByMap(Map<String, Object> param) {
		List<RoleAccountDO> list = roleAccountService.getList(param);
		if (list != null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}
}
