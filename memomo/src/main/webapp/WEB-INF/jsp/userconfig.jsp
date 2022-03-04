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

  <meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="css/index3.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

  <meta name="theme-color" content="#fafafa">


</head>
<body class="zentai">

<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<br/>
<br/>


	<div class="container
				border border-5 rounded-start">
				
				
		<div class="d-flex justify-content-center">
				<h1 class="display-4 d-flex justify-content-center"
					">${loginUser.username}</h1>
		</div>
		
		<br/>
		<br/>
		<br/>
		
		<div class="d-flex justify-content-center">
				<form action="${pageContext.request.contextPath}/UserConfig" method="post">
						<input type="hidden" name="command" value="DELETE" />
						<button type="submit" class="btn btn-danger" onclick="if(!(confirm('削除します、よろしいですか？'))) return false" >ユーザー削除</button>
				</form>
		</div>

	</div>
</body>
</html>