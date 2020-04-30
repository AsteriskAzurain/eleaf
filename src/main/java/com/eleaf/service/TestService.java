package com.eleaf.service;

import com.eleaf.entity.TestUser;

/**
 * 测试用 Service 接口，项目开发请优先借鉴本类
 * 
 * @author CTidy
 */
public interface TestService extends BaseService<TestUser> {
	String authenticate(TestUser user);
}
