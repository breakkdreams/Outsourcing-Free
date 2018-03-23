package com.zd.aoding.outsourcing.weChat.provider.facade.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.BankBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.BankDO;
import com.zd.aoding.outsourcing.weChat.api.facade.BankFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.BankService;



@Service
public class BankFacadeImpl implements BankFacade {
	@Autowired
	private BankService bankService;

	@Override
	public int insertBankDO(BankDO bankDO) {
		bankDO.insertInit();
		return bankService.insert(bankDO);
	}

	@Override
	public BankDO getBankDOByPK(Integer bankId) {
		Map<String, Object> param = new HashMap<>();
		param.put("id", bankId);
		param.put("deleted", 0);
		BankDO bankDO = bankService.get(bankId);
		if (bankDO != null) {
			return bankDO;
		}
		return null;
	}
	@Override
	public BankBO getBankBOByPK(Integer bankId) {
		Map<String, Object> param = new HashMap<>();
		param.put("id", bankId);
		param.put("deleted", 0);
		BankDO bankDO = bankService.get(bankId);
		if (bankDO != null) {
			BankBO bankVo = new BankBO(bankDO);
			return bankVo;
		}
		return null;
	}

	@Override
	public int updateBankDO(BankDO bankDO) {
		return bankService.update(bankDO);
	}

	@Override
	public PageResult<BankBO> getPageBankBO(PageEntity pageEntity) {
		PageResult<BankBO> pageResult = new PageResult<BankBO>();
		List<BankDO> list = bankService.getPagination(pageEntity);
		List<BankBO> listVo = new ArrayList<>();
		if (list != null && list.size() > 0) {
			for (BankDO bankPo : list) {
				listVo.add(new BankBO(bankPo));
			}
		}
		pageResult.setResultList(listVo);
		pageResult.setCurrentPage(pageEntity.getPage());
		pageResult.setPageSize(pageEntity.getSize());
		pageResult.setTotalSize(bankService.count(pageEntity.getParams()));
		return pageResult;
	}

	@Override
	public int countBank(Map<String, Object> param) {
		try {
			return bankService.count(param);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
