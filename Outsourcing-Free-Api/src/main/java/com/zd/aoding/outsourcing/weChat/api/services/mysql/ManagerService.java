package com.zd.aoding.outsourcing.weChat.api.services.mysql;

import com.zd.aoding.base.DAO.BaseServise;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ManagerDO;

public interface ManagerService extends BaseServise<ManagerDO> {

    int delete(Integer id);
}
