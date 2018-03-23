package com.zd.aoding.outsourcing.weChat.api.services.mysql;

import com.zd.aoding.base.adapter.SQLAdapter;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.DistributorMenuDO;

import java.util.List;

public interface DistributorMenuService extends BaseServise<DistributorMenuDO> {
    List<DistributorMenuDO> getBySQLAdapter(SQLAdapter adapter);
}
