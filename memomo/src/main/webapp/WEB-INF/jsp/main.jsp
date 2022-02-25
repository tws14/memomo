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
</form>
<br/>

<form action="MemoController" method="get">
<input type="hidden" name="MemoCommand" value="MEMO" />
<button type="submit">メモる</button>
</form>
<br/><br/>

<form action="MemoController" method="get">
<input type="hidden" name="MemoCommand" value="SEARCH" />
<input type="text" name="word"> : <button type="submit">検索</button>
</form>
<br/>
	<table>
			<tr>
				<th>メモ一覧</th>	
			</tr>
			
			<c:forEach var="Memo" items="${MemoList}">
			
									<!-- 各メモへのリンク -->
								
						<c:url var="templink" value="MemoController">
							<c:param name="MemoCommand" value="LOAD" />
							<c:param name="userId" value="${Memo.userid}" />
							<c:param name="Daimei" value="${Memo.daimei}" />
							<c:param name="Honbun" value="${Memo.honbun}" />
							<c:param name="memoId" value="${Memo.memoid}" />
						</c:url>
						
						<c:url var="deletelink" value="MemoController">
							<c:param name="MemoCommand" value="DELETE" />
							<c:param name="memoId" value="${Memo.memoid}" />
						</c:url>
			
			<tr>
				<td>${Memo.daimei}    
				<a href="${templink}">メモ</a> | <a href="${deletelink}"
														onclick="if(!(confirm('削除します、よろしいですか？'))) return false"
														>削除</a></td>
			</tr>
			
			</c:forEach>
	
	
	</table>

</body>
</html>