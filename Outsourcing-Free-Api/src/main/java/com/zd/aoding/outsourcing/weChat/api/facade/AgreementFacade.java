package com.zd.aoding.outsourcing.weChat.api.facade;

import java.util.Map;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.AgreementBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.AgreementDO;


public interface AgreementFacade {

	/**
	 * @Title: insertAgreementPo 
	 * @Description: 添加agreement
	 * @param AgreementPo
	 * @return
	 * @return: int
	 */
	int insertAgreementPo(AgreementDO agreementPo);
	
	/**
	 * @Title: getAgreementPoByPK 
	 * @Description: 根据主键查询banner
	 * @param bannerId
	 * @return
	 * @return: AgreementPo
	 */
	AgreementDO getAgreementPoByPK(String type);
	AgreementBO getAgreementVoByPK(Integer type);
	AgreementBO getAgreementVoById(Integer agreementId);
	
	/**
	 * @Title: updateAgreementPo 
	 * @Description: 修改agreement
	 * @param AgreementPo
	 * @return
	 * @return: int
	 */
	int updateAgreementPo(AgreementDO agreementPo);
	
	/**
	 * @Title: getPageAgreementVo
	 * @Description: TODO
	 * @param pageEntity
	 * @return
	 * @return: PageResult<AgreementVo>
	 */
	PageResult<AgreementBO> getPageAgreementVo(PageEntity pageEntity);
	
	/**
	 * @Title: countAgreement 
	 * @Description: 根据条件统计
	 * @param param
	 * @return
	 * @return: int
	 */
	int countAgreement(Map<String, Object> param);
}
