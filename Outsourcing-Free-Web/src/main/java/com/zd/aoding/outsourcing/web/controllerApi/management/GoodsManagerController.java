package com.zd.aoding.outsourcing.web.controllerApi.management;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.zd.aoding.common.StringDate.StringUtil;
import com.zd.aoding.common.log.LogUtil;
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.GoodsBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.RoleBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsDO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.GoodsOptionDO;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.GoodsOptionFacade;
import com.zd.aoding.outsourcing.weChat.api.utils.file.FileUtil;

@Api(value = "", description = "产品管理")
@Controller
@RequestMapping("ad/manager")
public class GoodsManagerController {
	@Autowired
	private HttpSession session;
	@Autowired
	private GoodsFacade goodsFacade;
	@Autowired
	private GoodsOptionFacade goodsOptionFacade;


	@ResponseBody
	@RequestMapping(value = "goods/DTPaging", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ApiOperation(value = "查看产品", httpMethod = "POST", notes = "查看产品", response = ResponseUtil.class)
	public Map<String, Object> getGoods(
			@ApiParam(required = false, name = "iDisplayStart", value = "分页查询开始条数（DataTable 内置参数）") @RequestParam(value = "iDisplayStart", required = false) String iDisplayStart,
			@ApiParam(required = false, name = "iDisplayLength", value = "分页查询条数（DataTable 内置参数）") @RequestParam(value = "iDisplayLength", required = false) String iDisplayLength,
			@ApiParam(required = false, name = "type", value = "1积分2奖金") @RequestParam(value = "type", required = false) String type,
			@ApiParam(required = false, name = "dealerId", value = "商家id") @RequestParam(value = "dealerId", required = false) String dealerId,
			@ApiParam(required = false, name = "categoryId", value = "一级分类id") @RequestParam(value = "categoryId", required = false) String categoryId,
			@ApiParam(required = false, name = "status", value = "1上架 -1下架 0待上架") @RequestParam(value = "status", required = false) String status,
			HttpServletRequest request) {
		try {
			List<Map<String, Object>> sess = (List<Map<String, Object>>) session.getAttribute("menuSession");//重点
			RoleBO rv = new RoleBO(sess, "库存设置查看");
			if(rv.getRoleFalg().equals("1")) {
				String sSearch = request.getParameter("sSearch");
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
				pageEntity.setOrderColumn("create_time");
				/**
				 * 查询条件参数
				 */
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("deleted", 0);
				/** 查看商户上架产品和已下架 */
				param.put("statusDealer", 1);
				if(!StringUtil.isNULL(sSearch)){
				    param.put("titleLike", sSearch);
				}
				param.put("type", type);
				if(!"-1".equals(dealerId) && !StringUtil.isNULL(dealerId)){
					param.put("dealerId", dealerId);
				}
				if(!"-1".equals(categoryId) && !StringUtil.isNULL(categoryId)){
					param.put("firstCatagory", categoryId);
				}
				if(!"-2".equals(status) && !StringUtil.isNULL(status)){
					param.put("statusAdmin", status);
				}
				pageEntity.setParams(param);
				/**
				 * 查询
				 */
				PageResult<GoodsBO> pageResult = goodsFacade.getPageGoodsVo(pageEntity);
				Map<String, Object> resultMap = new HashMap<String, Object>();
				String jsonString = JSON.toJSONString(pageResult.getResultList(), true);
				JSONArray jsonArray = JSON.parseArray(jsonString);
				resultMap.put("iTotalRecords", pageResult.getTotalSize());
				resultMap.put("iTotalDisplayRecords", pageResult.getTotalSize());
				resultMap.put("aaData", jsonArray);
				return resultMap;
			}else{
				return ResponseUtil.showMSGResultMap("暂无权限");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultMap();
		}
	}

	@ResponseBody
	@RequestMapping(value = "goods/detail", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "查看单个产品", httpMethod = "POST", notes = "查看单个产品", response = ResponseUtil.class)
	public String getMapper(
			@ApiParam(required = false, name = "goodsId", value = "goodsId") @RequestParam(value = "goodsId", required = false)
					String goodsId,
			HttpServletRequest request) {
		try {
			if (!StringUtil.isNULL(goodsId) && StringUtil.isNumber(goodsId)) {
				GoodsBO goodsVo = goodsFacade.getGoodsVoByPK(goodsId);
				if (goodsVo != null) {
					return ResponseUtil.successResultString(goodsVo);
				}
				return ResponseUtil.showMSGResultString("未找到");
			}
			return ResponseUtil.paramErrorResultString("id为空或格式不正确");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "goods/positionEdit", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiOperation(value = "查看单个产品", httpMethod = "POST", notes = "查看单个产品", response = ResponseUtil.class)
	public String goodsPositionEdit(
	        @ApiParam(required = false, name = "goodsId", value = "产品id") @RequestParam(value = "goodsId", required = false) String goodsId,
	        @ApiParam(required = false, name = "position", value = "产品位置,0默认1首页") @RequestParam(value = "position", required = false) String position,
	        HttpServletRequest request) {
	    try {
	        if (!StringUtil.isNULL(goodsId) && StringUtil.isNumber(goodsId)) {
	            GoodsDO goodsPo = goodsFacade.getGoodsPoByPK(Integer.parseInt(goodsId));
	            if (goodsPo != null) {
	                /*goodsPo.setPosition(position);
	                return ResponseUtil.successResultString(goodsVo);*/
	            }
	            return ResponseUtil.showMSGResultString("未找到");
	        }
	        return ResponseUtil.paramErrorResultString("id为空或格式不正确");
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseUtil.systemErrorResultString();
	    }
	}

	@ResponseBody
	@RequestMapping(value = "goods/allList", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded;charset=utf-8")
	@ApiOperation(value = "查询全部产品", httpMethod = "POST", notes = "查询全部产品", response = ResponseUtil.class)
	public String getAllList(
			@ApiParam(required = false, name = "modelId", value = "modelId") @RequestParam(value = "modelId", required = false)
					String modelId,
			HttpServletRequest request) {
		try {
			/**
			 * 查询条件参数
			 */
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("deleted", 0);
			if(!StringUtil.isNULL(modelId)){
				param.put("modelId",modelId);
			}
			/**
			 * 查询
			 */
			List<GoodsDO> goodsList = goodsFacade.getGoodsList(param);
			return ResponseUtil.successResultString(goodsList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	/*@ResponseBody
	@RequestMapping(value = "goods/agree", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded;charset=utf-8")
	@ApiOperation(value = "产品审核通过", httpMethod = "POST", notes = "产品审核通过", response = ResponseUtil.class)
	public String agree(
			@ApiParam(required = true, name = "goodsId", value = "goodsId") @RequestParam(value = "goodsId", required = true)
			String goodsId,
			HttpServletRequest request) {
		try {
			if(!StringUtil.isNumber(goodsId)){
				return ResponseUtil.resultString("goodsId错误", ResponseCodeEnum.PARAM_ERROR);
			}
			int i = goodsFacade.agreeGoods(Integer.parseInt(goodsId));
			if(i == 1){
				return ResponseUtil.successResultString("产品审核通过");
			}
			return ResponseUtil.resultString("通过失败", ResponseCodeEnum.ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	@ResponseBody
	@RequestMapping(value = "goods/refuse", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded;charset=utf-8")
	@ApiOperation(value = "产品审核拒绝", httpMethod = "POST", notes = "产品审核拒绝", response = ResponseUtil.class)
	public String refuse(
			@ApiParam(required = true, name = "goodsId", value = "goodsId") @RequestParam(value = "goodsId", required = true)
			String goodsId,
			@ApiParam(required = true, name = "rejectreason", value = "rejectreason") @RequestParam(value = "rejectreason", required = true)
			String rejectreason,
			HttpServletRequest request) {
		try {
			if(!StringUtil.isNumber(goodsId)){
				return ResponseUtil.resultString("goodsId错误", ResponseCodeEnum.PARAM_ERROR);
			}
			int i = goodsFacade.refuseGoods(Integer.parseInt(goodsId), rejectreason);
			if(i == 1){
				return ResponseUtil.successResultString("该产品已拒绝");
			}
			return ResponseUtil.resultString("拒绝失败", ResponseCodeEnum.ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}*/
	
	@ResponseBody
    @RequestMapping(value = "goods/position", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
    @ApiOperation(value = "设置产品位置", httpMethod = "POST", notes = "", response = ResponseUtil.class)
    public String showroomPosition(
            @ApiParam(required = true, name = "goodsId", value = "展品id") @RequestParam(value = "goodsId", required = true) String goodsId,
            @ApiParam(required = false, name = "position", value = "展品位置") @RequestParam(value = "position", required = false) String position,
            HttpServletRequest request) {
        try {
                if (!StringUtil.isNumber(goodsId)) {
                    return ResponseUtil.paramErrorResultString("goodsId=" + goodsId);
                }
                GoodsDO goodsVo = goodsFacade.getGoodsPoByPK(Integer
                        .parseInt(goodsId));
                if (goodsVo == null) {
                    return ResponseUtil.paramErrorResultString("找不到该产品");
                }
                GoodsDO updateGoodsPo = new GoodsDO();
                updateGoodsPo.setId(Integer.parseInt(goodsId));
                if (!(GoodsDO.position_default + "").equals(position)
                        && !(GoodsDO.position_home + "").equals(position)) {
                    return ResponseUtil.showMSGResultString("产品位置必须为0或1");
                }
                updateGoodsPo.setPosition(Integer.parseInt(position));
                int i = goodsFacade.updateGoodsPo(updateGoodsPo);
                if (i == 1) {
                    return ResponseUtil.successResultString("设置成功!");
                }
                return ResponseUtil.showMSGResultString("设置失败");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.systemErrorResultString();
        }
    }
	
	@ResponseBody
	@RequestMapping(value = "goods/shangjia", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded;charset=utf-8")
	@ApiOperation(value = "产品上架", httpMethod = "POST", notes = "产品上架", response = ResponseUtil.class)
	public String shangjia(
			@ApiParam(required = true, name = "goodsId", value = "goodsId") @RequestParam(value = "goodsId", required = true)
			String goodsId,
			HttpServletRequest request) {
		try {
			if(!StringUtil.isNumber(goodsId)){
				return ResponseUtil.paramErrorResultString("goodsId错误");
			}
			GoodsDO goods = goodsFacade.getGoodsPoByPK(Integer.parseInt(goodsId));
			if(goods == null){
				return ResponseUtil.showMSGResultString("找不到该产品");
			}
			if((GoodsDO.type_score+"").equals(goods.getType()+"")){
				if(goods.getScorePrice() == null){
					return ResponseUtil.showMSGResultString("积分产品未设置积分价 不能上架");
				}
			}
			if(goods.getTotalStock() <= 0){
				return ResponseUtil.showMSGResultString("库存为0 不能上架");
			}
			GoodsDO goodsPo = new GoodsDO();
			goodsPo.setId(Integer.parseInt(goodsId));
			goodsPo.setStatusAdmin(GoodsDO.STATUS_SHALL);
			int i = goodsFacade.updateGoodsPo(goodsPo);
			if(i == 1){
				return ResponseUtil.successResultString("上架成功");
			}
			return ResponseUtil.showMSGResultString("上架失败");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	@ResponseBody
	@RequestMapping(value = "goods/xiajia", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded;charset=utf-8")
	@ApiOperation(value = "产品下架", httpMethod = "POST", notes = "产品下架", response = ResponseUtil.class)
	public String xiajia(
			@ApiParam(required = true, name = "goodsId", value = "goodsId") @RequestParam(value = "goodsId", required = true)
			String goodsId,
			HttpServletRequest request) {
		try {
			if(!StringUtil.isNumber(goodsId)){
				return ResponseUtil.paramErrorResultString("goodsId错误");
			}
			GoodsDO goods = goodsFacade.getGoodsPoByPK(Integer.parseInt(goodsId));
			if(goods == null){
				return ResponseUtil.showMSGResultString("找不到该产品");
			}
			if(!(GoodsDO.STATUS_SHALL+"").equals(goods.getStatusAdmin()+"")){
				return ResponseUtil.showMSGResultString("该产品不是上架状态");
			}
			GoodsDO goodsPo = new GoodsDO();
			goodsPo.setId(Integer.parseInt(goodsId));
			goodsPo.setStatusAdmin(GoodsDO.STATUS_XIAJIA);
			goodsPo.setStatus(GoodsDO.STATUS_XIAJIA);
			int i = goodsFacade.updateGoodsPo(goodsPo);
			if(i == 1){
				return ResponseUtil.successResultString("下架成功");
			}
			return ResponseUtil.showMSGResultString("下架失败");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	@ResponseBody
	@RequestMapping(value = "goods/zero", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded;charset=utf-8")
	@ApiOperation(value = "设置断货", httpMethod = "POST", notes = "设置断货", response = ResponseUtil.class)
	public String zero(
			@ApiParam(required = true, name = "goodsId", value = "goodsId") @RequestParam(value = "goodsId", required = true)
			String goodsId,
			HttpServletRequest request) {
		try {
			if(!StringUtil.isNumber(goodsId)){
				return ResponseUtil.paramErrorResultString("goodsId错误");
			}
			GoodsDO goods = goodsFacade.getGoodsPoByPK(Integer.parseInt(goodsId));
			if(goods == null){
				return ResponseUtil.showMSGResultString("找不到该产品");
			}
			goods.setShowStock(0);
			goods.setTotalStock(0);
			int i = goodsFacade.updateGoodsPo(goods);
			if(i != 1){
				return ResponseUtil.showMSGResultString("修改失败");
			}
			List<GoodsOptionDO> list = goodsOptionFacade.getOptionPoByGoodsId(goods.getId());
			if(list != null){
				for(GoodsOptionDO goodsOptionPo : list){
					goodsOptionPo.setStock(0);
					int j = goodsOptionFacade.updateOptionByPk(goodsOptionPo);
					if(j != 1){
						LogUtil.dataError("修改产品配置库存为0失败 optionId="+goodsOptionPo.getId(), getClass());
					}
				}
			}
			return ResponseUtil.successResultString("设置断货成功");
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
}
