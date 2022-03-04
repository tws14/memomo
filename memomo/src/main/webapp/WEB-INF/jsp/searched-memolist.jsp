<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<title>Search Result</title>

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
					">検索結果</h1>
		</div>
<br/>

	<div class="d-flex justify-content-center">

		<div class="hyou d-flex justify-content-center 
					border border-5 rounded-start w-75">

		<table class="table table-striped w-75 mt-4">
				<tr>
					<th>メモ一覧</th>	
				</tr>
			
			
				<c:choose>
			 				 <c:when test="${status!=null}">
			 				 <td>該当なし</td>
			  			</c:when>
			  
			  <c:otherwise>
			<c:forEach var="Memo" items="${SearchedMemos}">
			
									<!-- 各メモへのリンク -->
								
						<c:url var="templink" value="${pageContext.request.contextPath}/MemoController">
							<c:param name="MemoCommand" value="LOAD" />
							<c:param name="userId" value="${Memo.userid}" />
							<c:param name="Daimei" value="${Memo.daimei}" />
							<c:param name="Honbun" value="${Memo.honbun}" />
							<c:param name="memoId" value="${Memo.memoid}" />
						</c:url>
						
						<c:url var="deletelink" value="${pageContext.request.contextPath}/MemoController">
							<c:param name="MemoCommand" value="DELETE" />
							<c:param name="memoId" value="${Memo.memoid}" />
						</c:url>

			
			<tr>
			
			   			
				<td>
				
					${Memo.daimei}  
					
					<div class="d-flex justify-content-end">
					<a href="${templink}">メモ</a> | <a href="${deletelink}"
														onclick="if(!(confirm('削除します、よろしいですか？'))) return false"
														>削除</a>
														
   					</div>
   				
			   </td>
			   
			</tr>
			
			</c:forEach>
			</c:otherwise>
			</c:choose>
	
	
			</table>
		
		</div>

	 </div>

	
	</div>
</body>
</html>