package com.korea.product.dto;

import com.korea.product.model.ProductEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDTO {
	private int id;
	private String name;
	private String description;
	private double price;
	
	//entity -> dto
	public ProductDTO(ProductEntity entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.description = entity.getDescription();
		this.price = entity.getPrice();
	}
	
	//dto -> entity
	public static ProductEntity toEntity(ProductDTO dto) {
		return ProductEntity.builder()
				.id(dto.getId())
				.name(dto.getName())
				.description(dto.getDescription())
				.price(dto.getPrice())
				.build();
	}
	
	
}

//- id,name,description,price 필드
//- entity -> dto / dto -> entity 변환 기능 만들기