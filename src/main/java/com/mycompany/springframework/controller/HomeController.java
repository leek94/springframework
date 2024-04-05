package com.mycompany.springframework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	@RequestMapping("/")
	public String index() {
		log.info("실행");
		log.info("난 몰라");
		log.info("실행2");
		log.info("실행3420");
		return "home";
		
	}
}
