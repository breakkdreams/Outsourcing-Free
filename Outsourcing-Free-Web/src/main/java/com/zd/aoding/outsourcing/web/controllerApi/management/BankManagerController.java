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
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.zd.aoding.common.StringDate.StringUtil;
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.BankBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.RoleBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.BankDO;
import com.zd.aoding.outsourcing.weChat.api.facade.BankFacade;

@Api(value = "", description = "banner图管理")
@Controller
@RequestMapping("ad/manager")
public class BankManagerController {
	@Autowired
	private BankFacade bankFacade;

	@ResponseBody
	@RequestMapping(value = "bank/DTPaging", method = RequestMethod.POST)
	@ApiOperation(value = "银行分页查询轮播图", httpMethod = "POST", response = ModelAndView.class)
	public Map<String, Object> bankDTPaging(
			@ApiParam(required = false, name = "iDisplayStart", value = "当前页数") @RequestParam(value = "iDisplayStart", required = false) String iDisplayStart,
			@ApiParam(required = false, name = "iDisplayLength", value = "分页条数") @RequestParam(value = "iDisplayLength", required = false) String iDisplayLength,
			HttpServletRequest request) {
		try {
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
			pageEntity.setOrderColumn("id");

			/**
			 * 查询条件参数
			 */
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("deleted", 0);
			pageEntity.setParams(param);
			/**
			 * 查询
			 */
			PageResult<BankBO> pageResult = bankFacade.getPageBankBO(pageEntity);
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
	@RequestMapping(value = "bank/edit", method = RequestMethod.POST)
	@ApiOperation(value = "客户修改银行", httpMethod = "POST", response = ModelAndView.class)
	public String updateBannner(
			@ApiParam(required = false, name = "bankId", value = "id") @RequestParam(value = "bankId", required = false) String bankId,
			@ApiParam(required = false, name = "bankImgUrl", value = "logo") @RequestParam(value = "bankImgUrl", required = false) String bankImgUrl,
			@ApiParam(required = false, name = "bankName", value = "银行名称") @RequestParam(value = "bankName", required = false) String bankName,
			@ApiParam(required = false, name = "color", value = "颜色") @RequestParam(value = "color", required = false) String color,
			@ApiParam(required = false, name = "show", value = "显示隐藏") @RequestParam(value = "show", required = false) String show,
			HttpServletRequest request) {
		try {
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> sess = (List<Map<String, Object>>) request.getSession(true).getAttribute("menuSession");
			RoleBO rv = new RoleBO(sess, "银行管理修改");
			if ("1".equals(rv.getRoleFalg())) {
				if (!StringUtil.isNumber(bankId)) {
					return ResponseUtil.paramErrorResultString("参数错误");
				}
				BankDO bPo = bankFacade.getBankDOByPK(Integer.parseInt(bankId));
				if (!StringUtil.isNULL(bankImgUrl)) {
					bPo.setBankImgUrl(bankImgUrl);
				}
				if (!StringUtil.isNULL(bankName)) {
//					if(!bankName.equals(bPo.getBankName())){
//						Map<String, Object> param = new HashMap<String, Object>();
//						param.put("deleted", 0);
//						param.put("bankName", bankName);
//						int num = bankFacade.countBank(param);
//						if(num>0){
//							return ResponseUtil.showMSGResultString("银行卡已存在");
//						}
//					}
					bPo.setBankName(bankName);
				}
				if (!StringUtil.isNULL(color)) {
					bPo.setColor(color);
				}
				if (!StringUtil.isNULL(show)) {
					bPo.setShows(Integer.parseInt(show));
				}
				int i = bankFacade.updateBankDO(bPo);
				if (i == 1) {
					return ResponseUtil.successResultString("修改成功");
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
	@RequestMapping(value = "bank/add", method = RequestMethod.POST)
	@ApiOperation(value = "客户添加银行", httpMethod = "POST", response = ModelAndView.class)
	public String addBank(
			@ApiParam(required = false, name = "bankImgUrl", value = "logo") @RequestParam(value = "bankImgUrl", required = false) String bankImgUrl,
			@ApiParam(required = false, name = "bankName", value = "银行名称") @RequestParam(value = "bankName", required = false) String bankName,
			@ApiParam(required = false, name = "color", value = "颜色") @RequestParam(value = "color", required = false) String color,
			HttpServletRequest request) {
		try {
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> sess = (List<Map<String, Object>>) request.getSession(true).getAttribute("menuSession");
			RoleBO rv = new RoleBO(sess, "银行管理添加");
			if ("1".equals(rv.getRoleFalg())) {
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("deleted", 0);
				param.put("bankName", bankName);
				int num = bankFacade.countBank(param);
				if(num>0){
					return ResponseUtil.showMSGResultString("银行卡已存在");
				}
				BankDO bPo = new BankDO();
				if (!StringUtil.isNULL(bankImgUrl)) {
					bPo.setBankImgUrl(bankImgUrl);
				}
				if (!StringUtil.isNULL(bankName)) {
					bPo.setBankName(bankName);
				}
				if (!StringUtil.isNULL(color)) {
					bPo.setColor(color);
				}
				bPo.insertInit();
				int i = bankFacade.insertBankDO(bPo);
				if (i == 1) {
					return ResponseUtil.successResultString("添加成功");
				}
				return ResponseUtil.showMSGResultString("添加失败");
			}
			return ResponseUtil.showMSGResultString("无权限");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
}
