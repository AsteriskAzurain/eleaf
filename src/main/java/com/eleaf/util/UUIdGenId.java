package com.eleaf.util;

import java.util.UUID;

import tk.mybatis.mapper.genid.GenId;

/**
 * 主键生成策略：UUID
 * 
 * @author CTidy
 */
public class UUIdGenId implements GenId<String> {

	@Override
	public String genId(String table, String column) {
		return UUID.randomUUID().toString();
	}

}
