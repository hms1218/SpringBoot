package com.korea.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.korea.user.dto.UserDTO;
import com.korea.user.model.UserEntity;
import com.korea.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //final이나 @NonNull이 붙은 필드를 생성자의 매개변수로 포함
public class UserService {
	
//	@Autowired
	private final UserRepository repository;
	//public UserService(UserRepository repositroy){
	//	this.repository = repository
	//} 
	//와 같다.
	
	//사용자 생성
	public List<UserDTO> create(UserEntity entity){
		repository.save(entity); //데이터베이스에 저장
		
		return repository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
	}
	
	//모든 사용자 조회
	public List<UserDTO> getAllUsers(){
		return repository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
	}
	
	//이메일로 사용자 검색하기
	public List<UserDTO> getUser(UserEntity entity){
		return repository.findByEmail(entity.getEmail());
	}
	
	//ID를 통해 이름과 이메일 수정하기
	
	
	
	
	
	
}
