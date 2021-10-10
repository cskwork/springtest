package com.board.service;

import java.util.List;

import com.board.domain.BoardVO;

public interface BoardService {

	public List list() throws Exception;
	
	public void write(BoardVO vo) throws Exception;
	
	public BoardVO view(int bno) throws Exception;
	
	public void edit(BoardVO vo) throws Exception;
	
}
