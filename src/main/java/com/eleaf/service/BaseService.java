package com.eleaf.service;

import java.util.List;

import com.eleaf.entity.BaseEntity;

/**
 * 基本 Service 接口，其它所有 Service 接口理应继承本接口
 *
 * @author CTidy
 * 
 * @param <T> 指定实体（JavaBean/POJO），不能为空
 */
public interface BaseService<T extends BaseEntity> {
	/**
	 * 查询全部，按默认主码排序
	 * 
	 * @return
	 */
	List<T> getAll();
	
	/**
	 * 查询全部
	 * 
	 * @param showHidden 是否显示隐藏字段
	 * @return 记录列表
	 */
	List<T> getAll(boolean showHidden);
	
	/**
	 * 查询全部
	 * 
	 * @param orderColumn 排序字段
	 * @param showHidden 是否显示隐藏字段
	 * @return 记录列表
	 */
	List<T> getAll(String orderColumn, boolean showHidden);
	
	/**
	 * 根据实体属性获取相应记录
	 * 
	 * @param record 赋有部分属性的记录
	 * @return 完整记录
	 */
	T getOne(T record);
	
	/**
	 * 根据主键查询指定记录
	 * 
	 * @param id 主键
	 * @return 指定记录
	 */
	T getOne(String id);
	
	/**
	 * 新增（插入）一条记录
	 * 
	 * @param record 新记录
	 * @return 新记录的主键
	 */
	String createOne(T record);

	// List<String> createAll(List<T> list);

	/**
	 * 更新指定记录
	 * 
	 * @param record 指定记录
	 * @return {@code true} 更新成功<br/>
	 *         {@code false} 更新失败
	 */
	boolean updateOne(T record);

	/**
	 * 切换隐藏状态（hide <--> appear）
	 * 
	 * @param record 指定记录
	 * @return {@code true} 切换成功<br/>
	 *         {@code false} 切换失败
	 */
	boolean toggleHide(T record);
	
	/**
	 * 显示指定记录，与 hideOne() 方法相反
	 * 
	 * @param record 指定记录
	 * @return {@code true} 显示成功<br/>
	 *         {@code false} 显示失败
	 */
	boolean appearOne(T record);
	
	/**
	 * 隐藏（逻辑删除）指定记录
	 * 
	 * @param record 指定记录
	 * @return {@code true} 隐藏成功<br/>
	 *         {@code false} 隐藏失败
	 */
	boolean hideOne(T record);

	/**
	 * 隐藏（逻辑删除）多条记录
	 * 
	 * @param recordsList 记录列表
	 * @return {@code true} 隐藏成功<br/>
	 *         {@code false} 隐藏失败
	 */
	boolean hideList(List<T> recordsList);

	/**
	 * 根据主键判断指定记录是否存在
	 * 
	 * @param id 主键
	 * @return {@code true} 记录已存在<br/>
	 *         {@code false} 记录不存在
	 */
	boolean exist(String id);
	
	boolean exist(String columnName, String value);
}
