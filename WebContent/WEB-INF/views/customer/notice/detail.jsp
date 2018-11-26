<%@page import="com.newlecture.web.entity.Notice"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	//사용자가 전달한 id값을 읽어온다. 
	
	/* Notice n =(Notice)request.getAttribute("n");
	request.setAttribute("n",n); */
	//컨트롤러  밑에는 뷰 .... 같이 있어.  이게 모델1
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<title>index</title>
		<link href="../../css/customer.css" type="text/css" rel="stylesheet" />
	</head>
	<body>
		<!-- header들어갈거 . 전체 inc 에서 끌어와 -->
		<jsp:include page="../../inc/header.jsp" />
		<div id="visual" class="customer">
			<div class="top-wrapper">
				
			</div>
		</div>
		<div id="main">
			<div class="top-wrapper clear">
				<div id="content">
					<h2>공지사항</h2>
					<h3 class="hidden">방문페이지위치</h3>
					<ul id="breadscrumb" class="block_hlist">
						<li id="home">
							<a href="">HOME</a>
						</li>
						<li>
							<a href="">고객센터</a>
						</li>
						<li>
							<a href="">공지사항</a>
						</li>
					</ul>
					<div id="notice-article-detail" class="article-detail margin-large" >						
						<dl class="article-detail-row">
							<dt class="article-detail-title">
								제목
							</dt>
							<dd class="article-detail-data">
								<%-- <%=request.getAttribute("title")%> --%>
								${n.title}
							</dd>
						</dl>
						<dl class="article-detail-row">
							<dt class="article-detail-title">
								작성일
							</dt>
							<dd class="article-detail-data">
								<%-- <%=request.getAttribute("regdate")%> --%>
								${n.regDate}
							</dd>
						</dl>
						<dl class="article-detail-row half-row">
							<dt class="article-detail-title">
								작성자
							</dt>
							<dd class="article-detail-data half-data" >
								<%-- <%=request.getAttribute("writer_id")%> --%>
								${n.writerId}
							</dd>
						</dl>
						<dl class="article-detail-row half-row">
							<dt class="article-detail-title">
								조회수
							</dt>
							<dd class="article-detail-data half-data">
								<%-- <%=request.getAttribute("hit")%> --%>
								${n.hit}
							</dd>
						</dl>
						<dl class="article-detail-row">
							<dt class="article-detail-title">
								첨부파일
							</dt>
							<dd class="article-detail-data">
								<a href="../../upload/aa.jpg" download>aa.jpg</a>
							</dd>
						</dl>

						<div class="article-content" >
								<%-- <%=request.getAttribute("content")%> --%>
								${n.content}
							<img src="../../upload/aa.jpg" />
						</div>
					</div>
					<p class="article-comment margin-small">
						<a class="btn-list button" href="list">목록</a>						
						<a class="btn-edit button" href="edit?id=${n.id}">수정</a>
						<a class="btn-del button" href="delete?id=${n.id}">삭제</a>
					</p>
					<div class="margin-small" style="border-top: 1px solid #dfdfdf;">
						<dl class="article-detail-row">
							<dt class="article-detail-title">
								▲ 다음글
							</dt>
							<dd class="article-detail-data">
								<a href="">다음 글이 없습니다.</a>
							</dd>
						</dl>
						<dl class="article-detail-row">
							<dt class="article-detail-title">
								▼ 이전글
							</dt>
							<dd class="article-detail-data">
								<a href="">제 12회 창업스쿨</a>
							</dd>
						</dl>
					</div>					
				</div>				
				<!-- aside 영역 -->
				<jsp:include page="../inc/aside.jsp" />
			</div>
		</div>
		<!-- footer들어갈거 . 전체 inc 에서 끌어와 -->
		<jsp:include page="../../inc/footer.jsp" />
	</body>
</html>
