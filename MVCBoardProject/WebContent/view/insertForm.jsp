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
				alert("������ �Է��ϼ���");
				title.focus();
			} else if (name.value == "") {
				alert("�̸��� �Է��ϼ���");
				name.focus();
			} else if (pass.value == "") {
				alert("��й�ȣ�� �Է��ϼ���");
				pass.focus();
			} else if (content.value == "") {
				alert("������ �Է��ϼ���");
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
	<td>��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
	<input type=input name='title' id="title" size=42></td>
</tr>
<tr>
	<td>��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
	<input type=input name='name' id="name" size=42 value='${login }'></td>
</tr>
<tr>
	<td>��й�ȣ <input type=password name='pass' id="pass" size=42></td>
</tr>
<tr>
	<td colspan=2><textarea rows = 10 cols=50 name='content' id="content"></textarea></td>
</tr>
<tr>
	<td><center>
		<input type=button value=���� onclick="checkInput();">
		<input type=reset value=���></center>
	</td>
</tr>
</table>
</form>
<p>
	<a href="/board/list.bod">�ʱ�ȭ��</a>&nbsp;&nbsp;&nbsp;
	</center>

</BODY></HTML>
