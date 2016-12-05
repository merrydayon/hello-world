<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>

<form id ="f" method="post" action="/board/loginProcess.bod">

	ID: <input type="text" name="login"><br>
	PASS: <input type="password" name="pass"><br>	

	<input type="submit" value="Login">		
	<input type="reset" value="Reset">

</form>

<br><a href="/board/list.bod">초기화면</a>
</body>
</html>

