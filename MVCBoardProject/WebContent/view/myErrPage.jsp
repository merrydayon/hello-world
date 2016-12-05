<%@ page contentType="text/html;charset=euc-kr"%>

 
<br>
<h1>${myErrMsg }</h1>
<br><a href="list.bod">초기화면</a>
<br>sevlet_path: ${requestScope["javax.servlet.forward.servlet_path"] }
<br>pageScope: ${pageScope }
<br>requestScope: ${requestScope }
<br>sessionScope: ${sessionScope }
<br>
