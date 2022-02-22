<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.user" %>
<%
user theAccount = (user) session.getAttribute("loginUser");
if(theAccount == null){%>
	<jsp:forward page="/memomo/login.jsp" />	
<% } %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memomo-main</title>
</head>
<body>
<h1>ログインユーザー:${loginUser.username}</h1>
<form action="UserController" method="get">
<input type="submit" value="ログアウト">
</form>

<br/>

<form action="UserConfig" method="get" >
<input type="submit" value="ユーザー設定">


<br/><br/>

	<table>
			<tr>
				<th>メモ一覧</th>	
			</tr>
			
			<c:forEach var="Memo" items="${MemoList}">
			
			<tr>
				<td>${Memo.daimei}</td>
			</tr>
			
			</c:forEach>
	
	
	</table>

</form>
</body>
</html>