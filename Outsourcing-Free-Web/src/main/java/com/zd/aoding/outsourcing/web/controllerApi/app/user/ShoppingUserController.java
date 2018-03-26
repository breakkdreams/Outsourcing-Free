package com.zd.aoding.outsourcing.web.controllerApi.app.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import com.zd.aoding.common.Md5.MD5Util;
import com.zd.aoding.common.StringDate.StringUtil;
import com.zd.aoding.common.log.LogUtil;
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.common.response.ResponseCodeEnum;
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.GoodsBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.GoodsOptionBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.GoodsSpecBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.ShoppingCartBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.ShoppingCartItemBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.ShoppingOrderBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.ShoppingOrderItemBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.UserAddressBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.UserBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.UserPurseBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.BaseOrder;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsOptionDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.RecordPursesDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ShoppingBookDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ShoppingCartDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ShoppingCartItemDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ShoppingOrderDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ShoppingOrderItemDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.SystemparamDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.UserPurseDO;
import com.zd.aoding.outsourcing.weChat.api.constant.ConfigLocal;
import com.zd.aoding.outsourcing.weChat.api.constant.SessionConstant;
import com.zd.aoding.outsourcing.weChat.api.facade.AddressFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsOptionFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsSpecFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.RecordFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SessionFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.ShoppingBookFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.ShoppingCartFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.ShoppingCartItemFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.ShoppingOrderFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.ShoppingOrderItemFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SystemparamFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.UserFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.UserPurseFacade;
import com.zd.aoding.outsourcing.weChat.api.utils.file.ConfigTemp;
import com.zd.aoding.plugin.KuaiDiNiao.KdniaoTrackQueryAPI;

@Api(value = "", description = "shopping 买买买，购物")
@Controller
@RequestMapping("user")
public class ShoppingUserController {
	@Autowired
	private SessionFacade sessionFacade;
	@Autowired
	private GoodsFacade goodsFacade;
	@Autowired
	private GoodsSpecFacade goodsSpecFacade;
	@Autowired
	private GoodsOptionFacade goodsOptionFacade;
	@Autowired
	private ShoppingCartFacade cartFacade;
	@Autowired
	private ShoppingCartItemFacade cartItemFacade;
	@Autowired
	private ShoppingOrderFacade orderFacade;
	@Autowired
	private ShoppingOrderItemFacade orderItemFacade;
	@Autowired
	private ShoppingBookFacade orderBookFacade;
	@Autowired
	private UserFacade userFacade;
	@Autowired
	private UserPurseFacade userPurseFacade;
	@Autowired
	private SystemparamFacade systemparamFacade;
	@Autowired
	private RecordFacade recordFacade;
	@Autowired
	private AddressFacade addressFacade;

	@ResponseBody
	@RequestMapping(value = "shopCart/add", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "加入购物车", httpMethod = "POST", notes = "创建购物车", response = ResponseUtil.class)
	public String shopCarAdd(
			@ApiParam(required = true, name = "goodsId", value = "产品id") @RequestParam(value = "goodsId", required = true) String goodsId,
			@ApiParam(required = true, name = "optionId", value = "规格配置id(无配置则设为0，有配置没选择也返回300)") @RequestParam(value = "optionId", required = true) String optionId,
			@ApiParam(required = false, name = "quantity", value = "数量") @RequestParam(value = "quantity", required = false) String quantity,
			// @ApiParam(required = true, name = "type", value = "类型 1积分 2现金")@RequestParam(value = "type", required = false) String type,
			@ApiParam(required = false, name = "iosCode", value = "iosCode") @RequestParam(value = "iosCode", required = false) String iosCode,
			HttpServletRequest request) {
		try {
			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
			if (sessionUser != null) {
				if (!StringUtil.isNumber(goodsId) || !StringUtil.isNumber(optionId)) {
					return ResponseUtil.paramErrorResultString("参数错误");
				}
				if (ConfigTemp.not_required_id_0.equals(optionId)) {
					System.out.println("=='0'");
					List<GoodsSpecBO> list = goodsSpecFacade.getGoodsSpecVoByGoodId(goodsId);
					if (list != null && list.size() > 0) {
						return ResponseUtil.showMSGResultString("未选择配置");
					}
				}
				GoodsOptionBO option = goodsOptionFacade.getOptionVoByPK(optionId);
				if (option != null && !goodsId.equals(option.getGoodsId() + "")) {
					LogUtil.dataError(optionId, this.getClass());
					return ResponseUtil.showMSGResultString("配置参数错误");
				}
				GoodsBO goods = goodsFacade.getGoodsVoByPK(goodsId);
				if (goods == null) {
					return ResponseUtil.paramErrorResultString("goodsId=" + goodsId);
				}

				double goodsPrice = goods.getMarketPrice();
				double optionPrice = option.getMarketPrice();
				// 默认购买数量1
				Integer quantityF = 1;
				if (StringUtil.isNumber(quantity)) {
					quantityF = Integer.parseInt(quantity);
				}
				// 查询购物车该商品数量
				int goodsNum = 0;
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("goodsId", goodsId);
				param.put("optionId", optionId);
				param.put("userId", sessionUser.getUserId());
				param.put("deleted", 0);
				List<ShoppingCartItemBO> goodsNumList = cartItemFacade.getListCartItemByMap(param);
				if (goodsNumList != null && goodsNumList.size() > 0) {
					goodsNum = goodsNumList.get(0).getQuantity();
				}

				if (!ConfigTemp.not_required_id_0.equals(optionId)) {
					if ((option.getStock() - goodsNum) < quantityF) {
						return ResponseUtil.showMSGResultString("产品库存不足，无法加入购物车");
					}
				} else {
					if ((goods.getShowStock() - goodsNum) < quantityF
							|| (goods.getTotalStock() - goodsNum) < quantityF) {
						return ResponseUtil.showMSGResultString("购物车已有" + goodsNum + "件商品,库存不足，无法加入购物车");
					}
				}
				// 查看购物车是否存在该商户
				ShoppingCartBO shoppingCartVo = cartFacade.getUserShoppingCartVoByDealerId(sessionUser.getUserId(), 1,
						"0", 1);
				if (shoppingCartVo != null) {
					// 查看购物车中是否有该配置产品
					ShoppingCartItemBO shoppingCartItemVo = cartItemFacade.getShoppingCartItemVoByGoodsIdAndOptionId(
							goodsId, optionId, shoppingCartVo.getShoppingCartId());
					if (shoppingCartItemVo != null) {
						quantityF = shoppingCartItemVo.getQuantity() + quantityF;
						shoppingCartItemVo.setQuantity(quantityF);
						ShoppingCartItemDO shoppingCartItemPo = new ShoppingCartItemDO();
						shoppingCartItemPo.setId(shoppingCartItemVo.getShoppingCartItemId());
						shoppingCartItemPo.setQuantity(quantityF);
						int i = cartItemFacade.updateCarItem(shoppingCartItemPo);
						if (i == 1) {
							return ResponseUtil.successResultString("成功加入购物车");
						}
					} else {
						if (ConfigTemp.not_required_id_0.equals(optionId)) {
							ShoppingCartItemDO newShopCartItem = new ShoppingCartItemDO(Integer.parseInt(optionId),
									Integer.parseInt(goodsId), shoppingCartVo.getShoppingCartId(), goodsPrice,
									quantityF, goods.getOriginalPrice(), goods.getCoverImgUrl(), goods.getTitle(),
									"无配置", shoppingCartVo.getBoughtUserId(), iosCode);
							int i = cartItemFacade.insertCarItem(newShopCartItem);
							if (i == 1) {
								return ResponseUtil.successResultString("成功加入购物车");
							}
						} else {
							ShoppingCartItemDO newShopCartItem = new ShoppingCartItemDO(Integer.parseInt(optionId),
									Integer.parseInt(goodsId), shoppingCartVo.getShoppingCartId(), optionPrice,
									quantityF, goods.getOriginalPrice(), goods.getCoverImgUrl(), goods.getTitle(),
									option.getTitle(), shoppingCartVo.getBoughtUserId(), iosCode);
							int i = cartItemFacade.insertCarItem(newShopCartItem);
							if (i == 1) {
								ShoppingCartDO updateShoppingCartPo = new ShoppingCartDO();
								updateShoppingCartPo.setId(shoppingCartVo.getShoppingCartId());
								updateShoppingCartPo.setShopGoodsNum(shoppingCartVo.getShopGoodsNum() + 1);
								int j = cartFacade.update(updateShoppingCartPo);
								if (j == 1) {
									return ResponseUtil.successResultString("成功加入购物车");
								}
							}
						}
					}
				} else {
					ShoppingCartDO newShoppingCartPo = new ShoppingCartDO("0", sessionUser.getUserId(), 1, 1);
					int j = cartFacade.insert(newShoppingCartPo);
					if (j == 1) {
						if (ConfigTemp.not_required_id_0.equals(optionId)) {
							ShoppingCartItemDO newShopCartItem = new ShoppingCartItemDO(Integer.parseInt(optionId),
									Integer.parseInt(goodsId), newShoppingCartPo.getId(), goodsPrice, quantityF,
									goods.getOriginalPrice(), goods.getCoverImgUrl(), goods.getTitle(), "无配置",
									sessionUser.getUserId(), iosCode);
							int i = cartItemFacade.insertCarItem(newShopCartItem);
							if (i == 1) {
								return ResponseUtil.successResultString("成功加入购物车");
							}
						} else {
							ShoppingCartItemDO newShopCartItem = new ShoppingCartItemDO(Integer.parseInt(optionId),
									Integer.parseInt(goodsId), newShoppingCartPo.getId(), optionPrice, quantityF,
									goods.getOriginalPrice(), goods.getCoverImgUrl(), goods.getTitle(),
									option.getTitle(), sessionUser.getUserId(), iosCode);
							int i = cartItemFacade.insertCarItem(newShopCartItem);
							if (i == 1) {
								return ResponseUtil.successResultString("成功加入购物车");
							}
						}
					}
				}
				return ResponseUtil.showMSGResultString("加入失败");
			}
			return ResponseUtil.notLoggedResultString();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}

	@ResponseBody
	@RequestMapping(value = "checkoutOrder/paging", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "付款成功以后订单详情", httpMethod = "POST", notes = "", response = ResponseUtil.class)
	@ApiResponses({ @ApiResponse(code = 1, message = "购物车实体类", response = ShoppingCartBO.class) })
	public String checkoutOrderPaging(
			@ApiParam(required = false, name = "pageNum", value = "当前pageNum页") @RequestParam(value = "pageNum", required = false) String pageNum,
			@ApiParam(required = false, name = "pageSize", value = "分页条数") @RequestParam(value = "pageSize", required = false) String pageSize,
			@ApiParam(required = true, name = "boughtWhat", value = "订单类型：订单簿‘orderBook’(正常流程返回订单簿提交的)，订单‘order’（待付款界面提交）") @RequestParam(value = "boughtWhat", required = true) String boughtWhat,
			@ApiParam(required = true, name = "orderId", value = "订单id（下单时返回的id）") @RequestParam(value = "orderId", required = true) String orderId,
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
				pageEntity.setOrderColumn("create_time");
				/**
				 * 查询条件参数
				 */
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("deleted", 0);
				param.put("boughtUserId", sessionUser.getUserId());
				if (!StringUtil.isNumber(orderId)) {
					return ResponseUtil.paramErrorResultString("参数错误");
				}
				if (ConfigLocal.pay_orderBook.equals(boughtWhat)) {
					param.put("shoppingOrderBookId", Integer.parseInt(orderId));
				} else {
					param.put("orderId", Integer.parseInt(orderId));
				}
				pageEntity.setParams(param);
				/**
				 * 查询
				 */
				PageResult<ShoppingOrderBO> pageResult = orderFacade.getPageOrderVo(pageEntity);
				return ResponseUtil.successResultString(pageResult);
			}
			return ResponseUtil.notLoggedResultString();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}

