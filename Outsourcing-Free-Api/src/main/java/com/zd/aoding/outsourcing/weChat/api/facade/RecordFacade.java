package com.zd.aoding.outsourcing.weChat.api.facade;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.RecordBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.RecordPurseBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.RecordPursesDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.RecordsDO;

public interface RecordFacade {
	
	int insertRecordDO(RecordsDO recordDO);
	int insertRecordPurseDO(RecordPursesDO recordPurseDO);
	/**
	 * @Title: getPagePushVo
	 * @Description: 分页查询
	 * @param pageEntity
	 * @return
	 * @return: PageResult<PushVo>
	 */
	PageResult<RecordBO> getPageRecordVo(PageEntity pageEntity);
	
	PageResult<RecordPurseBO> getPageRecordPurseVo(PageEntity pageEntity);
}
