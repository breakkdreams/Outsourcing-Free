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
var userLoginUrl = "adminLogn.web";
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

/** **********************************************************catagory********************************************** */
/** 分类管理页面 */
var categoryUrl =  "ad/manager/categoryUrlTablePage.web";
/** 添加分类页面 */
var addCatagoryPage =  "ad/manager/category/addPage.web";
/** 分类详情编辑页面 */
var catagoryDetailPage =  "ad/manager/category/detailPage.web";
/** 查询全部分类 */
var catagoryAllListUrl =  "ad/manager/category/allList.web";
/** 查询全部一级分类 */
var firstCategoryListUrl =  "ad/manager/category/allFirstList.web";
/** 分类主页 */
var catagoryIdexUrl =  "ad/manager/category/DTPaging.web";
/** 分类详情 */
var catagoryDetailUrl =  "ad/manager/category/detail.web";
/** 分类新增 */
var addCatagoryUrl =  "ad/manager/category/add.web";
/** 分类更新 */
var editCatagoryUrl =  "ad/manager/category/edit.web";
/** 分类首页更新 */
var editCatagoryIndexUrl =  "ad/manager/categoryIndex/edit.web";
/** 删除分类 */
var deletedCatagoryUrl =  "ad/manager/category/delete.web";

/** **********************************************************GoodsTypePo********************************************** */
/** 商品类型详情编辑页面 */
var typeDetailPage =  "ad/manager/type/detailPage.web";
var goodsTypeUrl =  'ad/manager/goodsTypeUrlTablePage.web';
var goodsTypeDTPaingUrl =  'ad/manager/goodsType/DTPaging.web';
var editGoodsTypeUrl =  'ad/manager/goodsType/edit.web';
var getDetailGoodsTypeUrl =  "ad/manager/goodsType/detail.web";
var getAllGoodsTypeListUrl =  "ad/manager/goodsType/allList.web";
/** **********************************************************BusinessTypePo********************************************** */
/** 商家类型详情编辑页面 */
var businessTypeUrl =  'ad/manager/businessTypeUrlTablePage.web';
var businessTypeDTPaingUrl =  'ad/manager/businessType/DTPaging.web';
var addBusinessTypeUrl =  'ad/manager/businessType/add.web';
var editBusinessTypeUrl =  'ad/manager/businessType/edit.web';
var deleteBusinessTypeUrl =  'ad/manager/businessType/delete.web';
var getDetailBusinessTypeUrl =  "ad/manager/businessType/detail.web";
var getAllBusinessTypeListUrl =  "ad/manager/businessType/allList.web";

/** **********************************************************GoodsModelPo********************************************** */
/** 商品评价详情编辑页面 */
var commentDetailPage =  "ad/manager/comment/detailPage.web";
var goodsCommentUrl =  'ad/manager/goodsCommentUrlTablePage.web';
var goodsCommentDTPaingUrl =  'ad/manager/goodsComment/DTPaging.web';
var editGoodsCommentUrl =  'ad/manager/goodsComment/edit.web';
var deleteGoodsCommentUrl =  'ad/manager/goodsComment/delete.web';
var getDetailGoodsCommentUrl =  "ad/manager/goodsComment/detail.web";

/** **********************************************************GoodsModelPo********************************************** */
/** 模块详情编辑页面 */
var modelDetailPage =  "ad/manager/model/detailPage.web";
var goodsModelUrl =  'ad/manager/goodsModelUrlTablePage.web';
var goodsModelDTPaingUrl =  'ad/manager/goodsModel/DTPaging.web';
var addGoodsModelUrl =  'ad/manager/goodsModel/add.web';
var editGoodsModelUrl =  'ad/manager/goodsModel/edit.web';
var deleteGoodsModelUrl =  'ad/manager/goodsModel/delete.web';
var getDetailGoodsModelUrl =  "ad/manager/goodsModel/detail.web";
var getAllGoodsModelListUrl =  "ad/manager/goodsModel/allList.web";

/** **********************************************************GoodsPo********************************************** */
/** 库存管理页面 */
var goodsUrl =  'ad/manager/goodsUrlTablePage.web';
/** 产品管理页面 */
var goodsGoodsUrl =  'ad/manager/goodsGoodsUrlTablePage.web';
var goodsPa =  'ad/manager/goodsPaTablePage.web';
var goodsDTPaingUrl =  'ad/manager/goods/DTPaging.web';
var getAllGoodsUrl =  'ad/manager/goods/allList.web';
var getGoodsDetailPage =  'ad/manager/goods/goodsDetailPage.web';
var agreeGoodsUrl =  'ad/manager/goods/agree.web';
var refuseGoodsUrl =  'ad/manager/goods/refuse.web';
var goodsShangjiaUrl =  'ad/manager/goods/shangjia.web';
var goodsXiajiaUrl =  'ad/manager/goods/xiajia.web';
var goodsZeroUrl =  'ad/manager/goods/zero.web';
var goodsPositionEditUrl =  'ad/manager/goods/position.web';
var goodsExcelUrl =  'ad/manager/goods/importExcel.web';

