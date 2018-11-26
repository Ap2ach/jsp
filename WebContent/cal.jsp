<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	계산기
	<!-- action="유알엘을 적자" method="post" -->
	<form action="cal" method="post">
		<div>
			<input type="text" name="display" value="${display}" readonly="readonly"> 
		</div>
		<div>
			<input type="submit" value="7" name="btn">
			<input type="submit" value="8" name="btn">
			<input type="submit" value="9" name="btn">
			<input type="submit" value="*" name="btn">
		</div>
		<div>
			<input type="submit" value="4" name="btn">
			<input type="submit" value="5" name="btn">
			<input type="submit" value="6" name="btn">
			<input type="submit" value="+" name="btn">
		</div>
		<div>
			<input type="submit" value="1" name="btn">
			<input type="submit" value="2" name="btn">
			<input type="submit" value="3" name="btn">
			<input type="submit" value="-" name="btn">
		</div>
		<div>
			<input type="submit" value="0" name="btn">
			<input type="submit" value="=" name="btn">
		</div>
		<input type="text"  name="second" value="${second}">
		<input type="text"  name="opp" value="${opp}">
	</form>
</body>
</html>