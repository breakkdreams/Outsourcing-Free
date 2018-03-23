package com.zd.aoding.outsourcing.weChat.api.facade;

import java.util.List;
import java.util.Map;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.MenuBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.MenuAdminDO;

public interface MenuAdminFacade {

	List<MenuAdminDO> getPoBySQLAdapter(Map<String, Object> param);

	List<MenuAdminDO> getList(Map<String, Object> param);

	int insertMenus(MenuAdminDO menuAdminPo);

	int updateMenu(MenuAdminDO menuAdminPo);

	MenuAdminDO getPoByPK(Integer i);

	PageResult<MenuBO> getPageMenuVo(PageEntity pageEntity);

	int countMenu(Map<String, Object> param);
}
