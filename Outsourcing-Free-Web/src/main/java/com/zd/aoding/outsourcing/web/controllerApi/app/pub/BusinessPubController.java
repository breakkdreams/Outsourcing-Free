package com.zd.aoding.outsourcing.web.controllerApi.app.pub;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.zd.aoding.common.StringDate.StringUtil;
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.ManagerBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ManagerDO;
import com.zd.aoding.outsourcing.weChat.api.facade.ManagerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Api(value = "", description = "商家管理")
@Controller
@RequestMapping("pub")
public class BusinessPubController {
	@Autowired
	private ManagerFacade managerFacade;

	@ResponseBody
	@RequestMapping(value = "business/getPage", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "商家列表查询", httpMethod = "POST", notes = "查看商家列表", response = ResponseUtil.class)
	public String getBank(
			@ApiParam(required = false, name = "pageNum", value = "当前第几页") @RequestParam(value = "pageNum", required = false) String pageNum,
			@ApiParam(required = false, name = "pageSize", value = "分页条数") @RequestParam(value = "pageSize", required = false) String pageSize,
			@ApiParam(required = false, name = "lat", value = "纬度") @RequestParam(value = "lat", required = false) String lat,
			@ApiParam(required = false, name = "lng", value = "经度") @RequestParam(value = "lng", required = false) String lng,
			@ApiParam(required = false, name = "ascOrDesc", value = "1正序 2倒序  默认倒序") @RequestParam(value = "ascOrDesc", required = false) String ascOrDesc,
			@ApiParam(required = false, name = "sort", value = "排序 1.访问量 2.距离") @RequestParam(value = "sort", required = false) String sort,
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
			param.put("type", ManagerDO.TYPE_BUSINESS);
			if(!StringUtil.isNULL(lat)){
				param.put("lat",lat);
			}
			if(!StringUtil.isNULL(lng)){
				param.put("lng",lng);
			}
			pageEntity.setParams(param);
			if("1".equals(sort)){
				pageEntity.setOrderColumn("total_visit");
			}
			if("2".equals(sort)){
				if(!StringUtil.isNULL(lat) && !StringUtil.isNULL(lng)){
					pageEntity.setOrderColumn("juli");
				}
			}
			if("1".equals(ascOrDesc)){
				pageEntity.setOrderTurn("asc");
			}
			if("2".equals(ascOrDesc)){
				pageEntity.setOrderTurn("desc");
			}
			PageResult<ManagerBO> pageResult = managerFacade.getPageManagerVo(pageEntity);
			return ResponseUtil.successResultString(pageResult);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
}
