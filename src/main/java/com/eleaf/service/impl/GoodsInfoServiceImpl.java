package com.eleaf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eleaf.dao.mapper.GoodsInfoDaoMapper;
import com.eleaf.entity.GoodsInfo;
import com.eleaf.service.GoodsInfoService;

@Service
public class GoodsInfoServiceImpl extends BaseServiceImpl<GoodsInfo> implements GoodsInfoService {
	
	@Autowired
	private GoodsInfoDaoMapper ginfodao;
	
	@Override
	public boolean hideOne(GoodsInfo record) {
		// TODO Auto-generated method stub
		GoodsInfo info=new GoodsInfo();
		info.setId(record.getId());
		info=ginfodao.selectByPrimaryKey(info.getId());
		int rs=-2;
		if(info.getHiddenFlag()==0) {
			rs=0;
		}
		if(info.getHiddenFlag()==1) {
			rs=ginfodao.goodsputDown(info);
		}
		if(rs==-2) return false;
		else {return true;}
	}

	@Override
	public List<GoodsInfo> goodsquerybyName(GoodsInfo record) {
		// TODO Auto-generated method stub
		return ginfodao.goodsquerybyName(record.getGoods_name());
	}

	@Override
	public boolean goodsputOn(GoodsInfo record) {
		// TODO Auto-generated method stub
		GoodsInfo info=new GoodsInfo();
		info.setId(record.getId());
		info=ginfodao.selectByPrimaryKey(info.getId());
		int rs=-2;
		if(info.getHiddenFlag()==0) {
			rs=ginfodao.goodsputOn(info);
		}
		if(info.getHiddenFlag()==1) {
			rs=ginfodao.goodsputDown(info);
		}
		if(rs==-2) return false;
		else {return true;}
	}

}
