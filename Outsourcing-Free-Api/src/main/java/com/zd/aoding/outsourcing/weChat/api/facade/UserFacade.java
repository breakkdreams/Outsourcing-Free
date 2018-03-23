package com.zd.aoding.outsourcing.weChat.api.facade;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.UserBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.AccountDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.UserDO;

public interface UserFacade {
	/**
	 * @Title: getUserByAccountId
	 * @Description: 根据账号查询用户信息
	 * @param id
	 * @return
	 * @return: UserVo
	 */
	UserBO getUserByAccountId(Integer id);
	
	UserDO getUserPoByAccountId(Integer id);
	
	UserDO getUserByUserName(String userName);
	
	UserDO getUserByUserCode(String refreeCode);
	/**
	 * @Title: registerUser
	 * @Description: 注册用户
	 * @param accountPo
	 * @param refereeCode
	 * @return
	 * @return: int
	 */
	int createUser(AccountDO accountPo, String refereeCode);
	
	int registerUser(AccountDO accountPo, String appCode, String refereePhone);
//	/**
//	 * @Title: insertRealInfo
//	 * @Description: 插入实名信息
//	 * @param userRealInfoPo
//	 * @return
//	 * @return: int
//	 */
//	int insertUserRealInfo(UserRealInfoPo userRealInfoPo);
//	/**
//	 * @Title: getUserRealInfo
//	 * @Description: 获取用户实名信息
//	 * @param userId
//	 * @return
//	 * @return: UserRealInfoPo
//	 */
//	UserRealInfoVo getUserRealInfo(Integer userId);
//	/**
//	 * @Title: updateUserRealInfo
//	 * @Description:更新实名信息
//	 * @param userRealInfoPo
//	 * @return
//	 * @return: int
//	 */
//	int updateUserRealInfo(UserRealInfoPo userRealInfoPo);
	/**
	 * @Title: getUserPage
	 * @Description: 查询用户
	 * @param pageEntity
	 * @return
	 * @return: PageResult<UserVo>
	 */
	PageResult<UserBO> getUserPage(PageEntity pageEntity);
	
	int updateUserPo(UserDO userPo);
	
	UserDO getUserByUserId(Integer userId);
}
