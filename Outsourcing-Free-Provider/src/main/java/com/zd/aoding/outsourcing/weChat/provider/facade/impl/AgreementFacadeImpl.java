package com.zd.aoding.outsourcing.weChat.provider.facade.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.AgreementBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.AgreementDO;
import com.zd.aoding.outsourcing.weChat.api.facade.AgreementFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.AgreementService;



@Service
public class AgreementFacadeImpl implements AgreementFacade {
	@Autowired
	private AgreementService agreementService;

	@Override
	public int insertAgreementPo(AgreementDO agreementPo) {
		agreementPo.insertInit();
		return agreementService.insert(agreementPo);
	}

	@Override
	public AgreementDO getAgreementPoByPK(String type) {
		Map<String, Object> param = new HashMap<>();
		param.put("type", type);
		param.put("deleted", 0);
		AgreementDO agreementPo = agreementService.get(Integer.parseInt(type));
		if (agreementPo != null) {
			return agreementPo;
		}
		return null;
	}

	@Override
	public int updateAgreementPo(AgreementDO agreementPo) {
		return agreementService.update(agreementPo);
	}

	@Override
	public PageResult<AgreementBO> getPageAgreementVo(PageEntity pageEntity) {
		PageResult<AgreementBO> pageResult = new PageResult<AgreementBO>();
		List<AgreementDO> list = agreementService.getPagination(pageEntity);
		List<AgreementBO> listVo = new ArrayList<>();
		if (list != null && list.size() > 0) {
			for (AgreementDO agreementPo : list) {
				listVo.add(new AgreementBO(agreementPo));
			}
		}
		pageResult.setResultList(listVo);
		pageResult.setCurrentPage(pageEntity.getPage());
		pageResult.setPageSize(pageEntity.getSize());
		pageResult.setTotalSize(agreementService.count(pageEntity.getParams()));
		return pageResult;
	}

	@Override
	public int countAgreement(Map<String, Object> param) {
		try {
			return agreementService.count(param);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public AgreementBO getAgreementVoByPK(Integer type) {
		Map<String, Object> param = new HashMap<>();
		param.put("type", type);
		param.put("deleted", 0);
		 List<AgreementDO> list = agreementService.getList(param);
		if (list != null && list.size()>0) {
			AgreementDO agreementPo = list.get(0);
			AgreementBO agreementVo = new AgreementBO(agreementPo);
			return agreementVo;
		}
		return null;
	}
	
	@Override
	public AgreementBO getAgreementVoById(Integer agreementId) {
		AgreementDO agreementDO = agreementService.get(agreementId);
		if (agreementDO != null) {
			return new AgreementBO(agreementDO);
		}
		return null;
	}

}
