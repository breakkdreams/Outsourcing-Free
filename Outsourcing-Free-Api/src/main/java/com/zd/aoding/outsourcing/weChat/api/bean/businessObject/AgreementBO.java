package com.zd.aoding.outsourcing.weChat.api.bean.businessObject;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.AgreementDO;

@ApiModel(value = "", description = "agreement类")
public class AgreementBO {
	@ApiModelProperty("agreementId")
	private Integer agreementId;
	@ApiModelProperty("agreementPo信息")
	private AgreementDO agreementPo;

	public AgreementBO(AgreementDO agreementPo) {
		this.agreementId = agreementPo.getId();
		this.agreementPo = agreementPo;
	}

	public Integer getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(Integer agreementId) {
		this.agreementId = agreementId;
	}

	public AgreementDO getAgreementPo() {
		return agreementPo;
	}

	public void setAgreementPo(AgreementDO agreementPo) {
		this.agreementPo = agreementPo;
	}
}
