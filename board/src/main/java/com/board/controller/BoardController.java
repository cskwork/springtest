package com.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.domain.BoardVO;
import com.board.service.BoardService;
import com.board.util.Page;

@Controller
@RequestMapping("/board/")
public class BoardController {
	private static final Logger LOGGER = LogManager.getLogger(BoardController.class);	
	
	/*
	public static void main(String[] args) {
	        try {
				LOGGER.info("This is an INFO level log message!");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	}
	*/
	 
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
		
		// 페이징 V2
		Page page = new Page();
		page.setNum(num);
		page.setCount(service.count());
	
		/*
		// 페이징 V1

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
		
		LOGGER.debug("게시물 갯수 "+ count );
		
		// 마지막 번호 재계산
		int endPageNum_tmp = (int)(Math.ceil((double)count / (double)pageNum_cnt));
		if(endPageNum > endPageNum_tmp) {
			endPageNum = endPageNum_tmp;
		}
		
		boolean prev = startPageNum == 1 ? false : true;
		boolean next = endPageNum * pageNum_cnt >= count ? false : true;
		*/
		
		List<BoardVO> list = null;
		list = service.listPage(page.getDisplayPost(), page.getPostNum());
		
		/*
		// 페이징 V1
		// 시작 및 끝 번호
		model.addAttribute("startPageNum", page.getStartPageNum());
		model.addAttribute("endPageNum", page.getEndPageNum());
		model.addAttribute("pageNum", page.getPageNum());	

		// 이전 및 다음 
		model.addAttribute("prev", page.getPrev());
		model.addAttribute("next", page.getNext());	
		*/
				
		model.addAttribute("page", page);
		model.addAttribute("list", list);	
		model.addAttribute("select", num); // 현재 페이지
	}
	
	// 게시물 목록 + 페이징 + 검색 
	@RequestMapping(value="/listPageSearch", method = RequestMethod.GET)
	public void getListPageSearch(Model model, @RequestParam("num") int num,
			@RequestParam(value="searchType", required=false, defaultValue="title") String searchType, 
			@RequestParam(value="keyword", required=false, defaultValue="") String keyword) throws Exception{
		
		// 페이징 
		Page page = new Page();
		page.setNum(num);
		page.setCount(service.searchCount(searchType, keyword));
		
		// 검색 타입과 검색어
		//page.setSearchTypeKeyword(searchType, keyword);
		page.setSearchType(searchType);
		page.setKeyword(keyword);
		
		List<BoardVO> list = null;
		list = service.listPageSearch(page.getDisplayPost(), page.getPostNum(), searchType, keyword);
		
		LOGGER.debug("SEARCH keyWord : " + keyword);
		model.addAttribute("page", page);
		model.addAttribute("list", list);		
		model.addAttribute("select", num); // 현재 페이지
		
		// 검색 상태 유지 작업 
		//model.addAttribute("searchType", searchType);
		//model.addAttribute("keyWord", keyword); 
	}
}


