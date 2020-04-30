package com.eleaf.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.eleaf.dao.BaseMapper;
import com.eleaf.entity.GoodsInfo;

public interface GoodsInfoMapper extends BaseMapper<GoodsInfo> {
	/**
	 * 商品名或商品简介模糊查询
	 * 
	 * @param keywords 搜索关键字
	 * @param appearOnly 如果只想搜索可见，则传入 true
	 * @return
	 */
	List<GoodsInfo> search(@Param("keywords")String keywords, @Param("appearOnly") boolean appearOnly);
}
