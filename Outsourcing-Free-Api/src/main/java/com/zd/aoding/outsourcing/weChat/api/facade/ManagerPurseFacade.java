package com.zd.aoding.outsourcing.weChat.api.facade;

import java.util.List;

import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.ManagerPurseBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.PurseBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.UserPurseBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ManagerPurseDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.UserPurseDO;

public interface ManagerPurseFacade {
	/**
	 * @Title: insertRMBPurse
	 * @Description: 插入人民币钱包
	 * @param rmbPursePo
	 * @return
	 * @return: int
	 */
	int insertManagerPurse(ManagerPurseDO managerPursePo);
	/**
	 * @Title: getPurseVoByUserId
	 * @Description: 查询钱包信息
	 * @param userId
	 * @param toWhat
	 * @return
	 * @return: PurseVo
	 */
	ManagerPurseBO getManagerPurseVoBymanagerId(Integer managerId, String appCode);
	List<ManagerPurseBO> getManagerPurseVoListByManagerId(Integer managerId);
	/**
	 * @Title: getPursePoByUserId
	 * @Description: 获取rmb钱包
	 * @param userId
	 * @return
	 * @return: RMBUserPursePo
	 */
	ManagerPurseDO getManagerPursePoByManagerId(Integer managerId, String appCode);
	ManagerPurseBO getManagerPurseByManagerId(Integer managerId, String appCode);
	int updateManagerPurse(ManagerPurseDO managerPurse);
	
	/**
	 * @Title: setPassword
	 * @Description: 设置密码
	 * @param id
	 * @param tradePassword
	 * @return
	 * @return: int
	 */
	int setManagerPassword(Integer id, String tradePassword);
}
