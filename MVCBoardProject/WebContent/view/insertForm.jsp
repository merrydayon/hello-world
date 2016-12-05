<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<HTML>
<HEAD>
	<script type="text/javascript">
		function checkInput() {
			var title = document.getElementById("title");
			var name = document.getElementById("name");
			var pass = document.getElementById("pass");
			var content = document.getElementById("content");
			
			if (title.value == "") {
				alert("제목을 입력하세요");
				title.focus();
			} else if (name.value == "") {
				alert("이름을 입력하세요");
				name.focus();
			} else if (pass.value == "") {
				alert("비밀번호를 입력하세요");
				pass.focus();
			} else if (content.value == "") {
				alert("내용을 입력하세요");
				content.focus();
			} else {
				var f = document.getElementById("f");
				f.submit();
			}						
		}
	</script>
</HEAD>
<BODY>		
<center>
<form id="f" method=post action= '/board/insertProcess.bod' >
<table>
<tr>
	<td><h1>new record.</h1>
	</td>
</tr>
<tr>
	<td>제&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목
	<input type=input name='title' id="title" size=42></td>
</tr>
<tr>
	<td>이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;름
	<input type=input name='name' id="name" size=42 value='${login }'></td>
</tr>
<tr>
	<td>비밀번호 <input type=password name='pass' id="pass" size=42></td>
</tr>
<tr>
	<td colspan=2><textarea rows = 10 cols=50 name='content' id="content"></textarea></td>
</tr>
<tr>
	<td><center>
		<input type=button value=전송 onclick="checkInput();">
		<input type=reset value=취소></center>
	</td>
</tr>
</table>
</form>
<p>
	<a href="/board/list.bod">초기화면</a>&nbsp;&nbsp;&nbsp;
	</center>

</BODY></HTML>
