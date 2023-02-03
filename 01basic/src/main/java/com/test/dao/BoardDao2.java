package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.dto.BoardDto2;

import lombok.extern.log4j.Log4j;

@Repository
@Log4j
public class BoardDao2 {
	
	@Autowired
	private DataSource ds;

	
	//SELECTAll
	public List<BoardDto2> SelectAll(int startno, int amount) {
		
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<BoardDto2> list = new ArrayList();
		BoardDto2 dto = null;
		

		try {
			conn = ds.getConnection();
			pstmt=conn.prepareStatement("select * from tbl_board2 order by no desc limit ?,?");
			pstmt.setInt(1, startno);
			pstmt.setInt(2, amount);
			rs=pstmt.executeQuery();
			if(rs!=null)
			{
				while(rs.next())
				{
					dto = new BoardDto2();
					dto.setBno(rs.getInt(1));
					dto.setTitle(rs.getString(2));
					dto.setContent(rs.getString(3));
					dto.setWriter(rs.getString(4));
					dto.setRegdate(rs.getString(5));
					dto.setUpdatedate(rs.getString(6));
					list.add(dto);
				}
				
			}	
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		
		}finally {
			try {rs.close();}catch(Exception e) {}
			try {pstmt.close();}catch(Exception e) {}
			try {conn.close();}catch(Exception e) {}
		}
		
		return list;
	}


	
	
	public int getAmount() {
		
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int cnt=0;
		
		try {
			conn = ds.getConnection();
			pstmt=conn.prepareStatement("select count(*) from tbl_board2");
			
			rs=pstmt.executeQuery();
			if(rs!=null)
			{
				rs.next();
				cnt=rs.getInt(1);
				
			}	
		}catch(Exception e) {
			e.printStackTrace();

		}finally {
			try {rs.close();}catch(Exception e) {}
			try {pstmt.close();}catch(Exception e) {}
			try {conn.close();}catch(Exception e) {}
		}
		
		return cnt;
	}
	
	
	public int Insert(BoardDto2 dto) {
		
		Connection conn=null;
		PreparedStatement pstmt = null;
		
		int result=0;
		
		try {
			conn = ds.getConnection();
			pstmt=conn.prepareStatement("insert into tbl_board2 values(null,?,?,?,now(),now())");
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getWriter());
			result=pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {pstmt.close();}catch(Exception e) {}
			try {conn.close();}catch(Exception e) {}
		}
		
		return result;
	}


	public BoardDto2 Select(int bno) {
		
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		BoardDto2 dto = null;
		

		try {
			conn = ds.getConnection();
			pstmt=conn.prepareStatement("select * from tbl_board2 where bno=?");
			pstmt.setInt(1, bno);
			rs=pstmt.executeQuery();
			if(rs!=null)
			{
				while(rs.next())
				{
					dto = new BoardDto2();
					dto.setBno(rs.getInt(1));
					dto.setTitle(rs.getString(2));
					dto.setContent(rs.getString(3));
					dto.setWriter(rs.getString(4));
					dto.setRegdate(rs.getString(5));
					dto.setUpdatedate(rs.getString(6));
				}
				
			}	
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		
		}finally {
			try {rs.close();}catch(Exception e) {}
			try {pstmt.close();}catch(Exception e) {}
			try {conn.close();}catch(Exception e) {}
		}
		
		return dto;
		
		
	}


	public int Update(BoardDto2 dto) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		
		int result=0;
		try {
			conn = ds.getConnection();
			pstmt=conn.prepareStatement("update tbl_board2 set title=?,content=? where bno=?");	
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getBno());
			
			result=pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {pstmt.close();}catch(Exception e) {}
			try {conn.close();}catch(Exception e) {}
		}	
		return result;
		
		
	}


	public int Delete(int bno) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		
		int result=0;
		try {
			conn = ds.getConnection();
			pstmt=conn.prepareStatement("delete from tbl_board2 where bno=?");	
			pstmt.setInt(1, bno);

			result=pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {pstmt.close();}catch(Exception e) {}
			try {conn.close();}catch(Exception e) {}
		}	
		return result;
		
	}
	
}