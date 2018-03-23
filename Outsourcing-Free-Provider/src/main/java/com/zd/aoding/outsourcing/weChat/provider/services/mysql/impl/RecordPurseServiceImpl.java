package com.zd.aoding.outsourcing.weChat.provider.services.mysql.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.base.DAO.BaseDAO;
import com.zd.aoding.base.DO.record.RecordPurseDO;
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.RecordPursesDO;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.RecordPurseService;


@Service
public class RecordPurseServiceImpl implements RecordPurseService {
	@Autowired
	private BaseDAO<RecordPursesDO, Integer> baseDAO;
	private String name = "钱包记录";
	private static final String nameSpace = RecordPursesDO.class.getName();
	private static final Logger LOGGER = Logger.getLogger(RecordPurseServiceImpl.class);

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
			return baseDAO.count(param, nameSpace);
		} catch (Exception e) {
			LOGGER.error(name + "根据条件统计总数失败：", e);
		}
		return -1;
	}

	@Override
	public int update(RecordPursesDO po) {

		try {
			po.setUpdateTime(new Date());
			return baseDAO.update(po, nameSpace);
		} catch (Exception e) {
			LOGGER.error(name + "根据主键更新信息失败：", e);
		}
		return -1;
	}

	@Override
	public RecordPursesDO get(Integer primaryKey) {

		try {
			return baseDAO.get(primaryKey, nameSpace);
		} catch (Exception e) {
			LOGGER.error(name + "根据主键获取信息失败：", e);
		}
		return null;
	}

	@Override
	public List<RecordPursesDO> getList(Map<String, Object> param) {

		try {
			List<RecordPursesDO> list = baseDAO.getList(param, nameSpace);
			return list;
		} catch (Exception e) {
			LOGGER.error(name + "根据所有满足条件获取数据失败：", e);
			System.out.println(param);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<RecordPursesDO> getPagination(PageEntity page) {

		try {
			List<RecordPursesDO> list = baseDAO.getPagination(page, nameSpace);
			return list;
		} catch (Exception e) {
			LOGGER.error(name + "根据所有满足条件获取分页数据失败：", e);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insert(RecordPursesDO po) {

		try {
			po.insertInit();
			int i = baseDAO.insert(po, nameSpace);
			return i;
		} catch (Exception e) {
			LOGGER.error(name + "插入失败：", e);
			e.printStackTrace();
		}
		return 0;
	}

}
