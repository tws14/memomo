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
String status = (String) request.getAttribute("status");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memomo-login</title>

  <meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="css/index2.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

  <meta name="theme-color" content="#fafafa">



</head>


<body class="zentai">

  <header>
  	<div class="logo">
  	<a href="index.html" style="text-decoration:none;">memomo</a>
  
  	</div>
  
  </header>
  
  <div class="container">
  
  		<div class="d-flex justify-content-center">
			<h2 class="display-4 border border-5 rounded-pill 
						d-flex justify-content-center"
						style="width: 300px;" >Sign in</h2>
						
		</div>
				
				<br/>

<% if(status == "no user"){%>
			<p class="lead d-flex justify-content-center text-danger">
				ユーザー名かパスワードが間違っています
			</p>
<% } %>

			<div>
		
					<form action="./UserController" method="post"/>
					
					  <div class="d-flex justify-content-center">
					
						<div class="input-group input-group-lg w-75 p-3">
 								 <span class="input-group-text" id="inputGroup-sizing-lg">ユーザー名</span>
 								 <input type="text" name="username" class="form-control" aria-label="Sizing input" aria-describedby="inputGroup-sizing-lg">
						</div>
						
					   </div>	
						
						<br/>
						
					<div class="d-flex justify-content-center">
						
						<div class="input-group input-group-lg w-75 p-3">
 							<span class="input-group-text" id="inputGroup-sizing-lg">パスワード</span>
 						   <input type="password" name="password" class="form-control" aria-label="Sizing  input" aria-describedby="inputGroup-sizing-lg">
						</div>
						
					</div>
						
							<br/>
						
						<div class="d-flex justify-content-center">
							<button class="btn btn-primary" type="submit">ログイン</button>
						</div>
							

					</form>
			
			</div>
				
	</div>
</body>
</html>