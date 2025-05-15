package com.korea.member.controller;

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

import com.korea.member.dto.MemberDTO;
import com.korea.member.dto.ResponseDTO;
import com.korea.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService service;
	
	//전체 회원 조회
	@GetMapping
	public ResponseEntity<?> getAllMember(){
		List<MemberDTO> dto = service.getAllMember();
		
		ResponseDTO<MemberDTO> response = ResponseDTO.<MemberDTO>builder().data(dto).build();
		
		return ResponseEntity.ok(response);
		
	}
	
	//이메일로 특정회원 조회
	@GetMapping("/{email}")
	public ResponseEntity<?> getMember_email(@PathVariable("email") String email){
		List<MemberDTO> dto = service.getMember_email(email);
		
		ResponseDTO<MemberDTO> response = ResponseDTO.<MemberDTO>builder().data(dto).build();
		
		return ResponseEntity.ok(response);
	}
	
	//회원 추가
	@PostMapping
	public ResponseEntity<?> saveMember(@RequestBody MemberDTO dto){
		List<MemberDTO> members = service.saveMember(dto);
		
		ResponseDTO<MemberDTO> response = ResponseDTO.<MemberDTO>builder().data(members).build();
		
		return ResponseEntity.ok(response);
	}
	
	//이메일로 비밀번호 변경
	@PutMapping("/{email}/password")
	public ResponseEntity<?> updateMember(@PathVariable("email") String email, @RequestBody MemberDTO dto){
		List<MemberDTO> members = service.updateMember(email, dto);
		
		ResponseDTO<MemberDTO> response = ResponseDTO.<MemberDTO>builder().data(members).build();
		
		return ResponseEntity.ok(response);
	}
	
	//회원아이디로 삭제
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteMember(@PathVariable("id") int id, @RequestBody MemberDTO dto){
		List<MemberDTO> members = service.deleteMember(id, dto);
		
		ResponseDTO<MemberDTO> response = ResponseDTO.<MemberDTO>builder().data(members).build();
		
		return ResponseEntity.ok(response);
	}
	
	
}

//전체 회원 조회 → GET /members
//- 이메일로 특정회원 조회 → GET/members/{email}
// - 한명만 조회가 되지만 리스트에 담아서 반환한다.
//- 회원 추가 → POST /members
// - 회원을 추가하고 전체 유저를 반환한다.
//- 이메일로 비밀번호 변경 → PUT /members/{email}/password (이메일은 경로에, 변경할 비밀번호는 @RequestBody로 받는다)
// - email을 통해 유저를 찾고 비밀번호를 수정하여 다시 저장한다.
// - 수정하고 전체 유저를 반환한다.
//- 회원아이디로 삭제 → DELETE /members/{id}
// - 삭제하고 전체 유저를 반환한다.