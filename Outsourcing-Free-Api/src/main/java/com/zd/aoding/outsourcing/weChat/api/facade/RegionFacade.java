package com.zd.aoding.outsourcing.weChat.api.facade;

import java.util.List;

import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.RegionDO;

public interface RegionFacade {
	/**
	 * @Title: getRegionByParentId
	 * @Description: 根据上级id查询省市区
	 * @param parentId
	 * @return
	 * @return: List<RegionPo>
	 */
	List<RegionDO> getRegionByParentId(Integer parentId);
	/**
	 * @Title: getRegionByRegionId
	 * @Description:根据id查询省市区
	 * @param RegionId
	 * @return
	 * @return: RegionMapper
	 */
	RegionDO getRegionByRegionId(Integer RegionId);
	/**
	 * @Title: getRegionByRegionId
	 * @Description:查询省市区是否关联
	 * @param RegionId
	 * @return
	 * @return: RegionMapper
	 */
	Boolean getRegionJoinBoolean(Integer provinceId, Integer cityId, Integer districtId);
	/**
	 * @Title: getProvinceCityDistrictByPK
	 * @Description: 获得省市区全地址
	 * @param provinceId
	 * @param cityId
	 * @param districtId
	 * @return
	 * @return: String
	 */
	String getProvinceCityDistrictByPK(Integer provinceId, Integer cityId, Integer districtId);
	/**
	 * @Title: getProvinceByNameLike
	 * @Description: 模糊查询省
	 * @param provinceName
	 * @return
	 * @return: RegionPo
	 */
	RegionDO getProvinceByNameLike(String provinceName);
	/**
	 * @Title: getRegionListByTypeAndName
	 * @Description: 根据类型和名称查询省市区
	 * @param Integer type, String name
	 * @return
	 * @return: RegionPo
	 */
	RegionDO getRegionListByTypeAndName(Integer type, String name);
}
