package com.zd.aoding.outsourcing.weChat.provider.services.mysql.impl;

import com.zd.aoding.base.DAO.BaseDAO;
import com.zd.aoding.base.adapter.SQLAdapter;
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.DistributorMenuDO;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.DistributorMenuService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class DistributorMenuServiceImpl implements DistributorMenuService {
	@Autowired
	private BaseDAO<DistributorMenuDO, Integer> baseDAO;
	private String name = "按钮（MenuAdmin）";
	private static final String nameSpace = DistributorMenuDO.class.getName();
	private static final Logger LOGGER = Logger.getLogger(DistributorMenuServiceImpl.class);

	@Override
	public List<DistributorMenuDO> getBySQLAdapter(SQLAdapter adapter) {
		
		try {
			List<DistributorMenuDO> list = baseDAO.getCustom(adapter,nameSpace);
			return list;
		} catch (Exception e) {
			LOGGER.error(name + "根据所有满足条件获取分页数据失败：", e);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insert(DistributorMenuDO Entity) {
		
		try {
			int i = baseDAO.insert(Entity,nameSpace);
			return i;
		} catch (Exception e) {
			LOGGER.error(name + "插入失败：", e);
			e.printStackTrace();
		}
		return 0;
	}

	
	@Override
	public int update(DistributorMenuDO po) {
		
		try {
			po.setUpdateTime(new Date());
			return baseDAO.update(po,nameSpace);
		} catch (Exception e) {
			LOGGER.error(name + "根据主键更新信息失败：", e);
		}
		return -1;
	}

	@Override
	public int count() {
		
		try {
			return baseDAO.count(nameSpace);
		} catch (Exception e) {
			LOGGER.error(name + "统计总数失败：", e);
		}
		return -1;
	}

	@Override
	public int count(Map<String, Object> params) {
		
		try {
			return baseDAO.count(params,nameSpace);
		} catch (Exception e) {
			LOGGER.error(name + "根据条件统计总数失败：", e);
		}
		return -1;
	}
	@Override
	public DistributorMenuDO get(Integer primaryKey) {
		
		try {
			return baseDAO.get(primaryKey,nameSpace);
		} catch (Exception e) {
			LOGGER.error(name + "根据主键获取信息失败：", e);
		}
		return null;
	}
	@Override
	public List<DistributorMenuDO> getList(Map<String, Object> param) {
		
		try {
			List<DistributorMenuDO> list = baseDAO.getList(param,nameSpace);
			return list;
		} catch (Exception e) {
			LOGGER.error(name + "根据所有满足条件获取数据失败：", e);
			System.out.println(param);
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<DistributorMenuDO> getPagination(PageEntity pageEntity) {
		
		try {
			List<DistributorMenuDO> list = baseDAO.getPagination(pageEntity,nameSpace);
			return list;
		} catch (Exception e) {
			LOGGER.error(name + "根据所有满足条件获取分页数据失败：", e);
			e.printStackTrace();
		}
		return null;
	}

}
