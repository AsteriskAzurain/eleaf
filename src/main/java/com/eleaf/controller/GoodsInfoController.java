package com.eleaf.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.eleaf.entity.DataResponse;
import com.eleaf.entity.GoodsInfo;
import com.eleaf.service.impl.GoodsInfoServiceImpl;

@RestController
@RequestMapping("/goods/info")
public class GoodsInfoController {
	
	@Autowired
	private GoodsInfoServiceImpl service;
	private GoodsInfo ginfo;
	Date date= new Date();
	Timestamp time=new Timestamp(date.getTime());
	String testuser="testfbw";
	String testtype="type1";
	String testimg="   ";
	/*
	 * Service
	 * queryall:getAll() --return List<T>
	 * create:createOne(T record) --return String:""/id
	 * delete/putdown:hideOne(GoodsInfo record) //overwritten --return boolean
	 * update:updateOne(T record) --return boolean
	 * retrieve:(1)getOne(String id) --return GoodsInfo
	 * 			(2)goodsquerybyName(GoodsInfo record) --return List<GoodsInfo>
	 * */
	
	/**
	 * 查询全部商品
	 * */
	@GetMapping("/getAll")
	@ResponseBody
	private DataResponse getAll() {
		DataResponse response = new DataResponse();
		List<GoodsInfo> result=service.getAll();
		return response.success(result);
	}
	
	/**
	 * 按照id逻辑删除一条记录
	 * */
	@GetMapping("/deletehide")
	@ResponseBody
	private ModelAndView delete(@RequestParam("id") String id) {
		GoodsInfo record =new GoodsInfo();
		record.setId(id);
		service.hideOne(record); 
		ModelAndView mv = new ModelAndView("redirect:/bgms/goods_info.jsp");
		return mv;
	}
	
	@GetMapping("/puton")
	@ResponseBody
	private ModelAndView puton(@RequestParam("id") String id) {
		GoodsInfo record =new GoodsInfo();
		record.setId(id);
		service.goodsputOn(record); 
		ModelAndView mv = new ModelAndView("redirect:/bgms/goods_info.jsp");
		return mv;
	}
	
	@GetMapping("/loadRecord")
	@ResponseBody
	private ModelAndView loadRecord(@RequestParam("id") String id,HttpSession session) {
		StringBuffer view=new StringBuffer();
		view.append("redirect:/bgms/update/goods_info_update.jsp?id=");
		view.append(id);
		ginfo=service.getOne(id);
		ModelAndView mv = new ModelAndView(view.toString());
//		ModelAndView mv = new ModelAndView("bgms/update/goods_info_update");
//		mv.addObject("record",ginfo);
		session.setAttribute("record", ginfo);
		return mv;
	}
	
	@GetMapping("/updateRecord")
	@ResponseBody
	private ModelAndView updateRecord(@RequestParam("id") String id,@RequestParam("goods_name") String goods_name,
			@RequestParam("goods_description") String goods_description,
			@RequestParam("goods_price") String goods_price,@RequestParam("goods_cost") String goods_cost,
			@RequestParam("customRadio") String customRadio) {
		GoodsInfo newinfo = new GoodsInfo();
		
		newinfo.setId(id);
		newinfo.setGoods_name(goods_name);
		newinfo.setGoods_description(goods_description);
		newinfo.setGoods_cost(Float.parseFloat(goods_cost));
		newinfo.setGoods_price(Float.parseFloat(goods_price));
		newinfo.setHiddenFlag(Integer.parseInt(customRadio));
		newinfo.setCreateTime(time);
		newinfo.setModifyTime(time);
		newinfo.setGoods_buy_count(0);
		newinfo.setGoods_view_count(0);
		
		//fix me
		newinfo.setCreator(testuser);
		newinfo.setModifier(testuser);
		newinfo.setType_id(testtype);
		newinfo.setGoods_img(testimg);
		newinfo.setGoods_main_img(testimg);
		
		service.updateOne(newinfo);
		
		ModelAndView mv = new ModelAndView("redirect:/bgms/goods_info.jsp");
		return mv;
	}
	@GetMapping("/insertRecord")
	@ResponseBody
	private ModelAndView insertRecord(@RequestParam("goods_name") String goods_name,
			@RequestParam("goods_description") String goods_description,
			@RequestParam("goods_price") String goods_price,@RequestParam("goods_cost") String goods_cost,
			@RequestParam("customRadio") String customRadio) {
		GoodsInfo newinfo = new GoodsInfo();
		
		newinfo.setId("good"+time.toString());
		newinfo.setGoods_name(goods_name);
		newinfo.setGoods_description(goods_description);
		newinfo.setGoods_cost(Float.parseFloat(goods_cost));
		newinfo.setGoods_price(Float.parseFloat(goods_price));
		newinfo.setHiddenFlag(Integer.parseInt(customRadio));
		newinfo.setCreateTime(time);
		newinfo.setModifyTime(time);
		newinfo.setGoods_buy_count(0);
		newinfo.setGoods_view_count(0);
		
		//fix me
		newinfo.setCreator(testuser);
		newinfo.setModifier(testuser);
		newinfo.setType_id(testtype);
		newinfo.setGoods_img(testimg);
		newinfo.setGoods_main_img(testimg);
		
		service.createOne(newinfo);
		
		ModelAndView mv = new ModelAndView("redirect:/bgms/goods_info.jsp");
		return mv;
	}
}
