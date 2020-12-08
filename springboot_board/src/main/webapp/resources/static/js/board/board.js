$(function() {

	const csrfHeader = $('meta[name="_csrf_header"]').attr('content');
	const csrfToken = $('meta[name="_csrf"]').attr('content');
	const startPage = parseInt($('input[id="startPage"]').attr('value'));
	const endPage = parseInt($('input[id="endPage"]').attr('value'));
	const pageBlock = parseInt($('input[id="pageBlock"]').attr('value'));
	const id = $('input[id="loginId"]').attr('value');
	const authority = $('input[id=loginAuthority]').attr('value');
	
	sessionStorage.setItem("id", id);
	sessionStorage.setItem("authority", authority);

	$("#write_article").click(function() {
		location.href = "/member/write";
	});

	$('#pageLoad a').click(function() {
		var clickId = $(this).attr('id');

		$.ajax({
			url : '/member/board',
			type : 'post',
			data : {
				nextPage : clickId
			},
			beforeSend : function(xhr) {
				xhr.setRequestHeader(csrfHeader, csrfToken);
			},
			success : function(result) {
				console.log(result);
				$('body').html(result);
			},
			error : function() {

			}
		});
	});

	$('#next').click(function() {
		console.log(startPage + "처음");
		console.log(endPage + "마지막");
		console.log(endPage + 1);
		$.ajax({
			url : '/member/board',
			type : 'post',
			data : {
				nextPage : endPage + 1
			},
			beforeSend : function(xhr) {
				xhr.setRequestHeader(csrfHeader, csrfToken);
			},
			success : function(result) {
				$('body').html(result);
			},
			error : function() {
				
			}
			
		});
	});

	$('#prev').click(function() {
		console.log('응앙');
		console.log(endPage);
		console.log(pageBlock);
		$.ajax({
			url : '/member/board',
			type : 'post',
			data : {
				nextPage : startPage - pageBlock
			},
			beforeSend : function(xhr) {
				xhr.setRequestHeader(csrfHeader, csrfToken);
			},
			success : function(result) {
				$('body').html(result);
			},
			error : function() {
				
			}
		});
	});
	
//	$('[id^=board]').click(function() {
//		console.log($('[id^=board]').attr('id'));
//		console.log($('[id^=board] input').val());
//	});
	
	$('[id^=content]').each(function(i){
		$(this).click(function(e){
			e.preventDefault();
			
			var boardNum = $(this).children('input').val();
			location.href="/member/boardRead?boardNum="+boardNum;
		});
	});
	
	$('#search_button').click(function() {
		
		const type = $("#search_type option:selected").val();
		const search = $('input[id=search]').val();

		if(!type) {
			alert("검색 조건을 설정해주세요.");
		} else if(!search) {
			alert("키워드를 입력해주세요.");
		} else {
			
			$.ajax({
				url : '/board/search',
				type : 'post',
				data : {
					search : search,
					type : type
				},
				beforeSend : function(xhr) {
					xhr.setRequestHeader(csrfHeader, csrfToken);
				},
				success : function(result) {
					$('#board_div').html(result);
				},
				error : function() {
					
				}
			})
		}
	});
	
	$('#next_search_list').click(function() {
		console.log(startPage + "처음");
		console.log(endPage + "마지막");
		console.log(endPage + 1);
		console.log(type);
		console.log(search);
		var type = $('input[id=type]').val();
		var search = $('input[id=search]').val();

		$.ajax({
			url : '/board/search',
			type : 'post',
			data : {
				nextPage : endPage + 1,
				type : type,
				search : search
			},
			beforeSend : function(xhr) {
				xhr.setRequestHeader(csrfHeader, csrfToken);
			},
			success : function(result) {
				$('board_div').html(result);
			},
			error : function() {
				
			}
			
		});
	});

	$('#prev_search_list').click(function() {
		console.log('응앙');
		console.log(endPage);
		console.log(pageBlock);
		var type = $('input[id=type]').val();
		var search = $('input[id=search]').val();
		console.log(type);
		console.log(search);
		
		$.ajax({
			url : '/board/search',
			type : 'post',
			data : {
				nextPage : startPage - pageBlock,
				type : type,
				search : search
			},
			beforeSend : function(xhr) {
				xhr.setRequestHeader(csrfHeader, csrfToken);
			},
			success : function(result) {
				$('board_div').html(result);
			},
			error : function() {
				
			}
		});
	});
	
	$('#page_load_search a').click(function() {
		var clickId = $(this).attr('id');
		var type = $('input[id=type]').val();
		var search = $('input[id=search]').val();
		console.log(clickId);
		$.ajax({
			url : '/board/search',
			type : 'post',
			data : {
				nextPage : clickId,
				type : type,
				search : search
			},
			beforeSend : function(xhr) {
				xhr.setRequestHeader(csrfHeader, csrfToken);
			},
			success : function(result) {
				$('#board_div').html(result);
			},
			error : function() {

			}
		});
	});
	
	$('#delete_article').click(function() {
		var checkList = new Array();

		$('input:checkbox[id^=checkbox]').each(function() {
			if(this.checked) {
				checkList.push(this.value);
			}
		});
		console.log(checkList);
		if(checkList == "") {
			alert("삭제할 게시글을 선택해주세요.");
		} else {
			$.ajaxSettings.traditional = true; // Ajax 배열 전송을 위해 써줘야함
			// 이것을 동해 data=1&data2=2 이렇게 넘어감
			$.ajax({
				url : '/admin/boardDelete',
				type : 'post',
				data : {
					checkList : checkList
				},
				beforeSend : function(xhr) {
					xhr.setRequestHeader(csrfHeader, csrfToken);
				},
				success : function(result) {
					if(parseInt(result) != 0) {
						alert("게시글을 삭제하였습니다.");
						location.reload();
					}
				},
				error : function() {
					
				}
			});
		}
		
	});
	
	$('#logout').click(function() {
		var check = confirm("로그아웃 하시겠습니까?");
		if(check) {
			alert("로그아웃 되었습니다.");
			location.href = "/member/logout";
		}
		
	});
		
});
