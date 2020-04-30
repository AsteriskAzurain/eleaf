package com.eleaf.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.eleaf.dao.BaseMapper;
import com.eleaf.entity.GoodsType;

public interface GoodsTypeMapper extends BaseMapper<GoodsType> {
	/**
	 * 类别名模糊查询
	 * 
	 * @param keywords 搜索关键字
	 * @param appearOnly 如果只想搜索可见，则传入 true
	 * @return
	 */
	List<GoodsType> search(@Param("keywords")String keywords, @Param("appearOnly") boolean appearOnly);
}
