package com.zd.aoding.outsourcing.weChat.provider.facade.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.common.log.LogUtil;
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.UserAddressBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.RegionDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.UserAddressDO;
import com.zd.aoding.outsourcing.weChat.api.facade.AddressFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.RegionFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.UserFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.UserAddressService;

@Service
public class AddressFacadeImpl implements AddressFacade {
	@Autowired
	private UserAddressService userAddressService;
	@Autowired
	private UserFacade userFacade;
	@Autowired
	private RegionFacade regionFacade;

	@Override
	public int insertAddressPo(UserAddressDO addressPo) {
		if (addressPo.getIsDefault() == 1) {
			UserAddressBO UserAddressVo = getDefaultAddressVo(addressPo.getUserId());
			if (UserAddressVo != null) {
				UserAddressDO updateDo = new UserAddressDO();
				updateDo.setId(UserAddressVo.getAddressId());
				updateDo.setIsDefault(0);
				int i = updateAddressPo(updateDo);
			}
		}
		addressPo.insertInit();
		return userAddressService.insert(addressPo);
	}
	@Override
	public UserAddressBO getDefaultAddressVo(Integer userId) {
		Map<String, Object> params = new HashMap<>();
		params.put("userId", userId);
		params.put("isDefault", 1);
		params.put("deleted", 0);
		UserAddressDO addressPo = getOneAddressPoByParams(params);
		if (addressPo != null) {
			UserAddressBO userAddressVo = new UserAddressBO(addressPo);
			userAddressVo = view(userAddressVo);
			return userAddressVo;
		}
		return null;
	}
	private UserAddressDO getOneAddressPoByParams(Map<String, Object> params) {
		// TODO Auto-generated method stub
		List<UserAddressDO> listDo = userAddressService.getList(params);
		if (listDo != null) {
			if (listDo.size() == 1) {
				return listDo.get(0);
			} else if (listDo.size() > 1) {
				LogUtil.dataError(listDo + "默认地址多个", this.getClass());
//				for (int i = 1; i < listDo.size(); i++) {
//					UserAddressPo updateDo = new UserAddressPo();
//					updateDo.setId(listDo.get(i).getId());
//					updateDo.setIsDefault(0);
//					int j = updateAddressPo(updateDo);
//				}
				return listDo.get(0);
			} else {
				return null;
			}
		}
		return null;
	}
	@Override
	public int updateAddressPo(UserAddressDO addressPo) {
		if (addressPo.getIsDefault() != null && addressPo.getIsDefault() == 1) {
			UserAddressBO address = getDefaultAddressVo(addressPo.getUserId());
			if (address != null && address.getAddressId() != addressPo.getId()) {
				UserAddressDO updateAddressPo = new UserAddressDO();
				updateAddressPo.setId(address.getAddressId());
				updateAddressPo.setIsDefault(0);
				// 取消默认地址
				userAddressService.update(updateAddressPo);
			}
		}
		return userAddressService.update(addressPo);
	}
	@Override
	public UserAddressBO getAddressVoByPK(String addressId) {
		// TODO Auto-generated method stub
		UserAddressDO addressPo = userAddressService.get(Integer.parseInt(addressId));
		if (addressPo != null) {
			return view(new UserAddressBO(addressPo));
		}
		return null;
	}
	@Override
	public PageResult<UserAddressBO> getPageAddressVo(PageEntity pageEntity) {
		PageResult<UserAddressBO> pageResult = new PageResult<UserAddressBO>();
		List<UserAddressDO> list = userAddressService.getPagination(pageEntity);
		List<UserAddressBO> listVo = new ArrayList<>();
		if (list != null && list.size() > 0) {
			for (UserAddressDO userAddressPo : list) {
				UserAddressBO userAddressVo = new UserAddressBO(userAddressPo);
				userAddressVo = view(userAddressVo);
				listVo.add(userAddressVo);
			}
		}
		pageResult.setResultList(listVo);
		pageResult.setCurrentPage(pageEntity.getPage());
		pageResult.setPageSize(pageEntity.getSize());
		pageResult.setTotalSize(userAddressService.count(pageEntity.getParams()));
		return pageResult;
	}
	
	@Override
	public List<UserAddressBO> getAddressVoList(Integer userId) {
		try {
			Map<String, Object> param = new HashMap<>();
			param.put("deleted", 0);
			param.put("userId", userId);
			List<UserAddressDO> list = userAddressService.getList(param);
			List<UserAddressBO> listVo = new ArrayList<UserAddressBO>();
			if(list != null && list.size() > 0){
				for(UserAddressDO userAddressPo : list){
					UserAddressBO userAddressVo = new UserAddressBO(userAddressPo);
					userAddressVo = view(userAddressVo);
					listVo.add(userAddressVo);
				}
			}
			return listVo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public List<UserAddressBO> getAddressVoListByName(String name) {
		try {
			Map<String, Object> param = new HashMap<>();
			param.put("deleted", 0);
			param.put("consignee", name);
			List<UserAddressDO> list = userAddressService.getList(param);
			List<UserAddressBO> listVo = new ArrayList<UserAddressBO>();
			if(list != null && list.size() > 0){
				for(UserAddressDO userAddressPo : list){
					UserAddressBO userAddressVo = new UserAddressBO(userAddressPo);
					listVo.add(userAddressVo);
				}
			}
			return listVo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public UserAddressBO view(UserAddressBO userAddressVo){
		String provinceName = "";
		String cityName = "";
		String districtName = "";
		RegionDO province = regionFacade.getRegionByRegionId(userAddressVo.getProvinceId());
		RegionDO city = regionFacade.getRegionByRegionId(userAddressVo.getCityId());
		RegionDO district = regionFacade.getRegionByRegionId(userAddressVo.getDistrictId());
		if(province != null){
			provinceName = province.getRegionName();
		}
		if(city != null){
			cityName = city.getRegionName();
		}
		if(district != null){
			districtName = district.getRegionName();
		}
		userAddressVo.setProvinceName(provinceName);
		userAddressVo.setCityName(cityName);
		userAddressVo.setDistrictName(districtName);
		return userAddressVo;
	}
	
	@Override
	public int countUserAddress(Integer userId) {
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("deleted", 0);
			param.put("userId", userId);
			return userAddressService.count(param);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
}
