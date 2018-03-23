package com.zd.aoding.outsourcing.weChat.api.services.mysql;

import java.util.List;
import java.util.Map;

import com.zd.aoding.base.DAO.BaseServise;
import com.zd.aoding.base.adapter.SQLAdapter;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ImageFileDO;

public interface ImageFileService extends BaseServise<ImageFileDO>{
	
	/**
	 * 
	 * @Title: getBySQLAdapter
	 * @Description: 慎用！定制，sql直接查询
	 * @param adapter
	 * @return
	 * @return: List<AccountDO>
	 */
	List<ImageFileDO> getBySQLAdapter(SQLAdapter adapter);

	int delete(Integer id);
	
	public abstract int storeImageForServerByAccount(ImageFileDO image, String MD5, String username);
	
	public abstract int storeImageForServer(ImageFileDO image, String MD5);
	/**
	 * 0.保存失败
	 * <p>
	 * 1.保存成功
	 * <p>
	 * 2.MD5校验失败
	 * <p>
	 * dir为需要存储的目录
	 * @param image
	 * @return
	 */
	public abstract int storeImageForServer(ImageFileDO image, String MD5, boolean needChangeSize);
	/**
	 * 0.保存失败
	 * <p>
	 * 1.保存成功
	 * <p>
	 * dir为需要存储的目录
	 * @param image
	 * @return
	 */
	public abstract int storeImage(ImageFileDO image, String dir, boolean needChangeSize);
	/**
	 * 0.保存失败
	 * <p>
	 * 1.保存成功
	 * <p>
	 * 返回信息以map<_id,code>存入保存信息
	 * <p>
	 * @param images
	 * @return
	 */
	public abstract Map<String, Integer> storeImages(List<ImageFileDO> images, String dir, boolean needChangeSize);
	public abstract Map<String, Integer> storeImages(List<ImageFileDO> images, String dir);
	/**
	 * 根据md5查询图片 ,有则返回id
	 * @param MD5
	 * @return
	 */
	public abstract ImageFileDO findImageByMD5(String MD5);

	
}
