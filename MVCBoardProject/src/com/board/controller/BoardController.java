package com.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.board.dao.BoardDAO;
import com.board.vo.BoardVO;

public class BoardController {
	private BoardDAO dao = null;
	private HttpSession session = null;
	
	public BoardController() {
		dao = new BoardDAO();
	}

	public void list(HttpServletRequest req, HttpServletResponse res) {
		ArrayList<BoardVO> list = dao.selectAll();
		req.setAttribute("list", list);
		
		myForward(req, res, "/view/list.jsp");
	}
	
	public void read(HttpServletRequest req, HttpServletResponse res) {
		String num = req.getParameter("num");
		
		// 일단 조회수 부터 증가
		dao.addCount(num);
		
		// 그다음 조회
		BoardVO b = dao.selectOne(num);
		if (b == null) {
			myErrPage(req, res, "글을 찾지 못함: "+num);
			return;
		}
		req.setAttribute("b", b);

		myForward(req, res, "/view/read.jsp");
	}
	
	public void insertForm(HttpServletRequest req, HttpServletResponse res) {
		// 로그인여부는 BoardLoginFilter에서 확인합니다
		
		try {
			res.sendRedirect("view/insertForm.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void insertProcess(HttpServletRequest req, HttpServletResponse res) {
		BoardVO b = new BoardVO();
		b.setTitle(req.getParameter("title"));
		b.setName(req.getParameter("name"));
		b.setPass(req.getParameter("pass"));
		b.setContent(req.getParameter("content"));
		
		dao.insert(b);
		
		myForward(req, res, "/view/insertSuccess.jsp");
	}
	
	public void search(HttpServletRequest req, HttpServletResponse res) {
		String condition = req.getParameter("condition");
		String word = req.getParameter("word");
		
		ArrayList<BoardVO> list = dao.search(condition, word);
		req.setAttribute("list", list);
		
		myForward(req, res, "/view/search.jsp");
	}
	
	public void delete(HttpServletRequest req, HttpServletResponse res) {
		req.setAttribute("passInputMsg", "");
		myForward(req, res, "/view/passInput.jsp");
	}
	
	public void deleteProcess(HttpServletRequest req, HttpServletResponse res) {
		String pass = req.getParameter("pass");
		String num = req.getParameter("num");
		
		// 해당글의 비밀번호를 조회하여
		String daoPass = dao.getPass(num);
		if (daoPass == null){
			myErrPage(req, res, "글비밀번호 조회 오류: "+num);
			return;
		}
		
		// 입력받은 비밀번호와 일치하면 삭제처리
		// 아니면 비밀번호입력화면으로 다시(메시지와 함께)
		String uri = null;
		if (pass.equals(daoPass)) {
			dao.delete(num);
			uri = "/view/deleteSuccess.jsp";
		} else {
			req.setAttribute("passInputMsg", "글비밀번호 불일치 다시입력하세요");
			uri = "/view/passInput.jsp";
		}
		
		myForward(req, res, uri);
	}
	
	public void modifyForm(HttpServletRequest req, HttpServletResponse res) {
		String num = req.getParameter("num");
		BoardVO b = dao.selectOne(num);
		
		if (b == null){
			myErrPage(req, res, "수정할 글을 찾지 못함: "+num);
			return;
		}
		req.setAttribute("b", b);
		
		myForward(req, res, "/view/modifyForm.jsp");
	}
	
	public void modifyProcess(HttpServletRequest req, HttpServletResponse res) {
		BoardVO b = new BoardVO();
		b.setNum(req.getParameter("num"));
		b.setTitle(req.getParameter("title"));
		b.setName(req.getParameter("name"));
		b.setPass(req.getParameter("pass"));
		b.setContent(req.getParameter("content"));
		dao.update(b);
		
		myForward(req, res, "/view/modifySuccess.jsp");
	}
	
	public void loginForm(HttpServletRequest req, HttpServletResponse res) {
		try {
			res.sendRedirect("view/loginForm.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loginProcess(HttpServletRequest req, HttpServletResponse res) {
		session = req.getSession(true);
		session.setAttribute("login", req.getParameter("login"));

		// 이전 화면이 있었으면 그 화면으로 아니면 최초화면으로
		String loginURI = (String)session.getAttribute("loginURI");
		if (loginURI == null) {
			loginURI = "list.bod";
		}
		session.setAttribute("loginURI", null);  //필요없어진 세션변수 말소
		
		try {
			res.sendRedirect(loginURI);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void logout(HttpServletRequest req, HttpServletResponse res) {
		session = req.getSession(true);
		session.setAttribute("login", null);
		
		try {
			res.sendRedirect("list.bod");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void myForward(HttpServletRequest req, HttpServletResponse res, String uri) {
		RequestDispatcher dispatcher = req.getRequestDispatcher(uri);
		try {
			dispatcher.forward(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void myErrPage(HttpServletRequest req, HttpServletResponse res, String myErrMsg) {
		req.setAttribute("myErrMsg", myErrMsg);
		myForward(req, res, "/view/myErrPage.jsp");
	}
	
}
