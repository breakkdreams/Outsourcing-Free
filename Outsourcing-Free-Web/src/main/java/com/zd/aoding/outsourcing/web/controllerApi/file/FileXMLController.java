package com.zd.aoding.outsourcing.web.controllerApi.file;
// package com.zhixiao.zx.xhn.controller.file;
//
// import java.io.File;
// import java.io.IOException;
// import java.io.PrintWriter;
// import java.util.Arrays;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
//
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
//
// import org.apache.log4j.Logger;
// import org.dom4j.Document;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.multipart.MultipartFile;
//
// import com.wordnik.swagger.annotations.Api;
// import com.wordnik.swagger.annotations.ApiOperation;
// import com.wordnik.swagger.annotations.ApiParam;
// import com.zhixiao.zx.global.utils.FileUtil;
// import com.zhixiao.zx.global.utils.RequestUtil;
// import com.zhixiao.zx.global.utils.ResponseUtil;
// import com.zhixiao.zx.xhn.facade.file.XMLFacade;
//
/// **
// * @ClassName: XMLController
// * @Description: xml数据传输
// * @author: zj
// * @date: 2017年2月13日 下午1:01:05
// */
// @Controller
// @RequestMapping("zx/file")
// @Api(value = "", description = "XML文件上传下载")
// public class FileXMLController {
// private Logger logger = Logger.getLogger(FileXMLController.class);
// @Autowired
// private XMLFacade xmlFacade;
//
// /**
// * 记录上传
// * @param file
// * @param model
// * @param request
// * @param response
// */
// @RequestMapping(value = "/xml/fileUpload", method = RequestMethod.POST, produces =
// "application/json;charset=utf-8")
// @ApiOperation(value = "XML文件上传", httpMethod = "POST", notes = "上传文件，XML文件解析", response =
// ResponseUtil.class)
// public void recordCarXML(@RequestParam(value = "file", required = true) MultipartFile file,
// HttpServletRequest request,
// HttpServletResponse response) {
// System.out.println("xml--------------------------------------");
// String paramId = RequestUtil.getString(request, "paramId");
// Map<String, Object> param = new HashMap<String, Object>();
// param.put("paramId", paramId);
// File uploadedFile = null;
// int comeOut = 0;// come=1;out=2
// try {
// uploadedFile = FileUtil.convertMultipartFileToFile(file);
// if (uploadedFile == null) {
// return;
// }
// String[] InOut = uploadedFile.getName().split("-");
// System.out.println(uploadedFile.getName() + "uploadedFile.getName()");
// System.out.println(InOut);
// List<String> list = Arrays.asList(InOut);
// if (list.contains("come")) {
// comeOut = 1;
// }
// if (list.contains("out")) {
// comeOut = 2;
// }
// param.put("comeOrOut", comeOut);
// Document document = xmlFacade.analysisXML(uploadedFile, param);
// response.setCharacterEncoding("utf-8");
// response.setContentType("text/xml; charset=UTF-8");
// PrintWriter out = response.getWriter();
// out.print(document.asXML());
// out.flush();
// out.close();
// } catch (Exception e) {
// // TODO Auto-generated catch block
// logger.error("文件报错出错", e);
// } finally {
// if (uploadedFile != null) {
// uploadedFile.delete();
// }
// }
// }
// /**
// * 从服务器读取车主信息表
// * @param model
// * @param request
// * @param response
// */
// @RequestMapping(value = "/xml/downLoad", method = { RequestMethod.POST, RequestMethod.GET },
// produces = "application/json;charset=utf-8")
// @ApiOperation(value = "XML文件下载", httpMethod = "POST", notes = "下载数据（Xml格式）", response =
// ResponseUtil.class)
// public void getCarInfos(@ApiParam(required = true, name = "userType", value = "账号类型")
// @RequestParam(value = "userType") String userType,
// HttpServletRequest request, HttpServletResponse response) {
// Map<String, Object> param = new HashMap<String, Object>();
// String str = RequestUtil.getString(request, "lastUpdateTime");
// param.put("userType", userType);
// System.out.println(userType + "userType");
// Document document = xmlFacade.generateXML(param);
// response.setCharacterEncoding("utf-8");
// response.setContentType("text/xml; charset=UTF-8");
// PrintWriter out;
// try {
// out = response.getWriter();
// out.print(document.asXML());
// out.flush();
// out.close();
// } catch (IOException e) {
// // TODO Auto-generated catch block
// logger.error("", e);
// }
// }
// }
