package com.zd.aoding.outsourcing.weChat.api.facade;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.BankBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.ChargeParamBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.BankDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ChargeParamDO;

import java.util.List;
import java.util.Map;

public interface ChargeParamFacade {
	
	int insertChargeParamDO(ChargeParamDO chargeParamDO);

	ChargeParamDO getChargeParamDOByPK(Integer chargeParamId);
	ChargeParamBO getChargeParamBOByPK(Integer chargeParamId);
	
	int updateChargeParamDO(ChargeParamDO chargeParamDO);
	
	PageResult<ChargeParamBO> getPageChargeParamBO(PageEntity pageEntity);
	
	int countChargeParam(Map<String, Object> param);

	List<ChargeParamDO> getChargeParamDOList(Map<String, Object> param);
}
