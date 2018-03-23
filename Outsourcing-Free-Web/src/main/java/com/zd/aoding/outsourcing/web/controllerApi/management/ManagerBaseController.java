package com.zd.aoding.outsourcing.web.controllerApi.management;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.*;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.*;
import com.zd.aoding.outsourcing.weChat.api.facade.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.zd.aoding.common.StringDate.StringUtil;
import com.zd.aoding.outsourcing.weChat.api.constant.SessionConstant;

/**
 * @ClassName: BaseController
 * @Description: 团装团基础入口页面
 * @author HCD
 * @date 2016年10月24日 下午9:38:23
 */
@Api(value = "", description = "后台及代理的所有界面")
@Controller
@RequestMapping("ad/manager")
public class ManagerBaseController {
	@Autowired
	private BannerFacade bannerFacade;
	@Autowired
	private MenuAdminFacade menuFacade;
	@Autowired
	private HttpSession session;
	@Autowired
	private RoleFacade roleFacade;
	@Autowired
	private CategoryFacade categoryFacade;
	@Autowired
	private SessionFacade sessionFacade;
	@Autowired
	private RoleAccountFacade roleAccountFacade;
	@Autowired
	private AccountFacade accountFacade;
	@Autowired
	private GoodsFacade goodsFacade;
	@Autowired
	private ShoppingOrderFacade shoppingOrderFacade;
	@Autowired
	private ShoppingOrderItemFacade shoppingOrderItemFacade;
	@Autowired
	private SystemparamFacade systemparamFacade;
	@Autowired
	private ManagerFacade managerFacade;
	@Autowired
	private AddressFacade addressFacade;
	@Autowired
	private UserPurseFacade userPurseFacade;
	@Autowired
	private UserFacade userFacade;
	@Autowired
	private GoodsModelFacade goodsModelFacade;
	@Autowired
	private GoodsSpecFacade goodsSpecFacade;
	@Autowired
	private GoodsParamFacade goodsParamFacade;
	@Autowired
	private CategoryGoodsSpecRelationFacade categoryGoodsSpecRelationFacade;
	@Autowired
	private AboutOurFacade aboutOurFacade;
	@Autowired 
	private BankFacade bankFacade;
	@Autowired 
	private AgreementFacade agreementFacade;
	@Autowired 
	private GoodsTypeFacade goodsTypeFacade;
	@Autowired 
	private GoodsCommentFacade goodsCommentFacade;
	@Autowired
	private ChargeParamFacade chargeParamFacade;

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
		List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
		String pName = request.getParameter("pName");
		String title = pName+"页面成功";
		if(!StringUtil.isNULL(pName)){
			pName = pName+"查看";
			RoleBO rv = new RoleBO(sess, pName);
			if("0".equals(rv.getRoleFalg()) || StringUtil.isNULL(rv.getRoleFalg()) ) {
				jspName = "errorTablePage";
				title = "无权限查看页面";
			}
		}
		
