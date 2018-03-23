package com.zd.aoding.outsourcing.weChat.api.facade;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.common.response.ResponseCodeEnum;
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.VersionDO;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.VersionService;

/**
 * @ClassName: VersionFacade
 * @Description: 手机端 标识符
 * @author: HCD
 * @date: 2017年2月16日 上午8:20:47
 */
@Service
public class VersionFacade {
	@Autowired
	private VersionService versionService;

	public String fineVersion(HttpServletRequest request, String versionNum) {
		try {
		    
			Map<String, Object> param = new HashMap<String, Object>();
			if(versionNum.contains("=")){
                String[] viser = versionNum.split("=");
                param.put("deleted", 0);
                param.put("versionNum", viser[0]);
                List<VersionDO> version = versionService.getList(param);
                if(Integer.parseInt(viser[1]) >= Integer.parseInt(version.get(0).getVersionNum())){
                    return ResponseUtil.resultString("0", ResponseCodeEnum.OK);
                }else{
                    return ResponseUtil.resultString("1", ResponseCodeEnum.OK);
                }
            }
			param.put("deleted", 0);
			param.put("versionNum", versionNum);
			List<VersionDO> version = versionService.getList(param);
			if (version.size() <= 0 || version == null) {
				VersionDO VersionPo = new VersionDO();
				//第一次插入说明版本已经更新了,1不用更新;然后把以前版本的标识改成0
				VersionPo.setBiaoShi(1);
				VersionPo.setVersionNum(versionNum);
				VersionPo.setCreateTime(new Date());
				int ver = versionService.insert(VersionPo);
				if (ver == 1) {
					return ResponseUtil.resultString("1", ResponseCodeEnum.OK);
				} else {
					return ResponseUtil.resultString("版本查询失败!", ResponseCodeEnum.ERROR);
				}
			} else {
				return ResponseUtil.resultString(version.get(0).getBiaoShi(), ResponseCodeEnum.OK);
			}
		} catch (Exception e) {
			return ResponseUtil.systemErrorResultString();
		}
	}
	public PageResult<VersionDO> showVersion(PageEntity entity) {
		List<VersionDO> list = versionService.getPagination(entity);
		PageResult<VersionDO> result = new PageResult<VersionDO>();
		for (VersionDO mapper : list) {
			mapper.setCreateTime(mapper.getCreateTime());
		}
		result.setResultList(list);
		result.setCurrentPage(entity.getPage());
		result.setPageSize(entity.getSize());
		int totalSize = versionService.count(entity.getParams());
		result.setTotalSize(totalSize);
		return result;
	}
	/**
	 * -1未找到该版本号,1成功,2失败
	 * @param versionId
	 * @param biaoShi
	 * @return
	 */
	public int openAndCloseVersion(String versionId, String biaoShi) {
		int version = Integer.parseInt(versionId);
		int bs = Integer.parseInt(biaoShi);
		VersionDO mapper = versionService.get(version);
		if (mapper == null || mapper.getDeleted() == 1) {
			return -1;
		}
		mapper.setBiaoShi(bs);
		mapper.setUpdateTime(new Date());
		int i = versionService.update(mapper);
		if (i == 1) {
			return 1;
		} else {
			return 2;
		}
	}
}
