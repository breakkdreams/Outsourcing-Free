package com.zd.aoding.outsourcing.web.controllerApi.app.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.plexus.util.StringUtils;
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
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.NoticeBO;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.UserBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.NoticeDO;
import com.zd.aoding.outsourcing.weChat.api.constant.SessionConstant;
import com.zd.aoding.outsourcing.weChat.api.facade.NoticeFacade;
import com.zd.aoding.outsourcing.weChat.api.facade.SessionFacade;

@Api(value = "", description = "通知管理")
@Controller
@RequestMapping("user")
public class NoticeUserController {
	@Autowired
	private SessionFacade sessionFacade;
	@Autowired
	private NoticeFacade noticeFacade;

	
	@ResponseBody
	@RequestMapping(value = "notice/paging", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiResponses({ @ApiResponse(code = 1, message = "配置名称", response = NoticeDO.class) })
	@ApiOperation(value = "分页查询通知", httpMethod = "POST", notes = "", response = ResponseUtil.class)
	public String paging(
			@ApiParam(required = false, name = "pageNum", value = "当前第几页") @RequestParam(value = "pageNum", required = false) String pageNum,
			@ApiParam(required = false, name = "pageSize", value = "分页条数") @RequestParam(value = "pageSize", required = false) String pageSize,
			@ApiParam(required = false, name = "type", value = "通知类型") @RequestParam(value = "type", required = false) String type,
			HttpServletRequest request) {
		try {
			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
			if (sessionUser != null) {
				// 初始化列表
				PageEntity pageEntity = new PageEntity();
				if (StringUtils.isNotBlank(pageNum)) {
					pageEntity.setPage(Integer.parseInt(pageNum));
				}
				if (StringUtils.isNotBlank(pageSize)) {
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
				param.put("userId", sessionUser.getUserId());
				if(StringUtil.isNumber(type)){
                    param.put("type", Integer.parseInt(type));
                }
				pageEntity.setParams(param);
				PageResult<NoticeBO> pageResult = noticeFacade.getPageNoticeVo(pageEntity);
				return ResponseUtil.successResultString(pageResult);
			}
			return ResponseUtil.notLoggedResultString();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	@ResponseBody
	@RequestMapping(value = "noticeSystem/paging", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiResponses({ @ApiResponse(code = 1, message = "配置名称", response = NoticeDO.class) })
	@ApiOperation(value = "分页查询系统通知", httpMethod = "POST", notes = "", response = ResponseUtil.class)
	public String noticeSystem(
			@ApiParam(required = false, name = "pageNum", value = "当前第几页") @RequestParam(value = "pageNum", required = false) String pageNum,
			@ApiParam(required = false, name = "pageSize", value = "分页条数") @RequestParam(value = "pageSize", required = false) String pageSize,
			HttpServletRequest request) {
		try {
			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
			if (sessionUser != null) {
				// 初始化列表
				PageEntity pageEntity = new PageEntity();
				if (StringUtils.isNotBlank(pageNum)) {
					pageEntity.setPage(Integer.parseInt(pageNum));
				}
				if (StringUtils.isNotBlank(pageSize)) {
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
				param.put("type", NoticeDO.type_system);
				param.put("userTime", sessionUser.getCreateTimeStr());
				pageEntity.setParams(param);
				PageResult<NoticeBO> pageResult = noticeFacade.getPageNoticeVo(pageEntity);
				return ResponseUtil.successResultString(pageResult);
			}
			return ResponseUtil.notLoggedResultString();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}

	
	@ResponseBody
	@RequestMapping(value = "notice/isSystemRead", method = RequestMethod.POST,produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiResponses({ @ApiResponse(code = 1, message = "配置名称", response = NoticeDO.class) })
	@ApiOperation(value = "查看是否有新的系统通知", httpMethod = "POST", notes = "查看是否有新的系统通知", response = ResponseUtil.class)
	public String getNewsAllNotice(
			HttpServletRequest request) {
		try {
			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
			if (sessionUser != null) {
				int i = noticeFacade.getNoReadNoticeNum(sessionUser.getUserId(), "0", sessionUser.getCreateTime());
				return ResponseUtil.successResultString(i);
			}
			return ResponseUtil.notLoggedResultString();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "notice/detail", method = RequestMethod.POST,produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiResponses({ @ApiResponse(code = 1, message = "配置名称", response = NoticeDO.class) })
	@ApiOperation(value = "根据ID查看通知", httpMethod = "POST", notes = "根据ID查看", response = ResponseUtil.class)
	public String getNewsNoticeById(
			@ApiParam(required = false, name = "noticeId", value = "通知id") @RequestParam(value = "noticeId", required = false) String noticeId,
			HttpServletRequest request) {
		try {
			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
			if (sessionUser != null) {
				if (StringUtil.isNumber(noticeId)) {
					NoticeBO noticeVo = noticeFacade.getNoticeVoById(Integer.parseInt(noticeId));
					if (noticeVo != null) {
						return ResponseUtil.successResultString(noticeVo);
					}
					return ResponseUtil.showMSGResultString("暂无记录");
				}
				 return ResponseUtil.showMSGResultString("暂无记录");
			}
			return ResponseUtil.notLoggedResultString();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	
	@ResponseBody
    @RequestMapping(value = "notice/isRead", method = RequestMethod.POST,produces = "application/x-www-form-urlencoded; charset=utf-8")
    @ApiResponses({ @ApiResponse(code = 1, message = "配置名称", response = NoticeDO.class) })
    @ApiOperation(value = "查看是否有新的通知", httpMethod = "POST", notes = "查看是否有新的通知", response = ResponseUtil.class)
    public String getNewsNoticeById(
            HttpServletRequest request) {
        try {
        	UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
            if (sessionUser != null) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("status", NoticeDO.status_default);
                map.put("deleted", 0);
                map.put("userId", sessionUser.getUserId());
                int i = noticeFacade.countNewsNotice(map);
                return ResponseUtil.successResultString(i);
            }
            return ResponseUtil.notLoggedResultString();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.systemErrorResultString();
        }
    }
	
	@ResponseBody
	@RequestMapping(value = "notice/read", method = RequestMethod.POST,produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiResponses({ @ApiResponse(code = 1, message = "配置名称", response = NoticeDO.class) })
	@ApiOperation(value = "阅读通知", httpMethod = "POST", notes = "根据ID阅读", response = ResponseUtil.class)
	public String readNotice(
	        @ApiParam(required = false, name = "noticeId", value = "通知id") @RequestParam(value = "noticeId", required = false) String noticeId,
	        HttpServletRequest request) {
	    try {
	    	UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
	        if (sessionUser != null) {
	            System.err.println(noticeId);
	            if (StringUtil.isNumber(noticeId)) {
	            	NoticeDO noticePo = new NoticeDO();
	                noticePo.setId(Integer.parseInt(noticeId));
	                noticePo.setStatus(NoticeDO.status_read);
	                int i = noticeFacade.updateNoticePo(noticePo);
	                if (i == 1) {
	                    return ResponseUtil.successResultString("");
	                }
	                return ResponseUtil.showMSGResultString("阅读失败");
	            }
	            return ResponseUtil.showMSGResultString("阅读失败");
	        }
	        return ResponseUtil.notLoggedResultString();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseUtil.systemErrorResultString();
	    }
	}
	
	@ResponseBody
    @RequestMapping(value = "notice/allRead", method = RequestMethod.POST,produces = "application/x-www-form-urlencoded; charset=utf-8")
    @ApiResponses({ @ApiResponse(code = 1, message = "配置名称", response = NoticeDO.class) })
    @ApiOperation(value = "阅读所有通知", httpMethod = "POST", notes = "阅读所有通知", response = ResponseUtil.class)
    public String readALLNotice(
            HttpServletRequest request) {
        try {
        	UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
            if (sessionUser != null) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("status", NoticeDO.status_default);
                    map.put("deleted", 0);
                    map.put("userId", sessionUser.getUserId());
                    int i = noticeFacade.readAllNotice(map);
                    if (i == 1) {
                        return ResponseUtil.successResultString("");
                    }
                    return ResponseUtil.systemErrorResultString();
            }
            return ResponseUtil.notLoggedResultString();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.systemErrorResultString();
        }
    }
	
	@ResponseBody
	@RequestMapping(value = "notice/allSystemRead", method = RequestMethod.POST,produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiResponses({ @ApiResponse(code = 1, message = "配置名称", response = NoticeDO.class) })
	@ApiOperation(value = "阅读所有系统通知", httpMethod = "POST", notes = "阅读所有通知", response = ResponseUtil.class)
	public String readALLSystemNotice(
			HttpServletRequest request) {
		try {
			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
			if (sessionUser != null) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("status", NoticeDO.status_default);
				map.put("deleted", 0);
				map.put("userId", sessionUser.getUserId());
				int i = noticeFacade.readSystemNotice(sessionUser.getUserId(), "0", sessionUser.getCreateTime());
				if (i == 1) {
					return ResponseUtil.successResultString("");
				}
				return ResponseUtil.systemErrorResultString();
			}
			return ResponseUtil.notLoggedResultString();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "notice/haveNewNotice", method = RequestMethod.POST,produces = "application/x-www-form-urlencoded; charset=utf-8")
	@ApiResponses({ @ApiResponse(code = 1, message = "配置名称", response = NoticeDO.class) })
	@ApiOperation(value = "查看是否有新的系统通知和普通通知", httpMethod = "POST", notes = "查看是否有新的系统通知和普通通知", response = ResponseUtil.class)
	public String haveNewNotice(
			HttpServletRequest request) {
		try {
			UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
			if (sessionUser != null) {
				int i = noticeFacade.getNoReadNoticeNum(sessionUser.getUserId(), "0", sessionUser.getCreateTime());
				Map<String, Object> map = new HashMap<String, Object>();
                map.put("status", NoticeDO.status_default);
                map.put("deleted", 0);
                map.put("userId", sessionUser.getUserId());
                int j = noticeFacade.countNewsNotice(map);
				return ResponseUtil.successResultString(i+j);
			}
			return ResponseUtil.notLoggedResultString();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.systemErrorResultString();
		}
	}
	
	@ResponseBody
    @RequestMapping(value = "notice/readAllNotice", method = RequestMethod.POST,produces = "application/x-www-form-urlencoded; charset=utf-8")
    @ApiResponses({ @ApiResponse(code = 1, message = "配置名称", response = NoticeDO.class) })
    @ApiOperation(value = "阅读所有普通通知和系统通知", httpMethod = "POST", notes = "阅读所有普通通知和系统通知", response = ResponseUtil.class)
    public String readALLAllNotice(
            HttpServletRequest request) {
        try {
        	UserBO sessionUser = (UserBO) sessionFacade.checkLoginSession(request, SessionConstant.LOGIN_TYPE_USER);
            if (sessionUser != null) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("status", NoticeDO.status_default);
                    map.put("deleted", 0);
                    map.put("userId", sessionUser.getUserId());
                    int i = noticeFacade.readAllNotice(map);
                    if (i == 1) {
                    	int j = noticeFacade.readSystemNotice(sessionUser.getUserId(), "0", sessionUser.getCreateTime());
                    	if(j == 1){
                    		return ResponseUtil.successResultString("阅读成功");
                    	}
                    	return ResponseUtil.showMSGResultString("阅读系统通知失败");
                    }
                    return ResponseUtil.showMSGResultString("阅读失败");
            }
            return ResponseUtil.notLoggedResultString();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.systemErrorResultString();
        }
    }
	
}
