package com.eleaf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eleaf.dao.mapper.AdminMapper;
import com.eleaf.entity.Admin;
import com.eleaf.service.AdminService;
import com.eleaf.util.verify.Verify;

/**
 * 管理员 Service 实现类
 * 
 * @author CTidy
 */
@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {
	@Autowired
	private AdminMapper dao;

	@Override
	public String createOne(Admin admin) {
		// 判空
		if (Verify.isInvalid(admin, "username")) {
			return "";
		}
		// 判重
		if (exist("username", admin.getUsername())) {
			return "";
		}
		if (dao.insertSelective(admin) > 0) {
			return dao.selectOne(admin).getId();
		}
		return "";
	}

	@Override
	public String authenticate(Admin admin) {
		// 判空
		if (Verify.isInvalid(admin, "username")) {
			return "";
		}
		return dao.authenticate(admin.getUsername(), admin.getPassword());
	}

	@Override
	public List<Admin> searchByUsername(String keywords) {
		// 判空
		if (Verify.isInvalid(keywords)) {
			return null;
		}
		return dao.searchByUsername(keywords, false);
	}
	
	@Override
	public boolean updatePassword(Admin admin) {
		// 判空
		if (Verify.isInvalid(admin)) {
			return false;
		}
		if (dao.updatePassword(admin.getId(), admin.getPassword(), false) > 0) {
			return true;
		}
		return false;
	}
}
