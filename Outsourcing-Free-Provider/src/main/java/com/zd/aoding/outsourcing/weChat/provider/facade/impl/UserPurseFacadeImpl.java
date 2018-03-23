package com.zd.aoding.outsourcing.weChat.provider.facade.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.common.Md5.MD5Util;
import com.zd.aoding.common.log.LogUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.PurseBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.UserPurseBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.UserPurseDO;
import com.zd.aoding.outsourcing.weChat.api.facade.UserPurseFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.UserPurseService;


@Service
public class UserPurseFacadeImpl implements UserPurseFacade {
	@Autowired
	private UserPurseService userPurseService;

	@Override
	public int insertPurse(UserPurseDO pursePo) {
		// TODO Auto-generated method stub
		return userPurseService.insert(pursePo);
	}
	@Override
	public PurseBO getPurseVoByUserId(Integer userId, String appCode) {
		UserPurseDO userPursePo = getOnlyOneUserPursePo(userId, appCode);
		return new PurseBO(userPursePo);
	}
	@Override
	public List<PurseBO> getPurseVoListByUserId(Integer userId) {
		Map<String, Object> param = new HashMap<>();
		param.put("userId", userId);
		param.put("deleted", 0);
		List<UserPurseDO> list = userPurseService.getList(param);
		List<PurseBO> listVo = new ArrayList<PurseBO>();
		if(list != null && list.size() > 0){
			for(UserPurseDO userPursePo : list){
				PurseBO purseVo = new PurseBO(userPursePo);
				String appName = "";
				purseVo.setAppName(appName);
				listVo.add(purseVo);
			}
		}
		return listVo;
	}
	private UserPurseDO getOnlyOneUserPursePo(Integer userId, String appCode) {
		Map<String, Object> param = new HashMap<>();
		param.put("userId", userId);
		param.put("deleted", 0);
		List<UserPurseDO> list = userPurseService.getList(param);
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
	public UserPurseDO getPursePoByUserId(Integer userId, String appCode) {
		// TODO Auto-generated method stub
		return getOnlyOneUserPursePo(userId, appCode);
	}
	@Override
	public UserPurseBO getUserPurseByUserId(Integer userId, String appCode) {
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("userId", userId);
			param.put("deleted", 0);
			List<UserPurseDO> list = userPurseService.getList(param);
			if(list != null && list.size() > 1){
				LogUtil.dataError("userId = "+userId +",出现多个钱包", getClass());
				return new UserPurseBO(list.get(0));
			}
			if(list != null && list.size() == 1){
				return new UserPurseBO(list.get(0));
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public int setPassword(Integer id, String tradePassword) {
		UserPurseDO entity = new UserPurseDO();
		entity.setId(id);
		entity.setPassword(MD5Util.MD5(tradePassword));
		return userPurseService.update(entity);
	}
	@Override
	public int updatePurse(UserPurseDO userPurse) {
		try {
			return userPurseService.update(userPurse);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
