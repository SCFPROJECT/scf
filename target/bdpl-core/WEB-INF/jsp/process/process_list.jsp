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
  <form action="<%=basePath%>/process/toDeploy" enctype="multipart/form-data" method="post">
  <input type="file" name="processFile" id="processFile"/>&nbsp;&nbsp;&nbsp;<button type="submit">发布</button>
  </form>
  	<table border="1">
  		<tr>
  			<td>流程ID</td>
  			<td>流程名称</td>
  			<td>流程key</td>
  			<td>版本</td>
  			<td>流程发布ID</td>
  			<td>流程资源名称</td>
  			<td>流程图名称</td>
  			<td>操作</td>
  		</tr>
  	
    <c:forEach items="${procList}" var="process">
    	<tr>
    		<td>${process.id_}</td>
  			<td><a target="_blank" href="<%=basePath%>/process/showPng?id=${process.deployment_id_}&pngName=${process.dgrm_resource_name_}" rel="external nofollow">${process.name_}</a></td>
  			<td>${process.key_}</td>
  			<td>${process.version_}</td>
  			<td>${process.deployment_id_}</td>
  			<td>${process.resource_name_}</td>
  			<td>${process.dgrm_resource_name_}</td>
  			<td><a href="<%=basePath%>/process/startProcdef?id=${process.id_}">启动</a></td>
  		</tr>
    </c:forEach>
    </table>
  </body>
</html>
