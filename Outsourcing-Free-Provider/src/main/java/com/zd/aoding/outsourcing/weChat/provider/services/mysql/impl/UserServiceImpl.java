package com.zd.aoding.outsourcing.weChat.provider.services.mysql.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.base.DAO.BaseDAO;
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.UserDO;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private BaseDAO<UserDO, Integer> baseDAO;
	private String name = "人民币实际交易订单（UserDO）";
	private static final String nameSpace = UserDO.class.getName();
	private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

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
	public int update(UserDO po) {
		
		try {
			po.setUpdateTime(new Date());
			return baseDAO.update(po,nameSpace);
		} catch (Exception e) {
			LOGGER.error(name + "根据主键更新信息失败：", e);
		}
		return -1;
	}
	@Override
	public UserDO get(Integer primaryKey) {
		
		try {
			return baseDAO.get(primaryKey,nameSpace);
		} catch (Exception e) {
			LOGGER.error(name + "根据主键获取信息失败：", e);
		}
		return null;
	}
	@Override
	public List<UserDO> getList(Map<String, Object> param) {
		
		try {
			List<UserDO> list = baseDAO.getList(param,nameSpace);
			return list;
		} catch (Exception e) {
			LOGGER.error(name + "根据所有满足条件获取数据失败：", e);
			System.out.println(param);
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<UserDO> getPagination(PageEntity pageEntity) {
		
		try {
			List<UserDO> list = baseDAO.getPagination(pageEntity,nameSpace);
			return list;
		} catch (Exception e) {
			LOGGER.error(name + "根据所有满足条件获取分页数据失败：", e);
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public int insert(UserDO Entity) {
		
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
	@Override
	public int delete(Integer id) {
		UserDO po = new UserDO();
		po.setId(id);
		po.setDeleted(1);
		return update(po);
	}
	@Override
	public int creatTable(String tableName) {
		
		try {

			int i = baseDAO.creatTable(tableName,nameSpace);
			return i;
		} catch (Exception e) {
			LOGGER.error(name + "插入失败：", e);
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public UserDO getPoByTableName(Integer id, String tableName) {
		
		try {
			return baseDAO.getByTableName(id, tableName,nameSpace);
		} catch (Exception e) {
			LOGGER.error(name + "根据主键获取信息失败：", e);
		}
		return null;
	}
	@Override
	public List<UserDO> getPaginationByTableName(PageEntity pageEntity,
			String tableName) {
		
		try {
			List<UserDO> list = baseDAO.getPaginationByTableName(pageEntity, tableName,nameSpace);
			return list;
		} catch (Exception e) {
			LOGGER.error(name + "根据所有满足条件获取分页数据失败：", e);
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<UserDO> getListByTableName(Map<String, Object> param, String tableName) {
		
		try {
			List<UserDO> list = baseDAO.getListByTableName(param, tableName,nameSpace);
			return list;
		} catch (Exception e) {
			LOGGER.error(name + "根据所有满足条件获取数据失败：", e);
			System.out.println(param);
			e.printStackTrace();
		}
		return null;
	}
}
