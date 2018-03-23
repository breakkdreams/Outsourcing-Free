package com.zd.aoding.outsourcing.web.controllerApi.management;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.zd.aoding.common.StringDate.StringUtil;
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.AccountBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.RoleBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsParamDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.RecordsDO;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsParamFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.RecordFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SessionFacade;
import com.zd.aoding.outsourcing.weChat.api.utils.file.ConfigTemp;

@Api(value = "", description = "产品配置管理")
@Controller
@RequestMapping("ad/manager/dealer/")
public class GoodsParamDealerController {
	@Autowired
	private GoodsParamFacade goodsParamFacade;
	@Autowired 
	private RecordFacade recordFacade;
	@Autowired
	private SessionFacade sessionFacade;

	@ResponseBody
	@RequestMapping(value = "goodsParam/add", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "产品配置添加", httpMethod = "POST", notes = "", response = ResponseUtil.class)
	public String goodsSpecAdd(
			@ApiParam(required = true, name = "goodsId", value = "产品id") @RequestParam(value = "goodsId", required = true) String goodsId,
			@ApiParam(required = true, name = "title", value = "产品属性名称") @RequestParam(value = "title", required = true) String title,
			@ApiParam(required = true, name = "value", value = "产品属性值") @RequestParam(value = "value", required = true) String value,
			HttpServletRequest request) {
		try {
            	@SuppressWarnings("unchecked")
				List<Map<String, Object>> sess = (List<Map<String, Object>>) request
                        .getSession(true).getAttribute("menuSession");
                RoleBO rv = new RoleBO(sess, "产品设置修改");
                if ("1".equals(rv.getRoleFalg())) {
                	if (!StringUtil.isNumber(goodsId)) {
                        return ResponseUtil.paramErrorResultString("参数错误");
                    }
                    GoodsParamDO gppo = new GoodsParamDO();
                    gppo.setGoodsId(Integer.parseInt(goodsId));
                    gppo.setTitle(title);
                    gppo.setValue(value);
                    int i = goodsParamFacade.insertGoodsParamPo(gppo);
                    if (i == 1) {
                    	//操作日志
    					AccountBO account = sessionFacade.checkLoginAccountSession(request);
    					RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "添加成功", RecordsDO.RECORDTYPE_MANAGER_ADD,
    						RecordBase.DEALTYPE_MANAGER, account.getAccountId(), Integer.parseInt(goodsId), "添加产品属性", "");
    					recordFacade.insertRecordDO(recordDO);
    					
                        return ResponseUtil.successResultString(gppo);
                    }
                    return ResponseUtil.showMSGResultString("添加失败");
                }
                return ResponseUtil.showMSGResultString("无权限");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	@ResponseBody
	@RequestMapping(value = "goodsParam/delete", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "产品配置删除", httpMethod = "POST", notes = "", response = ResponseUtil.class)
	public String goodsSpecDel(
			@ApiParam(required = true, name = "paramId", value = "属性id") @RequestParam(value = "paramId", required = true) String paramId,
			HttpServletRequest request) {
		try {
            	@SuppressWarnings("unchecked")
				List<Map<String, Object>> sess = (List<Map<String, Object>>) request
                        .getSession(true).getAttribute("menuSession");
                RoleBO rv = new RoleBO(sess, "产品设置修改");
                if ("1".equals(rv.getRoleFalg())) {
                	if (!StringUtil.isNumber(paramId)) {
                        return ResponseUtil.paramErrorResultString("参数错误");
                    }
                    int i = goodsParamFacade.deleteParamPo(Integer.parseInt(paramId));
                    if (i == 1) {
                    	//操作日志
    					AccountBO account = sessionFacade.checkLoginAccountSession(request);
    					RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "删除成功", RecordsDO.RECORDTYPE_MANAGER_DELETED,
    						RecordBase.DEALTYPE_MANAGER, account.getAccountId(), Integer.parseInt(paramId), "删除产品属性", "");
    					recordFacade.insertRecordDO(recordDO);
    					
                        return ResponseUtil.successResultString("删除成功!");
                    }
                    return ResponseUtil.showMSGResultString("删除失败");
                }
                return ResponseUtil.showMSGResultString("无权限");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	@ResponseBody
	@RequestMapping(value = "goodsParam/edit", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "产品配置修改", httpMethod = "POST", notes = "", response = ResponseUtil.class)
	public String goodsEdit(
			@ApiParam(required = true, name = "paramId", value = "产品属性id") @RequestParam(value = "paramId", required = true) String paramId,
			@ApiParam(required = true, name = "title", value = "产品属性名称") @RequestParam(value = "title", required = true) String title,
			@ApiParam(required = true, name = "value", value = "产品属性值") @RequestParam(value = "value", required = true) String value,
			HttpServletRequest request) {
		try {
            	@SuppressWarnings("unchecked")
				List<Map<String, Object>> sess = (List<Map<String, Object>>) request
                        .getSession(true).getAttribute("menuSession");
                RoleBO rv = new RoleBO(sess, "产品设置修改");
                if ("1".equals(rv.getRoleFalg())) {
                	if (!StringUtil.isNumber(paramId)) {
                        return ResponseUtil.paramErrorResultString("参数错误");
                    }
                    GoodsParamDO goodsParamPo = new GoodsParamDO();
                    goodsParamPo.setId(Integer.parseInt(paramId));
                    goodsParamPo.setTitle(title);
                    goodsParamPo.setValue(value);
                    int i = goodsParamFacade.addOrUpdateGoodsParam(goodsParamPo, ConfigTemp.updateType);
                    if (i == 1) {
                    	//操作日志
    					AccountBO account = sessionFacade.checkLoginAccountSession(request);
    					RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "修改成功", RecordsDO.RECORDTYPE_MANAGER_UPDATE,
    						RecordBase.DEALTYPE_MANAGER, account.getAccountId(), Integer.parseInt(paramId), "修改产品属性", "");
    					recordFacade.insertRecordDO(recordDO);
    					
                        return ResponseUtil.successResultString("修改成功!");
                    }
                    return ResponseUtil.showMSGResultString("修改失败");
                }
                return ResponseUtil.showMSGResultString("无权限");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	@ResponseBody
	@RequestMapping(value = "goodsParam/DTPaging", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ApiOperation(value = "分页查询产品属性", httpMethod = "POST", notes = "", response = ResponseUtil.class)
	public Map<String, Object> goodsSpecItemDTPaging(
			@ApiParam(required = false, name = "iDisplayStart", value = "分页查询开始条数（DataTable 内置参数）") @RequestParam(value = "iDisplayStart", required = false) String iDisplayStart,
			@ApiParam(required = false, name = "iDisplayLength", value = "分页查询条数（DataTable 内置参数）") @RequestParam(value = "iDisplayLength", required = false) String iDisplayLength,
			@ApiParam(required = false, name = "goodsId", value = "产品id") @RequestParam(value = "goodsId", required = false) String goodsId,
			HttpServletRequest request) {
		try {
//                String sSearch = request.getParameter("sSearch");
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
                param.put("goodsId", Integer.parseInt(goodsId));
                pageEntity.setParams(param);
                /**
                 * 查询
                 */
                PageResult<GoodsParamDO> pageResult = goodsParamFacade.getParamListPaging(pageEntity);
                Map<String, Object> resultMap = new HashMap<String, Object>();
                String jsonString = JSON.toJSONString(pageResult.getResultList(), true);
                JSONArray jsonArray = JSON.parseArray(jsonString);
                resultMap.put("iTotalRecords", pageResult.getTotalSize());
                resultMap.put("iTotalDisplayRecords", pageResult.getTotalSize());
                resultMap.put("aaData", jsonArray);
                return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultMap();
		}
	}
}
