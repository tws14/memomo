<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.user" %>
<%
user theAccount = (user) session.getAttribute("loginUser");
if(theAccount == null){%>
	<jsp:forward page="/login.jsp" />	
<% } %>
<%
//リクエストから取得
String status = (String) request.getAttribute("Dstatus");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MemoForm</title>
</head>
<body>
<h1>memomemo</h1>
<br/>
<% if(status == "not daimei"){%>
<p>題名は入力してください</p>
<% } %>
<br/>
<form action="MemoController" method="get"/>
<input type="hidden" name="MemoCommand" value="ADD" />
メモ名:<input type="text" name="daimei"><br/>
<textarea name="honbun" rows="10" cols="40">
本文を入力してください。
</textarea>	
<br/>
<button type="submit">メモる</button>

</form>
<br/><br/>

<form action="MemoController" method="get" >
<button type="submit">メインページへ</button>
</form>
</body>
</html>