package com.zd.aoding.outsourcing.weChat.api.facade;

import java.util.List;

import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.PurseBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.UserPurseBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.UserPurseDO;

public interface UserPurseFacade {
	/**
	 * @Title: insertRMBPurse
	 * @Description: 插入人民币钱包
	 * @param rmbPursePo
	 * @return
	 * @return: int
	 */
	int insertPurse(UserPurseDO pursePo);
	/**
	 * @Title: getPurseVoByUserId
	 * @Description: 查询钱包信息
	 * @param userId
	 * @param toWhat
	 * @return
	 * @return: PurseVo
	 */
	PurseBO getPurseVoByUserId(Integer userId, String appCode);
	List<PurseBO> getPurseVoListByUserId(Integer userId);
	/**
	 * @Title: getPursePoByUserId
	 * @Description: 获取rmb钱包
	 * @param userId
	 * @return
	 * @return: RMBUserPursePo
	 */
	UserPurseDO getPursePoByUserId(Integer userId, String appCode);
	UserPurseBO getUserPurseByUserId(Integer userId, String appCode);
	int updatePurse(UserPurseDO userPurse);
	
	/**
	 * @Title: setPassword
	 * @Description: 设置密码
	 * @param id
	 * @param tradePassword
	 * @return
	 * @return: int
	 */
	int setPassword(Integer id, String tradePassword);
}
