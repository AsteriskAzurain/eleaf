package com.eleaf.entity;

import javax.persistence.Column;

import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 管理员
 * 
 * @author CTidy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "e_account_admin")
public class Admin extends BaseEntity {

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
	
	/**
	 * 权限
	 */
	@Column(name = "permission")
	private Integer permission;

	@Override
	public String toString() {
		return "Admin { username: " + getUsername() + ", password: " + getPassword() + ", permission: "
				+ getPermission() + ", hashCode: " + hashCode() + "}";
	}
	
}
