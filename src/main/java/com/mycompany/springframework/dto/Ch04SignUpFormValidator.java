package com.mycompany.springframework.dto;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ch04SignUpFormValidator implements Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		log.info("실행");
		return Ch04SignUpForm.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		Ch04SignUpForm signUpForm = (Ch04SignUpForm) target;
		//ID 검사
		String mid = signUpForm.getMid();
		if(mid == null || mid.equals("")) {
			errors.rejectValue("mid", null, "아이디(mid)는 반드시 입력해야 합니다.");
		} else if(mid.length()<6 || mid.length()>12) {
			errors.rejectValue("mid", null, "아이디(mid)는 6자 이상 12자 이하로 입력해야 합니다.");
		}
		
		//Password 검사
		String mpassword = signUpForm.getMpassword();
		String pwPattern = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,15}";
		if(mpassword == null || mpassword.equals("")) {
			errors.rejectValue("mpassword", null, "비밀번호(mpassword)는 반드시 입력해야 합니다.");
		} else if(mpassword.length()<8 || mpassword.length()>15) {
			errors.rejectValue("mpassword", null, "비밀번호(mpassword)는 8자 이상 15자 이하로 입력해야 합니다.");
		} else if(!Pattern.matches(pwPattern, mpassword)) {
			errors.rejectValue("mpassword", null, "비밀번호는 알파벳 대소문자 및 숫자를 포함해야 합니다.");
		}
		
		String mname = signUpForm.getMname();
		String nPattern = "[a-zA-Z가-힣]{2,20}"; // 수정 해야함
		if(mname == null || mname.equals("")) {
			errors.rejectValue("mname", null, "이름은 반드시 입력해야 합니다.");
		} else if(!Pattern.matches(nPattern, mname)) {
			errors.rejectValue("mname", null, "유효한 이름을 입력하세요.");
		}
		
		String mphonenumber = signUpForm.getMphonenumber();
		String pPattern = "\\d{3}-\\d{3,4}-\\d{4}"; // 수정 해야함
		if(mphonenumber == null || mphonenumber.equals("")) {
			errors.rejectValue("mphonenumber", null, "전화번호는 반드시 입력해야 합니다.");
		} else if(!Pattern.matches(pPattern, mphonenumber)) {
			errors.rejectValue("mphonenumber", null, "전화번호 규칙 : OOO-OOOX-OOOO");
		}
		
		String memail = signUpForm.getMemail();
		String ePattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"; // 수정 해야함
		if(memail == null || memail.equals("")) {
			errors.rejectValue("memail", null, "이메일은 반드시 입력해야 합니다.");
		} else if(!Pattern.matches(ePattern, memail)) {
			errors.rejectValue("memail", null, "이메일 규칙 : abc@abc123.ab");
		}
		
	}
}
