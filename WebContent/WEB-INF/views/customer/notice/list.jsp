<!-- 초기 설정 (인코딩 등) -->
<%@page import="com.newlecture.web.entity.Notice"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%
	// /customer/notice/list
	//		<-->
	//NoticeListController로 가져가
%>
<!DOCTYPE html> 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<title>index</title>
		<link href="../../css/customer.css" type="text/css" rel="stylesheet" />
	</head>
	<body>
		<!-- header -->
		<jsp:include page="../../inc/header.jsp" />
		<!-- header -->
		<div id="visual" class="customer">
			<div class="top-wrapper">

			</div>
		</div>
		<div id="main">
			<div class="top-wrapper clear">
				<div id="content">
					<h2>공지사항</h2>
					<h3 class="hidden">방문페이지 로그</h3>
					<ul id="breadscrumb" class="block_hlist clear">
						<li>HOME</li>
						<li>
							고객센터
						</li>
						<li>
							공지사항목록
						</li>
					</ul>
					<h3 class="hidden">공지사항 목록</h3>
					<form id="content-searchform" class="article-search-form" action="notice.jsp" method="get">
						<fieldset>
							<legend class="hidden">
								목록 검색 폼
							</legend>
							<input type="hidden" name="pg" value="" />
							<label for="f"
							class="hidden">검색필드</label>
							<select name="f">
								<option value="TITLE">제목</option>
								<option value="CONTENT">내용</option>
							</select>
							<label class="hidden" for="q">검색어</label>
							<input type="text"
							name="q" value="" />
							<input type="submit" value="검색" />
						</fieldset>
					</form>
					<table class="article-list margin-small">
						<caption class="hidden">
							공지사항
						</caption>
						<thead>
							<tr>
								<th class="seq">번호</th>
								<th class="title">제목</th>
								<th class="writer">작성자</th>
								<th class="regdate">작성일</th>
							<th class="hit">조회수</th>
							</tr>
						</thead>
						<tbody>

							<% 
							/* request.setAttribute("a","안녕hello"); */
								/* request.setAttribute("b",new int[] {12,3,4,5,6});
								
									
								List<Notice> list = (List<Notice>) request.getAttribute("list");
								request.setAttribute("c",list);
								for(Notice n : list){
									request.setAttribute("notice", n); */
							%>
							<c:forEach var="n" items="${list}">
								<tr>
									<%-- <td class="seq"><%=n.getId()%></td> --%>
									<td class="seq">${n.id} <%-- ${b[3]}  ${c[1].title} --%> </td>
									<td class="title"><a href="detail?id=${n.id}">${n.title}</a></td>
									<td class="writer"><%-- <%=n.getwriterId()%> --%> ${n.writerId}</td>
									<td class="regdate">${n.regDate }</td>
									<td class="hit">${n.hit }</td>
								</tr>
							</c:forEach>
							<%
							//	}
							%>

						</tbody>
					</table>
					<p class="article-comment margin-small">
						<a class="btn-write button" href="reg">글쓰기</a>
					</p>
					<p id="cur-page" class="margin-small">
						<span class="strong">1</span> /
						10	page
					</p>
					<div id="pager-wrapper" class="margin-small">
						<div class="pager clear">
							<p id="btnPrev">
								<a class="button btn-prev"
								href="notice.jsp">이전</a>
							</p>
							<ul>
							<%-- <c:forEach var="i" begin="1" end="5">
								<li>
								<c:if test="${i==1}">
									<a href="" class="strong">${i}</a>
								</c:if>
								<c:if test="${i!=1}">
									<a href="">${i}</a>
								</c:if>
								</li>
							</c:forEach> --%>
							<!-- 기본값을 주자 페이쥐 -->
							<c:set var="page" value="1"/>
							
							<c:if test="${not empty param.p}">
								<c:set var="page" value="${param.p}" />
							</c:if>
							
							<c:forEach var="i" begin="1" end="5">
								<c:set var="cls" value="${i==page?'strong':''}" />		
								<li>
									<a href="?p=${i}" class="${cls}">${i}</a>
								</li>
							</c:forEach>
							</ul>
							<p id="btnNext">
								<span class="button btn-next">다음</span>
							</p>
						</div>
					</div>
				</div>
				<!-- aside 영역 -->
				<jsp:include page="../inc/aside.jsp" />
			</div>
		</div>
		<!-- footer 태그가 있어야 할 곳입니다. -->
		<jsp:include page="../../inc/footer.jsp"></jsp:include>
		<!-- footer 태그가 있어야 할 곳입니다. -->
	</body>
</html>
