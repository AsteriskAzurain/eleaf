package com.eleaf.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.eleaf.dao.BaseMapper;
import com.eleaf.entity.Admin;

/**
 * 管理员 Mapper
 * 
 * @author CTidy
 */
public interface AdminMapper extends BaseMapper<Admin> {
	/**
	 * 登录验证
	 * 
	 * @param username 用户名
	 * @param password md5加密后的密码
	 * @return 验证成功则返回相应id（主码），否则返回NULL
	 */
	String authenticate(@Param("username") String username, @Param("password") String password);
	
	/**
	 * 用户名模糊查询
	 * 
	 * @param keywords 搜索关键词
	 * @param appearOnly 如果只想搜索可见，则传入 true
	 * @return
	 */
	List<Admin> searchByUsername(@Param("keywords") String keywords, @Param("appearOnly") boolean appearOnly);
	
	int updatePassword(@Param("id") String id, @Param("password") String password, @Param("appearOnly") boolean appearOnly);
}
