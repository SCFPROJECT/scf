<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'to_list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
  </head>
  
  <body>
  	<table border="1">
  		<tr>
  			<td>ID</td>
  			<td>NAME</td>
  			<td>EMAIL</td>
  			<td>TELEPHONE</td>
  			<td>AGE</td>
  			<td>Operation</td>
  		</tr>
  	
    <c:forEach items="${userList}" var="u">
    	<tr>
    		<td>${u.id }</td>
  			<td>${u.name }</td>
  			<td>${u.email }</td>
  			<td>${u.telephone }</td>
  			<td>${u.age }</td>
  			<td><a href="<%=basePath%>/user/toupdate?id=${u.id}">update</a>&nbsp;&nbsp;&nbsp;<a href="<%=basePath%>/user/delete?id=${u.id}">Delete</a></td>
  		</tr>
    </c:forEach>
    </table>
  </body>
</html>
