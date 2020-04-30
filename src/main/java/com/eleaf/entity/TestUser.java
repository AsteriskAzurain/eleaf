package com.eleaf.entity;

import javax.persistence.Column;

import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 测试用 Bean，以用户为例，项目开发请优先借鉴本类
 * 
 * @author CTidy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "e_test_user")
public class TestUser extends BaseEntity {

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
		return "TestUser { username: " + getUsername() + ", password: " + getPassword() + ", hashCode: "
				+ hashCode() + "}";
	}

}
