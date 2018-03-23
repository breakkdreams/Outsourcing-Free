package com.zd.aoding.outsourcing.web.controllerApi.pay;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.zd.aoding.common.StringDate.StringUtil;
import com.zd.aoding.common.log.LogUtil;
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.config.Config;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.AccountBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.UserBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.BaseOrder;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ZFBOrder;
import com.zd.aoding.outsourcing.weChat.api.constant.ConfigLocal;
import com.zd.aoding.outsourcing.weChat.api.constant.SessionConstant;
import com.zd.aoding.outsourcing.weChat.api.facade.PayFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SessionFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.ZFBOrderService;
import com.zd.aoding.outsourcing.weChat.api.utils.file.ConfigTemp;
import com.zd.aoding.plugin.pay.alipay.config.AlipayConfig;
import com.zd.aoding.plugin.pay.alipay.util.AlipayNotify;
import com.zd.aoding.plugin.pay.alipay.util.AlipaySubmit;

@Api(value = "", description = "支付宝支付")
@Controller
@RequestMapping(value = "/ad/pay/aliapi")
public class AlipayApiController {
	private Log log = LogFactory.getLog(AlipayApiController.class);

	/***************** 测试 *****************/
	@RequestMapping(value = "/fund", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=utf-8")
	@ApiOperation(value = "支付宝支付测试界面", httpMethod = "POST", notes = "", response = ResponseUtil.class, hidden = true)
	public ModelAndView fund(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("pay/fund");
		return mav;// 付款的页面。本页面是为了测试而使用的
	}
	@RequestMapping(value = "/success", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=utf-8")
	@ApiOperation(value = "支付宝成功回调界面", httpMethod = "POST", notes = "", response = ResponseUtil.class, hidden = true)
	public ModelAndView success(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("pay/success");
		return mav;// 付款的页面。本页面是为了测试而使用的
	}

	@Autowired
	private PayFacade payFacade;
	@Autowired
	private SessionFacade sessionFacade;
	@Autowired
	private ZFBOrderService zfbOrderService;

	@RequestMapping(value = "/deposit", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "请求跳转支付宝支付页面", httpMethod = "POST", notes = "", response = ResponseUtil.class)
	public String deposit(
			@ApiParam(required = true, name = "payWay", value = "支付选择：支付宝传‘1’，奖金现金传‘2’，微信传‘3’，银联传‘4’") @RequestParam(value = "payWay", required = true) String payWay,
			@ApiParam(required = true, name = "orderId", value = "订单id（或下单时返回的订单簿id）") @RequestParam(value = "orderId", required = true) String orderId,
			@ApiParam(required = true, name = "boughtWhat", value = "订单类型：订单簿‘orderBook’(正常流程返回订单簿提交的)，订单‘order’（待付款界面提交）") @RequestParam(value = "boughtWhat", required = true) String boughtWhat,
			@ApiParam(required = false, name = "subject", value = "订单名称（订单概要名称8个字以内）") @RequestParam(value = "boughtWhat", required = false) String subject,
			Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Double allFee = payFacade.getTotalFee(orderId, boughtWhat);
		/*******/
		/*******/
		/*******/
		/*******/
		/*******/
		/*******/
		UserBO account = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
		if (account == null) {
			return ResponseUtil.notLoggedResultString();
		}
		if (allFee <= 0) {
			return null;
		}
		String result = "";
		// 商户订单号.
		// 商户网站订单系统中唯一订单号，必填
		// String out_trade_no = date.getTime() + "";
		// 订单名称
		// 必填
		// String subject = ServletRequestUtils.getStringParameter(request, "subject", "未知订单");
		// 支付类型
		// 必填，不能修改
		String payment_type = boughtWhat;
		// 服务器异步通知页面路径
		// 需http://格式的完整路径，不能加?id=123这类自定义参数
		// String notify_url = AlipayConfig.notify_url;
		// 防钓鱼时间戳
		// 若要使用请调用类文件submit中的query_timestamp函数
		String anti_phishing_key = "";
		// 客户端的IP地址
		// 非局域网的外网IP地址，如：221.0.0.1
		String exter_invoke_ip = "127.0.0.1";
		String quantity = ServletRequestUtils.getStringParameter(request, "quantity", "1");
		if (!StringUtil.isNumber(quantity) && Integer.parseInt(quantity) > 0) {
			result = "{\"success\":false,\"msg\":\"跳转失败，请稍候再试！\"}";
			ConfigTemp.writeToWeb(result, "html", response);
		}
		String body = ServletRequestUtils.getStringParameter(request, "body", "test");
		// 商品展示地址
		String show_url = ServletRequestUtils.getStringParameter(request, "show_url", "http://www.baidu.com");
		// 页面跳转同步通知页面路径
		// 需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/
		// String return_url =
		// "http://zj531723183.vicp.cc:28759/aliapi/return_url.web";
		// String return_url = AlipayConfig.return_url;
		// 需以http://开头的完整路径，例如：http://www.xxx.com/myorder.html
		// System.out.println(return_url);
		Map<String, String> sParaTemp = new HashMap<String, String>();
		/**
		 * 调用支付宝提供服务（即时到账）
		 */
		sParaTemp.put("service", "create_direct_pay_by_user");// 接口服务----即时到账
		sParaTemp.put("partner", AlipayConfig.partner);// 支付宝PID
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);// 统一编码
		sParaTemp.put("payment_type", AlipayConfig.payment_type);// 支付类型
//		sParaTemp.put("notify_url", AlipayConfig.notify_url);// 异步通知页面
		sParaTemp.put("notify_url", "http://testad.free.ngrok.cc/Outsourcing-WeChat-Web/ad/pay/aliapi/async.web");// 异步通知页面
		sParaTemp.put("return_url", AlipayConfig.return_url);// 页面跳转同步通知页面
		sParaTemp.put("seller_email", AlipayConfig.SELLER_EMAIL);// 卖家支付宝账号
		sParaTemp.put("subject", subject != null ? subject : "未知");// 商品名称
		sParaTemp.put("sign_type", "RSA");// 商品名称
		sParaTemp.put("total_fee", (allFee + ""));// 价格
		/**
		 * 测试开启
		 */
		sParaTemp.put("body", body);
		sParaTemp.put("show_url", show_url);
		sParaTemp.put("anti_phishing_key", AlipayConfig.anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", AlipayConfig.exter_invoke_ip);
		// 建立请求
		try {
			if (orderId != null && !"".equals(orderId)) {
				/**
				 * 已创建订单
				 */
				sParaTemp.put("out_trade_no", payFacade.getOutTradeNo(orderId, boughtWhat, account));// 商品订单编号
			} else {
				/**
				 * 未创建订单 预支付处理：创建订单
				 */
				// Map<String, Object> map = payFacade.perpay(request, boughtWhat,
				// MathUtil.DoubleBigDecimal(price), Integer.parseInt(quantity), account,
				// boughtObjectId);
				// sParaTemp.put("out_trade_no", payment_type + "_" + map.get("orderId") + "_" +
				// date.getTime());// 商品订单编号
			}
			String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "POST", "确认");
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			String s = gson.toJson(sHtmlText);
			model.addAttribute("sHtmlText", s);
			request.setAttribute("sHtmlText", s);
			result = "{\"success\":true,\"msg\":\"跳转成功\"}";
			ConfigTemp.writeToWeb(sHtmlText, "html", response);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			if (log.isWarnEnabled()) {
				LogUtil.operationRecord("ddddddddddddddddddddd");
			}
			result = "{\"success\":false,\"msg\":\"跳转失败，请稍候再试！\"}";
			ConfigTemp.writeToWeb(result, "html", response);
			return null;
		}
	}
	@RequestMapping(value = "/return_url")
	@ApiOperation(value = "返回路径", httpMethod = "POST", notes = "", response = ResponseUtil.class, hidden = true)
	public String Return_url(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}
		String return_url = request.getParameter("return_url");
		System.out.println(return_url);
		System.out.println(params);
		if (return_url != null && !AlipayConfig.return_url.equals(Config.SERVER + return_url)) {
			return "redirect:" + Config.SERVER + return_url;
		}
		return "redirect:" + Config.SERVER + "/zx/pc/user/webUser/userGeren.web";
	}
	/**
	 * @Title: async
	 * @Description: 支付宝异步请求回调接口
	 * @param request
	 * @param response
	 * @return
	 * @return: String
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/async", method = { RequestMethod.POST, RequestMethod.GET })
	@ApiOperation(value = "支付宝异步请求回调接口", notes = "", response = ResponseUtil.class)
	public String async(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("async");
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}
		System.out.println(params);
		String payment_type = request.getParameter("payment_type");
		String total_fee = request.getParameter("total_fee");
		String notify_time = request.getParameter("notify_time");
		String notify_type = request.getParameter("notify_type");
		String notify_id = request.getParameter("notify_id");
		/**
		 * 防止多次回调插入
		 */
		if (payFacade.checkZfBOrderIsInsert(notify_id)) {
			System.out.println("阻止重复回调=" + notify_id);
			PrintWriter pw = null;
			try {
				pw = new PrintWriter(response.getOutputStream());
				pw.print("success");
				pw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
			return null;
		}
		String sign_type = request.getParameter("sign_type");
		String sign = request.getParameter("sign");
		String out_trade_no = request.getParameter("out_trade_no");
		String subject = request.getParameter("subject");
		String trade_no = request.getParameter("trade_no");
		String trade_status = request.getParameter("trade_status");
		String seller_id = request.getParameter("seller_id");
		String seller_email = request.getParameter("seller_email");
		String buyer_id = request.getParameter("buyer_id");
		String buyer_email = request.getParameter("buyer_email");
		String quantity = request.getParameter("quantity");
		String price = request.getParameter("price");
		String body = request.getParameter("body");
		String gmt_create = request.getParameter("gmt_create");
		String gmt_payment = request.getParameter("gmt_payment");
		String is_total_fee_adjust = request.getParameter("is_total_fee_adjust");
		String use_coupon = request.getParameter("use_coupon");
		String discount = request.getParameter("discount");
		String refund_status = request.getParameter("refund_status");
		String gmt_refund = request.getParameter("gmt_refund");
		boolean isSign = false;
		try {
			isSign = AlipaySignature.rsaCheckV1(params, AlipayConfig.publicKey, "utf-8");
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		if (AlipayNotify.verify(params) || isSign) {// 验证成功
			if (trade_status.equals("TRADE_FINISHED")) {// 要写的逻辑。自己按自己的要求写
				LogUtil.operationRecord("TRADE_FINISHED" + out_trade_no);
			}
			if (trade_status.equals("TRADE_SUCCESS")) {
				ZFBOrder zFBOrder = new ZFBOrder(notify_time, notify_type, notify_id, sign_type, sign, out_trade_no, subject, payment_type, trade_no,
						trade_status, seller_id, seller_email, buyer_id, buyer_email, Double.parseDouble(total_fee), Integer.parseInt(quantity),
						Double.parseDouble(price), body, gmt_create, gmt_payment, is_total_fee_adjust, use_coupon, discount, refund_status,
						gmt_refund);
				System.out.println(zFBOrder);
				int z = zfbOrderService.insert(zFBOrder);
				System.out.println(z);
				String[] splits = out_trade_no.split("_");
				System.out.println(splits.length);
				if (splits.length < 3) {
					System.out.println(splits.length);
					return "pay/fail.web";
				}
				System.out.println(splits.length < 2);
				String boughtWhat = null;
				switch (splits[1]) {
					case "1":
						boughtWhat = ConfigLocal.pay_orderBook;
						break;
					case "2":
						boughtWhat = ConfigLocal.pay_order;
						break;
				}
				int i = payFacade.finshPaid(splits[0], splits[2], boughtWhat, BaseOrder.payType_ZFB);
				LogUtil.operationRecord(i + "i");
				// int i = payFacade.finishPay(request, zFBOrder);
				if (i > 0) {
					log.error("ok.......");
					// ZFBOrder zFBOrderUp = new ZFBOrder();
					// zFBOrderUp.setZfbId(zFBOrder.getZfbId());
					// zFBOrderUp.setNotify_id(notify_id);
					// int j = zfbOrderService.updateMapperByPK(zFBOrderUp);
					// System.out.println(">>>>>" + out_trade_no + (j == 1));
					return "pay/success.web";
				} else {
					log.error("oNo.......");
					return "pay/fail.web";
				}
			} else {
				LogUtil.operationRecord("trade_status=" + trade_status);
				return "pay/fail.web";
			}
		} else {// 验证失败
			LogUtil.operationRecord("验证shibai");
			return "pay/fail.web";
		}
	}
}
