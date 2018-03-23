package com.zd.aoding.outsourcing.web.controllerApi.management;

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
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.BusinessTypeBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.RoleBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.AccountDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ManagerDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.RecordsDO;
import com.zd.aoding.outsourcing.weChat.api.facade.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "", description = "商家管理")
@Controller
@RequestMapping("ad/manager")
public class DistributorBusinessManagerController {
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
	@Autowired
	private BusinessTypeFacade businessTypeFacade;


	@ResponseBody
	@RequestMapping(value = "business/add", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "添加商家", httpMethod = "POST", notes = "添加商家", response = ResponseUtil.class)
	public String addBusiness(
			@ApiParam(name = "username", value = "账号", required = true) @RequestParam(value = "username", required = false)
			String username,
			@ApiParam(name = "password", value = "密码", required = true) @RequestParam(value = "password", required = false)
			String password,
			@ApiParam(name = "name", value = "姓名", required = true) @RequestParam(value = "name", required = false)
			String name,
			@ApiParam(name = "contacts", value = "联系人", required = true) @RequestParam(value = "contacts", required = false)
			String contacts,
			@ApiParam(name = "businessType", value = "商家类型", required = true) @RequestParam(value = "businessType", required = false)
			String businessType,
			@ApiParam(name = "lng", value = "经度", required = true) @RequestParam(value = "lng", required = false)
			String lng,
			@ApiParam(name = "lat", value = "纬度", required = true) @RequestParam(value = "lat", required = false)
			String lat,
			@ApiParam(name = "provinceId", value = "省id", required = true) @RequestParam(value = "provinceId", required = false)
			String provinceId,
			@ApiParam(name = "images", value = "图片", required = true) @RequestParam(value = "images", required = false)
			String images,
			@ApiParam(name = "rebate", value = "返点", required = true) @RequestParam(value = "rebate", required = false)
			String rebate,
			@ApiParam(name = "content", value = "内容", required = true) @RequestParam(value = "content", required = false)
			String content,
			HttpServletRequest request) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			AccountBO loginAccount = sessionFacade.checkLoginAccountSession(request);
			RoleBO rv = new RoleBO(sess, "商家管理添加");
			if(rv.getRoleFalg().equals("1")) {
				if(loginAccount == null){
					return ResponseUtil.notLoggedResultString();
				}
				AccountDO account = accountFacade.getManagerAccountPoByPhone(username,null);
				if (account != null) {
					return ResponseUtil.showMSGResultString("该手机号已注册");
				}
				AccountDO accountPo = new AccountDO(AccountDO.TYPE_BUSINESS, username, MD5Util.MD5(password));
				Map<String,Object> param = new HashMap<String,Object>();
				if(!StringUtil.isNULL(contacts)){
					param.put("contacts",contacts);
				}
				if(StringUtil.isNumber(businessType)){
					param.put("businessType",businessType);
				}
				if(StringUtil.isNumber(provinceId)){
					param.put("provinceId",provinceId);
				}
				if(!StringUtil.isNULL(lng)){
					param.put("lng",lng);
				}
				if(!StringUtil.isNULL(lat)){
					param.put("lat",lat);
				}
				if(!StringUtil.isNULL(images)){
					param.put("images",images);
				}
				if(!StringUtil.isNULL(name)){
					param.put("name",name);
				}
				if(!StringUtil.isNULL(content)){
					param.put("content",content);
				}
				if(!StringUtil.isNULL(rebate)){
					param.put("name",new BigDecimal(rebate).doubleValue());
				}
				param.put("addAccountId",loginAccount.getAccountId());

				int i = accountFacade.registerBusiness(accountPo, param);
				if (i == 1) {
					//操作日志
					RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "添加经销商成功", RecordsDO.RECORDTYPE_MANAGER_ADD,
    					RecordBase.DEALTYPE_MANAGER, loginAccount.getAccountId(), accountPo.getId(), "添加经销商", "");
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
	@RequestMapping(value = "business/edit", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "编辑修改商家", httpMethod = "POST", notes = "编辑修改商家", response = ResponseUtil.class)
	public String editBusiness(
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
			@ApiParam(name = "contacts", value = "联系人", required = true) @RequestParam(value = "contacts", required = false)
					String contacts,
			@ApiParam(name = "businessType", value = "商家类型", required = true) @RequestParam(value = "businessType", required = false)
					String businessType,
			@ApiParam(name = "lng", value = "经度", required = true) @RequestParam(value = "lng", required = false)
					String lng,
			@ApiParam(name = "lat", value = "纬度", required = true) @RequestParam(value = "lat", required = false)
					String lat,
			@ApiParam(name = "provinceId", value = "省id", required = true) @RequestParam(value = "provinceId", required = false)
					String provinceId,
			@ApiParam(name = "images", value = "图片", required = true) @RequestParam(value = "images", required = false)
					String images,
			@ApiParam(name = "rebate", value = "返点", required = true) @RequestParam(value = "rebate", required = false)
					String rebate,
			@ApiParam(name = "content", value = "内容", required = true) @RequestParam(value = "content", required = false)
					String content,
			HttpServletRequest request) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "商家管理修改");
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
				if(!StringUtil.isNULL(contacts)){
					managerPo.setContacts(contacts);
				}
				if(StringUtil.isNumber(businessType)){
					managerPo.setBusinessType(Integer.parseInt(businessType));
				}
				if(StringUtil.isNumber(provinceId)){
					managerPo.setProvinceId(Integer.parseInt(provinceId));
				}
				if(!StringUtil.isNULL(lng)){
					managerPo.setLng(new BigDecimal(lng).doubleValue());
				}
				if(!StringUtil.isNULL(lat)){
					managerPo.setLat(new BigDecimal(lat).doubleValue());
				}
				if(!StringUtil.isNULL(images)){
					managerPo.setImages(images);
				}
				if(!StringUtil.isNULL(content)){
					managerPo.setContent(content);
				}
				if(!StringUtil.isNULL(rebate)){
					managerPo.setRebate(new BigDecimal(rebate).doubleValue());
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
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}


