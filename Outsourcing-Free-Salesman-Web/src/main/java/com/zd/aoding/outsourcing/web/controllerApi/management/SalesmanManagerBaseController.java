package com.zd.aoding.outsourcing.web.controllerApi.management;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.zd.aoding.common.StringDate.StringUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.*;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ManagerDO;
import com.zd.aoding.outsourcing.weChat.api.constant.SessionConstant;
import com.zd.aoding.outsourcing.weChat.api.facade.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: BaseController
 * @Description: 团装团基础入口页面
 * @author HCD
 * @date 2016年10月24日 下午9:38:23
 */
@Api(value = "", description = "后台及代理的所有界面")
@Controller
@RequestMapping("ad/manager")
public class SalesmanManagerBaseController {
	@Autowired
	private SessionFacade sessionFacade;
	@Autowired
	private ShoppingOrderFacade shoppingOrderFacade;
	@Autowired
	private ShoppingOrderItemFacade shoppingOrderItemFacade;
	@Autowired
	private ManagerFacade managerFacade;
	@Autowired
	private AddressFacade addressFacade;
	@Autowired
	private UserPurseFacade userPurseFacade;
	@Autowired
	private UserFacade userFacade;

	@RequestMapping(value = "index", method = RequestMethod.GET)
	@ApiOperation(value = "首页", httpMethod = "GET", notes = "首页", response = ModelAndView.class)
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		AccountBO account = sessionFacade.checkLoginAccountSession(request);
		mav.addObject("account", account);
		ManagerDO manager = managerFacade.getPoByAccountId(account.getAccountId());
		mav.addObject("manager", manager);
		mav.setViewName("web/managerJsp/index");
		mav.addObject(SessionConstant.OPTION, "index");
		return mav;
	}

	@RequestMapping(value = "{jspName}", method = RequestMethod.GET)
	@ApiOperation(value = "管理端界面", httpMethod = "GET", notes = "多数后台管理界面", response = ModelAndView.class)
	public ModelAndView managerJspName(
			@ApiParam(required = true, name = "jspName", value = "用户文件夹下jsp文件名") @PathVariable(value = "jspName") String jspName,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("web/managerJsp/" + jspName);
		mav.addObject(SessionConstant.OPTION, "managerJsp/" + jspName);
		return mav;
	}

	@RequestMapping(value = "block/{jspName}", method = RequestMethod.GET)
	@ApiOperation(value = "导航栏、侧边栏、底边栏", httpMethod = "GET", notes = "导航栏、侧边栏、底边栏", response = ModelAndView.class)
	public ModelAndView blockJsp(
			@ApiParam(required = true, name = "jspName", value = "用户文件夹下jsp文件名") @PathVariable(value = "jspName") String jspName,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		AccountBO sessionAccount = (AccountBO) sessionFacade.checkLoginAccountSession(request);
		mav.addObject("sessionAccount", sessionAccount);
		mav.setViewName("web/managerJsp/block/" + jspName);
		return mav;
	}

	@RequestMapping(value = "showMsg", method = RequestMethod.GET)
	@ApiOperation(value = "弹出框", httpMethod = "GET", notes = "弹出框", response = ModelAndView.class)
	public ModelAndView showMsg(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("baseBlock/showMsg");
		return mav;
	}

	@RequestMapping(value = "order/detailPage", method = RequestMethod.GET)
	@ApiOperation(value = "订单详情界面", httpMethod = "GET", response = ModelAndView.class)
	public ModelAndView orderDetailPage(
			@ApiParam(required = true, name = "orderId", value = "orderId") @RequestParam(value = "orderId")
			String orderId,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("web/managerJsp/orderDetailPage");
		mav.addObject(SessionConstant.OPTION, "orderUrl");
		ShoppingOrderBO orderVo = shoppingOrderFacade.getShoppingOrderVoByPK(Integer.parseInt(orderId));
		List<ShoppingOrderItemBO> listItemVo = shoppingOrderItemFacade.getOrderItemVoByOrderId(Integer.parseInt(orderId));
		mav.addObject("orderVo", orderVo);
		mav.addObject("listItemVo", listItemVo);
		return mav;
	}
	@RequestMapping(value = "user/infoPage", method = RequestMethod.GET)
	@ApiOperation(value = "用户详情界面", httpMethod = "GET", response = ModelAndView.class)
	public ModelAndView userDetailPage(
			@ApiParam(required = true, name = "accountId", value = "accountId") @RequestParam(value = "accountId")
			String accountId,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("web/managerJsp/userInfoPage");
		mav.addObject(SessionConstant.OPTION, "userUrl");
		UserBO userVo = userFacade.getUserByAccountId(Integer.parseInt(accountId));
		List<UserAddressBO> addressList = addressFacade.getAddressVoList(userVo.getUserId());
		List<PurseBO> purseList = userPurseFacade.getPurseVoListByUserId(userVo.getUserId());
		mav.addObject("userVo", userVo);
		mav.addObject("addressList", addressList);
		mav.addObject("purseList", purseList);
		return mav;
	}

	@RequestMapping(value = "business/businessEdit", method = RequestMethod.GET)
	@ApiOperation(value = "商家", httpMethod = "get", notes = "jsp的名称为部分路径", response = ModelAndView.class)
	public ModelAndView businessEditPage(
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		AccountBO accountBO = (AccountBO) sessionFacade.checkLoginAccountSession(request);
		if(accountBO!=null){
			ManagerBO managerBO = managerFacade.getBoByAccountId(accountBO.getAccountId());
			mav.addObject("accountBO", accountBO);
			mav.addObject("managerBO", managerBO);
		}
		mav.setViewName("/web/managerJsp/businessUrlEditPage");
		mav.addObject(SessionConstant.OPTION, "businessUrl");
		return mav;
	}
}
