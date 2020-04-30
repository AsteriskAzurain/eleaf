package com.eleaf.service;

import java.util.List;

import com.eleaf.entity.Admin;

/**
 * 管理员 Service 接口
 * 
 * @author CTidy
 */
public interface AdminService extends BaseService<Admin> {
	String authenticate(Admin admin);
	
	List<Admin> searchByUsername(String keywords);
	
	boolean updatePassword(Admin admin);
}
