package com.zd.aoding.outsourcing.web.controllerApi.app.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wordnik.swagger.annotations.Api;
import com.zd.aoding.outsourcing.weChat.api.facade.AccountFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SessionFacade;

@Api(value = "", description = "用户修改密码")
@Controller
@RequestMapping("user")
public class AccountUserController {
	@Autowired
	private SessionFacade sessionFacade;
	@Autowired
	private AccountFacade accountFacade;

//	@ResponseBody
//	@RequestMapping(value = "updatePassword", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
//	@ApiOperation(value = "修改密码", httpMethod = "POST", notes = "修改密码", response = ResponseUtil.class)
//	public String changePassword(
//			@ApiParam(required = true, name = "password", value = "原密码") @RequestParam(value = "password", required = true) String password,
//			@ApiParam(required = true, name = "newPassword", value = "新密码") @RequestParam(value = "newPassword", required = true) String newPassword,
//			@ApiParam(name = "appCode", value = "appCode") @PathVariable(value = "appCode") String appCode,
//			HttpServletRequest request) {
//		try {
//			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
//			if (sessionUser != null) {
//				if (StringUtil.isNULL(password) || StringUtil.isNULL(newPassword)) {
//					return ResponseUtil.resultString("参数错误", ResponseCodeEnum.PARAM_ERROR);
//				}
//				AccountDO account = accountFacade.getPoByPK(sessionUser.getAccountId());
//				String oldPassword = account.getPassword();
//				if (MD5Util.MD5(password).equals(oldPassword)) {
//					Integer id = sessionUser.getAccountId();
//					int i = accountFacade.reSetPassword(newPassword, id);
//					if (i == 1) {
//						return ResponseUtil.successResultString("修改密码成功");
//					}
//					return ResponseUtil.resultString("修改密码失败", ResponseCodeEnum.ERROR);
//				}
//				return ResponseUtil.resultString("原密码错误", ResponseCodeEnum.PARAM_ERROR);
//			}
//			return ResponseUtil.notLoggedResultString();
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ResponseUtil.systemErrorResultString();
//		}
//	}
}
