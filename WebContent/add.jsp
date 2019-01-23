
<%@page import="com.douzone.guestbook.dao.GuestBookDao"%>
<%@page import="com.douzone.guestbook.vo.GuestBookVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<% 
   	request.setCharacterEncoding("UTF-8");
   
   String name= request.getParameter("name");
   String password= request.getParameter("password");
   String message= request.getParameter("message");
   
   GuestBookVo vo=new GuestBookVo();
   vo.setName(name);
   vo.setPassword(password);
   vo.setText(message);
   
   
   new GuestBookDao().insert(vo);
   
   response.sendRedirect("/guestbook1");
   
%>
