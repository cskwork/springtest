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
}