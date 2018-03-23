package com.zd.aoding.outsourcing.weChat.provider.services.mysql.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zd.aoding.base.DAO.BaseDAO;
import com.zd.aoding.base.DO.base.DOBase;
import com.zd.aoding.base.adapter.SQLAdapter;
import com.zd.aoding.common.Md5.MD5Util;
import com.zd.aoding.common.StringDate.DateUtil;
import com.zd.aoding.common.json.BeanOrMapOrMongoOperationUtil;
import com.zd.aoding.common.page.PageEntity;
import com.zd.aoding.config.Config;
import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.ServerMathchBO;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ImageFileDO;
import com.zd.aoding.outsourcing.weChat.api.constant.ConfigConsts;
import com.zd.aoding.outsourcing.weChat.api.services.mysql.ImageFileService;
import com.zd.aoding.outsourcing.weChat.api.utils.file.GetStorePathAndCreateDirForStoreFile;
import com.zd.aoding.outsourcing.weChat.api.utils.file.ImageHelperByNew;
import com.zd.aoding.outsourcing.weChat.api.utils.file.MacthURL;
import com.zd.aoding.outsourcing.weChat.api.utils.file.SendUtil;

@Service
public class ImageFileServiceImpl implements ImageFileService{
	@Autowired
	private BaseDAO<ImageFileDO, Integer> baseDAO;
	private String name = "图片信息（ImageFileDO）";
	private static final String nameSpace = ImageFileDO.class.getName();
	
	
	private static final Logger LOGGER = Logger.getLogger(ImageFileServiceImpl.class);

	@Override
	public int count() {

		try {
			return baseDAO.count(nameSpace);
		} catch (Exception e) {
			LOGGER.error(name + "统计总数失败：", e);
		}
		return -1;
	}

	@Override
	public int count(Map<String, Object> param) {
		try {
			return baseDAO.count(param, nameSpace);
		} catch (Exception e) {
			LOGGER.error(name + "根据条件统计总数失败：", e);
		}
		return -1;
	}

	@Override
	public int update(ImageFileDO po) {
		try {
			po.setUpdateTime(new Date());
			return baseDAO.update(po, nameSpace);
		} catch (Exception e) {
			LOGGER.error(name + "根据主键更新信息失败：", e);
		}
		return -1;
	}

	@Override
	public ImageFileDO get(Integer primaryKey) {
		try {
			return baseDAO.get(primaryKey, nameSpace);
		} catch (Exception e) {
			LOGGER.error(name + "根据主键获取信息失败：", e);
		}
		return null;
	}

