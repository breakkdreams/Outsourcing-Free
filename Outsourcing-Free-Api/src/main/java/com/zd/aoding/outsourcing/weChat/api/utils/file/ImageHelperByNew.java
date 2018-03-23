package com.zd.aoding.outsourcing.weChat.api.utils.file;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.sun.media.imageio.plugins.tiff.TIFFTag;
import com.sun.media.jai.codec.TIFFEncodeParam;
import com.sun.media.jai.codec.TIFFField;
import com.sun.media.jai.codecimpl.TIFFImageEncoder;
import com.zd.aoding.outsourcing.weChat.api.bean.dataObject.ImageFileDO;

public class ImageHelperByNew {
	public static Map<String, String> contentTypes;
	static{
		contentTypes = new HashMap<String, String>();
		contentTypes.put("jpg", "image/jpeg");
		contentTypes.put("jpeg", "image/jpeg");
		contentTypes.put("jpe", "application/x-jpe");
		contentTypes.put("png", "image/png");
		contentTypes.put("gif", "image/gif");
		contentTypes.put("png", "application/octet-stream");
	}
	
	/**
	 * 获取文件类型 .后缀
	 * @param name
	 * @return
	 */
	public static String getEndFileName(ImageFileDO image){
		String result = null;
		if(image.getFileName()!=null&&image.getFileName().contains(".")){
			String[] ns = image.getFileName().split("\\.");
			result = ns[ns.length-1];
		}
		if(result==null){
			if(image.getFileContentType()!=null){
				String type = image.getFileContentType().toUpperCase();
				if(type.indexOf("JPEG")>0){
					result = "jpeg";
				}
				if(type.indexOf("JPG")>0){
					result = "jpg";
				}
				if(type.indexOf("PNG")>0){
					result = "png";
				}
				if(type.indexOf("GIF")>0){
					result = "gif";
				}
				if(type.indexOf("JPE")>0){
					result = "jpeg";
				}
				if(type.indexOf("OCTET-STREAM")>0){
					result = "png";
				}
			}
		}
		return result==null?null:result.toLowerCase();
	}
	
	/**
	 * 获取图片类型
	 * @param name
	 * @return
	 */
	public static String getContentTypeByName(String name){
		String result = null;
		if(name!=null&&name.contains(".")){
			String[] ns = name.split("\\.");
			String key = ns[ns.length-1];
			result  = contentTypes.get(key);
		}
		return result;
	}
	/**
	 * 获取图片宽高
	 * @param file
	 * @return
	 * @throws IOException 
	 */
	public static int[] getWH(ImageFileDO imagef){
		int w=0,h=0;
		BufferedImage bi = null; 
        try {
			bi = ImageIO.read(imagef.getFile());
			w = bi.getWidth();
			h = bi.getHeight();
			imagef.setWidth(w);
			imagef.setHeight(h);
		} catch (Exception e) {
			try {
				ThumbnailConvertByNew tc = new ThumbnailConvertByNew();
				tc.setCMYK_COMMAND(imagef.getFile().getPath());
				Image image =null;
				image = Toolkit.getDefaultToolkit().getImage(imagef.getFile().getPath());
				MediaTracker mediaTracker = new MediaTracker(new Container());
				mediaTracker.addImage(image, 0);
				mediaTracker.waitForID(0);
				w= image.getWidth(null);
				h= image.getHeight(null);
				imagef.setWidth(w);
				imagef.setHeight(h);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			} 
		}
		return new int[]{w,h};
	}
	
	
	/**
	 * 裁剪图片
	 * @param file
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 * @throws IOException
	 */
	public static File generateImageWithCut(ImageFileDO image,int x1,int y1,int x2,int y2) throws IOException{
		File result = File.createTempFile("temp_", "_temp");
		String fileType = getEndFileName(image);
		int w=0,h=0;
		BufferedImage bsrc = null; 
        bsrc = ImageIO.read(image.getFile());
        w = bsrc.getWidth();
        h = bsrc.getHeight();
        int tw = x2 - x1;
        int th = y2 - y1;
        if(tw>w||th>h){
        	throw new IOException("尺寸不合法");
        }
        BufferedImage btar = null;
        btar = new BufferedImage(tw, th, bsrc.getType());
        Graphics gtar =  btar.getGraphics();
		gtar.drawImage(bsrc, 0, 0, tw, th, x1, y1, x2, y2, null);
        ImageIO.write(btar, fileType, result); 
		return result;	
	}
	
