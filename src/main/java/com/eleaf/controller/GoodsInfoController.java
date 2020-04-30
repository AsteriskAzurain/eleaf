package com.eleaf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eleaf.entity.DataResponse;
import com.eleaf.entity.GoodsInfo;
import com.eleaf.service.GoodsInfoService;
import com.eleaf.util.verify.Verify;

@RestController
@RequestMapping("/goods/info")
public class GoodsInfoController {

	@Autowired
	private GoodsInfoService service;

	String testuser = "testfbw";
	String testtype = "type1";
	String testimg = "#NONE#";

	/**
	 * 获取全部商品
	 * 
	 * @return
	 */
	@GetMapping("/getAll")
	private DataResponse getAll() {
		DataResponse response = new DataResponse();
		List<GoodsInfo> result = service.getAll("create_time DESC", true);
		return response.success(result);
	}

	/**
	 * 获取指定商品的详细信息
	 * 
	 * @param id 商品id
	 * @return 商品实体
	 */
	@GetMapping("/getOne")
	private DataResponse getOne(@RequestParam("id") String id) {
		DataResponse response = new DataResponse();
		GoodsInfo goods = new GoodsInfo();
		goods.setId(id);
		goods = service.getOne(goods);
		return response.success(goods);
	}
	
	@GetMapping("/search")
	private DataResponse search(@RequestParam("keywords") String keywords) {
		DataResponse response = new DataResponse();
		List<GoodsInfo> result = service.search(keywords);
		return response.success(result);
	}

	/**
	 * 上架商品
	 * 
	 * @param id 商品id
	 * @return
	 */
	@PostMapping("/onSale")
	private DataResponse onSale(@RequestParam("id") String id) {
		DataResponse response = new DataResponse();
		GoodsInfo goods = new GoodsInfo();
		goods.setId(id);
		if (service.appearOne(goods)) {
			return response.success(null);
		}
		return response.error();
	}

	/**
	 * 下架商品
	 * 
	 * @param id 商品id
	 * @return
	 */
	@PostMapping("/withdraw")
	private DataResponse withdraw(@RequestParam("id") String id) {
		DataResponse response = new DataResponse();
		GoodsInfo goods = new GoodsInfo();
		goods.setId(id);
		if (service.hideOne(goods)) {
			return response.success(null);
		}
		return response.error();
	}

	/**
	 * 新增商品
	 * 
	 * @param goods_name        商品名称
	 * @param goods_description 商品描述
	 * @param goods_price       商品单价
	 * @param goods_cost        商品成本
	 * @return
	 */
	@PostMapping("/add")
	private DataResponse add(@RequestParam("goods_name") String goods_name,
			@RequestParam("goods_description") String goods_description,
			@RequestParam("goods_price") float goods_price, @RequestParam("goods_cost") float goods_cost) {
		DataResponse response = new DataResponse();

		GoodsInfo goods = new GoodsInfo();
		goods.setGoods_name(goods_name);
		goods.setGoods_description(goods_description);
		goods.setGoods_price(goods_price);
		goods.setGoods_cost(goods_cost);
		goods.setGoods_buy_count(0);
		goods.setGoods_view_count(0);
		// fix me
		goods.setType_id(testtype);
		goods.setGoods_img(testimg);
		goods.setGoods_main_img(testimg);

		// goods.setCreator(testuser);
		// goods.setModifier(testuser);

		String id = service.createOne(goods);
		if (Verify.isInvalid(id)) {
			return response.error("8001", "商品重复！");
		}
		return response.success(id);
	}

	/**
	 * 修改商品
	 * 
	 * @param id                商品id
	 * @param goods_name        商品名称
	 * @param goods_description 商品描述
	 * @param goods_price       商品单价
	 * @param goods_cost        商品成本
	 * @return
	 */
	@PostMapping("/update")
	private DataResponse update(@RequestParam("id") String id, @RequestParam("goods_name") String goods_name,
			@RequestParam("goods_description") String goods_description,
			@RequestParam("goods_price") float goods_price, @RequestParam("goods_cost") float goods_cost) {
		DataResponse response = new DataResponse();

		GoodsInfo goods = new GoodsInfo();
		goods.setId(id);
		goods.setGoods_name(goods_name);
		goods.setGoods_description(goods_description);
		goods.setGoods_price(goods_price);
		goods.setGoods_cost(goods_cost);
		goods.setGoods_buy_count(0);
		goods.setGoods_view_count(0);
		// fix
		// goods.setType_id(testtype);
		// goods.setGoods_img(testimg);
		// goods.setGoods_main_img(testimg);

		// goods.setCreator(testuser);
		// goods.setModifier(testuser);

		if (service.updateOne(goods)) {
			return response.success(null);
		}
		return response.error();
	}
}
