<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg=(String)request.getAttribute("msg");
	String again=(String)request.getAttribute("again");
%>
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>메세지</title>
</head>
<body>
	<script>
		alert('<%=msg%>');
		location.replace('<%=request.getContextPath()%><%=again%>');
	</script>
</body>
</html>