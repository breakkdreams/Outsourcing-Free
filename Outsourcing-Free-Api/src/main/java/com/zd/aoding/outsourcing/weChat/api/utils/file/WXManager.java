package com.zd.aoding.outsourcing.weChat.api.utils.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.zd.aoding.common.Md5.MD5Util;
import com.zd.aoding.common.log.LogUtil;

/**
 * 微信支付管理器 步骤1：用户在商户APP中选择商品，提交订单，选择微信支付。 步骤2：商户后台收到用户支付单，调用微信支付统一下单接口。参见【统一下单API】。
 * 步骤3：统一下单接口返回正常的prepay_id，再按签名规范重新生成签名后，将数据传输给APP。参与签名的字段名为appId，partnerId，prepayId，nonceStr，
 * timeStamp，package。注意：package的值格式为Sign=WXPay 步骤4：商户APP调起微信支付。api参见本章节【app端开发步骤说明】
 * 步骤5：商户后台接收支付通知。api参见【支付结果通知API】 步骤6：商户后台查询支付结果。，api参见【查询订单API】
 * @author and
 */
@Service
public class WXManager {
	public static String SUCCESS = "SUCCESS";
	public static String FAIL = "FAIL";

	// 生成随机字符串长度在20-32之间
	public static String generateRandom() throws Exception {
		String random = null;
		long nt = System.currentTimeMillis() + System.nanoTime();
		Random rand = new Random(nt);
		int seed = rand.nextInt(Integer.MAX_VALUE);
		int i = seed % 13;
		random = MD5Util.MD5(String.valueOf(seed));
		random = random.substring(12);
		return random;
	}
	/**
	 * 为统一下单 生成签名
	 * @param appid 公众账号ID String(32)
	 * @param mch_id 商户号 String(32)
	 * @param nonce_str 随机字符串 String(32)
	 * @param body 商品描述 String(32)
	 * @param attach 附加数据 String(127)
	 * @param out_trade_no 商户订单号 String(32)
	 * @param total_fee 总金额 int 单位为分
	 * @param spbill_create_ip 终端IP String(16)
	 * @param notify_url 通知地址 String(256)
	 * @param trade_type 交易类型 String(16)
	 */
	public String generateSign(String appid, String mch_id, String nonce_str, String body, String attach, String out_trade_no, int total_fee,
			String spbill_create_ip, String notify_url, String trade_type) throws Exception {
		String sign = null;
		SortedMap<String, Object> params = new TreeMap<String, Object>();
		params.put("appid", appid);
		params.put("mch_id", mch_id);
		params.put("nonce_str", nonce_str);
		params.put("body", body);
		params.put("attach", attach);
		params.put("out_trade_no", out_trade_no);
		params.put("total_fee", total_fee);
		params.put("spbill_create_ip", spbill_create_ip);
		params.put("notify_url", notify_url);
		params.put("trade_type", trade_type);
		StringBuffer buf = new StringBuffer();
		for (String k : params.keySet()) {
			buf.append(k);
			buf.append("=");
			buf.append(params.get(k));
			buf.append("&");
		}
		buf.append("key=");
		buf.append(WXConf.key);
		String temp = buf.toString();
		sign = MD5Util.MD5(temp).toUpperCase();
		LogUtil.operationRecord("sign生成参数---" + buf.toString());
		return sign;
	}

	// 统一下单接口
	private static String unifiedorderPath = "https://api.mch.weixin.qq.com/pay/unifiedorder";

