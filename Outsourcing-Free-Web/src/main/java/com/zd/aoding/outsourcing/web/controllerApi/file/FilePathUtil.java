//package com.mall.zd.global.utils;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//
//import org.apache.log4j.Logger;
///**
// * @ClassName:FileUtil
// * @author: Vinda.Z
// * @QQ:443550101
// * @email: Vinda.Zhang@gmail.com
// * @date:2012-6-22下午09:57:43
// */
//public class FileUtil {
//	
//	private static final Logger logger = Logger.getLogger(FileUtil.class);
//	
//	/**
//	 * 文件上传
//	 * @param fileName
//	 * @param input
//	 * @return
//	 * @throws IOException
//	 */
//	public static long writeFile(String fileName, InputStream input)
//			throws IOException {
//		CreateDirecroty(fileName);
//		FileOutputStream out = new FileOutputStream(fileName);
//		byte[] bytes = new byte[1024];
//		long offset = 0L;
//		int c;
//		while ((c = input.read(bytes)) != -1) {
//			out.write(bytes, 0, c);
//			offset += c;
//		}
//		input.close();
//		out.close();
//		return offset;
//	}
//
//	/**
//	 * 文件读取
//	 * @param fileName
//	 * @param out
//	 * @return
//	 * @throws IOException
//	 */
//	public static long readFile(String fileName, OutputStream out)
//			throws IOException {
//		fileName.replace("//", "/");
//		File localFile = new File(fileName);
//		if(!localFile.exists()){
//			throw new FileNotFoundException(fileName+" (系统找不到指定的路径�?)");
//		}
//		long filesize = localFile.length();
//		FileInputStream fis = new FileInputStream(localFile);
//		byte[] bytes = new byte[1024];
//		long step = filesize / 100;
//		step = (step == 0 ? 1 : step);
//		long process = 0;
//		long offset = 0L;
//		int c;
//		while ((c = fis.read(bytes)) != -1) {
//			out.write(bytes, 0, c);
//			offset += c;
//			long nowProcess = offset / step;
//			if (nowProcess > process) {
//				process = nowProcess;
//				if (process % 5 == 0) {
//					logger.debug("read [" + fileName + "] >>> "
//							+ offset + "/" + filesize + " bytes");
//				}
//			}
//		}
//		fis.close();
//		out.close();
//		return offset;
//	}
//
//	/**
//	 * 创建目录
//	 * 
//	 * @param remote
//	 *            文件绝对路径,�?'/'结尾
//	 * 
//	 * @return 目录创建是否成功
//	 * @throws IOException
//	 */
//	public static boolean CreateDirecroty(String remote) throws IOException {
//		remote.replace("\\", "/");
//		boolean success = true;
//		String directory = remote.substring(0, remote.lastIndexOf("/") + 1);
//		// 如果目录不存在，则创建目�?
//		File dir = new File(directory);
//		if (!dir.exists()) {
//			success=dir.mkdirs();
//		}
//		return success;
//	}
//	
//	/**
//	 * 删除改文件夹下的�?有文�?
//	 * @param dir
//	 * @return
//	 */
//	public static boolean deleteDir(File dir) {
//        if (dir.isDirectory()) {
//            String[] children = dir.list();
//            for (int i=0; i<children.length; i++) {
//                boolean success = deleteDir(new File(dir, children[i]));
//                if (!success) {
//                    return false;
//                }
//            }
//        }
//        // 目录此时为空，可以删�?
//        return dir.delete();
//    }
//}
package com.zd.aoding.outsourcing.web.controllerApi.file;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.zd.aoding.outsourcing.weChat.api.bean.businessObject.AccountBO;

public class FilePathUtil {
	/**
	 * 返回为false则表示创建目录失败
	 * <p>
	 * 以"/"作为分隔符
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static boolean createDir(String path) {
		String[] paths = path.split("/");
		boolean flag = true;
		String str = "";
		File file = null;
		for (String p : paths) {
			if (!"".equals(p)) {
				if (p.indexOf(":") != -1) {
					str = p + "//";
					continue;
				}
				str = str + "/" + p;
				file = new File(str);
				if (!file.exists() || !file.isDirectory()) {
					flag = file.mkdir();
				}
			}
			// 如果有目录创建失败立马返回创建失败
			if (!flag) {
				return false;
			}
		}
		return true;
	}

	public static String fileSavePath(String pathDir, AccountBO account, String fileName) {
		// TODO Auto-generated method stub
		String path = pathDir + "accountId=" + account.getAccountId() + "//"
				+ new SimpleDateFormat("yyyyMMdd").format(new Date()) + "//"
				+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + fileName;
		if (createDir(path)) {
			return path;
		} else {
			return pathDir + "temp" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + fileName;
		}
	}
}
