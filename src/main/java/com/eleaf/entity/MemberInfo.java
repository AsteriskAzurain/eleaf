package com.eleaf.entity;

import java.sql.Timestamp;

import javax.persistence.Column;

import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 会员信息
 * 
 * @author CTidy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "e_account_member_info")
public class MemberInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 昵称
	 */
	@Column(name = "nickname")
	private String nickname;

	/**
	 * 真实姓名
	 */
	@Column(name = "real_name")
	private String realName;

	/**
	 * 性别
	 * 男，女，{空}
	 */
	@Column(name = "gender")
	private String gender;

	/**
	 * 出生日期
	 */
	@Column(name = "birthday")
	private Timestamp birthday;

	/**
	 * 身份证
	 */
	@Column(name = "identity_card_num")
	private String identityCardNum;

	/**
	 * 电子邮箱
	 */
	@Column(name = "email")
	private String email;

	/**
	 * 手机
	 */
	@Column(name = "phone")
	private String phone;

	/**
	 * QQ
	 */
	@Column(name = "qq")
	private String qq;

	/**
	 * 微信
	 */
	@Column(name = "wechat")
	private String wechat;

	/**
	 * 家庭住址
	 */
	@Column(name = "address")
	private String address;

	/**
	 * 头像
	 */
	@Column(name = "profile_photo")
	private String profilePhoto;

	/**
	 * 个人简介
	 */
	@Column(name = "profile")
	private String profile;

}
