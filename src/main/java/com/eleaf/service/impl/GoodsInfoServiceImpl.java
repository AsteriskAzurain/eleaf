package com.eleaf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eleaf.dao.mapper.GoodsInfoMapper;
import com.eleaf.entity.GoodsInfo;
import com.eleaf.service.GoodsInfoService;
import com.eleaf.util.verify.Verify;

@Service
public class GoodsInfoServiceImpl extends BaseServiceImpl<GoodsInfo> implements GoodsInfoService {
	
	@Autowired
	private GoodsInfoMapper ginfodao;
	
	@Override
	public String createOne(GoodsInfo goods) {
		if (Verify.isInvalid(goods)) {
			return "";
		}
		if (exist(goods.getId())) {
			return "";
		}
		if (exist("goods_name", goods.getGoods_name())) {
			return "";
		}
		if (ginfodao.insertSelective(goods) > 0) {
			return ginfodao.selectOne(goods).getId();
		}
		return "";
	}
	
	@Override
	public List<GoodsInfo> search(String keywords) {
		// 判空
		if (Verify.isInvalid(keywords)) {
			return null;
		}
		return ginfodao.search(keywords, false);
	}

}
