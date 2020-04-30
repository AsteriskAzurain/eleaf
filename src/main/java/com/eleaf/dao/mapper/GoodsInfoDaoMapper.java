package com.eleaf.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.eleaf.dao.BaseMapper;
import com.eleaf.entity.GoodsInfo;

public interface GoodsInfoDaoMapper extends BaseMapper<GoodsInfo> {
	
	List<GoodsInfo> goodsquerybyName(@Param("gname") String gname);
	
	int goodsputDown(@Param("info") GoodsInfo info);
	
	GoodsInfo querybyid(@Param("id") String id) ;
	
	int goodsputOn(@Param("info") GoodsInfo info);
}
