package com.zd.aoding.outsourcing.weChat.api.utils.file;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: ConfigTemp
 * @Description:临时配置参数
 * @author: zj
 * @date: 2017年3月8日 下午1:14:42
 */
public class ConfigTemp {
	/**
	 * @fieldName: adminPhone
	 * @fieldType: String
	 * @Description: admin手机号
	 */
	public static final String adminPhone = "18357313932";
	/**
	 * @fieldName: refreeLeverAccountIds
	 * @fieldType: int
	 * @Description: 推荐保存线级10级
	 */
	public static final int refreeLeverAccountIds = 10;
	/**
	 * @fieldName: Purse_password
	 * @fieldType: String
	 * @Description:钱包出示密码
	 */
	public static final String Purse_password = "0000000";
	/**
	 * @fieldName: managerAccount_password
	 * @fieldType: String
	 * @Description: 管理员密码
	 */
	public static final String managerAccount_password = "123456";
	/**
	 * @fieldName: adminId
	 * @fieldType: int
	 * @Description: 所有关于admin账号的id
	 */
	public static final int adminId = 1;
	public static final int updateType = 1;
	public static final int insertType = 2;
	/**
	 * @fieldName: agentAllDealerProducts
	 * @fieldType: String
	 * @Description: 代理查询商户产品
	 */
	public static final String agentAllDealerProducts = "agent/allDealerProducts";
	/**
	 * @fieldName: agentAllDealers
	 * @fieldType: String
	 * @Description: 代理查询商户
	 */
	public static final String agentAllDealers = "agent/allDealers";
	/**
	 * @fieldName: customSerAllAdvertising
	 * @fieldType: String
	 * @Description: 客服查看广告
	 */
	public static final String customSerAllAdvertising = "customSer/allAdvertising";
	/**
	 * @fieldName: managerAllDealers
	 * @fieldType: String
	 * @Description: 后台查看商户
	 */
	public static final String managerAllDealers = "manager/allDealers";
	/**
	 * @fieldName: managerAllDealerProducts
	 * @fieldType: String
	 * @Description: 后台查看商户产品
	 */
	public static final String managerAllDealerProducts = "manager/allDealerProducts";
	/**
	 * @fieldName: customSerAllInformation
	 * @fieldType: String
	 * @Description: 代理查询咨询
	 */
	public static final String customSerAllInformation = "customSer/allInformation";
	/**
	 * @fieldName: customSerSaleAfter
	 * @fieldType: String
	 * @Description: 查询售后服务
	 */
	public static final String customSerSaleAfter = "customSer/allSaleAfter";
	/**
	 * @fieldName: customSerAllMallProducts
	 * @fieldType: String
	 * @Description: 客服查看B2C产品
	 */
	public static final String customSerAllMallProducts = "customSer/allMallProducts";
	/**
	 * @fieldName: customSerAllBrank
	 * @fieldType: String
	 * @Description: 客服添加品牌页面
	 */
	public static final String customSerAllBrank = "manager/cusAllBrank";
	/**
	 * @fieldName: dealerIndex
	 * @fieldType: String
	 * @Description: 商家个人信息页面
	 */
	public static final String dealerIndex = "dealer/index";
	/**
	 * @fieldName: activityApplicationPage
	 * @fieldType: String
	 * @Description: 商家活动申请页面
	 */
	public static final String activityApplicationPage = "dealer/activityApplicationPage";
	/**
	 * @fieldName: advertisementApplicationPage
	 * @fieldType: String
	 * @Description: 商家广告发布申请页面
	 */
	public static final String advertisementApplicationPage = "dealer/advertisementApplicationPage";
	/**
	 * @fieldName: clientPage
	 * @fieldType: String
	 * @Description: 商家客户名单页面
	 */
	public static final String clientPage = "dealer/clientPage";
	/**
	 * @fieldName: hongbaoPage
	 * @fieldType: String
	 * @Description: 商家查看红包页面
	 */
	public static final String hongbaoPage = "dealer/hongbaoPage";
	/**
	 * @fieldName: dealerIndex
	 * @fieldType: String
	 * @Description: 商家查看代金券页面
	 */
	public static final String couponPage = "dealer/couponPage";
	/**
	 * @fieldName: allProductsPage
	 * @fieldType: String
	 * @Description: 商家查看产品页面
	 */
	public static final String allProductsPage = "dealer/allProductsPage";
	/**
	 * @fieldName: offLineOrderPage
	 * @fieldType: String
	 * @Description: 商家查看线下订单
	 */
	public static final String offLineOrderPage = "dealer/offLineOrderPage";
	/**
	 * @fieldName: offLineOrderPage
	 * @fieldType: String
	 * @Description: 商家查看线下订单
	 */
	public static final String showroomUpdate = "showroom/showroomUpdatePage";
	/**
	 * @fieldName: all
	 * @fieldType: Integer
	 * @Description: 查询全部
	 */
	public static final Integer all = -1;
	/**
	 * @fieldName: not_required_id
	 * @fieldType: Object
	 * @Description:
	 */
	public static String not_required_id_0 = "0";
	
	/**
	 * 本方法封装了往前台设置的header,contentType等信息
	 * @param message 需要传给前台的数据
	 * @param type 指定传给前台的数据格式,如"html","json"等
	 * @param response HttpServletResponse对象
	 * @throws IOException
	 * @createDate 2010-12-31 17:55:41
	 */
	public static void writeToWeb(String message, String type, HttpServletResponse response) throws IOException {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/" + type + "; charset=utf-8");
		response.getWriter().write(message);
		response.getWriter().close();
	}
	
	/**
	 * @Title: DoubleBigDecimal
	 * @Description: 四舍五入i位小数
	 * @param d
	 * @param i
	 * @return设定文件
	 * @return double 返回类型
	 * 
	 */
	public static double DoubleBigDecimal(Double d, int i) {
		BigDecimal b2 = new BigDecimal(d);
		return b2.setScale(i, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
