<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>

<form id ="f" method="post" action="/board/deleteProcess.bod">

	PASS: <input type="password" name="pass"><br>
	<input type="hidden" value="${param.num }" name="num">
	<input type="submit" value="Confirm">		
</form>
<br>
<a href="list.bod">전체화면</a>&nbsp;<a href="read.bod?num=${param.num }">해당글읽기</a>

<br>
<br>
<h1><font color="red">${passInputMsg }</font></h1>

</body>
</html>

