<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header>
  	<div class="logo">
  	<a href="index.html" style="text-decoration:none;">memomo</a>
  
  	
  	<div class="navlist">
 	 <ul class="nav justify-content-end">
 	 
 	 					<c:url var="logoutlink" value="/UserController">
						
						</c:url>
						
						<c:url var="configlink" value="/UserConfig">

						</c:url>
						
						<c:url var="backlink" value="/MemoController">
		
						</c:url>
 	 		
		 <li class="nav-item">
 		 	<a class="nav-link" href="${logoutlink}">ログアウト</a>
 		 </li>
  		<li class="nav-item">
    		<a class="nav-link" href="${configlink}">ユーザー設定</a>
  		</li>
  		<li class="nav-item">
    		<a class="nav-link" href="${backlink}">メモリスト</a>
 		 </li>
   </ul>
   </div>

　</div>


</header>