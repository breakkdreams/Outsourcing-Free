package com.zd.aoding.outsourcing.web.controllerApi.management;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.GoodsCommentBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.RoleBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsCommentDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.RecordsDO;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsCommentFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.RecordFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SessionFacade;

@Api(value = "", description = "产品评价")
@Controller
@RequestMapping("ad/manager")
public class GoodsCommentManagerController {
	@Autowired
	private GoodsCommentFacade goodsCommentFacade;
	@Autowired
	private HttpSession session;
	@Autowired 
	private RecordFacade recordFacade;
	@Autowired
	private SessionFacade sessionFacade;

	@ResponseBody
	@RequestMapping(value = "goodsComment/edit", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "回复产品评价", httpMethod = "POST", notes = "修改产品模块", response = ResponseUtil.class)
	public String editGoodsComment(
			@ApiParam(name = "goodsCommentId", value = "id", required = false) @RequestParam(value = "goodsCommentId", required = true)
					String goodsCommentId,
			@ApiParam(name = "replyContent", value = "回复内容", required = false) @RequestParam(value = "replyContent", required = false)
					String replyContent,
			HttpServletRequest request) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "商品评价管理修改");
			if(rv.getRoleFalg().equals("1")) {
				if (!StringUtil.isNumber(goodsCommentId)) {
					return ResponseUtil.showMSGResultString("参数错误");
				}
				if(StringUtil.isNULL(replyContent)){
					return ResponseUtil.showMSGResultString("回复内容不能为空");
				}
				GoodsCommentDO goodsCommentPo = goodsCommentFacade.getGoodsCommentPoByPK(goodsCommentId);
				if (goodsCommentPo == null) {
					return ResponseUtil.showMSGResultString("参数错误");
				}
				goodsCommentPo.setReplyContent(replyContent);
				goodsCommentPo.setReplyState(GoodsCommentDO.REPLY_STATE_YES);

				int i = goodsCommentFacade.updateGoodsCommentPo(goodsCommentPo);
				if (i == 1) {
					//操作日志
					AccountBO account = sessionFacade.checkLoginAccountSession(request);
					RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "回复商品评价成功", RecordsDO.RECORDTYPE_MANAGER_UPDATE,
						RecordBase.DEALTYPE_MANAGER, account.getAccountId(),Integer.parseInt(goodsCommentId), "回复商品评价", "");
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
	@RequestMapping(value = "goodsComment/delete", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "删除评价", httpMethod = "POST", notes = "删除评价", response = ResponseUtil.class)
	public String deletedGoodsComment(
			@ApiParam(required = true, name = "goodsCommentId", value = "id") @RequestParam(value = "goodsCommentId", required = true)
					String goodsCommentId,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "商品评价管理删除");
			if(rv.getRoleFalg().equals("1")) {
				if (StringUtil.isNumber(goodsCommentId)) {
					GoodsCommentDO goodsCommentPo = goodsCommentFacade.getGoodsCommentPoByPK(goodsCommentId);
					if (goodsCommentPo == null) {
						return ResponseUtil.showMSGResultString("参数错误");
					}
					goodsCommentPo.setDeleted(1);
					int i = goodsCommentFacade.updateGoodsCommentPo(goodsCommentPo);
					if (i == 1) {
						//操作日志
						AccountBO account = sessionFacade.checkLoginAccountSession(request);
						RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "删除商品评价成功", RecordsDO.RECORDTYPE_MANAGER_DELETED,
							RecordBase.DEALTYPE_MANAGER, account.getAccountId(),Integer.parseInt(goodsCommentId), "删除商品评价", "");
						recordFacade.insertRecordDO(recordDO);
						
						return ResponseUtil.successResultString("删除成功");
					}
					return ResponseUtil.showMSGResultString("删除失败");
				}
				return ResponseUtil.showMSGResultString("缺少需要删除的goodsCommentId=" + goodsCommentId);
			}else{
				return ResponseUtil.showMSGResultString("暂无权限");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}

	@ResponseBody
	@RequestMapping(value = "goodsComment/DTPaging", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ApiOperation(value = "查看商品评价", httpMethod = "POST", notes = "查看商品评价", response = ResponseUtil.class)
	public Map<String, Object> getGoodsComment(
			@ApiParam(required = false, name = "iDisplayStart", value = "分页查询开始条数（DataTable 内置参数）") @RequestParam(value = "iDisplayStart", required = false) String iDisplayStart,
			@ApiParam(required = false, name = "iDisplayLength", value = "分页查询条数（DataTable 内置参数）") @RequestParam(value = "iDisplayLength", required = false) String iDisplayLength,
			HttpServletRequest request) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "商品评价管理查看");
			if(rv.getRoleFalg().equals("1")) {
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
				pageEntity.setParams(param);
				/**
				 * 查询
				 */
				PageResult<GoodsCommentBO> pageResult = goodsCommentFacade.getPageGoodsCommentVo(pageEntity);
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
	@RequestMapping(value = "goodsComment/detail", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "查看单个商品评价", httpMethod = "POST", notes = "查看单个商品评价", response = ResponseUtil.class)
	public String getMapper(
			@ApiParam(required = false, name = "goodsCommentId", value = "id") @RequestParam(value = "goodsCommentId", required = false)
					String goodsCommentId,
			HttpServletRequest request) {
		try {
			if (!StringUtil.isNULL(goodsCommentId) && StringUtil.isNumber(goodsCommentId)) {
				GoodsCommentDO goodsCommentPo = goodsCommentFacade.getGoodsCommentPoByPK(goodsCommentId);
				if (goodsCommentPo != null) {
					//操作日志
					AccountBO account = sessionFacade.checkLoginAccountSession(request);
					RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "查看商品评价详情成功", RecordsDO.RECORDTYPE_MANAGER_VIEW,
						RecordBase.DEALTYPE_MANAGER, account.getAccountId(),Integer.parseInt(goodsCommentId), "查看商品评价详情", "");
					recordFacade.insertRecordDO(recordDO);
					
					return ResponseUtil.successResultString(goodsCommentPo);
				}
				return ResponseUtil.showMSGResultString("参数错误");
			}
			return ResponseUtil.paramErrorResultString("分类id为空或格式不正确");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
}
