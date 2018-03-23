package com.zd.aoding.outsourcing.weChat.api.facade;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.PostageBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.PostageDO;

import java.util.List;
import java.util.Map;

public interface PostageFacade {
	
	int insertPostageDO(PostageDO postageDO);

	PostageDO getPostageDOByPK(Integer postageId);
	PostageBO getPostaBOByPK(Integer postageId);
	
	int updatePostageDO(PostageDO postageDO);
	
	PageResult<PostageBO> getPagePostageBO(PageEntity pageEntity);
	
	int countPostage(Map<String, Object> param);

	List<PostageDO> getAllPostageList(Map<String, Object> param);
}
