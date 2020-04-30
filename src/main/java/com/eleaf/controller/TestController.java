package com.eleaf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.eleaf.entity.DataResponse;
import com.eleaf.entity.TestUser;
import com.eleaf.service.TestService;
import com.eleaf.util.verify.Verify;

/**
 * 测试用 Controller 类，项目开发请优先借鉴本类
 * 
 * @author CTidy
 */
@RestController
@RequestMapping("/test/user")
public class TestController {
	@Autowired
	private TestService testService;

	/**
	 * 获取所有用户数据
	 * 
	 * @return 响应前端的 Json 数据，封装了所有的 TestUser
	 */
	@GetMapping("/getAll")
	private DataResponse getAll() {
		DataResponse response = new DataResponse();
		List<TestUser> result = testService.getAll();
		return response.success(result);
	}

	/**
	 * 用户注册
	 * 
	 * @param username 用户名
	 * @param password 密码
	 * @return 响应前端的 Json 数据，封装了 TestUser.id
	 */
	@PostMapping("/logon")
	private DataResponse logon(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		DataResponse response = new DataResponse();
		TestUser user = new TestUser();
		user.setUsername(username);
		user.setPassword(password);
		String result = testService.createOne(user);
		// TODO isNullOrEmpty()
		if (Verify.isInvalid(result)) {
			return response.error();
		}
		return response.success(result);
	}

	/**
	 * 用户登录
	 * 
	 * @param username 用户名
	 * @param password 密码
	 * @return 响应前端的 Json 数据，封装了 TestUser.id，{TODO TOKEN}
	 */
	@GetMapping("/login")
	@ResponseBody
	private DataResponse login(@RequestParam("username") String username, @RequestParam("password") String password) {
		DataResponse response = new DataResponse();
		TestUser user = new TestUser();
		user.setUsername(username);
		user.setPassword(password);
		String result = testService.authenticate(user);
		if (Verify.isInvalid(result)) {
			return response.error();
		}
		return response.success(result);
	}
}
