package com.zd.aoding.outsourcing.web.controllerApi.management;

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
import com.zd.aoding.common.StringDate.StringUtil;
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.RoleBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.CategoryGoodsSpecRelationDO;
import com.zd.aoding.outsourcing.weChat.api.facade.CategoryGoodsSpecRelationFacade;

@Api(value = "", description = "分类")
@Controller
@RequestMapping("ad/manager")
public class CategoryGoodsSpecRelationManagerController {
	@Autowired
	private CategoryGoodsSpecRelationFacade categoryGoodsSpecRelationFacade;
	@Autowired
	private HttpSession session;

	/**
	 * @Title: addCategory 
	 * @Description: 添加分类
	 * @param request
	 * @return
	 * @return: String
	 */
	@ResponseBody
	@RequestMapping(value = "categoryGoodsSpecRelation/addOrUpdate", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "添加分类", httpMethod = "POST", notes = "添加分类", response = ResponseUtil.class)
	public String addCategory(
			@ApiParam(name = "categoryId", value = "分类id", required = true) @RequestParam(value = "categoryId", required = true) 
			String categoryId,
			@ApiParam(name = "specs", value = "spec拼接,;分割,^判断是更改还是添加", required = true) @RequestParam(value = "specs", required = true) 
			String specs,
			HttpServletRequest request) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "分类管理添加");
			if(rv.getRoleFalg().equals("1")) {
				String[] specArray = specs.split(";");
				int len = specArray.length;
				for(int i = 0; i < len; i++){
					CategoryGoodsSpecRelationDO categoryGoodsSpecRelationPo = new CategoryGoodsSpecRelationDO();
					String spec = specArray[i];
					String[] relation = spec.split("!");
					int relationLenth = relation.length;
					categoryGoodsSpecRelationPo.setSpecName(relation[0]);
					categoryGoodsSpecRelationPo.setCategoryId(Integer.parseInt(categoryId));
					if(relationLenth>1){
						categoryGoodsSpecRelationPo.setId(Integer.parseInt(relation[1]));
						categoryGoodsSpecRelationFacade.updateCategoryGoodsSpecRelationPo(categoryGoodsSpecRelationPo);
					}else{
						categoryGoodsSpecRelationFacade.insertCategoryGoodsSpecRelationPo(categoryGoodsSpecRelationPo);
					}
				}
				return ResponseUtil.successResultString("操作成功");
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
	@RequestMapping(value = "categoryGoodsSpecRelation/delete", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "添加分类", httpMethod = "POST", notes = "添加分类", response = ResponseUtil.class)
	public String deleteCategory(
			@ApiParam(name = "relationId", value = "关联id", required = true) @RequestParam(value = "relationId", required = true) 
			String relationId,
			HttpServletRequest request) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "分类管理添加");
			if(rv.getRoleFalg().equals("1")) {
				if(!StringUtil.isNumber(relationId)){
					return ResponseUtil.paramErrorResultString("");
				}
				CategoryGoodsSpecRelationDO categoryGoodsSpecRelationPo = new CategoryGoodsSpecRelationDO();
				categoryGoodsSpecRelationPo.setId(Integer.parseInt(relationId));
				categoryGoodsSpecRelationPo.setDeleted(1);
				int i = categoryGoodsSpecRelationFacade.updateCategoryGoodsSpecRelationPo(categoryGoodsSpecRelationPo);
				if(i == 1){
					return ResponseUtil.successResultString("操作完成");
				}
				return ResponseUtil.showMSGResultString("操作失败");
			}else{
				return ResponseUtil.showMSGResultString("暂无权限");
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	
}
