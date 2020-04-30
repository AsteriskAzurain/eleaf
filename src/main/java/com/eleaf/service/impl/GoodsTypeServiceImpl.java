package com.eleaf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eleaf.dao.mapper.GoodsTypeMapper;
import com.eleaf.entity.GoodsType;
import com.eleaf.service.GoodsTypeService;
import com.eleaf.util.verify.Verify;

@Service
public class GoodsTypeServiceImpl extends BaseServiceImpl<GoodsType> implements GoodsTypeService {
	
	@Autowired
	private GoodsTypeMapper gtypedao;
	
	@Override
	public String createOne(GoodsType type) {
		if (Verify.isInvalid(type)) {
			return "";
		}
		if (exist(type.getId())) {
			return "";
		}
		if (exist("type_name", type.getType_name())) {
			return "";
		}
		if (gtypedao.insertSelective(type) > 0) {
			return gtypedao.selectOne(type).getId();
		}
		return "";
	}
	
	@Override
	public List<GoodsType> search(String keywords) {
		// 判空
		if (Verify.isInvalid(keywords)) {
			return null;
		}
		return gtypedao.search(keywords, false);
	}

}
