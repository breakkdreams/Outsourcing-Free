package com.zd.aoding.outsourcing.weChat.provider.facade.impl;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.BankBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.ChargeParamBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.BankDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ChargeParamDO;
import com.zd.aoding.outsourcing.weChat.api.facade.ChargeParamFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.ChargeParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ChargeParamFacadeImpl implements ChargeParamFacade {
	@Autowired
	private ChargeParamService chargeParamService;

	@Override
	public int insertChargeParamDO(ChargeParamDO chargeParamDO) {
		chargeParamDO.insertInit();
		return chargeParamService.insert(chargeParamDO);
	}

	@Override
	public ChargeParamDO getChargeParamDOByPK(Integer chargeParamId) {
		Map<String, Object> param = new HashMap<>();
		param.put("id", chargeParamId);
		param.put("deleted", 0);
		ChargeParamDO chargeParamDO = chargeParamService.get(chargeParamId);
		if (chargeParamDO != null) {
			return chargeParamDO;
		}
		return null;
	}
	@Override
	public ChargeParamBO getChargeParamBOByPK(Integer chargeParamId) {
		Map<String, Object> param = new HashMap<>();
		param.put("id", chargeParamId);
		param.put("deleted", 0);
		ChargeParamDO chargeParamDO = chargeParamService.get(chargeParamId);
		if (chargeParamDO != null) {
			ChargeParamBO chargeParamVo = new ChargeParamBO(chargeParamDO);
			return chargeParamVo;
		}
		return null;
	}

	@Override
	public int updateChargeParamDO(ChargeParamDO chargeParamDO) {
		return chargeParamService.update(chargeParamDO);
	}

	@Override
	public PageResult<ChargeParamBO> getPageChargeParamBO(PageEntity pageEntity) {
		PageResult<ChargeParamBO> pageResult = new PageResult<ChargeParamBO>();
		List<ChargeParamDO> list = chargeParamService.getPagination(pageEntity);
		List<ChargeParamBO> listVo = new ArrayList<>();
		if (list != null && list.size() > 0) {
			for (ChargeParamDO chargeParamPo : list) {
				listVo.add(new ChargeParamBO(chargeParamPo));
			}
		}
		pageResult.setResultList(listVo);
		pageResult.setCurrentPage(pageEntity.getPage());
		pageResult.setPageSize(pageEntity.getSize());
		pageResult.setTotalSize(chargeParamService.count(pageEntity.getParams()));
		return pageResult;
	}

	@Override
	public int countChargeParam(Map<String, Object> param) {
		try {
			return chargeParamService.count(param);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<ChargeParamDO> getChargeParamDOList(Map<String, Object> param) {
		List<ChargeParamDO> list = chargeParamService.getList(param);
		if(list != null){
			return list;
		}
		return null;
	}
}
