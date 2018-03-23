package com.zd.aoding.outsourcing.weChat.provider.facade.impl;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.SystemNoticeRecordDO;
import com.zd.aoding.outsourcing.weChat.api.facade.SystemNoticeRecordFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.SystemNoticeRecordService;

@Service
public class SysytemNoticeRecordFacadeImpl implements SystemNoticeRecordFacade {
	@Autowired
	private SystemNoticeRecordService systemNoticeRecordService;

	@Override
	public int insertSystemNoticeRecord(
			SystemNoticeRecordDO SystemNoticeRecordPo) {
		return systemNoticeRecordService.insert(SystemNoticeRecordPo);
	}

	@Override
	public int countSystemNoticeRecord(Integer userId, Integer noticeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("noticeId", noticeId);
		return systemNoticeRecordService.count(map);
	}

}
