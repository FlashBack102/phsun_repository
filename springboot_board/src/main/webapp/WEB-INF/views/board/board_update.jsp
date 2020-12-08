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
  <input type="hidden" id="exist_file" value="${vo.board_file}">
  <input type="hidden" id="board_num" value="${vo.board_num}">
  <fieldset>
    <legend>게시글 작성</legend>
    <div class="form-group row">
      <label for="staticEmail" class="col-sm-2 col-form-label">글 제목</label>
      <div class="col-sm-10">
        <input type="text" class="form-control-plaintext" id="subject" value="${vo.board_subject}">
      </div>
    </div>
    <div class="form-group">
      <label for="exampleTextarea">글 내용</label>
      <textarea class="form-control" id="content" rows="3">${vo.board_content}</textarea>
    </div>
    <div class="form-group">
      <label for="exampleInputFile">파일 첨부</label>
      <input type="file" class="form-control-file" id="file_upload" aria-describedby="fileHelp">
    </div>
    <button type="button" class="btn btn-primary" id="board_update">수정</button>
  </fieldset>
</form>
</body>
<script>

	var csrfHeader = $('meta[name="_csrf_header"]').attr('content');
	var csrfToken = $('meta[name="_csrf"]').attr('content');
	$("#board_update").click(function() {
		
		var param = $('form[id=article]').serialize();
		var boardNum = $('input[id=board_num]').val();
		var subject = $('input[id=subject]').val();
		var content = $('textarea[id=content]').val();
		var existFile = $('input[id=exist_file]').val();
		var newFile = $('input[id=file_upload]')[0].files[0];
		
		// var upup = $('input[id=imageupload]')[0].files[0];
		/* if(!file) {
			file = null;
		} */
		console.log(existFile);
		console.log(newFile);
		
		var formData = new FormData();
		formData.append('boardNum', boardNum);
		formData.append('subject', subject);
		formData.append('content', content);
		formData.append('existFile', existFile);
		formData.append('fileUpload', newFile);

		if(!subject) {
			alert("제목을 입력해주세요");
		} else if(!content) {
			alert("내용을 입력해주세요");
		} else {
			$.ajax({
				enctype : 'multipart/form-data',
				url : "/board/board_update_pro",
				type : "POST",
				processData : false,
				contentType : false,
				data : formData,
				beforeSend : function(xhr) {
					xhr.setRequestHeader(csrfHeader, csrfToken);
				},
				success : function(data) {
					if(parseInt(data) == 1) {
						alert("게시글이 수정되었습니다.");
					} else {
						alert("게시글 수정에 실패하였습니다.");
					}
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