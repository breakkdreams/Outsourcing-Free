package com.zd.aoding.outsourcing.web.controllerApi.app.user;

import java.text.SimpleDateFormat;

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
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import com.zd.aoding.common.StringDate.StringUtil;
import com.zd.aoding.common.response.ResponseCodeEnum;
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.AccountBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.UserBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.AccountDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.SystemparamDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.UserDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.UserPurseDO;
import com.zd.aoding.outsourcing.weChat.api.constant.SessionConstant;
import com.zd.aoding.outsourcing.weChat.api.facade.AccountFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SessionFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SystemparamFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.UserFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.UserPurseFacade;

import io.swagger.annotations.ApiParam;

/**
 * @ClassName: UserInfoController
 * @Description: 用户个人信息
 * @author: zj
 * @date: 2017年3月15日 下午1:21:37
 */
@Api(value = "", description = "个人信息管理")
@Controller
@RequestMapping("user")
public class UserInfoController {
	@Autowired
	private SessionFacade sessionFacade;
	@Autowired
	private SystemparamFacade systemparamFacade;
	@Autowired
	private UserPurseFacade userPurseFacade;
	@Autowired
	private UserFacade userFacade;
	@Autowired
	private AccountFacade accountFacade;

	@ResponseBody
	@RequestMapping(value = "change/portrait", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "修改头像", httpMethod = "POST", notes = "修改头像", response = ResponseUtil.class)
	public String changePortrait(
			@ApiParam(required = true, name = "imgUrl", value = "头像图片路径") @RequestParam(value = "imgUrl", required = true) String imgUrl,
			HttpServletRequest request) {
		try {
			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
			if (sessionUser != null) {
				UserDO updateUser = new UserDO();
				updateUser.setId(sessionUser.getUserId());
				if (StringUtil.isNULL(imgUrl)) {
					return ResponseUtil.paramErrorResultString("图片路径为空");
				}
				updateUser.setPortrait(imgUrl);
				int i = userFacade.updateUserPo(updateUser);
				if (i == 1) {
					UserBO userVo = userFacade.getUserByAccountId(sessionUser.getAccountId());
					AccountDO accountPo = accountFacade.getPoByPK(sessionUser.getAccountId());
					AccountBO accountMapper = new AccountBO(accountPo);
					if (accountMapper != null) {
						sessionFacade.saveLoginSession(accountMapper, SessionConstant.LOGIN_TYPE_USER, userVo, request);
						return ResponseUtil.successResultString("头像更新成功");
					}
					return ResponseUtil.showMSGResultString("头像更新失败");
				}
				return ResponseUtil.showMSGResultString("头像更新失败");
			} else {
				return ResponseUtil.notLoggedResultString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "updateUserInfo", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "修改基本信息", httpMethod = "POST", notes = "修改用户信息", response = ResponseUtil.class)
	public String updateUserInfo(
			@ApiParam(required = false, name = "birthdayStr", value = "生日传参，格式：yyyy-MM-dd") @RequestParam(value = "birthdayStr", required = false) String birthdayStr,
			@ModelAttribute UserDO userMapper, 
			HttpServletRequest request) {
		try {
			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
			if (sessionUser != null) {
				userMapper.setId(sessionUser.getUserId());
				String nickname = request.getParameter("nickname");
				if (!StringUtil.isNULL(nickname)) {
					userMapper.setNickName(nickname);
				}
				/*String realName = request.getParameter("realName");
				if (!StringUtil.isNULL(realName)) {
					userMapper.setr
				}*/
				String sex = request.getParameter("sex");
				if (!StringUtil.isNULL(sex) && StringUtil.isNumber(sex)) {
					userMapper.setSex(Integer.parseInt(sex));
				}
				String provinceId = request.getParameter("provinceId");
				if (!StringUtil.isNULL(provinceId) && StringUtil.isNumber(provinceId)) {
					userMapper.setProvinceId(Integer.parseInt(provinceId));
				}
				String cityId = request.getParameter("cityId");
				if (!StringUtil.isNULL(cityId) && StringUtil.isNumber(cityId)) {
					userMapper.setCityId(Integer.parseInt(cityId));
				}
				String districtId = request.getParameter("districtId");
				if (!StringUtil.isNULL(districtId) && StringUtil.isNumber(districtId)) {
					userMapper.setDistrictId(Integer.parseInt(districtId));
				}
				String address = request.getParameter("address");
				if (!StringUtil.isNULL(address)) {
					userMapper.setAddress(address);
				}
				// String birthday = request.getParameter("birthdayStr");
				if (!StringUtil.isNULL(birthdayStr)) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					userMapper.setBirthday(sdf.parse(birthdayStr));
				}
				int i = userFacade.updateUserPo(userMapper);
				if(i == 1){
					UserBO userVo = userFacade.getUserByAccountId(sessionUser.getAccountId());
					AccountBO accountMapper = sessionFacade.checkLoginAccountSession(request);
					if (accountMapper != null) {
						sessionFacade.saveLoginSession(accountMapper, SessionConstant.LOGIN_TYPE_USER, userVo, request);
						return ResponseUtil.successResultString("更新成功");
					}
				}
				return ResponseUtil.showMSGResultString("更新失败");
			} else {
				return ResponseUtil.notLoggedResultString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	@ResponseBody
	@RequestMapping(value = "userInfo", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "个人基本信息", httpMethod = "POST", notes = "个人基本信息", response = ResponseUtil.class)
	@ApiResponses({ @ApiResponse(code = 1, message = "实体类注释", response = UserBO.class) })
	public String userInfo(
			HttpServletRequest request) {
		try {
			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
			if (sessionUser != null) {
				//获取兑换比例
				String proportion = "1";
				SystemparamDO systemparamPo = systemparamFacade.getSystemparamPoByCode("proportion");
				if(systemparamPo != null){
					proportion = systemparamPo.getStringVale();
				}
				double d_proportion = Double.valueOf(proportion).doubleValue();
				UserPurseDO purseDO = userPurseFacade.getPursePoByUserId(sessionUser.getUserId(), "0");
				if(purseDO!=null){
					sessionUser.setScore(purseDO.getScore() * d_proportion);
					return ResponseUtil.successResultString(sessionUser);
				}
				return ResponseUtil.successResultString(sessionUser);
			} else {
				return ResponseUtil.notLoggedResultString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
//	@ResponseBody
//	@RequestMapping(value = "getVip", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
//	@ApiOperation(value = "兑换vip", httpMethod = "POST", notes = "兑换vip", response = ResponseUtil.class)
//	@ApiResponses({ @ApiResponse(code = 1, message = "实体类注释", response = UserVo.class) })
//	public String getVip(
//			HttpServletRequest request) {
//		try {
//			UserVo sessionUser = (UserVo) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER, "0");
//			if (sessionUser != null) {
//				SystemparamPo systemparam = systemparamFacade.getSystemparamPoByCode("vipScore");
//				int score = systemparam.getIntVale();
//				if(sessionUser.getVipScore() < score){
//					return ResponseUtil.resultString("会员积分不足", ResponseCodeEnum.ERROR);
//				}
//				UserPo user = new UserPo();
//				user.setId(sessionUser.getUserId());
//				user.setVipScore(sessionUser.getVipScore() - score);
//				user.setGradeSort(1);
//				int i = userFacade.updateUserPo(user);
//				if(i == 1){
//					return ResponseUtil.successResultString("兑换成功");
//				}
//				return ResponseUtil.resultString("兑换失败", ResponseCodeEnum.ERROR);
//			} else {
//				return ResponseUtil.notLoggedResultString();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ResponseUtil.systemErrorResultString();
//		}
//	}
}
