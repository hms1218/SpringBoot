package com.korea.page.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.korea.page.dto.PageDTO;
import com.korea.page.model.PageEntity;
import com.korea.page.repository.PageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PageService {

	private final PageRepository repository;

	//회원 조회
	public List<PageDTO> getUser() {
		
		return repository.findAll().stream().map(PageDTO::new).collect(Collectors.toList());
		
	}

	public List<PageDTO> signUpUser(PageDTO dto) {
		PageEntity entity = PageDTO.toEntity(dto);
		
		repository.save(entity);
		
		return getUser();
	}
}
