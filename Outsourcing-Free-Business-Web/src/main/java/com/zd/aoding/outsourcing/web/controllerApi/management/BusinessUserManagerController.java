package com.zd.aoding.outsourcing.web.controllerApi.management;

import java.math.BigDecimal;
import java.util.HashMap;
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
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.AccountBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.PurseBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.UserBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.RecordPursesDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.SystemparamDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.UserPurseDO;
import com.zd.aoding.outsourcing.weChat.api.facade.AccountFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.RecordFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SessionFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SystemparamFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.UserFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.UserPurseFacade;

@Api(value = "", description = "banner图管理")
@Controller
@RequestMapping("ad/manager")
public class BusinessUserManagerController {
	@Autowired
	private UserFacade userFacade;
	@Autowired
	private SystemparamFacade systemparamFacade;
	@Autowired
	private UserPurseFacade userPurseFacade;
	@Autowired
	private SessionFacade sessionFacade;
	@Autowired
	private RecordFacade recordFacade;
	/**
	 * @Title: getUser
	 * @Description: 条件查询
	 * @param request
	 * @return
	 * @return: String
	 */
	@ResponseBody
	@RequestMapping(value = "user/DTPaging", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ApiOperation(value = "查看", httpMethod = "POST", notes = "查看", response = ResponseUtil.class)
	public Map<String, Object> getUser(
			@ApiParam(required = false, name = "iDisplayStart", value = "分页查询开始条数（DataTable 内置参数）") @RequestParam(value = "iDisplayStart", required = false) String iDisplayStart,
			@ApiParam(required = false, name = "iDisplayLength", value = "分页查询条数（DataTable 内置参数）") @RequestParam(value = "iDisplayLength", required = false) String iDisplayLength,
			HttpServletRequest request) {
		try {
			String sSearch = request.getParameter("sSearch");
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
				if(!StringUtil.isNULL(sSearch)){
				    param.put("phoneLike", sSearch);
				}
				pageEntity.setParams(param);
				/**
				 * 查询
				 */
				PageResult<UserBO> pageResult = userFacade.getUserPage(pageEntity);
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
	@RequestMapping(value = "user/detail", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "查看用户", httpMethod = "POST", notes = "查看用户", response = ResponseUtil.class)
	public String userDetail(
			@ApiParam(required = true, name = "userId", value = "id") @RequestParam(value = "userId", required = true) String userId,
			HttpServletRequest request) {
		try {
			if (!StringUtil.isNULL(userId) && StringUtil.isNumber(userId)) {
				PurseBO userPurseBO = userPurseFacade.getPurseVoByUserId(Integer.parseInt(userId), "0");
				if (userPurseBO != null) {
					//获取兑换比例
					String proportion = "1";
					SystemparamDO systemparamPo = systemparamFacade.getSystemparamPoByCode("proportion");
					if(systemparamPo != null){
						proportion = systemparamPo.getStringVale();
					}
					double d_proportion = Double.valueOf(proportion).doubleValue();
					userPurseBO.setMoney(userPurseBO.getMoney()*d_proportion);
					return ResponseUtil.successResultString(userPurseBO);
				}
				return ResponseUtil.showMSGResultString("未找到");
			}
			return ResponseUtil.paramErrorResultString("id为空或格式不正确");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	
    @ResponseBody
    @RequestMapping(value = "userScore/edit", method = RequestMethod.POST)
    @ApiOperation(value = "修改用户积分", httpMethod = "POST", response = ModelAndView.class)
    public String editAboutOur(
            @ApiParam(required = false, name = "score", value = "积分") @RequestParam(value = "score", required = true) String score,
            @ApiParam(required = false, name = "userId", value = "用户id") @RequestParam(value = "userId", required = true) String userId,
            HttpServletRequest request) {
        try {
        	AccountBO account = sessionFacade.checkLoginAccountSession(request);
            if(!StringUtil.isNumber(userId)){
                return ResponseUtil.paramErrorResultString("找不到该信息");
            }
            UserPurseDO purseDO = userPurseFacade.getPursePoByUserId(Integer.parseInt(userId), "0");
            if(purseDO == null){
            	return ResponseUtil.paramErrorResultString("找不到该信息");
            }
            BigDecimal purseScore = new BigDecimal(purseDO.getScore());
            BigDecimal newScore = new BigDecimal(score);
            purseDO.setScore(newScore.doubleValue());
            int i = userPurseFacade.updatePurse(purseDO);
            if(i == 1){
            	RecordPursesDO recordPursesDO = new RecordPursesDO("", "增加积分成功", 
						RecordPursesDO.PURSETYPE_MANAGER,account.getAccountId(), 
						Integer.parseInt(userId), RecordPursesDO.PURSETYPE_SCORE, RecordPursesDO.TYPE_ADD, 
						newScore.intValue(), "score", "管理员为该用户修改积分:"+newScore.subtract(purseScore));
				recordFacade.insertRecordPurseDO(recordPursesDO);
                return ResponseUtil.successResultString("修改成功"); 
            }
            return ResponseUtil.showMSGResultString("修改失败"); 
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.systemErrorResultString();
        }
    }
}
