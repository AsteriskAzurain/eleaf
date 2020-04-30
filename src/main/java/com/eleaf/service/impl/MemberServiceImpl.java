package com.eleaf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eleaf.dao.mapper.MemberMapper;
import com.eleaf.entity.Member;
import com.eleaf.service.MemberService;
import com.eleaf.util.DesUtil;
import com.eleaf.util.UrlUtil;
import com.eleaf.util.verify.Verify;

@Service
public class MemberServiceImpl extends BaseServiceImpl<Member> implements MemberService {
	@Autowired
	private MemberMapper dao;

	@Override
	public String createOne(Member member) {
		// 判空
		if (Verify.isInvalid(member, "username")) {
			return "";
		}
		// 判重
		if (exist("username", member.getUsername())) {
			return "";
		}
		if (dao.insertSelective(member) > 0) {
			return dao.selectOne(member).getId();
		}
		return "";
	}

	@Override
	public String authenticate(Member member) {
		// 判空
		if (Verify.isInvalid(member, "username")) {
			return "";
		}
		return dao.authenticate(member.getUsername(), member.getPassword());
	}

	@Override
	public List<Member> searchByUsername(String keywords) {
		// 判空
		if (Verify.isInvalid(keywords)) {
			return null;
		}
		return dao.searchByUsername(keywords, false);
	}

	@Override
	public boolean updatePassword(Member member) {
		// 判空
		if (Verify.isInvalid(member)) {
			return false;
		}
		if (dao.updatePassword(member.getId(), member.getPassword(), false) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public String sendResetPwd(Member member, String email) {
		// 判空
		if (Verify.isInvalid(member, "username")) {
			return "";
		}
		String id = dao.resetPassword(member.getUsername(), email, true);
		if (Verify.isInvalid(id)) {
			return "";
		}
		long endTimes = System.currentTimeMillis() + 1 * 24 * 3600 * 1000; // 设置重置有效时长为 1 天
		String params = id + ";" + email + ";" + endTimes;
		try {
			String encodeValue = UrlUtil.getURLEncoderString(DesUtil.encrypt(params));
			// TODO 将加密后的URL发送到指定邮箱
			// content = EmailUtil.replace(content, "{EMAIL_SETPWD_ADD2}", "http://localhost:8080/eleaf/toResetPwd?vc="+encode);
			return encodeValue;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	@Override
	public String[] verifyResetPwd(String params) {
		if (Verify.isInvalid(params)) {
			return null;
		}
		try {
			// 此处直接 des 解码
			String decode = DesUtil.decrypt(params);
			String[] list = decode.split(";");
			if (null != list && list.length > 0) {
				long endTimes = Long.parseLong(list[2]);
				long currentTime = System.currentTimeMillis();
				if (endTimes <= currentTime) {
					return null;
				}
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean resetPwd(Member member) {
		// 判空
		if (Verify.isInvalid(member, "id", "password")) {
			return false;
		}
		if (dao.updateByPrimaryKeySelective(member) > 0) {
			return true;
		}
		return false;
	}

}
