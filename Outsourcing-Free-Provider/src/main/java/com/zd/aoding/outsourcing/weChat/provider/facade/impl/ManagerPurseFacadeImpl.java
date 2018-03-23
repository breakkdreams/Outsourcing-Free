package com.zd.aoding.outsourcing.weChat.provider.facade.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.common.Md5.MD5Util;
import com.zd.aoding.common.log.LogUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.ManagerPurseBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ManagerPurseDO;
import com.zd.aoding.outsourcing.weChat.api.facade.ManagerPurseFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.ManagerPurseService;


@Service
public class ManagerPurseFacadeImpl implements ManagerPurseFacade {
	@Autowired
	private ManagerPurseService managerPurseService;

	@Override
	public int insertManagerPurse(ManagerPurseDO managerPursePo) {
		return managerPurseService.insert(managerPursePo);
	}
	@Override
	public ManagerPurseBO getManagerPurseVoBymanagerId(Integer managerId, String appCode) {
		ManagerPurseDO managerPursePo = getOnlyOneManagerPursePo(managerId, appCode);
		return new ManagerPurseBO(managerPursePo);
	}
	@Override
	public List<ManagerPurseBO> getManagerPurseVoListByManagerId(Integer managerId) {
		Map<String, Object> param = new HashMap<>();
		param.put("managerId", managerId);
		param.put("deleted", 0);
		List<ManagerPurseDO> list = managerPurseService.getList(param);
		List<ManagerPurseBO> listVo = new ArrayList<ManagerPurseBO>();
		if(list != null && list.size() > 0){
			for(ManagerPurseDO managerPursePo : list){
				ManagerPurseBO purseVo = new ManagerPurseBO(managerPursePo);
				listVo.add(purseVo);
			}
		}
		return listVo;
	}
	private ManagerPurseDO getOnlyOneManagerPursePo(Integer managerId, String appCode) {
		Map<String, Object> param = new HashMap<>();
		param.put("managerId", managerId);
		param.put("deleted", 0);
		List<ManagerPurseDO> list = managerPurseService.getList(param);
		if (list == null || list.size() == 0) {
			return null;
		}
		if (list.size() == 1) {
			return list.get(0);
		}
		if (list.size() > 1) {
			LogUtil.dataError("多个钱包" + list, getClass());
			return list.get(0);
		}
		return null;
	}
	@Override
	public ManagerPurseDO getManagerPursePoByManagerId(Integer managerId, String appCode) {
		// TODO Auto-generated method stub
		return getOnlyOneManagerPursePo(managerId, appCode);
	}
	@Override
	public ManagerPurseBO getManagerPurseByManagerId(Integer managerId, String appCode) {
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("managerId", managerId);
			param.put("deleted", 0);
			List<ManagerPurseDO> list = managerPurseService.getList(param);
			if(list != null && list.size() > 1){
				LogUtil.dataError("managerId = "+managerId +",出现多个钱包", getClass());
				return new ManagerPurseBO(list.get(0));
			}
			if(list != null && list.size() == 1){
				return new ManagerPurseBO(list.get(0));
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public int setManagerPassword(Integer id, String tradePassword) {
		ManagerPurseDO entity = new ManagerPurseDO();
		entity.setId(id);
		entity.setPassword(MD5Util.MD5(tradePassword));
		return managerPurseService.update(entity);
	}
	@Override
	public int updateManagerPurse(ManagerPurseDO managerPurse) {
		try {
			return managerPurseService.update(managerPurse);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
