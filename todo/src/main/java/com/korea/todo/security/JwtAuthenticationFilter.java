package com.korea.todo.security;

import java.io.IOException;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
//OncePerRequestFilter
//한 요청당 한 번만 실행되는 필터
//doFilterInternal()메서드를 가지고 있다.
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	private final TokenProvider tokenProvider;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			//parsebearerToken메서드
			//HTTP 요청 헤더에서 Authorization 값을 가져와 bearer 토큰 형식인지 확인한 후, 토큰을 가져온다.
			//토큰이 없거나 유효하지 않으면 null을 반환
			String token = parsebearerToken(request);
			log.info("Filter is running...");
			
			//토큰 검사하기
			if(token != null && !token.equalsIgnoreCase("null")) {
				//userId 꺼내기. 위변조된 경우 예외처리를 한다.
				//TokenProvider에서 토큰을 검증하고 userId를 가져온다.
				String userId = tokenProvider.validateAndGetUserId(token);
				
				log.info("Authenticated user Id : " + userId);
				
				//사용자 인증 완료 후, SecurityContext에 인증 정보 등록
				//AbstractAuthenticationToken : 스프링 시큐리티에서 인증 정보를 표현하는 추상클래스
				//UsernamePasswordAuthenticationToken : 사용자 이름과 비밀번호 기반 인증에 쓰이는 구현체
				//생성자의 첫번째 인자 : 인증 주체를 나타내는 객체(userId, username)
				//생성자의 두번째 인자 : 인증에 사용된 자격 증명을 담는 필드(JWT 검증방식을 사용하므로 null)
				//생성자의 세번째 인자 : 사용자의 권한을 담는 컬렉션
				AbstractAuthenticationToken authentication = 
						new UsernamePasswordAuthenticationToken(userId, null, AuthorityUtils.NO_AUTHORITIES);
				
				//authentication객체에 현재 HTTP 요청과 관련된 부가정보를 담아주는 역할
				//WebAuthenticationDetailsSource() : 스프링 시큐리티에서 제공하는 클래스이고 
				//								  HttpServletRequest 객체로부터 인증 세부정보를 생성하는 역할을 한다.
				//buildDetails(request) : HttpServletRequest객체에서 인증과 관련된 추가적인 정보를 추출해 
				//						  WebAuthenticationDetails객체를 반환
				//						  위 객체는 사용자의 세션 ID, 클라이언트 IP 주소 등의 데이터를 담고있다.
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				//인증된 정보를 저장
				//SecurityContextHolder : 사용자의 인증 정보와 보안 컨텍스트를 관리하는 클래스
				//						  어플리케이션 내에서 현재 인증된 사용자의 정보를 저장하고 제공하는 역할
				SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
				
				//현재 요청에 대한 인증 정보를 securityContext에 저장하여 
				//스프링 시큐리티가 해당 사용자를 인증된 사용자로 인식하게 한다.
				securityContext.setAuthentication(authentication);
				
				//다른 api에서 방금 등록한 정보를 얻을 수 있다.
				SecurityContextHolder.setContext(securityContext);
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	//HttpServletRequest : 요청 정보를 받아올 수 있는 인터페이스
	//getMethod() : 요청메서드 확인 가능(GET, POST, PUT, DELETE)
	//getRequestURI() : 도메인 이후의 요청 경로를 반환
	//getProtocol() : 사용된 프로토콜과 버전을 반환
	private String parsebearerToken(HttpServletRequest request) {
		//요청 정보에서 헤더를 파싱해 bearer토큰을 반환한다.
		//bearer : 토큰을 소지하고 있는 사람이 곧 인증된 사용자다 라는 뜻
		
		//request.getHeader("Authorization") : HTTP헤더에서 Authorization에 들어있는 값을 얻어온다.
		String bearerToken = request.getHeader("Authorization");
		
		//bearer토큰 형식일 경우 토큰값만 반환
		//Authorization : bearer 토큰값
		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}
	
	
	
}
