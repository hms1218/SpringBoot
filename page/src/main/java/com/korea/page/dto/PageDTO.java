package com.korea.page.dto;

import com.korea.page.model.PageEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageDTO {

	private String id;	
	private String password;
	private String address;
	private String email;
	
	//entity -> dto
	public PageDTO(PageEntity entity) {
		this.id = entity.getId();
		this.password = entity.getPassword();
		this.address = entity.getAddress();
		this.email = entity.getEmail();
	}
	
	public static PageEntity toEntity(PageDTO dto) {
		return PageEntity.builder()
						.id(dto.getId())
						.password(dto.getPassword())
						.address(dto.getAddress())
						.email(dto.getEmail())
						.build();
	}
}
