package com.eleaf;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eleaf.entity.TestUser;
import com.eleaf.service.TestService;

import lombok.extern.slf4j.Slf4j;

/**
 * 注册测试
 * 
 * @author CTidy
 */
@Slf4j
public class LogonTest {
	String confMVC = "spring-mvc.xml"; // SpringMVC 配置
	String confMybatis = "spring-mybatis.xml"; // Mybatis 配置
	ApplicationContext ac = new ClassPathXmlApplicationContext(confMVC, confMybatis);
	TestService service = ac.getBean("testServiceImpl", TestService.class);

	@Test
	public void exist() {
		TestUser user = new TestUser();
		user.setUsername("first_test");
		if (service.exist("username", user.getUsername())) {
			log.info("用户 " + user.getUsername() + " 已存在!");
		} else {
			log.info("用户 " + user.getUsername() + " 不存在!");
		}
	}
	
	@Test
	public void failLogon() {
		TestUser user = new TestUser();
		user.setUsername("first_test");
		log.info("New id: " + service.createOne(user).toString());
		user.setPassword("123456");
		log.info("New id: " + service.createOne(user).toString());
	}
	
	@Test
	public void successLogon() {
		// 注意：注册方法不进行MD5加密！
		TestUser user = new TestUser();
		user.setUsername("陈满庭");  // 改成其它名字，否则重名会失败
		user.setPassword("cmt#1214");
		String id = service.createOne(user);
		log.info("New id: " + id);
		log.info(service.getOne(id).toString());
	}

}
