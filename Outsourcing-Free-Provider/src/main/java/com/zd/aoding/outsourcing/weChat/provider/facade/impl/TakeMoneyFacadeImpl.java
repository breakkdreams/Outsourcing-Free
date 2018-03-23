package com.zd.aoding.outsourcing.weChat.provider.facade.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.TakeMoneyBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.TakeMoneyDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.UserDO;
import com.zd.aoding.outsourcing.weChat.api.facade.TakeMoneyFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.UserFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.TakeMoneyService;

@Service
public class TakeMoneyFacadeImpl implements TakeMoneyFacade {
	@Autowired
	private TakeMoneyService takeMoneyService;
	@Autowired
	private UserFacade userFacade;

	@Override
	public int insertTakeMoneyDO(TakeMoneyDO takeMoneyDO) {
		takeMoneyDO.insertInit();
		return takeMoneyService.insert(takeMoneyDO);
	}

	@Override
	public int updateTakeMoneyDO(TakeMoneyDO takeMoneyDO) {
		return takeMoneyService.update(takeMoneyDO);
	}

	@Override
	public TakeMoneyBO getTakeMoneyBOByPK(String takeMoneyId) {
		Map<String, Object> param = new HashMap<>();
		TakeMoneyDO takeMoneyDO = takeMoneyService.get(Integer.parseInt(takeMoneyId));
		if (takeMoneyDO != null) {
			return new TakeMoneyBO(takeMoneyDO);
		}
		return null;
	}

	@Override
	public PageResult<TakeMoneyBO> getPageTakeMoneyBO(PageEntity pageEntity) {
		try {
			PageResult<TakeMoneyBO> pageResult = new PageResult<TakeMoneyBO>();
			List<TakeMoneyDO> list = takeMoneyService.getPagination(pageEntity);
			List<TakeMoneyBO> listVo = new ArrayList<>();
			if (list != null && list.size() > 0) {
				for (TakeMoneyDO takeMoneyPo : list) {
					TakeMoneyBO takeMoneyBO = view(takeMoneyPo);
					listVo.add(takeMoneyBO);
				}
			}
			pageResult.setResultList(listVo);
			pageResult.setCurrentPage(pageEntity.getPage());
			pageResult.setPageSize(pageEntity.getSize());
			pageResult.setTotalSize(takeMoneyService.count(pageEntity.getParams()));
			return pageResult;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private TakeMoneyBO view(TakeMoneyDO takeMoneyPo){
		TakeMoneyBO takeMoneyBO = new TakeMoneyBO(takeMoneyPo);
		String name = "";
		int userid = takeMoneyPo.getUserId();
		UserDO userDO = userFacade.getUserByUserId(userid);
		if(userDO!=null){
			name = userDO.getPhone();
		}
		takeMoneyBO.setName(name);
		return takeMoneyBO;
	}

	@Override
	public int countUserTakeMoney(Map<String, Object> param) {
		try {
			return takeMoneyService.count(param);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	
}
