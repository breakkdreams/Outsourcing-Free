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
public class BusinessBusinessManagerController {
	@Autowired
	private AccountFacade accountFacade;
	@Autowired
	private ManagerFacade managerFacade;
	@Autowired
	private SessionFacade sessionFacade;
	@Autowired 
	private RecordFacade recordFacade;
	@Autowired
	private BusinessTypeFacade businessTypeFacade;


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
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
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
