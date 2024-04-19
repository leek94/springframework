package com.mycompany.springframework.service;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service("service4") // service4 이름으로 관리됨 
					 // 안적을 경우 기본적으로 클래스의 첫자를 소문자로한 이름으로 관리(ch123Service3)
@Slf4j
public class Ch12Service4 {
	
	// 기본 생성자가 없으면 객체를 생성하지 않음
	// 매개변수가 있는 생성자를 사용하려면 기본 생성자도 만들어야함.
	public Ch12Service4() {
		log.info("실행");
	}
	
}
