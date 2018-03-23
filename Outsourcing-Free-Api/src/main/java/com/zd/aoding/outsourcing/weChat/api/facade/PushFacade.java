package com.zd.aoding.outsourcing.weChat.api.facade;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.PushBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.PushDO;

public interface PushFacade {
	/**
	 * @Title: getPagePushVo
	 * @Description: 分页查询
	 * @param pageEntity
	 * @return
	 * @return: PageResult<PushVo>
	 */
	PageResult<PushBO> getPagePushVo(PageEntity pageEntity);
	/**
	 * @Title: getPushVoByPk
	 * @Description: 根据id查询
	 * @param id
	 * @return
	 * @return: PushVo
	 */
	PushBO getPushVoByPk(Integer id);
	/**
	 * @Title: addPush
	 * @Description: 根据id查询
	 * @param pushPo
	 * @return
	 * @return: int
	 */
	int addPush(PushDO pushPo);
	int deletePush(Integer pushId);

}
