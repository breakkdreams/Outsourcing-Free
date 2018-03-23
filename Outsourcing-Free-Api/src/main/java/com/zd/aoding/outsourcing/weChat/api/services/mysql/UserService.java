package com.zd.aoding.outsourcing.weChat.api.services.mysql;

import java.util.List;
import java.util.Map;

import com.zd.aoding.base.DAO.BaseServise;
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.UserDO;

public interface UserService extends BaseServise<UserDO> {
	/**
	 * @Title: delete
	 * @Description: 逻辑删除
	 * @param id
	 * @return
	 * @return: int
	 */
	int delete(Integer id);
	
	int creatTable(String tableName);

	UserDO getPoByTableName(Integer id, String tableName);
	
	List<UserDO> getPaginationByTableName(PageEntity pageEntity, String tableName);
	List<UserDO> getListByTableName(Map<String, Object> param, String tableName);
}
