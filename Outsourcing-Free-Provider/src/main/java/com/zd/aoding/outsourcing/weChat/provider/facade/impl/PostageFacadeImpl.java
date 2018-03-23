package com.zd.aoding.outsourcing.weChat.provider.facade.impl;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.PostageBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.PostageDO;
import com.zd.aoding.outsourcing.weChat.api.facade.PostageFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.PostageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class PostageFacadeImpl implements PostageFacade {
	@Autowired
	private PostageService postageService;

	@Override
	public int insertPostageDO(PostageDO postageDO) {
		postageDO.insertInit();
		return postageService.insert(postageDO);
	}

	@Override
	public PostageDO getPostageDOByPK(Integer postageId) {
		Map<String, Object> param = new HashMap<>();
		param.put("id", postageId);
		param.put("deleted", 0);
		PostageDO postageDO = postageService.get(postageId);
		if (postageDO != null) {
			return postageDO;
		}
		return null;
	}
	@Override
	public PostageBO getPostaBOByPK(Integer postageId) {
		Map<String, Object> param = new HashMap<>();
		param.put("id", postageId);
		param.put("deleted", 0);
		PostageDO postageDO = postageService.get(postageId);
		if (postageDO != null) {
			PostageBO postageVo = new PostageBO(postageDO);
			return postageVo;
		}
		return null;
	}

	@Override
	public int updatePostageDO(PostageDO postageDO) {
		return postageService.update(postageDO);
	}

	@Override
	public PageResult<PostageBO> getPagePostageBO(PageEntity pageEntity) {
		PageResult<PostageBO> pageResult = new PageResult<PostageBO>();
		List<PostageDO> list = postageService.getPagination(pageEntity);
		List<PostageBO> listVo = new ArrayList<>();
		if (list != null && list.size() > 0) {
			for (PostageDO postagePo : list) {
				listVo.add(new PostageBO(postagePo));
			}
		}
		pageResult.setResultList(listVo);
		pageResult.setCurrentPage(pageEntity.getPage());
		pageResult.setPageSize(pageEntity.getSize());
		pageResult.setTotalSize(postageService.count(pageEntity.getParams()));
		return pageResult;
	}

	@Override
	public int countPostage(Map<String, Object> param) {
		try {
			return postageService.count(param);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<PostageDO> getAllPostageList(Map<String, Object> param) {
		List<PostageDO> list = postageService.getList(param);
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
}
