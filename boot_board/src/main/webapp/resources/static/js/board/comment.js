$(function() {
	
	const csrfHeader = $('meta[name="_csrf_header"]').attr('content');
	const csrfToken = $('meta[name="_csrf"]').attr('content');
	const boardNum = $('input[id=number]').val();
	const id = sessionStorage.getItem("id");
	
	$('#comment_register').click(function() {
		var comment = $('#comment_content').val();
		if(!comment) {
			alert("댓글을 입력해주세요");
		} else {
			$.ajax({
				url : '/board/comment_register',
				type : 'post',
				data : {
					comment : comment,
					boardNum : boardNum
				},
				beforeSend : function(xhr) {
					xhr.setRequestHeader(csrfHeader, csrfToken);
				},
				success : function(result) {
					if(parseInt(result) == 1) {
						location.reload();
						e.preventDefault();
					} else {
						
					}
				},
				error : function() {
					
				}
			})
		}
		
	});
	// $('[id^=board]')
	$('[id^=comment_delete]').each(function(i) {
		
		$(this).click(function(e) {
			e.preventDefault();
			var writer = $(this).next().next('input[id=comment_writer]').val();
			
			if(id != writer) {
				alert("답글 작성자가 아닙니다.");
			} else {
				var check = confirm("댓글을 삭제하시겠습니까?");
				
				if(check) {
					$.ajax({
						url : '/board/comment_delete',
						type : 'post',
						data : {
							commentNum : $(this).next('input[id=comment_num]').val()
						},
						beforeSend : function(xhr) {
							xhr.setRequestHeader(csrfHeader, csrfToken);
						},
						success : function(result) {
							if(parseInt(result) == 1) {
								alert("댓글이 삭제되었습니다");
								location.reload();
								e.preventDefault();								
							} else {
								alert("해당하는 댓글이 존재하지 않습니다.");
							}
						},
						error : function() {
							
						}
					})
				}
			}
			
		})
		
	});
	
	$('[id^=comment_reply]').each(function(i) {
		$(this).click(function(e) {
			e.preventDefault();
			var commentDisplay = $(this).attr('id').replace("comment_reply", "");
			
			$('#reply'+commentDisplay).show();
		})
	});
	
	$('[id^=reply_register]').each(function(i) {
		$(this).click(function(e) {
			e.preventDefault();
			
			var boardNum = $('#number').val();
			var commentNum = $(this).attr('id').replace("reply_register", "");
			
			content = $('#reply_content'+commentNum).val();
			ref = $('#comment_ref'+commentNum).val();
			level = $('#comment_level'+commentNum).val();
			length = $('#comment_length'+commentNum).val();

			$.ajax({
				url : '/board/reply_register',
				type : 'post',
				data : {
					boardNum : boardNum,
					content : content,
					ref : ref,
					level : level,
					length : length
				},
				beforeSend : function(xhr) {
					xhr.setRequestHeader(csrfHeader, csrfToken);
				},
				success : function(result) {
					if(parseInt(result) == 1) {
						alert("답글이 등록되었습니다.");
						location.reload();
					} else {
						alert("답글 등록에 실패하였습니다.");
					}
				},
				error : function() {
					
				}
			})
		})
	});
	
});