/** **************************************************经销商DistributorPo********************************************** */
var distributorUrl =  'ad/manager/distributorUrlTablePage.web';
var distributorUrlAddPage =  'ad/manager/distributorUrlAddPage.web';
var distributorEditPage =  'ad/manager/distributor/detailPage.web';
var distributorDTPaingUrl =  'ad/manager/distributor/DTPaging.web';
var addDistributorUrl =  'ad/manager/distributor/add.web';
var editDistributorrUrl =  'ad/manager/distributor/edit.web';
var deleteDistributorUrl =  'ad/manager/distributor/delete.web';
var firstChargeUrlEditPage =  'ad/manager/chargeParam/detailPage.web';

/** **************************************************商家businessPo********************************************** */
var businessUrl =  'ad/manager/businessUrlTablePage.web';
var businessDTPaingUrl =  'ad/manager/business/DTPaging.web';


/** **********************************************************SupplierPo********************************************** */
var supplierUrl =  'ad/manager/supplierUrlTablePage.web';
var supplerAddPage =  'ad/manager/supplier/editPage.web';
var supplierDTPaingUrl =  'ad/manager/supplier/DTPaging.web';
var addSupplierUrl =  'ad/manager/supplier/add.web';
var editSupplierUrl =  'ad/manager/supplier/edit.web';
var deleteSupplierUrl =  'ad/manager/supplier/delete.web';
var getSupplierDetailurl =  'ad/manager/supplier/detail.web';

/** **********************************************************DealerPo********************************************** */
var dealerUrl =  'ad/manager/dealerUrlTablePage.web';
var dealerAddPage =  'ad/manager/dealer/editPage.web';
var dealerDTPaingUrl =  'ad/manager/dealer/DTPaging.web';
var dealerListUrl =  'ad/manager/dealer/list.web';
var addDealerUrl =  'ad/manager/dealer/add.web';
var editDealerUrl =  'ad/manager/dealer/edit.web';
var deleteDealerUrl =  'ad/manager/dealer/delete.web';
var getDealerDetailurl =  'ad/manager/dealer/detail.web';
var dealerPurseDetailurl =  'ad/manager/dealerPurse/detail.web';

/** **********************************************************AppCompanyPo********************************************** */
var appCompanyUrl =  'ad/manager/appCompanyUrlTablePage.web';
var tongjiUrl =  'ad/manager/tongjiUrlTablePage.web';
var appCompanyAddPage =  'ad/manager/appCompany/editPage.web';
var appCompanyDTPaingUrl =  'ad/manager/appCompany/DTPaging.web';
var companyPurseDTPaingUrl =  'ad/manager/companyPurse/DTPaging.web';
/** 查看全部公司 */
var appCompanyListUrl =  'ad/manager/appCompany/getList.web';
/** 查看单个公司 */
var appCompanyPoUrl =  'ad/manager/appCompany/getPo.web';
var companyPursePoUrl =  'ad/manager/companyPurse/getPo.web';
var addAppCompanyUrl =  'ad/manager/appCompany/add.web';
var editAppCompanyUrl =  'ad/manager/appCompany/edit.web';
var deleteAppCompanyUrl =  'ad/manager/appCompany/delete.web';
/** **********************************************************BillPo********************************************** */
/** 货品账单页面 */
var billUrl =  'ad/manager/billUrlTablePage.web';
/** 分页查询货品账单 */
var billDTPaingUrl =  'ad/manager/bill/DTPaging.web';
/** 结算账单 */
var billSettlementUrl =  'ad/manager/bill/settlement.web';
/** 上传凭证 */
var billUploadUrl =  'ad/manager/bill/upload.web';
/** **********************************************************ScoreRecordPo********************************************** */
/** 积分转入记录页面 */
var scoreRecordUrl =  'ad/manager/scoreRecordUrlTablePage.web';
/** 积分转入记录分页 */
var scoreRecordDTPaingUrl =  'ad/manager/scoreRecord/DTPaging.web';
/** 奖金转入记录页面 */
var bonusRecordUrl =  'ad/manager/bonusRecordUrlTablePage.web';
/** 奖金转入记录分页 */
var bonusRecordDTPaingUrl =  'ad/manager/bonusRecord/DTPaging.web';
/** **********************************************************RechargeApplicationVo********************************************** */
/** 打款审核页面 */
var rechargeApplicationUrl =  'ad/manager/rechargeApplicationUrlTablePage.web';
/** 分页查询打款审核 */
var rechargeApplicationDTPaingUrl =  'ad/manager/rechargeApplication/DTPaging.web';
/** 拒绝打款 */
var rechargeApplicationRefuseUrl =  'ad/manager/rechargeApplication/refuse.web';
/** 确认打款 */
var rechargeApplicationAgreeUrl =  'ad/manager/rechargeApplication/agree.web';
/** 申请充值 */
var rechargeApplicationApplicationUrl =  'ad/manager/rechargeApplication/application.web';
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
var goodsAdminDTPaingUrl = 'ad/manager/dealer/goods/DTPaging.web';
var goodsAdminAddPage = 'ad/manager/goodsAdminAddPage.web';
var goodsAdminEditPage = 'ad/manager/dealer/goods/detailPage.web';

