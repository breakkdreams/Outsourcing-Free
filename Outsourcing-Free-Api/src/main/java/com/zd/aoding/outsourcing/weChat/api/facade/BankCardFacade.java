package com.zd.aoding.outsourcing.weChat.api.facade;

import java.util.Map;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.BankCardBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.BankCardDO;

public interface BankCardFacade {
	
	int insertBankCardDO(BankCardDO bankCardDO);
	
	BankCardDO getBankCardDOByPK(Integer bankCardId);
	BankCardBO getBankCardBOByPK(Integer bankCardId);
	
	int updateBankCardDO(BankCardDO bankCardDO);
	
	PageResult<BankCardBO> getPageBankCardBO(PageEntity pageEntity);
	
	int countBankCard(Map<String, Object> param);
}
