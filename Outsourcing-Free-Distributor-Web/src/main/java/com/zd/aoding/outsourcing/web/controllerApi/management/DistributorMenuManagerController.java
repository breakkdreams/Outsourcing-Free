package com.zd.aoding.outsourcing.web.controllerApi.management;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.DistributorMenuBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.DistributorMenuDO;
import com.zd.aoding.outsourcing.weChat.api.facade.DistributorMenuFacade;
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
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.MenuBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.RoleBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.MenuAdminDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.RecordsDO;
import com.zd.aoding.outsourcing.weChat.api.facade.MenuAdminFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.RecordFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SessionFacade;

@Api(value = "", description = "菜单管理")
@Controller
@RequestMapping("ad/manager")
public class DistributorMenuManagerController {
	@Autowired
	private DistributorMenuFacade menuFacade;
	@Autowired
	private HttpSession session;
	@Autowired 
	private RecordFacade recordFacade;
	@Autowired
	private SessionFacade sessionFacade;

	@ResponseBody
	@RequestMapping(value = "menu/add", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "添加菜单", httpMethod = "POST", notes = "添加菜单", response = ResponseUtil.class)
	public String addMenu(
			@ApiParam(name = "pName", value = "名称", required = true) @RequestParam(value = "pName", required = false)
			String pName,
			@ApiParam(name = "erId", value ="二级菜单", required = true) @RequestParam(value = "erId", required = false)
			String erId,
			@ApiParam(name = "url", value = "链接", required = true) @RequestParam(value = "url", required = false)
			String url,
			@ApiParam(name = "falg", value = "级别标识", required = true) @RequestParam(value = "falg", required = false)
			String falg,
			@ApiParam(name = "tubiao", value = "图标", required = true) @RequestParam(value = "tubiao", required = false)
			String tubiao,
			HttpServletRequest request) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "菜单管理添加");
			if(rv.getRoleFalg().equals("1")) {
				if(!StringUtil.isNumber(erId)){
					return ResponseUtil.showMSGResultString("类型错误");
				}
				if(StringUtil.isNULL(pName)){
					return ResponseUtil.showMSGResultString("名称错误");
				}
				if (Integer.parseInt(erId)>0){
					DistributorMenuDO menuAdminPo1 = menuFacade.getPoByPK(Integer.parseInt(erId));
					if(menuAdminPo1!=null){
						falg = (menuAdminPo1.getFalg()+1)+"";
					}
				}else{
					falg = 1+"";
				}
				if(StringUtil.isNULL(url)){
					url = "#";
				}
				DistributorMenuDO menuAdminPo = new DistributorMenuDO(pName, Integer.parseInt(erId), url, Integer.parseInt(falg), tubiao);
				int i = menuFacade.insertMenus(menuAdminPo);
				if (i == 1) {
					//操作日志
    				AccountBO account = sessionFacade.checkLoginAccountSession(request);
    				RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "添加菜单成功", RecordsDO.RECORDTYPE_MANAGER_ADD,
    					RecordBase.DEALTYPE_MANAGER, account.getAccountId(), menuAdminPo.getPid(), "添加菜单", "");
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
	@RequestMapping(value = "menu/edit", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "编辑修改菜单", httpMethod = "POST", notes = "修改菜单", response = ResponseUtil.class)
	public String editMenu(
			@ApiParam(name = "pName", value = "名称", required = true) @RequestParam(value = "pName", required = false)
					String pName,
			@ApiParam(name = "erId", value ="二级菜单", required = true) @RequestParam(value = "erId", required = false)
					String erId,
			@ApiParam(name = "url", value = "链接", required = true) @RequestParam(value = "url", required = false)
					String url,
			@ApiParam(name = "falg", value = "级别标识", required = true) @RequestParam(value = "falg", required = false)
					String falg,
			@ApiParam(name = "tubiao", value = "图标", required = true) @RequestParam(value = "tubiao", required = false)
					String tubiao,
			@ApiParam(name = "pId", value = "id", required = true) @RequestParam(value = "pId", required = false)
					String pId,
			HttpServletRequest request) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "菜单管理修改");
			if(rv.getRoleFalg().equals("1")) {
				if (!StringUtil.isNumber(pId)) {
					return ResponseUtil.paramErrorResultString("参数错误");
				}
				DistributorMenuDO menuAdminPo = menuFacade.getPoByPK(Integer.parseInt(pId));
				if (menuAdminPo == null) {
					return ResponseUtil.paramErrorResultString("参数错误");
				}
				if (Integer.parseInt(erId)>0){
					DistributorMenuDO menuAdminPo1 = menuFacade.getPoByPK(Integer.parseInt(erId));
					if(menuAdminPo1!=null){
						falg = (menuAdminPo1.getFalg()+1)+"";
					}
				}else{
					falg = 1+"";
				}
				if(!StringUtil.isNULL(erId) && StringUtil.isNumber(erId)){
					menuAdminPo.setErId(Integer.parseInt(erId));
				}
				if(!StringUtil.isNULL(pName)){
					menuAdminPo.setpName(pName);
				}
				if(!StringUtil.isNULL(falg)){
					menuAdminPo.setFalg(Integer.parseInt(falg));
				}
				if(StringUtil.isNULL(url)){
					url = "#";
				}
				menuAdminPo.setUrl(url);

				int i = menuFacade.updateMenu(menuAdminPo);
				if (i == 1) {
					//操作日志
    				AccountBO account = sessionFacade.checkLoginAccountSession(request);
    				RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "修改菜单成功", RecordsDO.RECORDTYPE_MANAGER_UPDATE,
    					RecordBase.DEALTYPE_MANAGER, account.getAccountId(), Integer.parseInt(pId), "修改菜单", "");
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
	@RequestMapping(value = "menu/delete", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "删除菜单", httpMethod = "POST", notes = "根据菜单id删除", response = ResponseUtil.class)
	public String deletedMenu(
			@ApiParam(required = true, name = "pId", value = "pId") @RequestParam(value = "pId", required = true)
					String pId,
			HttpServletRequest request) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "菜单管理删除");
			if(rv.getRoleFalg().equals("1")) {
				if (!StringUtil.isNULL(pId) && StringUtil.isNumber(pId)) {
					DistributorMenuDO menuAdminPo = menuFacade.getPoByPK(Integer.parseInt(pId));
					if (menuAdminPo == null) {
						return ResponseUtil.paramErrorResultString("参数错误");
					}
					menuAdminPo.setDeleted(1);
					int i = menuFacade.updateMenu(menuAdminPo);
					if (i == 1) {
						//操作日志
	    				AccountBO account = sessionFacade.checkLoginAccountSession(request);
	    				RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "删除菜单成功", RecordsDO.RECORDTYPE_MANAGER_DELETED,
	    					RecordBase.DEALTYPE_MANAGER, account.getAccountId(), Integer.parseInt(pId), "删除菜单", "");
	    				recordFacade.insertRecordDO(recordDO);
	    				
						return ResponseUtil.successResultString("删除成功");
					}
					return ResponseUtil.showMSGResultString("删除失败");
				}
				return ResponseUtil.paramErrorResultString("缺少需要删除的pId=" + pId);
			}else{
				return ResponseUtil.showMSGResultString("暂无权限");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "menu/DTPaging", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ApiOperation(value = "查看菜单列表", httpMethod = "POST", notes = "查看菜单列表", response = ResponseUtil.class)
	public Map<String, Object> getMenuPaging(
			@ApiParam(required = false, name = "iDisplayStart", value = "分页查询开始条数（DataTable 内置参数）") @RequestParam(value = "iDisplayStart", required = false) String iDisplayStart,
			@ApiParam(required = false, name = "iDisplayLength", value = "分页查询条数（DataTable 内置参数）") @RequestParam(value = "iDisplayLength", required = false) String iDisplayLength,
			HttpServletRequest request) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "菜单管理查看");
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
//				pageEntity.setOrderColumn("falg");
				/**
				 * 查询条件参数
				 */
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("deleted", 0);
				if(!StringUtil.isNULL(sSearch)){
					param.put("nameLike", sSearch);
				}
				pageEntity.setParams(param);
				/**
				 * 查询
				 */
				PageResult<DistributorMenuBO> pageResult = menuFacade.getPageMenuVo(pageEntity);
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
}
