package com.zd.aoding.outsourcing.weChat.api.services.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.base.DAO.BaseDAO;
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.WXOrderDO;

/**
 * 微信订单 服务类
 * 
 * @author and
 *
 */
@Service
public class WXOrderService {
	@Autowired
	private BaseDAO<WXOrderDO, Integer> baseDAO;
	private String name = "微信订单(WXOrderDO)";
	private static final String nameSpace = WXOrderDO.class.getName();
	private static final Logger LOGGER = Logger.getLogger(WXOrderService.class);

	/**
	 * 更新或者插入 微信订单 如果 系统 订单 orderId 已存在, 不处理 如果 系统 订单 orderId 不存在, 插入该条数据
	 * 
	 * @param wxOrder
	 */
	public int insert(WXOrderDO wxOrder) {
		
		try {
			int i = baseDAO.insert(wxOrder,nameSpace);

			return i;
		} catch (Exception e) {
			LOGGER.error(name + "根据所有满足条件获取数据失败：", e);
			e.printStackTrace();
		}
		return 0;
	}

	
	
	public int countMapper() {
		
		try {
			return baseDAO.count(nameSpace);
		} catch (Exception e) {
			LOGGER.error(name + "统计总数失败：", e);
		}
		return -1;
	}

	public int countMapper(Map<String, Object> param) {
		
		try {
			return baseDAO.count(param,nameSpace);
		} catch (Exception e) {
			LOGGER.error(name + "根据条件统计总数失败：", e);
		}
		return -1;
	}

	public int updateMapperByPK(WXOrderDO WXOrderDO) {
		
		try {
			return baseDAO.update(WXOrderDO,nameSpace);
		} catch (Exception e) {
			LOGGER.error(name + "根据主键更新信息失败：", e);
		}
		return -1;
	}

	public WXOrderDO getMapperByPK(Integer id) {
		

		try {
			return baseDAO.get(id,nameSpace);
		} catch (Exception e) {
			LOGGER.error(name + "根据主键获取信息失败：", e);
		}
		return null;
	}

	public List<WXOrderDO> getMapperList(Map<String, Object> param) {
		

		try {
			List<WXOrderDO> list = baseDAO.getList(param,nameSpace);

			return list;
		} catch (Exception e) {
			LOGGER.error(name + "根据所有满足条件获取数据失败：", e);
			System.out.println(param);
			e.printStackTrace();
		}
		return null;
	}

	public List<WXOrderDO> getMapperPage(PageEntity pageEntity) {
		

		try {
			List<WXOrderDO> list = baseDAO.getPagination(pageEntity,nameSpace);
			return list;
		} catch (Exception e) {
			LOGGER.error(name + "根据所有满足条件获取分页数据失败：", e);
			e.printStackTrace();
		}
		return null;
	}

	public int insertMapper(WXOrderDO Entity) {
		

		try {
			int i = baseDAO.insert(Entity,nameSpace);
			return i;
		} catch (Exception e) {
			LOGGER.error(name + "根据所有满足条件获取分页数据失败：", e);
			e.printStackTrace();
		}
		return 0;
	}

	public int delAccountMapper(Integer id) {
		

		try {
			int i = baseDAO.delete(id,nameSpace);
			return i;
		} catch (Exception e) {
			LOGGER.error(name + "根据所有满足条件获取分页数据失败：", e);
			e.printStackTrace();
		}
		return 0;
	}

	public WXOrderDO getByorderId(String orderId) {
		Map<String, Object> param = new HashMap<>();
		param.put("orderId", orderId);
		List<WXOrderDO> l = getMapperList(param);
		if (l != null && l.size() > 0) {
			return l.get(0);
		} else {
			return null;
		}
	}
}
