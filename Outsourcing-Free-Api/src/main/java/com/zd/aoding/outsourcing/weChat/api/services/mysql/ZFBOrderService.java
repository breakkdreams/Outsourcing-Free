package com.zd.aoding.outsourcing.weChat.api.services.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.base.DAO.BaseDAO;
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ZFBOrder;

@Service
public class ZFBOrderService {
	@Autowired
	private BaseDAO<ZFBOrder, Integer> baseDAO;
	private String name = "支付宝订单(ZFBOrder)";
	private static final String nameSpace = ZFBOrder.class.getName();
	private static final Logger LOGGER = Logger.getLogger(ZFBOrderService.class);

	/**
	 * 更新或者插入 微信订单 如果 系统 订单 orderId 已存在, 不处理 如果 系统 订单 orderId 不存在, 插入该条数据
	 * 
	 * @param wxOrder
	 */
	public int upsert(ZFBOrder zFBOrder) {
	

		try {
			if (zFBOrder != null && zFBOrder.getZfbId() != null) {
				ZFBOrder zfbOrder2 = baseDAO.get(zFBOrder.getZfbId(),nameSpace);
				if (zfbOrder2 != null) {
					return baseDAO.update(zFBOrder,nameSpace);

				} else {
					return baseDAO.insert(zFBOrder,nameSpace);
				}
			} else {
				return baseDAO.insert(zFBOrder,nameSpace);
			}

		} catch (Exception e) {
			LOGGER.error(name + "更新或插入失败：", e);
			e.printStackTrace();
		}
		return 0;
	}

	public int insert(ZFBOrder zFBOrder) {
	
		try {
			int i = baseDAO.insert(zFBOrder,nameSpace);
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

	public int updateMapperByPK(ZFBOrder zFBOrder) {
	
		try {
			return baseDAO.update(zFBOrder,nameSpace);
		} catch (Exception e) {
			LOGGER.error(name + "根据主键更新信息失败：", e);
		}
		return -1;
	}

	public ZFBOrder getMapperByPK(Integer id) {
	

		try {
			return baseDAO.get(id,nameSpace);
		} catch (Exception e) {
			LOGGER.error(name + "根据主键获取信息失败：", e);
		}
		return null;
	}

	public List<ZFBOrder> getMapperList(Map<String, Object> param) {
	

		try {
			List<ZFBOrder> list = baseDAO.getList(param,nameSpace);

			return list;
		} catch (Exception e) {
			LOGGER.error(name + "根据所有满足条件获取数据失败：", e);
			System.out.println(param);
			e.printStackTrace();
		}
		return null;
	}

	public List<ZFBOrder> getMapperPage(PageEntity pageEntity) {
	

		try {
			List<ZFBOrder> list = baseDAO.getPagination(pageEntity,nameSpace);
			return list;
		} catch (Exception e) {
			LOGGER.error(name + "根据所有满足条件获取分页数据失败：", e);
			e.printStackTrace();
		}
		return null;
	}

	public int insertMapper(ZFBOrder Entity) {
	

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

	public ZFBOrder findZFBOrderByZFB(String trade_no) {
		// TODO Auto-generated method stub
		return null;
	}

	public ZFBOrder getByorderId(String orderId) {
		Map<String, Object> param = new HashMap<>();
		param.put("orderId", orderId);
		List<ZFBOrder> l = getMapperList(param);
		if (l != null && l.size() > 0) {
			return l.get(0);
		} else {
			return null;
		}
	}

}
