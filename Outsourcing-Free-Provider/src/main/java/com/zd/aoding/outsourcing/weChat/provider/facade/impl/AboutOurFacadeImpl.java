package com.zd.aoding.outsourcing.weChat.provider.facade.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.common.log.LogUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.AboutOurBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.AboutOurDO;
import com.zd.aoding.outsourcing.weChat.api.facade.AboutOurFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.AboutOurService;

@Service
public class AboutOurFacadeImpl implements AboutOurFacade {

    @Autowired
    private AboutOurService aboutOurService;

    @Override
    public AboutOurDO getAboutOurByParam(Map<String, Object> map) {
        List<AboutOurDO> list = aboutOurService.getList(map);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public int insertAboutOurByParam(AboutOurDO aboutOurPo) {
        return aboutOurService.insert(aboutOurPo);
    }

    @Override
    public int updateAboutOurByParam(AboutOurDO aboutOurPo) {
        return aboutOurService.update(aboutOurPo);
    }
    @Override
	public AboutOurBO getAboutOurByAppCode(String appCode) {
		try {
			Map<String,Object> param = new HashMap<String, Object>();
//			param.put("appCode", appCode);
			param.put("deleted", 0);
			List<AboutOurDO> list = aboutOurService.getList(param);
			if(list != null && list.size() == 1){
				return new AboutOurBO(list.get(0));
			}
			if(list != null && list.size() > 1){
				LogUtil.dataError("appCode == "+appCode+",多个关于我们", getClass());
				return new AboutOurBO(list.get(0));
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
    
}
