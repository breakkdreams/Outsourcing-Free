package com.zd.aoding.outsourcing.weChat.api.utils.file;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.zd.aoding.config.Config;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ImageFileDO;

/**
 * 用于获取目录来动态创建文件保存的根
 * @author kcj
 */
public class GetStorePathAndCreateDirForStoreFile {
	/**
	 * 获取服务器的根目录
	 * @param request
	 * @return
	 */
	public static String getStorePath() {
		String path = Config.FILE_ROOT;
		return path;
	}
	/**
	 * 根据日期动态创建目录2016-4-20:9
	 * @param root
	 * @return
	 */
	public static String createDir(String root, ImageFileDO image) {
		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HH");
			String dir = sdf.format(date);
			String fileDir = root + "//" + dir;
			File file = new File(fileDir);
			boolean ok = false;
			if (!file.exists()) {
				ok = file.mkdir();
			} else {
				ok = true;
			}
			if (ok) {
				if (image != null) {
					image.setFileName(System.currentTimeMillis() + Random() % 99999 + "");
					;
					/**
					 * 图片服务器
					 */
					// image.setImgUrl(Config.domain+"/"+dir+"/"+image.getFileName()+"."+ImageHelperByNew.getEndFileName(image));
					/**
					 * 简易图片服务器
					 */
					image.setImgUrl("/" + dir + "/" + image.getFileName() + "." + ImageHelperByNew.getEndFileName(image));
				}
				return fileDir;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	private static int Random() {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * 根据日期动态创建目录2016-4-20:9
	 * @param request
	 * @return
	 */
	public static String createDir() {
		String root = getStorePath();
		return createDir(root, null);
	}
	/**
	 * 根据日期动态创建目录2016-4-20:9
	 * @param request
	 * @return
	 */
	public static String createDirAndSetUrl(ImageFileDO image) {
		String root = getStorePath();
		return createDir(root, image);
	}
	/**
	 * 根据用户名创建目录2016-4-20:9
	 * @param root
	 * @return
	 */
	public static String createDirByAccount(String root, ImageFileDO image, String username) {
		try {
			String fileDir = root + "//" + username;
			File file = new File(fileDir);
			boolean ok = false;
			if (!file.exists()) {
				ok = file.mkdir();
			} else {
				ok = true;
			}
			if (ok) {
				if (image != null) {
					image.setFileName(System.currentTimeMillis() + Random() % 99999 + "");
					;
					/**
					 * 图片服务器
					 */
					// image.setImgUrl(Config.domain+"/"+username+"/"+image.getFileName()+"."+ImageHelperByNew.getEndFileName(image));
					/**
					 * 简易图片服务器
					 */
					image.setImgUrl("/" + username + "/" + image.getFileName() + "." + ImageHelperByNew.getEndFileName(image));
				}
				return fileDir;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据日期动态创建目录2016-4-20:9
	 * @param request
	 * @return
	 */
	public static String createDirAndSetUrlByAccount(ImageFileDO image, String username) {
		String root = getStorePath();
		return createDirByAccount(root, image, username);
	}
}
