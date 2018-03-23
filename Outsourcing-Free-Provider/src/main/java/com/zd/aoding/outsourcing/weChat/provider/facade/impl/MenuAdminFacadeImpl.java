package com.zd.aoding.outsourcing.weChat.provider.facade.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.base.adapter.SQLAdapter;
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.MenuBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.MenuAdminDO;
import com.zd.aoding.outsourcing.weChat.api.facade.MenuAdminFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.MenuAdminService;

@Service
public class MenuAdminFacadeImpl implements MenuAdminFacade {
	@Autowired
	private MenuAdminService menuService;
	@Override
	public List<MenuAdminDO> getPoBySQLAdapter(Map<String, Object> param) {
		String obj = param.get("roleList") == null ? "" : param.get("roleList").toString();
		String sql = "SELECT  m.pid as pid,m.tubiao as tubiao,m.falg as falg, m.p_name as p_name,  m.ej_id as ej_id, m.url as url FROM menu m  where FIND_IN_SET(m.pid,'"+obj+"');";
		SQLAdapter adapter = new SQLAdapter();
		adapter.setSql(sql);
		List<MenuAdminDO> list =menuService.getBySQLAdapter(adapter);
		return list;
	}
	@Override
	public List<MenuAdminDO> getList(Map<String, Object> param) {
		return menuService.getList(param);
	}
	@Override
	public int insertMenus(MenuAdminDO menuAdminPo) {
		return insertMenu(menuAdminPo);
	}

	private int insertMenu(MenuAdminDO menuAdminPo) {
		menuAdminPo.insertInit();
		return menuService.insert(menuAdminPo);
	}
	@Override
	public int updateMenu(MenuAdminDO menuAdminPo) {
		return menuService.update(menuAdminPo);
	}
	@Override
	public MenuAdminDO getPoByPK(Integer i) {
		return menuService.get(i);
	}

	@Override
	public PageResult<MenuBO> getPageMenuVo(PageEntity pageEntity) {
		PageResult<MenuBO> pageResult = new PageResult<MenuBO>();
		List<MenuAdminDO> list = menuService.getPagination(pageEntity);
		List<MenuBO> listVo = new ArrayList<>();
		if (list != null && list.size() > 0) {
			for (MenuAdminDO menuAdminPo : list) {
				listVo.add(new MenuBO(menuAdminPo));
			}
		}
		pageResult.setResultList(listVo);
		pageResult.setCurrentPage(pageEntity.getPage());
		pageResult.setPageSize(pageEntity.getSize());
		pageResult.setTotalSize(menuService.count(pageEntity.getParams()));
		return pageResult;
	}

	@Override
	public int countMenu(Map<String, Object> param) {
		try {
			return menuService.count(param);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
