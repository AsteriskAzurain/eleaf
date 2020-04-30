package com.eleaf.service;

import com.eleaf.entity.Member;

/**
 * 管理员 Service 接口
 * 
 * @author CTidy
 */
public interface MemberService extends BaseService<Member> {
	String authenticate(Member member);
}
