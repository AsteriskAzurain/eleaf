package com.eleaf;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eleaf.dao.mapper.GoodsInfoDaoMapper;
import com.eleaf.entity.GoodsInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GInfoTest {
	String confMVC = "spring-mvc.xml"; // SpringMVC 配置
	String confMybatis = "spring-mybatis.xml"; // Mybatis 配置
	ApplicationContext ac = new ClassPathXmlApplicationContext(confMVC, confMybatis);
	GoodsInfoDaoMapper ginfodao=ac.getBean("goodsInfoDaoMapper", GoodsInfoDaoMapper.class);
	String fbw="testfbw";
	String type_id="type1";
	Date date= new Date();
	
	/*
	 * queryall:selectAll() --return List<GoodsInfo>
	 * create:insertSelective(bean) --return int
	 * delete:deleteByPrimaryKey(id) --return int
	 * update:updateByPrimaryKeySelective(bean) --return int
	 * retrieve:(1)selectByPrimaryKey(id) --return GoodsInfo
	 * 			(2)goodsquerybyName(goodsname) --return List<GoodsInfo>
	 * */
	
	
	public void getAll() {
		log.info("尝试查询全部记录……");
		log.info("查询的记录如下：");
		log.info(ginfodao.selectAll().toString());
		log.info(ginfodao.selectAll().get(1).getId());
		//log.info(ginfodao.goodsqueryall().get(0).getId());
	}

	public void getOne() {
		log.info("尝试查询一条记录……");
		log.info("查询的记录如下：");
		//log.info(ginfodao.selectByPrimaryKey("test1").toString());
		GoodsInfo info=new GoodsInfo();
		info.setId("test7");
//		info=ginfodao.selectByPrimaryKey(info.getId());
		info=ginfodao.querybyid(info.getId());
		log.info(info.toString());
		//ginfo.setGoods_name("1577");
//		log.info("模糊查找");
//		List<GoodsInfo> list=ginfodao.goodsquerybyName("1577");
//		log.info("one:"+list.get(0).toString());
//		log.info("all:"+list.toString());
	}

	@Test
	public void insertGinfo() {
		log.info("尝试保存一条记录……");
		GoodsInfo ginfo = new GoodsInfo();
		ginfo.setId("test8");
		ginfo.setHiddenFlag(1);
		ginfo.setCreateTime(new Timestamp(date.getTime()));
		ginfo.setCreator(fbw);
		ginfo.setModifyTime(new Timestamp(date.getTime()));
		ginfo.setModifier(fbw);
		ginfo.setGoods_name("test8");
		ginfo.setGoods_description("desc"+new Date().getTime());
		ginfo.setGoods_price(21.90f);
		ginfo.setGoods_cost(12f);
		ginfo.setType_id(type_id);
		ginfo.setGoods_img("  ");
		ginfo.setGoods_main_img("  ");
		//ginfodao.insert(ginfo);
		log.info("insert:"+ginfodao.insertSelective(ginfo));
	}

	public void deleteOne() {
		String id="test5";
		ginfodao.deleteByPrimaryKey(id);
	}
	
	public void hideOne() {
		GoodsInfo info=new GoodsInfo();
		info.setId("test6");
		info=ginfodao.selectByPrimaryKey(info.getId());
		log.info(info.getHiddenFlag().toString());
		//if hiddenflag==1 then
		int rs=ginfodao.goodsputDown(info);
		log.info("result:"+rs);
	}

	public void update() {
		GoodsInfo ginfo = new GoodsInfo();
		ginfo.setId("test3");
		ginfo.setHiddenFlag(1);
		ginfo.setCreateTime(new Timestamp(date.getTime()));
		ginfo.setCreator(fbw);
		ginfo.setModifyTime(new Timestamp(date.getTime()));
		ginfo.setModifier(fbw);
		ginfo.setGoods_name("update"+new Date().getTime());
		ginfo.setGoods_description("desc");
		ginfo.setGoods_price(67.90f);
		ginfo.setGoods_cost(54.877f);
		ginfo.setType_id(type_id);
		ginfo.setGoods_img("  ");
		ginfo.setGoods_main_img("  ");
		log.info("update"+ginfodao.updateByPrimaryKeySelective(ginfo));;
	}
}
