package com.zd.aoding.outsourcing.weChat.provider.facade.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.common.StringDate.DateUtil;
import com.zd.aoding.common.log.LogUtil;
import com.zd.aoding.common.onlyCodeNum.OnlyCodeNum;
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.PurseBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.UserBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.AccountDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.AccountRefereeDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.UserDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.UserPurseDO;
import com.zd.aoding.outsourcing.weChat.api.facade.AccountFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.AccountRefereeFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SystemparamFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.UserFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.UserPurseFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.UserService;

@Service
public class UserFacadeImpl implements UserFacade {
	@Autowired
	private UserService userService;
	@Autowired
	private AccountFacade accountFacade;
	@Autowired
	private UserPurseFacade userPurseFacade;
	@Autowired
	private SystemparamFacade systemparamFacade;
	@Autowired
	private AccountRefereeFacade accountRefereeFacade;

	@Override
	public UserBO getUserByAccountId(Integer accountId) {
		Map<String, Object> param = new HashMap<>();
		param.put("accountId", accountId);
		param.put("deleted", 0);
		UserDO userPo = getOnlyOneUserPoByMap(param);
		if (userPo != null) {
			UserBO userVo = view(userPo, UserBO.TO_ALL);
			return userVo;
		}
		return null;
	}
	@Override
	public UserDO getUserPoByAccountId(Integer accountId) {
		Map<String, Object> param = new HashMap<>();
		param.put("accountId", accountId);
		param.put("deleted", 0);
		UserDO userPo = getOnlyOneUserPoByMap(param);
		if (userPo != null) {
			return userPo;
		}
		return null;
	}
	private UserDO getOnlyOneUserPoByMap(Map<String, Object> param) {
		List<UserDO> list = userService.getList(param);
		if (list == null || list.size() == 0) {
			return null;
		}
		if (list.size() == 1) {
			return list.get(0);
		}
		if (list.size() > 1) {
			LogUtil.dataError("多个实名信息" + list, getClass());
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public int registerUser(AccountDO accountPo, String appCode, String refereeCode) {
		try {
			String phone = accountPo.getUsername();
			if(phone.contains("-")){
    			String[] ary = phone.split("-");
    			phone = ary[1];
    		}
			// 创建账号
			int i = accountFacade.insertAccount(accountPo);
			// 创建用户
			if (i == 1) {
				String reCode = OnlyCodeNum.getRefreeCode("", accountPo.getId());
				UserDO userPo = new UserDO(accountPo.getId(), phone, appCode);
				userPo.setRefreeCode(reCode);
				int u = userService.insert(userPo);
				// 创建钱包
				if (u == 1) {
					BigDecimal defaultValue = new BigDecimal("0");
					UserPurseDO rmbPursePo = new UserPurseDO(userPo.getId(), appCode);
					int p = userPurseFacade.insertPurse(rmbPursePo);
					System.out.println("p == "+p);
					if (p == 1) {
						if(refereeCode != null){
							UserDO userDO = getUserByUserCode(refereeCode);
							AccountDO refereeAccount = accountFacade.getPoByPK(userDO.getAccountId());
							if(refereeAccount != null){
								AccountRefereeDO accountRefereePo = new AccountRefereeDO(accountPo.getId(), refereeAccount.getId());
								int q = accountRefereeFacade.insertAccountRefereePo(accountRefereePo);
								if(q != 1){
									LogUtil.dataError("添加推荐关系失败berefereeAccountId=="+accountPo.getId()+",refereeAccountId=="+refereeAccount.getId(), getClass());
								}
//								SystemparamDO systemparam = systemparamFacade.getSystemparamPoByCode("refereeScore");
//								UserBO userVo = getUserByAccountId(refereeAccount.getId());
//								UserDO refereeUser = new UserDO();
//								refereeUser.setId(userVo.getUserId());
//								refereeUser.setVipScore(userVo.getVipScore()+systemparam.getIntVale());
//								int k = userService.update(refereeUser);
//								if(k != 1){
//									LogUtil.dataError("添加推荐积分失败 refereeUserId="+userVo.getUserId()+"积分="+systemparam.getIntVale(), getClass());
//								}
							}
						}
						return 1;
					} else {
						// 失败删除账号、用户
						deleteUser(userPo.getId());
						accountFacade.deleteAccount(accountPo.getId());
					}
				} else {
					// 失败删除账号
					accountFacade.deleteAccount(accountPo.getId());
				}
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	@Override
	public int createUser(AccountDO accountPo, String appCode) {
		try {
			UserDO userPo = new UserDO(accountPo.getId(), accountPo.getUsername(), appCode);
			int u = userService.insert(userPo);
			System.out.println("u == "+u);
			// 创建钱包
			if (u == 1) {
				BigDecimal defaultValue = new BigDecimal("0");
				UserPurseDO rmbPursePo = new UserPurseDO(userPo.getId(), appCode);
				int p = userPurseFacade.insertPurse(rmbPursePo);
				System.out.println("p == "+p);
				if (p == 1) {
					return 1;
				} else {
					// 失败用户
					deleteUser(userPo.getId());
				}
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	private int deleteUser(Integer id) {
		return userService.delete(id);
	}
	/*@Override
	public int insertUserRealInfo(UserRealInfoPo userRealInfoPo) {
		// TODO Auto-generated method stub
		return userRealInfoService.insert(userRealInfoPo);
	}
	@Override
	public UserRealInfoVo getUserRealInfo(Integer userId) {
		UserRealInfoPo info = getOnlyOneUserRealInfoPo(userId);
		if (info != null) {
			return new UserRealInfoVo(info);
		}
		return null;
	}*/
	/*@Override
	public int updateUserRealInfo(UserRealInfoPo userRealInfoPo) {
		// TODO Auto-generated method stub
		return userRealInfoService.update(userRealInfoPo);
	}*/
	/*private UserRealInfoPo getOnlyOneUserRealInfoPo(Integer userId) {
		Map<String, Object> param = new HashMap<>();
		param.put("userId", userId);
		param.put("deleted", 1);
		List<UserRealInfoPo> list = userRealInfoService.getList(param);
		if (list == null || list.size() == 0) {
			return null;
		}
		if (list.size() == 1) {
			return list.get(0);
		}
		if (list.size() > 1) {
			LogUtil.dataError("多个实名信息" + list, getClass());
			return list.get(0);
		}
		return null;
	}*/
	
	@Override
	public PageResult<UserBO> getUserPage(PageEntity pageEntity) {
		try {
			PageResult<UserBO> pageResult = new PageResult<UserBO>();
			List<UserDO> list = userService.getPagination(pageEntity);
			List<UserBO> listVo = new ArrayList<>();
			if (list != null && list.size() > 0) {
				for (UserDO userPo : list) {
					UserBO userVo = view(userPo,UserBO.TO_PAGING);
					listVo.add(userVo);
				}
			}
			pageResult.setResultList(listVo);
			pageResult.setCurrentPage(pageEntity.getPage());
			pageResult.setPageSize(pageEntity.getSize());
			pageResult.setTotalSize(userService.count(pageEntity.getParams()));
			return pageResult;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public int updateUserPo(UserDO userPo) {
		return userService.update(userPo);
	}
	@Override
	public UserDO getUserByUserId(Integer userId) {
		UserDO userPo = userService.get(userId);
		if (userPo != null) {
			return userPo;
		}
		return null;
	}
	
	private UserBO view(UserDO userPo, String pageorall){
		UserBO userVo = new UserBO(userPo, pageorall);
		String username = "";
		AccountDO account = accountFacade.getPoByPK(userPo.getAccountId());
		if(account != null){
			username = account.getUsername();
		}
		double score = 0.0;
		PurseBO purseBO = userPurseFacade.getPurseVoByUserId(userPo.getId(), "0");
		if(purseBO!=null){
			score = purseBO.getScore();
		}
		//推荐人
		String refereeUsername = "";
		AccountRefereeDO refreeDO = accountRefereeFacade.getAccountRefereePoByBeAccountId(userPo.getAccountId());
		if(refreeDO!=null){
			int accountId = refreeDO.getRefereeAccountId();
			AccountDO accountDO = accountFacade.getPoByPK(accountId);
			refereeUsername = accountDO.getUsername();
		}
		userVo.setUsername(username);
		String createTimeStr = DateUtil.Format("yyyy-MM-dd", userPo.getCreateTime());
		String createApp = "";
		userVo.setCreateTimeStr(createTimeStr);
		userVo.setCreateApp(createApp);
		userVo.setScore(score);
		userVo.setRefereeUsername(refereeUsername);
		return userVo;
	}
	
	@Override
	public UserDO getUserByUserName(String userName) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("nickName", userName);
		map.put("deleted", 0);
		List<UserDO> list = userService.getList(map);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	@Override
	public UserDO getUserByUserCode(String refreeCode) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("refreeCode", refreeCode);
		map.put("deleted", 0);
		List<UserDO> list = userService.getList(map);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
