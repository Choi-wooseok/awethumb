$(document).ready(function() {
    $('#summernote').summernote({
        height: 300,
        minHeight: null,
        maxHeight: null,
        focus: true,
        // 서머노트 이미지 업로드시 필요
		callbacks: {
			onImageUpload: function(files, editor, welEditable) {
				for (var i = files.length - 1; i >= 0; i--) {
					sendFile(files[i], editor, welEditable);
				}
			}
		}
    });
});

$("#bgChange").change((e) => {
	if ($(e.target).get(0).files.length != 0) {
		
		let pjtNo = $("#posUpdateBtn").data("pjtno")
		
		alert("11")
		console.log($(e.target).get(0).files[0])
		
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
    	data: {pjtNo : $("#posUpdateBtn").data("pjtno")},
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
			console.log(url)
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
	let thisNo = $(e.target).data("postno");
	let pjtNo = $("#posUpdateBtn").data("pjtno");
	$(".updateForm").append(`
		<input type="hidden" name="postNo" value="${thisNo}" />
		<input type="hidden" name="projectNo" value="${pjtNo}" />
	`);
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


