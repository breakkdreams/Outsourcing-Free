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

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.zd.aoding.common.StringDate.StringUtil;
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.RoleBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.AboutOurDO;
import com.zd.aoding.outsourcing.weChat.api.facade.AboutOurFacade;

/**
 * @ClassName: BaseController
 * @Description: 团装团基础入口页面
 * @author HCD
 * @date 2016年10月24日 下午9:38:23
 */
@Api(value = "", description = "后台及代理的所有界面")
@Controller
@RequestMapping("ad/manager/appCompany")
public class AboutOurAppCompanyController {

    @Autowired
    private AboutOurFacade aboutOurFacade;

    @ResponseBody
    @RequestMapping(value = "abountOur/detail", method = RequestMethod.POST)
    @ApiOperation(value = "供货商查看个人信息", httpMethod = "POST", response = ModelAndView.class)
    public String aboutOurDetail(
            HttpServletRequest request) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("deleted", 0);
            AboutOurDO aboutOurPo = aboutOurFacade.getAboutOurByParam(map);
            if(aboutOurPo == null){
                aboutOurPo = new AboutOurDO();
            }
            return ResponseUtil.successResultString(aboutOurPo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.systemErrorResultString();
        }
    }

    @ResponseBody
    @RequestMapping(value = "abountOur/edit", method = RequestMethod.POST)
    @ApiOperation(value = "客户修改关于我们", httpMethod = "POST", response = ModelAndView.class)
    public String editAboutOur(
            @ApiParam(required = false, name = "content", value = "详细描述") @RequestParam(value = "content", required = true) String content,
            @ApiParam(required = false, name = "id", value = "关于我们id") @RequestParam(value = "id", required = true) String id,
            HttpServletRequest request) {
        try {
           
            	@SuppressWarnings("unchecked")
				List<Map<String, Object>> sess = (List<Map<String, Object>>) request
                        .getSession(true).getAttribute("menuSession");
                RoleBO rv = new RoleBO(sess, "修改关于我们");
                if ("1".equals(rv.getRoleFalg())) {
                    if(!StringUtil.isNumber(id)){
                        return ResponseUtil.showMSGResultString("找不到该信息");
                    }
                    if(StringUtil.isNULL(content)){
                        return ResponseUtil.showMSGResultString("详细描述");
                    }
                    AboutOurDO aboutOurPo = new AboutOurDO();
                    aboutOurPo.setContent(content);
                    aboutOurPo.setId(Integer.parseInt(id));
                    int i = aboutOurFacade.updateAboutOurByParam(aboutOurPo);
                    if(i == 1){
                        return ResponseUtil.successResultString("修改成功"); 
                    }
                    return ResponseUtil.successResultString("修改失败"); 
                }
                return ResponseUtil.showMSGResultString("无权限");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.systemErrorResultString();
        }
    }
    @ResponseBody
    @RequestMapping(value = "abountOur/add", method = RequestMethod.POST)
    @ApiOperation(value = "供货商查看个人信息", httpMethod = "POST", response = ModelAndView.class)
    public String addAboutOur(
            @ApiParam(required = false, name = "content", value = "content") @RequestParam(value = "content", required = true) String content,
            HttpServletRequest request) {
        try {
            	@SuppressWarnings("unchecked")
				List<Map<String, Object>> sess = (List<Map<String, Object>>) request
                        .getSession(true).getAttribute("menuSession");
                RoleBO rv = new RoleBO(sess, "添加关于我们");
                if ("1".equals(rv.getRoleFalg())) {
                    if(StringUtil.isNULL(content)){
                        return ResponseUtil.showMSGResultString("详细描述");
                    }
                    AboutOurDO aboutOurPo = new AboutOurDO();
                    aboutOurPo.setContent(content);
                    int i = aboutOurFacade.insertAboutOurByParam(aboutOurPo);
                    if(i == 1){
                        return ResponseUtil.successResultString("添加成功"); 
                    }
                    return ResponseUtil.successResultString("添加失败"); 
                }
                return ResponseUtil.showMSGResultString("无权限");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.systemErrorResultString();
        }
    }

}
