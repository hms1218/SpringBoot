package com.korea.todo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korea.todo.model.ResponseDTO;
import com.korea.todo.service.TodoService;

@RestController
@RequestMapping("todo")
public class TodoController {

	//실행할 때 service객체가 필드로 직접 주입이 된다.
	@Autowired
	TodoService service;
	
	//주입받은 객체로 메서드를 실행하면 된다.
	@GetMapping("/test")
	public ResponseEntity<?> testTodo(){
		//service클래스에 있는 메서드를 호출
		String str = service.testService(); // str = TodoEntity.title("My first todo item")
		List<String> list = new ArrayList<>();
		list.add(str);
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		return ResponseEntity.ok().body(response);
	}
	
}

//- @Controller
//- @RestController
//- @Service
//- @Repository
//- @Configuration    ⇒ 전부 @Component의 자식 컴포넌트이다.