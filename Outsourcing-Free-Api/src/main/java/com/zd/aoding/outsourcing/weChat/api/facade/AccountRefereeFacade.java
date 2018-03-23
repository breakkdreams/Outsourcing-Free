package com.zd.aoding.outsourcing.weChat.api.facade;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.AccountRefereeBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.AccountRefereeDO;

public interface AccountRefereeFacade {
	/**
	 * @Title: insertAccountRefereePo
	 * @Description: 插入推荐关系
	 * @param accountRefereePo 
	 * @return
	 * @return: int 1成功
	 */
	int insertAccountRefereePo(AccountRefereeDO accountRefereePo);
	int upadteAccountRefereePo(AccountRefereeDO accountRefereePo);
	/**
	 * @Title: getAccountRefereePoByBeAccountId
	 * @Description: 根据被推荐人Id查询推荐关系
	 * @param beAccountId 
	 * @return
	 * @return: AccountRefereePo
	 */
	AccountRefereeDO getAccountRefereePoByBeAccountId(Integer beAccountId);
	
	/**
	 * 分页查询
	 * @param pageEntity
	 * @return
	 */
	PageResult<AccountRefereeBO> getPageAccountRefereeVo(PageEntity pageEntity);
}
