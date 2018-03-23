package com.zd.aoding.outsourcing.web.controllerApi.management;

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
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.AgreementBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.RoleBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.AgreementDO;
import com.zd.aoding.outsourcing.weChat.api.facade.AgreementFacade;

/**
 * @ClassName: BaseController
 * @Description: 团装团基础入口页面
 * @author HCD
 * @date 2016年10月24日 下午9:38:23
 */
@Api(value = "", description = "后台及代理的所有界面")
@Controller
@RequestMapping("ad/manager")
public class AgreementController {

    @Autowired
    private AgreementFacade agreementFacade;

    @ResponseBody
    @RequestMapping(value = "agreement/detail", method = RequestMethod.POST)
    @ApiOperation(value = "查看信息", httpMethod = "POST", response = ModelAndView.class)
    public String aboutOurDetail(
    		@ApiParam(required = false, name = "type", value = "类型") @RequestParam(value = "type", required = false) String type,
            HttpServletRequest request) {
        try {
            AgreementBO agreement = agreementFacade.getAgreementVoByPK(Integer.parseInt(type));
            if(agreement == null){
            	return ResponseUtil.showMSGResultString("未找到");
            }
            return ResponseUtil.successResultString(agreement);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.systemErrorResultString();
        }
    }

    @ResponseBody
    @RequestMapping(value = "agreement/edit", method = RequestMethod.POST)
    @ApiOperation(value = "修改", httpMethod = "POST", response = ModelAndView.class)
    public String editAboutOur(
            @ApiParam(required = false, name = "content", value = "详细描述") @RequestParam(value = "content", required = true) String content,
            @ApiParam(required = false, name = "type", value = "type") @RequestParam(value = "type", required = true) String type,
            HttpServletRequest request) {
        try {
           
            	@SuppressWarnings("unchecked")
				List<Map<String, Object>> sess = (List<Map<String, Object>>) request
                        .getSession(true).getAttribute("menuSession");
                RoleBO rv = new RoleBO(sess, "协议说明修改");
                if ("1".equals(rv.getRoleFalg())) {
                    if(!StringUtil.isNumber(type)){
                        return ResponseUtil.showMSGResultString("找不到该信息");
                    }
                    if(StringUtil.isNULL(content)){
                        return ResponseUtil.showMSGResultString("详细描述");
                    }
                    AgreementDO agreementDO = new AgreementDO();
                    agreementDO.setContent(content);
                    agreementDO.setType(Integer.parseInt(type));
                    int i = agreementFacade.updateAgreementPo(agreementDO);
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
}
