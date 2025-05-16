package com.example.board.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.board.dto.BoardDTO;
import com.example.board.model.BoardEntity;
import com.example.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository repository;

	//전체 조회
	public List<BoardDTO> getAllBoard() {
		List<BoardEntity> list = repository.findAll();
		
		return list.stream().map(BoardDTO::new).collect(Collectors.toList());
		
	}

	//게시물 추가하기
	public List<BoardDTO> addPost(BoardDTO dto) {
		BoardEntity entity = BoardDTO.toEntity(dto);
		
		repository.save(entity);
		
		return repository.findAll().stream().map(BoardDTO::new).collect(Collectors.toList());
	}
	
	//게시물 수정하기
	public List<BoardDTO> updatePost(Long id, BoardDTO dto) {
		Optional<BoardEntity> optional = repository.findById(id);
		
		if(optional.isPresent()) {
			BoardEntity entity = optional.get();
			
			entity.setTitle(dto.getTitle());
			entity.setAuthor(dto.getAuthor());
			entity.setContent(dto.getContent());
			
			repository.save(entity);
		}
		
		return getAllBoard();
	}
	
	//게시물 삭제하기
	public List<BoardDTO> deletePost(Long id) {
		Optional<BoardEntity> optional = repository.findById(id);
		
		if(optional.isPresent()) {
			BoardEntity entity = optional.get();
			
			repository.delete(entity);
		}
		
		return repository.findAll().stream().map(BoardDTO::new).collect(Collectors.toList());
		
	}

	
	
	
}
