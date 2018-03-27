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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.zd.aoding.base.DO.base.RecordBase;
import com.zd.aoding.common.Md5.MD5Util;
import com.zd.aoding.common.StringDate.StringUtil;
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.AccountBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.RoleBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.AccountDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ManagerDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.RecordsDO;
import com.zd.aoding.outsourcing.weChat.api.facade.AccountFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.ManagerFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.RecordFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SessionFacade;

@Api(value = "", description = "管理员管理")
@Controller
@RequestMapping("ad/manager")
public class DistributorAccountManagerController {
	@Autowired
	private AccountFacade accountFacade;
	@Autowired
	private ManagerFacade managerFacade;
	@Autowired
	private HttpSession session;
	@Autowired
	private SessionFacade sessionFacade;
	@Autowired 
	private RecordFacade recordFacade;

	@ResponseBody
	@RequestMapping(value = "account/add", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "添加account", httpMethod = "POST", notes = "添加account", response = ResponseUtil.class)
	public String addAccount(
			@ApiParam(name = "username", value = "账号", required = true) @RequestParam(value = "username", required = false)
					String username,
			@ApiParam(name = "password", value = "密码", required = true) @RequestParam(value = "password", required = false)
					String password,
			@ApiParam(name = "name", value = "姓名", required = true) @RequestParam(value = "name", required = false)
					String name,
			HttpServletRequest request) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			AccountBO loginAccount = sessionFacade.checkLoginAccountSession(request);
			RoleBO rv = new RoleBO(sess, "管理员管理添加");
			if(rv.getRoleFalg().equals("1")) {
				if(loginAccount == null){
					return ResponseUtil.notLoggedResultString();
				}
				AccountDO account = accountFacade.getManagerAccountPoByPhone(username,null);
				if (account != null) {
					return ResponseUtil.showMSGResultString("该手机号已注册");
				}
				AccountDO accountPo = new AccountDO(AccountDO.TYPE_MANAGER, username, MD5Util.MD5(password));
				int i = accountFacade.registerManager(accountPo, name, ManagerDO.TYPE_MANAGER, loginAccount.getAccountId());
				if (i == 1) {
					//操作日志
					RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "添加管理员成功", RecordsDO.RECORDTYPE_MANAGER_ADD,
    					RecordBase.DEALTYPE_MANAGER, loginAccount.getAccountId(), accountPo.getId(), "添加管理员", "");
    				recordFacade.insertRecordDO(recordDO);
    				
					return ResponseUtil.successResultString("添加成功");
				}
				return ResponseUtil.showMSGResultString("添加失败");
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
	@RequestMapping(value = "distributor/add", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "添加经销商", httpMethod = "POST", notes = "添加经销商", response = ResponseUtil.class)
	public String addDistributor(
			@ApiParam(name = "username", value = "账号", required = true) @RequestParam(value = "username", required = false)
			String username,
			@ApiParam(name = "password", value = "密码", required = true) @RequestParam(value = "password", required = false)
			String password,
			@ApiParam(name = "name", value = "姓名", required = true) @RequestParam(value = "name", required = false)
			String name,
			HttpServletRequest request) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			AccountBO loginAccount = sessionFacade.checkLoginAccountSession(request);
			RoleBO rv = new RoleBO(sess, "业务员管理添加");
			if(rv.getRoleFalg().equals("1")) {
				if(loginAccount == null){
					return ResponseUtil.notLoggedResultString();
				}
				AccountDO account = accountFacade.getManagerAccountPoByPhone(username,null);
				if (account != null) {
					return ResponseUtil.showMSGResultString("该手机号已注册");
				}
				AccountDO accountPo = new AccountDO(AccountDO.TYPE_RECURSIVE, username, MD5Util.MD5(password));
				int i = accountFacade.registerDistributor(accountPo, name, ManagerDO.TYPE_RECURSIVE,
						loginAccount.getAccountId(),loginAccount.getProvinceId());
				if (i == 1) {
					//操作日志
					RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "添加递推人员成功", RecordsDO.RECORDTYPE_MANAGER_ADD,
    					RecordBase.DEALTYPE_MANAGER, loginAccount.getAccountId(), accountPo.getId(), "添加递推人员", "");
    				recordFacade.insertRecordDO(recordDO);
    				
					return ResponseUtil.successResultString("添加成功");
				}
				return ResponseUtil.showMSGResultString("添加失败");
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
	@RequestMapping(value = "account/edit", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "编辑修改account", httpMethod = "POST", notes = "编辑修改account", response = ResponseUtil.class)
	public String editAccount(
			@ApiParam(name = "username", value = "账号", required = true) @RequestParam(value = "username", required = false)
					String username,
			@ApiParam(name = "password", value = "密码", required = true) @RequestParam(value = "password", required = false)
					String password,
			@ApiParam(name = "accountId", value = "id", required = true) @RequestParam(value = "accountId", required = false)
					String accountId,
			@ApiParam(name = "status", value = "状态", required = true) @RequestParam(value = "status", required = false)
					String status,
			@ApiParam(name = "name", value = "姓名", required = true) @RequestParam(value = "name", required = false)
					String name,
			HttpServletRequest request) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "管理员管理修改");
			if(rv.getRoleFalg().equals("1")) {
				if (!StringUtil.isNumber(accountId)) {
					return ResponseUtil.paramErrorResultString("参数错误");
				}
				AccountDO accountPo = accountFacade.getPoByPK(Integer.parseInt(accountId));
				if (accountPo == null) {
					return ResponseUtil.paramErrorResultString("参数错误");
				}
				if(!StringUtil.isNULL(status) && StringUtil.isNumber(status)){
					accountPo.setStatus(Integer.parseInt(status));
				}
				ManagerDO managerPo = managerFacade.getPoByAccountId(accountPo.getId());
				
				if(!StringUtil.isNULL(username)){
					if(!username.equals(managerPo.getName())){
						Map<String, Object> param = new HashMap<String, Object>();
						param.put("deleted", 0);
						param.put("username", username);
						param.put("idSelf", managerPo.getId());
						int p = accountFacade.countAccount(param);
						if(p>0){
							return ResponseUtil.showMSGResultString("该手机号已注册");
						}
					}
					accountPo.setUsername(username);
					managerPo.setPhone(username);
				}
				if(!StringUtil.isNULL(name)){
					managerPo.setName(name);
				}
				if(!StringUtil.isNULL(password) && !password.equals(accountPo.getPassword()) ){
					accountPo.setPassword(MD5Util.MD5(password));
				}

				int i = accountFacade.updateAccountPo(accountPo);
				if (i == 1) {
					managerFacade.updateManager(managerPo);
					//操作日志
					AccountBO account = sessionFacade.checkLoginAccountSession(request);
					RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "修改管理员成功", RecordsDO.RECORDTYPE_MANAGER_UPDATE,
    					RecordBase.DEALTYPE_MANAGER, account.getAccountId(), Integer.parseInt(accountId), "修改管理员", "");
    				recordFacade.insertRecordDO(recordDO);
    				
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
	@RequestMapping(value = "distributor/edit", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "编辑修改经销商", httpMethod = "POST", notes = "编辑修改经销商", response = ResponseUtil.class)
	public String editDistributor(
			@ApiParam(name = "username", value = "账号", required = true) @RequestParam(value = "username", required = false)
					String username,
			@ApiParam(name = "password", value = "密码", required = true) @RequestParam(value = "password", required = false)
					String password,
			@ApiParam(name = "accountId", value = "id", required = true) @RequestParam(value = "accountId", required = false)
					String accountId,
			@ApiParam(name = "status", value = "状态", required = true) @RequestParam(value = "status", required = false)
					String status,
			@ApiParam(name = "name", value = "姓名", required = true) @RequestParam(value = "name", required = false)
					String name,
			HttpServletRequest request) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "业务员管理修改");
			if(rv.getRoleFalg().equals("1")) {
				if (!StringUtil.isNumber(accountId)) {
					return ResponseUtil.showMSGResultString("参数错误");
				}
				AccountDO accountPo = accountFacade.getPoByPK(Integer.parseInt(accountId));
				if (accountPo == null) {
					return ResponseUtil.showMSGResultString("参数错误");
				}
				if(!StringUtil.isNULL(status) && StringUtil.isNumber(status)){
					accountPo.setStatus(Integer.parseInt(status));
				}
				ManagerDO managerPo = managerFacade.getPoByAccountId(accountPo.getId());

				if(!StringUtil.isNULL(username)){
					if(!username.equals(managerPo.getPhone())){
						Map<String, Object> param = new HashMap<String, Object>();
						param.put("deleted", 0);
						param.put("idSelf", managerPo.getId());
						param.put("username", username);
						int p = accountFacade.countAccount(param);
						if(p>0){
							return ResponseUtil.showMSGResultString("该手机号已注册");
						}
					}
					accountPo.setUsername(username);
					managerPo.setPhone(username);
				}
				if(!StringUtil.isNULL(name)){
					managerPo.setName(name);
				}
				if(!StringUtil.isNULL(password) && !password.equals(accountPo.getPassword()) ){
					accountPo.setPassword(MD5Util.MD5(password));
				}

				int i = accountFacade.updateAccountPo(accountPo);
				if (i == 1) {
					managerFacade.updateManager(managerPo);
					//操作日志
					AccountBO account = sessionFacade.checkLoginAccountSession(request);
					RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "修改管理员成功", RecordsDO.RECORDTYPE_MANAGER_UPDATE,
							RecordBase.DEALTYPE_MANAGER, account.getAccountId(), Integer.parseInt(accountId), "修改管理员", "");
					recordFacade.insertRecordDO(recordDO);

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
	@RequestMapping(value = "account/delete", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "删除account", httpMethod = "POST", notes = "删除account", response = ResponseUtil.class)
	public String deletedAccount(
			@ApiParam(required = true, name = "accountId", value = "accountId") @RequestParam(value = "accountId", required = true) String accountId,
			HttpServletRequest request) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "管理员管理删除");
			if(rv.getRoleFalg().equals("1")) {
				if (!StringUtil.isNULL(accountId) && StringUtil.isNumber(accountId)) {
					AccountDO accountPo = accountFacade.getPoByPK(Integer.parseInt(accountId));
					if (accountPo == null) {
						return ResponseUtil.paramErrorResultString("参数错误");
					}
					accountPo.setDeleted(1);
					int i = accountFacade.updateAccountPo(accountPo);
					if (i == 1) {
						ManagerDO managerPo = managerFacade.getPoByAccountId(accountPo.getId());
						managerPo.setDeleted(1);
						managerFacade.updateManager(managerPo);
						
						//操作日志
						AccountBO account = sessionFacade.checkLoginAccountSession(request);
						RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "删除管理员成功", RecordsDO.RECORDTYPE_MANAGER_DELETED,
	    					RecordBase.DEALTYPE_MANAGER, account.getAccountId(), Integer.parseInt(accountId), "删除管理员", "");
	    				recordFacade.insertRecordDO(recordDO);
	    				
	    				
						return ResponseUtil.successResultString("删除成功");
					}
					return ResponseUtil.showMSGResultString("删除失败");
				}
				return ResponseUtil.showMSGResultString("缺少需要删除的id");
			}else{
				return ResponseUtil.showMSGResultString("暂无权限");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}

	@ResponseBody
	@RequestMapping(value = "distributor/delete", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "删除Distributor", httpMethod = "POST", notes = "删除Distributor", response = ResponseUtil.class)
	public String deletedDistributor(
			@ApiParam(required = true, name = "accountId", value = "accountId") @RequestParam(value = "accountId", required = true) String accountId,
			HttpServletRequest request) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "业务员管理删除");
			if(rv.getRoleFalg().equals("1")) {
				if (!StringUtil.isNULL(accountId) && StringUtil.isNumber(accountId)) {
					AccountDO accountPo = accountFacade.getPoByPK(Integer.parseInt(accountId));
					if (accountPo == null) {
						return ResponseUtil.showMSGResultString("参数错误");
					}
					accountPo.setDeleted(1);
					int i = accountFacade.updateAccountPo(accountPo);
					if (i == 1) {
						ManagerDO managerPo = managerFacade.getPoByAccountId(accountPo.getId());
						managerPo.setDeleted(1);
						managerFacade.updateManager(managerPo);

						//操作日志
						AccountBO account = sessionFacade.checkLoginAccountSession(request);
						RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "删除管理员成功", RecordsDO.RECORDTYPE_MANAGER_DELETED,
	    					RecordBase.DEALTYPE_MANAGER, account.getAccountId(), Integer.parseInt(accountId), "删除管理员", "");
	    				recordFacade.insertRecordDO(recordDO);


						return ResponseUtil.successResultString("删除成功");
					}
					return ResponseUtil.showMSGResultString("删除失败");
				}
				return ResponseUtil.showMSGResultString("缺少需要删除的id");
			}else{
				return ResponseUtil.showMSGResultString("暂无权限");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	@ResponseBody
	@RequestMapping(value = "account/DTPaging", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ApiOperation(value = "查看account", httpMethod = "POST", notes = "查看account", response = ResponseUtil.class)
	public Map<String, Object> getAccount(
			@ApiParam(required = false, name = "iDisplayStart", value = "分页查询开始条数（DataTable 内置参数）") @RequestParam(value = "iDisplayStart", required = false) String iDisplayStart,
			@ApiParam(required = false, name = "iDisplayLength", value = "分页查询条数（DataTable 内置参数）") @RequestParam(value = "iDisplayLength", required = false) String iDisplayLength,
			HttpServletRequest request) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "管理员管理查看");
			if(rv.getRoleFalg().equals("1")) {
				String sSearch = request.getParameter("sSearch");
				// 初始化列表
				PageEntity pageEntity = new PageEntity();
				if (!StringUtil.isNULL(iDisplayStart) && Integer.valueOf(iDisplayStart) > 0) {
					pageEntity.setPage(Integer.valueOf(iDisplayStart) / Integer.valueOf(iDisplayLength) + 1);
				}
				if (!StringUtil.isNULL(iDisplayLength)) {
					pageEntity.setSize(Integer.valueOf(iDisplayLength));
				}
				/**
				 * 排序
				 */
				pageEntity.setOrderColumn("create_time");
				/**
				 * 查询条件参数
				 */
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("deleted", 0);
				param.put("type",AccountDO.TYPE_MANAGER);
				
				pageEntity.setParams(param);
				/**
				 * 查询
				 */
				PageResult<AccountBO> pageResult = accountFacade.getPageAccountVo(pageEntity);
				Map<String, Object> resultMap = new HashMap<String, Object>();
				String jsonString = JSON.toJSONString(pageResult.getResultList(), true);
				JSONArray jsonArray = JSON.parseArray(jsonString);
				resultMap.put("iTotalRecords", pageResult.getTotalSize());
				resultMap.put("iTotalDisplayRecords", pageResult.getTotalSize());
				resultMap.put("aaData", jsonArray);
				return resultMap;
			}else{
				return ResponseUtil.showMSGResultMap("暂无权限");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultMap();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "distributor/DTPaging", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ApiOperation(value = "查看经销商", httpMethod = "POST", notes = "查看经销商", response = ResponseUtil.class)
	public Map<String, Object> getDistributor(
			@ApiParam(required = false, name = "iDisplayStart", value = "分页查询开始条数（DataTable 内置参数）") @RequestParam(value = "iDisplayStart", required = false) String iDisplayStart,
			@ApiParam(required = false, name = "iDisplayLength", value = "分页查询条数（DataTable 内置参数）") @RequestParam(value = "iDisplayLength", required = false) String iDisplayLength,
			HttpServletRequest request) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "业务员管理查看");
			if(rv.getRoleFalg().equals("1")) {
				String sSearch = request.getParameter("sSearch");
				// 初始化列表
				PageEntity pageEntity = new PageEntity();
				if (!StringUtil.isNULL(iDisplayStart) && Integer.valueOf(iDisplayStart) > 0) {
					pageEntity.setPage(Integer.valueOf(iDisplayStart) / Integer.valueOf(iDisplayLength) + 1);
				}
				if (!StringUtil.isNULL(iDisplayLength)) {
					pageEntity.setSize(Integer.valueOf(iDisplayLength));
				}
				AccountBO account = sessionFacade.checkLoginAccountSession(request);
				/**
				 * 排序
				 */
				pageEntity.setOrderColumn("id");
				/**
				 * 查询条件参数
				 */
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("deleted", 0);
				param.put("type",AccountDO.TYPE_RECURSIVE);
				if(account.getAccountId()!=1){
					param.put("ownerId",account.getAccountId());
				}
				pageEntity.setParams(param);
				/**
				 * 查询
				 */
				PageResult<AccountBO> pageResult = accountFacade.getPageAccountVo(pageEntity);
				Map<String, Object> resultMap = new HashMap<String, Object>();
				String jsonString = JSON.toJSONString(pageResult.getResultList(), true);
				JSONArray jsonArray = JSON.parseArray(jsonString);
				resultMap.put("iTotalRecords", pageResult.getTotalSize());
				resultMap.put("iTotalDisplayRecords", pageResult.getTotalSize());
				resultMap.put("aaData", jsonArray);
				return resultMap;
			}else{
				return ResponseUtil.showMSGResultMap("暂无权限");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultMap();
		}
	}

	@ResponseBody
	@RequestMapping(value = "account/detail", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "查看单个账号", httpMethod = "POST", notes = "查看单个账号", response = ResponseUtil.class)
	public String getMapper(
			@ApiParam(required = false, name = "accountId", value = "accountId") @RequestParam(value = "accountId", required = false) String accountId,
			HttpServletRequest request) {
		try {
			if (!StringUtil.isNULL(accountId) && StringUtil.isNumber(accountId)) {
				AccountDO accountPo = accountFacade.getPoByPK(Integer.parseInt(accountId));
				if (accountPo != null) {
					//操作日志
					AccountBO account = sessionFacade.checkLoginAccountSession(request);
					RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "查看管理员详情成功", RecordsDO.RECORDTYPE_MANAGER_VIEW,
    					RecordBase.DEALTYPE_MANAGER, account.getAccountId(), Integer.parseInt(accountId), "查看管理员详情", "");
    				recordFacade.insertRecordDO(recordDO);
    				
					return ResponseUtil.successResultString(accountPo);
				}
				return ResponseUtil.showMSGResultString("未找到");
			}
			return ResponseUtil.showMSGResultString("id为空或格式不正确");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}

	@ResponseBody
	@RequestMapping(value = "manager/detail", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "查看单个账号", httpMethod = "POST", notes = "查看单个账号", response = ResponseUtil.class)
	public String getManagerMapper(
			@ApiParam(required = false, name = "accountId", value = "accountId") @RequestParam(value = "accountId", required = false) String accountId,
			HttpServletRequest request) {
		try {
			if (!StringUtil.isNULL(accountId) && StringUtil.isNumber(accountId)) {
				ManagerDO managerPo = managerFacade.getPoByAccountId(Integer.parseInt(accountId));
				if (managerPo != null) {
					return ResponseUtil.successResultString(managerPo);
				}
				return ResponseUtil.showMSGResultString("未找到");
			}
			return ResponseUtil.showMSGResultString("id为空或格式不正确");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
}
