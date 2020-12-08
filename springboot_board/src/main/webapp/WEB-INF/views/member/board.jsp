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
<script>
	history.pushState(null, null, location.href);
	window.onpopstate = function () {
	    history.go(1);
	};
</script>
</head>
<body style="width:1000px; margin: 0 auto;">
	<h1><a href="/member/board" style="color:black; text-decoration:none;">Home</a></h1>
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
		      <td><input type="checkbox" id="checkbox${board.board_num}" value="${board.board_num}"></td>
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
	  <ul class="pagination">
	  	<c:if test="${startPage ne 1}">
	      <li class="page-item">
	        <a class="page-link" id="prev">&laquo;</a>
	      </li>
	    </c:if>
	    <c:forEach var="i" begin="${startPage}" end="${endPage}">
	    	<c:if test="${i eq currentPage}">
	    	    <li class="page-item active" id="pageLoad">
			      <a class="page-link" id="${i}">${i}</a>
			    </li>
	    	</c:if>
	    	<c:if test="${i ne currentPage}">
			    <li class="page-item" id="pageLoad">
			      <a class="page-link" id="${i}">${i}</a>
			    </li>
	    	</c:if>
	    </c:forEach>
	    <c:if test="${endPage < pageCount }">
		    <li class="page-item">
		      <a class="page-link" id="next">&raquo;</a>
		    </li>
	    </c:if>
	  </ul>
	</div>
	</div>
	<form class="form-inline my-2 my-lg-0">
	  <select id="search_type" class="form-control mr-sm-2">
	    <option value=""></option>
	    <option value="board_subject">제목</option>
	    <option value="board_content">내용</option>
	    <option value="board_writer">작성자</option>
	  </select>
      <input class="form-control mr-sm-2" type="text" id="search" placeholder="Search">
      <button class="btn btn-secondary my-2 my-sm-0" id="search_button" type="button">Search</button>
    </form>
    
    <input type="hidden" id="loginId" value="${id}">
    <input type="hidden" id="loginAuthority" value="${authority}">
	<input type='hidden' id='pageBlock' value='${pageBlock}'>
	<input type='hidden' id="startPage" value='${startPage}'>
	<input type='hidden' id='endPage' value='${endPage}'>
	<div style="margin-top:10px;">
	  <button type="button" id="write_article" class="btn btn-primary disabled">글쓰기</button>
	  <c:if test = "${sessionScope.authority eq 'ADMIN'}">
	    <button type="button" id="delete_article" class="btn btn-secondary disabled">삭제(관리자)</button>
	  </c:if>
	  <button type="button" id="logout" class="btn btn-primary disabled">로그아웃</button>
	</div>	
</body>
</html>