	@Override
	public List<ImageFileDO> getList(Map<String, Object> params) {
		try {
			List<ImageFileDO> list = baseDAO.getList(params, nameSpace);
			return list;
		} catch (Exception e) {
			LOGGER.error(name + "根据所有满足条件获取数据失败：", e);
			System.out.println(params);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ImageFileDO> getPagination(PageEntity page) {

		try {
			List<ImageFileDO> list = baseDAO.getPagination(page, nameSpace);
			return list;
		} catch (Exception e) {
			LOGGER.error(name + "根据所有满足条件获取分页数据失败：", e);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insert(ImageFileDO po) {
		try {
			int i = baseDAO.insert(po, nameSpace);
			return i;
		} catch (Exception e) {
			LOGGER.error(name + "插入失败：", e);
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<ImageFileDO> getBySQLAdapter(SQLAdapter adapter) {

		try {
			List<ImageFileDO> list = baseDAO.getCustom(adapter, nameSpace);
			return list;
		} catch (Exception e) {
			LOGGER.error(name + "根据所有满足条件获取分页数据失败：", e);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int delete(Integer id) {
		ImageFileDO po = new ImageFileDO();
		po.setImgId(id);
		po.setDeleted(DOBase.DELETED);
		return update(po);
	}
	
	
	
	/*
	 * (non-Javadoc)
	 * @see com.xhn.community.file.service.ImageFileService#storeImageForServer(com.
	 * xhn.community.file.bean.ImageFile, java.lang.String)
	 */
	@Override
	public int storeImageForServer(ImageFileDO image, String MD5) {
		return storeImageForServer(image, MD5, false);
	}
	/*
	 * (non-Javadoc)
	 * @see com.xhn.community.file.service.ImageFileService#storeImageForServer(com.
	 * xhn.community.file.bean.ImageFile, java.lang.String, boolean)
	 */
	@Override
	public int storeImageForServer(ImageFileDO image, String MD5, boolean needChangeSize) {
		//baseDAO.setNameSpace(nameSpace);
		// 校验客户机上传的图片的MD5值和服务器接收到的MD5值是否相同
		try {
			InputStream is = new FileInputStream(image.getFile());
			byte[] b = new byte[is.available()];
			is.read(b);
			image.setMd5(MD5Util.MD5(b));
			is.close();
			// 检查这张图片是否上传过
			ImageFileDO checkImg = findImageByMD5(image.getMd5());
			if (checkImg != null) {
				image.setImgId(checkImg.getImgId());
				image.setImgUrl(checkImg.getImgUrl());
				image.setSize(checkImg.getSize());
				image.setFileContentType(checkImg.getFileContentType());
				image.setFileName(checkImg.getFileName());
				image.setWidth(checkImg.getWidth());
				image.setHeight(checkImg.getHeight());
				image.setCreateTime(checkImg.getCreateTime());
				image.setConvertIp(checkImg.getConvertIp());
				image.setAccountId(checkImg.getAccountId());
				return 1;
			}
			if (MD5 != null && !"".equals(MD5) && !image.getMd5().equals(MD5)) {
				return 2;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return 2;
		}
		ServerMathchBO sm = MacthURL.getMathchURL();
		boolean flag = false;
		// 如果存在子服务器，则转发到子服务器。不存在子服务器则存入当前服务器,当识别需要改变尺寸时，则存储本地
		if (sm != null && !needChangeSize) {
			String json = null;
			try {
				json = SendUtil.sendFile(sm.getUrl() + ConfigConsts.UPLOAD_PATH, image);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("子服务器文件转发存储失败!");
				flag = true;
			}
			@SuppressWarnings("unchecked")
			Map<String, Object> map = BeanOrMapOrMongoOperationUtil.JsonToBean(json, Map.class);
			if (map != null) {
				Object obj = map.get("jsondata");
				if (obj != null) {
					ImageFileDO image2 = BeanOrMapOrMongoOperationUtil.JsonToBean(obj.toString(), ImageFileDO.class);
					// 根据返回参数判断是否子服务器保存成功，如果没成功则继续保存到主服务器
					if (image2.getDeleted() == 0) {
						flag = true;
					} else {
						image.setImgUrl(image2.getImgUrl());
						image.setSize(image2.getSize());
						image.setFileContentType(image2.getFileContentType());
						image.setFileName(image2.getFileName());
						image.setDeleted(image2.getDeleted());
						image.setWidth(image2.getWidth());
						image.setHeight(image2.getHeight());
						image.setCreateTime(image2.getCreateTime());
						image.setConvertIp(image2.getConvertIp());
						image.setFile(null);
						baseDAO.insert(image,nameSpace);
						sm.setFileSize(sm.getFileSize() == null ? 0 : sm.getFileSize() + (image.getSize() == null ? 0 : image.getSize()));
						return 1;
					}
				} else {
					flag = true;
				}
			} else {
				flag = true;
			}
		}
		// 根据返回参数判断是否子服务器保存成功，如果没成功则继续保存到主服务器
		if (flag || sm == null || needChangeSize) {
			String dir = GetStorePathAndCreateDirForStoreFile.createDirAndSetUrl(image);
			System.err.println("dir:"+dir);
			int code = storeImage(image, dir, needChangeSize);
			// 校验客户机上传的图片的MD5值和服务器接收到的MD5值是否相同
			try {
				InputStream is = new FileInputStream(image.getFile());
				byte[] b = new byte[is.available()];
				if (code == 1) {
					is.read(b);
					image.setMd5(MD5Util.MD5(b));
					is.close();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return 2;
			}
			image.setSize((int) image.getFile().length());
			ImageHelperByNew.getWH(image);
			long times = System.currentTimeMillis();
			image.setCreateTime(new Date(times));
			image.setFile(null);
			if (code == 0) {
				image.setDeleted(1);
				return 0;
			} else {
				image.setDeleted(0);
				image.setCreateTime(new Date());
				baseDAO.insert(image,nameSpace);
			}
		}
		return 1;
	}
	/*
	 * (non-Javadoc)
	 * @see com.xhn.community.file.service.ImageFileService#storeImage(com.xhn.
	 * community.file.bean.ImageFile, java.lang.String, boolean)
	 */
	@Override
	public int storeImage(ImageFileDO image, String dir, boolean needChangeSize) {
		InputStream is = null;
		OutputStream os = null;
		try {
			int rotation = ImageHelperByNew.getRotation(image);
			if (rotation % 360 != 0) {
				ImageHelperByNew.transform(image, rotation);
			}
			if (needChangeSize) {
				/* dpi改变出问题暂未解决 */
				// ImageHelperByNew.TiffOutput(image);
				ImageHelperByNew.getWH(image);
				double multiple = ImageHelperByNew.getMultiple(image, 4290, 2910);
				int width = (int) (image.getWidth() * multiple + 1);
				int height = (int) (image.getHeight() * multiple + 1);
				image.setFile(ImageHelperByNew.generateImageWithWH(image, width, height));
				image.setWidth(width);
				image.setHeight(height);
			}
			is = new FileInputStream(image.getFile());
			byte[] temp = new byte[is.available()];
			is.read(temp);
			String path = dir + "/" + image.getFileName() + "." + (ImageHelperByNew.getEndFileName(image)!=null?ImageHelperByNew.getEndFileName(image):"jpeg");
			System.out.println("path-------"+path);
			os = new FileOutputStream(new File(path));
			os.write(temp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return 1;
	}
	/*
	 * (non-Javadoc)
	 * @see com.xhn.community.file.service.ImageFileService#storeImages(java.util. List,
	 * java.lang.String, boolean)
	 */
	@Override
	public Map<String, Integer> storeImages(List<ImageFileDO> images, String dir, boolean needChangeSize) {
		Map<String, Integer> codes = new HashMap<String, Integer>();
		if (images == null || images.size() == 0) {
			return codes;
		}
		for (ImageFileDO image : images) {
			codes.put(image.getImgId() + "", storeImage(image, dir, needChangeSize));
		}
		return codes;
	}
	/*
	 * (non-Javadoc)
	 * @see com.xhn.community.file.service.ImageFileService#storeImages(java.util. List,
	 * java.lang.String)
	 */
	@Override
	public Map<String, Integer> storeImages(List<ImageFileDO> images, String dir) {
		return storeImages(images, dir, false);
	}
	@Override
	public int storeImageForServerByAccount(ImageFileDO image, String MD5, String username) {
		return storeImageForServerByAccounts(image, MD5, false, username);
	}
	@Override
	public ImageFileDO findImageByMD5(String MD5) {
		//baseDAO.setNameSpace(nameSpace);
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<>();
		param.put("md5", MD5);
		List<ImageFileDO> imageFiles = baseDAO.getList(param, nameSpace);
		return imageFiles == null || imageFiles.size() == 0 ? null : imageFiles.get(0);
	}
	
	public int storeImageForServerByAccounts(ImageFileDO image, String MD5, boolean needChangeSize, String username) {
		
		// 校验客户机上传的图片的MD5值和服务器接收到的MD5值是否相同
		try {
			InputStream is = new FileInputStream(image.getFile());
			byte[] b = new byte[is.available()];
			is.read(b);
			image.setMd5(MD5Util.MD5(b));
			is.close();
			// 检查这张图片是否上传过
			ImageFileDO checkImg = findImageByMD5(image.getMd5());
			if (checkImg != null) {
				image.setImgId(checkImg.getImgId());
				image.setImgUrl(checkImg.getImgUrl());
				image.setSize(checkImg.getSize());
				image.setFileContentType(checkImg.getFileContentType());
				image.setFileName(checkImg.getFileName());
				image.setWidth(checkImg.getWidth());
				image.setHeight(checkImg.getHeight());
				image.setCreateTime(checkImg.getCreateTime());
				image.setCreateStr(checkImg.getCreateStr());
				image.setConvertIp(checkImg.getConvertIp());
				image.setDesc(checkImg.getDesc());
				image.setAccountId(checkImg.getAccountId());

				return 1;
			}
			if (MD5 != null && !"".equals(MD5) && !image.getMd5().equals(MD5)) {
				return 2;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return 2;
		}
		ServerMathchBO sm = MacthURL.getMathchURL();
		boolean flag = false;
		// 如果存在子服务器，则转发到子服务器。不存在子服务器则存入当前服务器,当识别需要改变尺寸时，则存储本地
		if (sm != null && !needChangeSize) {
			String json = null;
			try {
				json = SendUtil.sendFile(sm.getUrl() + Config.UPLOAD_PATH, image);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("子服务器文件转发存储失败!");
				flag = true;
			}
			@SuppressWarnings("unchecked")
			Map<String, Object> map = BeanOrMapOrMongoOperationUtil.JsonToBean(json, Map.class);
			if (map != null) {
				Object obj = map.get("jsondata");
				if (obj != null) {
					ImageFileDO image2 = BeanOrMapOrMongoOperationUtil.JsonToBean(obj.toString(), ImageFileDO.class);
					// 根据返回参数判断是否子服务器保存成功，如果没成功则继续保存到主服务器
					if (image2.getDeleted() == 0) {
						flag = true;
					} else {
						image.setImgUrl(image2.getImgUrl());
						image.setSize(image2.getSize());
						image.setFileContentType(image2.getFileContentType());
						image.setFileName(image2.getFileName());
						image.setDeleted(image2.getDeleted());
						image.setWidth(image2.getWidth());
						image.setHeight(image2.getHeight());
						image.setCreateTime(image2.getCreateTime());
						image.setCreateStr(image2.getCreateStr());
						image.setConvertIp(image2.getConvertIp());
						image.setFile(null);
						baseDAO.insert(image,nameSpace);
						sm.setFileSize(sm.getFileSize() == null ? 0
								: sm.getFileSize() + (image.getSize() == null ? 0 : image.getSize()));
						return 1;
					}
				} else {
					flag = true;
				}
			} else {
				flag = true;
			}
		}
		// 根据返回参数判断是否子服务器保存成功，如果没成功则继续保存到主服务器
		if (flag || sm == null || needChangeSize) {
			String dir = GetStorePathAndCreateDirForStoreFile.createDirAndSetUrlByAccount(image, username);
			int code = storeImage(image, dir, needChangeSize);
			// 校验客户机上传的图片的MD5值和服务器接收到的MD5值是否相同
			try {
				InputStream is = new FileInputStream(image.getFile());
				byte[] b = new byte[is.available()];
				if (code == 1) {
					is.read(b);
					image.setMd5(MD5Util.MD5(b));
					is.close();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return 2;
			}
			image.setSize((int) image.getFile().length());
			ImageHelperByNew.getWH(image);
			long times = System.currentTimeMillis();
			image.setCreateTime(new Date());
			image.setCreateStr(DateUtil.Format(DateUtil.FULL_ST_FORMAT, new Date()));
			image.setFile(null);
			if (code == 0) {
				image.setDeleted(1);
				return 0;
			} else {
				image.setDeleted(0);
				image.setCreateTime(new Date());
				baseDAO.insert(image,nameSpace);
			}
		}
		return 1;
	}

	
}
