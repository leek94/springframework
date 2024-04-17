package com.mycompany.springframework.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.springframework.dto.Ch06Cart;
import com.mycompany.springframework.dto.Ch06Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch06")
public class Ch06Controller {
	@GetMapping("/forward")
	public String forward(Model model, HttpServletRequest request) {
		Ch06Member member = new Ch06Member();
		member.setMid("spring");
		member.setMname("김벚꽃");
		member.setMemail("kim@naver.com");
		
		//request 범위에 객체 저장
		//forward할 때 까지 계속 사용할 수 있는 범위
		//응답이 전송되면 request 범위는 사라짐
		model.addAttribute("member1", member);
		request.setAttribute("member2", member);
		
		return "ch06/forward";
	}
	
	@GetMapping("/redirect")
	public String redirect(HttpSession session) {
		log.info("실행");
		
		List<String> items = new ArrayList<>();
		items.add("상품1");
		items.add("상품2");
		items.add("상품3");
		
		Ch06Cart cart = new Ch06Cart();
		cart.setItems(items);
		
		//session 범위에 객체를 저장
		//동일한 client(브라우저)가 사용할 수 있는 범위
		session.setAttribute("cart", cart);
		
		return "redirect:/ch06/sessionData";
	}
	
	@GetMapping("/sessionData")
	public String sessionData(HttpSession session) {
		log.info("실행");
		
		Ch06Cart cart = (Ch06Cart) session.getAttribute("cart");
		if(cart != null) {
			for(String item : cart.getItems()) {
				log.info(item);
			}
		}
		return "ch06/sessionData";
	}
	
	
}
