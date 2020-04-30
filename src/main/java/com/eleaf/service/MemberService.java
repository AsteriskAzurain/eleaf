package com.eleaf.service;

import java.util.List;

import com.eleaf.entity.Member;

/**
 * 管理员 Service 接口
 * 
 * @author CTidy
 */
public interface MemberService extends BaseService<Member> {
	String authenticate(Member member);
	
	List<Member> searchByUsername(String keywords);
	
	boolean updatePassword(Member member);
	
	String sendResetPwd(Member member, String email);
	
	String[] verifyResetPwd(String params);
	
	boolean resetPwd(Member member);
}
