package com.zd.aoding.outsourcing.web.controllerApi.management;

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
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.zd.aoding.common.StringDate.StringUtil;
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.common.page.PageResult;
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.BannerBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.RoleBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.BannerDO;
import com.zd.aoding.outsourcing.weChat.api.facade.BannerFacade;

@Api(value = "", description = "banner图管理")
@Controller
@RequestMapping("ad/manager")
public class BannerManagerController {
    @Autowired
    private BannerFacade bannerFacade;

    @ResponseBody
    @RequestMapping(value = "banner/DTPaging", method = RequestMethod.POST)
    @ApiOperation(value = "客户分页查询轮播图", httpMethod = "POST", response = ModelAndView.class)
    public Map<String, Object> userDTPaging(
            @ApiParam(required = false, name = "iDisplayStart", value = "当前页数") @RequestParam(value = "iDisplayStart", required = false) String iDisplayStart,
            @ApiParam(required = false, name = "iDisplayLength", value = "分页条数") @RequestParam(value = "iDisplayLength", required = false) String iDisplayLength,
            HttpServletRequest request) {
        try {
                //String s = request.getParameter("sSearch");
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
                param.put("deleted", 0);
                pageEntity.setParams(param);
                /**
                 * 查询
                 */
                PageResult<BannerBO> pageResult = bannerFacade.getPageBannerVo(pageEntity);
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
    @RequestMapping(value = "banner/detail", method = RequestMethod.POST)
    @ApiOperation(value = "客户查看轮播图详情", httpMethod = "POST", response = ModelAndView.class)
    public String showGoodsDetail(
            @ApiParam(required = true, name = "bannerId", value = "轮播图id") @RequestParam(value = "bannerId") String bannerId,
            HttpServletRequest request) {
        try {
                if(!StringUtil.isNumber(bannerId)){
                    return ResponseUtil.paramErrorResultString("参数错误");
                }
                BannerBO bVo = bannerFacade.getBannerVoByPK(Integer.parseInt(bannerId));
                return ResponseUtil.successResultString(bVo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.systemErrorResultString();
        }
    }
    
    
    @ResponseBody
    @RequestMapping(value = "banner/edit", method = RequestMethod.POST)
    @ApiOperation(value = "客户修改轮播图", httpMethod = "POST", response = ModelAndView.class)
    public String updateBannner(
            @ApiParam(required = false, name = "bannerId", value = "轮播图id") @RequestParam(value = "bannerId", required = false) String bannerId,
            @ApiParam(required = false, name = "type", value = "轮播图type") @RequestParam(value = "type", required = false) String type,
            @ApiParam(required = false, name = "position", value = "轮播图位置") @RequestParam(value = "position", required = false) String position,
            @ApiParam(required = false, name = "coverImgUrl", value = "轮播图图片url") @RequestParam(value = "coverImgUrl", required = false) String coverImgUrl,
            @ApiParam(required = false, name = "content", value = "内容") @RequestParam(value = "content", required = false) String content,
            @ApiParam(required = false, name = "goods", value = "产品id") @RequestParam(value = "goods", required = false) String goods,
            @ApiParam(required = false, name = "category", value = "分类id") @RequestParam(value = "category", required = false) String category,
            @ApiParam(required = false, name = "href", value = "超链接") @RequestParam(value = "href", required = false) String href,
            HttpServletRequest request) {
        try {
            	@SuppressWarnings("unchecked")
				List<Map<String, Object>> sess = (List<Map<String, Object>>) request
                        .getSession(true).getAttribute("menuSession");
                RoleBO rv = new RoleBO(sess, "轮播图管理修改");
                if ("1".equals(rv.getRoleFalg())) {
                    if(!StringUtil.isNumber(bannerId)){
                        return ResponseUtil.paramErrorResultString("参数错误");
                    }
                    BannerDO bPo = new BannerDO();
                    if(!StringUtil.isNULL(type)){
                        switch (type) {
                        case BannerDO.TYPE_Default:
                            bPo.setType(type);
                            break;
                        case BannerDO.TYPE_Hyperlink:
                        	if(StringUtil.isNULL(href)){
                        		return ResponseUtil.showMSGResultString("请输入超链接");
                        	}
                            bPo.setType(type);
                            bPo.setHyperlink(href);
                            break;
                        case BannerDO.TYPE_NewsNoticePo_category:
                        	if(StringUtil.isNumber(href)){
                        		return ResponseUtil.showMSGResultString("请选择分类");
                        	}
                        	bPo.setOwerId(Integer.parseInt(category));
                            bPo.setType(type);
                            break;
                        case BannerDO.TYPE_NewsNoticePo_goods:
                        	if(!StringUtil.isNumber(goods)){
                        		return ResponseUtil.showMSGResultString("请选择产品");
                        	}
                            bPo.setType(type);
                            bPo.setOwerId(Integer.parseInt(goods));
                            break;
                        case BannerDO.TYPE_NewsNoticePo_inside:
                        	if(StringUtil.isNULL(content)){
                        		return ResponseUtil.showMSGResultString("请输入内容");
                        	}
                            bPo.setType(type);
                            bPo.setContent(content);
                            break;
                            
                        default:
                            break;
                        }
                    }
                    if(!StringUtil.isNULL(coverImgUrl)){
                        bPo.setCoverImgUrl(coverImgUrl);
                    }
                    if(StringUtil.isNumber(position)){
                        bPo.setPosition(Integer.parseInt(position));
                    }
                    bPo.setId(Integer.parseInt(bannerId));
                    int i = bannerFacade.updateBannerPo(bPo);
                    if(i == 1){
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
    @RequestMapping(value = "banner/delete", method = RequestMethod.POST)
    @ApiOperation(value = "客户修改轮播图", httpMethod = "POST", response = ModelAndView.class)
    public String deleteBannner(
            @ApiParam(required = true, name = "bannerId", value = "轮播图id") @RequestParam(value = "bannerId") String bannerId,
            HttpServletRequest request) {
        try {
            	@SuppressWarnings("unchecked")
				List<Map<String, Object>> sess = (List<Map<String, Object>>) request
                        .getSession(true).getAttribute("menuSession");
                RoleBO rv = new RoleBO(sess, "轮播图管理删除");
                if ("1".equals(rv.getRoleFalg())) {
                    if(!StringUtil.isNumber(bannerId)){
                        return ResponseUtil.paramErrorResultString("参数错误");
                    }
                    BannerDO bPo = new BannerDO();
                    bPo.setId(Integer.parseInt(bannerId));
                    bPo.setDeleted(1);
                    int i = bannerFacade.updateBannerPo(bPo);
                    if(i == 1){
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
    @RequestMapping(value = "banner/add", method = RequestMethod.POST)
    @ApiOperation(value = "客户添加轮播图", httpMethod = "POST", response = ModelAndView.class)
    public String addBanner(
            @ApiParam(required = true, name = "type", value = "轮播图type") @RequestParam(value = "type") String type,
            @ApiParam(required = true, name = "position", value = "轮播图位置") @RequestParam(value = "position") String position,
            @ApiParam(required = true, name = "coverImgUrl", value = "轮播图图片url") @RequestParam(value = "coverImgUrl") String coverImgUrl,
            @ApiParam(required = true, name = "content", value = "内容") @RequestParam(value = "content", required = false) String content,
            @ApiParam(required = true, name = "goods", value = "产品id") @RequestParam(value = "goods", required = false) String goods,
            @ApiParam(required = true, name = "category", value = "分类id") @RequestParam(value = "category", required = false) String category,
            @ApiParam(required = true, name = "href", value = "超链接") @RequestParam(value = "href", required = false) String href,
            HttpServletRequest request) {
        try {
            	@SuppressWarnings("unchecked")
				List<Map<String, Object>> sess = (List<Map<String, Object>>) request
                        .getSession(true).getAttribute("menuSession");
                RoleBO rv = new RoleBO(sess, "轮播图管理添加");
                if ("1".equals(rv.getRoleFalg())) {
                    BannerDO bPo = new BannerDO();
                    if(!StringUtil.isNULL(type)){
                        switch (type) {
                        case BannerDO.TYPE_Default:
                            bPo.setType(type);
                            break;
                        case BannerDO.TYPE_Hyperlink:
                        	if(StringUtil.isNULL(href)){
                        		return ResponseUtil.showMSGResultString("请输入超链接");
                        	}
                            bPo.setType(type);
                            bPo.setHyperlink(href);
                            break;
                        case BannerDO.TYPE_NewsNoticePo_category:
                        	if(StringUtil.isNumber(href)){
                        		return ResponseUtil.showMSGResultString("请选择分类");
                        	}
                        	bPo.setOwerId(Integer.parseInt(category));
                            bPo.setType(type);
                            break;
                        case BannerDO.TYPE_NewsNoticePo_goods:
                        	if(!StringUtil.isNumber(goods)){
                        		return ResponseUtil.showMSGResultString("请选择产品");
                        	}
                            bPo.setType(type);
                            bPo.setOwerId(Integer.parseInt(goods));
                            break;
                        case BannerDO.TYPE_NewsNoticePo_inside:
                        	if(StringUtil.isNULL(content)){
                        		return ResponseUtil.showMSGResultString("请输入内容");
                        	}
                            bPo.setType(type);
                            bPo.setContent(content);
                            break;
                            
                        default:
                            break;
                        }
                    }
                    if(StringUtil.isNumber(position)){
                        int p = Integer.parseInt(position);
                        if(p == BannerDO.position_index){
                            bPo.setPosition(p);
                        }else if(p == BannerDO.position_score){
                            bPo.setPosition(p);
                        }else if (p == BannerDO.position_bonus){
                            bPo.setPosition(p);
                        }
                    }
                    if(!StringUtil.isNULL(coverImgUrl)){
                        bPo.setCoverImgUrl(coverImgUrl);
                    }
                    bPo.insertInit();
                    int i = bannerFacade.insertBannerPo(bPo);
                    if(i == 1){
                        return ResponseUtil.successResultString("添加成功");
                    }
                    return ResponseUtil.showMSGResultString("添加失败");
                }
                return ResponseUtil.showMSGResultString("无权限");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.systemErrorResultString();
        }
    }
}
