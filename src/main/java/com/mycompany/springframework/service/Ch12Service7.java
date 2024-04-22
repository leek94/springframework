package com.mycompany.springframework.service;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ch12Service7 {
	
	private List<String> list;
	private Set<Object> set;
	private Map<String, String> map;
	private Properties prop;
	
	public void setList(List<String> list) {
		log.info("실행 , 아이템 갯수: " + list.size());
		this.list = list;
	}
	// Object로 지정해서 어떤 값이든 받겠다는 의미
	public void setSet(Set<Object> set) {
		log.info("실행, 아이템 갯수: " + set.size());
		this.set = set;
	}
	
	public void setMap(Map<String, String> map) {
		log.info("실행, 아이템 갯수: " + map.size());
		this.map = map;
	}
	public void setProp(Properties prop) {
		log.info("실행, 아이템 갯수: " + prop.size());
		this.prop = prop;
	}
	
	// classNotFound 에러시 Project > Clean 이후에 재실행하면 에러 해결될 수 있음
	
}