		//操作日志
//		AccountBO account = sessionFacade.checkLoginAccountSession(request);
//		RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", title , RecordsDO.RECORDTYPE_MANAGER_VIEW,
//			RecordBase.DEALTYPE_MANAGER, account.getAccountId(), 0, pName+"页面", "");
//		recordFacade.insertRecordDO(recordDO);
		
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
		if(jspName.equals("left")){
			//获取该用户的菜单
			RoleAccountDO roleAccountPo = roleAccountFacade.getRoleAccountPoByPK(sessionAccount.getAccountId()+"");
			RoleDO rolePo = roleFacade.getPoByPK(roleAccountPo.getRoleId());
			String[] roleStr = (rolePo.getMenuList()).split(",");

			//获取全部菜单
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("deleted", 0);
			map.put("orderColumn", "sort");
			map.put("orderTurn", "asc");
			List<MenuAdminDO> list = menuFacade.getList(map);
			List<Map<String, Object>> listMenu = new ArrayList<>();
			List<Map<String, Object>> listMenuSess = new ArrayList<>();
			for (MenuAdminDO ass : list) {
				Map<String, Object> menPoPage = new HashMap<String,Object>();
				Map<String, Object> menuSess = new HashMap<String,Object>();
				if(roleStr.length>0){
					for (int i = 0 ; i <roleStr.length ; i++){
						if (roleStr[i].equals(ass.getPid()+"")){
							if(ass.getFalg()!=3){
								menPoPage.put("pid",ass.getPid());
								menPoPage.put("pName",ass.getpName());
								menPoPage.put("erId",ass.getErId());
								menPoPage.put("url",ass.getUrl());
								menPoPage.put("falg", ass.getFalg());
								menPoPage.put("tubiao", ass.getTubiao());
								listMenu.add(menPoPage);
							}else if(ass.getFalg()==3){
								menuSess.put("pid",ass.getPid());
								menuSess.put("pName",ass.getpName());
								menuSess.put("erId",ass.getErId());
								menuSess.put("url",ass.getUrl());
								menuSess.put("falg",ass.getFalg());
								listMenuSess.add(menuSess);
							}
						}
					}
				}
			}
			session.setAttribute("menuSession", listMenuSess);
			mav.addObject("listMenu", listMenu);
			mav.addObject("sessionAccount", sessionAccount);
		}
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

