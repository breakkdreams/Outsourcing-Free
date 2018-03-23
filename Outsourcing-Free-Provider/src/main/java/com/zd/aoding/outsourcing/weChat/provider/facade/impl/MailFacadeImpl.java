package com.zd.aoding.outsourcing.weChat.provider.facade.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.MailBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.MailDO;
import com.zd.aoding.outsourcing.weChat.api.facade.MailFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.MailService;



@Service
public class MailFacadeImpl implements MailFacade {
	@Autowired
	private MailService mailService;

	
	@Override
	public int addMail(MailDO mailPo) {
		try {
			mailPo.insertInit();
			return mailService.insert(mailPo);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public PageResult<MailBO> getPageMailVo(PageEntity pageEntity) {
		try {
			PageResult<MailBO> pageResult = new PageResult<MailBO>();
			List<MailDO> list = mailService.getPagination(pageEntity);
			List<MailBO> listVo = new ArrayList<>();
			if (list != null && list.size() > 0) {
				for (MailDO mailPo : list) {
					MailBO mailVo = new MailBO(mailPo);
					listVo.add(mailVo);
				}
			}
			pageResult.setResultList(listVo);
			pageResult.setCurrentPage(pageEntity.getPage());
			pageResult.setPageSize(pageEntity.getSize());
			pageResult.setTotalSize(mailService.count(pageEntity.getParams()));
			return pageResult;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public MailBO getMailVoByPk(Integer mailId) {
		try {
			MailDO mailPo = mailService.get(mailId);
			if(mailPo != null){
				MailBO mailVo = new MailBO(mailPo);
				return mailVo;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public MailDO getMailPoByPk(Integer mailId) {
		try {
			MailDO mailPo = mailService.get(mailId);
			return mailPo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public int deleteMail(Integer mailId) {
		try {
			MailDO mailPo = new MailDO();
			mailPo.setId(mailId);
			mailPo.setDeleted(1);
			return mailService.update(mailPo);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int updateMail(MailDO mailPo) {
		try {
			return mailService.update(mailPo);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	

}
