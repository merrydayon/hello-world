package com.board.service;

import java.util.ArrayList;

import com.board.vo.BoardVO;

public interface BoardService {
	public ArrayList<BoardVO> selectAll();
	public ArrayList<BoardVO> search(String condition, String word);
	public BoardVO selectOne(String num);
	public void insert(BoardVO b);
	public void delete(String num);
	public void update(BoardVO b);
	
}
