package com.zd.aoding.outsourcing.weChat.api.utils.file;

/**
 * 微信支付配置
 * @author and
 *
 */
public class WXConf {
	//公众账号ID 	appid 	是 	String(32) 	wxd678efh567hg6787 	微信分配的公众账号ID（企业号corpid即为此appId）
	//public static String appid = "wx3204923511ed3e99";
	public static String appid = "wx947072dea8c6eee1";
	//商户号 	mch_id 	是 	String(32) 	1230000109 	微信支付分配的商户号
	//public static String mch_id = "1480350902";
	public static String mch_id = "1483680602";
	//支付密钥
	//public static String key="tuanzhuangtuanwangluopjswzsongls";
	public static String key="yzgj2345654321yzgjnjyzgjwlkjyxgs";
	//交易类型
	public static String trade_type="APP";
	//微信支付回调地址,必须为直接访问地址！！！！！！！！！不能有代理和转发
	//public static String notify_url="http://27.54.227.170:8080/cop/pay/payhooks2";
	//public static String notify_url="https://116.62.63.192:8080/pay/wexinPay";
	//public static String notify_url="https://www.wdtzt.com/pay/wexinPay";
	public static String notify_url="http://www.zgnjntcp.com/pay/wexinPay";
//	public static String notify_url="http:/www.jxshzx.com:8080/cop/pay/payhooks2";
//	public static String notify_url="http://csfwqw.6655.la:18888/cop/pay/payhooks2";
//	public static String notify_url="http://wxpay.weixin.qq.com/pub_v2/pay/notify.v2.php";

//	
	public static String _package="Sign=WXPay";
	
	//HTTPS证书的本地路径
	//public static String certLocalPath = "C:/Windows/cop/apiclient_cert.p12";

	//HTTPS证书密码，默认密码等于商户号MCHID
	//public static String certPassword = "1480350902";
	public static String certPassword = "1483680602";
	
}
