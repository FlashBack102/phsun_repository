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
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous"> -->
<!--Fontawesome CDN-->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="${css}home.css">
<title>Insert title here</title>
</head>
<script>
	$(document).ready(function() {
		if($.cookie('remember_id')) {
			$('#login_id').val($.cookie('remember_id'));
			$('#remember_id').prop("checked", true);
		}
	});
</script>
<body>

<div class="container">
	<div class="d-flex justify-content-center h-100">
		<div class="card">
			<div class="card-header">
				<h3>로그인</h3>
			</div>
			<div class="card-body">
				<form>
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-user"></i></span>
						</div>
						<input type='text' class="form-control" name='login_id' id='login_id' placeholder="로그인 아이디"></input>
					</div>
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-key"></i></span>
						</div>
						<input type='password' class="form-control" name="login_pwd" id='login_pwd' placeholder="로그인 비밀번호"></input>
					</div>
					<div class="row align-items-center remember">
						<label>
							<input type="checkbox" id="remember_id">아이디 저장하기
						</label>
					</div>
					<div class="form-group">
						<button id="login_btn" type="button" class="btn float-right login_btn">로그인</button>
					</div>
				</form>
			</div>
			<div class="card-footer">
				<div class="d-flex justify-content-center links">
					<a href="#" id="register_page" class="btn float-right login_btn">회원가입</a>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
$(function() {
	var csrfHeader = $('meta[name="_csrf_header"]').attr('content');
	var csrfToken = $('meta[name="_csrf"]').attr('content');
	
	$('#register_page').click(function() {
		location.href="${pageContext.request.contextPath}/guest/register_form";
	}); // 회원가입 페이지로 이동
	
	$("#login_btn").click(function() {
		const param = $('form[id=login_form]').serialize();
		const login_id = $('input[id=login_id]').val();
		const login_pwd = $('input[id=login_pwd]').val();
		
		if(!login_id) {
			alert("아이디를 입력해주세요");
		} else if(!login_pwd) {
			alert("비밀번호를 입력해주세요");
		} else {
			
			if($('input[id=remember_id]').is(":checked")) {
				$.cookie('remember_id', login_id, { expires: 7, path: '/' });
			} else {
				$.removeCookie('remember_id');
			}
			
 			$.ajax({
				url : '${pageContext.request.contextPath}/guest/login',
				type : 'POST',
				data : {
					username : login_id,
					password : login_pwd
				},
				beforeSend : function(xhr) {
					xhr.setRequestHeader(csrfHeader, csrfToken);
				},
				success : function(result) {
					if(result != "") {
						location.href="${pageContext.request.contextPath}/member/board"
					} else if(result == "") {
						alert("로그인에 실패하였습니다.");
					}
				},
				error : function() {
					
				}
				
			});
		}
	}); // 로그인
	
});
</script>
</body>
</html>