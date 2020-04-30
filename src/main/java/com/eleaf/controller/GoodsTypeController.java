package com.eleaf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eleaf.entity.DataResponse;
import com.eleaf.entity.GoodsType;
import com.eleaf.service.GoodsTypeService;
import com.eleaf.util.verify.Verify;

@RestController
@RequestMapping("/goods/type")
public class GoodsTypeController {

	@Autowired
	private GoodsTypeService service;

	String testtype = "type1";

	/**
	 * 获取全部类别
	 * 
	 * @return
	 */
	@GetMapping("/getAll")
	private DataResponse getAll() {
		DataResponse response = new DataResponse();
		List<GoodsType> result = service.getAll("create_time DESC", true);
		return response.success(result);
	}

	/**
	 * 获取指定商品类别的详细信息
	 * 
	 * @param id 商品类别id
	 * @return 商品类别实体
	 */
	@GetMapping("/getOne")
	private DataResponse getOne(@RequestParam("id") String id) {
		DataResponse response = new DataResponse();
		GoodsType type = new GoodsType();
		type.setId(id);
		type = service.getOne(type);
		return response.success(type);
	}
	
	@GetMapping("/search")
	private DataResponse search(@RequestParam("keywords") String keywords) {
		DataResponse response = new DataResponse();
		List<GoodsType> result = service.search(keywords);
		return response.success(result);
	}

	/**
	 * 上架类别
	 * 
	 * @param id 类别id
	 * @return
	 */
	@PostMapping("/onSale")
	private DataResponse onSale(@RequestParam("id") String id) {
		DataResponse response = new DataResponse();
		GoodsType type = new GoodsType();
		type.setId(id);
		if (service.appearOne(type)) {
			return response.success(null);
		}
		return response.error();
	}

	/**
	 * 下架类别
	 * 
	 * @param id 类别id
	 * @return
	 */
	@PostMapping("/withdraw")
	private DataResponse withdraw(@RequestParam("id") String id) {
		DataResponse response = new DataResponse();
		GoodsType type = new GoodsType();
		type.setId(id);
		if (service.hideOne(type)) {
			return response.success(null);
		}
		return response.error();
	}

	/**
	 * 新增类别
	 * 
	 * @param type_name        类别名称
	 * @return
	 */
	@PostMapping("/add")
	private DataResponse add(@RequestParam("type_name") String type_name) {
		DataResponse response = new DataResponse();

		GoodsType type = new GoodsType();
		type.setType_name(type_name);
		

		String id = service.createOne(type);
		if (Verify.isInvalid(id)) {
			return response.error("8001", "商品类别重复！");
		}
		return response.success(id);
	}

	/**
	 * 修改类别
	 * 
	 * @param id               类别id
	 * @param type_name        类别名称
	 * @return
	 */
	@PostMapping("/update")
	private DataResponse update(@RequestParam("id") String id, @RequestParam("type_name") String type_name) {
		DataResponse response = new DataResponse();

		GoodsType type = new GoodsType();
		type.setId(id);
		type.setType_name(type_name);
		if (service.updateOne(type)) {
			return response.success(null);
		}
		return response.error();
	}
}