	/*
	 * 统一下单 字段名 变量名 必填 类型 示例值 描述 公众账号ID appid 是 String(32) wxd678efh567hg6787
	 * 微信分配的公众账号ID（企业号corpid即为此appId） 商户号 mch_id 是 String(32) 1230000109 微信支付分配的商户号 设备号 device_info
	 * 否 String(32) 013467007045764 终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB" 随机字符串 nonce_str 是
	 * String(32) 5K8264ILTKCH16CQ2502SI8ZNMTM67VS 随机字符串，不长于32位。推荐随机数生成算法 签名 sign 是 String(32)
	 * C380BEC2BFD727A4B6845133519F3AD6 签名，详见签名生成算法 商品描述 body 是 String(32) Ipad mini 16G 白色
	 * 商品或支付单简要描述 商品详情 detail 否 String(8192) Ipad mini 16G 白色 商品名称明细列表 附加数据 attach 否 String(127)
	 * 深圳分店 附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据 商户订单号 out_trade_no 是 String(32) 20150806125346
	 * 商户系统内部的订单号,32个字符内、可包含字母, 其他说明见商户订单号 货币类型 fee_type 否 String(16) CNY 符合ISO
	 * 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型 总金额 total_fee 是 Int 888 订单总金额，单位为分，详见支付金额 终端IP
	 * spbill_create_ip 是 String(16) 123.12.12.123 APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。 交易起始时间
	 * time_start 否 String(14) 20091225091010
	 * 订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则 交易结束时间 time_expire 否
	 * String(14) 20091227091010
	 * 订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。其他详见时间规则 注意：最短失效时间间隔必须大于5分钟
	 * 商品标记 goods_tag 否 String(32) WXG 商品标记，代金券或立减优惠功能的参数，说明详见代金券或立减优惠 通知地址 notify_url 是 String(256)
	 * http://www.weixin.qq.com/wxpay/pay.php 接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。 交易类型
	 * trade_type 是 String(16) JSAPI 取值如下：JSAPI，NATIVE，APP，详细说明见参数规定 商品ID product_id 否 String(32)
	 * 12235413214070356458058 trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义。 指定支付方式 limit_pay 否
	 * String(32) no_credit no_credit--指定不能使用信用卡支付 用户标识 openid 否 String(128)
	 * oUpF8uMuAJO_M2pxb1Q9zNjWeS6o
	 * trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识。openid如何获取，可参考【获取openid】。企业号请使用【企业号OAuth2.0接口】
	 * 获取企业号内成员userid，再调用【企业号userid转openid接口】进行转换
	 */
	/**
	 * appid 公众账号ID String(32) mch_id 商户号 String(32) nonce_str 随机字符串 String(32) sign 签名 String(32)
	 * notify_url 通知地址 String(256)
	 * @param body 商品描述 String(32)
	 * @param attach 附加数据 String(127)
	 * @param out_trade_no 商户订单号 String(32)
	 * @param total_fee 总金额 int 单位为分
	 * @param spbill_create_ip 终端IP String(16)
	 * @param trade_type 交易类型 String(16)
	 * @throws IOException
	 * @throws DocumentException
	 */
	public String unifiedorder(String body, String attach, String out_trade_no, int total_fee, String spbill_create_ip, String trade_type)
			throws IOException, DocumentException {
		String prepay_id = null;// 预处理订单id
		String appid = WXConf.appid;
		String mch_id = WXConf.mch_id;
		String notify_url = WXConf.notify_url;
		String nonce_str = null;
		try {
			nonce_str = generateRandom();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sign = null;
		try {
			sign = generateSign(appid, mch_id, nonce_str, body, attach, out_trade_no, total_fee, spbill_create_ip, notify_url, trade_type);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SortedMap<String, Object> params = new TreeMap<String, Object>();
		params.put("appid", appid);
		params.put("mch_id", mch_id);
		params.put("nonce_str", nonce_str);
		params.put("body", body);
		params.put("attach", attach);
		params.put("out_trade_no", out_trade_no);
		params.put("total_fee", total_fee);
		params.put("spbill_create_ip", spbill_create_ip);
		params.put("notify_url", notify_url);
		params.put("trade_type", trade_type);
		params.put("sign", sign);
		LogUtil.error("微信支付参数前" + new Gson().toJson(params), WXManager.class);
		Document d = XMLUtil.map2xml(params);
		URL url = new URL(unifiedorderPath);
		URLConnection conn = url.openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);
		OutputStream os = conn.getOutputStream();
		XMLUtil.writeXml2OutputStream(d, os, false);
		Document doc = XMLUtil.readXml4InputStream(conn.getInputStream());
		Element root = doc.getRootElement();
		Element returnCode = root.element("return_code");
		if ("SUCCESS".equals(returnCode.getTextTrim())) {
			Element resultCode = root.element("result_code");
			if ("SUCCESS".equals(resultCode.getTextTrim())) {
				Element prepayId = root.element("prepay_id");
				LogUtil.warn("预下单成功" + prepayId.getTextTrim(), WXManager.class);
				prepay_id = prepayId.getTextTrim();
			} else {
				Element errCode = root.element("err_code");
				Element errCodeDes = root.element("err_code_des");
				LogUtil.warn("预下单失败，错误代码[" + errCode.getTextTrim() + "  /  " + resultCode.getTextTrim() + "]:" + errCodeDes.getTextTrim(),
						WXManager.class);
			}
		} else {// 失败
			Element returnMsg = root.element("return_msg");
			LogUtil.warn(("预下单失败:" + returnMsg.getTextTrim()), WXManager.class);
		}
		return prepay_id;
	}
	
	
	
	// app预支付接口
	public Map<String, Object> prepay(String prepayId) {
		String appId = WXConf.appid;
		String partnerId = WXConf.mch_id;
		String nonceStr = null;
		try {
			nonceStr = generateRandom();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		long timeStamp = System.currentTimeMillis() / 1000;
		String _package = WXConf._package;
		String sign = null;
		try {
			sign = generateSign42(appId, partnerId, nonceStr, timeStamp, prepayId, _package);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Object> params = new TreeMap<String, Object>();
		params.put("appid", appId);
		params.put("partnerid", partnerId);
		params.put("noncestr", nonceStr);
		params.put("timestamp", timeStamp);
		params.put("prepayid", prepayId);
		params.put("package", _package);
		params.put("sign", sign);
		params.put("prepayId", prepayId);
		return params;
	}
	// 二次签名
	public static String generateSign42(String appId, String partnerId, String nonceStr, long timeStamp, String prepayId, String _package)
			throws Exception {
		String sign = null;
		SortedMap<String, Object> params = new TreeMap<String, Object>();
		params.put("appid", appId);
		params.put("partnerid", partnerId);
		params.put("noncestr", nonceStr);
		params.put("timestamp", timeStamp);
		params.put("prepayid", prepayId);
		params.put("package", _package);
		StringBuffer buf = new StringBuffer();
		for (String k : params.keySet()) {
			buf.append(k);
			buf.append("=");
			buf.append(params.get(k));
			buf.append("&");
		}
		buf.append("key=");
		buf.append(WXConf.key);
		String temp = buf.toString();
		sign = MD5Util.MD5(temp).toUpperCase();
		return sign;
	}
	// 成功返回
	public static void success(OutputStream os) throws DocumentException, IOException {
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("return_code", WXManager.SUCCESS);
		Document rd = XMLUtil.map2xml(res);
		XMLUtil.writeXml2OutputStream(rd, os, false);
		os.close();
	}
	// 失败返回
	public static void fail(OutputStream os) throws DocumentException, IOException {
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("return_code", WXManager.FAIL);
		Document rd = XMLUtil.map2xml(res);
		XMLUtil.writeXml2OutputStream(rd, os, false);
		os.close();
	}
	// 回调签名认证
	public static boolean callbackSign(Document d) {
		boolean result = false;
		String sign = null;
		SortedMap<String, String> params = XMLUtil.xml2smap(d);
		StringBuffer buf = new StringBuffer();
		for (String k : params.keySet()) {
			if (!"sign".equals(k)) {
				buf.append(k);
				buf.append("=");
				buf.append(params.get(k));
				buf.append("&");
			} else {
				sign = params.get(k);
			}
		}
		buf.append("key=");
		buf.append(WXConf.key);
		String temp = buf.toString();
		String sign2 = MD5Util.MD5(temp).toUpperCase();
		System.out.println("sign=" + sign);
		System.out.println("sign2=" + sign2);
		if (sign != null && sign.equals(sign2)) {
			result = true;
		}
		return result;
	}
	/**
	 * 为退款 生成签名
	 * @param appid 公众账号ID String(32)
	 * @param mch_id 商户号 String(32)
	 * @param nonce_str 随机字符串 String(32)
	 * @param body 商品描述 String(32)
	 * @param attach 附加数据 String(127)
	 * @param out_trade_no 商户订单号 String(32)
	 * @param total_fee 总金额 int 单位为分
	 * @param spbill_create_ip 终端IP String(16)
	 * @param notify_url 通知地址 String(256)
	 * @param trade_type 交易类型 String(16)
	 */
	public static String generateSign4Refund(String appid, String mch_id, String nonce_str, String transaction_id, String out_refund_no,
			Integer total_fee, Integer refund_fee, String refund_fee_type, String op_user_id) {
		String sign = null;
		SortedMap<String, Object> params = new TreeMap<String, Object>();
		params.put("appid", appid);
		params.put("mch_id", mch_id);
		params.put("nonce_str", nonce_str);
		params.put("transaction_id", transaction_id);
		params.put("out_refund_no", out_refund_no);
		params.put("total_fee", total_fee);
		params.put("refund_fee", refund_fee);
		params.put("refund_fee_type", refund_fee_type);
		params.put("op_user_id", op_user_id);
		StringBuffer buf = new StringBuffer();
		for (String k : params.keySet()) {
			buf.append(k);
			buf.append("=");
			buf.append(params.get(k));
			buf.append("&");
		}
		buf.append("key=");
		buf.append(WXConf.key);
		String temp = buf.toString();
		sign = MD5Util.MD5(temp).toUpperCase();
		System.out.println("---------------------------");
		System.out.println(sign);
		System.out.println(temp);
		System.out.println("---------------------------");
		return sign;
	}
	/*
	 * 公众账号ID appid 是 String(32) wx8888888888888888 微信分配的公众账号ID（企业号corpid即为此appId） 商户号 mch_id 是
	 * String(32) 1900000109 微信支付分配的商户号 设备号 device_info 否 String(32) 013467007045764 终端设备号 随机字符串
	 * nonce_str 是 String(32) 5K8264ILTKCH16CQ2502SI8ZNMTM67VS 随机字符串，不长于32位。推荐随机数生成算法 签名 sign 是
	 * String(32) C380BEC2BFD727A4B6845133519F3AD6 签名，详见签名生成算法 微信订单号 transaction_id 二选一 String(28)
	 * 1217752501201407033233368018 微信生成的订单号，在支付通知中有返回 商户订单号 out_trade_no String(32)
	 * 1217752501201407033233368018 商户侧传给微信的订单号 商户退款单号 out_refund_no 是 String(32)
	 * 1217752501201407033233368018 商户系统内部的退款单号，商户系统内部唯一，同一退款单号多次请求只退一笔 总金额 total_fee 是 Int 100
	 * 订单总金额，单位为分，只能为整数，详见支付金额 退款金额 refund_fee 是 Int 100 退款总金额，订单总金额，单位为分，只能为整数，详见支付金额 货币种类
	 * refund_fee_type 否 String(8) CNY 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型 操作员 op_user_id
	 * 是 String(32) 1900000109 操作员帐号, 默认为商户号
	 */
	// 微信退款
	/*public static boolean requestRefund(WXOrder wxOrder, Integer money) {
		boolean result = false;
		try {
			// WXOrderBack wxOrderBack = new WXOrderBack(wxOrder, money);
			// Document d = wxOrderBack.toXML();
			Document d = null;
			String refundPath = "https://api.mch.weixin.qq.com/secapi/pay/refund";// 退款接口
			URL url = new URL(refundPath);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setConnectTimeout(30000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			FileInputStream instream = new FileInputStream(new File(WXConf.certLocalPath));// 加载本地的证书进行https加密传输
			try {
				keyStore.load(instream, WXConf.certPassword.toCharArray());// 设置证书密码
			} catch (CertificateException e) {
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} finally {
				instream.close();
			}
			// Trust own CA and all self-signed certs
			SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, WXConf.certPassword.toCharArray()).useTLS().build();
			// Allow TLSv1 protocol only
			SSLSocketFactory sf = sslcontext.getSocketFactory();
			conn.setSSLSocketFactory(sf);
			OutputStream os = conn.getOutputStream();
			XMLUtil.writeXml2OutputStream(d, os, false);
			Document doc = XMLUtil.readXml4InputStream(conn.getInputStream());
			Element root = doc.getRootElement();
			Element returnCode = root.element("return_code");
			if ("SUCCESS".equals(returnCode.getTextTrim())) {
				Element resultCode = root.element("result_code");
				if ("SUCCESS".equals(resultCode.getTextTrim())) {
					LogUtil.operationRecord("微信-退款成功");
					result = true;
				} else {
					Element errCode = root.element("err_code");
					Element errCodeDes = root.element("err_code_des");
					LogUtil.operationRecord("微信-退款失败，错误代码[" + errCode.getTextTrim() + "]:" + errCodeDes.getTextTrim());
				}
			} else {// 失败
				Element returnMsg = root.element("return_msg");
				LogUtil.operationRecord("微信-退款失败:" + returnMsg.getTextTrim());
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.operationError("微信退款异常", WXManager.class, e);
		}
		return result;
	}*/
	public static void main(String[] args) throws Exception {
		String refundPath = "http://www.yijqi.com/cop/pay/payhooks2";// 退款接口
		// String refundPath = "http://127.0.0.1:18888/cop/pay/payhooks2";//退款接口
		SortedMap<String, Object> params = new TreeMap<String, Object>();
		params.put("return_code", "FAIL");
		params.put("return_msg", "签名失败");
		Document d = XMLUtil.map2xml2(params);
		URL url = new URL(refundPath);
		URLConnection conn = url.openConnection();
		conn.setConnectTimeout(30000);
		conn.setDoOutput(true);
		conn.setDoInput(true);
		OutputStream os = conn.getOutputStream();
		XMLUtil.writeXml2OutputStream(d, os, false);
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String temp = null;
		while ((temp = reader.readLine()) != null) {
			System.out.println(temp);
		}
		System.out.println("---------------------------");
		System.out.println("end");
	}
}
