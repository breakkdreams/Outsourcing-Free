package com.zd.aoding.outsourcing.web.controllerApi.management;

import java.util.HashMap;
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
import com.zd.aoding.common.StringDate.StringUtil;
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.MailBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.ManagerBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.MailDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ManagerDO;
import com.zd.aoding.outsourcing.weChat.api.constant.SessionConstant;
import com.zd.aoding.outsourcing.weChat.api.facade.MailFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SessionFacade;

@Api(value = "", description = "站内信")
@Controller
@RequestMapping("ad/manager")
public class DistributorMailManagerController {
	@Autowired
	private MailFacade mailFacade;
	@Autowired
	private HttpSession session;
	@Autowired
	private SessionFacade sessionFacade;

	
	@ResponseBody
	@RequestMapping(value = "mail/DTPaging", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ApiOperation(value = "查看站内信", httpMethod = "POST", notes = "查看站内信", response = ResponseUtil.class)
	public Map<String, Object> getScoreRecord(
			@ApiParam(required = false, name = "iDisplayStart", value = "分页查询开始条数（DataTable 内置参数）") @RequestParam(value = "iDisplayStart", required = false) String iDisplayStart,
			@ApiParam(required = false, name = "iDisplayLength", value = "分页查询条数（DataTable 内置参数）") @RequestParam(value = "iDisplayLength", required = false) String iDisplayLength,
			@ApiParam(required = false, name = "status", value = "0未读 1已读") @RequestParam(value = "status", required = false) String status,
			HttpServletRequest request) {
		try {
			ManagerBO manager = (ManagerBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_MANANGER);
			if(manager != null) {
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
				if((ManagerDO.TYPE_MANAGER+"").equals(manager.getType()+"")){
					param.put("type", MailDO.TYPE_DEALER);
					param.put("statusAdmin", status);
				}
//				if((ManagerDO.TYPE_DEALER+"").equals(manager.getType()+"")){
//					param.put("type", MailDO.TYPE_DEALER);
//					param.put("ownerId", manager.getOwnerId());
//					param.put("status", 0);
//				}
//				if((ManagerDO.TYPE_APPCOMPANY+"").equals(manager.getType()+"")){
//					param.put("type", MailDO.TYPE_COMPANY);
//					param.put("ownerId", manager.getOwnerId());
//					param.put("status", 0);
//				}
				if(!StringUtil.isNULL(sSearch)){
					
				}
				pageEntity.setParams(param);
				/**
				 * 查询
				 */
				PageResult<MailBO> pageResult = mailFacade.getPageMailVo(pageEntity);
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
	@RequestMapping(value = "mail/read", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "确认站内信", httpMethod = "POST", notes = "确认站内信", response = ResponseUtil.class)
	public String read(
			@ApiParam(required = false, name = "mailId", value = "id") @RequestParam(value = "mailId", required = false) String mailId,
			HttpServletRequest request) {
		try {
			ManagerBO manager = (ManagerBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_MANANGER);
			if(manager != null) {
				if(!StringUtil.isNumber(mailId)){
					return ResponseUtil.paramErrorResultString("id错误");
				}
				MailDO mail = mailFacade.getMailPoByPk(Integer.parseInt(mailId));
				if(mail == null){
					return ResponseUtil.showMSGResultString("未找到该站内信");
				}
				mail.setStatusAdmin(MailDO.STATUS_READ);
				int i = mailFacade.updateMail(mail);
				if(i == 1){
					return ResponseUtil.successResultString("确认成功");
				}
				return ResponseUtil.showMSGResultString("确认失败");
			}else{
				return ResponseUtil.showMSGResultString("暂无权限");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}

}
