package com.zd.aoding.outsourcing.weChat.provider.facade.impl;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.RecordBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.RecordPurseBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.AccountDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.RecordPursesDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.RecordsDO;
import com.zd.aoding.outsourcing.weChat.api.facade.AccountFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.RecordFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.RecordPurseService;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.RecordService;

@Service
public class RecordFacadeImpl implements RecordFacade {
	@Autowired
	private RecordService recordService;
	@Autowired
	private RecordPurseService recordPurseService;
	@Autowired
	private AccountFacade accountFacade;

	@Override
	public int insertRecordDO(RecordsDO recordDO) {
		recordDO.insertInit();
		return recordService.insert(recordDO);
	}

	@Override
	public int insertRecordPurseDO(RecordPursesDO recordPurseDO) {
		recordPurseDO.insertInit();
		return recordPurseService.insert(recordPurseDO);
	}

	@Override
	public PageResult<RecordBO> getPageRecordVo(PageEntity pageEntity) {
		try {
			PageResult<RecordBO> pageResult = new PageResult<RecordBO>();
			List<RecordsDO> list = recordService.getPageRecord(pageEntity);
			List<RecordBO> listVo = new ArrayList<>();
			if (list != null && list.size() > 0) {
				for (RecordsDO recordPo : list) {
					RecordBO recordVo = view(recordPo);
					listVo.add(recordVo);
				}
			}
			pageResult.setResultList(listVo);
			pageResult.setCurrentPage(pageEntity.getPage());
			pageResult.setPageSize(pageEntity.getSize());
			pageResult.setTotalSize(recordService.countRecord(null, pageEntity.getParams()));
			return pageResult;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private RecordBO view(RecordsDO recordPo){
		RecordBO recordVo = new RecordBO(recordPo);
		String dealName = "";
		AccountDO accountPo = accountFacade.getPoByPK(recordPo.getDealId());
		if(accountPo != null){
			dealName = accountPo.getUsername();
		}
		recordVo.setDealName(dealName);
		return recordVo;
	}
	
	@Override
	public PageResult<RecordPurseBO> getPageRecordPurseVo(PageEntity pageEntity) {
		try {
			PageResult<RecordPurseBO> pageResult = new PageResult<RecordPurseBO>();
			List<RecordPursesDO> list = recordPurseService.getPagination(pageEntity);
			List<RecordPurseBO> listVo = new ArrayList<>();
			if (list != null && list.size() > 0) {
				for (RecordPursesDO recordPo : list) {
					RecordPurseBO recordVo = new RecordPurseBO(recordPo);
					listVo.add(recordVo);
				}
			}
			pageResult.setResultList(listVo);
			pageResult.setCurrentPage(pageEntity.getPage());
			pageResult.setPageSize(pageEntity.getSize());
			pageResult.setTotalSize(recordPurseService.count(pageEntity.getParams()));
			return pageResult;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
