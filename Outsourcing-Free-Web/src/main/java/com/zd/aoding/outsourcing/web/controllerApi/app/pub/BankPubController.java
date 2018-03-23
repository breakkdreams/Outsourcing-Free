package com.zd.aoding.outsourcing.web.controllerApi.app.pub;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.BankBO;
import com.zd.aoding.outsourcing.weChat.api.facade.BankFacade;

@Api(value = "", description = "全部银行")
@Controller
@RequestMapping("pub")
public class BankPubController {
	@Autowired
	private BankFacade bankFacade;

	@ResponseBody
	@RequestMapping(value = "bank/getPage", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "银行查询", httpMethod = "POST", notes = "查看银行", response = ResponseUtil.class)
	public String getBank(
			@ApiParam(required = false, name = "pageNum", value = "当前第几页") @RequestParam(value = "pageNum", required = false) String pageNum,
			@ApiParam(required = false, name = "pageSize", value = "分页条数") @RequestParam(value = "pageSize", required = false) String pageSize,
			HttpServletRequest request) {
		try {
			// 初始化列表
			PageEntity pageEntity = new PageEntity();
			if (StringUtil.isNumber(pageNum)) {
				pageEntity.setPage(Integer.parseInt(pageNum));
			}
			if (StringUtil.isNumber(pageSize)) {
				pageEntity.setSize(Integer.parseInt(pageSize));
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
			param.put("shows", 1);
			pageEntity.setParams(param);
			PageResult<BankBO> pageResult = bankFacade.getPageBankBO(pageEntity);
			return ResponseUtil.successResultString(pageResult);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
}
