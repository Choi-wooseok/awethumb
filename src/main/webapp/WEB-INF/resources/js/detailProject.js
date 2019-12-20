$(document).ready(function() {
	let pjtNo = $("#updateBtn").data("pjtno");
	
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

// 이미지 선택 버튼 클릭 시 이미지 초기화
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

// 이미지 등록 시 이미지 이미지 선택화면에서 이미지 띄우는 화면으로 전환
function imageSlide() {
	let imgWrap = ".insertCont > div.note-editor:nth-child(2) > ";
	$(imgWrap + ".note-toolbar").addClass("hide");
	$(imgWrap + ".note-editing-area").addClass("block")
									 .addClass("imgBoxSize");
}

$(function () {
    $('.grid-stack').gridstack({
        animate: true,
        width: 12,
        disableDrag: true,
        disableResize: true,
        vertical_margin: 10,
        removeTimeout: 2000,
    });
});

// 그리드 넓이가 몇 이하일 경우 디자인 변경
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

// 등록 버튼 클릭 시 모달창 생성
$("#insertBtn").click(() => {
	$(".modalInsertWrap").addClass("block");
	$(".note-editable").html("");
	let pjtNo = $("#insertBtn").data("pjtno");
	$(".inpjtNo").html(`
		<input type="hidden" name="projectNo" value="${pjtNo}" />
	`)
})

// x버튼 클릭 시 모달창 제거
$(".closeBtn").click(() => {
	$(".modalInsertWrap").removeClass("block");
	$(".imageViewWrap").html('');
	$("#insertImg").removeClass("hide").removeClass("movePos")
})

// 모달창이 띄어졌을 시 스크롤 방지
$(".modalInsertWrap").on('scroll touchmove mousewheel', function(event) {
    event.preventDefault();
    event.stopPropagation();
    return false;
});

// ... 버튼 클릭 시 모달창 생성
$(".optionModalBtn").click((e) => {
	let postNo = $(e.target).data("msg");
    $(".optionF").addClass("block").attr("data-postNo", postNo);
    $(".modal").removeClass("block");
})
// 취소 버튼 클릭 시 모달창 제거
$(".cancel").click(() => {
    $(".optionF").removeClass("block");
})

// 수정아이콘 클릭 시 수정화면으로 이동
$("#updateBtn").click(() => {
	let projectNo = $("#updateBtn").data("pjtno");
	location.href=`/awethumb/detailProject/updateListForm.do?projectNo=${projectNo}`;
})

// 인스타 버튼 클릭시 DetailBoard 모달창 생성
$(".detailBtn").click((e) => {
	let postNo = $(e.target).data("msg");
	let promise = $.getJSON({
		url: "selectOneBoard.do",
		data : {postNo: postNo},
		success : (board) => {
			viewBoardAjax(board, postNo)
			
			// 오른쪽 Comment 높이 지정 (Content높이에 따라 변경됨)
			$(".comment").height(
		   		$(".modalContWrap").height()-($(".modalCont").height()+19)
		    )
		    
		    // Comment 불러오는 ajax
			$.ajax({
				url: "selectCommentList.do",
				data: {postNo: board.postNo},
				success : (cList) => {
					for (let i = 0; i < cList.length; i++) {
						commentListAjax(cList[i])
					}
				}
			});
			
			// image 불러오는 ajax
			$.ajax({
				url: "imageDownload.do",
				data: {postNo: board.postNo},
				success: (sArr) => {
					if (sArr != null) {
						for (let i = 0; i < sArr.length; i++) {
							$("#boxSize").append(`
								<img class="detailImage" src=${sArr[i]} />
							`)
							// 이미지가 모두 append된 후에 작업
							if (i == sArr.length-1) {
								$("#boxSize").slick();
							}
						}
					}
				}
			})
		}
	})
	$(".modal").addClass("block");
})

// 댓글 등록버튼 클릭 시 Ajax로 댓글 등
$(document).on("click", "#cmtInsertBtn", (e) => {
	let postNo = $(e.target).data("postno");
	if ($("#cmtCont").val().length != 0) {
		$.ajax({
			type: "post",
			url: "insertComment.do",
			data: {
				userNo : loginNo,
				postNo : postNo,
				cmtContent : $("#cmtCont").val()
			},
			success: () => {commentListViewAjax(postNo)}
		})
	}
});

// 댓글 삭제 버튼 클릭 시 
$(document).on("click", ".deleteCommentBoard", (e) => {
	let cmtNo = $(".modalMini").data("cmtno");
	$.ajax({
		url: "deleteComment.do",
		data: {cmtNo : cmtNo},
		success: () => {
			let postNo = $(".modalMini").data("postno");
			commentListViewAjax(postNo);
			$(".modalMini").removeData("cmtno")
		}
	})
});

// 댓글 수정 버튼 클릭 시
$(document).on("click", ".updateCommentBoard", (e) => {
	$(".modalMini").removeClass("block");
	let cmtNo = $(".modalMini").data("cmtno");
	$(".modal").addClass("block");
	let cmtSel = $("#cmt"+cmtNo);
	cmtSel.addClass("block");
	cmtSel.html(`
		<input class="upCmtCont" name="cmtCont" type="text">
		<button class="upCmtBtn" data-cmtNo="${cmtNo}">수정</button>
		<button class="cancelCmtBtn">취소</button>
	`);
});

// 취소 클릭 시 수정입력창 제거
$(document).on("click", ".cancelCmtBtn", (e) => {
	$(e.target).parent().removeClass("block");
});

// 수정 클릭 시 댓글 수정
$(document).on("click", ".upCmtBtn", (e) => {
	let cmtNo = $(e.target).data("cmtno");
	let postNo = $(".modalMini").data("postno")
	if ($(".upCmtCont").val().length != 0) {
		$.ajax({
			type: "post",
			url: "updateComment.do",
			data: {
				cmtNo : cmtNo,
				cmtContent : $(e.target).siblings(".upCmtCont").val()
			},
			success: () => {
				commentListViewAjax(postNo)
			}
		})
	};
})

// 댓글 등록 후 다시 뿌리는 Ajax
function commentListViewAjax(postNo) {
	$.ajax({
		url: "selectCommentList.do",
		data: {postNo : postNo},
		success: (cList) => {
			$(".modalMini").removeClass("block");
			$(".modal").addClass("block");
			$(".comment").empty();
			for (let i = 0; i < cList.length; i++) {
				$("#comment").append(commentListAjax(cList[i]));						
			}
			$("#cmtCont").val('');
		}
	})
}

// 댓글 ... 클릭 시 선택창 생성
$(document).on("click", ".cmtBtn", (e) => {
	$(".modal").removeClass("block");
	$(".modalMini").addClass("block")
				   .attr("data-cmtNo", $(e.target).data("cmtno"))
				   .attr("data-postNo", $("#cmtInsertBtn").data("postno"));
	
	// 유저넘버 필요
	if (loginNo == $(e.target).data("uno")) {
		$(".modalMini > div").html(`
			<div class="deleteCommentBoard">글 삭제</div>
            <div class="updateCommentBoard">글 수정</div>
            <div class="updatecancel">취소</div>
		`)
	} else {
		$(".modalMini > div").html(`
			<div class="report">부적절한 컨텐츠 신고</div>
            <div class="updatecancel">취소</div>
		`)
	}
})
// 취소버튼 클릭 시 선택창 제거
$(document).on("click", ".updatecancel", (e) => {
	$(".modalMini").removeClass("block");
	$(".modal").addClass("block");
})

// DOM
function commentListAjax(cList) {
	let like = ``;
	let code = 2;
	like = likeAdmin(`${cList.cmtNo}`, loginNo, code);
	$(".comment").append(
		`
		<div class="commentList">
			<div class="commentUserImg">
				<img src="./../images/test_user.jpg" alt="">
			</div>
			<div class="commentWrap">
				<div class="cmtInfo">
					<span>${cList.cmtUserNickname}</span>
					<span>${cList.cmtRegDt}</span>
					${like}
					<button class="cmtBtn">
						<i class="fas fa-ellipsis-h" data-cmtNo="${cList.cmtNo}" data-uNo="${cList.userNo}"></i>
					</button>
				</div>
				<div class="cmtContent">${cList.cmtContent}</div>
				<div class="cmtWrap" id="cmt${cList.cmtNo}"></div>
			</div>
		</div>
		`
	)
}

// 좋아요 버튼 추가
function viewBoardAjax(board, postNo) {
	let likecount = 0;
	let like = ``;
	let code = 1;
	like = likeAdmin(postNo, loginNo, code);
	$(".modal_content").html(
	`
	<div class="modalTitle">
		<button id="modalClose">
			<i class="fas fa-times"></i>
		</button>
	    <div class="ModaluserInfo">
	        <div class="commentUser">
	            <img src="./../images/test_user.jpg" alt="">
	        </div>
	        <div class="userName">
	            <a href="#">${board.writer}</a>
	        </div>
	    </div>
	</div>
	<div class="modalContWrap">
		<div id="boxSize" class="single-item"></div>
	    <div id="rightBox">
			<div class="modalCont">
				${board.postContent}
				${like}
			</div>
	        <div class="comment"></div>
	        <div class="insertComment">
				<input id="cmtCont" name="cmtCont" type="text" />
				<button id="cmtInsertBtn" type="button" data-postNo="${board.postNo}">등록</button>
	        </div>
	    </div>
	</div>
	`
	);
	likecount = likeCount(postNo);
	if (likecount > 0) {
		$("#countLike" + postNo).html(`
			<span id="countSpan${postNo}">${likecount}회 좋아요</span>
		`)
	}
	$("#modalClose").click(() => {
		$(".modal").removeClass("block");
		$("#boxSize").html(``);
	})
}

$(document).on("click", ".likeBtn", (e) => {
	likeClick(loginNo, e.target);
})

// 신고하기
$(document).on("click", ".report", (e) => {
	let postNo = $(".optionF").data("postno");
	let cmtNo = $(e.target).parent().parent().data("cmtno");

	if (cmtNo == null) { // 게시글 신고
		let newWindow = window.open("about:blank");
		newWindow.location.href = `/awethumb/report/insertReportForm.do?postNo=${postNo}`;
	} else { // 댓글 신고
		let newWindow = window.open("about:blank");
		newWindow.location.href = `/awethumb/report/insertReportForm.do?postNo=${postNo}&commentNo=${cmtNo}`;
	}
	
})

// 이미지 띄우는 스크립트
//function imgReSize({w, h}) {
//	let maxSize = 550;
//	let boxSize = document.getElementById("boxSize");
//	let rightBox = document.getElementById("rightBox");
//    if (w > maxSize && h > maxSize) {
//        if (w > h) {
//            boxSize.style.width = maxSize+"px";
//            boxSize.style.height = "auto";
//            image.style.width = "100%";
//        } else {
//	        boxSize.style.width = "auto";
//	        boxSize.style.height = maxSize+"px";
//	        image.style.height = "100%";
//        }
//    } else if (w > maxSize && h < maxSize) {
//        boxSize.style.width = maxSize+"px";
//        image.style.width =  "100%";
//    } else if (w < maxSize && h > maxSize) {
//        box.Size.style.height = maxSize+"px";
//        image.style.height = "100%";
//    }
//}

