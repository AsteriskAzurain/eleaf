package com.eleaf.entity;

import javax.persistence.Column;

import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 管理员日志
 * 
 * @author CTidy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "e_account_admin_log")
public class AdminLog extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 账号ID
	 */
	@Column(name = "account_id")
	private String accountId;
	
	/**
	 * 操作
	 */
	@Column(name = "operation")
	private String operation;
	
}
