<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<script type="text/javascript">
		function checkSearch() {
			var word = document.getElementById("word");
			if (word.value == "") {
				alert("검색어를 입력하세요");
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
		<h1>MVC 게시판</h1>		
		
		<!-- 로그인 -->
		<c:if test="${empty login }">
			<a href="loginForm.bod">login</a>
		</c:if>
		<c:if test="${!(empty login) }">
			${login }님 안녕하세요&nbsp;<a href="logout.bod?name=${login }">logout</a>			
		</c:if>
		<br>
		<br>
		
		<!-- 검색 -->
		<form id="f" method="post" action="/board/search.bod">
			<select name="condition" id="condition">
				<option value="name">글쓴이</option>
				<option value="title">제목</option>
			</select>
			<input type="text" name="word" id="word"/>
			<input type="button" value="검색" onclick="checkSearch();"/>
		</form>

<c:if test="${empty list }">
	조회결과가 없습니다
</c:if>
<c:if test="${!(empty list) }">
		<!-- 조회 -->
		<TABLE BORDER=1 CELLSPACING=1 CELLPADDING = 1><TR>
		<th width=100 bgcolor=#113366><font color=#ffffee size=2>번호</th>
		<th width=200 bgcolor=#113366><font color=#ffffee size=2>제목</th>
		<th width=100 bgcolor=#113366><font color=#ffffee size=2>글쓴이</th>
		<th width=150 bgcolor=#113366><font color=#ffffee size=2>날짜</th>
		<th width=100 bgcolor=#113366><font color=#ffffee size=2>조회수</th>
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
<a href="insertForm.bod">새글쓰기</a>
</body></html>