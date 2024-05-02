package com.mycompany.springframework.dto;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Ch13Board {
	private int bno;
	private String btitle;
	private String bcontent;
	private Date bdate;
	private String mid;
	private int bhitcount;
	
	private String battachoname; // Oracle 서버에서 _를 사용한 snake 스타일로 했을 경우 자바에서 carmel 스타일로 적어줘야함
	private String battachsname;
	private String battachtype;
	private byte[] battachdata;
	
	private MultipartFile battach; 
	// -> battach 파일은 MultipartFile로 해야함
	// MultipartFile은 BLOB대신 사용한거고 첨부 파일이 있을 경우 이걸 사용하면 됨
}
