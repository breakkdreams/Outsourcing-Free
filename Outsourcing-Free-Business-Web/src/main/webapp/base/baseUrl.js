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

var indexUrl =  "ad/manager/index.web";

/** **********************************************************userPo********************************************** */

var userUrl =  'ad/manager/userUrlTablePage.web';
var UserDTPagingUrl =  'ad/manager/user/DTPaging.web';
var editUserUrl =  'ad/manager/user/edit.web';
var userInfoPage =  'ad/manager/user/infoPage.web';
var userEditPage =  'ad/manager/user/editPage.web';
var resetPassword =  'ad/manager/user/resetPassword.web';
var userDetailUrl =  'ad/manager/user/detail.web';

/** **********************************************************BusinessTypePo********************************************** */
/** 商家类型详情编辑页面 */
var businessTypeUrl =  'ad/manager/businessTypeUrlTablePage.web';
var businessTypeDTPaingUrl =  'ad/manager/businessType/DTPaging.web';
var addBusinessTypeUrl =  'ad/manager/businessType/add.web';
var editBusinessTypeUrl =  'ad/manager/businessType/edit.web';
var deleteBusinessTypeUrl =  'ad/manager/businessType/delete.web';

/** **********************************************************NoticeVo********************************************** */
/** 商家系统消息页面 */
var businessNoticeUrl =  'ad/manager/businessNoticeUrlTablePage.web';
/** 分页查询商家消息 */
var businessNoticeDTPaingUrl =  'ad/manager/businessNotice/DTPaging.web';
/** 添加商家消息 */
var businessNoticeAddUrl =  'ad/manager/businessNotice/add.web';
/** **********************************************************OrderPo********************************************** */
var orderUrl =  'ad/manager/orderUrlTablePage.web';
/** **************************************************商家businessPo********************************************** */
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
