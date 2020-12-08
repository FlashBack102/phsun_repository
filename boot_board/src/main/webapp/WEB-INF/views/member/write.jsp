<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/bootstrap.css">
<title>Insert title here</title>
</head>
<body>
<form id="article" method="post" enctype="multipart/form-data">
  <fieldset>
    <legend>게시글 작성</legend>
    <div class="form-group row">
      <label for="staticEmail" class="col-sm-2 col-form-label">글 제목</label>
      <div class="col-sm-10">
        <input type="text" class="form-control-plaintext" id="subject">
      </div>
    </div>
    <div class="form-group">
      <label for="exampleTextarea">글 내용</label>
      <textarea class="form-control" id="content" rows="3"></textarea>
    </div>
    <div class="form-group">
      <label for="exampleInputFile">파일 첨부</label>
      <input type="file" class="form-control-file" id="imageupload" aria-describedby="fileHelp">
    </div>
    <button type="button" class="btn btn-primary" id="board_register">등록</button>
  </fieldset>
</form>
</body>
<script>

	var csrfHeader = $('meta[name="_csrf_header"]').attr('content');
	var csrfToken = $('meta[name="_csrf"]').attr('content');
	$("#board_register").click(function() {
		
		var param = $('form[id=article]').serialize();
		var subject = $('input[id=subject]').val();
		var content = $('textarea[id=content]').val();
		// var upup = $('input[id=imageupload]')[0].files[0];
		
		var formData = new FormData();
		formData.append('subject', subject);
		formData.append('content', content);
		formData.append('imageupload', $('input[id=imageupload]')[0].files[0]);

		if(!subject) {
			alert("제목을 입력해주세요");
		} else if(!content) {
			alert("내용을 입력해주세요");
		} else {
			$.ajax({
				enctype : 'multipart/form-data',
				url : "/member/boardRegister",
				type : "POST",
				processData : false,
				contentType : false,
				data : formData,
				beforeSend : function(xhr) {
					xhr.setRequestHeader(csrfHeader, csrfToken);
				},
				success : function() {
					alert("게시글이 등록되었습니다");
					location.href="/member/board";
				},
				error : function(request, status, error) {
					alert("code:" + request.status + "\n" + "message:"
							+ request.responseText + "\n" + "error:" + error);
				}
			});
		}

	})
</script>
</html>