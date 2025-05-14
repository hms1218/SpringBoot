package com.korea.product2.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korea.product2.dto.OrderDTO;
import com.korea.product2.dto.ProductDTO;
import com.korea.product2.dto.ResponseDTO;
import com.korea.product2.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService service;
	
	//주문조회 기능 만들기
	@GetMapping
	public ResponseEntity<?> getOrderTotals(){
		List<OrderDTO> order = service.getAllOrderTotalPrices();
		
		ResponseDTO<OrderDTO> response = ResponseDTO.<OrderDTO>builder().data(order).build();
		
		return ResponseEntity.ok(response);
	}
	
	//주문하기 기능 만들기
	@PostMapping
	public ResponseEntity<?> save(@RequestBody OrderDTO dto){
		List<ProductDTO> list = service.save(dto);
		
		ResponseDTO<ProductDTO> response = ResponseDTO.<ProductDTO>builder().data(list).build();
		
		return ResponseEntity.ok(response);
	}
	
}
