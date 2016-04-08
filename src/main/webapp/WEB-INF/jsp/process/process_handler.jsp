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
    
    <title>My JSP 'processhandler.jsp' starting page</title>
    
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
	<table>
		<tr>
			<td>流程ID</td>
			<td>${task.id}</td>
		</tr>
		<tr>
			<td>流程名称</td>
			<td>${task.name}</td>
		</tr>
		<tr>
			<td>流程任务</td>
			<td>${task.name}</td>
		</tr>
		<tr>
			<td>开始时间</td>
			<td>${task.createTime}</td>
		</tr>
		<tr>
			<td>处理流程</td>
			<td>
				<c:forEach items="${transList}" var="trans">
					<a href="<%=basePath%>/process/completeProcdef?id=${task.id}&outcome=${trans}">${trans}</a>
				</c:forEach>
			</td>
		</tr>
	</table>
  </body>
</html>
