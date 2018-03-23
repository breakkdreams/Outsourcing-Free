package com.zd.aoding.outsourcing.weChat.api.facade;

import java.util.Map;

import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.RoleAccountDO;


public interface RoleAccountFacade {

	int insertRoleAccountPo(RoleAccountDO roleAccountPo);

	RoleAccountDO getRoleAccountPoByPK(String accountId);

	int updateRoleAccountPo(RoleAccountDO roleAccountPo);

	RoleAccountDO getRoleAccountByMap(Map<String, Object> param);
}
