package com.korea.page.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korea.page.dto.PageDTO;
import com.korea.page.dto.ResponseDTO;
import com.korea.page.service.PageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/page")
@RequiredArgsConstructor
public class PageController {
	
	private final PageService service;
	
	//회원 조회
	@GetMapping
	public ResponseEntity<?> getUser(){
		List<PageDTO> list = service.getUser();
		
		ResponseDTO<PageDTO> response = ResponseDTO.<PageDTO>builder().data(list).build();
		
		return ResponseEntity.ok(response);
	}
	
	//회원추가
	@PostMapping("/signup")
	public ResponseEntity<?> signUpUser(@RequestBody PageDTO dto){
		List<PageDTO> list = service.signUpUser(dto);
		
		ResponseDTO<PageDTO> response = ResponseDTO.<PageDTO>builder().data(list).build();
		
		return ResponseEntity.ok(response);
	}
	
}
