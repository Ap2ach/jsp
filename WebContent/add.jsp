<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%!			//별도의 함수 블럭 
	private int add(int x,int y){
	return x+y;
}
%>
<%
//서비스 함수내에 들어가요 밑에 html도 마찬가지 
	


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보드마카감자떡치킨튀김</title>
</head>
<body>
   <form action="add" method="post">
       <div>
           <label>x : </label>
           <input type="text" name="x" />
       </div>
       <div> 
           <label>y : </label>
           <input type="text" name="y" />
       </div>
       <div>
           <input type="submit" name="cmd" value="덧셈" />
           <input type="submit" name="cmd" value="application" />
           <input type="submit" name="cmd" value="session" />
           <input type="submit" name="cmd" value="cookie" />
       </div>
       <div>
           <label>sum :
           ${sum} - ${param.s} 
           </label>
           <input name="sum" type="hidden"  value="${sum}">
           <span></span>
       </div>
   </form>
	<a href="mypage.jsp">마이페이지</a>
</body>
</html>