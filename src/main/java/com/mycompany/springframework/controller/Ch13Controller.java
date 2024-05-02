package com.mycompany.springframework.controller;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.springframework.dto.Ch13Board;
import com.mycompany.springframework.dto.Ch13Pager;
import com.mycompany.springframework.service.Ch13Service;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j // 로깅
@RequestMapping("/ch13")
public class Ch13Controller {

	@Autowired
	private Ch13Service service;

	@GetMapping("/writeBoardForm")
	public String writeBoardForm() {
		return "ch13/writeBoardForm";
	}

	@PostMapping("/writeBoard")
	public String writeBoard(Ch13Board board) {
		// 요청 데이터의 유효성 검사
		log.info("original filename: " + board.getBattach().getOriginalFilename());
		log.info("filetype:" + board.getBattach().getContentType());

		// 첨부 파일이 있는 여부 조사
		if (board.getBattach() != null && !board.getBattach().isEmpty()) {
			// 첨부  파일 있으면 -> 파일 이름 추가 및 타입을 추가해줌
			
			// DTO 추가 설정
			board.setBattachoname(board.getBattach().getOriginalFilename());
			board.setBattachtype(board.getBattach().getContentType());

			try {
				board.setBattachdata(board.getBattach().getBytes());
			} catch (Exception e) {

			}
		}
		// 이부분은 DB 설계에 따라서 변경 될 수 있음 grade를 주면 admin, user로 나뉘니깐
		// 로그인된 사용자 아이디 설정
		board.setMid("user");
		
		
		// 비지니스 로직 처리를 서비스로 위임
		service.writeBoard(board);
		return "redirect:/ch13/boardList";
	}

	/*
	 * board.xml 설정 내용 <insert id="insert"
	 * parameterType="com.company.springframework.dto.Ch13Board"> insert into
	 * board(bno, btitle, bcontent, bdate, mid, bhitcount) values(seq_bno.nextval,
	 * #{btitle}, #{bcontent}, sysdate, #{mid}, 0 ) <!-- #{btitle} => btitle의 필드값을
	 * #{}에 넣어준다는 의미--> </insert>
	 */
	
	@GetMapping("/boardList")
	public String boardList(String pageNo, Model model, HttpSession session) {
		if(pageNo == null) { // 처음 들어갈 경우에는 ?pageNo=4 이런식으로 넣어주지 않으면 null이므로 이걸 타고들어감
			
			// pageNo를 받지 못했을 경우, 세션에 저장되어 있는 지 확인
			// session의 경우 pageNo가 이전에 4 페이지였고 현재가 8페이지 일경우 뒤로가시 4페이지로 가는 걸 생각해서 session을 사용해야함
			pageNo = (String) session.getAttribute("pageNo");
			if(pageNo == null) {
				// 세션에 저장되어 있지 않을 경우 "1"로 실제 세팅
				pageNo = "1";
			}
		}
		
		// 세션에 pageNo 변환
		session.setAttribute("pageNo", pageNo);
		
		// 문자열로 받은 pageNo를 정수로 변환
		int intPageNo = Integer.parseInt(pageNo);
		
		// Pager 객체 새성
		int rowsPagingTarget = service.getTotalRow();
		Ch13Pager pager = new Ch13Pager(100, 10, rowsPagingTarget, intPageNo);
		//						페이지당 행수, 한번에 보이는 페이징 수, 전체 행수, 원하는 페이지 넘버	
		
		// Service에서 게시물 목록 요청
		List<Ch13Board> boardList = service.getBoardList(pager);
		
		// JSP에서 사용할 수 있도록 설정
		model.addAttribute("pager", pager);
		model.addAttribute("boardList", boardList);
		return "ch13/boardList";
	}
	
	
	@GetMapping("/detailBoard")
	public String detailBoard(int bno, Model model) {
		
		Ch13Board board = service.getBoard(bno);
		model.addAttribute("board", board);
		return "ch13/detailBoard";
	}
	
	@GetMapping("/attachDownload")
	public void attachDownload(int bno, HttpServletResponse response) throws Exception {
		// 다운로드할 데이터를 준비
		// 리턴을 사용하지 않으면 직접 만들어서 넘겨 줘야함
		Ch13Board board = service.getBoard(bno);
		byte[] data = service.getAttachData(bno);
		
		response.setContentType(board.getBattachtype());
		String fileName = new String(board.getBattachoname().getBytes("UTF-8"), "ISO-8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		
		log.info(board.getBattachtype());
		log.info("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		
		//응답 본문에 파일 데이터 출력
		OutputStream os = response.getOutputStream();
		
		os.write(data);
		os.flush();
		os.close();
		
	}
	
	@GetMapping("/updateBoardForm")
	public String updateBoardForm(int bno, Model model) {
		Ch13Board board = service.getBoard(bno);
		model.addAttribute("board", board);
		return "ch13/updateBoardForm";
	}
	
	@PostMapping("/updateBoard")
	public String updateBoard(Ch13Board board) {
		
		// 첨부 파일이 있는 여부 조사
		if (board.getBattach() != null && !board.getBattach().isEmpty()) {
			// 첨부  파일 있으면 -> 파일 이름 추가 및 타입을 추가해줌
			
			// DTO 추가 설정
			board.setBattachoname(board.getBattach().getOriginalFilename());
			board.setBattachtype(board.getBattach().getContentType());

			try {
				board.setBattachdata(board.getBattach().getBytes());
			} catch (Exception e) {

			}
		}
		// 로그인된 사용자 아이디 설정
		board.setMid("user");
		
		// 비지니스 로직 처리를 서비스로 위임
		service.updateBoard(board);
		return "redirect:/ch13/detailBoard?bno=" + board.getBno();
		
	}
	
	@GetMapping("/deleteBoard")
	public String deleteBoard(int bno) {
		service.removeBoard(bno);
		return "redirect:/ch13/boardList";
	}
	
	
	
	
	
	
}