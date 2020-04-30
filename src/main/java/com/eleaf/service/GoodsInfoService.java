package com.eleaf.service;

import java.util.List;

import com.eleaf.entity.GoodsInfo;

public interface GoodsInfoService extends BaseService<GoodsInfo> {
	List<GoodsInfo> search(String keywords);
}
