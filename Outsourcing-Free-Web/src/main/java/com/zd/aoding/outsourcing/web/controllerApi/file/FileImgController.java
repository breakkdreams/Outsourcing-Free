package com.zd.aoding.outsourcing.web.controllerApi.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.zd.aoding.common.json.BeanOrMapOrMongoOperationUtil;
import com.zd.aoding.common.response.ResponseCodeEnum;
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.config.Config;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ImageFileDO;
import com.zd.aoding.outsourcing.weChat.api.facade.SessionFacade;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.ImageFileService;
import com.zd.aoding.outsourcing.weChat.api.utils.file.FileUtil;

@Controller
@RequestMapping("ad/file")
@Api(value = "", description = "图片文件上传及下载")
public class FileImgController {
	Logger logger = Logger.getLogger(FileImgController.class);
	@Autowired
	private ImageFileService imageFileService;
	@Autowired
	private SessionFacade loginPoolService;

	@ResponseBody
	@RequestMapping(value = "webupload", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded;charset=utf-8")
	@ApiOperation(value = "单多图片上传", httpMethod = "POST", notes = "", response = ResponseUtil.class)
	public String webupload(
			@ApiParam(required = true, value = "可以单个也可以多个图片文件", name = "file") @RequestParam(value = "file", required = true) MultipartFile[] files,
			Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// AccountVo accountMapper =
		// loginPoolService.checkLoginAccountSession(request);
		// if (accountMapper != null) {
		System.err.println("--------webupload----------------1");
		File uploadedFile = null;
		FileOutputStream fos = null;
		try {
			Map<String, Object> mainresult = new HashMap<String, Object>();
			for (MultipartFile file : files) {
				Map<String, Object> result = new HashMap<String, Object>();
				uploadedFile = FileUtil.convertMultipartFileToFile(file);
				if (uploadedFile == null) {
					result.put("error", 1);
					result.put("message", "上传失败1");
					return ResponseUtil.resultString(result, ResponseCodeEnum.ERROR);
				}
				ImageFileDO image = new ImageFileDO();
				image.setAccountId(1);
				image.setFile(uploadedFile);
				image.setFileName(file.getName());
				image.setFileContentType(file.getContentType());
				int code = imageFileService.storeImageForServer(image, null);
				if (code == 1) {
					result.put("error", 0);
					result.put("url", image.getImgUrl());
					result.put("showUrl", Config.IMG_SERVER + image.getImgUrl());
					result.put("imgid", image.getImgId());
					result.put("message", "上传成功");
					list.add(result);
				} else {
					result.put("error", 1);
					result.put("message", "上传失败");
				}
			}
			// mainresult.put("error", 0);
			mainresult.put("message", list);
			return ResponseUtil.successResultString(mainresult);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("文件报错出错", e);
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
				if (uploadedFile != null) {
					uploadedFile.delete();
				}
			} catch (IOException e) {
			}
		}
		return ResponseUtil.resultString("上传失败", ResponseCodeEnum.ERROR);
		// } else {
		// return ResponseUtil.notLoggedResultString();
		// }
	}
	
