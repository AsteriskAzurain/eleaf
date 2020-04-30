package com.eleaf;

import org.junit.Test;

import com.eleaf.entity.TestUser;
import com.eleaf.util.verify.Verify;

/**
 * 判空全家桶(?)测试
 * 
 * @author CTidy
 */
public class VerifyTest {

	@Test
	public void isNotFull() {
		TestUser user = new TestUser();
		user.setUsername("48484");
		user.setPassword("123456");
		try {
			System.out.println(Verify.isNotFull(user, "username"));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
