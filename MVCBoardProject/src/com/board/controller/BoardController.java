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
		
		// �ϴ� ��ȸ�� ���� ����
		dao.addCount(num);
		
		// �״��� ��ȸ
		BoardVO b = dao.selectOne(num);
		if (b == null) {
			myErrPage(req, res, "���� ã�� ����: "+num);
			return;
		}
		req.setAttribute("b", b);

		myForward(req, res, "/view/read.jsp");
	}
	
	public void insertForm(HttpServletRequest req, HttpServletResponse res) {
		// �α��ο��δ� BoardLoginFilter���� Ȯ���մϴ�
		
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
		
		// �ش���� ��й�ȣ�� ��ȸ�Ͽ�
		String daoPass = dao.getPass(num);
		if (daoPass == null){
			myErrPage(req, res, "�ۺ�й�ȣ ��ȸ ����: "+num);
			return;
		}
		
		// �Է¹��� ��й�ȣ�� ��ġ�ϸ� ����ó��
		// �ƴϸ� ��й�ȣ�Է�ȭ������ �ٽ�(�޽����� �Բ�)
		String uri = null;
		if (pass.equals(daoPass)) {
			dao.delete(num);
			uri = "/view/deleteSuccess.jsp";
		} else {
			req.setAttribute("passInputMsg", "�ۺ�й�ȣ ����ġ �ٽ��Է��ϼ���");
			uri = "/view/passInput.jsp";
		}
		
		myForward(req, res, uri);
	}
	
	public void modifyForm(HttpServletRequest req, HttpServletResponse res) {
		String num = req.getParameter("num");
		BoardVO b = dao.selectOne(num);
		
		if (b == null){
			myErrPage(req, res, "������ ���� ã�� ����: "+num);
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

		// ���� ȭ���� �־����� �� ȭ������ �ƴϸ� ����ȭ������
		String loginURI = (String)session.getAttribute("loginURI");
		if (loginURI == null) {
			loginURI = "list.bod";
		}
		session.setAttribute("loginURI", null);  //�ʿ������ ���Ǻ��� ����
		
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
