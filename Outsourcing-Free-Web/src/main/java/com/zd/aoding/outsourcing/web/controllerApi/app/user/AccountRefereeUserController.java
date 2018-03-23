package com.zd.aoding.outsourcing.web.controllerApi.app.user;

import java.util.HashMap;
import java.util.Map;

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
import com.zd.aoding.common.StringDate.StringUtil;
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.AccountRefereeBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.UserBO;
import com.zd.aoding.outsourcing.weChat.api.constant.SessionConstant;
import com.zd.aoding.outsourcing.weChat.api.facade.AccountRefereeFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SessionFacade;

@Api(value = "", description = "用户推荐列表")
@Controller
@RequestMapping("user")
public class AccountRefereeUserController {
	@Autowired
	private SessionFacade sessionFacade;
	@Autowired
	private AccountRefereeFacade accountRefereeFacade;

	@ResponseBody
	@RequestMapping(value = "accountReferee/getPage", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "分页查询推荐关系", httpMethod = "POST", notes = "分页查询推荐关系", response = ResponseUtil.class)
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
				pageEntity.setOrderColumn("create_time");
				pageEntity.setOrderTurn("desc");
				/**
				 * 查询条件参数
				 */
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("refereeAccountId", sessionUser.getAccountId());
				param.put("deleted", 0);
				pageEntity.setParams(param);
				/**
				 * 查询
				 */
				PageResult<AccountRefereeBO> pageResult = accountRefereeFacade.getPageAccountRefereeVo(pageEntity);
				return ResponseUtil.successResultString(pageResult);
			}
			return ResponseUtil.notLoggedResultString();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	@ResponseBody
	@RequestMapping(value = "accountReferee/getPageTwo", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "根据直推账号id分页查询二级推荐关系", httpMethod = "POST", notes = "根据直推账号id分页查询二级推荐关系", response = ResponseUtil.class)
	public String accountReferee(
			@ApiParam(required = false, name = "pageNum", value = "当前页数（即当前第几页）") @RequestParam(value = "pageNum", required = false) String pageNum,
			@ApiParam(required = false, name = "pageSize", value = "分页条数") @RequestParam(value = "pageSize", required = false) String pageSize,
			@ApiParam(required = true, name = "beRefereeAccountId", value = "直推账号id") @RequestParam(value = "beRefereeAccountId", required = true) String beRefereeAccountId,
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
				pageEntity.setOrderColumn("create_time");
				pageEntity.setOrderTurn("desc");
				/**
				 * 查询条件参数
				 */
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("refereeAccountId", Integer.parseInt(beRefereeAccountId));
				param.put("deleted", 0);
				pageEntity.setParams(param);
				/**
				 * 查询
				 */
				PageResult<AccountRefereeBO> pageResult = accountRefereeFacade.getPageAccountRefereeVo(pageEntity);
				return ResponseUtil.successResultString(pageResult);
			}
			return ResponseUtil.notLoggedResultString();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
}
