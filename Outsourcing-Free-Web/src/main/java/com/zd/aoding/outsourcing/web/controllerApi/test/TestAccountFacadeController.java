//package com.zd.aoding.outsourcing.web.controllerApi.test;
//
//import java.math.BigDecimal;
//
//import org.springframework.stereotype.Controller;
//
//import com.wordnik.swagger.annotations.Api;
//import com.zd.aoding.common.StringDate.StringUtil;
//
//@Api(value = "", description = "账号信息管理")
//@Controller
//public class TestAccountFacadeController {
//
//	public static void main(String[] args) {
//		BigDecimal rechargeMoney = new BigDecimal("0");
//		BigDecimal num100 = new BigDecimal(100);
//		String chargeStr = "6";
//		if(!StringUtil.isNULL(chargeStr)){
//			rechargeMoney = new BigDecimal(chargeStr);
//			rechargeMoney = rechargeMoney.divide(num100,2,BigDecimal.ROUND_HALF_UP);
//			System.err.println("rechargeMoney:"+rechargeMoney);
//		}
//	}
//
//}
