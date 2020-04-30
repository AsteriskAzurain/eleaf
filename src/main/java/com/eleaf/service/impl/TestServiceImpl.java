package com.eleaf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eleaf.dao.mapper.TestMapper;
import com.eleaf.entity.TestUser;
import com.eleaf.service.TestService;
import com.eleaf.util.verify.Verify;

/**
 * 测试用 Service 实现类，项目开发请优先借鉴本类
 * 
 * @author CTidy
 */
@Service
public class TestServiceImpl extends BaseServiceImpl<TestUser> implements TestService {
	@Autowired
	private TestMapper dao;

	@Override
	public String createOne(TestUser user) {
		// 判空
		if (Verify.isInvalid(user, "username")) {
			return "";
		}
		// 判长
		if (user.getUsername().length() > 20) {
			return "";
		}
		// 判重
		if (exist(user.getId()) || exist("username", user.getUsername())) {
			return "";
		}
		if (dao.insertSelective(user) > 0) {
			return dao.selectOne(user).getId();
		}
		return "";
	}

	@Override
	public String authenticate(TestUser user) {
		// 判空
		if (Verify.isInvalid(user, "username")) {
			return "";
		}
		// 判长
		if (user.getUsername().length() > 20) {
			return "";
		}
		return dao.authenticate(user.getUsername(), user.getPassword());
	}
}
