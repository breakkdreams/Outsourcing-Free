package com.zd.aoding.outsourcing.weChat.provider.facade.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.common.log.LogUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.SystemparamDO;
import com.zd.aoding.outsourcing.weChat.api.facade.SystemparamFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.SystemparamService;

@Service
public class SystemparamFacadeImpl implements SystemparamFacade {
	@Autowired
	private SystemparamService systemparamService;

	@Override
	public SystemparamDO getSystemparamPoByCode(String code) {
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("deleted", 0);
			param.put("code", code);
			List<SystemparamDO> list = systemparamService.getList(param);
			if(list != null && list.size() == 1){
				return list.get(0);
			}
			if(list != null && list.size() > 1){
				LogUtil.dataError("出现多个系统参数 code="+code, getClass());
				return list.get(0);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<SystemparamDO> getSystemparamList(Map<String, Object> param) {
		try {
			return systemparamService.getList(param);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int updateSystemparam(SystemparamDO systemparamPo) {
		try {
			return systemparamService.update(systemparamPo);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	
}
