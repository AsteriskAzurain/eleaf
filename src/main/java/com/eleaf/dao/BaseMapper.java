package com.eleaf.dao;

import tk.mybatis.mapper.additional.aggregation.AggregationMapper;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * 基本 Mapper，其它所有 Mapper 理应继承本接口
 * <p />
 * <p>
 * 由于继承了 mapper.jar 中的接口，从而可以直接调用 selectAll()，selectOne() 等通用方法而无需配置。
 * </p>
 *
 * @author CTidy
 * 
 * @param <T> 指定实体（JavaBean/POJO），不能为空
 */
public interface BaseMapper<T> extends Mapper<T>, IdsMapper<T>, InsertListMapper<T>, AggregationMapper<T> {

}
