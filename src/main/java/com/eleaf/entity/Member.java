package com.eleaf.entity;

import javax.persistence.Column;

import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 会员
 * 
 * @author CTidy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "e_account_member")
public class Member extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户名
	 */
	@Column(name = "username")
	private String username;

	/**
	 * 密码
	 */
	@Column(name = "password")
	private String password;

	@Override
	public String toString() {
		return "Member { username: " + getUsername() + ", password: " + getPassword() + ", hashCode: "
				+ hashCode() + "}";
	}

}
