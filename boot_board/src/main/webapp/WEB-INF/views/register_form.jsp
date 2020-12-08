<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/resources/static/setting/path.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<link rel="stylesheet" href="${css}bootstrap.css">
<!--Bootsrap 4 CDN-->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<!--Fontawesome CDN-->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="${css}home.css">
<script src="${js}guest/register.js"></script>
<title>Insert title here</title>
</head>
<body>
<div class="container">
	<div class="d-flex justify-content-center h-100">
		<div class="card">
			<div class="card-header">
				<h3>회원가입</h3>
			</div>
			<div class="card-body">
				<form method='POST' id="register_form">
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-user"></i></span>
						</div>
						<input type="text" class="form-control" name="id" id="id" placeholder="회원가입 아이디">
					</div>
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-key"></i></span>
						</div>
						<input type="password" class="form-control" name="pwd" id="pwd" placeholder="회원가입 비밀번호">
					</div>
					<div style="color:white; margin:0 auto;">
						<input type="radio" name="radio_authority" value="ADMIN">&nbsp;&nbsp;&nbsp;관리자
						<input type="radio" name="radio_authority" value="MEMBER">&nbsp;&nbsp;&nbsp;회원
					</div>
					<div class="form-group">
						<button id="register" type="button" class="btn float-right login_btn">회원가입</button>
					</div>
				</form>
			</div>
			<div class="card-footer">
				<div class="d-flex justify-content-center links">
					<a href="#" id="login_page" class="btn float-right login_btn">로그인</a>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
<script>
$(function() {
	var csrfHeader = $('meta[name="_csrf_header"]').attr('content');
	var csrfToken = $('meta[name="_csrf"]').attr('content');
	
	$('#login_page').click(function() {
		location.href="${pageContext.request.contextPath}/guest/login_form";
	}); // 로그인 페이지로 이동

	$("#register").click(function() {
		
		const param = $('form[id=register_form]').serialize();
		const id = $('input[id=id]').val();
		const pwd = $('input[id=pwd]').val();
		const authority = $('input[name=radio_authority]:checked').val();
		
		if(!authority) {
			alert("권한 여부를 선택해주세요");
		} else if(!id) {
			alert("아이디를 입력해주세요");
		} else if(!pwd) {
			alert("비밀번호를 입력해주세요");
		} else {
			$.ajax({
				url : "${pageContext.request.contextPath}/guest/register",
				type : "POST",
				data : param,
				beforeSend : function(xhr) {
					xhr.setRequestHeader(csrfHeader, csrfToken);
				},
				success : function (result) {
					if(parseInt(result) == 1) {
						alert("회원가입 되었습니다");
					}
				},
				error : function() {
					alert("에러임");
				}
				
			});
		}
	}); // 회원가입
});

</script>
</html>