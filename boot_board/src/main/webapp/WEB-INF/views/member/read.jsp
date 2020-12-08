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
<script src="${pageContext.request.contextPath}/resources/static/js/board/read.js"></script>
<script src="${pageContext.request.contextPath}/resources/static/js/board/comment.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<form>
				<input type="hidden" id="number" value="${vo.board_num}">
				<input type="hidden" id="writer" value="${vo.board_writer}">
				<table class="table table-striped" style="width:1200px; text-align: center; border: 1px solid #dddddd">
					<thead>
						<tr>
							<th colspan="2"
								style="background-color: #eeeeee; text-align: center;">게시글 내용</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td style="width:100px;">작성자</td>
							<td>
								<input id="board_writer" style="background-color:white;" type="text" class="form-control" name="bbsTitle" maxlength="50"
							   value="${vo.board_writer}" readonly/>
							</td>
						</tr>
						<tr>
							<td style="width:100px;">작성일</td>
							<td>
								<input style="background-color:white;" type="text" class="form-control" name="bbsTitle" maxlength="50"
							   value="${vo.board_date}" readonly/>
							</td>
						</tr>
						<tr>
							<td style="width:100px;">제목</td>
							<td>
							  <input style="background-color:white;" type="text" class="form-control" name="bbsTitle" maxlength="50"
							   value="${vo.board_subject}" readonly/>
							</td>
						</tr>
						<tr>
							<td style="width:100px;">내용</td>
							<td>
							  <textarea class="form-control" style="height:350px; background-color:white;" readonly>${vo.board_content}</textarea>
							</td>
						</tr>
						<tr>
							<td style=":width:100px;">첨부 파일</td>
							<td id="attached_file">${vo.board_file}</td>
						</tr>
					</tbody>
				</table>	
				<input type="button" id="update" class="btn btn-primary pull-right" value="수정" />
				<input type="button" id="delete" class="btn btn-primary pull-right" value="삭제" />
			</form>
		</div>
		
		<div>
		  <form>
		    
		  </form>
		</div>
	</div>
	<div class="container" style="margin-top:10px;">
	  <div class="row">
	    <form>
	      <table class="table table-striped" style="width:1200px; text-align: center; border: 1px solid #dddddd">
	  	    <tr>
		      <td>작성자 : </td>
		      <td>
			    <textarea rows="2" cols="100" id="comment_content" class="form-control" style="background-color:white;"></textarea>
		      </td>
		      <td>
		        <button type="button" id="comment_register" class="btn btn-primary pull-right">등록</button>
		      </td>
		    </tr>
	      </table>
	    </form>
	  </div>
	  <div class="row">
	    <form>
	      <table class="table table-striped" style="width:1200px; text-align: center; border: 1px solid #dddddd">
	        <thead>
	          <c:forEach var="comment" items="${dtos}">
	            <tr>
  	              <td style="width:100px; text-align:left;">
  	                <c:if test="${comment.comment_level > 1}">
					  <c:set var="wid" value="${(comment.comment_level -1) * 10}"/>
						&nbsp;
					</c:if> <!-- 이거 살펴보니 공백이미지였네!!! -->
					
					<!-- 답글인 경우 : 들여쓰기 > 0 -->
					<c:if test="${comment.comment_level > 0}">
					  -->
					</c:if>
  	                ${comment.comment_writer}
  	              </td>
  	              <td>
  	                <input style="background-color:white;" type="text" class="form-control" name="bbsTitle" maxlength="50"
						value="${comment.comment_content}" readonly/>
				  </td>
				  <td style="width:200px;">
				    ${comment.comment_date}
				    <input type="button" id="comment_reply${comment.comment_num}" class="btn btn-primary pull-right" value="답글">
				    <input type="button" id="comment_delete${comment.comment_num}" class="btn btn-primary pull-right" value="삭제">
				    <input type="hidden" id="comment_num" value="${comment.comment_num}">
				    <input type="hidden" id="comment_writer" value="${comment.comment_writer}">
				  </td>
	            </tr>
	            <tr id="reply${comment.comment_num}" style="background-color:#dcdcdc; display:none;">
	              <td style="width:100px;">${comment.comment_writer}</td>
  	              <td>
  	                <input id="reply_content${comment.comment_num}" style="background-color:white;" type="text" class="form-control" name="bbsTitle" maxlength="50" />
				  </td>
				  <td>
				    <input type="button" class="btn btn-primary pull-right" id="reply_register${comment.comment_num}" value="답글달기">
				    <input type="hidden" id="comment_ref${comment.comment_num}" value="${comment.comment_ref}">
				    <input type="hidden" id="comment_level${comment.comment_num}" value="${comment.comment_level}">
				    <input type="hidden" id="comment_length${comment.comment_num}" value="${comment.comment_length}">
				  </td>
	            </tr>
	          </c:forEach>
	        </thead>
	      </table>
	    </form>
	  </div>
	</div>
</body>
</html>