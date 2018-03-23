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
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.TakeMoneyBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.RecordPursesDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.TakeMoneyDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.UserPurseDO;
import com.zd.aoding.outsourcing.weChat.api.facade.BankCardFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.RecordFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SessionFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.TakeMoneyFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.UserPurseFacade;

@Api(value = "", description = "提现管理")
@Controller
@RequestMapping("ad/manager")
public class TakeMoneyManagerController {
	@Autowired
	private SessionFacade sessionFacade;
	@Autowired
	private TakeMoneyFacade takeMoneyFacade;
	@Autowired
	private BankCardFacade bankCardFacade;
	@Autowired
	private UserPurseFacade userPurseFacade;
	@Autowired
	private RecordFacade recordFacade;

	@ResponseBody
    @RequestMapping(value = "takeMoney/getPage", method = RequestMethod.POST)
    @ApiOperation(value = "提现", httpMethod = "POST", response = ModelAndView.class)
	public Map<String, Object> getAddress(
			  @ApiParam(required = false, name = "iDisplayStart", value = "当前页数") @RequestParam(value = "iDisplayStart", required = false) String iDisplayStart,
	            @ApiParam(required = false, name = "iDisplayLength", value = "分页条数") @RequestParam(value = "iDisplayLength", required = false) String iDisplayLength,
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
			param.put("deleted", 0);
			if(!StringUtil.isNULL(sSearch)){
			    param.put("userName", sSearch);
			}
			pageEntity.setParams(param);
			/**
			 * 查询
			 */
			PageResult<TakeMoneyBO> pageResult = takeMoneyFacade.getPageTakeMoneyBO(pageEntity);
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
	@RequestMapping(value = "takeMoney/edit", method = RequestMethod.POST)
	@ApiOperation(value = "审核提现", httpMethod = "POST", response = ModelAndView.class)
	public String editAboutOur(
			@ApiParam(required = false, name = "state", value = "审核状态") @RequestParam(value = "state", required = true) String state,
			@ApiParam(required = false, name = "takeMoneyId", value = "id") @RequestParam(value = "takeMoneyId", required = true) String takeMoneyId,
			HttpServletRequest request) {
		try {
			AccountBO account = sessionFacade.checkLoginAccountSession(request);
			if (!StringUtil.isNumber(takeMoneyId)) {
				return ResponseUtil.paramErrorResultString("参数有误");
			}
			TakeMoneyBO takeMoneyBO = takeMoneyFacade.getTakeMoneyBOByPK(takeMoneyId);
			TakeMoneyDO takeMoneyDO = new TakeMoneyDO();
			takeMoneyDO.setId(takeMoneyBO.getTakeMoneyId());
			takeMoneyDO.setState(Integer.parseInt(state));
			if(account!=null){
				takeMoneyDO.setManagerId(account.getAccountId());
			}
			int i = takeMoneyFacade.updateTakeMoneyDO(takeMoneyDO);
			if (i == 1) {
				if(Integer.parseInt(state) == -1){//审核失败,返还金额
					double money = takeMoneyBO.getMoney();
					String chargeStr = "0";
					if(!StringUtil.isNULL(chargeStr)){
						chargeStr = takeMoneyBO.getCharge();
					}
					BigDecimal moneyBig = new BigDecimal(money);
					BigDecimal chargeBig = new BigDecimal(chargeStr);
					BigDecimal oneBig = new BigDecimal("1");
					UserPurseDO userPurse = userPurseFacade.getPursePoByUserId(takeMoneyBO.getUserId(), "0");
					double purseMoney = userPurse.getMoney();
					BigDecimal resu = moneyBig.divide(oneBig.subtract(chargeBig),2,BigDecimal.ROUND_HALF_DOWN);
					BigDecimal purseMoneyBig = new BigDecimal(purseMoney);
					userPurse.setMoney(purseMoneyBig.add(resu).doubleValue());
					int p = userPurseFacade.updatePurse(userPurse);
					if(p==1){
						RecordPursesDO recordPursesDO = new RecordPursesDO("", "返还余额成功", 
								RecordPursesDO.PURSETYPE_MANAGER,account.getAccountId(), 
								takeMoneyBO.getUserId(), RecordPursesDO.PURSETYPE_CASH, RecordPursesDO.TYPE_ADD, 
								resu.intValue(), "money", "提现审核失败,返还余额:"+resu);
						recordFacade.insertRecordPurseDO(recordPursesDO);
					}
				}
				return ResponseUtil.successResultString("修改成功");
			}
			return ResponseUtil.showMSGResultString("修改失败");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
}
