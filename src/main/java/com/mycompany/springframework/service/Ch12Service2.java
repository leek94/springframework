package com.mycompany.springframework.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ch12Service2 {
	
	//기본 생성자
	public Ch12Service2() {
		log.info("실행");
	}
	
	//인스턴스 메소드
	public Ch12Service2 getObject() {
		return new Ch12Service2();
	}
}