//var goodsUrl = 'ad/manager/supplier/goodsPage.web';
var getGoodsAllUrl = 'ad/manager/dealer/goods/allList.web';
//var addGoodsUrl = 'ad/manager/supplier/goods/add.web';

/** 查询产品分类 */
var dealerShowGoodsCategoryUrl= "ad/manager/dealer/catagory/list.web";
/** 添加产品 */
var dealerAddGoodsUrl = "ad/manager/dealer/goods/add.web";
/** 编辑产品 */
var dealerEditGoodsUrl = "ad/manager/dealer/goods/edit.web";
/** 查询产品表格分页 */
var goodsSpecTablePageUrl= "ad/manager/dealer/goodsSpecTablePage.web";
/** 自动生成产品配置属性组合 */
var goodsOptionDTPagingUrl = "ad/manager/dealer/goodsOption/DTPaging.web";
/** 增加产品配置属性 */
var goodsSpecItemAddUrl = "ad/manager/dealer/goodsSpecItem/add.web";
/** 增加产品配置 */
var goodsSpecAddUrl = "ad/manager/dealer/goodsSpec/add.web";
/** 删除产品配置 */
var goodsSpecDeleteUrl = "ad/manager/dealer/goodsSpec/delete.web";
/** 配置属性删除 */
var goodsSpecItemDeletedUrl = "ad/manager/dealer/goodsSpecItem/delete.web";
/** 修改产品配置 */
var goodsSpecEditUrl = "ad/manager/dealer/goodsSpec/edit.web";
/** 增加产品配置属性 */
var goodsSpecItemEditUrl = "ad/manager/dealer/goodsSpecItem/edit.web";
/**  自动生成产品配置属性组合 */
var goodsOptionAutoAddUrl = "ad/manager/dealer/goodsOption/autoAdd.web";
/** 产品配置属性组合编辑 */
var goodsOptionEditUrl = "ad/manager/dealer/goodsOption/edit.web";
/** 产品配置属性组合编辑 */
var goodsOptionDetailUrl = "ad/manager/dealer/goodsOption/detail.web";
/** 修改产品配置组合图片 */
var goodsEditOptionImgUrl = "ad/manager/dealer/goodsOption/editImg.web";
/** 改变产品状态 */
var changeGoodsStatusUrl = "ad/manager/dealer/goods/change/status.web";
/** 查看全部产品模块 */
var dealerGetGoodsModelUrl= "ad/manager/dealer/goodsModel/list.web";
/** 产品属性编辑页面 */
var goodsParamUrlPage = "ad/manager/dealer/goodsParamTablePage.web";
/** 产品属性编辑页面 */
var goodsParamDTPagingUrl = "ad/manager/dealer/goodsParam/DTPaging.web";
/** 产品属性编辑页面 */
var goodsParamAddUrl = "ad/manager/dealer/goodsParam/add.web";
/** 产品属性修改 */
var goodsParamEditUrl = "ad/manager/dealer/goodsParam/edit.web";
/** 产品属性删除 */
var goodsParamDeleteUrl = "ad/manager/dealer/goodsParam/delete.web";
/** 删除产品 */
var deletedGoodsUrl = "ad/manager/dealer/goods/delete.web";

/** **********************************************************bannerPo********************************************** */
var bannerUrl = 'ad/manager/bannerUrlTablePage.web';
/** 查看轮播图分页 */
var bannerDTPagingUrl= "ad/manager/banner/DTPaging.web";
/** 修改轮播图页面 */
var appCompanyBannerEditPageUrl = "ad/manager/dealer/bannerEdit.web";
/** 删除轮播图 */
var appCompanyBannerDeletedUrl = "ad/manager/banner/delete.web";
/** 添加轮播图页面 */
var appCompanyBannerAddPageUrl = "ad/manager/bannerUrlAddPage.web";
/** 添加轮播图 */
var appCompanyBannerAddUrl = "ad/manager/banner/add.web";
/** 修改轮播图 */
var appCompanyBannerEditUrl = "ad/manager/banner/edit.web";

