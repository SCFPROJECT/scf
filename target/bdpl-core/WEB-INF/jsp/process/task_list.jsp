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
    
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    
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
  			<td>任务ID</td>
  			<td>任务名称</td>
  			<td>任务开始时间</td>
  			<td>操作</td>
  		</tr>
  	
    <c:forEach items="${taskList}" var="task">
    	<tr>
    		<td>${task.id}</td>
  			<td><a target="_blank" href="<%=basePath%>/process/showCurrentPng?taskId=${task.id}" rel="external nofollow">${task.name}</a></td>
  			<td>${task.createTime}</td>
  			<td><a href="<%=basePath%>/process/toTask?id=${task.id}">处理</a></td>
  		</tr>
    </c:forEach>
    </table>
  </body>
</html>
