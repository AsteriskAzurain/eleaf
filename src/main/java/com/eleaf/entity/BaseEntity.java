package com.eleaf.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.eleaf.util.UUIdGenId;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

/**
 * 基本 Bean
 * <p/>
 * <p>
 * 注意：推荐将所有的基本数据类型替换为对应的封装类，如 int -> Integer，便于空值（NULL）操作。
 * </p>
 * 
 * @author CTidy
 */
@Data
@MappedSuperclass
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * UUID（PRIMARY KEY）
	 */
	@Id
	@KeySql(genId = UUIdGenId.class)
	@Column(name = "id")
	private String id;

	/**
	 * 隐藏状态
	 */
	@Column(name = "hidden_flag")
	private Integer hiddenFlag;

	/**
	 * 记录创建时间
	 */
	@Column(name = "create_time")
	private Timestamp createTime;

	/**
	 * 记录创建者
	 */
	@Column(name = "creator")
	private String creator;

	/**
	 * 记录最后修改时间
	 */
	@Column(name = "modify_time")
	private Timestamp modifyTime;

	/**
	 * 记录最后修改者
	 */
	@Column(name = "modifier")
	private String modifier;

	/**
	 * 备注
	 */
	@Column(name = "memo")
	private String memo;

}
