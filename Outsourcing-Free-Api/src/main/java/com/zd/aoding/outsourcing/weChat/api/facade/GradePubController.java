package com.zd.aoding.outsourcing.weChat.api.facade;
//package com.zd.api.controller.pub;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.wordnik.swagger.annotations.Api;
//import com.wordnik.swagger.annotations.ApiOperation;
//import com.wordnik.swagger.annotations.ApiParam;
//import com.wordnik.swagger.annotations.ApiResponse;
//import com.wordnik.swagger.annotations.ApiResponses;
//import com.zd.common.StringDate.StringUtil;
//import com.zd.common.page.PageEntity;
//import com.zd.common.page.PageResult;
//import com.zd.common.response.ResponseCodeEnum;
//import com.zd.common.response.ResponseUtil;
//import com.zd.model.po.GradePo;
//
//@Api(value = "", description = "等级制度管理")
//@Controller
//@RequestMapping("pub")
//public class GradePubController {
//	@Autowired
//	private GradeFacade gradeFacade;
//
//	
//	@ResponseBody
//	@RequestMapping(value = "grade/paging", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
//	@ApiOperation(value = "分页查询", httpMethod = "POST", notes = "", response = ResponseUtil.class)
//	public String paging(
//			@ApiParam(required = false, name = "pageNum", value = "当前第几页") @RequestParam(value = "pageNum", required = false) String pageNum,
//			@ApiParam(required = false, name = "pageSize", value = "分页条数") @RequestParam(value = "pageSize", required = false) String pageSize,
//			HttpServletRequest request) {
//		try {
//			// 初始化列表
//			PageEntity pageEntity = new PageEntity();
//			if (StringUtil.isNumber(pageNum)) {
//				pageEntity.setPage(Integer.parseInt(pageNum));
//			}
//			if (StringUtil.isNumber(pageSize)) {
//				pageEntity.setSize(Integer.parseInt(pageSize));
//			}
//			/**
//			 * 排序
//			 */
//			pageEntity.setOrderColumn("update_time");
//			/**
//			 * 查询条件参数
//			 */
//			Map<String, Object> param = new HashMap<String, Object>();
//			param.put("deleted", 0);
//			pageEntity.setParams(param);
//			PageResult<GradePo> pageResult = gradeFacade.getPageGradePo(pageEntity);
//			return ResponseUtil.successResultString(pageResult);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ResponseUtil.systemErrorResultString();
//		}
//	}
//
//	@ResponseBody
//	@RequestMapping(value = "grade/detail", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
//	@ApiOperation(value = "查看单个等级制度", httpMethod = "POST", notes = "根据等级制度Id查看", response = ResponseUtil.class)
//	@ApiResponses({ @ApiResponse(code = 1, message = "实体类注释", response = GradePo.class) })
//	public String getMapper(
//			@ApiParam(required = false, name = "gradeId", value = "等级制度Id") @RequestParam(value = "gradeId", required = false) String gradeId,
//			HttpServletRequest request) {
//		try {
//			if (StringUtil.isNumber(gradeId)) {
//				GradePo gradePo = gradeFacade.getGradePoById(Integer.parseInt(gradeId));
//				if (gradePo != null) {
//					return ResponseUtil.successResultString(gradePo);
//				}
//				return ResponseUtil.resultString("空", ResponseCodeEnum.PARAM_ERROR);
//			}
//			return ResponseUtil.resultString("参数错误", ResponseCodeEnum.PARAM_ERROR);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ResponseUtil.systemErrorResultString();
//		}
//	}
//}
