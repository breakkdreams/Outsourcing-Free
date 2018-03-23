package com.zd.aoding.outsourcing.weChat.api.facade;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.DistributorMenuBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.DistributorMenuDO;

import java.util.List;
import java.util.Map;

public interface DistributorMenuFacade {

	List<DistributorMenuDO> getPoBySQLAdapter(Map<String, Object> param);

	List<DistributorMenuDO> getList(Map<String, Object> param);

	int insertMenus(DistributorMenuDO distributorMenuPo);

	int updateMenu(DistributorMenuDO distributorMenuPo);

	DistributorMenuDO getPoByPK(Integer i);

	PageResult<DistributorMenuBO> getPageMenuVo(PageEntity pageEntity);

	int countMenu(Map<String, Object> param);
}
