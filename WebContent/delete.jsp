<%@page import="com.douzone.guestbook.dao.GuestBookDao"%>
<%@page import="com.douzone.guestbook.vo.GuestBookVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% 
   	request.setCharacterEncoding("UTF-8");
   
   String no= request.getParameter("no");
   String password= request.getParameter("password");
   
   GuestBookVo vo=new GuestBookVo();
   vo.setNo(Integer.parseInt(no));
   vo.setPassword(password);
   
   new GuestBookDao().delete(vo);
   
   	response.sendRedirect("/guestbook1");
   
%>