	@ResponseBody
	@RequestMapping(value = "business/delete", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "删除商家", httpMethod = "POST", notes = "删除商家", response = ResponseUtil.class)
	public String deletedBusiness(
			@ApiParam(required = true, name = "accountId", value = "accountId") @RequestParam(value = "accountId", required = true) String accountId,
			HttpServletRequest request) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "商家管理删除");
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
	@RequestMapping(value = "business/DTPaging", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ApiOperation(value = "查看商家", httpMethod = "POST", notes = "查看商家", response = ResponseUtil.class)
	public Map<String, Object> getBusiness(
			@ApiParam(required = false, name = "iDisplayStart", value = "分页查询开始条数（DataTable 内置参数）") @RequestParam(value = "iDisplayStart", required = false) String iDisplayStart,
			@ApiParam(required = false, name = "iDisplayLength", value = "分页查询条数（DataTable 内置参数）") @RequestParam(value = "iDisplayLength", required = false) String iDisplayLength,
			HttpServletRequest request) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "商家管理查看");
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
				param.put("type",AccountDO.TYPE_BUSINESS);
				param.put("ownerId",account.getAccountId());

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
	@RequestMapping(value = "business/detail", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "查看单个账号", httpMethod = "POST", notes = "查看单个账号", response = ResponseUtil.class)
	public String getManagerBusiness(
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

	@ResponseBody
	@RequestMapping(value = "businessType/allList", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded;charset=utf-8")
	@ApiOperation(value = "查询全部商家类型", httpMethod = "POST", notes = "查询全部商家类型", response = ResponseUtil.class)
	public String getAllBusinessTypeList(
			HttpServletRequest request) {
		try {
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("deleted",0);
			List<BusinessTypeBO> businessTypeList = businessTypeFacade.getBusinessTypeList(param);
			if (businessTypeList != null) {
				return ResponseUtil.successResultString(businessTypeList);
			}
			return ResponseUtil.showMSGResultString("查询失败");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
}
