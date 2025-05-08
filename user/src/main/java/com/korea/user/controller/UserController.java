package com.korea.user.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korea.user.dto.UserDTO;
import com.korea.user.model.UserEntity;
import com.korea.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	
//	@Autowired
	private final UserService service;
	
	//사용자 추가
	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody UserDTO dto){
		UserEntity entity = UserDTO.toEntity(dto);
		List<UserDTO> users = service.create(entity);
		return ResponseEntity.ok(users);
	}
	
	//모든 사용자 조회
	@GetMapping
	public ResponseEntity<?> getAllUsers(){
		List<UserDTO> users = service.getAllUsers();
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("/{email}")
	public ResponseEntity<?> getUser(@PathVariable("email") String email){
		UserDTO users = service.getUserByEmail(email);
		return ResponseEntity.ok(users);
	}
	
	@PutMapping
	public ResponseEntity<?> updateUser(@RequestBody UserDTO dto){
		UserEntity entity = UserDTO.toEntity(dto);
		List<UserDTO> updateUser = service.updateUser(entity);
		
		return ResponseEntity.ok(updateUser);
	}
	
	
}
