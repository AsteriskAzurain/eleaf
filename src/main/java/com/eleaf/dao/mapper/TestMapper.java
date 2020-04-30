package com.eleaf.dao.mapper;

import org.apache.ibatis.annotations.Param;

import com.eleaf.dao.BaseMapper;
import com.eleaf.entity.TestUser;

/**
 * 测试用 Mapper，项目开发请优先借鉴本类
 * 
 * @author CTidy
 */
public interface TestMapper extends BaseMapper<TestUser> {
	/**
	 * 登录验证
	 * 
	 * @param username 用户名
	 * @param password md5加密后的密码
	 * @return 验证成功则返回相应id（主码），否则返回NULL
	 */
	String authenticate(@Param("username") String username, @Param("password") String password);
}
