package com.zd.aoding.outsourcing.weChat.api.utils.file;

import javax.servlet.http.HttpServletRequest;

/**
 * Request工具类
 * @author kong
 */
public class RequestUtil {
	public static String getString(HttpServletRequest request, String name, String defaultValue) {
		if (name == null || "".equals(name)) {
			return defaultValue;
		}
		Object obj = request.getParameter(name);
		if (obj == null || "".equals(obj.toString())) {
			return defaultValue;
		}
		return obj.toString();
	}
	public static String getString(HttpServletRequest request, String name) {
		return getString(request, name, null);
	}
	public static int getInt(HttpServletRequest request, String name, int defaultValue) {
		return Integer.parseInt(getString(request, name, String.valueOf(defaultValue)));
	}
	public static int getInt(HttpServletRequest request, String name) {
		return getInt(request, name, 0);
	}
	public static float getFloat(HttpServletRequest request, String name, float defaultValue) {
		return Float.parseFloat(getString(request, name, String.valueOf(defaultValue)));
	}
	public static float getFloat(HttpServletRequest request, String name) {
		return getFloat(request, name, 0.0f);
	}
	public static double getDouble(HttpServletRequest request, String name, double defaultValue) {
		return Double.parseDouble(getString(request, name, String.valueOf(defaultValue)));
	}
	public static double getDouble(HttpServletRequest request, String name) {
		return getDouble(request, name, 0);
	}
	public static long getLong(HttpServletRequest request, String name, long defaultValue) {
		return Long.parseLong(getString(request, name, String.valueOf(defaultValue)));
	}
	public static long getLong(HttpServletRequest request, String name) {
		return getLong(request, name, 0);
	}
	public static short getShort(HttpServletRequest request, String name, short defaultValue) {
		return Short.parseShort(getString(request, name, String.valueOf(defaultValue)));
	}
	public static short getShort(HttpServletRequest request, String name) {
		return getShort(request, name, (short) 0);
	}
	
	
	public static String getClientIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
