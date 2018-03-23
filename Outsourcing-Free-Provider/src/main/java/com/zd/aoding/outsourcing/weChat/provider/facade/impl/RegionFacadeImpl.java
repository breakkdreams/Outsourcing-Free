package com.zd.aoding.outsourcing.weChat.provider.facade.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.common.log.LogUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.RegionDO;
import com.zd.aoding.outsourcing.weChat.api.facade.RegionFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.RegionService;

@Service
public class RegionFacadeImpl implements RegionFacade {
	@Autowired
	private RegionService regionService;

	@Override
	public List<RegionDO> getRegionByParentId(Integer parentId) {
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("parentId", parentId);
			param.put("orderColumn", "region_id");
			List<RegionDO> list = regionService.getList(param);
			return list;
		} catch (Exception e) {
			LogUtil.operationError("通过parentId查询失败", this.getClass(), e);
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public RegionDO getRegionByRegionId(Integer RegionId) {
		try {
			return regionService.get(RegionId);
		} catch (Exception e) {
			LogUtil.operationError("通过id查询省市区失败", this.getClass(), e);
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public Boolean getRegionJoinBoolean(Integer provinceId, Integer cityId, Integer districtId) {
		RegionDO cityMapper = regionService.get(cityId);
		if (cityMapper.getParentId().equals(provinceId)) {
			RegionDO districtMapper = regionService.get(districtId);
			if (districtMapper.getParentId().equals(cityMapper.getRegionId())) {
				return true;
			}
		}
		return false;
	}
	@Override
	public String getProvinceCityDistrictByPK(Integer provinceId, Integer cityId, Integer districtId) {
		RegionDO province = regionService.get(provinceId);
		RegionDO city = regionService.get(cityId);
		RegionDO district = regionService.get(districtId);
		if (province != null && city != null && district != null) {
			return province.getRegionName() + city.getRegionName() + district.getRegionName();
		}
		return null;
	}
	@Override
	public RegionDO getProvinceByNameLike(String provinceName) {
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("regionType", 1);
			param.put("provinceName", provinceName);
			List<RegionDO> list = regionService.getList(param);
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public RegionDO getRegionListByTypeAndName(Integer type, String name) {
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("regionType", type+1);
			param.put("regionName", name);
			List<RegionDO> list = regionService.getList(param);
			if (list != null && list.size() == 1) {
				return list.get(0);
			}
			if (list != null && list.size() > 1) {
				LogUtil.dataError("直辖市重复", getClass());
				return list.get(0);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
