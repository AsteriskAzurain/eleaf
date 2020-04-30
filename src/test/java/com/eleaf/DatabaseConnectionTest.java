package com.eleaf;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eleaf.dao.mapper.TestMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 数据库连接测试，项目开发时请最先调式该类
 * 
 * @author CTidy
 */
@Slf4j
public class DatabaseConnectionTest {
	String confMVC = "spring-mvc.xml"; // SpringMVC 配置
	String confMybatis = "spring-mybatis.xml"; // Mybatis 配置
	ApplicationContext ac = new ClassPathXmlApplicationContext(confMVC, confMybatis);
	TestMapper dao = ac.getBean("testMapper", TestMapper.class);

	@Test
	public void databaseConnectionTest() {
		log.info("尝试连接到数据库……");
		log.info(dao.selectAll().toString());
		log.info("数据库连接成功！");
	}

}