	@ResponseBody
	@RequestMapping(value = "shopCart/paging", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "查询购物车", httpMethod = "POST", notes = "", response = ResponseUtil.class)
	@ApiResponses({ @ApiResponse(code = 1, message = "购物车实体类", response = ShoppingCartBO.class) })
	public String shopCarPaging(
			@ApiParam(required = false, name = "pageNum", value = "当前pageNum页") @RequestParam(value = "pageNum", required = false) String pageNum,
			@ApiParam(required = false, name = "pageSize", value = "分页条数") @RequestParam(value = "pageSize", required = false) String pageSize,
			// @ApiParam(required = false, name = "type", value = "类型 1积分 2现金")
			// @RequestParam(value = "type", required = false) String type,
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
				pageEntity.setOrderColumn("create_time");
				/**
				 * 查询条件参数
				 */
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("deleted", 0);
				param.put("boughtUserId", sessionUser.getUserId());
				// if (StringUtil.isNumber(type)) {
				// param.put("type", Integer.parseInt(type));
				// }
				pageEntity.setParams(param);
				/**
				 * 查询
				 */
				PageResult<ShoppingCartBO> pageResult = cartFacade.getPageShoppingCartVo(pageEntity,
						sessionUser.getUserId());
				return ResponseUtil.successResultString(pageResult);
			}
			return ResponseUtil.notLoggedResultString();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}

	@ResponseBody
	@RequestMapping(value = "shopCart/edit", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "购物车编辑", httpMethod = "POST", notes = "创建购物车", response = ResponseUtil.class)
	public String shopCarEdit(
			@ApiParam(required = true, name = "param", value = "编辑购物车 ；json格式产品信息param=[{\"cartItemId\" : 1,\"optionId\" : 1,\"quantity\" : 1,\"iosCode\" : 1"
					+ "},...]") @RequestParam(value = "param", required = true) String param,
			HttpServletRequest request) {
		try {
			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
					
			if (sessionUser != null) {
				if (!param.contains("cartItemId") || !param.contains("optionId") || !param.contains("quantity")) {
					return ResponseUtil.paramErrorResultString("参数错误");
				}
				List<HashMap> items = JSON.parseArray(param, HashMap.class);
				if (items != null) {
					for (int i = 0; i < items.size(); i++) {
						Integer cartItemId = (Integer) items.get(i).get("cartItemId");
						Integer optionId = (Integer) items.get(i).get("optionId");
						Integer quantity = (Integer) items.get(i).get("quantity");
						String iosCode = (String) items.get(i).get("iosCode");
						quantity = quantity != null ? quantity : 1;
						GoodsOptionBO option = goodsOptionFacade.getOptionVoByPK(optionId);

						// 查询购物车该商品数量
						int goodsNum = 0;
						Map<String, Object> itemParam = new HashMap<String, Object>();
						itemParam.put("goodsId", option.getGoodsId());
						itemParam.put("optionId", optionId);
						itemParam.put("userId", sessionUser.getUserId());
						itemParam.put("deleted", 0);
						List<ShoppingCartItemBO> goodsNumList = cartItemFacade.getListCartItemByMap(itemParam);
						if (goodsNumList != null && goodsNumList.size() > 0) {
							goodsNum = goodsNumList.get(0).getQuantity();
						}

						GoodsBO goods = goodsFacade.getGoodsVoByPK(option.getGoodsId() + "");
						if (goods == null) {
							return ResponseUtil.paramErrorResultString("goodsId=" + option.getGoodsId());
						}

//						if (!ConfigTemp.not_required_id_0.equals(optionId)) {
//							if ((option.getStock() - goodsNum) < quantity) {
//								return ResponseUtil.errorResultString("产品库存不足，无法加入购物车");
//							}
//						} else {
//							if ((goods.getShowStock() - goodsNum) < quantity
//									|| (goods.getTotalStock() - goodsNum) < quantity) {
//								return ResponseUtil.errorResultString("产品库存不足，无法加入购物车");
//							}
//						}

						if (!ConfigTemp.not_required_id_0.equals(optionId + "")) {
							if (option == null) {
								return ResponseUtil.showMSGResultString("配置参数错误");
							}
						}
						ShoppingCartItemBO shoppingCartItemVo = cartItemFacade.getItemVoByPK(cartItemId);
						if (shoppingCartItemVo != null) {
							ShoppingCartItemDO shoppingCartItemPo = new ShoppingCartItemDO();
							shoppingCartItemPo.setId(shoppingCartItemVo.getShoppingCartItemId());
							shoppingCartItemPo.setQuantity(quantity);
							shoppingCartItemPo.setOptionId(optionId);
							ShoppingCartDO shoppingCart = cartFacade.getCartOnlyPoByPK(shoppingCartItemVo.getCartId());
							double optionPrice = option.getMarketPrice();
							// if (shoppingCart.getType().equals(
							// GoodsPo.type_score)) {
							// if (option != null) {
							// optionPrice = option.getScorePrice();
							// }
							// }
							// if (shoppingCart.getType().equals(
							// GoodsPo.type_cash)) {
							// if (option != null) {
							// optionPrice = option.getBonusPrice();
							// }
							// }
							if (!ConfigTemp.not_required_id_0.equals(optionId + "")) {
								shoppingCartItemPo.setActualPrice(optionPrice);
								shoppingCartItemPo.setShowOption(option.getTitle());
							}
							shoppingCartItemPo.setIosCode(iosCode);
							int j = cartItemFacade.updateCarItem(shoppingCartItemPo);
							if (j != 1) {
								LogUtil.operationRecord("操作失败");
							}
						}
					}
					return ResponseUtil.successResultString("修改成功");
				}
				return ResponseUtil.showMSGResultString("修改失败");
			}
			return ResponseUtil.notLoggedResultString();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}

	@ResponseBody
	@RequestMapping(value = "shopCart/deleted", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "购物车删除", httpMethod = "POST", notes = "创建购物车", response = ResponseUtil.class)
	public String shopCarDel(
			@ApiParam(required = true, name = "param", value = "编辑购物车 ；json格式产品信息param=[{\"cartItemId\" : 1"
					+ "},...]") @RequestParam(value = "param", required = true) String param,
			HttpServletRequest request) {
		try {
			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
					
			if (sessionUser != null) {
				if (!param.contains("cartItemId")) {
					return ResponseUtil.paramErrorResultString("参数错误");
				}
				List<HashMap> items = JSON.parseArray(param, HashMap.class);
				if (items != null) {
					for (int i = 0; i < items.size(); i++) {
						Integer cartItemId = (Integer) items.get(i).get("cartItemId");
						ShoppingCartItemBO shoppingCartItemVo = cartItemFacade.getItemVoByPK(cartItemId);
						if (shoppingCartItemVo != null) {
							int j = cartItemFacade.deletedCartItemPo(cartItemId);
							if (j != 1) {
								LogUtil.operationRecord("操作失败");
							}
						}
					}
					return ResponseUtil.successResultString("删除成功");
				}
				return ResponseUtil.showMSGResultString("删除失败");
			}
			return ResponseUtil.notLoggedResultString();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}

	@ResponseBody
	@RequestMapping(value = "cart/preOrder", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "购物车预下单", httpMethod = "POST", notes = "用于绘制下单界面（分两种情况）：1购物车到预下单(本方法)，2商品直接跳转预下单", response = ResponseUtil.class)
	public String preOrderCart(
			@ApiParam(required = true, name = "param", value = "json格式产品信息param=[{\"cartId\" : 1, \"cartItem\":[{\"cartItemId\" : 1},{\"cartItemId\" : 2}]},...]") @RequestParam(value = "param", required = true) String param,
			// @ApiParam(required = true, name = "type", value = "类型 1积分 2奖金")
			// @RequestParam(value = "type", required = false) String type,
			HttpServletRequest request) {
		try {
			if (!param.contains("cartId") || !param.contains("cartItem") || !param.contains("cartItemId")) {
				return ResponseUtil.paramErrorResultString("param参数错误");
			}
			// 获取兑换比例
			String proportion = "1";
			SystemparamDO systemparamPo = systemparamFacade.getSystemparamPoByCode("proportion");
			if (systemparamPo != null) {
				proportion = systemparamPo.getStringVale();
			}
			double d_proportion = Double.valueOf(proportion).doubleValue();

			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
					
			int scoreTotal = 0;// 积分总价
			if (sessionUser != null) {
				
				//用户积分
				double scoreCheck = 0.0;
				UserPurseBO userPurse = userPurseFacade.getUserPurseByUserId(sessionUser.getUserId(), "0");
				if(userPurse!=null){
					scoreCheck = userPurse.getScore();
				}
				
				double totalFee = 0;
				List<HashMap> catrt = JSON.parseArray(param, HashMap.class);
				if (catrt == null || catrt.size() == 0) {
					return ResponseUtil.errorResultString("参数错误");
				}
				List<ShoppingCartBO> shoppingCartVoList = new ArrayList<>();
				if (catrt != null) {
					System.out.println(catrt);
					for (int i = 0; i < catrt.size(); i++) {
						Integer cartId = (Integer) catrt.get(i).get("cartId");
						ShoppingCartBO cartVo = cartFacade.getCartVoByPK(cartId);
						Double atotalFee = 0D;
						if (cartVo != null) {
							List<HashMap> items = JSON.parseArray(catrt.get(i).get("cartItem").toString(),
									HashMap.class);
							List<ShoppingCartItemBO> cartItemVoList = new ArrayList<>();
							int goodNum = 0;
							for (int j = 0; j < items.size(); j++) {
								Integer cartItemId = (Integer) items.get(j).get("cartItemId");
								if (cartItemId != null) {
									ShoppingCartItemBO cartItem = cartItemFacade.getItemVoByPK(cartItemId);
									if (cartItem != null) {
										int cartNum = cartItem.getQuantity();//购物车数量
										int option = cartItem.getOptionId();
										if(option>0){
											GoodsOptionDO optionDO = goodsOptionFacade.getOptionPoByPK(option);
											if(optionDO!=null){
												int optionNum = optionDO.getStock();
												if(cartNum > optionNum){
													return ResponseUtil.showMSGResultString("库存不足");
												}
											}
										}
										GoodsDO goodsPo = goodsFacade.getGoodsPoByPK(cartItem.getGoodsId());
										if (goodsPo != null) {
											scoreTotal += goodsPo.getIntegralDeductible() * cartItem.getQuantity();
										}
										goodNum = goodNum + cartItem.getQuantity();
										Double itemTotalFee = cartItem.getActualPrice() * cartItem.getQuantity();
										atotalFee = atotalFee + itemTotalFee;
										cartItem.setItemTotalFee(itemTotalFee);
										cartItem.setIntegralDeductible(goodsPo.getIntegralDeductible());
										cartItem.setIntegralPercent(goodsPo.getIntegralPercent());
										cartItem.setRebate(goodsPo.getRebate());
										cartItemVoList.add(cartItem);
									} else {
										LogUtil.dataError("cartItemId=" + cartItemId + "cartItem=" + cartItem,getClass());
									}
								} else {
									LogUtil.dataError("cartItemId=" + cartItemId, getClass());
								}
							}
							if ((scoreTotal / d_proportion) > scoreCheck) {
								scoreTotal = (int) (scoreCheck * d_proportion);
							}
							cartVo.setShopGoodsNum(goodNum);
							totalFee = totalFee + atotalFee;
							cartVo.setShoppingCartItemVoList(cartItemVoList);
							cartVo.setIntegralDeductibleCount(scoreTotal);
							shoppingCartVoList.add(cartVo);
						}
					}
					Map<String, Object> map = new HashMap<>();
					// 返回开关状态
					int isOpen = 1;
					if (scoreCheck < 0 || scoreCheck == 0 || scoreTotal == 0) {
						isOpen = 0;
					}
					map.put("isOpen", isOpen);
					map.put("totalFee", totalFee);
					map.put("scoreTotal", scoreTotal);
					map.put("userMoney1", scoreCheck * d_proportion);
					map.put("shoppingCartVoList", shoppingCartVoList);
					return ResponseUtil.successResultString(map);
				}
			}
			return ResponseUtil.notLoggedResultString();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}

	@ResponseBody
	@RequestMapping(value = "direct/preOrder", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "直接预下单", httpMethod = "POST", notes = "用于绘制下单界面（分两种情况）：1购物车到预下单，2商品直接跳转预下单(本方法)", response = ResponseUtil.class)
	public String preOrderDirect(
			@ApiParam(required = true, name = "goodsId", value = "产品id") @RequestParam(value = "goodsId", required = true) String goodsId,
			@ApiParam(required = true, name = "optionId", value = "配置id") @RequestParam(value = "optionId", required = true) String optionId,
			@ApiParam(required = true, name = "quantity", value = "数量") @RequestParam(value = "quantity", required = true) String quantity,
			@ApiParam(required = false, name = "iosCode", value = "iosCode") @RequestParam(value = "iosCode", required = false) String iosCode,
			HttpServletRequest request) {
		try {
			if (!StringUtil.isNumber(goodsId) || !StringUtil.isNumber(optionId) || !StringUtil.isNumber(quantity)) {
				return ResponseUtil.paramErrorResultString("参数错误");
			}
			// 获取兑换比例
			String proportion = "1";
			SystemparamDO systemparamPo = systemparamFacade.getSystemparamPoByCode("proportion");
			if (systemparamPo != null) {
				proportion = systemparamPo.getStringVale();
			}
			double d_proportion = Double.valueOf(proportion).doubleValue();
			int scoreTotal = 0;

			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
					
			if (sessionUser != null) {
				
				//用户积分
				double scoreCheck = 0.0;
				UserPurseBO userPurse = userPurseFacade.getUserPurseByUserId(sessionUser.getUserId(), "0");
				if(userPurse!=null){
					scoreCheck = userPurse.getScore();
				}
				
				Double aTotalFee = 0D;
				GoodsOptionBO option = goodsOptionFacade.getOptionVoByPK(optionId);
				if (option != null && !goodsId.equals(option.getGoodsId() + "")) {
					LogUtil.dataError(optionId, this.getClass());
					return ResponseUtil.showMSGResultString("配置参数错误");
				}
				GoodsBO goods = goodsFacade.getGoodsVoByPK(goodsId);
				if (goods == null) {
					return ResponseUtil.paramErrorResultString("goodsId=" + goodsId);
				}
				double goodsPrice = goods.getMarketPrice();
				double optionPrice = option.getMarketPrice();
				// 默认购买数量1
				Integer quantityF = 1;
				if (StringUtil.isNumber(quantity)) {
					quantityF = Integer.parseInt(quantity);
				}
				scoreTotal = goods.getIntegralDeductible() * quantityF;
				ShoppingCartDO cartPo = new ShoppingCartDO("0", sessionUser.getUserId(), 1, 1);
				if (goods.getShowStock() < quantityF || goods.getTotalStock() < quantityF) {
					return ResponseUtil.showMSGResultString("产品库存不足，无法购买");
				} else {
					if (!ConfigTemp.not_required_id_0.equals(optionId)) {
						if (option.getStock() < quantityF) {
							return ResponseUtil.showMSGResultString("产品库存不足，无法购买");
						}
					}
				}
				if (ConfigTemp.not_required_id_0.equals(optionId + "")) {
					ShoppingCartItemDO cartItem = new ShoppingCartItemDO(Integer.parseInt(optionId),
							Integer.parseInt(goodsId), 0, goodsPrice, quantityF, goods.getOriginalPrice(),
							goods.getCoverImgUrl(), goods.getTitle(), "无配置", sessionUser.getUserId(), iosCode);
					Double itemTotalFee = cartItem.getActualPrice() * cartItem.getQuantity();
					aTotalFee = aTotalFee + itemTotalFee;
					cartItem.setItemTotalFee(itemTotalFee);
					List<ShoppingCartItemBO> cartItemVoList = new ArrayList<>();
					ShoppingCartItemBO cartItemVo = new ShoppingCartItemBO(cartItem);
					String dealerName = "";
					String dealerPhone = "";
					if ((scoreTotal / d_proportion) > scoreCheck) {
						scoreTotal = (int) (scoreCheck * d_proportion);
					}
					cartItemVo.setDealerName(dealerName);
					cartItemVo.setIntegralDeductible(goods.getIntegralDeductible());
					cartItemVo.setIntegralPercent(goods.getIntegralPercent());
					cartItemVo.setRebate(goods.getRebate());
					cartItemVoList.add(cartItemVo);
					ShoppingCartBO shoppingCartVo = new ShoppingCartBO(cartPo, ShoppingCartBO.TO_ALL);
					shoppingCartVo.setDealerPhone(dealerPhone);
					shoppingCartVo.setShopGoodsNum(quantityF);
					shoppingCartVo.setShoppingCartItemVoList(cartItemVoList);
					shoppingCartVo.setIntegralDeductibleCount(scoreTotal);
					Map<String, Object> map = new HashMap<>();
					// 返回开关状态
					int isOpen = 1;
					if (scoreCheck < 0 ||scoreCheck == 0 || scoreTotal == 0) {
						isOpen = 0;
					}
					map.put("isOpen", isOpen);
					map.put("totalFee", aTotalFee);
					map.put("scoreTotal", scoreTotal);
					List<ShoppingCartBO> shoppingCartVoList = new ArrayList<>();
					shoppingCartVoList.add(shoppingCartVo);
					map.put("shoppingCartVoList", shoppingCartVoList);
					return ResponseUtil.successResultString(map);
				} else {
					ShoppingCartItemDO cartItem = new ShoppingCartItemDO(Integer.parseInt(optionId),
							Integer.parseInt(goodsId), 0, optionPrice, quantityF, goods.getOriginalPrice(),
							goods.getCoverImgUrl(), goods.getTitle(), option.getTitle(), sessionUser.getUserId(),
							iosCode);
					Double itemTotalFee = cartItem.getActualPrice() * cartItem.getQuantity();
					aTotalFee = aTotalFee + itemTotalFee;
					cartItem.setItemTotalFee(itemTotalFee);
					List<ShoppingCartItemBO> cartItemVoList = new ArrayList<>();
					ShoppingCartItemBO cartItemVo = new ShoppingCartItemBO(cartItem);
					String dealerName = "";
					String dealerPhone = "";

					if (scoreTotal > scoreCheck) {
						scoreTotal = (int) (scoreCheck * 1);
					}
					cartItemVo.setDealerName(dealerName);
					cartItemVo.setIntegralDeductible(goods.getIntegralDeductible());
					cartItemVo.setIntegralPercent(goods.getIntegralPercent());
					cartItemVo.setRebate(goods.getRebate());
					cartItemVoList.add(cartItemVo);
					ShoppingCartBO shoppingCartVo = new ShoppingCartBO(cartPo, ShoppingCartBO.TO_ALL);
					shoppingCartVo.setDealerPhone(dealerPhone);
					shoppingCartVo.setShopGoodsNum(quantityF);
					shoppingCartVo.setShoppingCartItemVoList(cartItemVoList);
					shoppingCartVo.setIntegralDeductibleCount(scoreTotal);

					Map<String, Object> map = new HashMap<>();
					// 返回开关状态
					int isOpen = 1;
					if (scoreCheck < 0 ||scoreCheck == 0 || scoreTotal == 0) {
						isOpen = 0;
					}
					map.put("isOpen", isOpen);
					map.put("totalFee", aTotalFee);
					map.put("scoreTotal", scoreTotal);
					List<ShoppingCartBO> shoppingCartVoList = new ArrayList<>();
					shoppingCartVoList.add(shoppingCartVo);
					map.put("shoppingCartVoList", shoppingCartVoList);
					map.put("userMoney1", scoreCheck * d_proportion);
					return ResponseUtil.successResultString(map);
				}
			}
			return ResponseUtil.notLoggedResultString();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}

	@ResponseBody
	@RequestMapping(value = "order/order", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; text/html; charset=utf-8")
	@ApiOperation(value = "下单", httpMethod = "POST", notes = "创建商城订单 参数param：orderId购物车订单id（直接下单id==0），optionId参数规格配置id(无配置则设为0，有配置没选则也返回300)", response = ResponseUtil.class)
	public String order(
			@ApiParam(required = true, name = "param", value = "json格式产品信息param=[{\"cartId\" : 1,\"dealerId\" : 1,\"message\" :\"留言\" , \"cartItem\":[{\"cartItemId\" : 1,\"goodsId\" : 1,\"optionId\" : 1,\"quantity\" : 5},{\"cartItemId\" : 2\"goodsId\" : 1,\"optionId\" : 1,\"quantity\" : 5}]},...]") @RequestParam(value = "param", required = true) String param,
			// @ApiParam(required = true, name = "goodsIdParam", value =
			// "json格式产品信息goodsIdParam=[{\"goodsId\" : 1},...]")
			// @RequestParam(value = "goodsIdParam", required = true) String
			// goodsIdParam,
			@ApiParam(required = true, name = "boughtAddressId", value = "收货地址id") @RequestParam(value = "boughtAddressId", required = true) String boughtAddressId,
			@ApiParam(required = true, name = "score", value = "积分值(不适用填0)") @RequestParam(value = "score", required = false) String score,
			@ApiParam(required = true, name = "isuse", value = "是否使用积分(1.使用 2.不使用)") @RequestParam(value = "isuse", required = false) String isuse,
			@ApiParam(required = false, name = "password", value = "支付密码") @RequestParam(value = "password", required = false) String password,
			@ApiParam(required = true, name = "payType", value = "支付方式(1.支付宝,5余额)") @RequestParam(value = "payType", required = false) String payType,
			HttpServletRequest request) {
		try {
			if (!param.contains("cartId") || !param.contains("cartItem") || !param.contains("message")
					|| !param.contains("cartItemId") || !param.contains("goodsId") || !param.contains("optionId")
					|| !param.contains("quantity") || !StringUtil.isNumber(boughtAddressId)) {
				return ResponseUtil.paramErrorResultString("param参数错误");
			}
			// 前台传来积分值
			int scoreInt = 0;
			if ("1".equals(isuse)) {
				if (StringUtil.isNULL(password)) {
					return ResponseUtil.showMSGResultString("请输入支付密码");
				}
				scoreInt = Integer.valueOf(score).intValue();
			}
			if ("5".equals(payType)) {
				if (StringUtil.isNULL(password)) {
					return ResponseUtil.showMSGResultString("请输入支付密码");
				}
			}
			// 获取兑换比例
			String proportion = "1";
			SystemparamDO systemparamPo = systemparamFacade.getSystemparamPoByCode("proportion");
			if (systemparamPo != null) {
				proportion = systemparamPo.getStringVale();
			}
			double d_proportion = Double.valueOf(proportion).doubleValue();

			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
					
			if (sessionUser != null) {
				if ("1".equals(isuse)  || "5".equals(payType)) {
				    UserPurseDO userPurse = userPurseFacade.getPursePoByUserId(sessionUser.getUserId(), "0");
				    if (userPurse == null) {
						return ResponseUtil.errorResultString("未找到用户钱包");
					}
					if (!MD5Util.MD5(password).equals(userPurse.getPassword())) {
						return ResponseUtil.showMSGResultString("支付密码错误");
					}
					if(ConfigLocal.password_purse.equals(password)){
						return ResponseUtil.showMSGResultString("请修改默认密码后再进行支付");
					}
				}
				// 1.判断是否使用积分
				if ("2".equals(isuse)) {
					scoreInt = 0;
				}
				// 2.获取个人积分
				double userScore = 0.0;
				UserPurseDO purse = userPurseFacade.getPursePoByUserId(sessionUser.getUserId(), "0");
				if(purse==null){
					return ResponseUtil.errorResultString("查询钱包失败");
				}
				userScore = purse.getScore();
				if (userScore < 0 && "1".equals(isuse)) {
					return ResponseUtil.showMSGResultString("积分不足");
				}
				if (userScore < (scoreInt / d_proportion)) {
					return ResponseUtil.showMSGResultString("积分不足");
				}

				double totalFee = 0;
				double realTotalFee = 0;
				List<HashMap> catrt = JSON.parseArray(param, HashMap.class);
				if (catrt == null || catrt.size() == 0) {
					return ResponseUtil.errorResultString("参数错误");
				}

				List<ShoppingOrderBO> orderVoList = new ArrayList<>();
				if (catrt != null) {
					System.out.println("catrt == " + catrt);
					for (int i = 0; i < catrt.size(); i++) {
						Integer cartId = (Integer) catrt.get(i).get("cartId");
						String message = (String) catrt.get(i).get("message");
						Integer dealerId = 1;
						System.out.println("cartId == " + cartId);
						ShoppingCartDO cartPo = cartFacade.getCartOnlyPoByPK(cartId);
						if (cartId == 0) {
							Double showroomTotalFee = 0D;
							ShoppingOrderDO orderPo = new ShoppingOrderDO("0", sessionUser.getUserId(), message,
									Integer.parseInt(boughtAddressId), dealerId, 1);
							UserAddressBO address = addressFacade.getAddressVoByPK(boughtAddressId);
							if(address!=null){
								orderPo.setUpdateConsignee(address.getConsignee());
								orderPo.setUpdatePhone(address.getPhone());
								orderPo.setUpdateAddress(address.getFullAddress());
							}
							// 下单
							int iO = orderFacade.order(orderPo);
							if (iO == 1) {
								List<HashMap> items = JSON.parseArray(catrt.get(i).get("cartItem").toString(),
										HashMap.class);
								System.out.println("items == " + items);
								List<ShoppingOrderItemBO> orderItemVoList = new ArrayList<>();
								int goodNum = 0;
								for (int j = 0; j < items.size(); j++) {
									Integer cartItemId = (Integer) items.get(j).get("cartItemId");
									Integer optionId = (Integer) items.get(j).get("optionId");
									Integer goodsId = (Integer) items.get(j).get("goodsId");
									Integer quantity = (Integer) items.get(j).get("quantity");
									GoodsOptionBO option = goodsOptionFacade.getOptionVoByPK(optionId);
									if (option != null && !goodsId.equals(option.getGoodsId())) {
										LogUtil.dataError(optionId + "", this.getClass());
										return ResponseUtil.showMSGResultString("配置参数错误");
									}
									GoodsBO goods = goodsFacade.getGoodsVoByPK(goodsId + "");
									if (goods == null) {
										return ResponseUtil.paramErrorResultString("goodsId=" + goodsId);
									}
									Integer integralDeductibleCount = 0;// 商品总积分
									if (goods.getIntegralDeductible() != null && goods.getIntegralDeductible() > 0) {
										integralDeductibleCount = goods.getIntegralDeductible();
									}
									if ("1".equals(isuse)) {
										if (scoreInt > integralDeductibleCount * quantity) {
											orderPo.setId(orderPo.getId());
											orderPo.setDeleted(1);
											orderFacade.updateOrderPo(orderPo);
											return ResponseUtil.showMSGResultString("输入积分不能大于商品积分");
										}
									}
									double goodsPrice = goods.getMarketPrice();
									double optionPrice = option.getMarketPrice();
									// 默认购买数量1
									if (quantity == null) {
										quantity = 1;
									}
									if (quantity != null && quantity == 0) {
										quantity = 1;
									}
									// 无配置
									if (ConfigTemp.not_required_id_0.equals(optionId + "")) {
										ShoppingOrderItemDO orderItemPo = new ShoppingOrderItemDO(orderPo.getId(),
												optionId, goodsId, goodsPrice, quantity, goods.getOriginalPrice(),
												goods.getCoverImgUrl(), goods.getTitle(), "无配置");
										goodNum = goodNum + quantity;
										Double itemTotalFee = orderItemPo.getActualPrice() * orderItemPo.getQuantity();
										showroomTotalFee = showroomTotalFee + itemTotalFee;
										orderItemPo.setIntegralDeductible(goods.getIntegralDeductible());
										orderItemPo.setIntegralPercent(goods.getIntegralPercent());
										int iOI = orderItemFacade.insertOrderItem(orderItemPo);
										System.out.println("optionId==0,iOI==" + iOI);
										if (iOI == 1) {
											orderItemVoList.add(new ShoppingOrderItemBO(orderItemPo));
										}
									} else if (option != null) {
										ShoppingOrderItemDO orderItemPo = new ShoppingOrderItemDO(orderPo.getId(),
												optionId, goodsId, optionPrice, quantity, goods.getOriginalPrice(),
												goods.getCoverImgUrl(), goods.getTitle(), option.getTitle());
										goodNum = goodNum + quantity;
										Double itemTotalFee = orderItemPo.getActualPrice() * orderItemPo.getQuantity();
										showroomTotalFee = showroomTotalFee + itemTotalFee;
										orderItemPo.setIntegralDeductible(goods.getIntegralDeductible());
										orderItemPo.setIntegralPercent(goods.getIntegralPercent());
										int iOI = orderItemFacade.insertOrderItem(orderItemPo);
										if (iOI == 1) {
											orderItemVoList.add(new ShoppingOrderItemBO(orderItemPo));
										}
									}
								}
								orderPo.setGoodsNum(goodNum);
								orderPo.setTotalFee(showroomTotalFee);
								totalFee = totalFee + showroomTotalFee;
								realTotalFee = totalFee - scoreInt;
								if (totalFee < 0) {
									totalFee = 0;
								}
								if (realTotalFee < 0) {
									realTotalFee = 0;
								}
								orderPo.setRealTotalFee(realTotalFee);
								orderPo.setTotalFee(totalFee);
								iO = orderFacade.updateOrderPo(orderPo);
								if (iO == 1) {
									ShoppingOrderBO shoppingOrderVo = new ShoppingOrderBO(orderPo,
											ShoppingCartBO.TO_ALL);
									shoppingOrderVo.setShoppingOrderItemVoList(orderItemVoList);
									orderVoList.add(shoppingOrderVo);
									System.out.println("orderVoList.size=" + orderVoList.size());
								}
								orderFacade.changeShowStock(orderPo.getId());
							}
						}
						if (cartId != 0) {
							Double supplierTotalFee = 0D;
							if (cartPo != null) {
								ShoppingOrderDO orderPo = new ShoppingOrderDO(cartPo, message,
										Integer.parseInt(boughtAddressId), dealerId);
								UserAddressBO address = addressFacade.getAddressVoByPK(boughtAddressId);
								if(address!=null){
									orderPo.setUpdateConsignee(address.getConsignee());
									orderPo.setUpdatePhone(address.getPhone());
									orderPo.setUpdateAddress(address.getFullAddress());
								}
								// 下单
								int iO = orderFacade.order(orderPo);
								if (iO == 1) {
									List<HashMap> items = JSON.parseArray(catrt.get(i).get("cartItem").toString(),
											HashMap.class);
									System.out.println(items);
									List<ShoppingOrderItemBO> orderItemVoList = new ArrayList<>();
									int goodNum = 0;
									for (int j = 0; j < items.size(); j++) {
										Integer cartItemId = (Integer) items.get(j).get("cartItemId");
										Integer goodsId = (Integer) items.get(j).get("goodsId");
										GoodsBO goods = goodsFacade.getGoodsVoByPK(goodsId + "");
										if (goods == null) {
											return ResponseUtil.paramErrorResultString("goodsId=" + goodsId);
										}
										if (cartItemId != null) {
											ShoppingCartItemDO cartItem = cartItemFacade.getCartItemPoByPK(cartItemId);
											if (cartItem != null) {
												ShoppingOrderItemDO orderItemPo = new ShoppingOrderItemDO(
														orderPo.getId(), cartItem);
												goodNum = goodNum + cartItem.getQuantity();// 获取总数
												Double itemTotalFee = cartItem.getActualPrice()
														* cartItem.getQuantity();// 获取该商品总价
												supplierTotalFee = supplierTotalFee + itemTotalFee;// 所有商品总价
												orderItemPo.setItemTotalFee(itemTotalFee);
												orderItemPo.setIntegralDeductible(goods.getIntegralDeductible());
												orderItemPo.setIntegralPercent(goods.getIntegralPercent());
												int iOI = orderItemFacade.insertOrderItem(orderItemPo);
												if (iOI == 1) {
													// 删除购物车中明细
													int iCI = cartItemFacade.deletedCartItemPo(cartItemId);
													orderItemVoList.add(new ShoppingOrderItemBO(orderItemPo));
												}
											} else {
												LogUtil.dataError("cartItemId=" + cartItemId + "cartItem=" + cartItem,
														getClass());
											}
										} else {
											LogUtil.dataError("cartItemId=" + cartItemId, getClass());
										}
									}
									orderPo.setGoodsNum(goodNum);
									orderPo.setTotalFee(supplierTotalFee);
									totalFee = totalFee + supplierTotalFee;
									realTotalFee = totalFee - scoreInt;
									if (totalFee < 0) {
										totalFee = 0;
									}
									if (realTotalFee < 0) {
										realTotalFee = 0;
									}
									orderPo.setRealTotalFee(realTotalFee);
									orderPo.setTotalFee(totalFee);
									iO = orderFacade.updateOrderPo(orderPo);
									if (iO == 1) {
										ShoppingOrderBO shoppingOrderVo = new ShoppingOrderBO(orderPo,
												ShoppingCartBO.TO_ALL);
										shoppingOrderVo.setShoppingOrderItemVoList(orderItemVoList);
										orderVoList.add(shoppingOrderVo);
									}
									orderFacade.changeShowStock(orderPo.getId());
								}
							}
						}
					}
					if (orderVoList.size() > 0) {
						ShoppingBookDO shoppingBookPo = new ShoppingBookDO(param, "0", sessionUser.getUserId(),
								Integer.parseInt(boughtAddressId), realTotalFee, "下单", ShoppingBookDO.type_Shopping);
						int iOB = orderBookFacade.insertOrderBookPo(shoppingBookPo);
						if (iOB == 1) {
							for (ShoppingOrderBO shoppingOrderVo : orderVoList) {
								ShoppingOrderDO shoppingOrderPo = new ShoppingOrderDO();
								shoppingOrderPo.setId(shoppingOrderVo.getId());
								shoppingOrderPo.setShoppingOrderBookId(shoppingBookPo.getId());
								shoppingOrderPo.setScoreTotal(scoreInt);
								shoppingOrderPo.setProportion(d_proportion);
								shoppingOrderPo.setPayType(Integer.parseInt(payType));
								int k = orderFacade.updateOrderPo(shoppingOrderPo);
								if (k == 1 && scoreInt > 0) {
									// 扣除用户积分
									UserPurseDO userPursePo = userPurseFacade.getPursePoByUserId(sessionUser.getUserId(), "0");
									userPursePo.setScore(userScore-(scoreInt / d_proportion));
									int p = userPurseFacade.updatePurse(userPursePo);
									if (p == 1) {
										// 加入日志
										if(scoreInt>0){
											RecordPursesDO recordPursesDO = new RecordPursesDO("", "下单后扣除积分成功", 
													RecordPursesDO.PURSETYPE_USER,shoppingOrderPo.getId(), 
													sessionUser.getUserId(), RecordPursesDO.PURSETYPE_SCORE, RecordPursesDO.TYPE_CUT, 
													scoreInt, "score", sessionUser.getUsername()+"在商城消费积分:"+scoreInt);
											int u = recordFacade.insertRecordPurseDO(recordPursesDO);
											if (u == 1) {
												 System.err.println("添加日志成功");
											}
										}
									}
								}
							}
							Map<String, Object> map = new HashMap<>();
							map.put("orderBookId", shoppingBookPo.getId());
							map.put("totalFee", totalFee);
							map.put("realTotalFee", realTotalFee);
							map.put("shoppingCartVoList", orderVoList);
							map.put("payType", payType);
							map.put("isuse", isuse);
							return ResponseUtil.successResultString(map);
						}
					}
					return ResponseUtil.showMSGResultString("下单失败");
				}
			}
			return ResponseUtil.notLoggedResultString();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}

	public static boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式
		Matcher match = pattern.matcher(str);
		if (match.matches() == false) {
			return false;
		} else {
			return true;
		}
	}

	@ResponseBody
	@RequestMapping(value = "order/paging", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "分页查询订单", httpMethod = "POST", notes = "分页查询商城订单", response = ResponseUtil.class)
	public String orderOrderPaging(
			@ApiParam(required = false, name = "pageNum", value = "当前pageNum页") @RequestParam(value = "pageNum", required = false) String pageNum,
			@ApiParam(required = false, name = "pageSize", value = "分页条数") @RequestParam(value = "pageSize", required = false) String pageSize,
			@ApiParam(required = false, name = "process", value = "1:待付款，2待发货，3已发货，4已收货，(-1查询全部)") @RequestParam(value = "process", required = false) String process,
			// @ApiParam(required = false, name = "type", value = "1积分2奖金")
			// @RequestParam(value = "type", required = false) String type,
			HttpServletRequest request) {
		try {
			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
					
			if (sessionUser != null) {
				String sSearch = request.getParameter("sSearch");
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
				pageEntity.setOrderColumn("create_time");
				pageEntity.setOrderTurn("DESC");
				/**
				 * 查询条件参数
				 */
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("boughtUserId", sessionUser.getUserId());
				param.put("isShow", 0);
				param.put("deleted", 0);
				// if (StringUtil.isNumber(type)) {
				// param.put("orderType", Integer.parseInt(type));
				// }
				if (process != null) {
					if (BaseOrder.process_XiaDan.equals(Integer.parseInt(process))) {
						param.put("process", BaseOrder.process_XiaDan);
					}
					if (BaseOrder.process_FuKuan.equals(Integer.parseInt(process))) {
						param.put("process", BaseOrder.process_FuKuan);
						System.out.println(process);
					}
					if (BaseOrder.process_FaHuo.equals(Integer.parseInt(process))) {
						param.put("process", BaseOrder.process_FaHuo);
					}
					if (BaseOrder.process_ShouHuo.equals(Integer.parseInt(process))) {
						param.put("process", BaseOrder.process_ShouHuo);
					}
					if (BaseOrder.process_PingJia.equals(Integer.parseInt(process))) {
						param.put("process", BaseOrder.process_PingJia);
						if (BaseOrder.process_PingJia.equals(-1)) {
							param.put("noShoppingCart", 0);
						}
					} else {
						param.put("noShoppingCart", 0);
					}
				} else {
					param.put("noShoppingCart", 0);
				}
				pageEntity.setParams(param);
				/**
				 * 查询
				 */
				PageResult<ShoppingOrderBO> pageResult = orderFacade.getPageOrderVo(pageEntity);
				return ResponseUtil.successResultString(pageResult);
			}
			return ResponseUtil.notLoggedResultString();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}

	@ResponseBody
	@RequestMapping(value = "order/detail", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "查询某个商城订单", httpMethod = "POST", notes = "查询某个商城订单", response = ResponseUtil.class)
	@ApiResponses({ @ApiResponse(code = 1, message = "返回实体类注释", response = ShoppingOrderBO.class) })
	public String orderDetail(
			@ApiParam(required = true, name = "orderId", value = "商城订单id") @RequestParam(value = "orderId", required = true) String orderId,
			HttpServletRequest request) {
		try {
			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
					
			if (sessionUser != null) {
				if (StringUtil.isNumber(orderId)) {
					ShoppingOrderBO shoppingOrderVo = orderFacade.getOrderVoByPK(Integer.parseInt(orderId));
					if (shoppingOrderVo != null) {
						SystemparamDO systemparamPo = systemparamFacade.getSystemparamPoByCode("servicePhone");
						if (systemparamPo != null) {
							shoppingOrderVo.setServicePhone(systemparamPo.getStringVale());
						}
						return ResponseUtil.successResultString(shoppingOrderVo);
					}
					return ResponseUtil.paramErrorResultString("订单不存在orderId=" + orderId);
				}
				return ResponseUtil.errorResultString("orderId不正确" + orderId);
			}
			return ResponseUtil.notLoggedResultString();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}

	@ResponseBody
	@RequestMapping(value = "order/confirmReceipt", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "确认收货", httpMethod = "POST", notes = "确认收货", response = ResponseUtil.class)
	@ApiResponses({ @ApiResponse(code = 1, message = "返回实体类注释", response = ShoppingOrderBO.class) })
	public String confirmReceipt(
			@ApiParam(required = true, name = "orderId", value = "订单id") @RequestParam(value = "orderId", required = true) String orderId,
			HttpServletRequest request) {
		try {
			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
					
			if (sessionUser != null) {
				if (StringUtil.isNumber(orderId)) {
					ShoppingOrderBO shoppingOrderVo = orderFacade.getOrderVoByPK(Integer.parseInt(orderId));
					if (shoppingOrderVo != null) {
						if (!sessionUser.getUserId().equals(shoppingOrderVo.getBoughtUserId())) {
							return ResponseUtil.showMSGResultString("确认收货失败,这个订单不是你的");
						}
						if (!ShoppingOrderDO.process_FaHuo.equals(shoppingOrderVo.getProcess())) {
							return ResponseUtil.showMSGResultString("当前订单状态不能确认收货");
						}
						int i = orderFacade.checkReceive(Integer.parseInt(orderId), 0);
						if (i == 1) {
							return ResponseUtil.successResultString("确认收货成功");
						}
						return ResponseUtil.showMSGResultString("确认收货失败");
					}
					return ResponseUtil.paramErrorResultString("订单不存在orderId=" + orderId);
				}
				return ResponseUtil.errorResultString("orderId不正确" + orderId);
			}
			return ResponseUtil.notLoggedResultString();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}

	@ResponseBody
	@RequestMapping(value = "order/getGoodsNum", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "查询购物车数量", httpMethod = "POST", notes = "查询购物车数量", response = ResponseUtil.class)
	public String orderGetGoodsNum(
			// @ApiParam(required = false, name = "type", value = "1积分2奖金")
			// @RequestParam(value = "type", required = false) String type,
			HttpServletRequest request) {
		try {
			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
					
			if (sessionUser != null) {
				/**
				 * 查询条件参数
				 */
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("boughtUserId", sessionUser.getUserId());
				// param.put("type", type);
				param.put("deleted", 0);
				/**
				 * 查询
				 */
				int quality = 0;
				List<ShoppingCartBO> list = cartFacade.getGoodsNum(param);
				for (int i = 0; i < list.size(); i++) {
					int shoppingCartId = list.get(i).getShoppingCartId();
					List<ShoppingCartItemBO> itemList = cartItemFacade.getListByCartId(shoppingCartId);
					for (int j = 0; j < itemList.size(); j++) {
						quality += itemList.get(j).getQuantity();
					}
				}
				return ResponseUtil.successResultString(quality);
			}
			return ResponseUtil.notLoggedResultString();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}

	@ResponseBody
	@RequestMapping(value = "order/cancel", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "取消订单", httpMethod = "POST", notes = "取消订单", response = ResponseUtil.class)
	@ApiResponses({ @ApiResponse(code = 1, message = "返回实体类注释", response = ShoppingOrderBO.class) })
	public String cancel(
			@ApiParam(required = true, name = "orderId", value = "订单id") @RequestParam(value = "orderId", required = true) String orderId,
			HttpServletRequest request) {
		try {
			// 获取兑换比例
			String proportion = "1";
			SystemparamDO systemparamPo = systemparamFacade.getSystemparamPoByCode("proportion");
			if (systemparamPo != null) {
				proportion = systemparamPo.getStringVale();
			}
			double d_proportion = Double.valueOf(proportion).doubleValue();

			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
					
			if (sessionUser != null) {
				
				//用户积分
				double scoreCheck = 0.0;
				UserPurseBO userPurse = userPurseFacade.getUserPurseByUserId(sessionUser.getUserId(), "0");
				if(userPurse!=null){
					scoreCheck = userPurse.getScore();
				}
				
				if (StringUtil.isNumber(orderId)) {
					ShoppingOrderBO shoppingOrderVo = orderFacade.getOrderVoByPK(Integer.parseInt(orderId));
					if (shoppingOrderVo != null) {
						if (!sessionUser.getUserId().equals(shoppingOrderVo.getBoughtUserId())) {
							return ResponseUtil.showMSGResultString("取消订单失败,这个订单不是你的");
						}
						if (!ShoppingOrderDO.process_XiaDan.equals(shoppingOrderVo.getProcess())) {
							return ResponseUtil.showMSGResultString("当前订单状态不能取消订单");
						}
						int i = orderFacade.cancelOrder(Integer.parseInt(orderId));
						if (i == 1) {
							int orderScore = shoppingOrderVo.getScoreTotal();// 获取订单积分
							UserPurseDO userPurseDO = userPurseFacade.getPursePoByUserId(sessionUser.getUserId(), "0");
							userPurseDO.setScore(scoreCheck+ (orderScore / d_proportion));
							int j = userPurseFacade.updatePurse(userPurseDO);
							if (j == 1) {
								if(orderScore>0){
									// 加入日志
									RecordPursesDO recordPursesDO = new RecordPursesDO("", "返回积分成功", 
											RecordPursesDO.PURSETYPE_USER,Integer.parseInt(orderId), 
											sessionUser.getUserId(), RecordPursesDO.PURSETYPE_SCORE, RecordPursesDO.TYPE_CUT, 
											orderScore, "score", "您在商城取消订单返回积分:"+orderScore);
									int u = recordFacade.insertRecordPurseDO(recordPursesDO);
								}
							}
							return ResponseUtil.successResultString("取消订单成功");
						}
						return ResponseUtil.showMSGResultString("取消订单失败");
					}
					return ResponseUtil.errorResultString("订单不存在orderId=" + orderId);
				}
				return ResponseUtil.errorResultString("orderId不正确" + orderId);
			}
			return ResponseUtil.notLoggedResultString();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}

	@ResponseBody
	@RequestMapping(value = "order/deleted", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "删除订单", httpMethod = "POST", notes = "删除订单", response = ResponseUtil.class)
	@ApiResponses({ @ApiResponse(code = 1, message = "返回实体类注释", response = ShoppingOrderBO.class) })
	public String deleted(
			@ApiParam(required = true, name = "orderId", value = "订单id") @RequestParam(value = "orderId", required = true) String orderId,
			HttpServletRequest request) {
		try {
			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
					
			if (sessionUser != null) {
				if (StringUtil.isNumber(orderId)) {
					ShoppingOrderBO shoppingOrderVo = orderFacade.getOrderVoByPK(Integer.parseInt(orderId));
					if (shoppingOrderVo != null) {
						if (!sessionUser.getUserId().equals(shoppingOrderVo.getBoughtUserId())) {
							return ResponseUtil.showMSGResultString("删除订单失败,这个订单不是你的");
						}
						if (!ShoppingOrderDO.process_ShouHuo.equals(shoppingOrderVo.getProcess())) {
							return ResponseUtil.showMSGResultString("当前订单状态不能删除订单");
						}
						int i = orderFacade.deletedOrder(Integer.parseInt(orderId));
						if (i == 1) {
							return ResponseUtil.successResultString("删除订单成功");
						}
						return ResponseUtil.showMSGResultString("删除订单失败");
					}
					return ResponseUtil.errorResultString("订单不存在orderId=" + orderId);
				}
				return ResponseUtil.errorResultString("orderId不正确" + orderId);
			}
			return ResponseUtil.notLoggedResultString();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}

	@ResponseBody
	@RequestMapping(value = "order/countNum", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "用户订单数量", httpMethod = "POST", notes = "用户订单数量", response = ResponseUtil.class)
	@ApiResponses({ @ApiResponse(code = 1, message = "返回实体类注释", response = ShoppingOrderBO.class) })
	public String countNum(HttpServletRequest request) {
		try {
			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
					
			if (sessionUser != null) {
				Map<String, Object> map = orderFacade.countUserOrderNum(sessionUser.getUserId());
				return ResponseUtil.successResultString(map);
			}
			return ResponseUtil.notLoggedResultString();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}

	@ResponseBody
	@RequestMapping(value = "order/getOrderTrackInfo", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "查看快递详情", httpMethod = "POST", notes = "查看快递详情", response = ResponseUtil.class)
	public String getKdniaoInfo(
			@ApiParam(required = false, name = "orderId", value = "订单id") @RequestParam(value = "orderId", required = false) String orderId,
			HttpServletRequest request) {
		try {
			if (!StringUtil.isNULL(orderId) && !StringUtil.isNumber(orderId)) {
				return ResponseUtil.paramErrorResultString("参数错误");
			}
			ShoppingOrderBO orderVo = orderFacade.getOrderVoByPK(Integer.parseInt(orderId));
			String trackName = orderVo.getExpressName();
			String trackNum = orderVo.getExpressNum();
			if (StringUtil.isNULL(trackNum)) {
				return ResponseUtil.paramErrorResultString("该订单缺少快递单号");
			}
			if (StringUtil.isNULL(trackName)) {
				return ResponseUtil.paramErrorResultString("该订单缺少快递公司名称");
			}
			KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();
			String result = api.getOrderTracesByJson(trackName, trackNum);
			System.out.println(result);
			Gson gson = new Gson();
			Map<String, Object> map = new HashMap<String, Object>();
			map = gson.fromJson(result, map.getClass());
			map.put("expressNameView", orderVo.getExpressNameView());
			String mapStr = JSON.toJSONString(map);
			JSONObject jsStr = JSONObject.parseObject(mapStr);
			return ResponseUtil.successResultString(jsStr);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}

}
