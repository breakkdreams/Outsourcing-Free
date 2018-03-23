package com.zd.aoding.outsourcing.weChat.api.services.mysql;

import java.util.List;

import com.zd.aoding.base.adapter.SQLAdapter;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.MenuAdminDO;

public interface MenuAdminService extends BaseServise<MenuAdminDO> {
	List<MenuAdminDO> getBySQLAdapter(SQLAdapter adapter);
}