	/**
	 * 角色编辑页面
	 * @param roleId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "role/roleUrlEditPage", method = RequestMethod.GET)
	@ApiOperation(value = "角色编辑界面", httpMethod = "GET", response = ModelAndView.class)
	public ModelAndView roleEdit(
			@ApiParam(required = true, name = "roleId", value = "roleId") @RequestParam(value = "roleId")
					String roleId,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("web/managerJsp/roleUrlEditPage");
		mav.addObject(SessionConstant.OPTION, "roleUrl");
		RoleDO rolePo = roleFacade.getPoByPK(Integer.parseInt(roleId));
		mav.addObject("rolePo", rolePo);
		return mav;
	}
	
	@RequestMapping(value = "goods/goodsDetailPage", method = RequestMethod.GET)
	@ApiOperation(value = "产品详情界面", httpMethod = "GET", response = ModelAndView.class)
	public ModelAndView goodsDetailPage(
			@ApiParam(required = true, name = "goodsId", value = "goodsId") @RequestParam(value = "goodsId")
			String goodsId,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("web/managerJsp/goodsDetailPage");
		mav.addObject(SessionConstant.OPTION, "goodsUrl");
		GoodsBO goods = goodsFacade.getGoodsVoByPK(goodsId);
		mav.addObject("goodsVo", goods);
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


	@RequestMapping(value = "menu/menuUrlEditPage", method = RequestMethod.GET)
	@ApiOperation(value = "菜单编辑界面", httpMethod = "GET", response = ModelAndView.class)
	public ModelAndView menuEdit(
			@ApiParam(required = true, name = "pId", value = "pId") @RequestParam(value = "pId")
					String pId,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("web/managerJsp/menuUrlEditPage");
		mav.addObject(SessionConstant.OPTION, "menuUrl");
		MenuAdminDO menuAdminPo = menuFacade.getPoByPK(Integer.parseInt(pId));
		mav.addObject("menuAdminPo", menuAdminPo);
		return mav;
	}

	@RequestMapping(value = "category/detailPage", method = RequestMethod.GET)
	@ApiOperation(value = "分类详情界面", httpMethod = "GET", response = ModelAndView.class)
	public ModelAndView categoryDetail(
			@ApiParam(required = true, name = "categoryId", value = "categoryId") @RequestParam(value = "categoryId") String categoryId,
			@ApiParam(required = true, name = "returnFlag", value = "returnFlag") @RequestParam(value = "returnFlag") String returnFlag,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("web/managerJsp/categoryUrlEditPage");
		mav.addObject(SessionConstant.OPTION, "categoryUrl");
		CategoryDO categoryPo = categoryFacade.getCategoryPoByPK(categoryId);
		CategoryBO categoryVo = new CategoryBO(categoryPo);
		categoryVo.setReturnFlag(Integer.parseInt(returnFlag));
		mav.addObject("categoryVo", categoryVo);
		return mav;
	}
	@RequestMapping(value = "model/detailPage", method = RequestMethod.GET)
	@ApiOperation(value = "模块详情界面", httpMethod = "GET", response = ModelAndView.class)
	public ModelAndView modelDetail(
			@ApiParam(required = true, name = "modelId", value = "modelId") @RequestParam(value = "modelId") String modelId,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("web/managerJsp/modelUrlEditPage");
		mav.addObject(SessionConstant.OPTION, "goodsModelUrl");
		GoodsModelBO modelVo = goodsModelFacade.getGoodsModelVoByPK(Integer.parseInt(modelId));
		mav.addObject("modelVo", modelVo);
		return mav;
	}
	@RequestMapping(value = "type/detailPage", method = RequestMethod.GET)
	@ApiOperation(value = "类型详情界面", httpMethod = "GET", response = ModelAndView.class)
	public ModelAndView typeDetail(
			@ApiParam(required = true, name = "typeId", value = "typeId") @RequestParam(value = "typeId") String typeId,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("web/managerJsp/typeUrlEditPage");
		mav.addObject(SessionConstant.OPTION, "goodsTypeUrl");
		GoodsTypeBO typeVo = goodsTypeFacade.getGoodsTypeVoByPK(Integer.parseInt(typeId));
		mav.addObject("typeVo", typeVo);
		return mav;
	}

	@RequestMapping(value = "category/addPage", method = RequestMethod.GET)
	@ApiOperation(value = "分类添加页面", httpMethod = "GET", response = ModelAndView.class)
	public ModelAndView categoryAdd(
			@ApiParam(required = true, name = "returnFlag", value = "returnFlag") @RequestParam(value = "returnFlag") String returnFlag,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("web/managerJsp/categoryUrlAddPage");
		mav.addObject(SessionConstant.OPTION, "categoryUrl");
		CategoryDO categoryPo = new CategoryDO();
		CategoryBO categoryVo = new CategoryBO(categoryPo);
		categoryVo.setReturnFlag(Integer.parseInt(returnFlag));
		mav.addObject("categoryVo", categoryVo);
		return mav;
	}

	@RequestMapping(value = "system/systemparamUrlTablePage", method = RequestMethod.GET)
    @ApiOperation(value = "系统参数界面", httpMethod = "GET", notes = "系统参数界面", response = ModelAndView.class)
    public ModelAndView showSystemParam(
            HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("deleted", 0);
        List<SystemparamDO> list = systemparamFacade.getSystemparamList(param);
        mav.addObject("systemParam", list);
        mav.setViewName("web/managerJsp/systemparamUrlTablePage");
        mav.addObject(SessionConstant.OPTION, "systemparamUrl");
        return mav;
    }
	
	 @RequestMapping(value = "dealer/goods/detailPage", method = RequestMethod.GET)
	    @ApiOperation(value = "产品详情界面", httpMethod = "GET", response = ModelAndView.class)
	    public ModelAndView goodsDetail(
	            @ApiParam(required = true, name = "goodsId", value = "goodsId") @RequestParam(value = "goodsId") String goodsId,
	            HttpServletRequest request) {
	        ModelAndView mav = new ModelAndView();
	        mav.setViewName("web/managerJsp/goodsAdminEditPage");
	        mav.addObject(SessionConstant.OPTION, "goodsAdminUrl");
	        GoodsBO goodsVo = goodsFacade.getGoodsVoByPK(goodsId);
	        mav.addObject("goodsVo", goodsVo);
	        return mav;
	    }
	 
	 @RequestMapping(value="dealer/goodsSpecTablePage", method = RequestMethod.GET)
	    @ApiOperation(value="供货商基础页面跳转", httpMethod="get", notes = "jsp的名称为部分路径", response = ModelAndView.class)
	    public ModelAndView showUpdateGoodsWeb(
	            @ApiParam(name = "goodsId", value = "产品id", required = true) @RequestParam(value = "goodsId") String goodsId,
	            HttpServletRequest request) {
	        ModelAndView mav = new ModelAndView();
	        	
	            List<GoodsSpecBO> goodsSpecVoList = goodsSpecFacade.getGoodsSpecVoByGoodId(goodsId);
	            mav.addObject("size",0);
	            if (goodsSpecVoList!=null && goodsSpecVoList.size() > 0) {
	                mav.addObject("size", goodsSpecVoList.size());
	                for (int i = 0; i < goodsSpecVoList.size(); i++) {
	                    goodsSpecVoList.get(i).setSort(i + 1);
	                } 
	                mav.addObject("goodsSpecVoList", goodsSpecVoList);
	            }else{
	            	int goodsIntId = Integer.parseInt(goodsId);
	            	GoodsDO goodsPo = goodsFacade.getGoodsPoByPK(goodsIntId);
	            	Integer thirdCatagoryId = goodsPo.getThirdCatagory();
	            	List<String> list = categoryGoodsSpecRelationFacade.getSpecNameByCategoryId(thirdCatagoryId);
	            	if(list != null && list.size() > 0){
	            		int size = list.size();
	            		for(int i = 0; i < size; i++){
	            			GoodsSpecDO goodsSpecPo = new GoodsSpecDO();
	            			goodsSpecPo.setGoodsId(goodsIntId);
	            			goodsSpecPo.setTitle(list.get(i));
	            			goodsSpecFacade.insertGoodsSpecPo(goodsSpecPo);
	            		}
	            		List<GoodsSpecBO> goodsSpecVoTwoList = goodsSpecFacade.getGoodsSpecVoByGoodId(goodsId);
	            		mav.addObject("size", goodsSpecVoTwoList.size());
	                    for (int i = 0; i < goodsSpecVoTwoList.size(); i++) {
	                    	goodsSpecVoTwoList.get(i).setSort(i + 1);
	                    } 
	                    mav.addObject("goodsSpecVoList", goodsSpecVoTwoList);
	            	}
	        }
            GoodsBO goods = goodsFacade.getGoodsVoByPK(goodsId);
            mav.addObject("goodsVo", goods);
	        mav.setViewName("web/managerJsp/goodsSpecTablePage");
	        mav.addObject(SessionConstant.OPTION, "goodsGoodsUrl");
	        return mav;
	    }
	 
	 @RequestMapping(value="dealer/goodsParamTablePage", method = RequestMethod.GET)
	    @ApiOperation(value="供货商基础页面跳转", httpMethod="get", notes = "jsp的名称为部分路径", response = ModelAndView.class)
	    public ModelAndView showUpdateGoodsParamWeb(
	            @ApiParam(name = "goodsId", value = "产品id", required = true) @RequestParam(value = "goodsId") String goodsId,
	            HttpServletRequest request) {
	        ModelAndView mav = new ModelAndView();
	            List<GoodsParamBO> voList = goodsParamFacade.getParamListByGoodsId(Integer.parseInt(goodsId));
	            mav.addObject("size",0);
	            if (voList!=null) {
	                mav.addObject("size", voList.size());
	                for (int i = 0; i < voList.size(); i++) {
	                    voList.get(i).setSort(i + 1);
	                } 
	            }
	            mav.addObject("voList", voList);
	            GoodsBO goods = goodsFacade.getGoodsVoByPK(goodsId);
	            mav.addObject("goodsVo", goods);
	        mav.setViewName("web/managerJsp/goodsParamTablePage");
	        mav.addObject(SessionConstant.OPTION, "goodsAdmin");
	        return mav;
	    }
	  @RequestMapping(value = "dealer/bannerEdit", method = RequestMethod.GET)
	    @ApiOperation(value = "供货商基础页面跳转", httpMethod = "get", notes = "jsp的名称为部分路径", response = ModelAndView.class)
	    public ModelAndView bannerEditPage(
	    		@ApiParam(name = "bannerId", value = "轮播图id", required = true) @RequestParam(value = "bannerId", required = true) String bannerId,
	    		HttpServletRequest request) {
	    	
	    	ModelAndView mav = new ModelAndView();
	    	if(StringUtil.isNumber(bannerId)){
	            BannerBO bannerVo = bannerFacade.getBannerVoByPK(Integer.parseInt(bannerId));
	            mav.addObject("banner", bannerVo);
	        }
	    	mav.setViewName("/web/managerJsp/bannerEditPage");
	    	mav.addObject(SessionConstant.OPTION, "bannerUrl");
	    	return mav;
	    }
	  @RequestMapping(value = "bank/bankEdit", method = RequestMethod.GET)
	  @ApiOperation(value = "银行修改", httpMethod = "get", notes = "jsp的名称为部分路径", response = ModelAndView.class)
	  public ModelAndView bankEditPage(
			  @ApiParam(name = "bankId", value = "id", required = true) @RequestParam(value = "bankId", required = true) String bankId,
			  HttpServletRequest request) {
		  
		  ModelAndView mav = new ModelAndView();
		  if(StringUtil.isNumber(bankId)){
			  BankBO bankVo = bankFacade.getBankBOByPK(Integer.parseInt(bankId));
			  mav.addObject("bankVo", bankVo);
		  }
		  mav.setViewName("/web/managerJsp/bankEditPage");
		  mav.addObject(SessionConstant.OPTION, "bankUrl");
		  return mav;
	  }
	  
	  @RequestMapping(value = "comment/detailPage", method = RequestMethod.GET)
	  @ApiOperation(value = "回复评价", httpMethod = "get", notes = "jsp的名称为部分路径", response = ModelAndView.class)
	  public ModelAndView commentEditPage(
			  @ApiParam(name = "goodsCommentId", value = "id", required = true) @RequestParam(value = "goodsCommentId", required = true) String goodsCommentId,
			  HttpServletRequest request) {
		  
		  ModelAndView mav = new ModelAndView();
		  if(StringUtil.isNumber(goodsCommentId)){
			  GoodsCommentBO goodsCommentVo = goodsCommentFacade.getGoodsCommentVoByPK(Integer.parseInt(goodsCommentId));
			  mav.addObject("goodsCommentVo", goodsCommentVo);
		  }
		  mav.setViewName("/web/managerJsp/goodsCommentEditPage");
		  mav.addObject(SessionConstant.OPTION, "goodsCommentUrl");
		  return mav;
	  }

	@RequestMapping(value = "distributor/detailPage", method = RequestMethod.GET)
	@ApiOperation(value = "经销商", httpMethod = "get", notes = "jsp的名称为部分路径", response = ModelAndView.class)
	public ModelAndView distributorEditPage(
			@ApiParam(name = "accountId", value = "id", required = true) @RequestParam(value = "accountId", required = true) String accountId,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		if(StringUtil.isNumber(accountId)){
			AccountBO accountBO = accountFacade.getBoByPK(Integer.parseInt(accountId));
			mav.addObject("accountBO", accountBO);
		}
		mav.setViewName("/web/managerJsp/distributorUrlEditPage");
		mav.addObject(SessionConstant.OPTION, "distributorUrl");
		return mav;
	}
	  
	@RequestMapping(value = "chargeParam/detailPage", method = RequestMethod.GET)
	@ApiOperation(value = "首充赠送", httpMethod = "get", notes = "jsp的名称为部分路径", response = ModelAndView.class)
	public ModelAndView chargeParamEditPage(
			@ApiParam(name = "accountId", value = "id", required = true) @RequestParam(value = "accountId", required = true) String accountId,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String namePage = "/web/managerJsp/firstChargeUrlAddPage";
		if(StringUtil.isNumber(accountId)){
			Map<String,Object> pamp = new HashMap<String,Object>();
			pamp.put("deleted",0);
			pamp.put("distributorId",accountId);
			List<ChargeParamDO> list = chargeParamFacade.getChargeParamDOList(pamp);
			if (list!=null && list.size()>0){
				mav.addObject("listVO", new ChargeParamBO(list.get(0)));
				namePage = "/web/managerJsp/firstChargeUrlEditPage";
			}
		}
		mav.setViewName(namePage);
		mav.addObject(SessionConstant.OPTION, "distributorUrl");
		return mav;
	}

	  @RequestMapping(value = "category/goodsSpecPage", method = RequestMethod.GET)
		@ApiOperation(value = "分类配置管理界面", httpMethod = "GET", response = ModelAndView.class)
		public ModelAndView goodsSpecPage(
				@ApiParam(required = true, name = "categoryId", value = "分类Id") @RequestParam(value = "categoryId") String categoryId,
				HttpServletRequest request) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/web/managerJsp/categoryUrlEditGoodsSpecPage");
			mav.addObject(SessionConstant.OPTION, "categoryUrl");
			List<CategoryGoodsSpecRelationBO> listVo = categoryGoodsSpecRelationFacade.getListByCategoryId(Integer.parseInt(categoryId));
			CategoryDO category = categoryFacade.getCategoryPoByPK(categoryId);
			String categoryName = "";
			if(category != null){
				categoryName = category.getName();
			}
			if(listVo.size() > 0){
				mav.addObject("listVo", listVo);
			}
			//用于定位删除元素id
			mav.addObject("deleteSize", listVo.size()+1);
			mav.addObject("categoryId", category.getId());
			mav.addObject("categoryName", categoryName);
			
			return mav;
		}
	  
	  @RequestMapping(value = "aboutOurEdit/edit", method = RequestMethod.GET)
	    @ApiOperation(value = "关于我们基础页面跳转", httpMethod = "get", notes = "jsp的名称为部分路径", response = ModelAndView.class)
	    public ModelAndView aoutOurEditPage(
//	    		@ApiParam(name = "bannerId", value = "轮播图id", required = true) @RequestParam(value = "bannerId", required = true) String bannerId,
	    		HttpServletRequest request) {
	    	
	    	ModelAndView mav = new ModelAndView();
	    	mav.setViewName("/web/managerJsp/aboutOurPage");
			mav.addObject(SessionConstant.OPTION, "aboutOurPage");
	    	Map<String, Object> map = new HashMap<String, Object>();
            map.put("deleted", 0);
            AboutOurDO aboutOurPo = aboutOurFacade.getAboutOurByParam(map);
            if(aboutOurPo != null){
                //aboutOurPo = new AboutOurPo();
                mav.addObject("aboutOurPo", aboutOurPo);
            }
	    	return mav;
	    }
	  
//	  @RequestMapping(value = "agreement/edit", method = RequestMethod.GET)
//	  @ApiOperation(value = "关于我们基础页面跳转", httpMethod = "get", notes = "jsp的名称为部分路径", response = ModelAndView.class)
//	  public ModelAndView agreementEditPage(
//	    		@ApiParam(name = "agreementId", value = "id", required = true) @RequestParam(value = "agreementId", required = true) String agreementId,
//			  HttpServletRequest request) {
//		  
//		  ModelAndView mav = new ModelAndView();
//		  mav.setViewName("/web/managerJsp/agreementPage");
//		  mav.addObject(SessionConstant.OPTION, "agreementPage");
//		  Map<String, Object> map = new HashMap<String, Object>();
//		  map.put("deleted", 0);
//		  AgreementBO agreementBO = agreementFacade.getAgreementVoById(Integer.parseInt(agreementId));
//		  if(agreementBO != null){
//			  mav.addObject("agreementBO", agreementBO);
//		  }
//		  return mav;
//	  }
}
