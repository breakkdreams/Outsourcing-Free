package com.zd.aoding.outsourcing.web.controllerApi.app.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import com.zd.aoding.common.StringDate.StringUtil;
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.common.response.ResponseCodeEnum;
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.UserAddressBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.UserBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.UserAddressDO;
import com.zd.aoding.outsourcing.weChat.api.constant.SessionConstant;
import com.zd.aoding.outsourcing.weChat.api.facade.AddressFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SessionFacade;

@Api(value = "", description = "用户收货地址管理")
@Controller
@RequestMapping("user")
public class AddressUserController {
	@Autowired
	private SessionFacade sessionFacade;
	@Autowired
	private AddressFacade addressFacade;

	@ResponseBody
	@RequestMapping(value = "address/add", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "添加用户地址", httpMethod = "POST", notes = "添加地址", response = ResponseUtil.class)
	public String addAddress(
			@ApiParam(name = "isDefault", value = "是否默认地址，true：设为默认地址", required = false) @RequestParam(value = "isDefault", required = false) String isDefault,
			@ApiParam(name = "addressMapper", value = "地址", required = true) @ModelAttribute UserAddressDO addressPo, 
			HttpServletRequest request) {
		try {
			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
			if (sessionUser != null) {
				int a = addressFacade.countUserAddress(sessionUser.getUserId());
				if(a >= 10){
					return ResponseUtil.showMSGResultString("收货地址最多设置10个");
				}
				addressPo.setUserId(sessionUser.getUserId());
				addressPo.setIsDefault(0);
				if (StringUtil.isNumber(isDefault)) {
					if ("1".equals(isDefault)) {
						addressPo.setIsDefault(1);
					}
				}
				addressPo.insertInit();
				int j = addressFacade.insertAddressPo(addressPo);
				if (j == 1) {
					return ResponseUtil.successResultString("添加成功");
				}
				return ResponseUtil.showMSGResultString("添加失败");
			} else {
				return ResponseUtil.notLoggedResultString();
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	@ResponseBody
	@RequestMapping(value = "address/edit", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "编辑修改用户地址", httpMethod = "POST", notes = "修改地址", response = ResponseUtil.class)
	public String editAddress(
			@ApiParam(name = "isDefault", value = "是否默认地址:1设为默认地址，0非默认", required = true) @RequestParam(value = "isDefault", required = true) String isDefault,
			@ApiParam(name = "addressId", value = "地址id", required = false) @RequestParam(value = "addressId", required = true) String addressId,
			@ApiParam(name = "地址", value = "密码", required = true) @ModelAttribute UserAddressDO addressPo, 
			HttpServletRequest request) {
		try {
			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
			if (sessionUser != null) {
				addressPo.setIsDefault(0);
				if (StringUtil.isNumber(isDefault)) {
					if ("1".equals(isDefault)) {
						addressPo.setIsDefault(1);
					}
				}
				if (!StringUtil.isNumber(addressId)) {
					return ResponseUtil.paramErrorResultString("参数错误");
				}
				addressPo.setId(Integer.parseInt(addressId));
				UserAddressBO addressVo = addressFacade.getAddressVoByPK(addressId);
				if (addressVo == null) {
					return ResponseUtil.paramErrorResultString("参数错误");
				}
				if (!addressVo.getUserId().equals(sessionUser.getUserId())) {
					return ResponseUtil.showMSGResultString("非用户地址");
				}
				addressPo.setUserId(sessionUser.getUserId());
				int j = addressFacade.updateAddressPo(addressPo);
				System.out.println(addressPo);
				System.out.println(j);
				if (j == 1) {
					return ResponseUtil.successResultString("修改成功");
				}
				return ResponseUtil.showMSGResultString("修改失败");
			} else {
				return ResponseUtil.notLoggedResultString();
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	/**
	 * @Title: deletedAddress
	 * @Description: 删除地址
	 * @param addressId
	 * @param request
	 * @return
	 * @return: String
	 */
	@ResponseBody
	@RequestMapping(value = "address/delete", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "删除用户地址", httpMethod = "POST", notes = "根据地址id删除地址", response = ResponseUtil.class)
	public String deletedAddress(
			@ApiParam(required = true, name = "addressId", value = "地址Id") @RequestParam(value = "addressId", required = true) String addressId,
			HttpServletRequest request) {
		try {
			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
			if (sessionUser != null) {
				if (!StringUtil.isNULL(addressId) && StringUtil.isNumber(addressId)) {
					UserAddressBO addressVo = addressFacade.getAddressVoByPK(addressId);
					if (addressVo == null) {
						return ResponseUtil.paramErrorResultString("参数错误");
					}
					if (!addressVo.getUserId().equals(sessionUser.getUserId())) {
						return ResponseUtil.showMSGResultString("非用户地址");
					}
					UserAddressDO updateAddressPo = new UserAddressDO();
					updateAddressPo.setId(addressVo.getAddressId());
					updateAddressPo.setDeleted(1);
					int i = addressFacade.updateAddressPo(updateAddressPo);
					if (i == 1) {
						return ResponseUtil.successResultString("删除成功");
					}
					return ResponseUtil.showMSGResultString("删除失败");
				}
				return ResponseUtil.paramErrorResultString("缺少需要删除的地址addressId=" + addressId);
			} else {
				return ResponseUtil.notLoggedResultString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	@ResponseBody
	@RequestMapping(value = "address/default", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "设置默认地址", httpMethod = "POST", notes = "根据地址id设置默认地址", response = ResponseUtil.class)
	public String setDefault(
			@ApiParam(required = true, name = "addressId", value = "地址Id") @RequestParam(value = "addressId", required = true) String addressId,
			HttpServletRequest request) {
		try {
			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
			if (sessionUser != null) {
				if (!StringUtil.isNULL(addressId) && StringUtil.isNumber(addressId)) {
					UserAddressBO addressVo = addressFacade.getAddressVoByPK(addressId);
					if (addressVo == null) {
						return ResponseUtil.paramErrorResultString("参数错误");
					}
					if (addressVo.getIsDefault() != null && addressVo.getIsDefault() == 1) {
						return ResponseUtil.showMSGResultString("已是默认地址");
					}
					if (!addressVo.getUserId().equals(sessionUser.getUserId())) {
						return ResponseUtil.showMSGResultString("非用户地址");
					}
					UserAddressDO updateAddressPo = new UserAddressDO();
					updateAddressPo.setId(addressVo.getAddressId());
					updateAddressPo.setIsDefault(1);
					updateAddressPo.setUserId(sessionUser.getUserId());
					int i = addressFacade.updateAddressPo(updateAddressPo);
					if (i == 1) {
						return ResponseUtil.successResultString("设置成功");
					}
					return ResponseUtil.showMSGResultString("删除失败");
				}
				return ResponseUtil.paramErrorResultString("地址id为空或格式不正确");
			} else {
				return ResponseUtil.notLoggedResultString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	/**
	 * @Title: getAddress
	 * @Description: 查看收货地址
	 * @param accountId
	 * @param request
	 * @return
	 * @return: String
	 */
	@ResponseBody
	@RequestMapping(value = "address/getPage", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "分页查询收货地址", httpMethod = "POST", notes = "查看收货地址", response = ResponseUtil.class)
	public String getAddress(
			@ApiParam(required = false, name = "pageNum", value = "当前页数（即当前第几页）") @RequestParam(value = "pageNum", required = false) String pageNum,
			@ApiParam(required = false, name = "pageSize", value = "分页条数") @RequestParam(value = "pageSize", required = false) String pageSize,
			HttpServletRequest request) {
		try {
			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
			if (sessionUser != null) {
				// 初始化列表
				PageEntity pageEntity = new PageEntity();
				if (StringUtil.isNumber(pageNum)) {
					pageEntity.setPage(Integer.parseInt(pageNum));
				}
				if (StringUtil.isNumber(pageSize)) {
					pageEntity.setSize(Integer.parseInt(pageSize));
				}
				/**
				 * 排序
				 */
				pageEntity.setOrderColumn("is_default");
				pageEntity.setOrderTurn("desc");
				/**
				 * 查询条件参数
				 */
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("userId", sessionUser.getUserId());
				param.put("deleted", 0);
				pageEntity.setParams(param);
				/**
				 * 查询
				 */
				PageResult<UserAddressBO> pageResult = addressFacade.getPageAddressVo(pageEntity);
				return ResponseUtil.successResultString(pageResult);
			}
			return ResponseUtil.notLoggedResultString();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	@ResponseBody
	@RequestMapping(value = "address/getPageAndDefault", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "查询默认地址和分页查询其他收货地址", httpMethod = "POST", notes = "查询默认地址和分页查询其他收货地址", response = ResponseUtil.class)
	public String getAddressAndDefault(
			@ApiParam(required = false, name = "pageNum", value = "当前页数（即当前第几页）") @RequestParam(value = "pageNum", required = false) String pageNum,
			@ApiParam(required = false, name = "pageSize", value = "分页条数") @RequestParam(value = "pageSize", required = false) String pageSize,
			HttpServletRequest request) {
		try {
			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
			if (sessionUser != null) {
				// 初始化列表
				PageEntity pageEntity = new PageEntity();
				if (StringUtil.isNumber(pageNum)) {
					pageEntity.setPage(Integer.parseInt(pageNum));
				}
				if (StringUtil.isNumber(pageSize)) {
					pageEntity.setSize(Integer.parseInt(pageSize));
				}
				/**
				 * 排序
				 */
				pageEntity.setOrderColumn("update_time");
				/**
				 * 查询条件参数
				 */
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("userId", sessionUser.getUserId());
				param.put("deleted", 0);
				param.put("isDefault", 0);
				pageEntity.setParams(param);
				/**
				 * 查询
				 */
				PageResult<UserAddressBO> pageResult = addressFacade.getPageAddressVo(pageEntity);
				UserAddressBO defaultAddress = addressFacade.getDefaultAddressVo(sessionUser.getUserId());
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("defaultAddress", defaultAddress);
				map.put("pageResult", pageResult);
				return ResponseUtil.successResultString(map);
			}
			return ResponseUtil.notLoggedResultString();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	@ResponseBody
	@RequestMapping(value = "address/detail", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "查看单个收货地址", httpMethod = "POST", notes = "登陆后根据地址id查看单个收货地址", response = ResponseUtil.class)
	@ApiResponses({ @ApiResponse(code = 1, message = "地址实体类注释", response = UserAddressBO.class) })
	public String getAddressMapper(
			@ApiParam(required = false, name = "addressId", value = "地址id") @RequestParam(value = "addressId", required = false) String addressId,
			HttpServletRequest request) {
		try {
			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
			if (sessionUser != null) {
				if (!StringUtil.isNULL(addressId) && StringUtil.isNumber(addressId)) {
					UserAddressBO addressVo = addressFacade.getAddressVoByPK(addressId);
					if (addressVo == null) {
						return ResponseUtil.paramErrorResultString("参数错误");
					}
					if (addressVo.getUserId().equals(sessionUser.getUserId())) {
						return ResponseUtil.successResultString(addressVo);
					}
					return ResponseUtil.showMSGResultString("非用户地址");
				}
				return ResponseUtil.paramErrorResultString("地址id为空或格式不正确");
			}
			return ResponseUtil.notLoggedResultString();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	@ResponseBody
	@RequestMapping(value = "address/getDefault", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "查看默认收货地址", httpMethod = "POST", notes = "查看默认收货地址", response = ResponseUtil.class)
	public String getDefaultAddress(
			HttpServletRequest request) {
		try {
			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
			if (sessionUser != null) {
				UserAddressBO addressVo = addressFacade.getDefaultAddressVo(sessionUser.getUserId());
				if (addressVo != null) {
					return ResponseUtil.successResultString(addressVo);
				}
				return ResponseUtil.showMSGResultString("没有默认地址");
			}
			return ResponseUtil.notLoggedResultString();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
}
