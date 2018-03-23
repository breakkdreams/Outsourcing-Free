/**   
 * Copyright © 2017 嘉兴市奥丁网络科技有限公司. All rights reserved.
 * 
 * @Title: LogionController.java 
 * @Prject: Outsourcing-WeChat-Web
 * @Package: com.zd.aoding.outsourcing.web.controllerApi.management 
 * @Description: TODO
 * @author: HCD   
 * @date: 2017年12月28日 上午10:11:05 
 * @version: V1.0   
 */
package com.zd.aoding.outsourcing.web.controllerApi.management;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.*;
import com.zd.aoding.outsourcing.weChat.api.facade.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.zd.aoding.common.Md5.MD5Util;
import com.zd.aoding.common.StringDate.StringUtil;
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.AccountBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.ManagerBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.RoleBO;
import com.zd.aoding.outsourcing.weChat.api.constant.SessionConstant;

@Api(value = "", description = "基础界面")
@Controller
public class DistributorLogionController {
	
	@Autowired
	private AccountFacade accountFacade;
	@Autowired
	private RoleFacade roleFacade;
	@Autowired
	private SessionFacade sessionFacade;
	@Autowired
	private DistributorMenuFacade menuFacade;
	@Autowired
	private ManagerFacade managerFacade;
	@Autowired
	private HttpSession session;
	@Autowired
	private RoleAccountFacade roleAccountFacade;

	
	/**
	 * 登录
	 * @param userName
	 * @param password
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "adminLogin", method = RequestMethod.POST)
	@ApiOperation(value = "登录")
	public String logn(
			@ApiParam(required = true, name = "loginType", value = "登陆类型:经销商‘managerUserVo’") @RequestParam(value = "loginType", required = true) String loginType,
			@ApiParam(name = "userName", value = "账号")String userName,
			@ApiParam(name = "password", value = "密码")String password,
			HttpServletRequest request){
		try {
			if (StringUtil.isNULL(loginType) || StringUtil.isNULL(userName) || StringUtil.isNULL(password)) {
				return ResponseUtil.showMSGResultString("参数错误");
			}
			if (!SessionConstant.LOGIN_TYPE_MANANGER.equals(loginType)) {
				return ResponseUtil.showMSGResultString("非法登录");
			}
			AccountDO accountPo = accountFacade.getManagerAccountPoByPhone(userName,AccountDO.TYPE_DISTRIBUTOR);
			if (accountPo != null) {
				switch (loginType) {
					case SessionConstant.LOGIN_TYPE_MANANGER:
						if (accountPo.getStatus().equals(AccountDO.STATUS_UNUSE)){
							return ResponseUtil.showMSGResultString("账户被禁用");
						}
						if (accountPo.getPassword().equals(MD5Util.MD5(new String(password.getBytes())))) {
							ManagerDO manager = managerFacade.getPoByAccountId(accountPo.getId());
							ManagerBO managerVo = new ManagerBO(manager);
							AccountBO accountVo = new AccountBO(accountPo);
							sessionFacade.saveLoginSession(accountVo, SessionConstant.LOGIN_TYPE_MANANGER, managerVo, request);

							RoleAccountDO roleAccountPo = roleAccountFacade.getRoleAccountPoByPK(accountVo.getAccountId()+"");
							RoleDO rolePo = roleFacade.getPoByPK(roleAccountPo.getRoleId());
							String[] roleStr = (rolePo.getMenuList()).split(",");
							//获取全部菜单
							Map<String, Object> map = new HashMap<String,Object>();
							List<DistributorMenuDO> list = menuFacade.getList(map);
							List<Map<String, Object>> listMenuSess = new ArrayList<>();
							for (DistributorMenuDO ass : list) {
								Map<String, Object> menuSess = new HashMap<String,Object>();
								if(roleStr.length>0){
									for (int i = 0 ; i <roleStr.length ; i++){
										if (roleStr[i].equals(ass.getPid()+"")){
											if(ass.getFalg()==3){
												menuSess.put("pName",ass.getpName());
												menuSess.put("pid",ass.getPid());
												menuSess.put("erId",ass.getErId());
												menuSess.put("url",ass.getUrl());
												menuSess.put("falg",ass.getFalg());
												listMenuSess.add(menuSess);
											}
										}
									}
								}
							}
							session.setAttribute("menuSession", listMenuSess);

							return ResponseUtil.successResultString("登录成功");
						}
						return ResponseUtil.showMSGResultString("密码错误");
					default:
						break;
				}
			}
			return ResponseUtil.showMSGResultString("账号不存在！");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	
	@ResponseBody
    @RequestMapping(value="logoff", method = RequestMethod.POST)
    @ApiOperation(value="退出登录", httpMethod="post", notes = "jsp的名称为部分路径", response = ModelAndView.class)
    public String loginoff(
            HttpServletRequest request) {
        try {
            int i = sessionFacade.removeSessionAndMemcache(request);
            if(i == 1){
                return ResponseUtil.successResultString("退出成功");
            }
            return ResponseUtil.successResultString("退出失败");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.systemErrorResultString();
        }
    }
	
	/**
	 * @Title: loginJsp
	 * @Description: 账号登陆界面
	 * @param jspName
	 * @param request
	 * @return
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "login/{jspName}", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "所有登录界面", httpMethod = "GET", notes = "创建账号", response = ModelAndView.class)
	public ModelAndView loginJsp(
			@ApiParam(required = true, name = "jspName", value = "jsp页面文件名") @PathVariable(value = "jspName") String jspName,
			HttpServletRequest request) {
		System.out.println(jspName);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("web/login/" + jspName);
		return mav;
	}
	
	/**
	 * 添加/修改 角色权限<role>
	 * @param session
	 * @param roleName
	 * @param menuIds
	 * @return 返回 falg  1 为修改 2为添加
	 */
	@RequestMapping(value = "admin/insertRole", method = RequestMethod.POST)
	@ApiOperation(value = "添加角色")
	public @ResponseBody String insertRole(HttpServletRequest reques,HttpSession session,
			@ApiParam(name = "roleName", value = "角色名字")String roleName,
			@ApiParam(name = "menuIds", value = "按钮ID")String[] menuIds){
		      String flag =""; 
		      String menuList ="";
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
		      String roleId = reques.getParameter("roleId");
		      if(!StringUtils.isNotBlank(roleId)){
		    	  RoleBO rv = new RoleBO(sess, "角色修改");
				  if(rv.getRoleFalg().equals("1")){
					
		    	  RoleDO updaterolePo = new RoleDO();
		    	  for (String ids : menuIds) {
		    		  menuList = menuList+String.valueOf(ids)+",";
		    	  }
		    	  updaterolePo.setRoleId(Integer.parseInt(roleId));
		    	  updaterolePo.setRoleName(roleName);
		    	  updaterolePo.setMenuList(menuList);
		    	  int i = roleFacade.updateRole(updaterolePo);
		    	  flag =i+"";
				}else{
					flag ="error";
				}
		      }else{
		    	  RoleBO rv = new RoleBO(sess, "角色添加");
				if(rv.getRoleFalg().equals("1")){
		    	  RoleDO rolePo = new RoleDO();
		    	  for (String ids : menuIds) {
		    		  menuList = menuList+String.valueOf(ids)+",";
		    	  }
		    	  rolePo.setRoleName(roleName);
		    	  rolePo.setMenuList(menuList);
		    	  int i = roleFacade.insertRoles(rolePo);
		    	  flag= i+"";
		      }else{
		    	  flag ="error";
		      }
		     }
		      return flag;
	}
	/**
	 * 删除角色<role>
	 * @param reques
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "admin/deleteRole", method = RequestMethod.POST)
	@ApiOperation(value = "删除角色")
	public @ResponseBody String deleteRole(HttpServletRequest reques,HttpSession session,
			@ApiParam(name = "roleId", value = "角色ID")int roleId){
		      String flag =""; 
		      List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
		    	 RoleBO rv = new RoleBO(sess, "角色删除");
				if(rv.getRoleFalg().equals("1")){
		    	  RoleDO deleteRolePo = new RoleDO();
		    	  deleteRolePo.setRoleId(roleId);
		    	  deleteRolePo.setDeleted(1);
		    	  int i = roleFacade.updateRole(deleteRolePo);
		    	  flag =i+"";
			}else{
				flag ="error";
			}
				return flag;
	}
	
	@ResponseBody
	@RequestMapping(value = "ad/manager/updatePassword", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "修改密码", httpMethod = "POST", notes = "修改密码", response = ResponseUtil.class)
	public String resetPassword(
			@ApiParam(required = true, name = "accountId", value = "id") @RequestParam(value = "accountId", required = true) String accountId,
			@ApiParam(required = true, name = "oldPassword", value = "原密码") @RequestParam(value = "oldPassword", required = true) String oldPassword,
			@ApiParam(required = true, name = "newPassword", value = "新密码") @RequestParam(value = "newPassword", required = true) String newPassword,
			HttpServletRequest request) {
		try {
			AccountBO accountVo = sessionFacade.checkLoginAccountSession(request);
			if(accountVo != null){
				if (!StringUtil.isNumber(accountId) || StringUtil.isNULL(oldPassword) || StringUtil.isNULL(newPassword)) {
					return ResponseUtil.paramErrorResultString("参数错误");
				}
				if(!accountId.equals(accountVo.getAccountId()+"")){
					return ResponseUtil.paramErrorResultString("accountId与登录账号不一致");
				}
				AccountDO account = accountFacade.getPoByPK(Integer.parseInt(accountId));
				if(!MD5Util.MD5(oldPassword).equals(account.getPassword())){
					return ResponseUtil.showMSGResultString("原密码错误");
				}
				account.setPassword(MD5Util.MD5(newPassword));
				int i = accountFacade.updateAccountPo(account);
				if(i == 1){
					return ResponseUtil.successResultString("修改成功");
				}
				return ResponseUtil.showMSGResultString("修改失败");
			}
			return ResponseUtil.notLoggedResultString();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
}
	

