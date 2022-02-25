<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.user" %>
<%
user theAccount = (user) session.getAttribute("loginUser");
if(theAccount == null){%>
	<jsp:forward page="/login.jsp" />	
<% } %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UserConfig</title>
</head>
<body>
<h1>ログインユーザー:${loginUser.username}</h1>
<form action="UserConfig" method="post">
<input type="hidden" name="command" value="DELETE" />
<input type="submit" value="ユーザー削除" onclick="if(!(confirm('削除します、よろしいですか？'))) return false" />
</form>

<br/>
<form action="UserConfig" method="post" >
<input type="hidden" name="command" value="BACK" />
<input type="submit" value="メインページへ">
</form>

</body>
</html>