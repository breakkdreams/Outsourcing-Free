package com.zd.aoding.outsourcing.weChat.api.facade;

import java.util.Map;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.BankBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.BankDO;

public interface BankFacade {
	
	int insertBankDO(BankDO bankDO);
	
	BankDO getBankDOByPK(Integer bankId);
	BankBO getBankBOByPK(Integer bankId);
	
	int updateBankDO(BankDO bankDO);
	
	PageResult<BankBO> getPageBankBO(PageEntity pageEntity);
	
	int countBank(Map<String, Object> param);
}
