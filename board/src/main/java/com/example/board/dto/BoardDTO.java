package com.example.board.dto;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.example.board.model.BoardEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
	private Long id;
	private String title;
	private String author;
	
	@CreationTimestamp
	private LocalDateTime writingTime;
	
	private String content;
	
	//entity -> dto
	public BoardDTO(BoardEntity entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.author = entity.getAuthor();
		this.writingTime = entity.getWritingTime();
		this.content = entity.getContent();
	}
	
	//dto -> entity
	public static BoardEntity toEntity(BoardDTO dto) {
		return BoardEntity.builder()
					.id(dto.getId())
					.title(dto.getTitle())
					.author(dto.getAuthor())
					.writingTime(dto.getWritingTime())
					.content(dto.getContent())
					.build();
	}				
}
