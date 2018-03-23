package com.zd.aoding.outsourcing.weChat.api.facade;


import java.util.Map;

import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.AboutOurBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.AboutOurDO;

public interface AboutOurFacade {
    
    AboutOurDO getAboutOurByParam(Map<String, Object> map);
    int insertAboutOurByParam(AboutOurDO aboutOurPo);
    int updateAboutOurByParam(AboutOurDO aboutOurPo);
    AboutOurBO getAboutOurByAppCode(String appCode);
}
