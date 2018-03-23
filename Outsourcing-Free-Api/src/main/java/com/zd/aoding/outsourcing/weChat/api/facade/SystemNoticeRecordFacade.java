package com.zd.aoding.outsourcing.weChat.api.facade;

import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.SystemNoticeRecordDO;

public interface SystemNoticeRecordFacade {
	/**
	 * 插入系统通知阅读记录
	 * @param SystemNoticeRecordPo 系统通知阅读记录
	 * @return
	 */
	int insertSystemNoticeRecord(SystemNoticeRecordDO SystemNoticeRecordPo);
	
	/**
	 * 插入系统通知阅读记录
	 * @param SystemNoticeRecordPo 系统通知阅读记录
	 * @return
	 */
	int countSystemNoticeRecord(Integer userId, Integer noticeId);
	
	
	
}
