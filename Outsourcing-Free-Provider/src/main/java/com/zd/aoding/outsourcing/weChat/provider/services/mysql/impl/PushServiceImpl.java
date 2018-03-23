package com.zd.aoding.outsourcing.weChat.provider.services.mysql.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.base.DAO.BaseDAO;
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.PushDO;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.PushService;

@Service
public class PushServiceImpl implements PushService {
	@Autowired
	private BaseDAO<PushDO, Integer> baseDAO;
	private String name = "推送（PushDO）";
	private static final String nameSpace = PushDO.class.getName();
	private static final Logger LOGGER = Logger.getLogger(PushServiceImpl.class);

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
	public int count(Map<String, Object> param) {
		
		try {
			return baseDAO.count(param,nameSpace);
		} catch (Exception e) {
			LOGGER.error(name + "根据条件统计总数失败：", e);
		}
		return -1;
	}
	@Override
	public int update(PushDO po) {
		
		try {
			po.setUpdateTime(new Date());
			return baseDAO.update(po,nameSpace);
		} catch (Exception e) {
			LOGGER.error(name + "根据主键更新信息失败：", e);
		}
		return -1;
	}
	@Override
	public PushDO get(Integer primaryKey) {
		
		try {
			return baseDAO.get(primaryKey,nameSpace);
		} catch (Exception e) {
			LOGGER.error(name + "根据主键获取信息失败：", e);
		}
		return null;
	}
	@Override
	public List<PushDO> getList(Map<String, Object> param) {
		
		try {
			List<PushDO> list = baseDAO.getList(param,nameSpace);
			return list;
		} catch (Exception e) {
			LOGGER.error(name + "根据所有满足条件获取数据失败：", e);
			System.out.println(param);
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<PushDO> getPagination(PageEntity pageEntity) {
		
		try {
			List<PushDO> list = baseDAO.getPagination(pageEntity,nameSpace);
			return list;
		} catch (Exception e) {
			LOGGER.error(name + "根据所有满足条件获取分页数据失败：", e);
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public int insert(PushDO Entity) {
		
		try {
			Entity.insertInit();
			int i = baseDAO.insert(Entity,nameSpace);
			return i;
		} catch (Exception e) {
			LOGGER.error(name + "插入失败：", e);
			e.printStackTrace();
		}
		return 0;
	}
}
