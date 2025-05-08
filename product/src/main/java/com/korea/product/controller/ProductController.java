package com.korea.product.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.korea.product.dto.ProductDTO;
import com.korea.product.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
	
	//서비스 주입
	private final ProductService service;
	
	//추가
	@PostMapping
	public ResponseEntity<?> addProduct(@RequestBody ProductDTO dto){
		List<ProductDTO> products = service.addProduct(dto);
		
		return ResponseEntity.ok(products);
	}
	
	//모든상품조회(최소금액이 있으면 최소금액 이상인 제품만 조회)
	//클라이언트가 최소금액을 전달할 수도 있다.
	@GetMapping
	public ResponseEntity<?> getAllProducts(@RequestParam(required=false) Double minPrice){
		//required=false : 값을 전달하지 않아도됨.
		//값을 받지 않을 때는 null값을 받아야하기 때문에 double -> Double
		List<ProductDTO> products = service.getFilteredProducts(minPrice);
		
		return ResponseEntity.ok(products);
	}
	
	//수정
	@PutMapping("/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody ProductDTO dto){
		
		List<ProductDTO> products = service.updateProducts(id,dto);
		
		return ResponseEntity.ok(products);
		
	}
	
	//삭제
	//@PathVariable을 이용하여 삭제하기
	//경로 : /{id}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable int id){
		boolean isDeleted = service.deleteProducts(id);
		
		//삭제가 정상처리되면 본문없이 204 NoContent 응답
		if(isDeleted) {
			return ResponseEntity.noContent().build();
		}
		//삭제 실패시 404 Not Found 응답
		return ResponseEntity.notFound().build();
		
	}
	
//	@DeleteMapping("/{id}")
//	public ResponseEntity<?> deleteProduct(@PathVariable int id){
//		List<ProductDTO> products = service.deleteProducts(id);
//		
//		return ResponseEntity.ok(products);
//	}
	
	
}

//- 상품추가
//- 모든상품조회(최소금액이 있으면 최소금액 이상인 제품만 조회)
//- 상품수정(id를 가지고 이름, 설명, 가격 수정)
//- 삭제(id를 가지고 상품 삭제)