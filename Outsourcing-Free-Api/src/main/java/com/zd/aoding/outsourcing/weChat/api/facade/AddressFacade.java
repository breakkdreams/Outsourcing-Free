package com.zd.aoding.outsourcing.weChat.api.facade;

import java.util.List;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.UserAddressBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.UserAddressDO;

public interface AddressFacade {
	/**
	 * @Title: insertAddressPo
	 * @Description: TODO
	 * @param addressPo
	 * @return
	 * @return: int
	 */
	int insertAddressPo(UserAddressDO addressPo);
	/**
	 * @Title: updateAddressPo
	 * @Description: 修改地址
	 * @param addressPo
	 * @return
	 * @return: int
	 */
	int updateAddressPo(UserAddressDO addressPo);
	/**
	 * @Title: getAddressVoByPK
	 * @Description: 根据id查询地址
	 * @param addressId
	 * @return
	 * @return: UserAddressVo
	 */
	UserAddressBO getAddressVoByPK(String addressId);
	/**
	 * @Title: getDefaultAddressVo
	 * @Description: 查看用户默认地址
	 * @param userId
	 * @return
	 * @return: AccountAddressVo
	 */
	UserAddressBO getDefaultAddressVo(Integer userId);
	List<UserAddressBO> getAddressVoList(Integer userId);
	
	List<UserAddressBO> getAddressVoListByName(String name);
	/**
	 * @Title: getPageAddressVo
	 * @Description: 条件分页查询
	 * @param pageEntity
	 * @return
	 * @return: PageResult<UserAddressVo>
	 */
	PageResult<UserAddressBO> getPageAddressVo(PageEntity pageEntity);
	
	/**
	 * @Title: countUserAddress
	 * @Description: 统计用户收货地址数量
	 * @param userId
	 * @return
	 * @return: int
	 */
	int countUserAddress(Integer userId);
}
