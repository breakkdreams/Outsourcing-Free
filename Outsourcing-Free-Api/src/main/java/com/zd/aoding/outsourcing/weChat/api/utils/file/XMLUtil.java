package com.zd.aoding.outsourcing.weChat.api.utils.file;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.dom4j.CDATA;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Text;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * xml 工具类 依赖于 dom4j
 * @author and
 */
public class XMLUtil {
	// 一层解析
	public static Document map2xml(Map<String, Object> map) {
		Document document = DocumentHelper.createDocument();
		Element xml = document.addElement("xml");
		for (String k : map.keySet()) {
			Element node = xml.addElement(k);
			if (!"body".equals(k)) {
				CDATA cdata = DocumentHelper.createCDATA(String.valueOf(map.get(k)));
				node.add(cdata);
			} else {
				String t = String.valueOf(map.get(k));
				CDATA cdata = DocumentHelper.createCDATA(t);
				node.add(cdata);
				// Text text = DocumentHelper.createText(t);
				// node.addText(t );
			}
		}
		return document;
	}
	// 一层解析
	public static Document map2xml2(Map<String, Object> map) {
		Document document = DocumentHelper.createDocument();
		Element xml = document.addElement("xml");
		for (String k : map.keySet()) {
			Element node = xml.addElement(k);
			String t = String.valueOf(map.get(k));
			Text text = DocumentHelper.createText(t);
			node.addText(t);
		}
		return document;
	}
	// 一层解析
	public static Map<String, String> xml2map(Document d) {
		Map<String, String> map = new HashMap<String, String>();
		Element root = d.getRootElement();
		List<Element> list = root.elements();
		for (Element ele : list) {
			String name = ele.getName();
			String text = ele.getText();
			map.put(name, text);
		}
		return map;
	}
	// 一层解析
	public static SortedMap<String, String> xml2smap(Document d) {
		SortedMap<String, String> map = new TreeMap<String, String>();
		Element root = d.getRootElement();
		List<Element> list = root.elements();
		for (Element ele : list) {
			String name = ele.getName();
			String text = ele.getText();
			map.put(name, text);
		}
		return map;
	}
	// 将输入流中的数据解析为xml 文档
	public static Document readXml4InputStream(InputStream is) throws DocumentException {
		Document d = null;
		SAXReader sreader = new SAXReader();
		d = sreader.read(is);
		return d;
	}
	// 将输入流中的数据解析为xml 文档
	public static void writeXml2OutputStream(Document d, OutputStream os, boolean needDeclaration) throws DocumentException, IOException {
		// //直接流处理
		// PrintWriter pw = new PrintWriter(os);
		// pw.print(xml);
		// pw.close();
		// 去除声明
		OutputFormat format = new OutputFormat();
		format.setSuppressDeclaration(needDeclaration); // 是否需要声明
		XMLWriter out = new XMLWriter(os, format);
		out.write(d);
		out.flush();
		out.close();
	}
	public static void main(String[] args) throws Exception {
		// String nonce_str = null;
		// try {
		// nonce_str = WXManager.generateRandom();
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// String appid = WXConf.appid;
		// String mch_id = WXConf.mch_id;
		// String body = "测试数据";
		// String attach = "55ff5ad6acada714685079dc";
		// String out_trade_no="55ff5ad6acada714685079dc";
		// int total_fee = 1;
		// String spbill_create_ip = "196.168.1.1";
		// String notify_url = WXConf.notify_url;
		// String trade_type = WXConf.trade_type;
		// String sign = WXManager.generateSign(appid, mch_id, nonce_str, body,
		// attach, out_trade_no, total_fee, spbill_create_ip, notify_url,
		// trade_type);
		// SortedMap<String, Object> params = new TreeMap<String, Object>();
		// params.put("appid", appid);
		// params.put("mch_id", mch_id);
		// params.put("nonce_str", nonce_str);
		// params.put("body", body);
		// params.put("attach", attach);
		// params.put("out_trade_no", out_trade_no);
		// params.put("total_fee", total_fee);
		// params.put("spbill_create_ip", spbill_create_ip);
		// params.put("notify_url", notify_url);
		// params.put("trade_type", trade_type);
		// params.put("sign", sign);
		// Document d = map2xml(params);
		// String path = "f:/2.xml";
		//// SAXReader saxReader = new SAXReader(false);
		//// d = saxReader.read(new File(path));
		// URL url = new URL("https://api.mch.weixin.qq.com/pay/unifiedorder");
		//// URL url = new URL(WXConf.notify_url);
		// URLConnection conn = url.openConnection();
		// conn.setDoOutput(true);
		// conn.setDoInput(true);
		// OutputStream os = conn.getOutputStream();
		//// OutputFormat format = new OutputFormat();
		//// format.setSuppressDeclaration(true); //注意这句
		//// XMLWriter out = new XMLWriter(os,format);
		// XMLWriter out = new XMLWriter(os);
		// out.write(d);
		// System.out.println("发送参数====");
		// System.out.println(os.toString());
		// out.close();
		// System.out.println("返回结果====");
		// SAXReader sreader = new SAXReader();
		// Document doc = sreader.read(conn.getInputStream());
		// Element root = doc.getRootElement();
		// Element returnCode = root.element("return_code");
		// if("SUCCESS".equals(returnCode.getTextTrim())){
		// Element resultCode = root.element("result_code");
		// if("SUCCESS".equals(resultCode.getTextTrim())){
		// Element prepayId = root.element("prepay_id");
		// System.out.println("预下单成功"+prepayId.getTextTrim());
		// }else{
		// System.out.println("预下单失败");
		// }
		// }else{//失败
		// System.out.println("预下单失败");
		// Element returnMsg = root.element("return_msg");
		// System.out.println(returnMsg.getTextTrim());
		// }
		// System.out.println("=============");
		// BufferedReader reader = new BufferedReader(new
		// InputStreamReader(conn.getInputStream()));
		// String temp = null;
		// while((temp=reader.readLine())!=null){
		// System.out.println(temp);
		// }
		// System.out.println(MD5Util.MD5("中文"));
	}
}