	/**
	 * 生成 自定义宽高图片,缩略图 
	 * @param file 原图片文件
	 * @param tw	自定义宽
	 * @param th	自定义高
	 * @return
	 * @throws IOException 
	 */
	public static File generateImageWithWH(ImageFileDO image,int tw,int th) throws IOException{
		File result = File.createTempFile("temp_", "_temp");
		String fileType = getEndFileName(image);
		int w=0,h=0;
		BufferedImage bsrc = null; 
		Image src=Toolkit.getDefaultToolkit().createImage(image.getFile().getPath()) ;
		bsrc =  toBufferedImage(src);
        w = bsrc.getWidth();
        h = bsrc.getHeight();
        BufferedImage btar = null;
        btar = new BufferedImage(tw, th, bsrc.getType());
        Graphics2D gtar =  btar.createGraphics();
        Color bgColor = new Color(255, 255, 255);
        gtar.setBackground(bgColor);
        gtar.setColor(bgColor);
        gtar.fillRect(0, 0, tw, th);
        if(w/(h*1.0)==tw/(th*1.0)){//转换比例 相同 1:1
			gtar.drawImage(bsrc, 0, 0, tw, th, 0, 0, w, h,  null);
        }else{
        	if(w>=tw&&h>=th){
        		if(w>h){//tw,nh
        			int n = w/tw;
        			int nh = h/n;
            		int sh = (th-nh)/2<0?0:(th-nh)/2;
        			gtar.drawImage(bsrc, 0, sh, tw, th-sh, 0, 0, w, h,  null);
        		}else{
        			int n = h/th;
        			int nw = w/n;
        			int sw = (tw-nw)/2<0?0:(tw-nw)/2;
        			gtar.drawImage(bsrc, sw,  0,  tw-sw, th, 0, 0, w, h,  null);
        		}
        	}else if(w>=tw&&h<=th){
        		int n = w/tw;
    			int nh = h/n;
    			int sh = (th-nh)/2<0?0:(th-nh)/2;
    			gtar.drawImage(bsrc, 0, sh, tw, th-sh, 0, 0, w, h,  null);
        	}else if(w<=tw&&h>=th){
        		int n = h/th;
    			int nw = w/n;
    			int sw = (tw-nw)/2<0?0:(tw-nw)/2;
    			gtar.drawImage(bsrc, sw,  0,  tw-sw, th, 0, 0, w, h,  null);
        	}else{
        		int sw = (tw-w)/2<0?0:(tw-w)/2;
        		int sh = (th-h)/2<0?0:(th-h)/2;
    			gtar.drawImage(bsrc, sw,  sh,  tw-sw, th-sh, 0, 0, w, h,  null);
        	}
        }
        ImageIO.write(btar, fileType, result); 
		return result;	
	}
	
	
	
	public static BufferedImage toBufferedImage(Image image) {
		if (image instanceof BufferedImage) {
			return (BufferedImage) image;
		}
		// This code ensures that all the pixels in the image are loaded
		image = new ImageIcon(image).getImage();
		BufferedImage bimage = null;
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		try {
			int transparency = Transparency.OPAQUE;
			GraphicsDevice gs = ge.getDefaultScreenDevice();
			GraphicsConfiguration gc = gs.getDefaultConfiguration();
			bimage = gc.createCompatibleImage(image.getWidth(null),
					image.getHeight(null), transparency);
		} catch (HeadlessException e) {
			// The system does not have a screen
		}
		if (bimage == null) {
			// Create a buffered image using the default color model
			int type = BufferedImage.TYPE_INT_RGB;
			bimage = new BufferedImage(image.getWidth(null),
					image.getHeight(null), type);
		}
		// Copy image to buffered image
		Graphics g = bimage.createGraphics();
		// Paint the image onto the buffered image
		g.drawImage(image, 0, 0, null);
		g.dispose();
		return bimage;
	}

	
	
	public static void transform(ImageFileDO image,int rotation) throws IOException{
		String fileType = (ImageHelperByNew.getEndFileName(image)!=null?ImageHelperByNew.getEndFileName(image):"jpeg");
		BufferedImage src = ImageIO.read(image.getFile());  
        BufferedImage des = Rotate(src, rotation);  
        ImageIO.write(des, fileType, image.getFile());
	}
    
    public static BufferedImage Rotate(Image src, int angel) {  
        int src_width = src.getWidth(null);  
        int src_height = src.getHeight(null);  
        // calculate the new image size  
        Rectangle rect_des = CalcRotatedSize(new Rectangle(new Dimension(src_width, src_height)), angel);  
  
        BufferedImage res = null;  
        res = new BufferedImage(rect_des.width, rect_des.height,  
                BufferedImage.TYPE_INT_RGB);  
        Graphics2D g2 = res.createGraphics();  
        // transform  
        g2.translate((rect_des.width - src_width) / 2,  
                (rect_des.height - src_height) / 2);  
        g2.rotate(Math.toRadians(angel), src_width / 2, src_height / 2);  
  
        g2.drawImage(src, null, null);  
        return res;  
    }  
  
