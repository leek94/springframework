package com.mycompany.springframework.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mycompany.springframework.dao.Ch12DaoInterface;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Ch12Service9 {
	
	// 필드 주입
	@Autowired @Qualifier("ch12Dao6")private Ch12DaoInterface field1;
	
	@Resource(name="ch12Dao7") private Ch12DaoInterface field2;
	
	// 이름으로 주입해야 하는 상황이 이런 상황임 
	// 같은 인터페이스를 구현하는데 필드 주입시 에러 나므로 이름을 적어주면 에러 안남
	// @Autowired의 @Qualifier나 @Resource의 name="" 을 적지 않으면 탑입으로 찾아감 -> 2개면 에러남
	// Dao6, Dao7이 주입 될 수 있음 -> 대입 될 수 있는게 2개일 경우  => 에러 발생
	
	// 관리 객체가 1개 뿐일 때는 되는데 2개 이상일 경우 이름을 적어주자
	@Autowired
	public Ch12Service9(@Qualifier("ch12Dao6") Ch12DaoInterface field1) {
		
	}
	
	@Resource(name="ch12Dao7")
	public void setFiled2(Ch12DaoInterface field2) {
		this.field2 = field2;
	}

	

	
}
