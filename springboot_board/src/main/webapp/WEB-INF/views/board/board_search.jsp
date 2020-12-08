<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/bootstrap.css">
<script src="${pageContext.request.contextPath}/resources/static/js/board/board.js"></script>
<title>Insert title here</title>
</head>
<body>
<div id="board_div">
<table class="table table-hover" style="width:1000px; margin:0 auto;">
	  <thead>
	    <tr>
	      <th scope="col"></th>
	      <th scope="col">글번호</th>
	      <th scope="col">제목</th>
	      <th scope="col">내용</th>
	      <th scope="col">작성자</th>
	      <th scope="col">작성일</th>
	    </tr>
	  </thead>
	  <tbody>
	  	<c:forEach var="board" items="${boardList}">
		    <tr class="table-primary">
		      <td><input type="checkbox" id="${board.board_num}"></td>
		      <th scope="row">${board.board_num}</th>
		      <td>${board.board_subject}</td>
		      <td id="content${board.board_num}"><input type='hidden' value='${board.board_num}'>${board.board_content}</td>
		      <td>${board.board_writer}</td>
		      <td>${board.board_date}</td>
		    </tr>
	    </c:forEach>
	  </tbody>
	</table>
	<div style="margin:0 auto; width:100%;">
	  <input type="hidden" id="type" value="${type}">
	  <input type="hidden" id="search" value="${search}">
	  <ul class="pagination">
	  	<c:if test="${startPage ne 1}">
	      <li class="page-item">
	        <a class="page-link" id="prev_search_list">&laquo;</a>
	      </li>
	    </c:if>
	    <c:forEach var="i" begin="${startPage}" end="${endPage}">
	    	<c:if test="${i eq currentPage}">
	    	    <li class="page-item active" id="page_load_search">
			      <a class="page-link" id="${i}">${i}</a>
			    </li>
	    	</c:if>
	    	<c:if test="${i ne currentPage}">
			    <li class="page-item" id="page_load_search">
			      <a class="page-link" id="${i}">${i}</a>
			    </li>
	    	</c:if>
	    </c:forEach>
	    <c:if test="${endPage < pageCount }">
		    <li class="page-item">
		      <a class="page-link" id="next_search_list">&raquo;</a>
		    </li>
	    </c:if>
	  </ul>
	</div>
</div>
</body>
</html>