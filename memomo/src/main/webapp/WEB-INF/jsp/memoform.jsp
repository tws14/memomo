<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MemoForm</title>
</head>
<body>
<h1>memomemo</h1>

<form action="MemoController" method="get"/>
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