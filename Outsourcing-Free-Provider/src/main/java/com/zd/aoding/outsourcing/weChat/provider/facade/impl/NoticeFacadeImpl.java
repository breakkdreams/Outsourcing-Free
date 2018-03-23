package com.zd.aoding.outsourcing.weChat.provider.facade.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.NoticeBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.NoticeDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.SystemNoticeRecordDO;
import com.zd.aoding.outsourcing.weChat.api.facade.NoticeFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SystemNoticeRecordFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.NoticeService;

@Service
public class NoticeFacadeImpl implements NoticeFacade {
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private SystemNoticeRecordFacade systemNoticeRecordFacade;

	@Override
	public int insertNoticePo(NoticeDO noticePo) {
		noticePo.insertInit();
		return noticeService.insert(noticePo);
	}
	@Override
	public PageResult<NoticeBO> getPageNoticeVo(PageEntity pageEntity) {
		try {
			PageResult<NoticeBO> pageResult = new PageResult<NoticeBO>();
			List<NoticeDO> list = noticeService.getPagination(pageEntity);
			List<NoticeBO> listVo = new ArrayList<>();
			if (list != null && list.size() > 0) {
				for (NoticeDO noticePo : list) {
					NoticeBO noticeVo = view(noticePo);
					listVo.add(noticeVo);
				}
			}
			pageResult.setResultList(listVo);
			pageResult.setCurrentPage(pageEntity.getPage());
			pageResult.setPageSize(pageEntity.getSize());
			pageResult.setTotalSize(noticeService.count(pageEntity.getParams()));
			return pageResult;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private NoticeBO view(NoticeDO noticePo){
		NoticeBO noticeVo = new NoticeBO(noticePo);
		String companyName = "";
		String appCode = noticePo.getAppCode();
		noticeVo.setCompanyName(companyName);
		return noticeVo;
	}
	
	@Override
	public int countNewsNotice(Map<String, Object> param) {
		try {
			return noticeService.count(param);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public List<NoticeBO> getList(Map<String, Object> param) {
		List<NoticeDO> list = noticeService.getList(param);
		List<NoticeBO> listVo = new ArrayList<NoticeBO>();
		if(list != null && list.size() > 0){
			for(NoticeDO noticePo : list){
				listVo.add(new NoticeBO(noticePo));
			}
		}
		return listVo;
	}
	
	@Override
	public NoticeBO getNoticeVoById(int noticeId) {
		Map<String, Object> param = new HashMap<>();
		param.put("id", noticeId);
		param.put("deleted", 0);
		NoticeDO noticePo = noticeService.get(noticeId);
		if (noticePo != null) {
			return new NoticeBO(noticePo);
		}
		return null;
	}
	
	@Override
	public int updateNoticePo(NoticeDO noticePo) {
		return noticeService.update(noticePo);
	}

    @Override
    public int readAllNotice(Map<String, Object> param) {
        try {
            List<NoticeDO> list = noticeService.getList(param);
            for(NoticeDO noticePo : list){
                noticePo.setStatus(NoticeDO.status_read);
                updateNoticePo(noticePo);
            }
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        
    }

	@Override
	public int getNoReadNoticeNum(Integer userId, String appCode, Date userCreateTime) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("deleted", 0);
//		param.put("appCode", appCode);
		param.put("type", NoticeDO.type_system);
		param.put("userTime", userCreateTime);
		List<NoticeDO> list = noticeService.getList(param);
		if(list != null && list.size() > 0){
			int countNum = 0;
			for(int i = 0; i < list.size(); i++){
				NoticeDO noticePo = list.get(i);
				int num = systemNoticeRecordFacade.countSystemNoticeRecord(userId, noticePo.getId());
				if(num == 0){
					countNum += 1;
				}
			}
			return countNum;
		}
		return 0;
	}

	@Override
	public int readSystemNotice(Integer userId, String appCode,
			Date userCreateTime) {
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("deleted", 0);
			//param.put("appCode", appCode);
			param.put("type", NoticeDO.type_system);
			param.put("userTime", userCreateTime);
			List<NoticeDO> list = noticeService.getList(param);
			if(list != null && list.size() > 0){
				for(int i = 0; i < list.size(); i++){
					NoticeDO noticePo = list.get(i);
					int num = systemNoticeRecordFacade.countSystemNoticeRecord(userId, noticePo.getId());
					if(num == 0){
						SystemNoticeRecordDO systemNoticeRecordPo = new SystemNoticeRecordDO(userId, appCode, noticePo.getId());
						systemNoticeRecordFacade.insertSystemNoticeRecord(systemNoticeRecordPo);
					}
				}
				return 1;
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
}
