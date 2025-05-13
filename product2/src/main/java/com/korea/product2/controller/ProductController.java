package com.korea.product2.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korea.product2.dto.ProductDTO;
import com.korea.product2.dto.ResponseDTO;
import com.korea.product2.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product2")
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService service;
	
	//상품조회
	@GetMapping
	public ResponseEntity<?> findAll(){
		List<ProductDTO> products = service.findAll();
		
		ResponseDTO<ProductDTO> response = ResponseDTO.<ProductDTO>builder().data(products).build();
		
		return ResponseEntity.ok(response);
	}
	
	//상품추가
	@PostMapping
	public ResponseEntity<?> create(@RequestBody ProductDTO dto){
		List<ProductDTO> products = service.create(dto);
		
		ResponseDTO<ProductDTO> response = ResponseDTO.<ProductDTO>builder().data(products).build();
		
		return ResponseEntity.ok(response);
	}
}
