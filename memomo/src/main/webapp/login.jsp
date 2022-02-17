<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.user" %>
<%
user theAccount = (user) session.getAttribute("loginUser");
if(theAccount != null){%>
	<jsp:forward page="/WEB-INF/jsp/main.jsp" />	
<% } %>
 <%
//セッションから取得
String status = (String) session.getAttribute("status");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memomo-login</title>
</head>
<body>
<h1>ログイン</h1>

<% if(status == "no user"){%>
<p>ユーザー名かパスワードが間違っています</p>
<% } %>
<form action="UserController" method="post"/>
ユーザー名:<input type="text" name="username"><br/>
パスワード:<input type="password" name="password"><br/>
<input type="submit" value="ログイン">

</form>
</body>
</html>