package com.zd.aoding.outsourcing.weChat.api.facade;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.NoticeBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.NoticeDO;

public interface NoticeFacade {
	/**
	 * 添加新闻资讯
	 * @param NoticePo
	 * @return
	 */
	int insertNoticePo(NoticeDO noticePo);
	
	int updateNoticePo(NoticeDO noticePo);
	
	NoticeBO getNoticeVoById(int noticeId);
	/**
	 * @Title: getPagePushVo
	 * @Description: 分页查询
	 * @param pageEntity
	 * @return
	 * @return: PageResult<PushVo>
	 */
	PageResult<NoticeBO> getPageNoticeVo(PageEntity pageEntity);
	
	/**
	 * 条数
	 * @param param
	 * @return
	 */
	int countNewsNotice(Map<String, Object> param);
	

	List<NoticeBO> getList(Map<String, Object> param);
	int readAllNotice(Map<String, Object> param);
	
	int getNoReadNoticeNum(Integer userId, String appCode, Date userCreateTime);
	int readSystemNotice(Integer userId, String appCode, Date userCreateTime);
}
