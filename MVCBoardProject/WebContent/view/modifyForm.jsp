<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<HTML>
<HEAD>
	<script type="text/javascript">
		function checkModify() {
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
<form id="f" method=post action= '/board/modifyProcess.bod' >
<table>
<tr>
	<td><h1>modify record....</h1>
	</td>
</tr>
<tr>
	<td>제&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목
	<input id="title" type=input name='title' size=42 value='${b.title }'></td>
</tr>
<tr>
	<td>이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;름
	<input id="name" type=input name='name' size=42 value='${b.name }'></td>
</tr>
<tr>
	<td>비밀번호 <input id="pass" type=password name='pass' size=42 value=${b.pass }></td>
</tr>
<tr>
	<td colspan=2><textarea id="content" rows = 10 cols=50 name='content' >${b.content }</textarea></td>
</tr>
<tr>
	<td><center>
		<input type=button value=전송 onclick="checkModify();">
		<input type=reset value=취소></center>
	</td>
</tr>
</table>
<input type=hidden name='num' value=${param.num }>
</form>
<p>
	<a href="/board/read.bod?num=${param.num }">해당글읽기</a>&nbsp;<a href="/board/list.bod">전체화면</a>
	</center>

</BODY></HTML>
