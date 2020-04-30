package com.eleaf.service;

import java.util.List;

import com.eleaf.entity.GoodsInfo;

public interface GoodsInfoService extends BaseService<GoodsInfo> {
	/*
	 * Dao
	 * queryall:selectAll() --return List<GoodsInfo>
	 * create:insertSelective(bean) --return int
	 * delete:(1)deleteByPrimaryKey(id) --return int
	 * 		  (2)goodsputDown(entity) --return int
	 * update:updateByPrimaryKeySelective(bean) --return int
	 * retrieve:(1)selectByPrimaryKey(id) --return GoodsInfo
	 * 			(2)goodsquerybyName(goodsname) --return List<GoodsInfo>
	 * */
	
	/*
	 * Service
	 * queryall:getAll() --return List<T>
	 * create:createOne(T record) --return String:""/id
	 * delete/putdown:hideOne(GoodsInfo record) //overwritten --return boolean
	 * update:updateOne(T record) --return boolean
	 * retrieve:(1)getOne(String id) --return GoodsInfo
	 * 			(2)goodsquerybyName(GoodsInfo record) --return List<GoodsInfo>
	 * */
	public List<GoodsInfo> goodsquerybyName(GoodsInfo record);
	
	public boolean goodsputOn(GoodsInfo record);
}
