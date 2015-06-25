package com.chinacreator.c2.flow.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CommonUtil {
	public static byte[] inputStreamToByte(InputStream is) throws IOException {
		if(is == null){
			return null;
		}
		ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
		int ch;
		while ((ch = is.read()) != -1) {
			bytestream.write(ch);
		}
		byte imgdata[] = bytestream.toByteArray();
		return imgdata;
	}
	
	public static InputStream byteToInputStream(byte[] bytes){
		if(bytes == null){
			return null;
		}
		InputStream sbs = new ByteArrayInputStream(bytes);
		return sbs;
	}
	
	/**
	 * 判断字符串是否为null或者空、空串、空格。
	 * 
	 * @param str
	 * @return 是：返回true 否：返回false
	 */
	public static boolean stringNullOrEmpty(String str) {
		boolean b = true;
		if (null != str && !"".equals(str.trim())) {
			b = false;
		}
		return b;
	}
}
