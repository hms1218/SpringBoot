package com.korea.todo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Controller
//클라이언트로부터 요청을 받아오거나, 서버로부터 응답을 내보내는 역할 
@RestController
//html같은 뷰 페이지를 반환하는 대신, JSON이나 XML형식의 데이터를 반환하는
//RESTful API를 제공하는데 적합하다.
@RequestMapping("test")
public class TestController {

}
