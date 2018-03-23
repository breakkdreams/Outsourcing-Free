package com.zd.aoding.outsourcing.weChat.api.bean.dataObject;

import java.io.File;
import java.io.Serializable;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.zd.aoding.base.DO.base.DOBase;


/** 
 * @ClassName: ImageFileDO 
 * @Description: 图片信息实体数据类
 * @author: HCD
 * @date: 2017年12月26日 下午2:12:56  
 */
@ApiModel(value = "", description = "图片信息实体数据类")
public class ImageFileDO  extends DOBase implements Serializable{
	
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -8798599937595475158L;
	
	@ApiModelProperty("账户主键id")
	private Integer imgId;
	@ApiModelProperty("上传帐号")
	private Integer accountId; 
	@ApiModelProperty("保存的url路径")
	private String imgUrl;
	@ApiModelProperty("图片大小")
	private Integer size;
	@ApiModelProperty("图片类型")
	private String fileContentType;
	@ApiModelProperty("图片宽度")
	private Integer width;
	@ApiModelProperty("图片宽高")
	private Integer height;
	@ApiModelProperty("IP地址")
	private String convertIp; 
	@ApiModelProperty("整个文件 的MD5值加密")
	private String md5;
	@ApiModelProperty("图片类型 0：普通 1：默认头像")
	private Integer type; 
	@ApiModelProperty("文件")
	private File file; 
	@ApiModelProperty("文件名")
	private String fileName;
	private String desc; // 文件描述
	private String createStr; // 创建时间
	
	public ImageFileDO(){
		super();
		// TODO Auto-generated constructor stub
	}
	public ImageFileDO(Integer imgId , Integer accountId , String imgUrl , Integer size , String fileContentType , Integer width , Integer height , String convertIp , String md5 , Integer type){
		super();
		this.imgId = imgId;
		this.accountId = accountId;
		this.imgUrl = imgUrl;
		this.size = size;
		this.fileContentType = fileContentType;
		this.width = width;
		this.height = height;
		this.convertIp = convertIp;
		this.md5 = md5;
		this.type = type;
	}
	
	public Integer getImgId() {
		return imgId;
	}
	
	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}
	
	public Integer getAccountId() {
		return accountId;
	}
	
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	
	public String getImgUrl() {
		return imgUrl;
	}
	
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public Integer getSize() {
		return size;
	}
	
	public void setSize(Integer size) {
		this.size = size;
	}
	
	public String getFileContentType() {
		return fileContentType;
	}
	
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	
	public Integer getWidth() {
		return width;
	}
	
	public void setWidth(Integer width) {
		this.width = width;
	}
	
	public Integer getHeight() {
		return height;
	}
	
	public void setHeight(Integer height) {
		this.height = height;
	}
	
	public String getConvertIp() {
		return convertIp;
	}
	
	public void setConvertIp(String convertIp) {
		this.convertIp = convertIp;
	}
	
	public String getMd5() {
		return md5;
	}
	
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	
	public Integer getType() {
		return type;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	
	public File getFile() {
		return file;
	}
	
	public void setFile(File file) {
		this.file = file;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getCreateStr() {
		return createStr;
	}
	public void setCreateStr(String createStr) {
		this.createStr = createStr;
	}
	@Override
	public String toString() {
		return "ImageFile [imgId=" + imgId + ", accountId=" + accountId + ", imgUrl=" + imgUrl + ", size=" + size + ", fileContentType=" + fileContentType + ", width=" + width + ", height=" + height
			+ ", convertIp=" + convertIp + ", md5=" + md5 + ", type=" + type + "]";
	}

	
}
