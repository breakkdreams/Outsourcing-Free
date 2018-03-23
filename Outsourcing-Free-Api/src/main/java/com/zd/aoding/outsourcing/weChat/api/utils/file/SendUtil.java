package com.zd.aoding.outsourcing.weChat.api.utils.file;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ImageFileDO;

public class SendUtil {
	public static String sendPost(String url, Map<String, String> params) {
        OutputStreamWriter out = null;
        BufferedReader in = null;        
        StringBuilder result = new StringBuilder(); 
        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn =(HttpURLConnection) realUrl.openConnection();
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // POST方法
            conn.setRequestMethod("POST");
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            
            
            try {
            	conn.connect();
			} catch (ConnectException e) {
				// TODO: handle exception
				System.out.println("服务器不能正常连接!");
				return null;
			}
            
            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            // 发送请求参数            
            if (params != null) {
		          StringBuilder param = new StringBuilder(); 
		          for (Map.Entry<String, String> entry : params.entrySet()) {
		        	  if(param.length()>0){
		        		  param.append("&");
		        	  }	        	  
		        	  param.append(entry.getKey());
		        	  param.append("=");
		        	  param.append(entry.getValue());		        	  
		          }
		          out.write(param.toString());
            }
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            
            conn.disconnect();
        } catch (Exception e) {    
        	e.printStackTrace();
        }finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result.toString();
    }
	/**
	 * 发送图片
	 * @param url
	 * @param image
	 * @return
	 */
	@SuppressWarnings("resource")
	public static String sendFile(String path, ImageFileDO image)throws Exception{
		StringBuilder result = new StringBuilder(); 
		DataOutputStream dos = null;
		BufferedReader in = null;
		try {
			URL url = new URL(path);

			String boundary = "Boundary-b1ed-4060-99b9-fca7ff59c113"; // Could be ant strings
			String Enter = "\r\n";

			FileInputStream fis = new FileInputStream(image.getFile());

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			conn.setUseCaches(false);
			conn.setInstanceFollowRedirects(true);
			conn.setRequestProperty("Content-Type","multipart/form-data;boundary=" + boundary);

			try {
				conn.connect();
			} catch (ConnectException e) {
				// TODO: handle exception
				System.out.println("服务器不能正常连接!");
				return result.toString();
			}
	            

			dos = new DataOutputStream(conn.getOutputStream());

			// part 1
			String part1 = "--" + boundary + Enter
					+ "Content-Type: "+image.getFileContentType() + Enter
					+ "Content-Disposition: form-data; filename=\""
					+ image.getFileName() + "\"; name=\"file\"" + Enter + Enter;
			// part 2
			String part2 = Enter + "--" + boundary + Enter
					+ "Content-Type: "+image.getFileContentType() + Enter
					+ "Content-Disposition: form-data; name=\"dataFormat\""
					+ Enter + Enter + "hk" + Enter + "--" + boundary + "--";

			byte[] xmlBytes = new byte[fis.available()];
			fis.read(xmlBytes);

			dos.writeBytes(part1);
			dos.write(xmlBytes);
			dos.writeBytes(part2);

			dos.flush();
			dos.close();
			fis.close();

			if(conn.getResponseCode()!=200){
				return result.toString();
			}
			
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			
			String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
			
			conn.disconnect();  
	              
	        }catch(Exception e){  
	            throw e;
	        }finally{
	        	try{
	                if(dos!=null){
	                	dos.close();
	                }
	                if(in!=null){
	                    in.close();
	                }
	            }
	            catch(IOException ex){
	                ex.printStackTrace();
	            }
	        }
        return result.toString();
    }
}
