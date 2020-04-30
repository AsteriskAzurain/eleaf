package com.eleaf.entity;

import javax.persistence.Column;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "e_goods_type")
public class GoodsType extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "type_name")
	private String type_name;
	
}
