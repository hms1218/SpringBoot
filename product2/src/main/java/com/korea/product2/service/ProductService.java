package com.korea.product2.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.korea.product2.dto.ProductDTO;
import com.korea.product2.model.ProductEntity;
import com.korea.product2.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	
	private final ProductRepository repository;
	
	//조회
	public List<ProductDTO> findAll(){
		List<ProductEntity> products = repository.findAll();
		
		return products.stream().map(ProductDTO::new).collect(Collectors.toList());
	}
	
	//유효검사
	private void validate(ProductEntity entity) {
		if(entity == null) {
			throw new RuntimeException("Entity cannot be null");
		}
	}
	
	//추가
	public List<ProductDTO> create(ProductDTO dto){
		ProductEntity entity = ProductDTO.toEntity(dto);
		
		validate(entity);
		
		repository.save(entity);
		
		return repository.findAll().stream().map(ProductDTO::new).collect(Collectors.toList());
	}
}
