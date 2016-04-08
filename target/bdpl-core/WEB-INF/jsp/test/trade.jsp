<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'trade.jsp' starting page</title>
    
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
   <form method="post" accept-charset='GB2312' action="https://www.gnete.com/bin/scripts/OpenVendor/gnete/V36/GetOvOrder.asp" align="center">
	<input type="text" name="MerId" value="${merId }"><br>
	<input type="text" name="OrderNo" value="${orderNo }"><br>
	<input type="text" name="OrderAmount" value="${amount }"><br>
	<input type="text" name="CurrCode" value="${currCode }"><br>
	<input type="text" name="OrderType" value="${orderType }"><br>
	<input type="text" name="CallBackUrl" value="${callBackUrl }"><br>
	<input type="text" name="LangType" value="${langType }"><br>
	<input type="text" name="BuzType" value="${buzType }"><br>
	<input type="text" name="SignMsg" value="${signMsg }"><br>
	<input type="submit" value="提交"/>
</form>
  </body>
</html>
