package com.zd.aoding.outsourcing.weChat.api.facade;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.ManagerBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ManagerDO;

public interface ManagerFacade {

	int updateManager(ManagerDO managerPo);

	ManagerDO getPoByAccountId(Integer accountId);
	ManagerBO getBoByAccountId(Integer accountId);

	PageResult<ManagerBO> getPageManagerVo(PageEntity pageEntity);

}
