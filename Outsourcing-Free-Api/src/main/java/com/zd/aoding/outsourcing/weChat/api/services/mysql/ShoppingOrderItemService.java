package com.zd.aoding.outsourcing.weChat.api.services.mysql;

import java.util.List;

import com.zd.aoding.base.DAO.BaseServise;
import com.zd.aoding.base.adapter.SQLAdapter;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ShoppingOrderItemDO;

public interface ShoppingOrderItemService extends BaseServise<ShoppingOrderItemDO> {
	List<ShoppingOrderItemDO> getBySQLAdapter(SQLAdapter adapter);
}
