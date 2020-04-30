package com.eleaf.service;

import java.util.List;

import com.eleaf.entity.GoodsType;

public interface GoodsTypeService extends BaseService<GoodsType> {
	List<GoodsType> search(String keywords);
}
