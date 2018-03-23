package com.zd.aoding.outsourcing.weChat.provider.services.mysql.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.base.DAO.BaseDAO;
import com.zd.aoding.base.DO.base.RecordBase;
import com.zd.aoding.base.adapter.SQLAdapter;
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.RecordsDO;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.RecordService;


@Service
public class RecordServiceImpl implements RecordService {
	@Autowired
	private BaseDAO<RecordsDO, Serializable> baseDAO;

	private String name = "记录";
	private static final String nameSpace = RecordsDO.class.getName();
	private static final Logger LOGGER = Logger.getLogger(RecordServiceImpl.class);

	@Override
	public List<RecordsDO> getPageRecord(PageEntity pageEntity) {
		try {
			List<RecordsDO> list = baseDAO.getPagination(pageEntity, nameSpace);
			return list;
		} catch (Exception e) {
			LOGGER.error(name + "根据所有满足条件获取分页数据失败：", e);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insert(RecordsDO entityDo) {
		try {
			entityDo.insertInit();
			int i = baseDAO.insert(entityDo, nameSpace);
			return i;
		} catch (Exception e) {
			LOGGER.error(name + "插入失败：", e);
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int countRecord(Integer recordLever) {

		try {
			return baseDAO.count(nameSpace);
		} catch (Exception e) {
			LOGGER.error(name + "统计总数失败：", e);
		}
		return -1;
	}

	@Override
	public int countRecord(Integer recordLever, Map<String, Object> param) {
		try {
			return baseDAO.count(param, nameSpace);
		} catch (Exception e) {
			LOGGER.error(name + "根据条件统计总数失败：", e);
		}
		return -1;
	}
	@Override
	public int countRecordByMap(Map<String, Object> param) {
		try {
			return baseDAO.count(param, nameSpace);
		} catch (Exception e) {
			LOGGER.error(name + "根据条件统计总数失败：", e);
		}
		return -1;
	}

	@Override
	public RecordsDO get(Integer recordId, Integer lever) {
		SQLAdapter adapter = new SQLAdapter();
		String table = "err";
		switch (lever) {
		case RecordBase.LEVER_IMPORTANT:
			table = "record_Important";
			break;
		case RecordBase.LEVER_NORMAL:
			table = "record_Normal";
			break;
		default:
			table = "errTeble";
			break;
		}
		StringBuffer sql = new StringBuffer("select  *  from ");
		sql.append(table).append(" where id = ").append(recordId);
		adapter.setSql(sql.toString());
		List<RecordsDO> list = baseDAO.getCustom(adapter, nameSpace);
		if (list != null && list.size() == 1) {
			return list.get(0);
		} else {
			LOGGER.error("数据重了" + list.toString());
		}
		return null;
	}

}
