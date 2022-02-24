<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.user" %>
<%
user theAccount = (user) session.getAttribute("loginUser");
if(theAccount == null){%>
	<jsp:forward page="/memomo/login.jsp" />	
<% } %>
 <%
//セッションから取得
String status = (String) request.getAttribute("status");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>configcheck</title>
</head>
<body>
<h1>ログイン</h1>

<% if(status == "not"){%>
<p>ユーザー名かパスワードが一致しません</p>
<% } %>
<form action="UserConfig" method="post">
<input type="hidden" name="command" value="CHECK" />
ユーザー名:<input type="text" name="username"><br/>
パスワード:<input type="password" name="password"><br/>
<input type="submit" value="ユーザーチェック">

</form>

<br/>
<form action="UserConfig" method="post" >
<input type="hidden" name="command" value="BACK" />
<input type="submit" value="メインページへ">
</form>
</body>
</html>