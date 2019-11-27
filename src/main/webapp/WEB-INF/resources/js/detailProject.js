$(document).ready(function() {
	
	$.ajax({
    	data: {pjtNo : 1},
    	type: "post",
    	url: 'selectProjectName.do',
    	success: (project) => {
    		$(".pjtName").html(`${project.projectTitle}`);
    	}
    })
	
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
        disableDrag: true,
        disableResize: true,
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

let modalWrap = document.querySelector(".modalInsertWrap");
// 등록버튼 클릭 시 모달창 생성
document.querySelector("#insertBtn").addEventListener("click", () => {
    modalWrap.classList.toggle("block");
});
// x버튼 클릭 시 모달창 제거
document.querySelector("#closeBtn").addEventListener("click", () => {
    modalWrap.classList.toggle("block");
});

// 모달창이 띄어졌을 시 스크롤 방지
$(".modalInsertWrap").on('scroll touchmove mousewheel', function(event) {
    event.preventDefault();
    event.stopPropagation();
    return false;
});

// ... 버튼 클릭 시 모달창 생성
$(".optionModalBtn").click((e) => {
	let thisNo = $(e.target).data("msg");
    $(".optionF").addClass("block");
    $(".modal").removeClass("block");
})
// 취소 버튼 클릭 시 모달창 제거
$(".cancel").click(() => {
    $(".optionF").removeClass("block");
})

// 수정아이콘 클릭 시 수정화면으로 이동
$("#updateBtn").click(() => {
	location.href='/awethumb/detailProject/updateListForm.do';
})


// 인스타 버튼 클릭시 모달창 생성
$(".detailBtn").click((e) => {
	let posNo = $(e.target).data("msg");
	let promise = $.getJSON({
		url: "selectOneBoard.do",
		data : {postNo: posNo},
		success : (board) => {
			promise.done(
				viewBoardAjax(board),
				setTimeout(() => {
					imgReSize({
						w : $("#image").width(),
						h : $("#image").height()
					})
					$('.single-item').slick();
				}, 100)
			);
			
			$.ajax({
				url: "selectCommentList.do",
				data: {postNo: board.postNo},
				success : (cList) => {
					for (let i = 0; i < cList.length; i++) {
						commentListAjax(cList[i])						
					}
				}
			})
			
			$("#cmtInsertBtn").click((e) => {
				$.ajax({
					type: "post",
					url: "insertComment.do",
					data: {
						postNo : $(e.target).data("postno"),
						cmtContent : $("#cmtCont").val()
					},
					success: () => {
						$.ajax({
							url: "selectCommentList.do",
							data: {postNo : $(e.target).data("postno")},
							success: (cList) => {
								$(".comment").empty();
								for (let i = 0; i < cList.length; i++) {
									$("#comment").append(commentListAjax(cList[i]));						
								}
								$("#cmtCont").val('');								
							}
						})
					}
				})
			})
		}
	})
	$(".modal").addClass("block");
})
function commentListAjax(cList) {
	$(".comment").append(
	`
	<div class="commentList">
        <div class="commentUserImg">
            <img src="./../images/test_user.jpg" alt="">
        </div>
        <div class="commentWrap">
          	 ${cList.cmtContent}
			<button class="cmtBtn" data-cmtNo="${cList.cmtNo}">
				<i class="fas fa-ellipsis-h"></i>
			</button>
        </div>
    </div>
	`
	)
}
function viewBoardAjax(board) {
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
	
	if ($(".modalCont > p").children("img").length != 0) {
		$("#boxSize").html(
			$(".modalCont > p").children("img")
		)
	}
	$("#modalClose").click(() => {
		$(".modal").removeClass("block");
		$("#boxSize").html(``);
		/*$("#boxSize").removeClass("imgWrap");*/
	})
}


// 이미지 띄우는 스크립트
function imgReSize({w, h}) {
	let maxSize = 550;
	let boxSize = document.getElementById("boxSize");
	let rightBox = document.getElementById("rightBox");
    if (w > maxSize && h > maxSize) {
        if (w > h) {
            boxSize.style.width = maxSize+"px";
            boxSize.style.height = "auto";
            image.style.width = "100%";
        } else {
	        boxSize.style.width = "auto";
	        boxSize.style.height = maxSize+"px";
	        image.style.height = "100%";
        }
    } else if (w > maxSize && h < maxSize) {
        boxSize.style.width = maxSize+"px";
        image.style.width =  "100%";
    } else if (w < maxSize && h > maxSize) {
        box.Size.style.height = maxSize+"px";
        image.style.height = "100%";
    }
    rightBox.style.height = boxSize.style.height;
    
    $(".comment").height(
   		$("#rightBox").height()-$(".modalCont").height()-75
    )
}

