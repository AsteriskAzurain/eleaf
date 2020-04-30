package com.eleaf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eleaf.entity.Admin;
import com.eleaf.entity.DataResponse;
import com.eleaf.service.AdminService;
import com.eleaf.util.verify.Verify;

/**
 * 管理员 Controller 类
 * 
 * @author CTidy
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService service;

	/**
	 * 获取管理员列表
	 * 
	 * @return 响应前端的 Json 数据，封装了所有的 Admin
	 */
	@GetMapping("/getAll")
	private DataResponse getAll() {
		DataResponse response = new DataResponse();
		List<Admin> result = service.getAll("create_time", true);
		return response.success(result);
	}
	
	/**
	 * 根据主键获取管理员
	 * 
	 * @param id 主键
	 * @return 响应前端的 Json 数据，封装了对应主键的 Admin
	 */
	@GetMapping("/getOne")
	private DataResponse getOne(@RequestParam("id") String id) {
		DataResponse response = new DataResponse();
		Admin admin = new Admin();
		admin.setId(id);
		admin = service.getOne(admin);
		return response.success(admin);
	}
	
	/**
	 * 按用户名搜索管理员，模糊查询
	 * 
	 * @param keywords 搜索关键词
	 * @return 响应前端的 Json 数据，封装了符合条件的 Admin
	 */
	@GetMapping("/searchByUsername")
	private DataResponse searchByUsername(String keywords) {
		DataResponse response = new DataResponse();
		List<Admin> result = service.searchByUsername(keywords);
		return response.success(result);
	}

	/**
	 * 添加管理员
	 * 
	 * @param username 用户名
	 * @param password 密码
	 * @return 响应前端的 Json 数据，封装了 Admin.id
	 */
	@PostMapping("/add")
	private DataResponse add(@RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("permission") int permission) {
		DataResponse response = new DataResponse();
		Admin admin = new Admin();
		admin.setUsername(username);
		admin.setPassword(password);
		admin.setPermission(permission);
		String result = service.createOne(admin);
		if (Verify.isInvalid(result)) {
			return response.error("5001", "用户名已存在！");
		}
		return response.success(result);
	}
	
	/**
	 * 管理员修改密码
	 * 
	 * @param id 主键
	 * @param password 新密码
	 * @return 响应前端的 Json 数据，封装空值（Null）
	 */
	@PostMapping("/updatePwd")
	private DataResponse updatePwd(@RequestParam("id") String id, @RequestParam("password") String password) {
		DataResponse response = new DataResponse();
		Admin admin = new Admin();
		admin.setId(id);
		admin.setPassword(password);
		if (service.updatePassword(admin)) {
			return response.success(null);
		}
		return response.error();
	}
	
	/**
	 * 封禁管理员
	 * 
	 * @param id 管理员id
	 * @return 响应前端的 Json 数据，封装空值（Null）
	 */
	@PostMapping("/ban")
	private DataResponse ban(@RequestParam("id") String id) {
		DataResponse response = new DataResponse();
		Admin admin = new Admin();
		admin.setId(id);
		if (service.hideOne(admin)) {
			return response.success(null);
		}
		return response.error();
	}
	
	/**
	 * 解封管理员
	 * 
	 * @param id 管理员id
	 * @return 响应前端的 Json 数据，封装空值（Null）
	 */
	@PostMapping("/unban")
	private DataResponse unban(@RequestParam("id") String id) {
		DataResponse response = new DataResponse();
		Admin admin = new Admin();
		admin.setId(id);
		if (service.appearOne(admin)) {
			return response.success(null);
		}
		return response.error();
	}

	/**
	 * 管理员登录
	 * 
	 * @param username 用户名
	 * @param password 密码
	 * @return 响应前端的 Json 数据，封装了 Admin.id，{TODO TOKEN}
	 */
	@GetMapping("/login")
	private DataResponse login(@RequestParam("username") String username, @RequestParam("password") String password) {
		DataResponse response = new DataResponse();
		Admin admin = new Admin();
		admin.setUsername(username);
		admin.setPassword(password);
		String result = service.authenticate(admin);
		if (Verify.isInvalid(result)) {
			return response.error("5002", "用户名或密码错误！");
		}
		return response.success(result);
	}
}
