package com.mycompany.springframework.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ch12Service3 {

	private Ch12Service3() {}
	
	public static Ch12Service3 getInstance() {
		log.info("실행");
		return new Ch12Service3();
	}
}
