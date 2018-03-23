/**
 * 放置所有Url且只放url
 * @页面跳转统一用后缀 Page
 * @其他接口后缀 Url
 * 
 */
/**
 * @param a标签或其他路径跳转 ，
 *            均调用该方法以保证路径全部在改页面
 */
function redirect(redirectUrl) {
	window.location.href = redirectUrl;
}
//var severName = "/ScoreMall-Admin";

/** **************************************file*********************************** */
//http://192.168.1.195:8884
var webUpLoadUrl = "ad/file/webupload.file";
var kindeditorfileuploadUpLoadUrl = "ad/file/kindeditorfileupload.file";

/** 用户注册手机验证码 */
var userRegisterPhoneCode = "pub/phoneRegCode.web";
/** 用户注册 */
var userRegister = "pub/regUser.web";


/** 用户登录 */
var userLoginPage = "login/managerLogin.web";
/** 用户登录 */
var userLoginUrl = "adminLogin.web";
/** 用户登录 */
var logoffUrl = "logoff.web";

var menuListUrl = 'ad/manager/menu/allList.web';
var indexUrl =  "ad/manager/index.web";
/** **********************************************************rolePo********************************************** */

var roleUrl =  'ad/manager/roleUrlTablePage.web';
var roleAddPage =  'ad/manager/roleUrlAddPage.web';
var roleEditPage =  'ad/manager/role/roleUrlEditPage.web';
var roleDTPaingUrl =  'ad/manager/role/DTPaging.web';
var addRoleUrl =  'ad/manager/role/add.web';
var editRoleUrl =  'ad/manager/role/edit.web';
var deleteRoleUrl =  'ad/manager/role/delete.web';
var roleListUrl =  'ad/manager/role/allList.web';
var roleDetailUrl =  'ad/manager/role/detail.web';

var addRoleAccountUrl =  'ad/manager/roleAccount/add.web';
var getRoleAccountDetailUrl =  'ad/manager/roleAccount/detail.web';

/** **********************************************************menuPo********************************************** */
var menuUrl =  'ad/manager/menuUrlTablePage.web';
var menuAddPage =  'ad/manager/menuUrlAddPage.web';
var menuEditPage =  'ad/manager/menu/menuUrlEditPage.web';
var menuDTPaingUrl =  'ad/manager/menu/DTPaging.web';
var addMenuUrl =  'ad/manager/menu/add.web';
var editMenuUrl =  'ad/manager/menu/edit.web';
var deleteMenuUrl =  'ad/manager/menu/delete.web';

/** **********************************************************userPo********************************************** */

var userUrl =  'ad/manager/userUrlTablePage.web';
var UserDTPagingUrl =  'ad/manager/user/DTPaging.web';
var editUserUrl =  'ad/manager/user/edit.web';
var userInfoPage =  'ad/manager/user/infoPage.web';
var userEditPage =  'ad/manager/user/editPage.web';
var resetPassword =  'ad/manager/user/resetPassword.web';
var userDetailUrl =  'ad/manager/user/detail.web';

var accountUrl =  'ad/manager/accountUrlTablePage.web';
var accountDTPaingUrl =  'ad/manager/account/DTPaging.web';
var addAccountUrl =  'ad/manager/account/add.web';
var editAccountUrl =  'ad/manager/account/edit.web';
var deleteAccountUrl =  'ad/manager/account/delete.web';
var getAccountDetailUrl =  'ad/manager/account/detail.web';
var getManagerDetailUrl =  'ad/manager/manager/detail.web';



/** **********************************************************BusinessTypePo********************************************** */
/** 商家类型详情编辑页面 */
var businessTypeUrl =  'ad/manager/businessTypeUrlTablePage.web';
var businessTypeDTPaingUrl =  'ad/manager/businessType/DTPaging.web';
var addBusinessTypeUrl =  'ad/manager/businessType/add.web';
var editBusinessTypeUrl =  'ad/manager/businessType/edit.web';
var deleteBusinessTypeUrl =  'ad/manager/businessType/delete.web';
var getDetailBusinessTypeUrl =  "ad/manager/businessType/detail.web";

/** **********************************************************GoodsModelPo********************************************** */
/** 模块详情编辑页面 */
var editGoodsModelUrl =  'ad/manager/goodsModel/edit.web';

/** **************************************************经销商DistributorPo********************************************** */
var distributorUrl =  'ad/manager/distributorUrlTablePage.web';
var distributorUrlAddPage =  'ad/manager/distributorUrlAddPage.web';
var distributorEditPage =  'ad/manager/distributor/detailPage.web';
var distributorDTPaingUrl =  'ad/manager/distributor/DTPaging.web';
var addDistributorUrl =  'ad/manager/distributor/add.web';
var editDistributorrUrl =  'ad/manager/distributor/edit.web';
var deleteDistributorUrl =  'ad/manager/distributor/delete.web';
var firstChargeUrlEditPage =  'ad/manager/chargeParam/detailPage.web';

var salesmanUrl =  'ad/manager/salesmanUrlTablePage.web';
var salesmanDTPaingUrl =  'ad/manager/salesman/DTPaging.web';
var addSalesmanUrl =  'ad/manager/salesman/add.web';
var editSalesmanUrl =  'ad/manager/salesman/edit.web';


/** **********************************************************AppCompanyPo********************************************** */
/** 查看单个公司 */
var appCompanyPoUrl =  'ad/manager/appCompany/getPo.web';
/** **********************************************************NoticeVo********************************************** */
/** 添加系统消息 */
var noticeAddUrl =  'ad/manager/notice/add.web';
/** 系统消息页面 */
var noticeUrl =  'ad/manager/noticeUrlTablePage.web';
/** 分页查询系统消息 */
var noticeDTPaingUrl =  'ad/manager/notice/DTPaging.web';

