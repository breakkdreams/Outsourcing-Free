package com.zd.aoding.outsourcing.web.controllerApi.app.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import com.zd.aoding.common.StringDate.StringUtil;
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.common.response.ResponseCodeEnum;
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.BankBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.BankCardBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.UserBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.BankCardDO;
import com.zd.aoding.outsourcing.weChat.api.constant.SessionConstant;
import com.zd.aoding.outsourcing.weChat.api.facade.BankCardFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.BankFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SessionFacade;
import com.zd.aoding.outsourcing.weChat.api.utils.file.SMSManager;

@Api(value = "", description = "用户银行卡管理")
@Controller
@RequestMapping("user")
public class BankUserController {
	@Autowired
	private SessionFacade sessionFacade;
	@Autowired
	private BankCardFacade bankCardFacade;
	@Autowired
	private BankFacade bankFacade;

	@ResponseBody
	@RequestMapping(value = "bankCard/add", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "添加用户银行卡", httpMethod = "POST", notes = "添加用户银行卡", response = ResponseUtil.class)
	public String addBankCard(
			@ApiParam(name = "bankid", value = "银行id", required = false) @RequestParam(value = "bankid", required = false) String bankid,
			@ApiParam(name = "bankKaihu", value = "开户行", required = false) @RequestParam(value = "bankKaihu", required = false) String bankKaihu,
			@ApiParam(name = "bankNum", value = "银行卡号", required = false) @RequestParam(value = "bankNum", required = false) String bankNum,
			@ApiParam(name = "cardName", value = "持卡人姓名", required = false) @RequestParam(value = "cardName", required = false) String cardName,
			@ApiParam(name = "phone", value = "手机号", required = false) @RequestParam(value = "phone", required = false) String phone,
			@ApiParam(required = true, name = "regCode", value = "手机验证码") @RequestParam(value = "regCode", required = true) String regCode,
			HttpServletRequest request) {
		try {
			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
			if (sessionUser != null) {
				if ("".equals(regCode) || regCode == null) {
					return ResponseUtil.showMSGResultString("未填写验证码");
				}
				String code = sessionFacade.checkMemcache(SMSManager.getRechangePasswordCode(phone));
				if (!regCode.equals(code)) {
					return ResponseUtil.showMSGResultString("验证码错误!");
				}
				String bankName = "";
				BankBO bankBO = bankFacade.getBankBOByPK(Integer.parseInt(bankid));
				if(bankBO!=null){
					bankName = bankBO.getBankName();
				}
				BankCardDO bankCardDO = new BankCardDO(BankCardDO.type_company, sessionUser.getUserId(), bankName, bankKaihu, bankNum, "", cardName, phone,Integer.parseInt(bankid));
				int j = bankCardFacade.insertBankCardDO(bankCardDO);
				if (j == 1) {
					return ResponseUtil.successResultString("添加成功");
				}
				return ResponseUtil.showMSGResultString("添加失败");
			} else {
				return ResponseUtil.notLoggedResultString();
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	@ResponseBody
	@RequestMapping(value = "bankCard/edit", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "编辑修改用户银行卡", httpMethod = "POST", notes = "编辑修改用户银行卡", response = ResponseUtil.class)
	public String editBankCard(
			@ApiParam(name = "bankCardId", value = "银行卡id", required = false) @RequestParam(value = "bankCardId", required = false) String bankCardId,
			@ApiParam(name = "ownerId", value = "用户id", required = false) @RequestParam(value = "ownerId", required = false) String ownerId,
			@ApiParam(name = "bankName", value = "银行名称", required = false) @RequestParam(value = "bankName", required = false) String bankName,
			@ApiParam(name = "bankKaihu", value = "开户行", required = false) @RequestParam(value = "bankKaihu", required = false) String bankKaihu,
			@ApiParam(name = "bankNum", value = "银行卡号", required = false) @RequestParam(value = "bankNum", required = false) String bankNum,
			@ApiParam(name = "cardName", value = "持卡人姓名", required = false) @RequestParam(value = "cardName", required = false) String cardName,
			@ApiParam(name = "phone", value = "手机号", required = false) @RequestParam(value = "phone", required = false) String phone,
			HttpServletRequest request) {
		try {
			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
			if (sessionUser != null) {
				if (!StringUtil.isNumber(bankCardId)) {
					return ResponseUtil.paramErrorResultString("bankCardId参数错误");
				}
				BankCardDO bankCardDO = bankCardFacade.getBankCardDOByPK(Integer.parseInt(bankCardId));
				if (bankCardDO == null) {
					return ResponseUtil.paramErrorResultString("参数错误");
				}
				if(StringUtil.isNumber(ownerId)){
					bankCardDO.setOwnerId(Integer.parseInt(ownerId));
				}
				if(!StringUtil.isNULL(bankName)){
					bankCardDO.setBankName(bankName);
				}
				if(!StringUtil.isNULL(bankKaihu)){
					bankCardDO.setBankKaihu(bankKaihu);
				}
				if(!StringUtil.isNULL(bankNum)){
					bankCardDO.setBankNum(bankNum);
				}
				if(!StringUtil.isNULL(cardName)){
					bankCardDO.setCardName(cardName);
				}
				if(!StringUtil.isNULL(phone)){
					bankCardDO.setPhone(phone);
				}
				int j = bankCardFacade.updateBankCardDO(bankCardDO);
				if (j == 1) {
					return ResponseUtil.successResultString("修改成功");
				}
				return ResponseUtil.showMSGResultString("修改失败");
			} else {
				return ResponseUtil.notLoggedResultString();
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	/**
	 * @Title: deletedAddress
	 * @Description: 删除
	 * @param addressId
	 * @param request
	 * @return
	 * @return: String
	 */
	@ResponseBody
	@RequestMapping(value = "bankCard/delete", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "删除用户银行卡", httpMethod = "POST", notes = "根据地址id删除用户银行卡", response = ResponseUtil.class)
	public String deletedBankCard(
			@ApiParam(required = true, name = "bankCardId", value = "银行卡Id") @RequestParam(value = "bankCardId", required = true) String bankCardId,
			HttpServletRequest request) {
		try {
			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
			if (sessionUser != null) {
				if (!StringUtil.isNULL(bankCardId) && StringUtil.isNumber(bankCardId)) {
					BankCardDO bankCardDO = bankCardFacade.getBankCardDOByPK(Integer.parseInt(bankCardId));
					if (bankCardDO == null) {
						return ResponseUtil.paramErrorResultString("参数错误");
					}
					bankCardDO.setDeleted(1);
					int i = bankCardFacade.updateBankCardDO(bankCardDO);
					if (i == 1) {
						return ResponseUtil.successResultString("删除成功");
					}
					return ResponseUtil.showMSGResultString("删除失败");
				}
				return ResponseUtil.paramErrorResultString("缺少需要删除的地址bankCardId=" + bankCardId);
			} else {
				return ResponseUtil.notLoggedResultString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "bankCard/getPage", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "分页查询银行卡", httpMethod = "POST", notes = "分页查询银行卡", response = ResponseUtil.class)
	public String getBankCard(
			@ApiParam(required = false, name = "pageNum", value = "当前页数（即当前第几页）") @RequestParam(value = "pageNum", required = false) String pageNum,
			@ApiParam(required = false, name = "pageSize", value = "分页条数") @RequestParam(value = "pageSize", required = false) String pageSize,
			HttpServletRequest request) {
		try {
			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
			if (sessionUser != null) {
				// 初始化列表
				PageEntity pageEntity = new PageEntity();
				if (StringUtil.isNumber(pageNum)) {
					pageEntity.setPage(Integer.parseInt(pageNum));
				}
				if (StringUtil.isNumber(pageSize)) {
					pageEntity.setSize(Integer.parseInt(pageSize));
				}
				/**
				 * 排序
				 */
				pageEntity.setOrderColumn("id");
				/**
				 * 查询条件参数
				 */
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("ownerId", sessionUser.getUserId());
				param.put("deleted", 0);
				pageEntity.setParams(param);
				/**
				 * 查询
				 */
				PageResult<BankCardBO> pageResult = bankCardFacade.getPageBankCardBO(pageEntity);
				return ResponseUtil.successResultString(pageResult);
			}
			return ResponseUtil.notLoggedResultString();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	@ResponseBody
	@RequestMapping(value = "bankCard/detail", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "查看单个银行卡信息", httpMethod = "POST", notes = "登陆后根据id查看单个银行卡信息", response = ResponseUtil.class)
	@ApiResponses({ @ApiResponse(code = 1, message = "实体类注释", response = BankCardBO.class) })
	public String getBankCardMapper(
			@ApiParam(required = false, name = "bankCardId", value = "银行卡id") @RequestParam(value = "bankCardId", required = false) String bankCardId,
			HttpServletRequest request) {
		try {
			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
			if (sessionUser != null) {
				if (!StringUtil.isNULL(bankCardId) && StringUtil.isNumber(bankCardId)) {
					BankCardBO bankCardBO = bankCardFacade.getBankCardBOByPK(Integer.parseInt(bankCardId));
					if (bankCardBO == null) {
						return ResponseUtil.paramErrorResultString("参数错误");
					}
					return ResponseUtil.successResultString(bankCardBO);
				}
				return ResponseUtil.paramErrorResultString("地址id为空或格式不正确");
			}
			return ResponseUtil.notLoggedResultString();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/phoneBankCode", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "发送银行卡验证码", httpMethod = "POST", notes = "测试阶段请求均失败code==300，请求之后使用验证码‘123456’", response = ResponseUtil.class)
	public String phoneRegCode(@ApiParam(required = true, name = "phone", value = "手机号") @RequestParam(value = "phone", required = true) String phone,
			HttpServletRequest request) {
		try {
			int i = SMSManager.sendBankCode(phone);
			if (i == 1) {
				return ResponseUtil.successResultString("发送成功请注意查收");
			}
			return ResponseUtil.showMSGResultString("验证码发送失败");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
}
