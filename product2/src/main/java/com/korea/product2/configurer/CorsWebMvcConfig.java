package com.korea.product2.configurer;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsWebMvcConfig implements WebMvcConfigurer{
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// addMapping("/**"): 모든 경로에 대해 CORS 설정을 적용
        registry.addMapping("/**")
            // allowedOrigins("http://localhost:3000"): React 애플리케이션이 실행되는 도메인(출처)에서 오는 요청을 허용
            .allowedOrigins("http://localhost:3000")
            // allowedMethods("GET", "POST", "PUT", "DELETE"): HTTP 메서드(GET, POST, PUT, DELETE)를 허용
            .allowedMethods("GET", "POST", "PUT", "DELETE")
            // allowedHeaders("*"): 모든 헤더를 허용
            .allowedHeaders("*")
            // allowCredentials(true): 쿠키나 인증 정보를 포함한 요청을 허용
            .allowCredentials(true);
	}
}
