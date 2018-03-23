package com.zd.aoding.outsourcing.weChat.api.facade;

import java.util.List;
import java.util.Map;

import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.SystemparamDO;


public interface SystemparamFacade {
	/**
	 * 根据code查询
	 * @param String code
	 * @return
	 */
	SystemparamDO getSystemparamPoByCode(String code);
	
	List<SystemparamDO> getSystemparamList(Map<String, Object> param);
	
	int updateSystemparam(SystemparamDO systemparamPo);
}
