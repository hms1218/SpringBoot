package com.korea.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.korea.todo.model.TodoEntity;
import com.korea.todo.persistence.TodoRepository;

@Service
//스프링 프레임워크에서 제공하는 어노테이션 중 하나로,
//서비스 레이어에 사용되는 클래스를 명시할 때 사용
//이 어노테이션을 사용하면 스프링이 해당 클래스를 스프링 컨테이너에서 관리하는 
//빈(bean)으로 등록하고, 비즈니스 로직을 처리하는 역할을 맡는다.

//쿼리문을 사용하지 않고 JPA메서드를 이용해서 테이블에 추가하고 조회까지 해서 결과를 확인한 것
public class TodoService {

	@Autowired
	private TodoRepository repository;
	
	public String testService() {
		//엔티티 하나 생성
		TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
		//TodoEntity 저장(DB에 저장)
		repository.save(entity);
		//TodoEntity 검색(SELECT)
		TodoEntity savedEntity = repository.findById(entity.getId()).get();
		return savedEntity.getTitle();
	}
}

//Optional
//null값이 나와도 추가적인 처리를 할 수 있는 다양한 메서드를 제공한다.
//1. isPresent() : 반환된 Optional 객체 안에 값이 존재하면 true, 존재하지 않으면 false를 반환한다.
//2. get() : Optional 안에 값이 존재할 때, 그 값을 반환한다.
//만약 값이 존재하지 않는데 호출하면 NoSuchElementException이 발생할 수 있다.
//3. orElse(T other) : 값이 존재하지 않을 때 기본값을 반환한다.

//findById()메서드의 반환형이 Optional인 이유는 조회하려는 ID가 존재하지 않을 수 있기 때문이다.
//NullPointException을 방지할 수 있다.


