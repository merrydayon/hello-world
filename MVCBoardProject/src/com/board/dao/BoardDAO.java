package com.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.board.service.BoardService;
import com.board.vo.BoardVO;

public class BoardDAO implements BoardService {
	private DataSource ds = null;
	
	public BoardDAO() {
		try {
			InitialContext context = new InitialContext();
			ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<BoardVO> selectAll() {
		String sql = " select"
				   + " num"
				   + ",pass"
				   + ",name"
				   + ",wdate"
				   + ",title"
				   + ",content"
				   + ",count"
				   + " from board"
				   + " order by num desc "
				   ;
		
		ArrayList<BoardVO> list =null;

		try {
			Connection con = ds.getConnection();
			PreparedStatement pstat = con.prepareStatement(sql);
			ResultSet rs = pstat.executeQuery();

			// select 유무확인
			if (rs.next()) {
				list = new ArrayList<BoardVO>();
				
				do {
					BoardVO b = new BoardVO();
					b.setNum(rs.getString(1));
					b.setName(rs.getString(3));
					b.setWdate(rs.getString(4));
					b.setTitle(rs.getString(5));
					b.setCount(rs.getString(7));
					
					list.add(b);
				}while(rs.next());
			}
			
			pstat.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public BoardVO selectOne(String num) {
		String sql = " select"
				   + " num"
				   + ",pass"
				   + ",name"
				   + ",wdate"
				   + ",title"
				   + ",content"
				   + ",count"
				   + " from board"
				   + " where num = ?"
				   ;

		BoardVO b = null;
		
		try {
			Connection con = ds.getConnection();
			PreparedStatement pstat = con.prepareStatement(sql);
			pstat.setString(1, num);
			ResultSet rs = pstat.executeQuery();
			
			if (rs.next()) {
				b = new BoardVO(rs.getString(1)
						      , rs.getString(2)
						      , rs.getString(3)
						      , rs.getString(4)
						      , rs.getString(5)
						      , rs.getString(6)
						      , rs.getString(7)
						      );
			}
			
			pstat.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return b;
	}

	@Override
	public void insert(BoardVO b) {
		String sql = " insert into board("
				   + " num"
				   + ",pass"
				   + ",name"
				   + ",wdate"
				   + ",title"
				   + ",content"
				   + ",count"
				   + ")values("
				   + " bnum.nextval"
				   + ",?"
				   + ",?"
				   + ",sysdate"
				   + ",?"
				   + ",?"
				   + ",0"
				   + ")"
				   ;
		
		try{
			Connection con = ds.getConnection();
			PreparedStatement pstat = con.prepareStatement(sql);
			pstat.setString(1, b.getPass());
			pstat.setString(2, b.getName());
			pstat.setString(3, b.getTitle());
			pstat.setString(4, b.getContent());
			
			int iCnt = pstat.executeUpdate();
			System.out.println("insertCount: "+iCnt);
			
			pstat.close();
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String num) {
		String sql = " delete"
				   + " from board"
				   + " where num=?"
				   ;
		
		try{
			Connection con = ds.getConnection();
			PreparedStatement pstat = con.prepareStatement(sql);
			pstat.setString(1, num);
			
			int dCnt = pstat.executeUpdate();
			System.out.println("deleteCount: "+dCnt);
			
			pstat.close();
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void update(BoardVO b) {
		String sql = " update board"
				   + " set    "
				   + " pass = ?"
				   + ",name = ?"
				   + ",wdate = sysdate"
				   + ",title = ?"
				   + ",content = ?"
				   + " where  num = ?"
				   ;
		
		try{
			Connection con = ds.getConnection();
			PreparedStatement pstat = con.prepareStatement(sql);
			pstat.setString(1, b.getPass());
			pstat.setString(2, b.getName());
			pstat.setString(3, b.getTitle());
			pstat.setString(4, b.getContent());
			pstat.setString(5, b.getNum());
			
			int uCnt = pstat.executeUpdate();
			System.out.println("updateCount: "+uCnt);
			
			pstat.close();
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<BoardVO> search(String condition, String word) {
		String sql = " select"
				   + " num"
				   + ",pass"
				   + ",name"
				   + ",wdate"
				   + ",title"
				   + ",content"
				   + ",count"
				   + " from board"
				   + " where " + condition + " like ?"
				   + " order by num desc"
		           ;
		
		ArrayList<BoardVO> list =null;

		try {
			Connection con = ds.getConnection();
			PreparedStatement pstat = con.prepareStatement(sql);
			pstat.setString(1, "%"+word+"%");
			ResultSet rs = pstat.executeQuery();

			// select 유무 확인
			if (rs.next()) {
				list = new ArrayList<BoardVO>();
				
				do {
					BoardVO b = new BoardVO();
					b.setNum(rs.getString(1));
					b.setName(rs.getString(3));
					b.setWdate(rs.getString(4));
					b.setTitle(rs.getString(5));
					b.setCount(rs.getString(7));
					
					list.add(b);
				}while(rs.next());
			}
			
			pstat.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public void addCount(String num){
		String sql = " update board"
				   + " set"
				   + " count = count+1"
				   + " where  num = ?"
				   ;
		
		try {
			Connection con = ds.getConnection();
			PreparedStatement pstat = con.prepareStatement(sql);
			pstat.setString(1, num);
			int uCnt = pstat.executeUpdate();
			System.out.println("addCount");

			pstat.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getPass(String num){
		String pass = null;
		
		BoardVO b = null;
		b = selectOne(num);
		
		if (b != null) {
			pass = b.getPass();
		}
		
		return pass;
	}
}
