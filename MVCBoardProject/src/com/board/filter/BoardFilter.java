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
		
		
		if (reqString.equals("/list.bod")) {  //�ʱ�ȭ��
			bc.list(req, res);
			
		}else if (reqString.equals("/read.bod")){  //�б�ȭ��
			bc.read(req, res);
			
		}else if (reqString.equals("/insertForm.bod")){  //���۵��ȭ�� 
			bc.insertForm(req, res);
			
		}else if (reqString.equals("/insertProcess.bod")){ //���۵��
			bc.insertProcess(req, res);
			
		}else if (reqString.equals("/search.bod")){ //�˻�ȭ��
			bc.search(req, res);
			
		}else if (reqString.equals("/delete.bod")){ //����ȭ��
			bc.delete(req, res);
			
		}else if (reqString.equals("/deleteProcess.bod")){ //����ó��
			bc.deleteProcess(req, res);
			
		}else if (reqString.equals("/modifyForm.bod")){ //����ȭ��
			bc.modifyForm(req, res);
			
		}else if (reqString.equals("/modifyProcess.bod")){ //����ó��
			bc.modifyProcess(req, res);
			
		}else if (reqString.equals("/loginForm.bod")){ //�α���ȭ��
			bc.loginForm(req, res);
			
		}else if (reqString.equals("/loginProcess.bod")){ //�α���ó��
			bc.loginProcess(req, res);
			
		}else if (reqString.equals("/logout.bod")){ //�α׾ƿ�
			bc.logout(req, res);
			
		}else { //����
			bc.myErrPage(req, res, "ó���� �� ���� ��û: "+reqString);
			
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
