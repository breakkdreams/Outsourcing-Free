package com.zd.aoding.outsourcing.web.controllerApi.app.pub;

import java.util.List;

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
import com.zd.aoding.common.response.ResponseCodeEnum;
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.RegionDO;
import com.zd.aoding.outsourcing.weChat.api.facade.RegionFacade;

@Api(value = "", description = "地址")
@Controller
@RequestMapping("pub")
public class RegionPubController {
	@Autowired
	private RegionFacade regionFacade;

	/**
	 * @Title: getRegion
	 * @Description: 地址三级联动
	 * @param isDefault
	 * @param addressMapper
	 * @param request
	 * @return
	 * @return: String
	 */
	@ResponseBody
	@RequestMapping(value = "region", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "查询省市区", httpMethod = "POST", notes = "根据上级id查询", response = ResponseUtil.class)
	public String getRegionList(
			@ApiParam(required = true, name = "parentId", value = "上级id（0找国家1找省。。。）") @RequestParam(value = "parentId", required = true) String parentId,
			HttpServletRequest request) {
		try {
			if (StringUtil.isNumber(parentId)) {
				List<RegionDO> regionList = regionFacade.getRegionByParentId(Integer.parseInt(parentId));
				if (regionList != null) {
					return ResponseUtil.successResultString(regionList);
				}
				return ResponseUtil.showMSGResultString("查询失败");
			}
			return ResponseUtil.paramErrorResultString("上级id为空或者类型不正确");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	@ResponseBody
	@RequestMapping(value = "region/detail", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "查询省市区详情", httpMethod = "POST", notes = "查询省市区某个", response = ResponseUtil.class)
	@ApiResponses(@ApiResponse(code = 1, message = "实体类注释", response = RegionDO.class))
	public String regionDetail(
			@ApiParam(required = true, name = "regionId", value = "省市区id") @RequestParam(value = "regionId", required = true) String regionId,
			HttpServletRequest request) {
		try {
			if (StringUtil.isNumber(regionId)) {
				RegionDO region = regionFacade.getRegionByRegionId(Integer.parseInt(regionId));
				if (region != null) {
					return ResponseUtil.successResultString(region);
				}
				return ResponseUtil.showMSGResultString("查询失败");
			}
			return ResponseUtil.paramErrorResultString("id错误");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	@ResponseBody
	@RequestMapping(value = "regionLike", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "根据省名模糊搜索", httpMethod = "POST", notes = "根据省名模糊搜索", response = ResponseUtil.class)
	public String regionLike(
			@ApiParam(required = true, name = "provinceName", value = "省名") @RequestParam(value = "provinceName", required = true) String provinceName,
			HttpServletRequest request) {
		try {
			if (!StringUtil.isNULL(provinceName)) {
				RegionDO region = regionFacade.getProvinceByNameLike(provinceName);
				if (region != null) {
					return ResponseUtil.successResultString(region);
				}
				return ResponseUtil.showMSGResultString("未找到该省");
			}
			return ResponseUtil.paramErrorResultString("省名未填");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
}
