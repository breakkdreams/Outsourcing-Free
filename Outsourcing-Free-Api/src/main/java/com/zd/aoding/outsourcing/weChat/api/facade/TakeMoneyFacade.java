package com.zd.aoding.outsourcing.weChat.api.facade;

import java.util.List;
import java.util.Map;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.TakeMoneyBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.UserAddressBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.TakeMoneyDO;

public interface TakeMoneyFacade {
	/**
	 * @Title: insertAddressPo
	 * @Description: TODO
	 * @param addressPo
	 * @return
	 * @return: int
	 */
	int insertTakeMoneyDO(TakeMoneyDO takeMoneyDO);
	/**
	 * @Title: updateAddressPo
	 * @Description: 修改地址
	 * @param addressPo
	 * @return
	 * @return: int
	 */
	int updateTakeMoneyDO(TakeMoneyDO takeMoneyDO);
	/**
	 * @Title: getAddressVoByPK
	 * @Description: 根据id查询地址
	 * @param addressId
	 * @return
	 * @return: UserAddressVo
	 */
	TakeMoneyBO getTakeMoneyBOByPK(String takeMoneyId);
	/**
	 * @Title: getPageAddressVo
	 * @Description: 条件分页查询
	 * @param pageEntity
	 * @return
	 * @return: PageResult<UserAddressVo>
	 */
	PageResult<TakeMoneyBO> getPageTakeMoneyBO(PageEntity pageEntity);
	
	/**
	 * @Title: countUserAddress
	 * @Description: 统计用户收货地址数量
	 * @param userId
	 * @return
	 * @return: int
	 */
	int countUserTakeMoney(Map<String, Object> param);
}
