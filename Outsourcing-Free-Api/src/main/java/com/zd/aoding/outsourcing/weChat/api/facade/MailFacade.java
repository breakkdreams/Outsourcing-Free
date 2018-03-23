package com.zd.aoding.outsourcing.weChat.api.facade;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.MailBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.MailDO;

public interface MailFacade {
	/**
	 * @Title: getPageMailVo
	 * @Description: 分页查询
	 * @param pageEntity
	 * @return
	 * @return: PageResult<MailVo>
	 */
	PageResult<MailBO> getPageMailVo(PageEntity pageEntity);
	/**
	 * @Title: getMailVoByPk
	 * @Description: 根据id查询
	 * @param id
	 * @return
	 * @return: MailVo
	 */
	MailBO getMailVoByPk(Integer id);
	MailDO getMailPoByPk(Integer id);
	
	int addMail(MailDO mailPo);
	int updateMail(MailDO mailPo);
	int deleteMail(Integer mailId);

}
