package com.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.domain.BoardVO;
import com.board.service.BoardService;

@Controller
@RequestMapping("/board/")
public class BoardController {
	
	@Inject
	BoardService service;
	
	// 게시물 목록
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public void getList(Model model) throws Exception{
	
		List<BoardVO> list = null;
		list = service.list();
		model.addAttribute("list", list);	
	}
	
	// 게시물 작성 GET
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	 void getWrite() throws Exception {
	   
	}
	
	// 게시물 작성 POST
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String posttWrite(BoardVO vo) throws Exception {
	  
	  service.write(vo);
	  return "redirect:/board/list";
	}
	
	// 게시물 조회
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public void getView(@RequestParam("bno") int bno, Model model) throws Exception {
		
		BoardVO vo = service.view(bno);
		model.addAttribute("view", vo);
	}
	
	// 게시물 수정 GET
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public void getEdit(@RequestParam("bno") int bno, Model model) throws Exception{
		
		BoardVO vo = service.view(bno);
		model.addAttribute("view", vo);
	}
	
	// 게시물 수정 POST
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String postEdit(BoardVO vo) throws Exception{
		
		service.edit(vo);
		return "redirect:/board/view?bno="+vo.getBno();
	}
	
	// 게시물 삭제 GET
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String getDelete(@RequestParam("bno") int bno) throws Exception{
		
		service.delete(bno);
		return "redirect:/board/list";
	}
	
	// 게시물 목록 + 페이징
	@RequestMapping(value="/listPage", method = RequestMethod.GET)
	public void getListPage(Model model, @RequestParam("num") int num) throws Exception{
	
		// 게시물 총 갯수 
		int count = service.count();
		
		// 한 페이지에 출력할 게시물 갯수
		int postNum = 20;
		
		// 하단 페이징 번호 ([ 게시물 총 갯수 / 한 페이지에 출력할 갯수])
		int pageNum = (int)Math.ceil((double)count/postNum);
		
		// 출력할 게시물
		int displayPost = (num - 1) * postNum;
		
		// 한번에 표시할 페이징 번호의 갯수
		int pageNum_cnt = 10;
		
		// 표시되는 페이지 번호 중 마지막 번호
		int endPageNum = (int)(Math.ceil((double)num / (double)pageNum_cnt)
				* pageNum_cnt);
		
		// 표시되는 페이지 번호 중 첫번째 번호
		int startPageNum = endPageNum - (pageNum_cnt -1);
		
		List<BoardVO> list = null;
		list = service.listPage(displayPost, postNum);
		model.addAttribute("list", list);	
		model.addAttribute("pageNum", pageNum);	
	}
}


