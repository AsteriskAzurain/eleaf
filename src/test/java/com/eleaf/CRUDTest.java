package com.eleaf;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eleaf.dao.mapper.TestMapper;
import com.eleaf.entity.TestUser;

import lombok.extern.slf4j.Slf4j;

/**
 * 数据库基本操作（增删改查）测试，在数据库连接测试通过后再调试该类
 * 
 * @author CTidy
 */
@Slf4j
public class CRUDTest {
	String confMVC = "spring-mvc.xml"; // SpringMVC 配置
	String confMybatis = "spring-mybatis.xml"; // Mybatis 配置
	ApplicationContext ac = new ClassPathXmlApplicationContext(confMVC, confMybatis);
	TestMapper dao = ac.getBean("testMapper", TestMapper.class);

	@Test
	public void getAll() {
		log.info("尝试查询全部记录……");
		log.info("查询的记录如下：");
		log.info(dao.selectAll().toString());
	}

	@Test
	public void getOne() {
		log.info("尝试查询一条记录……");
		log.info("查询的记录如下：");
		log.info(dao.selectOne(null).toString());
	}

	@Test
	// insertList() 类似，传入装载Bean的List，不再演示
	public void putOne() {
		log.info("尝试保存一条记录……");
		TestUser user = new TestUser();
		// 此处未进行重复值判断，请自行检查
		user.setUsername("Eclipse");
		// 此处未进行MD5码加密处理
		user.setPassword("Hello123");
		dao.insertSelective(user);
		log.info("保存的记录如下：");
		log.info(dao.selectOne(user).toString());
	}

	@Test
	public void setOne() {
		log.info("尝试修改一条记录……");
		TestUser user = new TestUser();
		user.setUsername("Eclipse");
		user = dao.selectOne(user);
		user.setPassword("Java233");
		dao.updateByPrimaryKeySelective(user);
		log.info("修改记录如下：");
		log.info(dao.selectOne(user).toString());
	}

}
