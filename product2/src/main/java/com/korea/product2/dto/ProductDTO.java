package com.korea.product2.dto;

import java.time.LocalDateTime;

import com.korea.product2.model.ProductEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
	private int id;
	private String name;
	private String stock;
	private double price;
	private LocalDateTime registerDate;
	private LocalDateTime updateDate;
	
	//entity -> dto
	public ProductDTO(ProductEntity entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.stock = entity.getStock();
		this.price = entity.getPrice();
		this.registerDate = entity.getRegisterDate();
		this.updateDate = entity.getUpdateDate();
	}
	
	//dto -> entity
	public static ProductEntity toEntity(ProductDTO dto) {
		return ProductEntity.builder()
			.id(dto.getId())
			.name(dto.getName())
			.stock(dto.getStock())
			.price(dto.getPrice())
			.registerDate(dto.getRegisterDate())
			.updateDate(dto.getUpdateDate())
			.build();
	}
	
}
