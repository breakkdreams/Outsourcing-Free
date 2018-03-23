package com.zd.aoding.outsourcing.web.controllerApi.management;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.RoleBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.RoleAccountDO;
import com.zd.aoding.outsourcing.weChat.api.facade.RoleAccountFacade;

@Api(value = "", description = "用户角色管理")
@Controller
@RequestMapping("ad/manager")
public class RoleAccountManagerController {
	@Autowired
	private RoleAccountFacade roleAccountFacade;
	@Autowired
	private HttpSession session;

	@ResponseBody
	@RequestMapping(value = "roleAccount/add", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "添加用户角色", httpMethod = "POST", notes = "添加用户角色", response = ResponseUtil.class)
	public String addRoleAccount(
			@ApiParam(name = "accountId", value = "用户id", required = true) @RequestParam(value = "accountId", required = false)
			String accountId,
			@ApiParam(name = "roleId", value = "角色id", required = true) @RequestParam(value = "roleId", required = false)
			String roleId,
			HttpServletRequest request) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "管理员管理修改角色");
			if(rv.getRoleFalg().equals("1")) {
				if(!StringUtil.isNumber(accountId)|| !StringUtil.isNumber(roleId)){
					return ResponseUtil.paramErrorResultString("类型错误");
				}
				int i = 0;
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("deleted",0);
				map.put("accountId",accountId);
				RoleAccountDO roleAccountPo = roleAccountFacade.getRoleAccountByMap(map);
				if(roleAccountPo == null){
					RoleAccountDO roleAccountPo1 = new RoleAccountDO(Integer.parseInt(accountId),Integer.parseInt(roleId));
					i = roleAccountFacade.insertRoleAccountPo(roleAccountPo1);
				}else{
					roleAccountPo.setRoleId(Integer.parseInt(roleId));
					i = roleAccountFacade.updateRoleAccountPo(roleAccountPo);
				}

				if (i == 1) {
					return ResponseUtil.successResultString("修改成功");
				}
				return ResponseUtil.showMSGResultString("修改失败");
			}else{
				return ResponseUtil.showMSGResultString("暂无权限");
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}

	@ResponseBody
	@RequestMapping(value = "roleAccount/detail", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "查看单个用户角色", httpMethod = "POST", notes = "查看单个用户角色", response = ResponseUtil.class)
	public String getAdvertisementMapper(
			@ApiParam(required = false, name = "accountId", value = "accountId") @RequestParam(value = "accountId", required = false) String accountId,
			HttpServletRequest request) {
		try {
			if (!StringUtil.isNULL(accountId) && StringUtil.isNumber(accountId)) {
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("deleted",0);
				map.put("accountId",accountId);
				RoleAccountDO roleAccountPo = roleAccountFacade.getRoleAccountByMap(map);
				if (roleAccountPo != null) {
					return ResponseUtil.successResultString(roleAccountPo);
				}
				return ResponseUtil.showMSGResultString("未找到");
			}
			return ResponseUtil.paramErrorResultString("id为空或格式不正确");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}

}
