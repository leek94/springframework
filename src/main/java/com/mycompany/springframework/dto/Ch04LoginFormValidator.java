package com.mycompany.springframework.dto;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ch04LoginFormValidator implements Validator{
	@Override
	public boolean supports(Class<?> klass) {
		log.info("실행");
		return Ch04LoginForm.class.isAssignableFrom(klass);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		Ch04LoginForm loginForm = (Ch04LoginForm) target;
		//ID 검사
		String mid = loginForm.getMid();
		if(mid == null || mid.equals("")) {
			errors.rejectValue("mid", "errors.mid.required", "아이디(mid)는 반드시 입력해야 합니다."); // 기본적으로 중간 키의 값을 출력하고 없다면 뒤의 값을 출력
		} else if(mid.length()<6 || mid.length()>12) {
			errors.rejectValue("mid", "errors.mid.length", new String[] {"6", "12"}, "아이디(mid)는 6자 이상 12자 이하로 입력해야 합니다.");
		}
		
		//Password 검사
		String mpassword = loginForm.getMpassword();
		String pattern = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,15}";
		if(mpassword == null || mpassword.equals("")) {
			errors.rejectValue("mpassword", null, "비밀번호(mpassword)는 반드시 입력해야 합니다.");
		} else if(mpassword.length()<6 || mpassword.length()>12) {
			errors.rejectValue("mpassword", null, "비밀번호(mpassword)는 8자 이상 15자 이하로 입력해야 합니다.");
		} else if(!Pattern.matches(pattern, mpassword)) {
			errors.rejectValue("mpassword", null, "비밀번호는 알파벳 대소문자 및 숫자를 포함해야 합니다.");
		}
		
	}
}
