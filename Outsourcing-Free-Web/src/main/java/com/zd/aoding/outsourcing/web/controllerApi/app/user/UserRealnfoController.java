package com.zd.aoding.outsourcing.web.controllerApi.app.user;
/*package com.zd.api.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import com.zd.api.facade.AccountFacade;
import com.zd.api.facade.SessionFacade;
import com.zd.api.facade.user.UserFacade;
import com.zd.api.util.SMSManager;
import com.zd.common.StringDate.StringUtil;
import com.zd.common.constant.SessionConstant;
import com.zd.common.idCard.IdcardValidator;
import com.zd.common.response.ResponseCodeEnum;
import com.zd.common.response.ResponseUtil;
import com.zd.model.api.vo.UserRealInfoVo;
import com.zd.model.api.vo.UserVo;
import com.zd.model.po.UserRealInfoPo;

@Api(value = "", description = "用户账实名登记")
@Controller
@RequestMapping("user")
public class UserRealnfoController {
	@Autowired
	private AccountFacade accountFacade;
	@Autowired
	private UserFacade userFacade;
	@Autowired
	private SessionFacade sessionFacade;

	@ResponseBody
	@RequestMapping(value = "realInfo/detail", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "获取实名信息", httpMethod = "POST", notes = "", response = ResponseUtil.class)
	@ApiResponses({ @ApiResponse(code = 1, message = "配置名称", response = UserRealInfoVo.class) })
	public String realInfoDetail(HttpServletRequest request) {
		try {
			UserVo user = (UserVo) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
			if (user == null) {
				return ResponseUtil.notLoggedResultString();
			}
			UserRealInfoVo userRealInfoPo = userFacade.getUserRealInfo(user.getUserId());
			if (userRealInfoPo != null) {
				return ResponseUtil.successResultString(userRealInfoPo);
			} else {
				return ResponseUtil.showMSGResultString("未完善");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	@ResponseBody
	@RequestMapping(value = "realInfo/add", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "添加实名信息", httpMethod = "POST", notes = "检查手机号是否注册，code==200已注册，300未注册，999系统错误", response = ResponseUtil.class)
	public String realInfoAdd(@ApiParam(required = true, name = "code", value = "验证码") @RequestParam(value = "code", required = true) String code,
			@ApiParam(required = true, name = "idCard", value = "身份证") @RequestParam(value = "idCard", required = true) String idCard,
			@ApiParam(required = true, name = "realName", value = "姓名") @RequestParam(value = "realName", required = true) String realName,
			@ApiParam(required = false, name = "idCardFrontUrl", value = "身份证正面") @RequestParam(value = "idCardFrontUrl", required = false) String idCardFrontUrl,
			@ApiParam(required = false, name = "idCardBackUrl", value = "身份证背面") @RequestParam(value = "idCardBackUrl", required = false) String idCardBackUrl,
			@ApiParam(required = false, name = "idCardAndPersonUrl", value = "手持身份") @RequestParam(value = "idCardAndPersonUrl", required = false) String idCardAndPersonUrl,
			HttpServletRequest request) {
		try {
			UserVo user = (UserVo) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
			if (user == null) {
				return ResponseUtil.notLoggedResultString();
			}
			UserRealInfoVo userRealInfoPoOld = userFacade.getUserRealInfo(user.getUserId());
			if (userRealInfoPoOld != null) {
				return ResponseUtil.errorResultString("已添加实名信息请修改，而不是添加");
			}
			
			idCard.replace(" ", "");
			IdcardValidator iv = new IdcardValidator();
			if (StringUtil.isNULL(idCard) || idCard.length() != 18 || !iv.isValidatedAllIdcard(idCard)) {
				return ResponseUtil.resultString("身份证格式有错", ResponseCodeEnum.PARAM_ERROR);
			}
			if (StringUtil.isNULL(realName) || realName.length() > 6 || realName.length() < 2) {
				return ResponseUtil.paramErrorResultString(realName + "=realName");
			}
			String rCode = sessionFacade.checkMemcache(SMSManager.getRealNameCode(user.getPhone()));
			if (!code.equals(rCode)) {
				return ResponseUtil.resultString("验证码错误！", ResponseCodeEnum.PARAM_ERROR);
			}
			
			
			UserRealInfoPo userRealInfoPo = new UserRealInfoPo(user.getUserId(), realName, idCard, idCardFrontUrl, idCardBackUrl, idCardAndPersonUrl);
			int i = userFacade.insertUserRealInfo(userRealInfoPo);
			if (i == 1) {
				return ResponseUtil.successResultString("添加成功");
			} else {
				return ResponseUtil.errorResultString("添加失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	@ResponseBody
	@RequestMapping(value = "realInfo/edit", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "编辑实名信息", httpMethod = "POST", notes = "检查手机号是否注册，code==200已注册，300未注册，999系统错误", response = ResponseUtil.class)
	public String realInfoEdit(
			@ApiParam(required = false, name = "userRealInfoId", value = "用户实名信息") @RequestParam(value = "userRealInfoId", required = false) String userRealInfoId,
			@ApiParam(required = false, name = "idCard", value = "身份证") @RequestParam(value = "idCard", required = false) String idCard,
			@ApiParam(required = false, name = "realName", value = "姓名") @RequestParam(value = "realName", required = false) String realName,
			@ApiParam(required = false, name = "idCardFrontUrl", value = "身份证正面") @RequestParam(value = "idCardFrontUrl", required = false) String idCardFrontUrl,
			@ApiParam(required = false, name = "idCardBackUrl", value = "身份证背面") @RequestParam(value = "idCardBackUrl", required = false) String idCardBackUrl,
			@ApiParam(required = false, name = "idCardAndPersonUrl", value = "手持身份") @RequestParam(value = "idCardAndPersonUrl", required = false) String idCardAndPersonUrl,
			HttpServletRequest request) {
		try {
			UserVo user = (UserVo) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
			if (user == null) {
				return ResponseUtil.notLoggedResultString();
			}
			if (!StringUtil.isNumber(userRealInfoId)) {
				return ResponseUtil.paramErrorResultString("userRealInfoId" + userRealInfoId);
			}
			if (idCard != null) {
				idCard.replace(" ", "");
				IdcardValidator iv = new IdcardValidator();
				if (StringUtil.isNULL(idCard) || idCard.length() != 18 || !iv.isValidatedAllIdcard(idCard)) {
					return ResponseUtil.resultString("身份证格式有错", ResponseCodeEnum.PARAM_ERROR);
				}
			}
			if (realName != null) {
				if (StringUtil.isNULL(realName) || realName.length() > 6 || realName.length() < 2) {
					return ResponseUtil.paramErrorResultString(realName + "=realName");
				}
			}
			UserRealInfoPo userRealInfoPo = new UserRealInfoPo(user.getUserId(), realName, idCard, idCardFrontUrl, idCardBackUrl, idCardAndPersonUrl);
			userRealInfoPo.setId(Integer.parseInt(userRealInfoId));
			int i = userFacade.updateUserRealInfo(userRealInfoPo);
			if (i == 1) {
				return ResponseUtil.successResultString("修改成功");
			} else {
				return ResponseUtil.errorResultString("修改失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	*//**
	 * @Title: phoneRegCode
	 * @Description:注册发送验证码
	 * @param phone
	 * @param request
	 * @return
	 * @return: String
	 *//*
	@ResponseBody
	@RequestMapping(value = "realInfoCode", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "实名认证验证码", httpMethod = "POST", notes = "用户个人信息手机号接收；测试阶段请求均失败code==300，请求之后使用验证码‘123456’", response = ResponseUtil.class)
	public String realInfoCode(HttpServletRequest request) {
		try {
			UserVo user = (UserVo) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
			if (user == null) {
				return ResponseUtil.notLoggedResultString();
			}
			int i = SMSManager.sendRealNameCode(user.getPhone());
			if (i == 1) {
				return ResponseUtil.successResultString("发送成功请注意查收");
			}
			return ResponseUtil.resultString("验证码发送失败", ResponseCodeEnum.ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
}
*/