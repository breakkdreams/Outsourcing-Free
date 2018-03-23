package com.zd.aoding.outsourcing.web.controllerApi.management;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.zd.aoding.common.StringDate.DateUtil;
import com.zd.aoding.common.StringDate.StringUtil;
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.RoleBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ChargeParamDO;
import com.zd.aoding.outsourcing.weChat.api.facade.ChargeParamFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Api(value = "", description = "充值赠送管理")
@Controller
@RequestMapping("ad/manager")
public class ChargeParamManagerController {
	@Autowired
	private ChargeParamFacade chargeParamFacade;


	@ResponseBody
	@RequestMapping(value = "chargeParam/edit", method = RequestMethod.POST)
	@ApiOperation(value = "修改充值赠送", httpMethod = "POST", response = ModelAndView.class)
	public String updateChargeParam(
			@ApiParam(required = false, name = "chargeParamId", value = "id") @RequestParam(value = "chargeParamId", required = false)
			String chargeParamId,
			@ApiParam(required = false, name = "firstWeek", value = "首周") @RequestParam(value = "firstWeek", required = false)
			String firstWeek,
			@ApiParam(required = false, name = "firstMonth", value = "首月") @RequestParam(value = "firstMonth", required = false)
			String firstMonth,
			@ApiParam(required = false, name = "firstSeason", value = "首季度") @RequestParam(value = "firstSeason", required = false)
			String firstSeason,
			@ApiParam(required = false, name = "totalMoney", value = "赠送金额") @RequestParam(value = "totalMoney", required = false)
			String totalMoney,
			HttpServletRequest request) {
		try {
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> sess = (List<Map<String, Object>>) request.getSession(true).getAttribute("menuSession");
			RoleBO rv = new RoleBO(sess, "经销商管理修改");
			if ("1".equals(rv.getRoleFalg())) {
				if (!StringUtil.isNumber(chargeParamId)) {
					return ResponseUtil.showMSGResultString("参数错误");
				}
				ChargeParamDO chargeParamDO = chargeParamFacade.getChargeParamDOByPK(Integer.parseInt(chargeParamId));
				if(chargeParamDO == null){
					return ResponseUtil.showMSGResultString("未找到");
				}
				if (!StringUtil.isNULL(firstWeek)) {
					BigDecimal bigWeek = new BigDecimal(firstWeek);
					chargeParamDO.setFirstWeek(bigWeek.doubleValue());
				}
				if (!StringUtil.isNULL(firstMonth)) {
					BigDecimal bigMonth = new BigDecimal(firstMonth);
					chargeParamDO.setFirstMonth(bigMonth.doubleValue());
				}
				if (!StringUtil.isNULL(firstSeason)) {
					BigDecimal bigSeason = new BigDecimal(firstSeason);
					chargeParamDO.setFirstSeason(bigSeason.doubleValue());
				}
				if (!StringUtil.isNULL(totalMoney)) {
					BigDecimal bigMoney = new BigDecimal(totalMoney);
					chargeParamDO.setTotalMoney(bigMoney.doubleValue());
				}

				int i = chargeParamFacade.updateChargeParamDO(chargeParamDO);
				if (i == 1) {
					return ResponseUtil.successResultString("修改成功");
				}
				return ResponseUtil.showMSGResultString("修改失败");
			}
			return ResponseUtil.showMSGResultString("无权限");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}

	@ResponseBody
	@RequestMapping(value = "chargeParam/add", method = RequestMethod.POST)
	@ApiOperation(value = "添加充值赠送金额", httpMethod = "POST", response = ModelAndView.class)
	public String addChargeParam(
			@ApiParam(required = false, name = "startTime", value = "开始时间") @RequestParam(value = "startTime", required = false)
			String startTime,
			@ApiParam(required = false, name = "firstWeek", value = "首周") @RequestParam(value = "firstWeek", required = false)
			String firstWeek,
			@ApiParam(required = false, name = "firstMonth", value = "首月") @RequestParam(value = "firstMonth", required = false)
			String firstMonth,
			@ApiParam(required = false, name = "firstSeason", value = "首季度") @RequestParam(value = "firstSeason", required = false)
			String firstSeason,
			@ApiParam(required = false, name = "totalMoney", value = "赠送金额") @RequestParam(value = "totalMoney", required = false)
			String totalMoney,
			@ApiParam(required = false, name = "distributorId", value = "经销商id") @RequestParam(value = "distributorId", required = false)
			String distributorId,
			HttpServletRequest request) {
		try {
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> sess = (List<Map<String, Object>>) request.getSession(true).getAttribute("menuSession");
			RoleBO rv = new RoleBO(sess, "经销商管理添加");
			if ("1".equals(rv.getRoleFalg())) {
				if (!StringUtil.isNumber(distributorId)) {
					return ResponseUtil.showMSGResultString("未找到经销商");
				}
				Date statDate = new Date();
				if(!StringUtil.isNULL(startTime)){
					statDate = DateUtil.getGmtDate(startTime);
				}
				BigDecimal bigWeek = new BigDecimal("0");
				BigDecimal bigMonth = new BigDecimal("0");
				BigDecimal bigSeason = new BigDecimal("0");
				BigDecimal bigMoney = new BigDecimal("0");
				if (!StringUtil.isNULL(firstWeek)) {
					bigWeek = new BigDecimal(firstWeek);
				}
				if (!StringUtil.isNULL(firstMonth)) {
					bigMonth = new BigDecimal(firstMonth);
				}
				if (!StringUtil.isNULL(firstSeason)) {
					bigSeason = new BigDecimal(firstSeason);
				}
				if (!StringUtil.isNULL(totalMoney)) {
					bigMoney = new BigDecimal(totalMoney);
				}
				ChargeParamDO chargeParamDO = new ChargeParamDO(statDate, bigWeek.doubleValue(),bigMonth.doubleValue(),
						bigSeason.doubleValue(),bigMoney.doubleValue(),Integer.parseInt(distributorId));
				chargeParamDO.insertInit();
				int i = chargeParamFacade.insertChargeParamDO(chargeParamDO);
				if (i == 1) {
					return ResponseUtil.successResultString("添加成功");
				}
				return ResponseUtil.showMSGResultString("添加失败");
			}
			return ResponseUtil.showMSGResultString("无权限");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
}
