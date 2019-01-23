package com.douzone.guestbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.douzone.guestbook.vo.GuestBookVo;





public class GuestBookDao {
	public boolean insert(GuestBookVo vo) {
		boolean result=false;
		
		Connection conn=null;
		PreparedStatement pstmt = null;
		
		
		try {
			
			conn = getConnection();
			
			String sql="insert"+" into guestbook"+" values (null,?,?,?,now())";
			pstmt= conn.prepareStatement(sql);
			
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getText());
			
			int count=pstmt.executeUpdate();
			result=count==1;
			
		} catch (SQLException e) {
			System.out.println("error:"+e);
		} finally {
			try {
				if(pstmt!=null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	
	public  boolean delete(GuestBookVo vo) {
		boolean result=false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			
			String sql="delete "+" from"+" guestbook"+" where no=? and pasword=?";
			pstmt= conn.prepareStatement(sql);
	
			
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2,vo.getPassword());
			
			int count=pstmt.executeUpdate();
			result=count==1;
			
		} catch (SQLException e) {
			System.out.println("error:"+e);
		} finally {
			try {
				if(pstmt!=null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return result;
	}
	
	public List<GuestBookVo> getList() {
		List<GuestBookVo> list = new ArrayList<GuestBookVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		try {
			conn = getConnection();
			
			String sql=
					"select no,name,pasword,message,reg_date"+ 
					" from guestbook"+ 
					" order by no desc";
			
			pstmt= conn.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			while(rs.next()) {
				long no=rs.getLong(1);
				String name=rs.getString(2);
				String password=rs.getString(3);
				String message=rs.getString(4);
				String regdate=rs.getString(5);
				
				
				GuestBookVo vo=new GuestBookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setPassword(password);
				vo.setText(message);
				vo.setRegDate(regdate);
				
				list.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error" + e);
		} finally {
			try {
				if(rs !=null) {
					rs.close();
				}
				if(pstmt!=null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://127.0.0.1:3306/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}

		return conn;
	}
}
