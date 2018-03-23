package com.zd.aoding.outsourcing.web.controllerApi.management;

import java.util.ArrayList;
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
import com.zd.aoding.common.StringDate.StringUtil;
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.AccountBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.RoleBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.MenuAdminDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.RecordsDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.RoleDO;
import com.zd.aoding.outsourcing.weChat.api.facade.MenuAdminFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.RecordFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.RoleFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SessionFacade;

@Api(value = "", description = "角色管理")
@Controller
@RequestMapping("ad/manager")
public class RoleManagerController {
	@Autowired
	private RoleFacade roleFacade;
	@Autowired
	private SessionFacade sessionService;
	@Autowired
	private MenuAdminFacade menuFacade;
	@Autowired
	private HttpSession session;
	@Autowired 
	private RecordFacade recordFacade;
	@Autowired
	private SessionFacade sessionFacade;

	@ResponseBody
	@RequestMapping(value = "role/add", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "添加角色", httpMethod = "POST", notes = "添加角色", response = ResponseUtil.class)
	public String addRole(
			@ApiParam(name = "roleName", value = "角色名称", required = true) @RequestParam(value = "roleName", required = false)
			String roleName,
			@ApiParam(name = "menuList", value = "菜单列表", required = true) @RequestParam(value = "menuList", required = false)
			String menuList,
			/*@ApiParam(name = "ownerId", value = "ownerId", required = true) @RequestParam(value = "ownerId", required = false)
			String ownerId,*/
			String isSystemMsg,
			HttpServletRequest request) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "角色管理添加");
			if(rv.getRoleFalg().equals("1")) {
				if(StringUtil.isNULL(roleName)){
					return ResponseUtil.showMSGResultString("角色名称错误");
				}
				RoleDO rolePo = new RoleDO(RoleDO.TYPE_MANAGE,roleName,menuList, 0);
				int i = roleFacade.insertRoles(rolePo);
				if (i == 1) {
					//操作日志
    				AccountBO account = sessionFacade.checkLoginAccountSession(request);
    				RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "添加角色成功", RecordsDO.RECORDTYPE_MANAGER_ADD,
    					RecordBase.DEALTYPE_MANAGER, account.getAccountId(), rolePo.getRoleId(), "添加角色", "");
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
	@RequestMapping(value = "role/edit", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "编辑修改角色", httpMethod = "POST", notes = "修改角色", response = ResponseUtil.class)
	public String editRole(
			@ApiParam(name = "type", value = "平台类型", required = true) @RequestParam(value = "type", required = false)
			String type,
			@ApiParam(name = "roleName", value = "角色名称", required = true) @RequestParam(value = "roleName", required = false)
			String roleName,
			@ApiParam(name = "menuList", value = "菜单列表", required = true) @RequestParam(value = "menuList", required = false)
			String menuList,
			@ApiParam(name = "roleId", value = "id", required = true) @RequestParam(value = "roleId", required = false)
			String roleId,
			HttpServletRequest request) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "角色管理修改");
			if(rv.getRoleFalg().equals("1")) {
				if (!StringUtil.isNumber(roleId)) {
					return ResponseUtil.paramErrorResultString("参数错误");
				}
				RoleDO rolePo = roleFacade.getPoByPK(Integer.parseInt(roleId));
				if (rolePo == null) {
					return ResponseUtil.errorResultString("参数错误");
				}
				if(!StringUtil.isNULL(type) && StringUtil.isNumber(type)){
					rolePo.setType(Integer.parseInt(type));
				}
				if(!StringUtil.isNULL(roleName)){
					rolePo.setRoleName(roleName);
				}
				if(!StringUtil.isNULL(menuList)){
					rolePo.setMenuList(menuList);
				}

				int i = roleFacade.updateRole(rolePo);
				if (i == 1) {
					//操作日志
    				AccountBO account = sessionFacade.checkLoginAccountSession(request);
    				RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "修改角色成功", RecordsDO.RECORDTYPE_MANAGER_UPDATE,
    					RecordBase.DEALTYPE_MANAGER, account.getAccountId(), Integer.parseInt(roleId), "修改角色", "");
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
	@RequestMapping(value = "role/delete", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "删除角色", httpMethod = "POST", notes = "根据角色id删除", response = ResponseUtil.class)
	public String deletedRole(
			@ApiParam(required = true, name = "roleId", value = "roleId") @RequestParam(value = "roleId", required = true)
					String roleId,
			HttpServletRequest request) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "角色管理删除");
			if(rv.getRoleFalg().equals("1")) {
				if (!StringUtil.isNULL(roleId) && StringUtil.isNumber(roleId)) {
					RoleDO rolePo = roleFacade.getPoByPK(Integer.parseInt(roleId));
					if (rolePo == null) {
						return ResponseUtil.paramErrorResultString("参数错误");
					}
					rolePo.setDeleted(1);
					int i = roleFacade.updateRole(rolePo);
					if (i == 1) {
						//操作日志
	    				AccountBO account = sessionFacade.checkLoginAccountSession(request);
	    				RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "删除角色成功", RecordsDO.RECORDTYPE_MANAGER_DELETED,
	    					RecordBase.DEALTYPE_MANAGER, account.getAccountId(), Integer.parseInt(roleId), "删除角色", "");
	    				recordFacade.insertRecordDO(recordDO);
	    				
						return ResponseUtil.successResultString("删除成功");
					}
					return ResponseUtil.showMSGResultString("删除失败");
				}
				return ResponseUtil.paramErrorResultString("缺少需要删除的roleId=" + roleId);
			}else{
				return ResponseUtil.showMSGResultString("暂无权限");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	
	/**
	 * @Title: getRolePaging
	 * @Description: 条件查询
	 * @param request
	 * @return
	 * @return: String
	 */
	@ResponseBody
	@RequestMapping(value = "role/DTPaging", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ApiOperation(value = "查看角色列表", httpMethod = "POST", notes = "查看角色列表", response = ResponseUtil.class)
	public Map<String, Object> getRolePaging(
			@ApiParam(required = false, name = "iDisplayStart", value = "分页查询开始条数（DataTable 内置参数）") @RequestParam(value = "iDisplayStart", required = false) String iDisplayStart,
			@ApiParam(required = false, name = "iDisplayLength", value = "分页查询条数（DataTable 内置参数）") @RequestParam(value = "iDisplayLength", required = false) String iDisplayLength,
			HttpServletRequest request) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "角色管理查看");
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
				param.put("type", RoleDO.TYPE_MANAGE);
				if(!StringUtil.isNULL(sSearch)){
//					param.put("title", sSearch);
				}
				pageEntity.setParams(param);
				/**
				 * 查询
				 */
				PageResult<RoleBO> pageResult = roleFacade.getPageRoleVo(pageEntity);
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
	@RequestMapping(value = "role/allList", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded;charset=utf-8")
	@ApiOperation(value = "查询全部角色", httpMethod = "POST", notes = "查询全部角色", response = ResponseUtil.class)
	public String getAllList(
//			@ApiParam(required = false, name = "type", value = "类型") @RequestParam(value = "type", required = false) String type,
			HttpServletRequest request) {
		try {
			/**
			 * 查询条件参数
			 */
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("deleted", 0);
			/**
			 * 查询
			 */
			List<RoleDO> roleList = roleFacade.getList(param);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			String jsonString = JSON.toJSONString(roleList, true);
			JSONArray jsonArray = JSON.parseArray(jsonString);
			resultMap.put("aaData", jsonArray);
			return ResponseUtil.successResultString(roleList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	@ResponseBody
	@RequestMapping(value = "menu/allList", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded;charset=utf-8")
	@ApiOperation(value = "查询全部资讯", httpMethod = "POST", notes = "查询全部资讯", response = ResponseUtil.class)
	public String getMenuAllList(
//			@ApiParam(required = false, name = "type", value = "类型") @RequestParam(value = "type", required = false) String type,
			HttpServletRequest request) {
		try {
			String menuList = request.getParameter("menuList");
			String[] menuListStr = {};
			if(!StringUtil.isNULL(menuList)){
				menuListStr = menuList.split(",");
			}
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("deleted", 0);
			List<MenuAdminDO> list = menuFacade.getList(map);
			List<Map<String,Object>> resutList = new ArrayList<Map<String,Object>>();
			if(list!=null && list.size()>0){
				for (int i = 0; i <list.size() ; i++) {
					Map<String,Object> mapvalue = new HashMap<String,Object>();

					if(menuListStr.length>0){
						for (int j = 0 ; j <menuListStr.length ; j++ ) {
							if((list.get(i).getPid()+"").equals(menuListStr[j])){
								mapvalue.put("checked",true);
							}
						}
					}

					mapvalue.put("name",list.get(i).getpName()+"");
					mapvalue.put("pId",list.get(i).getErId());
					mapvalue.put("id",list.get(i).getPid());
					mapvalue.put("open",true);
					resutList.add(mapvalue);
				}
			}
			String jsonString = JSON.toJSONString(resutList, true);
			JSONArray jsonArray = JSON.parseArray(jsonString);
			return ResponseUtil.successResultString(jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}

	@ResponseBody
	@RequestMapping(value = "role/detail", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "查看单个角色", httpMethod = "POST", notes = "查看单个角色", response = ResponseUtil.class)
	public String getMapper(
			@ApiParam(required = false, name = "roleId", value = "roleId") @RequestParam(value = "roleId", required = false) String roleId,
			HttpServletRequest request) {
		try {
			if (!StringUtil.isNULL(roleId) && StringUtil.isNumber(roleId)) {
				RoleDO rolePo = roleFacade.getPoByPK(Integer.parseInt(roleId));
				if (rolePo != null) {
					//操作日志
    				AccountBO account = sessionFacade.checkLoginAccountSession(request);
    				RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "查看角色详情成功", RecordsDO.RECORDTYPE_MANAGER_VIEW,
    					RecordBase.DEALTYPE_MANAGER, account.getAccountId(), Integer.parseInt(roleId), "查看角色详情", "");
    				recordFacade.insertRecordDO(recordDO);
    				
					return ResponseUtil.successResultString(rolePo);
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
