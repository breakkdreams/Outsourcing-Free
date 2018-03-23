package com.zd.aoding.outsourcing.weChat.provider.facade.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.common.StringDate.StringUtil;
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.PushBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.PushDO;
import com.zd.aoding.outsourcing.weChat.api.facade.PushFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.PushService;



@Service
public class PushFacadeImpl implements PushFacade {
	@Autowired
	private PushService pushService;

	
	@Override
	public int addPush(PushDO pushPo) {
		try {
			pushPo.insertInit();
			return pushService.insert(pushPo);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public PageResult<PushBO> getPagePushVo(PageEntity pageEntity) {
		try {
			PageResult<PushBO> pageResult = new PageResult<PushBO>();
			List<PushDO> list = pushService.getPagination(pageEntity);
			List<PushBO> listVo = new ArrayList<>();
			if (list != null && list.size() > 0) {
				for (PushDO pushPo : list) {
					PushBO pushVo = view(pushPo);
					listVo.add(pushVo);
				}
			}
			pageResult.setResultList(listVo);
			pageResult.setCurrentPage(pageEntity.getPage());
			pageResult.setPageSize(pageEntity.getSize());
			pageResult.setTotalSize(pushService.count(pageEntity.getParams()));
			return pageResult;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public PushBO getPushVoByPk(Integer pushId) {
		try {
			PushDO pushPo = pushService.get(pushId);
			PushBO pushVo = new PushBO(pushPo);
			return pushVo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public int deletePush(Integer pushId) {
		try {
			PushDO pushPo = new PushDO();
			pushPo.setId(pushId);
			pushPo.setDeleted(1);
			return pushService.update(pushPo);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	private PushBO view(PushDO pushPo){
		PushBO pushVo = new PushBO(pushPo);
		String companyName = "";
		String appCode = pushPo.getApp();
		String[] appCodeArr = StringUtil.split(appCode, ",");
		pushVo.setCompanyName(companyName);
		return pushVo;
	}

}
