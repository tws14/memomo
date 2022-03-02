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
					">memomemo</h1>

		</div>

<br/>
<% if(status == "not daimei"){%>
					<p class="lead d-flex justify-content-center text-danger">
					題名は入力してください</p>
<% } %>
<br/>

		<div class="d-flex justify-content-center">
 		
 			<div class="hyou
					border border-5 rounded-start w-75">
 		
 				
 				
				<form action="MemoController" method="get"/>
					
						<input type="hidden" name="MemoCommand" value="UPDATE"/>
						<input type="hidden" name="memoid" value="${memoid}"/>

				<div class="mb-2">
  					<label for="FormControlInput1" class="form-label">メモ名</label>
  				</div>
  				<div class="d-flex justify-content-center">
  					<input type="text" name="daimei" class="form-control w-75" id="FormControlInput1" value="${daimei}">
				</div>
				
				<div>
 				 	<label for="FormControlTextarea1" class="form-label">メモ内容</label>
 				 </div>
 				 <div class="d-flex justify-content-center">
 				 	<textarea name="honbun" class="form-control w-75" id="FormControlTextarea1" rows="10">${honbun}</textarea>
				</div>
				
				<div class="d-flex justify-content-end">
					<button type="submit" class="btn btn-secondary">編集</button>
				</div>

				</form>
				
			</div>
		
		</div>

	</div>
</body>
</html>