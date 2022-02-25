<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Result</title>
</head>
<body>
<h1>検索結果</h1>
<br/>
<c:if test="${SeachedMemos==null}">
該当なし
</c:if>
<br/>
	<table>
			<tr>
				<th>メモ一覧</th>	
			</tr>
			
			<c:forEach var="Memo" items="${SearchedMemos}">
			
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

<br/><br/>

<form action="MemoController" method="get" >
<button type="submit">メインページへ</button>
</form>

</body>
</html>