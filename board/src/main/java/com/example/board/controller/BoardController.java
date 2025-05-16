package com.example.board.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.dto.BoardDTO;
import com.example.board.dto.ResponseDTO;
import com.example.board.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

	private final BoardService service;
	
	//전체조회
	@GetMapping("/all")
	public ResponseEntity<?> getAllBoard(){
		List<BoardDTO> dto = service.getAllBoard();
		
		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(dto).build();
		
		return ResponseEntity.ok(response);
	}
	
	//게시물 추가하기
	@PostMapping("/add")
	public ResponseEntity<?> addPost(@RequestBody BoardDTO dto){
		List<BoardDTO> list = service.addPost(dto);
		
		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(list).build();
		
		return ResponseEntity.ok(response);
	}
	
	//게시물 수정하기
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updatePost(@PathVariable("id") Long id, @RequestBody BoardDTO dto){
		List<BoardDTO> list = service.updatePost(id, dto);
		
		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(list).build();
		
		return ResponseEntity.ok(response);
	}
	
	//게시물 삭제하기
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deletePost(@PathVariable("id") Long id){
		List<BoardDTO> list = service.deletePost(id);
		
		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(list).build();
		
		return ResponseEntity.ok(response);
	}
	
	
	
}
