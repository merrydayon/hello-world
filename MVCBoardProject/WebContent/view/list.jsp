<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<script type="text/javascript">
		function checkSearch() {
			var word = document.getElementById("word");
			if (word.value == "") {
				alert("�˻�� �Է��ϼ���");
				word.focus();
			} else {
				var f = document.getElementById("f");
				f.submit();
			}						
		}
	</script>
	</head>
<body>
<center>
		<h1>MVC �Խ���</h1>		
		
		<!-- �α��� -->
		<c:if test="${empty login }">
			<a href="loginForm.bod">login</a>
		</c:if>
		<c:if test="${!(empty login) }">
			${login }�� �ȳ��ϼ���&nbsp;<a href="logout.bod?name=${login }">logout</a>			
		</c:if>
		<br>
		<br>
		
		<!-- �˻� -->
		<form id="f" method="post" action="/board/search.bod">
			<select name="condition" id="condition">
				<option value="name">�۾���</option>
				<option value="title">����</option>
			</select>
			<input type="text" name="word" id="word"/>
			<input type="button" value="�˻�" onclick="checkSearch();"/>
		</form>

<c:if test="${empty list }">
	��ȸ����� �����ϴ�
</c:if>
<c:if test="${!(empty list) }">
		<!-- ��ȸ -->
		<TABLE BORDER=1 CELLSPACING=1 CELLPADDING = 1><TR>
		<th width=100 bgcolor=#113366><font color=#ffffee size=2>��ȣ</th>
		<th width=200 bgcolor=#113366><font color=#ffffee size=2>����</th>
		<th width=100 bgcolor=#113366><font color=#ffffee size=2>�۾���</th>
		<th width=150 bgcolor=#113366><font color=#ffffee size=2>��¥</th>
		<th width=100 bgcolor=#113366><font color=#ffffee size=2>��ȸ��</th>
		</tr>

		<c:forEach items="${list }" var="b">
		    <tr bgcolor=pink>
			   <td align=center bgcolor=pink>&nbsp;<font size=2>${b.num }</td>
		       
		      <td align=center bgcolor=pink>&nbsp;<font size=2><a href="read.bod?num=${b.num }">${b.title }</a></td>
		       
		      <td align=center bgcolor=pink>&nbsp;<font size=2>${b.name }</td>
		       
		      <td align=center bgcolor=pink>&nbsp;<font size=2>${b.wdate }</td>
		       
		      <td align=center bgcolor=pink>&nbsp;<font size=2>${b.count }</td>
		       
		    </tr>
		</c:forEach>
	</table>
</c:if>
	    
<br></br>
<a href="insertForm.bod">���۾���</a>
</body></html>