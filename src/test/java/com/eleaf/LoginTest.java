package com.eleaf;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eleaf.entity.TestUser;
import com.eleaf.service.TestService;
import com.eleaf.util.verify.Verify;

import lombok.extern.slf4j.Slf4j;

/**
 * 登录测试
 * 
 * @author CTidy
 */
@Slf4j
public class LoginTest {
	String confMVC = "spring-mvc.xml"; // SpringMVC 配置
	String confMybatis = "spring-mybatis.xml"; // Mybatis 配置
	ApplicationContext ac = new ClassPathXmlApplicationContext(confMVC, confMybatis);
	TestService service = ac.getBean("testServiceImpl", TestService.class);

	@Test
	public void authenticate() {
		TestUser user = new TestUser();
		
		user.setUsername("first_test");
		if (Verify.isInvalid(service.authenticate(user))) {
			log.info("第1次：登录失败！");
		} else {
			log.info("第1次：登陆成功");
		}
		
		user.setPassword("123456");
		if (Verify.isInvalid(service.authenticate(user))) {
			log.info("第2次：登录失败！");
		} else {
			log.info("第2次：登陆成功");
		}
		
		user.setPassword("root#123456");
		if (Verify.isInvalid(service.authenticate(user))) {
			log.info("第3次：登录失败！");
		} else {
			log.info("第3次：登陆成功");
		}
	}

}
