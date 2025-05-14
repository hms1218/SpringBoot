package com.korea.member.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.korea.member.dto.MemberDTO;
import com.korea.member.model.MemberEntity;
import com.korea.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository repository;
	
	//전체 회원 조회
	public List<MemberDTO> getAllMember(){
		List<MemberEntity> entity = repository.findAll();
		
		return entity.stream().map(MemberDTO::new).collect(Collectors.toList());
	}
	
	//이메일로 특정 회원 조회
	public List<MemberDTO> getMember_email(String email){
		Optional<MemberEntity> optional = repository.findByEmail(email);
		
		return optional.stream().map(MemberDTO::new).collect(Collectors.toList());
	}
	
	//회원 추가
	public List<MemberDTO> saveMember(MemberDTO dto){
		MemberEntity entity = MemberDTO.toEntity(dto);
		
		repository.save(entity);
		
		return repository.findAll().stream().map(MemberDTO::new).collect(Collectors.toList());
	}
	
	//이메일로 비밀번호 변경
	public List<MemberDTO> updateMember(String email, MemberDTO dto){
		Optional<MemberEntity> optional = repository.findByEmail(email);
		
		if(optional.isPresent()) {
			MemberEntity entity = optional.get();
			entity.setPassword(dto.getPassword());
			
			repository.save(entity);
		}
		
		return repository.findAll().stream().map(MemberDTO::new).collect(Collectors.toList());
	}
	
	//회원아이디로 삭제
	public List<MemberDTO> deleteMember(int id, MemberDTO dto){
		Optional<MemberEntity> optional = repository.findById(id);
		
		if(optional.isPresent()) {
			MemberEntity entity = optional.get();
			
			repository.delete(entity);
		}
		
		return repository.findAll().stream().map(MemberDTO::new).collect(Collectors.toList());
		
	}

	
}

//전체 회원 조회 → GET /members
//- 이메일로 특정회원 조회 → GET/members/{email}
//   - 한명만 조회가 되지만 리스트에 담아서 반환한다.
//- 회원 추가 → POST /members
//   - 회원을 추가하고 전체 유저를 반환한다.
//- 이메일로 비밀번호 변경 → PUT /members/{email}/password (이메일은 경로에, 변경할 비밀번호는 @RequestBody로 받는다)
//   - email을 통해 유저를 찾고 비밀번호를 수정하여 다시 저장한다.
//   - 수정하고 전체 유저를 반환한다.
//- 회원아이디로 삭제 → DELETE /members/{id}
//   - 삭제하고 전체 유저를 반환한다.