package com.eleaf.dao.mapper;

import org.apache.ibatis.annotations.Param;

import com.eleaf.dao.BaseMapper;
import com.eleaf.entity.Member;

/**
 * 会员 Mapper
 * 
 * @author CTidy
 */
public interface MemberMapper extends BaseMapper<Member> {
	/**
	 * 登录验证
	 * 
	 * @param username 用户名
	 * @param password md5加密后的密码
	 * @return 验证成功则返回相应id（主码），否则返回NULL
	 */
	String authenticate(@Param("username") String username, @Param("password") String password);
}
