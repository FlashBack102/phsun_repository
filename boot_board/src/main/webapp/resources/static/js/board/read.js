$(function() {
	
	const csrfHeader = $('meta[name="_csrf_header"]').attr('content');
	const csrfToken = $('meta[name="_csrf"]').attr('content');
	const id = sessionStorage.getItem("id");

	$('#update').click(function() {
		if(id == $('input[id="board_writer"]').val()) {
			boardNum = $('input[id="number"').val();
			location.href="/board/board_update?boardNum="+boardNum;
		}
	});
	
	$('#delete').click(function() {
		
		if(id == $('input[id="board_writer"]').val()) {
			var check = confirm("해당 게시글을 삭제하시겠습니까?");
			if(check) {

				$.ajax({
					url : '/board/delete',
					type : 'post',
					data : {
						boardNum : $('input[id="number"]').val()
					},
					beforeSend : function(xhr) {
						xhr.setRequestHeader(csrfHeader, csrfToken);
					},
					success : function(data) {
						if(data == 1) {
							alert("게시글이 삭제되었습니다.");
							location.href="/member/board";
						} else {
							alert("삭제에 실패하였습니다.");
						}
					},
					error : function() {
						alert("게시글 삭제에 실패하였습니다.");
					}
				});
			} else {
				
			}
		} else {
			alert("작성자가 아닙니다");
		}
	})
	
	$('#attached_file').click(function() {
		
		var attachedFile = $('td[id=attached_file]').text();
		const encFileName = encodeURI(attachedFile);
		
		if(!encFileName) {
			
		} else {
		    window.location ='/board/downloads?attachedFile='+encFileName;
			
			// window.location.assign('/board/downloads');
			$.ajax({
				url : '/board/downloads',
				type : 'post',
				data : {
					attachedFile : attachedFile
				},
				beforeSend : function(xhr) {
					xhr.setRequestHeader(csrfHeader, csrfToken);
				},
				success : function() {
					
				},
				error : function() {
					
				}
			})
		}
		
	})
});