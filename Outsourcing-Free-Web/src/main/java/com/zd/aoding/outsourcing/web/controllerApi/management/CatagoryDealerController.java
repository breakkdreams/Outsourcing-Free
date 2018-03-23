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

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.zd.aoding.common.StringDate.StringUtil;
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.CategoryDO;
import com.zd.aoding.outsourcing.weChat.api.facade.CategoryFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SessionFacade;

@Api(value = "", description = "分类")
@Controller
@RequestMapping("ad/manager/dealer")
public class CatagoryDealerController {
    @Autowired
    private CategoryFacade categoryFacade;
    @Autowired
    private SessionFacade sessionService;

    /**
     * @Title: getCatagory
     * @Description: TODO
     * @param pageNum
     * @param pageSize
     * @param request
     * @return
     * @return: String
     */
    @ResponseBody
    @RequestMapping(value = "catagory/list", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "查看分类", httpMethod = "POST", notes = "查看分类", response = ResponseUtil.class)
    public String getCatagory(
            @ApiParam(required = true, name = "parentCategoryId", value = "上级分类id") @RequestParam(value = "parentCategoryId", required = true) String parentCategoryId,
            HttpServletRequest request) {
        try {
            String parentId = request.getParameter("parentCategoryId");
            if(!StringUtil.isNumber(parentId)){
                return ResponseUtil.paramErrorResultString("参数错误");
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("parentCategoryId", Integer.parseInt(parentId));
            map.put("deleted", 0);
            List<CategoryDO> list = categoryFacade.getCategoryList(map);
            return ResponseUtil.successResultString(list);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.systemErrorResultString();
        }
    }
}
