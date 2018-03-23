package com.zd.aoding.outsourcing.weChat.provider.facade.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.common.log.LogUtil;
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.AccountRefereeBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.AccountRefereeDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.UserDO;
import com.zd.aoding.outsourcing.weChat.api.facade.AccountFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.AccountRefereeFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.UserFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.AccountRefereeService;

@Service
public class AccountRefereeFacadeImpl implements AccountRefereeFacade {
	@Autowired
	private AccountRefereeService accountRefereeService;
	@Autowired
	private AccountFacade accountFacade;
	@Autowired
	private UserFacade userFacade;

	@Override
	public int insertAccountRefereePo(AccountRefereeDO accountRefereePo) {
		try {
			int i = accountRefereeService.insert(accountRefereePo);
			if(i != 1){
				i = accountRefereeService.insert(accountRefereePo);
			}
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public AccountRefereeDO getAccountRefereePoByBeAccountId(Integer beAccountId) {
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("deleted", 0);
			param.put("beRefereeAccountId", beAccountId);
			List<AccountRefereeDO> list = accountRefereeService.getList(param);
			if(list != null && list.size() == 1){
				return list.get(0);
			}
			if(list != null && list.size() > 1){
				LogUtil.dataError("根据被推荐人查出多个推荐关系", getClass());
				return list.get(0);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public PageResult<AccountRefereeBO> getPageAccountRefereeVo(PageEntity pageEntity) {
		try {
			PageResult<AccountRefereeBO> pageResult = new PageResult<AccountRefereeBO>();
			List<AccountRefereeDO> list = accountRefereeService.getPagination(pageEntity);
			List<AccountRefereeBO> listVo = new ArrayList<AccountRefereeBO>();
			for(AccountRefereeDO accountRefereePo : list){
				if(accountRefereePo != null){
					listVo.add(view(accountRefereePo));
				}
			}
			pageResult.setResultList(listVo);
			pageResult.setCurrentPage(pageEntity.getPage());
			pageResult.setPageSize(pageEntity.getSize());
			pageResult.setTotalSize(accountRefereeService.count(pageEntity.getParams()));
			return pageResult;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private AccountRefereeBO view(AccountRefereeDO accountRefereePo){
		AccountRefereeBO accountRefereeVo = new AccountRefereeBO(accountRefereePo);
		String nickName = "未完善";
		String phone = "未完善";
		UserDO beUser = userFacade.getUserPoByAccountId(accountRefereePo.getBeRefereeAccountId());
		if(beUser != null){
			if(beUser.getNickName() != null){
				nickName = beUser.getNickName();
			}
			if(beUser.getPhone() != null){
				phone = beUser.getPhone();
			}
		}
		accountRefereeVo.setNickName(nickName);
		accountRefereeVo.setPhone(phone);
		return accountRefereeVo;
	}

    @Override
    public int upadteAccountRefereePo(AccountRefereeDO accountRefereePo) {
        return accountRefereeService.update(accountRefereePo);
    }

}
