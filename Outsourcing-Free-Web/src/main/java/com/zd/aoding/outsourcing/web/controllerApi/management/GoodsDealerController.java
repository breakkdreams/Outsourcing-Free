package com.zd.aoding.outsourcing.web.controllerApi.management;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.zd.aoding.base.DO.base.RecordBase;
import com.zd.aoding.common.StringDate.StringUtil;
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.AccountBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.GoodsBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.GoodsSpecBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.RoleBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsModelDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsOptionDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsSpecItemDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.RecordsDO;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsModelFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsOptionFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsSpecFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsSpecItemFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.RecordFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SessionFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.StatisticFacade;
import com.zd.aoding.outsourcing.weChat.api.utils.file.FileUtil;

/**
 * @ClassName: RegisterAccountController
 * @Description:注册业务入口
 * @author: zj
 * @date: 2017年3月13日 下午4:12:08
 */
@Api(value = "", description = "产品管理")
@Controller
@RequestMapping("ad/manager/dealer")
public class GoodsDealerController {
    @Autowired
    private GoodsFacade goodsFacade;
    @Autowired
    private GoodsOptionFacade goodsOptionFacade;
    @Autowired
    private GoodsSpecFacade goodsSpecFacade;
    @Autowired
    private GoodsSpecItemFacade goodsSpecItemFacade;
    @Autowired
    private GoodsModelFacade goodsModelFacade;
    @Autowired
    private StatisticFacade statisticFacade;
	@Autowired 
	private RecordFacade recordFacade;
	@Autowired
	private SessionFacade sessionFacade;

