package com.korea.member.dto;

import com.korea.member.model.MemberEntity;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
	private int id;
	private String name;
	
	@Column(name = "email", unique = true, nullable = false)
	private String email;
	
	private String password;
	
	//entity -> dto
	public MemberDTO(MemberEntity entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.email = entity.getEmail();
		this.password = entity.getPassword();
	}
	
	//dto -> entity
	public static MemberEntity toEntity(MemberDTO dto) {
		return MemberEntity.builder()
						.id(dto.getId())
						.name(dto.getName())
						.email(dto.getEmail())
						.password(dto.getPassword())
						.build();
	}
	
	
}
