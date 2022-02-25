<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//セッションから取得
String status = (String) request.getAttribute("status");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
</head>
<body>
<h1>新規登録</h1>

<% if(status == "tourokuzumi"){%>
<p>そのユーザー名は既に登録されています</p>
<% } else if(status == "error"){ %>
<p>ユーザー名とパスワードは空白を含まない４字以上１６字以下で登録してください</p>
<%} else if(status == "different"){ %>
<p>パスワードが一致しません</p>
<% } %>

<form action="UserTourokuController" method="post"/>
ユーザー名:<input type="text" name="username"><br/>
パスワード:<input type="password" name="password"><br/>
確認用パスワード:<input type="password" name="repass"><br/>
<input type="submit" value="登録">

</form>

</body>
</html>