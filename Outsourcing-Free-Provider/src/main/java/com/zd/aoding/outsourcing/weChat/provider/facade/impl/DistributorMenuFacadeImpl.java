package com.zd.aoding.outsourcing.weChat.provider.facade.impl;

import com.zd.aoding.base.adapter.SQLAdapter;
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.DistributorMenuBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.DistributorMenuDO;
import com.zd.aoding.outsourcing.weChat.api.facade.DistributorMenuFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.DistributorMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DistributorMenuFacadeImpl implements DistributorMenuFacade {
	@Autowired
	private DistributorMenuService distributorMenuService;
	@Override
	public List<DistributorMenuDO> getPoBySQLAdapter(Map<String, Object> param) {
		String obj = param.get("roleList") == null ? "" : param.get("roleList").toString();
		String sql = "SELECT  m.pid as pid,m.tubiao as tubiao,m.falg as falg, m.p_name as p_name,  m.ej_id as ej_id, m.url as url FROM menu m  where FIND_IN_SET(m.pid,'"+obj+"');";
		SQLAdapter adapter = new SQLAdapter();
		adapter.setSql(sql);
		List<DistributorMenuDO> list =distributorMenuService.getBySQLAdapter(adapter);
		return list;
	}
	@Override
	public List<DistributorMenuDO> getList(Map<String, Object> param) {
		return distributorMenuService.getList(param);
	}
	@Override
	public int insertMenus(DistributorMenuDO menuAdminPo) {
		return insertMenu(menuAdminPo);
	}

	private int insertMenu(DistributorMenuDO menuAdminPo) {
		menuAdminPo.insertInit();
		return distributorMenuService.insert(menuAdminPo);
	}
	@Override
	public int updateMenu(DistributorMenuDO menuAdminPo) {
		return distributorMenuService.update(menuAdminPo);
	}
	@Override
	public DistributorMenuDO getPoByPK(Integer i) {
		return distributorMenuService.get(i);
	}

	@Override
	public PageResult<DistributorMenuBO> getPageMenuVo(PageEntity pageEntity) {
		PageResult<DistributorMenuBO> pageResult = new PageResult<DistributorMenuBO>();
		List<DistributorMenuDO> list = distributorMenuService.getPagination(pageEntity);
		List<DistributorMenuBO> listVo = new ArrayList<>();
		if (list != null && list.size() > 0) {
			for (DistributorMenuDO menuAdminPo : list) {
				listVo.add(new DistributorMenuBO(menuAdminPo));
			}
		}
		pageResult.setResultList(listVo);
		pageResult.setCurrentPage(pageEntity.getPage());
		pageResult.setPageSize(pageEntity.getSize());
		pageResult.setTotalSize(distributorMenuService.count(pageEntity.getParams()));
		return pageResult;
	}

	@Override
	public int countMenu(Map<String, Object> param) {
		try {
			return distributorMenuService.count(param);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
