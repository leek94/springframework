package com.mycompany.springframework.dto;

import lombok.Data;

@Data
public class Ch07JoinForm {
	private String mid;
	private String mname;
	private String mpassword;
	//private String mjob = "공무원"; 기본값 지정의 방식
	//private String mcity = "제주"; 기본값 지정의 방식
	private String mjob;
	private String mcity;
}