/** 产品销量 */
var goodsOrderUrl = "ad/manager/goodsOrderUrlTablePage.web";
/** 当日订单 */
var goodsOrderUrlToday = "ad/manager/goodsOrderUrl.web";
/** 产品销量页面 */
var goodsSaleReportUrl = "ad/manager/goodsOrderUrlSaleReport.web";
/** 产品销量分页 */
var goodsOrderDTPagingUrl = "ad/manager/orderReport/DTPaging.web";
/** 产品统计 */
var reportCountUrl = "ad/manager/countReport/DTPaging.web";
/** 产品统计分页 */
//var goodsPurSumUrl = "ad/manager/dealer/goodsPurSum/DTPaging.web";
var goodsPurSumUrl = "ad/manager/goodsReportDT/DTPaging.web";
/** 历史统计分页 */
var historyReportUrl = "ad/manager/statistic/DTPaging.web";
/** 昨日统计 */
var yesterdayReportUrl = "ad/manager/statistic/detail.web";
/** 订单item分页 */
var orderItemUrl = "ad/manager/orderItem/DTPaging.web";
/** 订单item页面 */
var orderItemPage = "ad/manager/goodsOrderUrlItemTablePage.web";
/** 商品首页展示 */
var goodsPositionUrl = "ad/manager/dealer/goodsPosition/edit.web";
/** 商品返利展示 */
var goodsChosenUrl = "ad/manager/dealer/goodsChosen/edit.web";

var categoryGoodsSpecRelationAddOrUpdateUrl = 'ad/manager/categoryGoodsSpecRelation/addOrUpdate.web';
var categoryGoodsSpecRelationDeleteUrl = 'ad/manager/categoryGoodsSpecRelation/delete.web';
/** 分类配置管理页面 */
var categoryGoodsSpecPage = "ad/manager/category/goodsSpecPage.web";

/** 客户添加关于我们 */
var appCompanyAddAboutOurUrl = "ad/manager/appCompany/abountOur/add.web";
/** 客户修改关于我们 */
var appCompanyEditAboutOurUrl = "ad/manager/appCompany/abountOur/edit.web";
/** 客户查看关于我们页面 */
var appCompanyAboutOurPageUrl = "ad/manager/appCompany/aboutOurPage.web";
/** 客户查看关于我们 */
var appCompanyGetAboutOurUrl = "ad/manager/appCompany/abountOur/detail.web";
/** 客户查看关于我们页面 */
var aboutOurPage = "ad/manager/aboutOurEdit/edit.web";

/** 系统日志页面 */
var recordUrl =  'ad/manager/recordUrlTablePage.web';
/** 分页查询系统日志 */
var recordDTPaingUrl =  'ad/manager/record/DTPaging.web';

/** 产品图片修改 */
var dealerUpdateImgUrl="ad/manager/dealer/goods/updateImg.web";
/** 产品图片删除 */
var dealerDeleteImgUrl="ad/manager/dealer/goods/deleteImg.web";

/** 用户修改积分 */
var updateUserScore ="ad/manager/userScore/edit.web";

/** 提现页面 */
var takeMoneyUrl = 'ad/manager/takeMoneyUrlTablePage.web';
/** 查看提现分页 */
var takeMoneyDTPagingUrl= "ad/manager/takeMoney/getPage.web";
/** 审核 */
var checkState = "ad/manager/takeMoney/edit.web";

/** **********************************************************bankPo********************************************** */
/** 银行页面 */
var bankUrl = 'ad/manager/bankUrlTablePage.web';
/** 银行分页 */
var bankDTPaingUrl = 'ad/manager/bank/DTPaging.web';
/** 银行添加 */
var bankAddUrl = 'ad/manager/bank/add.web';
/** 银行修改 */
var bankEditUrl = 'ad/manager/bank/edit.web';
/** 银行修改页面 */
var bankEditPage = 'ad/manager/bank/bankEdit.web';
/** 银行添加页面 */
var bankAddPage = 'ad/manager/bankUrlAddPage.web';

/** **********************************************************agreementPo********************************************** */
var agreementUrl = 'ad/manager/agreementUrlPage.web';
var agreementDetail = 'ad/manager/agreement/detail.web';
var agreementEdit = 'ad/manager/agreement/edit.web';

var chargeParamAddUrl = 'ad/manager/chargeParam/add.web';
var chargeParamEditUrl = 'ad/manager/chargeParam/edit.web';

var getAllPostageUrl = 'ad/manager/postage/allList.web';
var getPostageDetailUrl = 'ad/manager/postage/detail.web';
var editPostageUrl = 'ad/manager/postage/edit.web';

var sendGoodsPage = 'ad/manager/sendGoodsTablePage.web';
var getAllGoodsSpecPage = 'ad/manager/dealer/goodsSpec/allList.web';

