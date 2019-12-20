$(document).ready(function() {
	let pjtNo = $("#posUpdateBtn").data("pjtno");
	$.ajax({
		url: pageContextPath + "/api/project/" + pjtNo + "/img",
		success: (result) => {
			$(".bgWrap").html(`
				<img src="${result}" />
			`)
		}
	})
	
    $('#summernote').summernote({
        minHeight: null,
        maxHeight: null,
        focus: true,
        toolbar : [
        	['style', ['bold', 'italic', 'underline', 'clear']],
        	['fontsize', ['fontsize']],
        ],
        popover: {},
        disableDragAndDrop: true,
    });
});

$("#bgChange").change((e) => {
	if ($(e.target).get(0).files.length != 0) {
		let pjtNo = $("#posUpdateBtn").data("pjtno")
		var data = new FormData();
		data.append('projectFile', $(e.target).get(0).files[0]);
		$.ajax({
			data: data,
			url: pageContextPath + "/api/project/" + pjtNo + "/img",
			type: "POST",
			dataType: "text",
			contentType: false,
			processData: false,
			cache: false,
			success: () => {
				$.ajax({
					url: pageContextPath + "/api/project/" + pjtNo + "/img",
					success: (result) => {
						$(".bgWrap").html(`
							<img src="${result}" />
						`)
					}
				})
			}
		});
	}
})

$(".pjtName").click(() => {
	$.ajax({
    	data: {projectNo : $("#posUpdateBtn").data("pjtno")},
    	type: "post",
    	url: 'selectProjectName.do',
    	success: (project) => {
    		$(".pjtTitle").html(`
    			<form method="post" action="updateProjectName.do">
					<input id="pjtName" name="pjtName" value="${project.projectTitle}" />
					<input type="hidden" name="pjtNo" value="${project.projectNo}" />
					<button class="udtTitle">수정</button>
				</form>
			`)
    	}
    })
})

function sendFile(file, el, welEditable) {
	var data = new FormData();
	data.append('file', file);
	$.ajax({
		data: data,
		type: "POST",
		url: 'imageUpload.do',
		cache: false,
		contentType: false,
		enctype: 'multipart/form-data',
		processData: false,
		success: function(url) {
			$("#summernote").summernote('insertImage', url)
		}
    });
}


$(function () {
    $('.grid-stack').gridstack({
        animate: true,
        width: 12,
        vertical_margin: 10,
        removeTimeout: 2000,
    });
});

$(".grid-stack-item").resize((e) => {
    let width = $(e.target).width();
    let height = $(e.target).height();

    // 3 x 3 이하일 경우
    if (width <= 320 && height <= 235) {
        $(e.target).addClass('min_layout');
    } else {
        $(e.target).removeClass('min_layout');
    }
});

// 모달창이 띄어졌을 시 스크롤 방지
$(".modalInsertWrap").on('scroll touchmove mousewheel', function(event) {
    event.preventDefault();
    event.stopPropagation();
    return false;
});

// 신고하기 등등 버튼 모달창 띄우기 / 제거하기
$(".optionModalBtn").click((e) => {
	let thisNo = $(e.target).data("msg");
	$(".optionModalWrap").addClass("block");
	$(".deleteBoard").attr("data-postNo", thisNo);
	$(".updateBoard").attr("data-postNo", thisNo);	
})
// 삭제 클릭시 이벤트
$(".deleteBoard").click((e) => {
	$(".optionU").removeClass("block");
	let pjtNo = $("#posUpdateBtn").data("pjtno");
	$.post({
		url: "delete.do",
		data: {
			pjtNo: pjtNo, 
			postNo: $(e.target).data("postno")
		},
		success: () => {
			window.location.href=`/awethumb/detailProject/updateListForm.do?projectNo=${pjtNo}`
		}
	})
})

// 수정클릭 시 글쓰기 창 띄우기 / 제거하기
$(".updateBoard").click((e) => {
	$(".modalInsertWrap").addClass("block");
	let postNo = $(e.target).data("postno");
	let pjtNo = $("#posUpdateBtn").data("pjtno");
	$.ajax({
		url : "updateSelectOneBoard.do",
		data : {postNo : postNo},
		success : (board) => {
			$(".insertFormWrap").append(`
				<input type="hidden" name="projectNo" value="${board.projectNo}" />
				<input type="hidden" name="postNo" value="${board.postNo}" />
			`)
			$(".note-editable").html(`
				${board.postContent}
			`)
		}
	})
	
	$.ajax({
		url: "imageDownload.do",
		data: {postNo: postNo},
		success: (sArr) => {
			if (sArr != null) {
				for (let i = 0; i < sArr.length; i++) {
					$(".imageViewWrap").append(`
						<img class="detailImage" src=${sArr[i]} />
					`)
					if (i == sArr.length-1) {
						$(".imageViewWrap").slick();
						$("#insertImg").addClass("movePos");
					}
				}
			}
		}
	})
})

$("#insertImg").change((e) => {
	$(".imageViewWrap").html("").removeClass("slick-slider").removeClass("slick-initialized");
	let images = $(e.target).get(0).files;
	
	// 이미지가 1개이상 선택 되었을 경우
	if (images.length != 0) {
		for (let i = 0; i < images.length; i++) {
			var data = new FormData();
			data.append('file', images[i]);
			$.ajax({
				data: data,
				url: "imageUpload.do",
				type: "POST",
				dataType: "json",
				contentType: false,
				processData: false,
				async: false,
				cache: false,
				success: (bfile) => {
					// 가져온 url로 이미지 그리기
					$(".imageViewWrap").append(`
						<img class="imgPos" src="${bfile.url}" />
					`)
					// 이미지가 모두 append된 후에 작업
					if (i == images.length-1) {
						$(".imageViewWrap").slick();
						$("#insertImg").addClass("movePos");
					}
				}
			})
		}
	// 이미지가 0개 선택인 경우
	} else if (images.length == 0) {
		$("#insertImg").removeClass("movePos");
	}
})


$(".updatecancel").click(() => {
	$(".optionU").removeClass("block");
})

$("#closeBtn").click(() => {
    $(".modalInsertWrap").removeClass("block");
})

$("#posUpdateBtn").click((e) => {
	let projectNo = $(e.target).data("pjtno");
	if($(".grid-stack-item").length == 0) {
		window.location.href=`/awethumb/detailProject/${projectNo}`;
	}
	$(".grid-stack-item").each((i, e) => {
		let posNo = $(e).data("postno");
		let posX = $(e).data("gs-x");
		let posY = $(e).data("gs-y");
		let posW = $(e).data("gs-width");
		let posH = $(e).data("gs-height");
		$.post({
			url: "updateList.do",
			data: {
				postNo: posNo,
				x_coord: posX,
				y_coord: posY,
				width : posW,
				hight : posH
			},
			success: () => {
				window.location.href=`/awethumb/detailProject/${projectNo}`
			}
		});
	})
})


