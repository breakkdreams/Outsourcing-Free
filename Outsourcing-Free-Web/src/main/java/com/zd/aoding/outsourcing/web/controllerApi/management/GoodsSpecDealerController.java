package com.zd.aoding.outsourcing.web.controllerApi.management;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.GoodsOptionBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.GoodsSpecBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.RoleBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsOptionDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsSpecDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsSpecItemDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.RecordsDO;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsOptionFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsSpecFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsSpecItemFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.RecordFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SessionFacade;
import com.zd.aoding.outsourcing.weChat.api.utils.file.Dikaerj;

@Api(value = "", description = "产品配置管理")
@Controller
@RequestMapping("ad/manager/dealer/")
public class GoodsSpecDealerController {
    @Autowired
    private GoodsFacade goodsFacade;
    @Autowired
    private GoodsSpecFacade goodsSpecFacade;
    @Autowired
    private GoodsOptionFacade goodsOptionFacade;
    @Autowired
    private GoodsSpecItemFacade goodsSpecItemFacade;
	@Autowired 
	private RecordFacade recordFacade;
	@Autowired
	private SessionFacade sessionFacade;

    @ResponseBody
    @RequestMapping(value = "goodsSpec/add", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
    @ApiOperation(value = "产品配置添加", httpMethod = "POST", notes = "", response = ResponseUtil.class)
    public String goodsSpecAdd(
            @ApiParam(required = true, name = "goodsId", value = "产品id") @RequestParam(value = "goodsId", required = true) String goodsId,
            @ApiParam(required = true, name = "title", value = "产品配置名称") @RequestParam(value = "title", required = true) String title,
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
                    List<GoodsSpecBO>  list = goodsSpecFacade.getGoodsSpecVoByGoodId(goodsId);
                    if(list != null && list.size() >=2){
                        return ResponseUtil.showMSGResultString("产品配置最多添加两个");
                    }
                    GoodsSpecDO goodsSpecDo = new GoodsSpecDO(
                            Integer.parseInt(goodsId), title);
                    int i = goodsSpecFacade.insertGoodsSpecPo(goodsSpecDo);
                    if (i == 1) {
                    	//操作日志
    					AccountBO account = sessionFacade.checkLoginAccountSession(request);
    					RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "添加成功", RecordsDO.RECORDTYPE_MANAGER_ADD,
    						RecordBase.DEALTYPE_MANAGER, account.getAccountId(), Integer.parseInt(goodsId), "添加产品配置", "");
    					recordFacade.insertRecordDO(recordDO);
    					
                        return ResponseUtil.successResultString(goodsSpecDo);
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
    @RequestMapping(value = "goodsSpec/delete", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
    @ApiOperation(value = "产品配置删除", httpMethod = "POST", notes = "", response = ResponseUtil.class)
    public String goodsSpecDel(
            @ApiParam(required = true, name = "specId", value = "配置id") @RequestParam(value = "specId", required = true) String specId,
            HttpServletRequest request) {
        try {
            	@SuppressWarnings("unchecked")
				List<Map<String, Object>> sess = (List<Map<String, Object>>) request
                        .getSession(true).getAttribute("menuSession");
                RoleBO rv = new RoleBO(sess, "产品设置修改");
                if ("1".equals(rv.getRoleFalg())) {
                	if (!StringUtil.isNumber(specId)) {
                        return ResponseUtil.paramErrorResultString("参数错误");
                    }
                    GoodsSpecDO gsPo = new GoodsSpecDO();
                    gsPo.setId(Integer.parseInt(specId));
                    gsPo.setDeleted(1);
                    int i = goodsSpecFacade.updateGoodsSpecPo(gsPo);
                    if (i == 1) {
                    	//操作日志
    					AccountBO account = sessionFacade.checkLoginAccountSession(request);
    					RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "删除成功", RecordsDO.RECORDTYPE_MANAGER_DELETED,
    						RecordBase.DEALTYPE_MANAGER, account.getAccountId(), Integer.parseInt(specId), "删除产品配置", "");
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
    @RequestMapping(value = "goodsSpec/edit", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
    @ApiOperation(value = "产品配置修改", httpMethod = "POST", notes = "", response = ResponseUtil.class)
    public String goodsEdit(
            @ApiParam(required = true, name = "specId", value = "产品配置id") @RequestParam(value = "specId", required = true) String specId,
            @ApiParam(required = true, name = "title", value = "产品配置名称") @RequestParam(value = "title", required = true) String title,
            HttpServletRequest request) {
        try {
            	@SuppressWarnings("unchecked")
				List<Map<String, Object>> sess = (List<Map<String, Object>>) request
                        .getSession(true).getAttribute("menuSession");
                RoleBO rv = new RoleBO(sess, "产品设置修改");
                if ("1".equals(rv.getRoleFalg())) {
                	if (!StringUtil.isNumber(specId)) {
                        return ResponseUtil.paramErrorResultString("参数错误");
                    }
                    GoodsSpecDO goodsSpecDo = new GoodsSpecDO();
                    goodsSpecDo.setId(Integer.parseInt(specId));
                    goodsSpecDo.setTitle(title);
                    int i = goodsSpecFacade.updateGoodsSpecPo(goodsSpecDo);
                    if (i == 1) {
                    	//操作日志
    					AccountBO account = sessionFacade.checkLoginAccountSession(request);
    					RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "修改成功", RecordsDO.RECORDTYPE_MANAGER_UPDATE,
    						RecordBase.DEALTYPE_MANAGER, account.getAccountId(), Integer.parseInt(specId), "修改产品配置", "");
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
    @RequestMapping(value = "goodsSpecItem/add", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
    @ApiOperation(value = "查询配置详情删除", httpMethod = "POST", notes = "", response = ResponseUtil.class)
    public String goodsSpecItemAdd(
            @ApiParam(required = true, name = "specId", value = "产品配置id	") @RequestParam(value = "specId", required = false) String specId,
            @ApiParam(required = true, name = "title", value = "产品配置属性名称	") @RequestParam(value = "title", required = false) String title,
            HttpServletRequest request) {
        try {
            	@SuppressWarnings("unchecked")
				List<Map<String, Object>> sess = (List<Map<String, Object>>) request
                        .getSession(true).getAttribute("menuSession");
                RoleBO rv = new RoleBO(sess, "产品设置修改");
                if ("1".equals(rv.getRoleFalg())) {
                	if (!StringUtil.isNumber(specId)) {
                        return ResponseUtil.paramErrorResultString("specId");
                    }
                    GoodsSpecItemDO goodsSpecItemDo = new GoodsSpecItemDO(
                            Integer.parseInt(specId), title);
                    int i = goodsSpecItemFacade
                            .insertGoodsSpecItemPo(goodsSpecItemDo);
                    if (i == 1) {
                    	//操作日志
    					AccountBO account = sessionFacade.checkLoginAccountSession(request);
    					RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "添加成功", RecordsDO.RECORDTYPE_MANAGER_ADD,
    						RecordBase.DEALTYPE_MANAGER, account.getAccountId(), Integer.parseInt(specId), "添加产品配置详情", "");
    					recordFacade.insertRecordDO(recordDO);
    					
                        return ResponseUtil.successResultString(goodsSpecItemDo);
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
    @RequestMapping(value = "goodsSpecItem/edit", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
    @ApiOperation(value = "产品配置属性修改", httpMethod = "POST", notes = "", response = ResponseUtil.class)
    public String goodsSpecItemEdit(
            @ApiParam(required = true, name = "specItemId", value = "产品配置id") @RequestParam(value = "specItemId", required = true) String specItemId,
            @ApiParam(required = true, name = "title", value = "产品配置名称") @RequestParam(value = "title", required = true) String title,
            @ApiParam(required = false, name = "isShow", value = "配置属性是否可选（0否1是）") @RequestParam(value = "isShow", required = false) String isShow,
            HttpServletRequest request) {
        try {
            	@SuppressWarnings("unchecked")
				List<Map<String, Object>> sess = (List<Map<String, Object>>) request
                        .getSession(true).getAttribute("menuSession");
                RoleBO rv = new RoleBO(sess, "产品设置修改");
                if ("1".equals(rv.getRoleFalg())) {
                	if (!StringUtil.isNumber(specItemId)) {
                        return ResponseUtil.paramErrorResultString("参数错误");
                    }
                    GoodsSpecItemDO goodsSpecItemDo = new GoodsSpecItemDO();
                    goodsSpecItemDo.setId(Integer.parseInt(specItemId));
                    goodsSpecItemDo.setTitle(title);
                    if (StringUtil.isNumber(isShow)) {
                        goodsSpecItemDo.setIsShow(Integer.parseInt(isShow));
                    }
                    int i = goodsSpecItemFacade
                            .updateGoodsSpecItemPo(goodsSpecItemDo);
                    if (i == 1) {
                    	//操作日志
    					AccountBO account = sessionFacade.checkLoginAccountSession(request);
    					RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "修改成功", RecordsDO.RECORDTYPE_MANAGER_UPDATE,
    						RecordBase.DEALTYPE_MANAGER, account.getAccountId(), Integer.parseInt(specItemId), "修改产品配置详情", "");
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
    @RequestMapping(value = "goodsSpecItem/delete", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
    @ApiOperation(value = "查询配置详情删除", httpMethod = "POST", notes = "", response = ResponseUtil.class)
    public String goodsSpecItemDel(
            @ApiParam(required = true, name = "specItemId", value = "产品配置属性id	") @RequestParam(value = "specItemId", required = false) String specItemId,
            HttpServletRequest request) {
        try {
            	@SuppressWarnings("unchecked")
				List<Map<String, Object>> sess = (List<Map<String, Object>>) request
                        .getSession(true).getAttribute("menuSession");
                RoleBO rv = new RoleBO(sess, "产品设置修改");
                if ("1".equals(rv.getRoleFalg())) {
                	if (!StringUtil.isNumber(specItemId)) {
                        return ResponseUtil.paramErrorResultString("specItemId");
                    }
                    GoodsSpecItemDO gsiPo = new GoodsSpecItemDO();
                    gsiPo.setDeleted(1);
                    gsiPo.setId(Integer.parseInt(specItemId));
                    int i = goodsSpecItemFacade.updateGoodsSpecItemPo(gsiPo);
                    if (i == 1) {
                    	//操作日志
    					AccountBO account = sessionFacade.checkLoginAccountSession(request);
    					RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "删除成功", RecordsDO.RECORDTYPE_MANAGER_DELETED,
    						RecordBase.DEALTYPE_MANAGER, account.getAccountId(), Integer.parseInt(specItemId), "删除产品配置详情", "");
    					recordFacade.insertRecordDO(recordDO);
    					
                        return ResponseUtil.successResultString("删除成功");
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
    @RequestMapping(value = "goodsSpecItem/DTPaging", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ApiOperation(value = "分页查询产品配置属性", httpMethod = "POST", notes = "", response = ResponseUtil.class)
    public Map<String, Object> goodsSpecItemDTPaging(
            @ApiParam(required = false, name = "iDisplayStart", value = "分页查询开始条数（DataTable 内置参数）") @RequestParam(value = "iDisplayStart", required = false) String iDisplayStart,
            @ApiParam(required = false, name = "iDisplayLength", value = "分页查询条数（DataTable 内置参数）") @RequestParam(value = "iDisplayLength", required = false) String iDisplayLength,
            @ApiParam(required = false, name = "specId", value = "配置id") @RequestParam(value = "specId", required = false) String specId,
            HttpServletRequest request) {
        try {
//                String sSearch = request.getParameter("sSearch");
                // 初始化列表
                PageEntity pageEntity = new PageEntity();
                if (!StringUtil.isNULL(iDisplayStart)
                        && Integer.valueOf(iDisplayStart) > 0) {
                    pageEntity.setPage(Integer.valueOf(iDisplayStart)
                            / Integer.valueOf(iDisplayLength) + 1);
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
                param.put("specId", specId);
                pageEntity.setParams(param);
                /**
                 * 查询
                 */
                PageResult<GoodsSpecItemDO> pageResult = goodsSpecItemFacade
                        .getGoodsSpecItemPaging(pageEntity);
                Map<String, Object> resultMap = new HashMap<String, Object>();
                String jsonString = JSON.toJSONString(
                        pageResult.getResultList(), true);
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

    @ResponseBody
    @RequestMapping(value = "goodsOption/autoAdd", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
    @ApiOperation(value = "自动生成产品配置组合", httpMethod = "POST", notes = "", response = ResponseUtil.class)
    public String goodsOption(
            @ApiParam(required = true, name = "goodsId", value = "产品id	") @RequestParam(value = "goodsId", required = false) String goodsId,
            @ApiParam(required = true, name = "param", value = "配置参数	") @RequestParam(value = "param", required = false) String param,
            HttpServletRequest request) {
        try {
            	@SuppressWarnings("unchecked")
				List<Map<String, Object>> sess = (List<Map<String, Object>>) request
                        .getSession(true).getAttribute("menuSession");
                RoleBO rv = new RoleBO(sess, "产品设置修改");
                if ("1".equals(rv.getRoleFalg())) {
                	@SuppressWarnings("rawtypes")
                    List<HashMap> spec = JSON.parseArray(param, HashMap.class);
                    if (!StringUtil.isNumber(goodsId)) {
                        return ResponseUtil.paramErrorResultString("参数错误");
                    }
                    goodsOptionFacade.deletedAllOptionByGoodsId(goodsId);
                    if (spec.size() == 0) {
                        return ResponseUtil.paramErrorResultString("param");
                    }
                    @SuppressWarnings("rawtypes")
                    List<List> idLL = new ArrayList<>();
                    @SuppressWarnings("rawtypes")
                    List<List> titleLL = new ArrayList<>();
                    for (int j = 0; j < spec.size(); j++) {
                        String specItemIds = (String) spec.get(j).get("specItemIds");
                        String[] arrSpecItemId = specItemIds.split(",");
                        for (int i = 0; i < arrSpecItemId.length; i++) {
                            GoodsSpecItemDO updateDo = new GoodsSpecItemDO();
                            updateDo.setId(Integer.parseInt(arrSpecItemId[i]));
                            updateDo.setIsShow(1);
                            goodsSpecItemFacade.updateGoodsSpecItemPo(updateDo);
                        }
                        String[] specItem = specItemIds.split(",");
                        List<String> ids = Arrays.asList(specItem);
                        String specItemIitles = (String) spec.get(j).get("specItemIitles");
                        String[] specItemTitle = specItemIitles.split(",");
                        List<String> titles = Arrays.asList(specItemTitle);
                        if (specItem.length > 0 && specItemTitle.length > 0) {
                            idLL.add(ids);
                            titleLL.add(titles);
                        }
                    }
                    List<String> id = Dikaerj.descartes(idLL);
                    List<String> title = Dikaerj.descartes(titleLL);
                    if (title.size() > 0) {
                        int j = title.size() + 1;
                        for (int i = 0; i < title.size(); i++) {
                            GoodsOptionDO goodsOptionDo = new GoodsOptionDO(
                                    Integer.parseInt(goodsId), title.get(i)
                                            .replace(",", ";"), id.get(i).replace(
                                            ",", "_"));
                            j = j - goodsOptionFacade.insertOptionPo(goodsOptionDo);
                        }
                        if (j == 1) {
                        	//操作日志
        					AccountBO account = sessionFacade.checkLoginAccountSession(request);
        					RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "添加成功", RecordsDO.RECORDTYPE_MANAGER_ADD,
        						RecordBase.DEALTYPE_MANAGER, account.getAccountId(), Integer.parseInt(goodsId), "生成产品配置组合", "");
        					recordFacade.insertRecordDO(recordDO);
        					
                            return ResponseUtil.successResultString("成功配置");
                        }
                        return ResponseUtil.showMSGResultString("部分更新失败");
                    }
                    return ResponseUtil.paramErrorResultString("参数错误");
                }
                return ResponseUtil.showMSGResultString("无权限");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.systemErrorResultString();
        }
    }

    @ResponseBody
    @RequestMapping(value = "goodsOption/DTPaging", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ApiOperation(value = "分页查询产品配置属性组合", httpMethod = "POST", notes = "", response = ResponseUtil.class)
    public Map<String, Object> goodsOptionDTPaging(
            @ApiParam(required = false, name = "iDisplayStart", value = "分页查询开始条数（DataTable 内置参数）") @RequestParam(value = "iDisplayStart", required = false) String iDisplayStart,
            @ApiParam(required = false, name = "iDisplayLength", value = "分页查询条数（DataTable 内置参数）") @RequestParam(value = "iDisplayLength", required = false) String iDisplayLength,
            @ApiParam(required = false, name = "goodsId", value = "产品id	") @RequestParam(value = "goodsId", required = false) String goodsId,
            HttpServletRequest request) {
        try {
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("goodsId", goodsId);
                param.put("deleted", 0);
                PageEntity pageEntity = new PageEntity(1, 100, param);
                pageEntity.setOrderColumn("update_time");
                /**
                 * 查询
                 */
                PageResult<GoodsOptionBO> pageResult = goodsOptionFacade.getPageOption(pageEntity);
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

    @ResponseBody
    @RequestMapping(value = "goodsOption/edit", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
    @ApiOperation(value = "配置属性组合编辑", httpMethod = "POST", notes = "", response = ResponseUtil.class)
    public String goodsOptionDel(
            @ApiParam(required = true, name = "goodsId", value = "产品id	") @RequestParam(value = "goodsId", required = false) String goodsId,
            @ApiParam(required = true, name = "optionId", value = "产品配置属性组合id	") @RequestParam(value = "optionId", required = false) String optionId,
            @ApiParam(required = true, name = "marketPrice", value = "价格	") @RequestParam(value = "marketPrice", required = false) String marketPrice,
            @ApiParam(required = true, name = "purchasePrice", value = "价格	") @RequestParam(value = "purchasePrice", required = false) String purchasePrice,
            @ApiParam(required = true, name = "stock", value = "库存	") @RequestParam(value = "stock", required = false) String stock,
            @ApiParam(required = true, name = "barCode", value = "条形码") @RequestParam(value = "barCode", required = false) String barCode,
            @ApiParam(required = true, name = "showStock", value = "显示库存	") @RequestParam(value = "showStock", required = false) String showStock,
            HttpServletRequest request) {
        try {
            	@SuppressWarnings("unchecked")
				List<Map<String, Object>> sess = (List<Map<String, Object>>) request
                        .getSession(true).getAttribute("menuSession");
                RoleBO rv = new RoleBO(sess, "产品设置修改");
                if ("1".equals(rv.getRoleFalg())) {
                	if (StringUtil.isNumber(goodsId)) {
                        if (marketPrice != null && stock != null) {
                        	if(StringUtil.isNULL(marketPrice)){
                        		return ResponseUtil.showMSGResultString("请输入价格");
                        	}
                        	if(StringUtil.isNULL(purchasePrice)){
                        		return ResponseUtil.showMSGResultString("请输入成本价");
                        	}
//                        	if(!StringUtil.isNumber(showStock)){
//                        		return ResponseUtil.paramErrorResultString("请输入显示库存");
//                        	}
                        	if(!StringUtil.isNumber(stock)){
                        		return ResponseUtil.showMSGResultString("请输入库存");
                        	}
                        	GoodsDO goodsPo = goodsFacade.getGoodsPoByPK(Integer.parseInt(goodsId));
                        	if(goodsPo!=null){
                        		if(goodsPo.getIntegralDeductible()>Double.parseDouble(marketPrice)){
                        			return ResponseUtil.showMSGResultString("价格不能小于积分价");
                        		}
                        	}
                            int i = goodsOptionFacade.updateAllOptionByGoodsId(goodsId, Double.parseDouble(marketPrice), Double.parseDouble(purchasePrice),
                                    Integer.parseInt(stock), Integer.parseInt(stock));
                            
                            if (i == 1) {
                            	
                            	//操作日志
                				AccountBO account = sessionFacade.checkLoginAccountSession(request);
                				RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "修改成功", RecordsDO.RECORDTYPE_MANAGER_UPDATE,
                					RecordBase.DEALTYPE_MANAGER, account.getAccountId(), Integer.parseInt(goodsId), "修改属性组合价格:"+marketPrice+",库存:"+stock, "");
                				recordFacade.insertRecordDO(recordDO);
                            	
                                return ResponseUtil.successResultString("yes");
                            } else if (i == -2) {
                                return ResponseUtil.showMSGResultString("库存总和超过产品真实库存");
                            } 
//                            else if(i == -3){
//                                return ResponseUtil
//                                        .errorResultString("显示库存总和超过产品真实库存");
//                            }
                        }
                    }
                    if (StringUtil.isNumber(optionId)) {
                    	
                        GoodsOptionDO goodsOptionDo = new GoodsOptionDO();
                        GoodsOptionDO optionPo = goodsOptionFacade.getOptionPoByPK(Integer.parseInt(optionId));
                        Integer goodsid = optionPo.getGoodsId();
                        if(StringUtil.isNULL(purchasePrice)){
                        	purchasePrice = Double.toString(optionPo.getPurchasePrice());
                    	}
                        GoodsDO goodsPo = goodsFacade.getGoodsPoByPK(goodsid);
                        Integer totalStock = goodsPo.getTotalStock();
                        List<GoodsOptionDO> list = goodsOptionFacade.getOptionPoByGoodsId(goodsid);
                        int size = list.size();
                        Integer optionStock = 0;
                        Integer optionShowStock = 0;
                        for (int i = 0; i < size; i++) {
                        	GoodsOptionDO goodsOptionPo = list.get(i);
                            if(goodsOptionPo.getId().equals(Integer.parseInt(optionId))){
                                continue;
                            }
                            optionStock += goodsOptionPo.getStock();
                            optionShowStock += goodsOptionPo.getShowStock();
                        }
                        optionStock += Integer.parseInt(stock);
                        if (optionStock > totalStock) {
                            return ResponseUtil.showMSGResultString("库存总和超过产品真实库存");
                        }
//                        if(optionShowStock > totalStock){
//                            return ResponseUtil.errorResultString("显示库存总和超过产品真实库存");
//                        }
                        goodsOptionDo.setId(Integer.parseInt(optionId));
                        if (marketPrice != null) {
                        	if(goodsPo.getIntegralDeductible()>Double.parseDouble(marketPrice)){
                    			return ResponseUtil.showMSGResultString("价格不能小于积分价");
                    		}
                            goodsOptionDo.setMarketPrice(Double.parseDouble(marketPrice));
                        }
                        if (StringUtil.isNumber(stock)) {
                            goodsOptionDo.setStock(Integer.parseInt(stock));
                            goodsOptionDo.setShowStock(Integer.parseInt(stock));
                        }
//                        if (StringUtil.isNumber(showStock)){
//                            goodsOptionDo.setShowStock(Integer.parseInt(showStock));
//                        }
                        goodsOptionDo.setPurchasePrice(Double.parseDouble(purchasePrice));
                        goodsOptionDo.setBarCode(barCode);
//                        if(!StringUtil.isNULL(barCode)){
//                        	Map<String, Object> param = new HashMap<String, Object>();
//                        	param.put("barCode", barCode);
//                        	List<GoodsOptionDO> optionList = goodsOptionFacade.getGoodsOptionByPatam(param);
//                        	if(optionList == null || optionList.size() == 0){
//                        		goodsOptionDo.setBarCode(barCode);
//                        	}else{
//                        		return ResponseUtil.paramErrorResultString("已存在相同的条形码");
//                        	}
//                        }
                        int i = goodsOptionFacade.updateGoodsOptionPo(goodsOptionDo);
                        if (i == 1) {
                        	//操作日志
            				AccountBO account = sessionFacade.checkLoginAccountSession(request);
            				RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "修改成功", RecordsDO.RECORDTYPE_MANAGER_UPDATE,
            					RecordBase.DEALTYPE_MANAGER, account.getAccountId(), Integer.parseInt(optionId), "修改属性组合价格:"+marketPrice+",库存:"+stock, "");
            				recordFacade.insertRecordDO(recordDO);
            				
                            return ResponseUtil.successResultString("yes");
                        }
                    }
                    return ResponseUtil.paramErrorResultString("参数错误");
                }
                return ResponseUtil.showMSGResultString("无权限");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.systemErrorResultString();
        }
    }

    @ResponseBody
    @RequestMapping(value = "goodsOption/editImg", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
    @ApiOperation(value = "配置属性组合图片修改", httpMethod = "POST", notes = "", response = ResponseUtil.class)
    public String goodsOptionImgEdit(
            @ApiParam(required = true, name = "optionId", value = "产品配置属性组合id	") @RequestParam(value = "optionId") String optionId,
            @ApiParam(required = true, name = "img", value = "图片") @RequestParam(value = "img") String img,
            HttpServletRequest request) {
        try {
            	@SuppressWarnings("unchecked")
				List<Map<String, Object>> sess = (List<Map<String, Object>>) request
                        .getSession(true).getAttribute("menuSession");
                RoleBO rv = new RoleBO(sess, "产品设置修改");
                if ("1".equals(rv.getRoleFalg())) {
                	if (StringUtil.isNumber(optionId)) {
                        if (StringUtil.isNULL(img)) {
                            return ResponseUtil.showMSGResultString("请上传图片");
                        }
                        GoodsOptionDO goodsOptionDo = new GoodsOptionDO();
                        goodsOptionDo.setId(Integer.parseInt(optionId));
                        goodsOptionDo.setThumb(img);
                        int i = goodsOptionFacade
                                .updateGoodsOptionPo(goodsOptionDo);
                        if (i == 1) {
                        	//操作日志
        					AccountBO account = sessionFacade.checkLoginAccountSession(request);
        					RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "修改成功", RecordsDO.RECORDTYPE_MANAGER_UPDATE,
        						RecordBase.DEALTYPE_MANAGER, account.getAccountId(), Integer.parseInt(optionId), "修改配置属性组合图片", "");
        					recordFacade.insertRecordDO(recordDO);
        					
                            return ResponseUtil.successResultString("yes");
                        }
                    }
                    return ResponseUtil.paramErrorResultString("参数");
                }
                return ResponseUtil.showMSGResultString("无权限");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.systemErrorResultString();
        }
    }

    @ResponseBody
    @RequestMapping(value = "goodsOption/detail", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
    @ApiOperation(value = "配置属性组合详情", httpMethod = "POST", notes = "", response = ResponseUtil.class)
    public String goodsOptionDetail(
            @ApiParam(required = true, name = "optionId", value = "产品配置属性组合id  ") @RequestParam(value = "optionId") String optionId,
            HttpServletRequest request) {
        try {
                GoodsOptionBO vo = goodsOptionFacade.getOptionVoByPK(Integer.parseInt(optionId));
                return ResponseUtil.successResultString(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.systemErrorResultString();
        }
    }
}