    public static Rectangle CalcRotatedSize(Rectangle src, int angel) {  
        // if angel is greater than 90 degree, we need to do some conversion  
        if (angel >= 90) {  
            if(angel / 90 % 2 == 1){  
                int temp = src.height;  
                src.height = src.width;  
                src.width = temp;  
            }  
            angel = angel % 90;  
        }  
  
        double r = Math.sqrt(src.height * src.height + src.width * src.width) / 2;  
        double len = 2 * Math.sin(Math.toRadians(angel) / 2) * r;  
        double angel_alpha = (Math.PI - Math.toRadians(angel)) / 2;  
        double angel_dalta_width = Math.atan((double) src.height / src.width);  
        double angel_dalta_height = Math.atan((double) src.width / src.height);  
  
        int len_dalta_width = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_width));  
        int len_dalta_height = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_height));  
        int des_width = src.width + len_dalta_width * 2;  
        int des_height = src.height + len_dalta_height * 2;  
        return new java.awt.Rectangle(new Dimension(des_width, des_height));  
    }  
    
	public static int getRotation(ImageFileDO image){
		int rotation = 0;
		try {
			Metadata metadata = null;
			metadata = ImageMetadataReader.readMetadata(image.getFile());
			if(metadata==null){
				return rotation;
			}
			Directory _directory = metadata.getDirectory((ExifIFD0Directory.class));  
			if (_directory!=null&&_directory.containsTag(ExifIFD0Directory.TAG_ORIENTATION)){
			    int orientation = _directory.getInt(ExifIFD0Directory.TAG_ORIENTATION);  
			    switch (orientation) {  
			        case 1: // "Top, left side (Horizontal / normal)"; 
			        	System.out.println("Top, left side (Horizontal / normal)");
			        	break;
			        case 2: // "Top, right side (Mirror horizontal)";  
			        	System.out.println("Top, right side (Mirror horizontal)");
			        	break;
			        case 3: // "Bottom, right side (Rotate 180)";
			        	System.out.println("Bottom, right side (Rotate 180)");
			        	rotation = 180;
			        	break;
			        case 4: // "Bottom, left side (Mirror vertical)";  
			        	System.out.println("Bottom, left side (Mirror vertical)");
			        	break;
			        case 5: // "Left side, top (Mirror horizontal and rotate 270 CW)";  
			        	System.out.println("Left side, top (Mirror horizontal and rotate 270 CW)");
			        	rotation = 270;
			        	break;
			        case 6: // "Right side, top (Rotate 90 CW)";  
			        	System.out.println("Right side, top (Rotate 90 CW)");
			        	rotation = 90;
			        	break;
			        case 7: // "Right side, bottom (Mirror horizontal and rotate 90 CW)"; 
			        	System.out.println("Right side, bottom (Mirror horizontal and rotate 90 CW)");
			        	rotation = 90;
			        	break;
			        case 8: // "Left side, bottom (Rotate 270 CW)";  
			        	System.out.println("Left side, bottom (Rotate 270 CW)");
			        	rotation = 270;
			        	break;
			        default:  
			        	System.out.println("no orientation of value : "+orientation);
			        	break;
			    }
			}else{
				System.out.println("no exif info");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rotation;
	}
	
    public static int TiffOutput(ImageFileDO imageNew) {  
        try {  
        	if(imageNew==null||imageNew.getFile()==null){
        		return 0;
        	}
        	Image src=Toolkit.getDefaultToolkit().createImage(imageNew.getFile().getPath()) ;
        	RenderedImage image = toBufferedImage(src);
            if (image != null) {  
                TIFFEncodeParam param = new TIFFEncodeParam();  
                param.setCompression(TIFFEncodeParam.COMPRESSION_NONE);  
                TIFFField[] extras = new TIFFField[2];  
                extras[0] = new TIFFField(282, TIFFTag.TIFF_RATIONAL, 1, new long[][]{{300, 1}, {0, 0}});  
                extras[1] = new TIFFField(283, TIFFTag.TIFF_RATIONAL, 1, new long[][]{{300, 1}, {0, 0}});  
                param.setExtraFields(extras);  
                FileOutputStream outputStream = new FileOutputStream(imageNew.getFile());  
                TIFFImageEncoder encoder = new TIFFImageEncoder(outputStream, param);  
                encoder.encode(image);  
                outputStream.close();  
                return 1;
            }  
        } catch (IOException ex) {  
            ex.printStackTrace();  
        }  
        return 0;
    } 
    
    public static double getMultiple(ImageFileDO image,int needtw,int needth){
    	if(image==null){
    		return 1;
    	}
    	if(image.getWidth()==null||image.getHeight()==null){
    		ImageHelperByNew.getWH(image);
    	}
//    	double temp =1;
    	double temp_1 =1;
    	try {
    		DecimalFormat df = new DecimalFormat("0.0");
//    		temp = Double.parseDouble(df.format((needtw/(image.getWidth()*1.0))))+0.1;
        	temp_1 = Double.parseDouble(df.format((needth/(image.getHeight()*1.0))))+0.1;
		} catch (Exception e) {
			// TODO: handle exception
		}
//    	return temp>=temp_1?temp:temp_1;
    	return temp_1;
    }
}
