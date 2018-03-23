///**   
// * Copyright © 2018 嘉兴市奥丁网络科技有限公司. All rights reserved.
// * 
// * @Title: TestAccountServiceController.java 
// * @Prject: Outsourcing-WeChat-Web
// * @Package: com.zd.aoding.outsourcing.web.controllerApi.test 
// * @Description: TODO
// * @author: HCD   
// * @date: 2018年1月2日 上午10:06:39 
// * @version: V1.0   
// */
//package com.zd.aoding.outsourcing.web.controllerApi.test;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.wordnik.swagger.annotations.Api;
//import com.wordnik.swagger.annotations.ApiOperation;
//import com.wordnik.swagger.annotations.ApiParam;
//import com.zd.aoding.common.page.PageEntity;
//import com.zd.aoding.common.response.ResponseCodeEnum;
//import com.zd.aoding.common.response.ResponseUtil;
//import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.AccountDO;
//import com.zd.aoding.outsourcing.weChat.api.services.mysql.AccountService;
//
///** 
// * @ClassName: TestAccountServiceController 
// * @Description: TODO
// * @author: HCD
// * @date: 2018年1月2日 上午10:06:39  
// */
//@Api(value = "", description = "账号信息测试")
//@Controller
//public class TestAccountServiceController {
//	
//	
//	
//	@Autowired
//	private AccountService accountService;
//	
//	/** 
//	 * @Title: getAccount 
//	 * @Description: 测试根据accountId查询
//	 * @author: HCD
//	 * @param acountId
//	 * @param request
//	 * @return
//	 * @return: String
//	 */
//	@ResponseBody
//	@RequestMapping(value="testAccount/getAccount",method = RequestMethod.POST, produces = "application/x-www-form-urlencoded;charset=utf-8")
//	@ApiOperation(value = "账号信息测试",httpMethod = "POST", notes = "账号信息测试", response = ResponseUtil.class)
//	public String getAccount(
//			@ApiParam(name = "acountId", value = "账户id") @RequestParam(value = "acountId") String acountId,
//			HttpServletRequest request){
//		AccountDO accountDO = accountService.get(Integer.parseInt(acountId));
//		if(accountDO != null){
//			return ResponseUtil.resultString(accountDO, ResponseCodeEnum.OK);
//		}
//		return ResponseUtil.resultString("用户信息查询失败", ResponseCodeEnum.ERROR);
//	}
//	
//	/** 
//	 * @Title: getAccountLimit 
//	 * @Description: 账号信息分页测试
//	 * @author: HCD
//	 * @param pageSize
//	 * @param pageNum
//	 * @param request
//	 * @return
//	 * @return: String
//	 */
//	@ResponseBody
//	@RequestMapping(value="testAccount/getAccountLimit",method = RequestMethod.POST, produces = "application/x-www-form-urlencoded;charset=utf-8")
//	@ApiOperation(value = "账号信息分页测试",httpMethod = "POST", notes = "账号信息分页测试", response = ResponseUtil.class)
//	public String getAccountLimit(
//			@ApiParam(name = "pageSize", value = "每页条数") @RequestParam(value = "pageSize") String pageSize,
//			@ApiParam(name = "pageNum", value = "起始页码") @RequestParam(value = "pageNum") String pageNum,
//			HttpServletRequest request){
//		PageEntity pageEntity = new PageEntity();
//		Map<String,Object> map = new HashMap<>();
//		pageEntity.setPage(Integer.parseInt(pageNum));
//		pageEntity.setSize(Integer.parseInt(pageSize));
//		pageEntity.setOrderColumn("create_time");
//		pageEntity.setOrderTurn("DESC");
//		map.put("deleted", 0);
//		pageEntity.setParams(map);
//		List<AccountDO> accountLst = accountService.getPagination(pageEntity);
//		if(accountLst != null && accountLst.size() > 0){
//			return ResponseUtil.resultString(accountLst, ResponseCodeEnum.OK);
//		}
//		return ResponseUtil.resultString("分页测试失败", ResponseCodeEnum.ERROR);
//	}
//	
//	/** 
//	 * @Title: deleteAccount 
//	 * @Description: 删除用户信息账号信息
//	 * @author: HCD
//	 * @param accountId
//	 * @param request
//	 * @return
//	 * @return: String
//	 */
//	@ResponseBody
//	@RequestMapping(value="testAccount/deleteAccount",method = RequestMethod.POST, produces = "application/x-www-form-urlencoded;charset=utf-8")
//	@ApiOperation(value = "删除用户信息账号信息",httpMethod = "POST", notes = "删除用户信息账号信息", response = ResponseUtil.class)
//	public String deleteAccount(
//			@ApiParam(name = "accountId", value = "用户id") @RequestParam(value = "accountId") String accountId,
//			HttpServletRequest request){
//		AccountDO accountDO = new AccountDO();
//		accountDO.setAccountId(Integer.parseInt(accountId));
//		accountDO.setDeleted(1);
//		int account = accountService.update(accountDO);
//		return ResponseUtil.resultString(account, ResponseCodeEnum.OK);
//	}
//}
