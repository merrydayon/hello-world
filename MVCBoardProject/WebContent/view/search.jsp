<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<center>
		<h1>MVC 게시판</h1>		

		<br>
		${param.condition }가 '${param.word }'인 목록
		<br>		

		<c:if test="${empty list }">
			조회결과가 없습니다
		</c:if>
		<c:if test="${!(empty list) }">
			<TABLE BORDER=1 CELLSPACING=1 CELLPADDING = 1><TR>
			<th width=100 bgcolor=#113366><font color=#ffffee size=2>번호</th>
			<th width=200 bgcolor=#113366><font color=#ffffee size=2>제목</th>
			<th width=100 bgcolor=#113366><font color=#ffffee size=2>글쓴이</th>
			<th width=150 bgcolor=#113366><font color=#ffffee size=2>날짜</th>
			<th width=100 bgcolor=#113366><font color=#ffffee size=2>조회수</th>
			</TR>
	
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
<a href="list.bod">초기화면</a>
</body></html>