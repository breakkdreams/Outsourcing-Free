package com.zd.aoding.outsourcing.weChat.api.constant;


/** 
 * @ClassName: ConfigConsts 
 * @Description: 系统参数配置常量类
 * @author: HCD
 * @date: 2017年12月25日 下午1:12:02  
 */
public class ConfigConsts {
	
	/** 正式服务器地址 */
	public static final  String FORMAL_SEVER = "http://127.0.0.1:8080";
	/** 本地务器地址 */
	public static final  String LOCAL_SEVER = "http://127.0.0.1:8080";
	/** 静态保存文件地址 */
	public static final  String IMG_FILE_SAVE = "D://img";
	/** 静态视频保存文件地址 */
	public static final  String VIDEO_FILE_SAVE = "D://video";
	/** 图片部分路径*/
	public static final String IMG = "/img";
	/** 视频部分路径 */
	public static final String VIDEO = "/video";
	/** 图片服务器地址 */
	public static final String IMG_SHOW = FORMAL_SEVER + IMG;
	/** 视频服务器地址 */
	public static final String VIDEO_SHOW = FORMAL_SEVER + VIDEO;
	/** 0测试1正式(是否正式上线)*/
	public static final int IS_ONLINE = 0;
	
	/** 主服务器配置 */
	public static String tableUrl = "http://img.jxshzx.com-512000";
	/** 服务器上传子服务器图片 */
	public final static String UPLOAD_PATH = "/img/vA2DZQFE31o/yABzsw7JmiDWRhQS5mPA.action";
	/** 服务器上传子服务器多张图片 */
	public final static String UPLOAD_MANY_PATH = "/img/vA2DZQFE31o/UJlr2ZgWKj3IN.action";
	/** 获取子服务器已用文件大小 */
	public final static String GET_FILE_SIZE_PATH = "/img/vA2DZQFE31o/vNpKmbeCbTdVX49SbdRcuQ.action";
}
