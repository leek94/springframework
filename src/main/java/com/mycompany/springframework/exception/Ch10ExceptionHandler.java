package com.mycompany.springframework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Component
@Slf4j
public class Ch10ExceptionHandler {

	@ExceptionHandler(NullPointerException.class)
	public String handleNullPointerException() {
		// 예외 처리 내용 작성
		log.info("실행");
		
		return "ch10/error500_null";
	}
	
	@ExceptionHandler(Ch10CustomException.class)
	public String Ch10CustomException(Ch10CustomException e, Model model) {
									// 예외를 들고옴
		
		// 예외 처리 내용 작성
		log.info("실행");
		model.addAttribute("message",e.getMessage());
		
		return "ch10/error500_custom";
	}
	// 이 예외가 발생하면 알아서 실행됨
	
	// 위 예외를 제외한 500을 발생시키는 모든 예외를 처리
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //500
	public String handlle500Exception(Exception e) {
		return "ch10/error500";
	}
	// 특정 예외를 지정하는게 아니라 그냥 예외 발생하면 여기로 가라는 의미

	
	// 404 예외 처리
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND) // 404
	public String handle404() {
		
		return "ch10/error404";
	}
}
