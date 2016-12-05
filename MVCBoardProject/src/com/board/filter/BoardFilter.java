package com.board.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.controller.BoardController;

public class BoardFilter implements Filter {

    public BoardFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;

		BoardController bc = new BoardController();
		
		//System.out.println(req.getRequestURI() + ":" + req.getServletPath());
		String reqString = req.getServletPath();
		System.out.println("reqString: "+reqString);
		
		
		if (reqString.equals("/list.bod")) {  //초기화면
			bc.list(req, res);
			
		}else if (reqString.equals("/read.bod")){  //읽기화면
			bc.read(req, res);
			
		}else if (reqString.equals("/insertForm.bod")){  //새글등록화면 
			bc.insertForm(req, res);
			
		}else if (reqString.equals("/insertProcess.bod")){ //새글등록
			bc.insertProcess(req, res);
			
		}else if (reqString.equals("/search.bod")){ //검색화면
			bc.search(req, res);
			
		}else if (reqString.equals("/delete.bod")){ //삭제화면
			bc.delete(req, res);
			
		}else if (reqString.equals("/deleteProcess.bod")){ //삭제처리
			bc.deleteProcess(req, res);
			
		}else if (reqString.equals("/modifyForm.bod")){ //수정화면
			bc.modifyForm(req, res);
			
		}else if (reqString.equals("/modifyProcess.bod")){ //수정처리
			bc.modifyProcess(req, res);
			
		}else if (reqString.equals("/loginForm.bod")){ //로그인화면
			bc.loginForm(req, res);
			
		}else if (reqString.equals("/loginProcess.bod")){ //로그인처리
			bc.loginProcess(req, res);
			
		}else if (reqString.equals("/logout.bod")){ //로그아웃
			bc.logout(req, res);
			
		}else { //오류
			bc.myErrPage(req, res, "처리할 수 없는 요청: "+reqString);
			
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
