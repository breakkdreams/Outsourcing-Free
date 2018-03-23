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
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import com.zd.aoding.common.StringDate.StringUtil;
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.common.response.ResponseCodeEnum;
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.BannerBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.BannerDO;
import com.zd.aoding.outsourcing.weChat.api.facade.BannerFacade;

@Api(value = "", description = "banner图管理")
@Controller
@RequestMapping("pub")
public class BannerPubController {
	@Autowired
	private BannerFacade bannerFacade;

	/**
	 * 分页查询
	 * @param pageNum
	 * @param pageSize
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "banner/paging", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiResponses({ @ApiResponse(code = 1, message = "配置名称", response = BannerDO.class) })
	@ApiOperation(value = "分页查询", httpMethod = "POST", notes = "", response = ResponseUtil.class)
	public String paging(
			@ApiParam(required = false, name = "pageNum", value = "当前第几页") @RequestParam(value = "pageNum", required = false) String pageNum,
			@ApiParam(required = false, name = "pageSize", value = "分页条数") @RequestParam(value = "pageSize", required = false) String pageSize,
			//@ApiParam(required = false, name = "position", value = "1首页") @RequestParam(value = "position", required = false) String position,
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
			pageEntity.setOrderColumn("update_time");
			/**
			 * 查询条件参数
			 */
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("deleted", 0);
			//if(StringUtil.isNumber(position)){
				param.put("position", 1);
			//}
			pageEntity.setParams(param);
			PageResult<BannerBO> pageResult = bannerFacade.getPageBannerVo(pageEntity);
			return ResponseUtil.successResultString(pageResult);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
/**
 * 查看单个
 * @param bannerId
 * @param request
 * @return
 */
	@ResponseBody
	@RequestMapping(value = "banner/detail", method = RequestMethod.POST,produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiResponses({ @ApiResponse(code = 1, message = "配置名称", response = BannerDO.class) })
	@ApiOperation(value = "查看单个banner", httpMethod = "POST", notes = "根据ID查看banner", response = ResponseUtil.class)
	public String getBannerById(
			@ApiParam(required = false, name = "bannerId", value = "bannerid") @RequestParam(value = "bannerId", required = false) String bannerId,
			HttpServletRequest request) {
		try {
			if (StringUtil.isNumber(bannerId)) {
				BannerBO bannerVo = bannerFacade.getBannerVoByPK(Integer.parseInt(bannerId));
				if (bannerVo != null) {
					return ResponseUtil.successResultString(bannerVo);
				}
				return ResponseUtil.showMSGResultString("暂无数据");
			}
			return ResponseUtil.showMSGResultString("暂无数据");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
}
