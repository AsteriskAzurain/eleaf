package com.eleaf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eleaf.entity.DataResponse;
import com.eleaf.entity.Member;
import com.eleaf.entity.MemberInfo;
import com.eleaf.service.MemberInfoService;
import com.eleaf.service.MemberService;
import com.eleaf.util.verify.Verify;

/**
 * 管理员 Controller 类
 * 
 * @author CTidy
 */
@RestController
@RequestMapping("/member")
public class MemberController {
	@Autowired
	private MemberService service;
	@Autowired
	private MemberInfoService subService;

	/**
	 * 获取会员列表
	 * 
	 * @return 响应前端的 Json 数据，封装了所有的 Member
	 */
	@GetMapping("/getAll")
	private DataResponse getAll() {
		DataResponse response = new DataResponse();
		List<Member> result = service.getAll("create_time", true);
		return response.success(result);
	}

	/**
	 * 根据主键获取会员
	 * 
	 * @param id 主键
	 * @return 响应前端的 Json 数据，封装了对应主键的 Member
	 */
	@GetMapping("/getOne")
	private DataResponse getOne(@RequestParam("id") String id) {
		DataResponse response = new DataResponse();
		Member member = new Member();
		member.setId(id);
		member = service.getOne(member);
		return response.success(member);
	}

	/**
	 * 按用户名搜索会员，模糊查询
	 * 
	 * @param keywords 搜索关键词
	 * @return 响应前端的 Json 数据，封装了符合条件的 Member
	 */
	@GetMapping("/searchByUsername")
	private DataResponse searchByUsername(String keywords) {
		DataResponse response = new DataResponse();
		List<Member> result = service.searchByUsername(keywords);
		return response.success(result);
	}

	/**
	 * 注册
	 * 
	 * @param username 用户名
	 * @param password 密码
	 * @return 响应前端的 Json 数据，封装了 Member.id
	 */
	@PostMapping("/logon")
	private DataResponse logon(@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("email") String email, @RequestParam("nickname") String nickname) {
		DataResponse response = new DataResponse();
		Member member = new Member();
		member.setUsername(username);
		member.setPassword(password);
		String id = service.createOne(member);
		if (Verify.isInvalid(id)) {
			return response.error("4001", "用户名已存在！");
		}
		MemberInfo memberInfo = new MemberInfo();
		memberInfo.setId(id);
		memberInfo.setEmail(email);
		memberInfo.setNickname(nickname);
		subService.createOne(memberInfo);
		return response.success(id);
	}

	/**
	 * 修改密码
	 * 
	 * @param id       会员id
	 * @param password 新密码
	 * @return 响应前端的 Json 数据，封装空值（Null）
	 */
	@PostMapping("/updatePwd")
	private DataResponse updatePwd(@RequestParam("id") String id, @RequestParam("password") String password) {
		DataResponse response = new DataResponse();
		Member member = new Member();
		member.setId(id);
		member.setPassword(password);
		if (service.updatePassword(member)) {
			return response.success();
		}
		return response.error();
	}

	/**
	 * 封禁会员
	 * 
	 * @param id 会员id
	 * @return 响应前端的 Json 数据，封装空值（Null）
	 */
	@PostMapping("/ban")
	private DataResponse ban(@RequestParam("id") String id) {
		DataResponse response = new DataResponse();
		Member member = new Member();
		member.setId(id);
		if (service.hideOne(member)) {
			return response.success();
		}
		return response.error();
	}

	/**
	 * 解封会员
	 * 
	 * @param id 会员id
	 * @return 响应前端的 Json 数据，封装空值（Null）
	 */
	@PostMapping("/unban")
	private DataResponse unban(@RequestParam("id") String id) {
		DataResponse response = new DataResponse();
		Member member = new Member();
		member.setId(id);
		if (service.appearOne(member)) {
			return response.success();
		}
		return response.error();
	}

	/**
	 * 会员登录
	 * 
	 * @param username 用户名
	 * @param password 密码
	 * @return 响应前端的 Json 数据，封装了 Admin.id，{TODO TOKEN}
	 */
	@GetMapping("/login")
	private DataResponse login(@RequestParam("username") String username, @RequestParam("password") String password) {
		DataResponse response = new DataResponse();
		Member member = new Member();
		member.setUsername(username);
		member.setPassword(password);
		String result = service.authenticate(member);
		if (Verify.isInvalid(result)) {
			return response.error("4002", "用户名或密码错误！");
		}
		return response.success(result);
	}
	
	@GetMapping("/exist")
	private DataResponse exist(@RequestParam("username") String username) {
		return new DataResponse().success(service.exist("username", username));
	}
	
	@GetMapping("/sendResetPwd")
	private DataResponse sendResetPwd(@RequestParam("username") String username, @RequestParam("email") String email) {
		DataResponse response = new DataResponse();
		Member member = new Member();
		member.setUsername(username);
		String encodeValue = service.sendResetPwd(member, email);
		if (Verify.isInvalid(encodeValue)) {
			return response.error();
		}
		return response.success(encodeValue);
	}
	
	@GetMapping("/verifyResetPwd")
	private DataResponse verifyResetPwd(@RequestParam("vc") String vc) {
		DataResponse response = new DataResponse();
		String[] list = service.verifyResetPwd(vc);
		if (null == list || list.length == 0) {
			return response.error("4500", "当前链接已失效，请重新申请重置！");
		}
		return response.success(list);
	}
	
	@PostMapping("/resetPwd")
	private DataResponse resetPwd(@RequestParam("id") String id, @RequestParam("password") String password) {
		DataResponse response = new DataResponse();
		Member member = new Member();
		member.setId(id);
		member.setPassword(password);
		if (service.resetPwd(member)) {
			return response.success();
		}
		return response.error();
	}
}