	@ResponseBody
	@RequestMapping(value = "webupload1", method = RequestMethod.POST, produces = { "application/json;charset=utf-8", "text/html;charset=utf-8",
			"application/x-www-form-urlencoded;charset=utf-8", "image/jpeg;charset=utf-8" })
	@ApiOperation(value = "单多图片上传", httpMethod = "POST", notes = "", response = ResponseUtil.class)
	public String webupload1(
			@ApiParam(required = true, value = "单图片文件", name = "file") @RequestParam(value = "file", required = true) MultipartFile file,
			Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
			System.err.println("--------webupload----------------111");
			File uploadedFile = null;
			FileOutputStream fos = null;
			try {
				Map<String, Object> result = new HashMap<String, Object>();
				uploadedFile = FileUtil.convertMultipartFileToFile(file);
				if (uploadedFile == null) {
					result.put("error", 1);
					result.put("message", "上传失败1");
					return ResponseUtil.resultString(result, ResponseCodeEnum.ERROR);
				}
				ImageFileDO image = new ImageFileDO();
				image.setAccountId(1);
				image.setFile(uploadedFile);
				image.setFileName(file.getName());
				image.setFileContentType(file.getContentType());
				int code = imageFileService.storeImageForServer(image, null);
				if (code == 1) {
					result.put("url", image.getImgUrl());
					result.put("showUrl", Config.IMG_SERVER + image.getImgUrl());
					result.put("imgid", image.getImgId());
					return ResponseUtil.successResultString(result);
				} else {
					result.put("error", 1);
					result.put("message", "上传失败");
					return ResponseUtil.resultString(result, ResponseCodeEnum.ERROR);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("文件报错出错", e);
			} finally {
				try {
					if (fos != null) {
						fos.close();
					}
					if (uploadedFile != null) {
						uploadedFile.delete();
					}
				} catch (IOException e) {
				}
			}
			System.out.println("false");
			return ResponseUtil.resultString("上传失败", ResponseCodeEnum.ERROR);
	}

	/**
	 * 用于 kindeditor编辑器图片上传
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws FileUploadException
	 */
	@ResponseBody
	@RequestMapping(value = "kindeditorfileupload", method = { RequestMethod.POST, RequestMethod.GET }, produces = {
			"application/json;charset=utf-8", "text/html;charset=utf-8" })
	@ApiOperation(value = "kingEdit图片上传(只用于kingedit)", notes = "", response = ResponseUtil.class)
	public String kindeditorFileUpload(
			@ApiParam(name = "imgFile", value = "图片(单图片)", required = true) MultipartFile imgFile,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		// AccountVo a = loginPoolService.checkLoginAccountSession(request);
		System.out.println(imgFile);
		// if (a != null) {
		File uploadedFile = FileUtil.convertMultipartFileToFile(imgFile);
		if (uploadedFile == null) {
			result.put("error", 1);
			result.put("errorS", "1");
			result.put("message", "未提交文件");
			return BeanOrMapOrMongoOperationUtil.mapToJson(result);
		}
		ImageFileDO image = new ImageFileDO();
		image.setAccountId(1);
		image.setFile(uploadedFile);
		image.setFileName(imgFile.getName());
		image.setFileContentType(imgFile.getContentType());
		int code = imageFileService.storeImageForServer(image, null);
		if (code == 1) {
			result.put("error", 0);
			result.put("errorS", "0");
			result.put("url", Config.IMG_SERVER + image.getImgUrl());
			return BeanOrMapOrMongoOperationUtil.mapToJson(result);
		}
		result.put("error", 1);
		result.put("errorS", "1");
		result.put("message", "上传失败");
		return BeanOrMapOrMongoOperationUtil.mapToJson(result);
		// } else {
		// result.put("error", 1);
		// result.put("errorS", "1");
		// result.put("message", "未登录");
		// return BeanOrMapOrMongoOperationUtil.mapToJson(result);
		// }
	}
	// @ResponseBody
	// @RequestMapping(value = "/file/imageDownload", method = {
	// RequestMethod.POST,
	// RequestMethod.GET }, produces =
	// "application/x-www-form-urlencoded;charset=utf-8")
	// @ApiOperation(value = "图片查看下载", notes = "", response =
	// ResponseUtil.class)
	// public String imgFileDownload(
	// @ApiParam(name = "imgId", value = "图片id（遗弃、暂时不用）", required = true)
	// @RequestParam(value = "imgId", required = true) String imgId,
	// HttpServletRequest request, HttpServletResponse response) {
	// ImageFile imageFile =
	// imageFileService.getMapperByPK(Integer.parseInt(imgId));
	// return ResponseUtil.successResultString(Config.ImgSever +
	// imageFile.getImgUrl());
	// }
}
