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
<form id="f" method=post action= '/board/modifyProcess.bod' >
<table>
<tr>
	<td><h1>modify record....</h1>
	</td>
</tr>
<tr>
	<td>��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
	<input id="title" type=input name='title' size=42 value='${b.title }'></td>
</tr>
<tr>
	<td>��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
	<input id="name" type=input name='name' size=42 value='${b.name }'></td>
</tr>
<tr>
	<td>��й�ȣ <input id="pass" type=password name='pass' size=42 value=${b.pass }></td>
</tr>
<tr>
	<td colspan=2><textarea id="content" rows = 10 cols=50 name='content' >${b.content }</textarea></td>
</tr>
<tr>
	<td><center>
		<input type=button value=���� onclick="checkModify();">
		<input type=reset value=���></center>
	</td>
</tr>
</table>
<input type=hidden name='num' value=${param.num }>
</form>
<p>
	<a href="/board/read.bod?num=${param.num }">�ش���б�</a>&nbsp;<a href="/board/list.bod">��üȭ��</a>
	</center>

</BODY></HTML>
