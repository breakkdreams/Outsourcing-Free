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
import com.zd.aoding.base.DO.base.RecordBase;
import com.zd.aoding.common.StringDate.StringUtil;
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.AccountBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.RoleBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.RecordsDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.SystemparamDO;
import com.zd.aoding.outsourcing.weChat.api.facade.RecordFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SessionFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SystemparamFacade;

@Controller
@Api(value = "",description = "admin管理系统参数")
@RequestMapping("ad/manager")
public class SystemParamManagerController {
	@Autowired
	private HttpSession session;
    @Autowired
    private SystemparamFacade systemParamFacade;
    @Autowired
	private RecordFacade recordFacade;
	@Autowired
	private SessionFacade sessionFacade;
    
    
    @ResponseBody
    @RequestMapping(value = "systemparam/edit", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
    @ApiOperation(value = "修改系统参数", httpMethod = "POST", notes = "修改系统参数", response = ResponseUtil.class)
    public String systemParamEdit(
            @ApiParam(required = false, name = "code", value = "参数code") @RequestParam(value = "code") String code,
            @ApiParam(required = false, name = "intValue", value = "参数值") @RequestParam(value = "intValue", required = false) String intValue,
            HttpServletRequest request) {
        try {
        	List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "系统参数修改");
			if(rv.getRoleFalg().equals("1")) {
                if(!StringUtil.isNumber(intValue)){
                    return ResponseUtil.paramErrorResultString("参数值错误");
                }
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("code", code);
                param.put("deleted", 0);
                List<SystemparamDO> list = systemParamFacade.getSystemparamList(param);
                if(list != null && list.size() == 1){
                    int id = list.get(0).getSystemParamId();
                    SystemparamDO systemParam = new SystemparamDO();
                    systemParam.setSystemParamId(id);
                    systemParam.setIntVale(Integer.parseInt(intValue));
                    int i = systemParamFacade.updateSystemparam(systemParam);
                    if(i == 1){
                    	//操作日志
        				AccountBO account = sessionFacade.checkLoginAccountSession(request);
        				RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "修改成功", RecordsDO.RECORDTYPE_MANAGER_UPDATE,
        					RecordBase.DEALTYPE_MANAGER, account.getAccountId(), id, "修改系统参数"+code+"值为"+intValue, "");
        				recordFacade.insertRecordDO(recordDO);
        				
                        return ResponseUtil.successResultString("修改成功");
                    }
                    return ResponseUtil.showMSGResultString("修改失败");
                }
                return ResponseUtil.errorResultString("找不到该系统参数");
            }
            return ResponseUtil.showMSGResultString("无权限");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.systemErrorResultString();
        }
    }
    
    @ResponseBody
    @RequestMapping(value = "systemparamStringVal/edit", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
    @ApiOperation(value = "修改系统参数", httpMethod = "POST", notes = "修改系统参数", response = ResponseUtil.class)
    public String systemParamStringEdit(
    		@ApiParam(required = false, name = "code", value = "参数code") @RequestParam(value = "code") String code,
    		@ApiParam(required = false, name = "stringValue", value = "参数值") @RequestParam(value = "stringValue", required = false) String stringValue,
    		HttpServletRequest request) {
    	try {
    		List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
    		RoleBO rv = new RoleBO(sess, "系统参数修改");
    		if(rv.getRoleFalg().equals("1")) {
    			if(StringUtil.isNULL(stringValue)){
    				return ResponseUtil.paramErrorResultString("参数值错误");
    			}
    			Map<String, Object> param = new HashMap<String, Object>();
    			param.put("code", code);
    			param.put("deleted", 0);
    			List<SystemparamDO> list = systemParamFacade.getSystemparamList(param);
    			if(list != null && list.size() == 1){
    				int id = list.get(0).getSystemParamId();
    				SystemparamDO systemParam = new SystemparamDO();
    				systemParam.setSystemParamId(id);
    				systemParam.setStringVale(stringValue);
    				int i = systemParamFacade.updateSystemparam(systemParam);
    				if(i == 1){
    					//操作日志
        				AccountBO account = sessionFacade.checkLoginAccountSession(request);
        				RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "修改成功", RecordsDO.RECORDTYPE_MANAGER_UPDATE,
        					RecordBase.DEALTYPE_MANAGER, account.getAccountId(), id, "修改系统参数"+code+"值为"+stringValue, "");
        				recordFacade.insertRecordDO(recordDO);
        				
    					return ResponseUtil.successResultString("修改成功");
    				}
    				return ResponseUtil.showMSGResultString("修改失败");
    			}
    			return ResponseUtil.errorResultString("找不到该系统参数");
    		}
    		return ResponseUtil.showMSGResultString("无权限");
    	} catch (Exception e) {
    		e.printStackTrace();
    		return ResponseUtil.systemErrorResultString();
    	}
    }
    
}
