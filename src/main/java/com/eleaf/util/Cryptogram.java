package com.eleaf.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Cryptogram {
	/**
	 * MD5码加密
	 * <p/>
	 * <p>
	 * 摘要算法，将不同长度的字符串加密成 24 字符的密文
	 * </p>
	 * 
	 * @param msg 真值（不限长，任意字符，包括中文）
	 * @return 密文（24 字符）
	 */
	public static String md5(String msg) {
		// 摘要算法，将不同长度的字符串转换为等长的，不可逆
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] input = msg.getBytes(); // input，真值
			byte[] output = md.digest(input); // output，密文
			// 利用 Base64 算法，将 output 转成字符串
			String str = Base64.encodeBase64String(output);
			// String str = new String(output); //容易出现乱码，一般不使用
			return str;
		} catch (NoSuchAlgorithmException e) {
			log.error("md5加密失败!");
		}
		return "";
	}
}
