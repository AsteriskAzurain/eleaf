package com.eleaf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eleaf.dao.mapper.MemberInfoMapper;
import com.eleaf.entity.MemberInfo;
import com.eleaf.service.MemberInfoService;
import com.eleaf.util.verify.Verify;

@Service
public class MemberInfoServiceImpl extends BaseServiceImpl<MemberInfo> implements MemberInfoService {
	@Autowired
	private MemberInfoMapper dao;
	
	@Override
	public String createOne(MemberInfo memberInfo) {
		// 判空
		if (Verify.isInvalid(memberInfo, "email", "nickname")) {
			return "";
		}
		if (dao.insertSelective(memberInfo) == 0) {
			return "";
		}
		return dao.selectOne(memberInfo).getId();
		
	}
}
