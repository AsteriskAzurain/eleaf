package com.eleaf.entity;

import javax.persistence.Column;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "e_goods_info")
public class GoodsInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "goods_name")
	private String goods_name;

	@Column(name = "goods_description")
	private String goods_description;

	@Column(name = "goods_price")
	private Float goods_price;
	
	@Column(name = "goods_cost")
	private Float goods_cost;
	
	@Column(name = "type_id")
	private String type_id;
	
	@Column(name = "goods_buy_count")
	private Integer goods_buy_count;
	
	@Column(name = "goods_view_count")
	private Integer goods_view_count;
	
	@Column(name = "goods_img")
	private String goods_img;
	
	@Column(name = "goods_main_img")
	private String goods_main_img;
	
}