/** 经销商系统消息页面 */
var distributorNoticeUrl =  'ad/manager/distributorNoticeUrlTablePage.web';
/** 分页查询经销商消息 */
var distributorNoticeDTPaingUrl =  'ad/manager/distributorNotice/DTPaging.web';
/** 添加经销商消息 */
var distributorNoticeAddUrl =  'ad/manager/distributorNotice/add.web';

/** 商家系统消息页面 */
var businessNoticeUrl =  'ad/manager/businessNoticeUrlTablePage.web';
/** 分页查询商家消息 */
var businessNoticeDTPaingUrl =  'ad/manager/businessNotice/DTPaging.web';
/** 添加商家消息 */
var businessNoticeAddUrl =  'ad/manager/businessNotice/add.web';
/** **********************************************************OrderPo********************************************** */
var orderUrl =  'ad/manager/orderUrlTablePage.web';
var scoreOrderUrl =  'ad/manager/scoreOrderUrlTablePage.web';
var bonusOrderUrl =  'ad/manager/bonusOrderUrlTablePage.web';
var orderDTPaingUrl =  'ad/manager/order/DTPaging.web';
var getOrderDetailurl =  'ad/manager/order/detail.web';
/** 查询快递 */
var expressInfoUrl =  "ad/manager/order/getKdniaoInfo.web";
/** 订单修改 */
var editOrderUrl =  "ad/manager/order/edit.web";
/** 订单地址修改 */
var editOrderAddressUrl =  "ad/manager/order/editAddress.web";
/** 订单详情 */
var orderDetailPage =  "ad/manager/order/detailPage.web";
/** 退货申请页面 */
var returnApplicationUrl =  'ad/manager/returnApplicationUrlTablePage.web';
/** 退货申请页面 */
var returnMoneyApplicationUrl =  'ad/manager/returnMoneyApplicationUrlTablePage.web';
/** 退货申请页面 */
var returnGoodsApplicationUrl =  'ad/manager/returnGoodsApplicationUrlTablePage.web';
/** 分页退货申请 */
var returnApplicationDTPaingUrl =  'ad/manager/returnApplication/DTPaging.web';
/** 修改退货申请状态 */
var editReturnApplicationUrl =  'ad/manager/returnApplication/edit.web';
/** 拒绝退货申请 */
var refuseReturnApplicationUrl =  'ad/manager/returnApplication/refuse.web';
/** 完成退货 */
var finishReturnApplicationUrl =  'ad/manager/returnApplication/finish.web';
/** 查看快递详情 */
var GetOrderTrackInfoUrl =  'ad/manager/returnApplication/getOrderTrackInfo.web';
/** 查看申请详情 */
var returnApplicationDetailPage =  'ad/manager/returnApplication/detailPage.web';

/** **********************************************************systemparamPo********************************************** */
var systemparamUrl =  'ad/manager/system/systemparamUrlTablePage.web';
var editSystemParamUrl =  'ad/manager/systemparam/edit.web';
var editSystemParamStringUrl =  'ad/manager/systemparamStringVal/edit.web';
var managerUpdatePasswordsUrl =  'ad/manager/updatePassword';
/** *********************************************************mailPo********************************************** */
var mailDTPaingUrl =  'ad/manager/mail/DTPaging.web';
var readMailUrl =  'ad/manager/mail/read.web';
/** **********************************************************couponPo********************************************** */
/** 优惠券页面 */
var couponUrl =  'ad/manager/couponUrlTablePage.web';
/** 优惠券分页 */
var couponDTPaingUrl =  'ad/manager/coupon/DTPaging.web';
/** 优惠券添加 */
var couponAddUrl =  'ad/manager/coupon/add.web';
/** 优惠券删除 */
var couponDeleteUrl =  'ad/manager/coupon/delete.web';

/** **********************************************************goodsPo********************************************** */
var goodsAdmin = 'ad/manager/goodsAdminTablePage.web';

/** 系统日志页面 */
var recordUrl =  'ad/manager/recordUrlTablePage.web';
/** 分页查询系统日志 */
var recordDTPaingUrl =  'ad/manager/record/DTPaging.web';

/** 用户修改积分 */
var updateUserScore ="ad/manager/userScore/edit.web";

/** 提现页面 */
var takeMoneyUrl = 'ad/manager/takeMoneyUrlTablePage.web';
/** 查看提现分页 */
var takeMoneyDTPagingUrl= "ad/manager/takeMoney/getPage.web";
/** 审核 */
var checkState = "ad/manager/takeMoney/edit.web";

/** **********************************************************agreementPo********************************************** */

var chargeParamAddUrl = 'ad/manager/chargeParam/add.web';
var chargeParamEditUrl = 'ad/manager/chargeParam/edit.web';

/** **************************************************商家businessPo********************************************** */
/** 商家页面 */
var businessUrl =  'ad/manager/businessUrlTablePage.web';
/** 分页 */
var businessDTPaingUrl =  'ad/manager/business/DTPaging.web';
/** 添加 */
var businessAddUrl = 'ad/manager/business/add.web';
/** 修改 */
var businessEditUrl = 'ad/manager/business/edit.web';
/** 删除 */
var businessDeletedUrl = 'ad/manager/business/delete.web';
/** 修改页面 */
var businessEditPage = 'ad/manager/business/businessEdit.web';
/** 添加页面 */
var businessAddPage = 'ad/manager/businessUrlAddPage.web';

/** 查询全部省 */
var regionAllList = 'ad/manager/region/allList.web';
/** 查询全部商家类型 */
var businessTypeAllList = 'ad/manager/businessType/allList.web';