    @ResponseBody
    @RequestMapping(value = "goods/add", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
    @ApiOperation(value = "产品添加", httpMethod = "POST", notes = "", response = ResponseUtil.class)
    public String goodsAdd(
            @ApiParam(required = false, name = "firstCatagory", value = "第一级分类") @RequestParam(value = "firstCatagory", required = false) String firstCatagory,
            @ApiParam(required = false, name = "secondCatagory", value = "第二级分类") @RequestParam(value = "secondCatagory", required = false) String secondCatagory,
            @ApiParam(required = false, name = "thirdCatagory", value = "第三级分类") @RequestParam(value = "thirdCatagory", required = false) String thirdCatagory,
            @ApiParam(required = false, name = "title", value = "产品名称") @RequestParam(value = "title", required = false) String title,
            @ApiParam(required = false, name = "coverImgUrl", value = "主图片") @RequestParam(value = "coverImgUrl", required = false) String coverImgUrl,
            @ApiParam(required = false, name = "imgsUrl", value = "轮播图片") @RequestParam(value = "imgsUrl", required = false) String imgsUrl,
            @ApiParam(required = false, name = "description", value = "概要描述") @RequestParam(value = "description", required = false) String description,
            @ApiParam(required = false, name = "purchasePrice", value = "产品进货价") @RequestParam(value = "purchasePrice", required = false) String purchasePrice,
            @ApiParam(required = false, name = "originalPrice", value = "原价") @RequestParam(value = "originalPrice", required = false) String originalPrice,
            @ApiParam(required = false, name = "marketPrice", value = "售卖价") @RequestParam(value = "marketPrice", required = false) String marketPrice,
//            @ApiParam(required = false, name = "price", value = "现价根据goodsType变化") @RequestParam(value = "price", required = true) String price,
            @ApiParam(required = false, name = "scorePrice", value = "现价根据goodsType变化") @RequestParam(value = "scorePrice", required = false) String scorePrice,
            @ApiParam(required = false, name = "cashPrice", value = "现价根据goodsType变化") @RequestParam(value = "cashPrice", required = false) String cashPrice,
            @ApiParam(required = false, name = "goodsType", value = "产品类型") @RequestParam(value = "goodsType", required = false) String goodsType,
//            @ApiParam(required = true, name = "showStock", value = "是否显示库存") @RequestParam(value = "showStock", required = true) String showStock,
            @ApiParam(required = false, name = "content", value = "描述") @RequestParam(value = "content", required = false) String content,
            @ApiParam(required = false, name = "supplierId", value = "供货商id") @RequestParam(value = "supplierId", required = false) String supplierId,
            @ApiParam(required = false, name = "totalStock", value = "总库存数") @RequestParam(value = "totalStock", required = false) String totalStock,
            @ApiParam(required = false, name = "modelId", value = "模块id") @RequestParam(value = "modelId", required = false) String modelId,
            @ApiParam(required = false, name = "barCode", value = "条形码") @RequestParam(value = "barCode", required = false) String barCode,
            @ApiParam(required = false, name = "integralDeductible", value = "积分抵扣价") @RequestParam(value = "integralDeductible", required = false) String integralDeductible,
            @ApiParam(required = false, name = "typeId", value = "类型id") @RequestParam(value = "typeId", required = false) String typeId,
            HttpServletRequest request) {
        try {
				@SuppressWarnings("unchecked")
				List<Map<String, Object>> sess = (List<Map<String, Object>>) request
                        .getSession(true).getAttribute("menuSession");
                RoleBO rv = new RoleBO(sess, "产品设置添加");
                if ("1".equals(rv.getRoleFalg())) {
                    if(StringUtil.isNULL(purchasePrice)){
                    	return ResponseUtil.showMSGResultString("进货价异常");
                    }
                    if(StringUtil.isNULL(originalPrice)){
                    	return ResponseUtil.showMSGResultString("原价异常");
                    }
                    if(StringUtil.isNULL(marketPrice)){
                    	return ResponseUtil.showMSGResultString("价格异常");
                    }
                    if(Integer.parseInt(integralDeductible)>Double.parseDouble(marketPrice)){
                    	return ResponseUtil.showMSGResultString("积分价不能大于售价");
                    }
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("barCode", barCode);
                    map.put("deleted", 0);
                    int list = goodsFacade.countGoods(map);
                    if (list > 0) {
                        return ResponseUtil.showMSGResultString("条形码重复");
                    }
                    GoodsDO goodsDo = new GoodsDO(0,
                            Integer.parseInt(firstCatagory),
                            Integer.parseInt(secondCatagory),
                            Integer.parseInt(thirdCatagory), title,
                            coverImgUrl, imgsUrl, description, content,
                            Integer.parseInt(totalStock),
                            Double.parseDouble(purchasePrice),
                            1, Double.parseDouble(scorePrice),0.0,
                            Integer.parseInt(totalStock),0,Integer.parseInt(typeId));
                    if(StringUtil.isNumber(modelId)){
                    	goodsDo.setModelId(modelId);
                    }
                    goodsDo.setBarCode(barCode);
                    goodsDo.setOriginalPrice(Double.parseDouble(originalPrice));
                    goodsDo.setIntegralDeductible(Integer.parseInt(integralDeductible));
                    if(Double.parseDouble(marketPrice)>0){
                    	 double integralPercent = Integer.parseInt(integralDeductible)/Double.parseDouble(marketPrice);
                         goodsDo.setIntegralPercent(integralPercent);
                    }
                    goodsDo.setMarketPrice(Double.parseDouble(marketPrice));
                    goodsDo.insertInit();
                    int i = goodsFacade.insertGoodsPo(goodsDo);
                    if (i == 1) {
                    	//操作日志
    					AccountBO account = sessionFacade.checkLoginAccountSession(request);
    					RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "添加成功", RecordsDO.RECORDTYPE_MANAGER_ADD,
    						RecordBase.DEALTYPE_MANAGER, account.getAccountId(), goodsDo.getId(), "添加产品售卖价:"+marketPrice+",库存:"+totalStock, "");
    					recordFacade.insertRecordDO(recordDO);
    					
                        return ResponseUtil.successResultString("添加成功!");
                    }
                    return ResponseUtil.showMSGResultString("添加失败");
                } else {
                    return ResponseUtil.showMSGResultString("无权限");
                }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.systemErrorResultString();
        }
    }
//
//    @ResponseBody
//    @RequestMapping(value = "showSupplier/list", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
//    @ApiOperation(value = "产品下架上架放入特殊展位", httpMethod = "POST", notes = "", response = ResponseUtil.class)
//    public String goodsDel(HttpServletRequest request) {
//        try {
//            DealerPo dealerPo = (DealerPo) sessionFacade.checkLoginSession(
//                    request, SessionConstant.LOGIN_TYPE_DEALER);
//            if (dealerPo != null) {
//                List<SupplierPo> list = supplierFacade.getAllSupplierList();
//                return ResponseUtil.successResultString(list);
//            }
//            return ResponseUtil.notLoggedResultString();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseUtil.systemErrorResultString();
//        }
//    }
//
    @ResponseBody
    @RequestMapping(value = "goodsModel/list", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
    @ApiOperation(value = "产品下架上架放入特殊展位", httpMethod = "POST", notes = "", response = ResponseUtil.class)
    public String goodsModelList(HttpServletRequest request) {
        try {
        	Map<String, Object> param = new HashMap<String,Object>();
        	param.put("deleted", 0);
            List<GoodsModelDO> list = goodsModelFacade.getGoodsModelPoList(param);
            return ResponseUtil.successResultString(list);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.systemErrorResultString();
        }
    }

    @ResponseBody
    @RequestMapping(value = "goods/edit", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
    @ApiOperation(value = "产品修改", httpMethod = "POST", notes = "", response = ResponseUtil.class)
    public String goodsEdit(
            @ApiParam(required = false, name = "goodsId", value = "产品id") @RequestParam(value = "goodsId", required = false) String goodsId,
            @ApiParam(required = false, name = "firstCatagory", value = "第一级分类") @RequestParam(value = "firstCatagory", required = false) String firstCatagory,
            @ApiParam(required = false, name = "secondCatagory", value = "第二级分类") @RequestParam(value = "secondCatagory", required = false) String secondCatagory,
            @ApiParam(required = false, name = "thirdCatagory", value = "第三级分类") @RequestParam(value = "thirdCatagory", required = false) String thirdCatagory,
            @ApiParam(required = false, name = "title", value = "产品名称") @RequestParam(value = "title", required = false) String title,
            @ApiParam(required = false, name = "coverImgUrl", value = "主图片") @RequestParam(value = "coverImgUrl", required = false) String coverImgUrl,
            @ApiParam(required = false, name = "imgsUrl", value = "轮播图片") @RequestParam(value = "imgsUrl", required = false) String imgsUrl,
            @ApiParam(required = false, name = "description", value = "概要描述") @RequestParam(value = "description", required = false) String description,
            @ApiParam(required = false, name = "content", value = "描述") @RequestParam(value = "content", required = false) String content,
            @ApiParam(required = false, name = "totalStock", value = "总库存数") @RequestParam(value = "totalStock", required = false) String totalStock,
//            @ApiParam(required = true, name = "showStock", value = "显示库存") @RequestParam(value = "showStock", required = true) String showStock,
            //@ApiParam(required = true, name = "goodsType", value = "产品类型") @RequestParam(value = "goodsType", required = true) String goodsType,
            @ApiParam(required = false, name = "scorePrice", value = "现价根据goodsType变化") @RequestParam(value = "scorePrice", required = false) String scorePrice,
            @ApiParam(required = false, name = "cashPrice", value = "现价根据goodsType变化") @RequestParam(value = "cashPrice", required = false) String cashPrice,
            @ApiParam(required = false, name = "purchasePrice", value = "产品进货价") @RequestParam(value = "purchasePrice", required = false) String purchasePrice,
            @ApiParam(required = false, name = "originalPrice", value = "原价") @RequestParam(value = "originalPrice", required = false) String originalPrice,
            @ApiParam(required = false, name = "modelId", value = "模块id") @RequestParam(value = "modelId", required = false) String modelId,
            @ApiParam(required = false, name = "integralDeductible", value = "积分抵扣价") @RequestParam(value = "integralDeductible", required = false) String integralDeductible,
            @ApiParam(required = false, name = "marketPrice", value = "售卖价") @RequestParam(value = "marketPrice", required = false) String marketPrice,
            @ApiParam(required = false, name = "typeId", value = "类型id") @RequestParam(value = "typeId", required = false) String typeId,
            HttpServletRequest request) {
        try {
                @SuppressWarnings("unchecked")
				List<Map<String, Object>> sess = (List<Map<String, Object>>) request
                        .getSession(true).getAttribute("menuSession");
                RoleBO rv = new RoleBO(sess, "产品设置修改");
                if ("1".equals(rv.getRoleFalg())) {
                    if (!StringUtil.isNumber(goodsId)) {
                        return ResponseUtil.paramErrorResultString("参数错误");
                    }
                    if(Integer.parseInt(integralDeductible)>Double.parseDouble(marketPrice)){
                    	return ResponseUtil.showMSGResultString("积分价不能大于售价");
                    }
                    List<GoodsOptionDO> optionList = goodsOptionFacade.getOptionPoByGoodsId(Integer.parseInt(goodsId));
                    if(optionList!=null && optionList.size()>0){
                    	for (GoodsOptionDO goodsOptionPo : optionList) {
                    		if(Integer.parseInt(integralDeductible)>goodsOptionPo.getMarketPrice()){
                            	return ResponseUtil.showMSGResultString("积分价不能大于配置价");
                            }
						}
                    }
                    GoodsDO goodsPo = goodsFacade.getGoodsPoByPK(Integer
                            .parseInt(goodsId));
                    if (goodsPo == null) {
                        return ResponseUtil.showMSGResultString("找不到该产品");
                    }
                    GoodsDO goodsDo = new GoodsDO();
                    goodsDo.setId(Integer.parseInt(goodsId));
                    if (!StringUtil.isNULL(firstCatagory)
                            && StringUtil.isNumber(firstCatagory)) {
                        goodsDo.setFirstCatagory(Integer
                                .parseInt(firstCatagory));
                    }
//                    if(StringUtil.isNumber(showStock)){
//                    	goodsDo.setShowStock(Integer.parseInt(showStock));
//                    }
                    if(!StringUtil.isNULL(purchasePrice)){
                    	goodsDo.setPurchasePrice(Double.parseDouble(purchasePrice));
                    }
                    if(!StringUtil.isNULL(marketPrice)){
                    	goodsDo.setMarketPrice(Double.parseDouble(marketPrice));
                    }
                    if(!StringUtil.isNULL(originalPrice)){
                    	goodsDo.setOriginalPrice(Double.parseDouble(originalPrice));
                    }
//                    if(StringUtil.isNumber(goodsType)){
//                    	goodsDo.setType(Integer.parseInt(goodsType));
//                    }
                    if(StringUtil.isNumber(modelId)){
                    	goodsDo.setModelId(modelId);
                    }
                    if(StringUtil.isNumber(typeId)){
                    	goodsDo.setTypeId(Integer.parseInt(typeId));
                    }
                    if (!StringUtil.isNULL(secondCatagory)
                            && StringUtil.isNumber(secondCatagory)) {
                        goodsDo.setSecondCatagory(Integer
                                .parseInt(secondCatagory));
                    }
                    if (!StringUtil.isNULL(thirdCatagory)
                            && StringUtil.isNumber(thirdCatagory)) {
                        goodsDo.setThirdCatagory(Integer
                                .parseInt(thirdCatagory));
                    }
                    if (!StringUtil.isNULL(title)) {
                        goodsDo.setTitle(title);
                    }
                    if (!StringUtil.isNULL(coverImgUrl)) {
                        goodsDo.setCoverImgUrl(coverImgUrl);
                    }
                    if (!StringUtil.isNULL(scorePrice)) {
                    	goodsDo.setScorePrice(Double.parseDouble(scorePrice));
                    }
                    if (!StringUtil.isNULL(cashPrice)) {
                    	goodsDo.setBonusPrice(Double.parseDouble(cashPrice));
                    }
                    if (!StringUtil.isNULL(imgsUrl)) {
                        goodsDo.setImgsUrl(imgsUrl);
                    }
                    if (!StringUtil.isNULL(description)) {
                        goodsDo.setDescription(description);
                    }
                    if (!StringUtil.isNULL(content)) {
                        goodsDo.setContent(content);
                    }
                    if (!StringUtil.isNULL(totalStock)
                            && StringUtil.isNumber(totalStock)) {
                        goodsDo.setTotalStock(Integer.parseInt(totalStock));
                        goodsDo.setShowStock(Integer.parseInt(totalStock));
                    }
                    if(!StringUtil.isNULL(integralDeductible)){
                    	goodsDo.setIntegralDeductible(Integer.parseInt(integralDeductible));
                    }
                	if(Double.parseDouble(marketPrice)>0){
                		double integralPercent = Integer.parseInt(integralDeductible)/Double.parseDouble(marketPrice);
                        goodsDo.setIntegralPercent(integralPercent);
                	}
                   
                    int i = goodsFacade.updateGoodsPo(goodsDo);
                    if (i == 1) {
                    	//操作日志
    					AccountBO account = sessionFacade.checkLoginAccountSession(request);
    					RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "修改成功", RecordsDO.RECORDTYPE_MANAGER_UPDATE,
    						RecordBase.DEALTYPE_MANAGER, account.getAccountId(), Integer.parseInt(goodsId), "修改产品售卖价:"+marketPrice+",库存:"+totalStock, "");
    					recordFacade.insertRecordDO(recordDO);
    					
                        return ResponseUtil.successResultString("修改成功!");
                    }
                    return ResponseUtil.showMSGResultString("修改失败");
                } else {
                    return ResponseUtil.showMSGResultString("无权限");
                }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.systemErrorResultString();
        }
    }
    
    @ResponseBody
    @RequestMapping(value = "goodsPosition/edit", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
    @ApiOperation(value = "产品首页修改", httpMethod = "POST", notes = "", response = ResponseUtil.class)
    public String goodsPositionEdit(
            @ApiParam(required = true, name = "goodsId", value = "产品id") @RequestParam(value = "goodsId", required = true) String goodsId,
            @ApiParam(required = false, name = "position", value = "是否首页") @RequestParam(value = "position", required = false) String position,
            HttpServletRequest request) {
        try {
            if (!StringUtil.isNumber(goodsId)) {
                return ResponseUtil.paramErrorResultString("参数错误");
            }
            GoodsDO goodsPo = goodsFacade.getGoodsPoByPK(Integer.parseInt(goodsId));
            if (goodsPo == null) {
                return ResponseUtil.showMSGResultString("找不到该产品");
            }
            if(goodsPo.getStatusAdmin()!=GoodsDO.STATUS_SHALL){
            	return ResponseUtil.showMSGResultString("产品未上架");
            }
            GoodsDO goodsDo = new GoodsDO();
            goodsDo.setId(Integer.parseInt(goodsId));
            if (!StringUtil.isNULL(position)) {
                goodsDo.setPosition(Integer.parseInt(position));
            }
            int i = goodsFacade.updateGoodsPo(goodsDo);
            if (i == 1) {
            	//操作日志
				AccountBO account = sessionFacade.checkLoginAccountSession(request);
				RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "修改成功", RecordsDO.RECORDTYPE_MANAGER_UPDATE,
					RecordBase.DEALTYPE_MANAGER, account.getAccountId(), Integer.parseInt(goodsId), "产品上下架首页", "");
				recordFacade.insertRecordDO(recordDO);
				
                return ResponseUtil.successResultString("修改成功!");
            }
            return ResponseUtil.showMSGResultString("修改失败");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.systemErrorResultString();
        }
    }
    
    @ResponseBody
    @RequestMapping(value = "goodsChosen/edit", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
    @ApiOperation(value = "产品返利修改", httpMethod = "POST", notes = "", response = ResponseUtil.class)
    public String goodsChosenEdit(
    		@ApiParam(required = true, name = "goodsId", value = "产品id") @RequestParam(value = "goodsId", required = true) String goodsId,
    		@ApiParam(required = false, name = "rebate", value = "是否返利") @RequestParam(value = "rebate", required = false) String rebate,
    		HttpServletRequest request) {
    	try {
    		if (!StringUtil.isNumber(goodsId)) {
    			return ResponseUtil.paramErrorResultString("参数错误");
    		}
    		GoodsDO goodsPo = goodsFacade.getGoodsPoByPK(Integer.parseInt(goodsId));
    		if (goodsPo == null) {
    			return ResponseUtil.showMSGResultString("找不到该产品");
    		}
    		GoodsDO goodsDo = new GoodsDO();
    		goodsDo.setId(Integer.parseInt(goodsId));
    		if (!StringUtil.isNULL(rebate)) {
    			goodsDo.setRebate(Integer.parseInt(rebate));
    		}
    		int i = goodsFacade.updateGoodsPo(goodsDo);
    		if (i == 1) {
    			return ResponseUtil.successResultString("修改成功!");
    		}
    		return ResponseUtil.showMSGResultString("修改失败");
    	} catch (Exception e) {
    		e.printStackTrace();
    		return ResponseUtil.systemErrorResultString();
    	}
    }

    @ResponseBody
    @RequestMapping(value = "goods/delete", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
    @ApiOperation(value = "删除产品", httpMethod = "POST", notes = "根据产品id删除产品", response = ResponseUtil.class)
    public String deletedCatagory(
            @ApiParam(required = true, name = "goodsId", value = "产品Id") @RequestParam(value = "goodsId", required = true) String goodsId,
            HttpServletRequest request) {
        try {
                @SuppressWarnings("unchecked")
				List<Map<String, Object>> sess = (List<Map<String, Object>>) request
                        .getSession(true).getAttribute("menuSession");
                RoleBO rv = new RoleBO(sess, "产品设置删除");
                if ("1".equals(rv.getRoleFalg())) {
                    if (!StringUtil.isNULL(goodsId) && StringUtil.isNumber(goodsId)) {
                        GoodsDO goodsPo = goodsFacade.getGoodsPoByPK(Integer.parseInt(goodsId));
                        if (goodsPo == null) {
                            return ResponseUtil.paramErrorResultString("参数错误");
                        }
                        GoodsDO updateGoods = new GoodsDO();
                        updateGoods.setDeleted(1);
                        updateGoods.setId(Integer.parseInt(goodsId));
                        int i = goodsFacade.updateGoodsPo(updateGoods);
                        if (i == 1) {
                        	//操作日志
            				AccountBO account = sessionFacade.checkLoginAccountSession(request);
            				RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "删除成功", RecordsDO.RECORDTYPE_MANAGER_DELETED,
            					RecordBase.DEALTYPE_MANAGER, account.getAccountId(), Integer.parseInt(goodsId), "删除产品", "");
            				recordFacade.insertRecordDO(recordDO);
            				
                            return ResponseUtil.successResultString("删除成功");
                        }
                        return ResponseUtil.showMSGResultString("删除失败");
                    }
                    return ResponseUtil.paramErrorResultString("缺少需要删除的goodsId=" + goodsId);
                }
                return ResponseUtil.showMSGResultString("无权限");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.systemErrorResultString();
        }
    }

    @ResponseBody
    @RequestMapping(value = "goods/detail", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
    @ApiOperation(value = "产品详情", httpMethod = "POST", notes = "", response = ResponseUtil.class)
    public String goodsDetail(
            @ApiParam(required = true, name = "goodsId", value = "产品id") @RequestParam(value = "goodsId", required = true) String goodsId,
            HttpServletRequest request) {
        try {
                if (!StringUtil.isNumber(goodsId)) {
                    return ResponseUtil.paramErrorResultString("goodsId=" + goodsId);
                }
                GoodsBO goodsVo = goodsFacade.getGoodsVoByPK(goodsId);
                if (goodsVo != null) {
                	//操作日志
    				AccountBO account = sessionFacade.checkLoginAccountSession(request);
    				RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "查看产品详情成功", RecordsDO.RECORDTYPE_MANAGER_VIEW,
    					RecordBase.DEALTYPE_MANAGER, account.getAccountId(), Integer.parseInt(goodsId), "查看产品详情", "");
    				recordFacade.insertRecordDO(recordDO);
    				
                    return ResponseUtil.successResultString(goodsVo);
                }
                return ResponseUtil.showMSGResultString("查询失败");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.systemErrorResultString();
        }
    }

    @ResponseBody
    @RequestMapping(value = "goods/priceEdit", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
    @ApiOperation(value = "产品详情", httpMethod = "POST", notes = "", response = ResponseUtil.class)
    public String goodsDetail(
            @ApiParam(required = true, name = "goodsId", value = "产品id") @RequestParam(value = "goodsId", required = true) String goodsId,
            @ApiParam(required = true, name = "goodsType", value = "产品类型") @RequestParam(value = "goodsType", required = true) String goodsType,
            @ApiParam(required = true, name = "price", value = "修改的价格值") @RequestParam(value = "price", required = true) String price,
            HttpServletRequest request) {
        try {
                @SuppressWarnings("unchecked")
				List<Map<String, Object>> sess = (List<Map<String, Object>>) request
                        .getSession(true).getAttribute("menuSession");
                RoleBO rv = new RoleBO(sess, "产品设置查询");
                if ("1".equals(rv.getRoleFalg())) {
                    if (!StringUtil.isNumber(goodsId)
                            || !StringUtil.isNumber(price)
                            || !StringUtil.isNumber(goodsType)) {
                        return ResponseUtil.paramErrorResultString("参数错误");
                    }
                    int id = Integer.parseInt(goodsId);
                    double goodsPrice = Double.parseDouble(price);
                    int type = Integer.parseInt(goodsType);
                    GoodsDO gPo = new GoodsDO();
                    gPo.setId(id);
                    if (type == GoodsDO.type_score) {
                        gPo.setScorePrice(goodsPrice);
                        gPo.setBonusPrice(0.00);
                    } else {
                        gPo.setBonusPrice(goodsPrice);
                        gPo.setScorePrice(0.00);
                    }
                    int i = goodsFacade.updateGoodsPo(gPo);
                    if (i == 1) {
                    	//操作日志
        				AccountBO account = sessionFacade.checkLoginAccountSession(request);
        				RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "修改产品价格成功", RecordsDO.RECORDTYPE_MANAGER_UPDATE,
        					RecordBase.DEALTYPE_MANAGER, account.getAccountId(), Integer.parseInt(goodsId), "修改产品价格", "");
        				recordFacade.insertRecordDO(recordDO);
        				
                        return ResponseUtil.successResultString("修改成功");
                    }
                    return ResponseUtil.showMSGResultString("修改失败");
                }
                return ResponseUtil.showMSGResultString("无权限");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.systemErrorResultString();
        }
    }

    @ResponseBody
    @RequestMapping(value = "goods/DTPaging", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ApiOperation(value = "分页查询产品", httpMethod = "POST", notes = "分页查询红包", response = ResponseUtil.class)
    public Map<String, Object> dtPaging(
            @ApiParam(required = false, name = "status", value = "产品状态0默认 1上架  -1下架 ") @RequestParam(value = "status", required = false) String status,
            @ApiParam(required = false, name = "modelId", value = "模块id") @RequestParam(value = "modelId", required = false) String modelId,
            @ApiParam(required = false, name = "firstCatagory", value = "一级分类id") @RequestParam(value = "firstCatagory", required = false) String firstCatagory,
            @ApiParam(required = false, name = "type", value = "产品类型") @RequestParam(value = "type", required = false) String type,
            @ApiParam(required = false, name = "iDisplayStart", value = "分页查询开始条数（DataTable 内置参数）") @RequestParam(value = "iDisplayStart", required = false) String iDisplayStart,
            @ApiParam(required = false, name = "iDisplayLength", value = "分页查询条数（DataTable 内置参数）") @RequestParam(value = "iDisplayLength", required = false) String iDisplayLength,
            HttpServletRequest request) {
        try {
                String sSearch = request.getParameter("sSearch");
                // 初始化列表
                PageEntity pageEntity = new PageEntity();
                if (!StringUtil.isNULL(iDisplayStart)
                        && Integer.valueOf(iDisplayStart) > 0) {
                    pageEntity.setPage(Integer.valueOf(iDisplayStart)
                            / Integer.valueOf(iDisplayLength) + 1);
                }
                if (!StringUtil.isNULL(iDisplayLength)) {
                    pageEntity.setSize(Integer.valueOf(iDisplayLength));
                }
                /**
                 * 排序
                 */
                pageEntity.setOrderColumn("create_time");
                /**
                 * 查询条件参数
                 */
                Map<String, Object> param = new HashMap<String, Object>();
                /*
                 * if(!StringUtil.isNULL(sSearch)){ param.put("quantity",
                 * sSearch); }
                 */
                if (!StringUtil.isNULL(sSearch)) {
                    param.put("titleLike", sSearch);
                }
                if(StringUtil.isNumber(type)){
                    param.put("type", Integer.parseInt(type));
                }
                if(StringUtil.isNumber(status) || "-1".equals(status)){
                    param.put("status", Integer.parseInt(status));
                }
                if(StringUtil.isNumber(modelId)){
                    param.put("modelId", Integer.parseInt(modelId));
                }
                if(StringUtil.isNumber(firstCatagory)){
                    param.put("firstCatagory", Integer.parseInt(firstCatagory));
                }
                param.put("deleted", 0);
                pageEntity.setParams(param);
                /**
                 * 查询
                 */
                PageResult<GoodsBO> pageResult = goodsFacade.getPageGoodsVo(pageEntity);
                Map<String, Object> resultMap = new HashMap<String, Object>();
                String jsonString = JSON.toJSONString(
                        pageResult.getResultList(), true);
                JSONArray jsonArray = JSON.parseArray(jsonString);
                resultMap.put("iTotalRecords", pageResult.getTotalSize());
                resultMap.put("iTotalDisplayRecords", pageResult.getTotalSize());
                resultMap.put("aaData", jsonArray);
                return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.systemErrorResultMap();
        }
    }

    @ResponseBody
    @RequestMapping(value = "goods/change/status", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
    @ApiOperation(value = "产品下架上架放入特殊展位", httpMethod = "POST", notes = "", response = ResponseUtil.class)
    public String goodsDel(
            @ApiParam(required = true, name = "goodsId", value = "展厅id") @RequestParam(value = "goodsId", required = true) String goodsId,
            @ApiParam(required = true, name = "status", value = "status==0下架，1上架，2特殊展位（每个展厅只限4个）") @RequestParam(value = "status", required = true) String status,
            HttpServletRequest request) {
        try {
                @SuppressWarnings("unchecked")
				List<Map<String, Object>> sess = (List<Map<String, Object>>) request
                        .getSession(true).getAttribute("menuSession");
                RoleBO rv = new RoleBO(sess, "产品设置修改");
                if ("1".equals(rv.getRoleFalg())) {
                    if (!StringUtil.isNumber(goodsId)
                            && !StringUtil.isNumber(status)) {
                        return ResponseUtil.paramErrorResultString("参数错误");
                    }
                    int s = Integer.parseInt(status);
                    int g = Integer.parseInt(goodsId);
                    if (s == GoodsDO.STATUS_SHALL) {
                        List<GoodsOptionDO> option = goodsOptionFacade.getOptionPoByGoodsId(g);
                        if (option == null || option.size() == 0) {
                            List<GoodsSpecBO> voList = goodsSpecFacade.getGoodsSpecVoByGoodId(goodsId);
                            if (voList != null && voList.size() > 0) {
                                return ResponseUtil.showMSGResultString("该产品有规格，但您并没有为其添加组合。请完善产品规格");
                            }
                            return ResponseUtil.showMSGResultString("请为该产品添加配置组合");
                        } else {
                            String specIds = "";
                            for (GoodsOptionDO optionPo : option) {
                                specIds += optionPo.getSpecIds();
                                Double optionMarketPrice = optionPo.getMarketPrice();
                                Integer stock = optionPo.getStock();
                                if(optionMarketPrice == 0.00){
                                    return ResponseUtil.showMSGResultString("请为所有规格设置价格和库存");
                                }
                            }
                            List<GoodsSpecBO> voList = goodsSpecFacade.getGoodsSpecVoByGoodId(goodsId);
                            int size = voList.size();
                            for (int i = 0; i < size; i++) {
                                Map<String, Object> map = new HashMap<String, Object>();
                                map.put("specId", voList.get(i).getGoodsSpecId());
                                map.put("deleted", 0);
                                List<GoodsSpecItemDO> itemList = goodsSpecItemFacade.getGoodsSpecItemPoByParam(map);
                                for (GoodsSpecItemDO itemPo : itemList) {
                                    if (specIds.indexOf(itemPo.getId().toString()) == -1) {
                                        return ResponseUtil.showMSGResultString("请为所有的规格添加组合,或删除多余的规格");
                                    }
                                }
                            }

                        }
                    }
                    int i = goodsFacade.changeGoodsDoStatus(g, s);
                    if (i == 1) {
                    	//操作日志
        				AccountBO account = sessionFacade.checkLoginAccountSession(request);
        				RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "修改产品上下架成功", RecordsDO.RECORDTYPE_MANAGER_UPDATE,
        					RecordBase.DEALTYPE_MANAGER, account.getAccountId(), Integer.parseInt(goodsId), "修改产品上下架", "");
        				recordFacade.insertRecordDO(recordDO);
        				
                        return ResponseUtil.successResultString("操作成功!");
                    }
                    return ResponseUtil.showMSGResultString("操作失败");
                }
                return ResponseUtil.showMSGResultString("无权限");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.systemErrorResultString();
        }
    }

    @ResponseBody
    @RequestMapping(value = "goods/allList", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded;charset=utf-8")
    @ApiOperation(value = "查询全部产品", httpMethod = "POST", notes = "查询全部产品", response = ResponseUtil.class)
    public String getAllList(
    		@ApiParam(required = false, name = "categoryLever", value = "lever") @RequestParam(value = "categoryLever", required = false) String categoryLever,
    		@ApiParam(required = false, name = "categoryId", value = "id") @RequestParam(value = "categoryId", required = false) String categoryId,
    		HttpServletRequest request) {
        try {
                /**
                 * 查询条件参数
                 */
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("deleted", 0);
                if(!StringUtil.isNULL(categoryLever)){
                	if(!StringUtil.isNULL(categoryId)){
                		if("1".equals(categoryLever)){
                			param.put("firstCatagory", categoryId);
                    	}else if("2".equals(categoryLever)){
                    		param.put("secondCatagory", categoryId);
                    	}else if("3".equals(categoryLever)){
                    		param.put("thirdCatagory", categoryId);
                    	}
                    }
                }
                
                /**
                 * 查询
                 */
                List<GoodsDO> goodsList = goodsFacade.getGoodsList(param);
                Map<String, Object> resultMap = new HashMap<String, Object>();
                String jsonString = JSON.toJSONString(goodsList, true);
                JSONArray jsonArray = JSON.parseArray(jsonString);
                resultMap.put("aaData", jsonArray);
                return ResponseUtil.successResultString(goodsList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.systemErrorResultString();
        }
    }

    @ResponseBody
    @RequestMapping(value = "goods/deleteImg", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
    @ApiOperation(value = "删除产品图片", httpMethod = "POST", notes = "删除产品图片", response = ResponseUtil.class)
    public String deleteImg(
            @ApiParam(required = true, name = "imgUrl", value = "图片路径") @RequestParam(value = "imgUrl", required = true) String imgUrl,
            @ApiParam(required = true, name = "goodsId", value = "产品id") @RequestParam(value = "goodsId", required = true) String goodsId,
            HttpServletRequest request) {
        try {
                @SuppressWarnings("unchecked")
				List<Map<String, Object>> sess = (List<Map<String, Object>>) request
                        .getSession(true).getAttribute("menuSession");
                RoleBO rv = new RoleBO(sess, "产品设置修改");
                if ("1".equals(rv.getRoleFalg())) {
                    if (!StringUtil.isNumber(goodsId)) {
                        return ResponseUtil.paramErrorResultString("goodsId错误");
                    }
                    GoodsDO goodsPo = goodsFacade.getGoodsPoByPK(Integer
                            .parseInt(goodsId));
                    String imgGroup = goodsPo.getImgsUrl();
                    if (imgGroup != null) {
                        GoodsDO goodsDo = new GoodsDO();
                        goodsDo.setId(goodsPo.getId());
                        String[] imgs = imgGroup.split(",");
                        for (int i = 0; i < imgs.length; i++) {
                            int j = imgUrl.indexOf(imgs[i]);
                            if (j != -1) {
                                String ig = imgs[i] + ",";
                                imgGroup = imgGroup.replace(ig, "");
                                int isDelete = imgGroup.indexOf(imgs[i]);
                                if (isDelete != -1) {
                                    imgGroup = imgGroup.replace("," + imgs[i],
                                            "");
                                    int isDeleteTo = imgGroup.indexOf(imgs[i]);
                                    if (isDeleteTo != -1) {
                                        imgGroup = imgGroup
                                                .replace(imgs[i], "");
                                    }
                                }
                                goodsDo.setImgsUrl(imgGroup);
                                int k = goodsFacade.updateGoodsPo(goodsDo);
                                if (k == 1) {
                                    // showroom.setImages(imgGroup);
                                    // sessionService.saveLoginSession(sessionService.checkLoginAccountSession(request),
                                    // SessionConstant.LOGIN_TYPE_DEALER,
                                    // mapper,
                                    // request);
                                	//操作日志
                    				AccountBO account = sessionFacade.checkLoginAccountSession(request);
                    				RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "删除产品图片成功", RecordsDO.RECORDTYPE_MANAGER_UPDATE,
                    					RecordBase.DEALTYPE_MANAGER, account.getAccountId(), Integer.parseInt(goodsId), "删除产品图片", "");
                    				recordFacade.insertRecordDO(recordDO);
                    				
                    				GoodsBO goodsVo = goodsFacade.getGoodsVoByPK(goodsId);
                    				
                                    return ResponseUtil.successResultString(goodsVo);
                                }
                                return ResponseUtil.showMSGResultString("删除失败");
                            }
                        }
                    }
                    return ResponseUtil.showMSGResultString("产品图片中没有这个图片");
                }
                return ResponseUtil.showMSGResultString("无权限");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.systemErrorResultString();
        }
    }

    @ResponseBody
    @RequestMapping(value = "goods/updateImg", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
    @ApiOperation(value = "修改图片", httpMethod = "POST", notes = "修改图片", response = ResponseUtil.class)
    public String updateImg(
            @ApiParam(required = true, name = "imgUrl", value = "旧的图片路径") @RequestParam(value = "imgUrl", required = true) String imgUrl,
            @ApiParam(required = true, name = "newImgUrl", value = "新的图片路径") @RequestParam(value = "newImgUrl", required = true) String newImgUrl,
            @ApiParam(required = true, name = "goodsId", value = "产品id") @RequestParam(value = "goodsId", required = true) String goodsId,
            HttpServletRequest request) {
        try {
                @SuppressWarnings("unchecked")
				List<Map<String, Object>> sess = (List<Map<String, Object>>) request
                        .getSession(true).getAttribute("menuSession");
                RoleBO rv = new RoleBO(sess, "产品设置修改");
                if ("1".equals(rv.getRoleFalg())) {
                    if (!StringUtil.isNumber(goodsId)) {
                        return ResponseUtil.paramErrorResultString("goodsId错误");
                    }
                    GoodsDO goodsPo = goodsFacade.getGoodsPoByPK(Integer
                            .parseInt(goodsId));
                    String imgGroup = goodsPo.getImgsUrl();
                    int l = imgGroup.indexOf(newImgUrl);
                    if (l != -1) {
                        return ResponseUtil.showMSGResultString("您已存在相同图片");
                    }
                    if (imgGroup != null) {
                    	GoodsDO goodsDo = new GoodsDO();
                        goodsDo.setId(goodsPo.getId());
                        String[] imgs = imgGroup.split(",");
                        for (int i = 0; i < imgs.length; i++) {
                            int j = imgUrl.indexOf(imgs[i]);
                            if (j != -1) {
                                imgGroup = imgGroup.replace(imgs[i], newImgUrl);
                                goodsDo.setImgsUrl(imgGroup);
                                int k = goodsFacade.updateGoodsPo(goodsDo);
                                if (k == 1) {
                                	//操作日志
                    				AccountBO account = sessionFacade.checkLoginAccountSession(request);
                    				RecordsDO recordDO = new RecordsDO(RecordBase.LEVER_NORMAL, "", "修改产品图片成功", RecordsDO.RECORDTYPE_MANAGER_UPDATE,
                    					RecordBase.DEALTYPE_MANAGER, account.getAccountId(), Integer.parseInt(goodsId), "修改产品图片", "");
                    				recordFacade.insertRecordDO(recordDO);
                    				
                    				GoodsBO goodsVo = goodsFacade.getGoodsVoByPK(goodsId);
                    				
                                    return ResponseUtil.successResultString(goodsVo);
                                }
                                return ResponseUtil.showMSGResultString("修改失败");
                            }
                        }
                    }
                    return ResponseUtil.showMSGResultString("产品图片中没有这个图片");
                }
                return ResponseUtil.showMSGResultString("无权限");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.systemErrorResultString();
        }
    }
    
    @ResponseBody
	@RequestMapping(value = "goods/importExcel", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded;charset=utf-8")
	@ApiOperation(value = "导入excel设置库存", httpMethod = "POST", notes = "设置断货", response = ResponseUtil.class)
	public String excel(
			@ApiParam(required = true, name = "file", value = "file") @RequestParam(value = "file", required = true)
			MultipartFile file,
			HttpServletRequest request) {
		try {
			File uploadedFile = FileUtil.convertMultipartFileToFile(file);
			if (uploadedFile == null) {
				return ResponseUtil.showMSGResultString("文件出错");
			}
			List<Map<String, Object>> list = goodsFacade.saveExcel(uploadedFile);
			if(list == null || list.size() == 0){
				return ResponseUtil.successResultString("上传成功");
			}
			return ResponseUtil.showMSGResultString("部分上传失败");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
    
    @ResponseBody
    @RequestMapping(value = "goodsPurSum/DTPaging", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ApiOperation(value = "分页查询产品", httpMethod = "POST", notes = "分页查询红包", response = ResponseUtil.class)
    public Map<String, Object> purSumPaging(
            @ApiParam(required = false, name = "iDisplayStart", value = "分页查询开始条数（DataTable 内置参数）") @RequestParam(value = "iDisplayStart", required = false) String iDisplayStart,
            @ApiParam(required = false, name = "iDisplayLength", value = "分页查询条数（DataTable 内置参数）") @RequestParam(value = "iDisplayLength", required = false) String iDisplayLength,
            @ApiParam(required = false, name = "time", value = "time") @RequestParam(value = "time", required = false) String time,
            HttpServletRequest request) {
        try {
                // 初始化列表
                PageEntity pageEntity = new PageEntity();
                if (!StringUtil.isNULL(iDisplayStart) && Integer.valueOf(iDisplayStart) > 0) {
                    pageEntity.setPage(Integer.valueOf(iDisplayStart) / Integer.valueOf(iDisplayLength) + 1);
                }
                if (!StringUtil.isNULL(iDisplayLength)) {
                    pageEntity.setSize(Integer.valueOf(iDisplayLength));
                }
                /**
                 * 排序
                 */
               
                /**
                 * 查询条件参数
                 */
                Map<String, Object> param = new HashMap<String, Object>();
                if(!StringUtil.isNULL(time)){
    				param.put("times", time);
    			}
    			//判断 去掉积分付款及未付款订单
    			param.put("payType", 1);
                param.put("deleted", 0);
                pageEntity.setParams(param);
                /**
                 * 查询
                 */
                PageResult<GoodsBO> pageResult = goodsFacade.getPageGoodsVoByParam(pageEntity);
                Map<String, Object> resultMap = new HashMap<String, Object>();
                String jsonString = JSON.toJSONString(pageResult.getResultList(), true);
                JSONArray jsonArray = JSON.parseArray(jsonString);
                resultMap.put("iTotalRecords", pageResult.getTotalSize());
                resultMap.put("iTotalDisplayRecords", pageResult.getTotalSize());
                resultMap.put("aaData", jsonArray);
                return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.systemErrorResultMap();
        }
    }
}
