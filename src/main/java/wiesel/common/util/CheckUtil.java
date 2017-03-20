/*******************************************************************************
 * $Header$
 * $Revision$
 * $Date$
 *
 *==============================================================================
 *
 * Copyright (c) 2001-2016 Bosssoft Co, Ltd.
 * All rights reserved.
 * 
 * Created on 2016年11月4日
 *******************************************************************************/

package wiesel.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * TODO 请求连接校验工具
 *
 * @author wujian (mailto:wujian@bosssoft.com.cn)
 */
public class CheckUtil {
	private static final String token = "wujianwiesel";

	public static boolean checkSingnature(String signature, String timestamp,
			String nonce) {
		// 组装数组
		String[] arr = new String[] { token, timestamp, nonce };

		// 排序
		Arrays.sort(arr);

		StringBuffer content = new StringBuffer();

		// 拼接成字符串
		for (String string : arr) {
			content.append(string);
		}

		// 获取加密后的结果
		String temp = getSha1(content.toString());

		// 校验
		return temp.equals(signature);
	}

	/**
	 * SHA1加密算法
	 * 
	 * @param content
	 * @return
	 */
	private static String getSha1(String content) {
		if (null == content || 0 == content.length()) {
			return null;
		}
		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
			mdTemp.update(content.getBytes("UTF-8"));

			byte[] md = mdTemp.digest();
			int j = md.length;
			char[] buf = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
				buf[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(buf);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;

	}
}

/*
 * 修改历史 $Log$
 */