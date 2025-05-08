package com.korea.product.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.korea.product.dto.ProductDTO;
import com.korea.product.model.ProductEntity;
import com.korea.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	
	private final ProductRepository repository;
	
	//추가하기
	public List<ProductDTO> addProduct(ProductDTO dto){
		ProductEntity entity = ProductDTO.toEntity(dto);
		//jpa로 데이터베이스에 CRUD할 때 반드시 entity로만 해야한다.
		
		repository.save(entity); // 데이터베이스에 저장
		
		return repository.findAll().stream().map(ProductDTO::new).collect(Collectors.toList());
	}
	
	//모든상품조회(최소금액이 있으면 최소금액 이상인 제품만 조회)
	public List<ProductDTO> getFilteredProducts(Double minPrice){
		//일단 전체 조회를 한다.
		List<ProductEntity> products = repository.findAll();
		
		//가격 필터링(minPrice가 있을 경우)
		if(minPrice != null) {
			products = products
						.stream()
						.filter(product -> product.getPrice() >= minPrice)
						.collect(Collectors.toList());
		}
		
		return products.stream().map(ProductDTO::new).collect(Collectors.toList());
	
	}
	
	//수정
	public List<ProductDTO> updateProducts(int id,ProductDTO dto){
		//id를 통해서 데이터베이스에 저장되어 있는 데이터를 꺼내온다.
		
		//@PathVariable을 사용했으면 id로 찾아도 된다.
		Optional<ProductEntity> productOptional = repository.findById(id);
		
		if(productOptional.isPresent()) {
			//Optional에 들어있는 데이터를 꺼낸다.
			ProductEntity entity = productOptional.get();
			//수정하려고하는 데이터로 다시 세팅한다.
			entity.setName(dto.getName());
			entity.setDescription(dto.getDescription());
			entity.setPrice(dto.getPrice());
			//다시 세팅했으면 데이터베이스에 저장한다.
			repository.save(entity);
		}
		
		return repository.findAll().stream().map(ProductDTO::new).collect(Collectors.toList());
				
//		ProductEntity entity = ProductDTO.toEntity(dto);
//				
//		Optional<ProductEntity> productOptional = repository.findById(entity.getId());
		
//		productOptional.ifPresent(product -> {
//			product.setName(entity.getName());
//			product.setDescription(entity.getDescription());
//			product.setPrice(entity.getPrice());
//			
//			repository.save(product); //새로운 데이터를 데이터베이스에 추가하기
//		});
		
//		return repository.findAll().stream().map(ProductDTO::new).collect(Collectors.toList());
	}
	
	//삭제
	public boolean deleteProducts(int id){
		//id를 통해서 데이터베이스에 데이터가 실제로 존재하는지
		Optional<ProductEntity> optionalEntity = repository.findById(id);
		//존재한다면
		if(optionalEntity.isPresent()) {
			//데이터베이스에서 삭제
			repository.deleteById(id);
			
			return true;
		}
		
		return false;
	}
	
//	public List<ProductDTO> deleteProducts(int id){
//		Optional<ProductEntity> optionalEntity = repository.findById(id);
//		
////		ProductEntity entity = ProductDTO.toEntity(dto);
////		
////		repository.delete(entity);
//		
//		if(optionalEntity.isPresent()) {
//			repository.deleteById(id);
//		}
//	
//		return repository.findAll().stream().map(ProductDTO::new).collect(Collectors.toList());
//	}
	
	
}

//- 상품추가
//- 모든상품조회(최소금액이 있으면 최소금액 이상인 제품만 조회)
//- 상품수정(id를 가지고 이름, 설명, 가격 수정)
//- 삭제(id를 가지고 상품 삭제)