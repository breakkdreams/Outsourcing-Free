package com.zd.aoding.outsourcing.web.controllerApi.management;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.zd.aoding.common.StringDate.StringUtil;
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.RegionDO;
import com.zd.aoding.outsourcing.weChat.api.facade.RegionFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(value = "", description = "省")
@Controller
@RequestMapping("ad/manager")
public class RegionManagerController {

    @Autowired
    private RegionFacade regionFacade;

    @ResponseBody
    @RequestMapping(value = "region/allList", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded;charset=utf-8")
    @ApiOperation(value = "查询全部省", httpMethod = "POST", notes = "查询全部省", response = ResponseUtil.class)
    public String getAllList(
			@ApiParam(required = false, name = "parentId", value = "上级id") @RequestParam(value = "parentId", required = false) String parentId,
            HttpServletRequest request) {
        try {
            if (StringUtil.isNumber(parentId)) {
                List<RegionDO> regionList = regionFacade.getRegionByParentId(Integer.parseInt(parentId));
                if (regionList != null) {
                    return ResponseUtil.successResultString(regionList);
                }
                return ResponseUtil.showMSGResultString("查询失败");
            }
            return ResponseUtil.showMSGResultString("上级id为空或者类型不正确");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.systemErrorResultString();
        }
    }
}
