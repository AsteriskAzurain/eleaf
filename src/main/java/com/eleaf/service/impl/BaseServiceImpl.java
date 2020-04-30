package com.eleaf.service.impl;

import java.lang.reflect.ParameterizedType;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.eleaf.dao.BaseMapper;
import com.eleaf.entity.BaseEntity;
import com.eleaf.service.BaseService;
import com.eleaf.util.verify.Verify;

import tk.mybatis.mapper.entity.Example;

/**
 * 基本 Service 实现类，其它所有 Service 接口理应继承本实现类
 *
 * @author CTidy
 * 
 * @param <T> 指定实体（JavaBean/POJO），不能为空
 */
public class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

	@Autowired
	private BaseMapper<T> dao;
	
	@Override
	public List<T> getAll() {
		return dao.selectAll();
	}
	
	@Override
	public List<T> getAll(boolean showHidden) {
		if (showHidden) {
			return getAll();
		}
		// 通过漫长的反射，获取类型参数的类型
		// getGenericSuperclass(), 获取直接超类，包括接口、类型参数等
		// getActualTypeArguments()，获取实际类型参数对应的类型列表
		@SuppressWarnings("unchecked")
		Class<T> entityType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		// 构造 Example 条件
		Example example = new Example(entityType);
		example.createCriteria().andEqualTo("hidden_flag", 0);
		return dao.selectByExample(example);
	}
	
	@Override
	public List<T> getAll(String orderColumn, boolean showHidden) {
		// 通过漫长的反射，获取类型参数的类型
		// getGenericSuperclass(), 获取直接超类，包括接口、类型参数等
		// getActualTypeArguments()，获取实际类型参数对应的类型列表
		@SuppressWarnings("unchecked")
		Class<T> entityType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		// 构造 Example 条件
		Example example = new Example(entityType);
		example.setOrderByClause(orderColumn);
		if (showHidden) {
			example.createCriteria();
		} else {
			example.createCriteria().andEqualTo("hidden_flag", 0);
		}
		return dao.selectByExample(example);
	}
	
	@Override
	public T getOne(T record) {
		return dao.selectOne(record);
	}
	
	@Override
	public T getOne(String id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public String createOne(T record) {
		if (Verify.isInvalid(record)) {
			return "";
		}
		if (exist(record.getId())) {
			return "";
		}
		if (dao.insertSelective(record) > 0) {
			return dao.selectOne(record).getId();
		}
		return "";
	}

	@Override
	public boolean updateOne(T record) {
		if (Verify.isInvalid(record)) {
			return false;
		}
		record.setModifyTime(new Timestamp(System.currentTimeMillis()));
		if (dao.updateByPrimaryKeySelective(record) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean toggleHide(T record) {
		if (Verify.isInvalid(record)) {
			return false;
		}
		if (record.getHiddenFlag() == null) {
			record = getOne(record);
		}
		record.setHiddenFlag(record.getHiddenFlag() == 0 ? 1 : 0);
		return updateOne(record);
	}
	
	@Override
	public boolean appearOne(T record) {
		if (Verify.isInvalid(record)) {
			return false;
		}
		if (record.getHiddenFlag() == null || record.getHiddenFlag() == 1) {
			record.setHiddenFlag(0);
			return updateOne(record);
		}
		return true;
	}
	
	@Override
	public boolean hideOne(T record) {
		if (Verify.isInvalid(record)) {
			return false;
		}
		if (record.getHiddenFlag() == null || record.getHiddenFlag() == 0) {
			record.setHiddenFlag(1);
			return updateOne(record);
		}
		return true;
	}

	@Override
	public boolean hideList(List<T> recordsList) {
		// TODO hideList()，批量隐藏（删除）
		return false;
	}

	@Override
	public boolean exist(String id) {
		return dao.existsWithPrimaryKey(id);
	}

	@Override
	public boolean exist(String filed, String value) {
		// 通过漫长的反射，获取类型参数的类型
		// getGenericSuperclass(), 获取直接超类，包括接口、类型参数等
		// getActualTypeArguments()，获取实际类型参数对应的类型列表
		@SuppressWarnings("unchecked")
		Class<T> entityType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		// 构造 Example 条件
		Example example = new Example(entityType);
		example.createCriteria().andEqualTo(filed, value);
		if (dao.selectCountByExample(example) > 0) {
			return true;
		}
		return false;
	}
}
