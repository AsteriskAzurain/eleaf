package com.eleaf;

import org.junit.Test;

import com.eleaf.util.Cryptogram;

import lombok.extern.slf4j.Slf4j;

/**
 * MD5加密测试
 * 
 * @author CTidy
 */
@Slf4j
public class CryptogramTest {

	@Test
	public void md5Digest() {
		String passwordTrue = "just$f../\\efefwfd235你sdv~";
		String passwordDigest = Cryptogram.md5(passwordTrue);
		log.info("加密前： " + passwordTrue);
		log.info("加密后： " + passwordDigest);
		log.info("加密后长度： " + passwordDigest.length());
		
		passwordTrue = "我很短233";
		passwordDigest = Cryptogram.md5(passwordTrue);
		log.info("加密前： " + passwordTrue);
		log.info("加密后： " + passwordDigest);
		log.info("加密后长度： " + passwordDigest.length());
	}
}
