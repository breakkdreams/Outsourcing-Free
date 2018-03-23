package com.zd.aoding.outsourcing.web.controllerApi.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.zd.aoding.common.response.ResponseCodeEnum;
import com.zd.aoding.common.response.ResponseUtil;
import com.zd.aoding.config.Config;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.AccountBO;
import com.zd.aoding.outsourcing.weChat.api.facade.SessionFacade;

@Controller
@RequestMapping("ad/file")
@Api(value = "", description = "一般文件上传下载")
public class FileController {
	Logger logger = Logger.getLogger(FileController.class);
	@Autowired
	private SessionFacade sessionFacade;

	/**
	 * @Title: imgageUpload
	 * @Description: 图片上传接口
	 * @param file
	 * @param model
	 * @param request
	 * @param response
	 * @return设定文件
	 * @return String 返回类型
	 */
	@ResponseBody
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ApiOperation(value = "文件上传", notes = "上传文件，需要用户登录,单文件多文件均可", response = ResponseUtil.class)
	public String imgageUpload(HttpServletRequest request, HttpServletResponse response) {
		AccountBO a = sessionFacade.checkLoginAccountSession(request);
		if (a != null) {
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());
			if (multipartResolver.isMultipart(request)) {
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				Iterator<String> iter = multiRequest.getFileNames();
				while (iter.hasNext()) {
					MultipartFile file = multiRequest.getFile((String) iter.next());
					if (file != null) {
						String fileName = file.getOriginalFilename();
						String pathDir = Config.FILE_ROOT;
						// 下面的加的日期是为了防止上传的名字一样
						String path = FilePathUtil.fileSavePath(pathDir, a, fileName);
						// + new SimpleDateString("yyyyMMddHHmmss").String(new
						// Date()) +fileName ;
						File localFile = new File(path);
						try {
							System.out.println(path);
							System.out.println(file);
							file.transferTo(localFile);
						} catch (IllegalStateException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return ResponseUtil.systemErrorResultString();
						}
					}
				}
			}
			return ResponseUtil.successResultString("上传成功");
		} else {
			return ResponseUtil.notLoggedResultString();
		}
	}

	@ResponseBody
	@RequestMapping(value = "/fileDownload", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = "application/json;charset=utf-8")
	@ApiOperation(value = "文件下载", notes = "需要用户登录,单文件下载", response = ResponseUtil.class)
	public String imgFileDownload(String fileName, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
		try {
			String path = "D://img//";// 这个download目录为啥建立在classes下的
			InputStream inputStream = new FileInputStream(new File(path + File.separator + fileName));
			OutputStream os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			// 这里主要关闭。
			os.close();
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return ResponseUtil.resultString("未找到文件", ResponseCodeEnum.ERROR);
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseUtil.resultString("下载失败", ResponseCodeEnum.ERROR);
		}
		// 返回值要注意，要不然就出现下面这句错误！
		// java+getOutputStream() has already been called for this response
		return null;
	}
}
