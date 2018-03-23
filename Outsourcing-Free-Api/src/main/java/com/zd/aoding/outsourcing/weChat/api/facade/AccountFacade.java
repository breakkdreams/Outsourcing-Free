package com.zd.aoding.outsourcing.weChat.api.facade;

import java.util.List;
import java.util.Map;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.AccountBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.AccountDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ManagerDO;

public interface AccountFacade {
	/**
	 * @Title: insertAccount
	 * @Description: 插入账号
	 * @param type 插入账号类型
	 * @param AccountDO 账号
	 * @return
	 * @return: int 1成功
	 */
	int insertAccountByType(Integer type, AccountDO AccountDO);
	AccountDO getPoByPK(Integer i);
	AccountBO getBoByPK(Integer i);
	List<AccountDO> getPoBySQLAdapter(Map<String, Object> param);
	/**
	 * 登录查询
	 * @param param
	 * @return
	 */
	List<AccountDO> getList(Map<String, Object> param);
	/**
	 * @Title: getUserAccountDOByPhone
	 * @Description:根据用户手机号查询账号
	 * @param phone
	 * @return
	 * @return: AccountDO
	 */
	AccountDO getUserAccountPoByPhone(String phone);
	AccountDO getManagerAccountPoByPhone(String phone,Integer type);
	/**
	 * @Title: reSetPassword
	 * @Description: 重设密码
	 * @param newPassword
	 * @param id
	 * @return
	 * @return: int
	 */
	int reSetPassword(String newPassword, Integer id);
	/**
	 * @Title: insertAccount
	 * @Description: 插入账号
	 * @param accountPo
	 * @return
	 * @return: int
	 */
	int insertAccount(AccountDO accountPo);
	/**
	 * @Title: deleteAccount
	 * @Description: 删除账号
	 * @param id
	 * @return
	 * @return: int
	 */
	int deleteAccount(Integer id);

	int updateAccountPo(AccountDO accountPo);

	/**
	 * 注册管理员
	 * @param accountPo
	 * @param name
	 * @return
	 */
	int registerManager(AccountDO accountPo,String name, Integer type, Integer addAccountId);
	/**
	 * 注册经销商
	 * @param accountPo
	 * @param name
	 * @return
	 */
	int registerDistributor(AccountDO accountPo,String name, Integer type, Integer addAccountId,Integer provinceId);
	/**
	 * 注册营业员
	 * @param accountPo
	 * @param name
	 * @return
	 */
	int registerSalesman(AccountDO accountPo,String name, Integer type, Integer addAccountId);
	/**
	 * 注册商家
	 * @param accountPo
	 * @param param
	 * @return
	 */
	int registerBusiness(AccountDO accountPo,Map<String, Object> param);

	/**
	 * 注册供货商
	 * @param accountPo
	 * @param param
	 * @return
	 */
	int registerSupplier(AccountDO accountPo,Map<String, Object> param);
	
	/**
	 * 注册商户
	 * @param accountPo
	 * @param param
	 * @return
	 */
	int registerDealer(AccountDO accountPo,Map<String, Object> param);

	/**
	 * 注册客户
	 * @param accountPo
	 * @param param
	 * @return
	 */
	int registerAppCompany(AccountDO accountPo,Map<String, Object> param);

	PageResult<AccountBO> getPageAccountVo(PageEntity pageEntity);
	int countAccount(Map<String, Object> param);
}
