package com.zd.aoding.outsourcing.weChat.provider.facade.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.config.Config;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.BankBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.BankCardBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.BankCardDO;
import com.zd.aoding.outsourcing.weChat.api.facade.BankCardFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.BankFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.BankCardService;



@Service
public class BankCardFacadeImpl implements BankCardFacade {
	@Autowired
	private BankCardService bankCardService;
	@Autowired
	private BankFacade bankFacade;

	@Override
	public int insertBankCardDO(BankCardDO bankCardDO) {
		bankCardDO.insertInit();
		return bankCardService.insert(bankCardDO);
	}

	@Override
	public BankCardDO getBankCardDOByPK(Integer bankCardId) {
		Map<String, Object> param = new HashMap<>();
		param.put("id", bankCardId);
		param.put("deleted", 0);
		BankCardDO bankCardDO = bankCardService.get(bankCardId);
		if (bankCardDO != null) {
			return bankCardDO;
		}
		return null;
	}
	@Override
	public BankCardBO getBankCardBOByPK(Integer bankCardId) {
		Map<String, Object> param = new HashMap<>();
		param.put("id", bankCardId);
		param.put("deleted", 0);
		BankCardDO bankCardDO = bankCardService.get(bankCardId);
		if (bankCardDO != null) {
			BankCardBO bankCardVo = new BankCardBO(bankCardDO);
			return bankCardVo;
		}
		return null;
	}

	@Override
	public int updateBankCardDO(BankCardDO bankCardDO) {
		return bankCardService.update(bankCardDO);
	}

	@Override
	public PageResult<BankCardBO> getPageBankCardBO(PageEntity pageEntity) {
		PageResult<BankCardBO> pageResult = new PageResult<BankCardBO>();
		List<BankCardDO> list = bankCardService.getPagination(pageEntity);
		List<BankCardBO> listVo = new ArrayList<>();
		if (list != null && list.size() > 0) {
			for (BankCardDO bankCardPo : list) {
				listVo.add(view(bankCardPo));
			}
		}
		pageResult.setResultList(listVo);
		pageResult.setCurrentPage(pageEntity.getPage());
		pageResult.setPageSize(pageEntity.getSize());
		pageResult.setTotalSize(bankCardService.count(pageEntity.getParams()));
		return pageResult;
	}
	
	private BankCardBO view(BankCardDO bankCardDO){
		BankCardBO bankCardBO = new BankCardBO(bankCardDO);
		
		String color = "";
		String logo = "";
		BankBO bankBO = bankFacade.getBankBOByPK(bankCardDO.getBankId());
		if(bankBO!=null){
			color = bankBO.getColor();
			logo = bankBO.getBankImgUrl();
		}
		bankCardBO.setColor(color);
		bankCardBO.setImgLogo(logo);
		return bankCardBO;
	}

	@Override
	public int countBankCard(Map<String, Object> param) {
		try {
			return bankCardService.count(param);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
