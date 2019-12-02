let loginUserNo = $(".loginUserNo").val();
let postNo = document.querySelectorAll(".postNo");
// 댓글등록
$(".commentInsertBtn").on("click", (e) => {
	let postNumber = $(e.target).data("postnumber");
	let commentWriter = $(".commentWriter" + postNumber).val();
	console.log("qwe : " + postNumber);
	console.log("qwe : " + commentWriter);
	$.ajax({
		url: "boardCommentInsert.do",
		contentType : "application/json", 
		method:"POST",
		data: JSON.stringify({
			postNo: postNumber,
			cmtContent: commentWriter,
			userNo : loginUserNo
		}),
		dataType: "json",
		success: list =>  boardCommentListAjax(list)
		
	});
	$(".commentWriter"+ postNumber).val("");
});

$(".commentboardmodal").on("click", ".delete", (e) => {
	let cmtNo = $(".commentboardmodal").data("cmtno");
	let postNumber = $(e.target).data("postnumber");
	$.ajax({
		url: "boardCommentDelete.do",
		data: {
			cmtNo: cmtNo,
			postNo: postNumber
			},
		dateType:"json",
		success: (list) => boardCommentListAjax(list)
	});
});
	for (let a of postNo){
		let postnum = a.value; // 게시글 번호받기 걷기
		$(function () {
			// 삭제
//			$(".commentboardmodal").on("click", ".delete", (e) => {
//				let cmtNo = $(".commentboardmodal").data("cmtno");
//				$.ajax({
//					url: "boardCommentDelete.do",
//					data: {
//						cmtNo: cmtNo,
//						postNo: postnum
//						},
//					dateType:"json",
//					success: (list) => boardCommentListAjax(list)
//				});
//			});
			// 수정취소
			$("#boardCommentList" + postnum).on("click", "button.cancel", (e) => {
				$("#updateText").css("display","none");
			});
			// 댓글 수정
			$("#boardCommentList" + postnum).on("click", "button.update", (e) => {
				let cmtNo = $(e.target).data("cmtNo");
				let cmtCon = $(e.target).data("content");
				$.ajax({
					url: "boardCommentUpdate.do",
					type: "POST",
					data: {
						postNo : postnum,
						cmtContent: $("#contentUpdate").val(), 
						cmtNo :  cmtNo
					},
					dataType: "json",
					success: list => boardCommentListAjax(list)
				});
				
			});
			$(".commentboardmodal").on("click", ".modify", (e) => {
				let cmtNo = $(e.target).data("cmtNo");
				let cmtCon = $(e.target).data("cmtComment");
				$("#commentWrap" +  cmtNo).append(
						`<div id="updateText" class="updateText">
							<input type="text" id="contentUpdate" value="${cmtCon}"/>
							<div>
								<button class="update" data-cmtNo=${cmtNo}>수정</button>
								<button class="cancel" data-cmtNo=${cmtNo}>취소</button>
							</div>
						</div>`
				);
			}); // 댓글수정폼
		}); // 수정삭제등록
		commentListAjax();
		function commentListAjax() {
			$.getJSON({
				url: "boardCommentList.do",
				data: {postNo: postnum},
				success: list => boardCommentListAjax(list)
			});
		}
		function boardCommentListAjax(list) {
			$bcla = $(`<div></div>`);
			$.each(list, (i	, c) => {
				if(c.postNo == postnum){
					if (loginUserNo == c.userNo){
					$bcla.append(
						   `<div class="commentList${c.cmtNo} commentList" id="commentList">
							    <div class="commentUserImg">
									<img src="./../images/test_user.jpg" alt="">
								</div>
								<div id="commentWrap${c.cmtNo}" class="commentWrap">
									로그인성공
									<span style="color:red;">내용 : ${c.cmtContent}</span>
									<span style="color:blue;">작성일자 : ${c.cmtRegDt}</span>
									댓글유저번호 : ${c.userNo}
									로그인 유저번호: ${loginUserNo}
									댓글번호 : ${c.cmtNo}
									<button type="button" data-btn="modal1" id="commentModal${c.cmtNo}"
									 class="commentModal"
									 data-commentNo="${c.cmtNo}"
									 data-commentConetext="${c.cmtContext}">
										<i class="fas fa-ellipsis-h"></i>
									</button>
								</div>
							</div>`
						);
					abc = c.cmtNo;
					}
					else {
						$bcla.append(
								   `<div class="commentList${c.cmtNo} commentList" id="commentList">
									    <div class="commentUserImg">
											<img src="./../images/test_user.jpg" alt="">
										</div>
										<div id="commentWrap${c.cmtNo}" class="commentWrap">
											로그인실패
											내용 : ${c.cmtContent}
											작성일자 : ${c.cmtRegDt}
											댓글유저번호 : ${c.userNo}
											로그인 유저번호: ${loginUserNo}
										</div>
									</div>`
								);
					} // else
				}// if
					
			}); // each
			$("#boardCommentList" + postnum).html($bcla);
		};
		$(".myBoard" + postnum).click(() => {
		    $("#modalBoard" + postnum).css("display","block");
		});
		$(".boardClose" + postnum).click(() => {
		    $("#modalBoard" + postnum).css("display","none");
		});
		
	} //for

	
	

// click 이벤트
$(document).on( "click",".commentModal", (e) => {
//	$(".commentModalmini").addClass("block");
	$(".commentboardmodal").attr("data-cmtNo", $(e.target).data("commentno"));
	$(".commentboardmodal").attr("data-cmtComment", $(e.target).data("commentconetext"));
	let obj = e.target
	if (obj.nodeName == "I") {
		obj = obj.parentNode;
	}
	let comnum = $(obj).data("commentno");
	$(".commentboardmodal").css("display","block");
	$(document).on("click",".commentModalClose", () => {
		$(".commentboardmodal").css("display","none");
	});
});
$(document).on("click", ".updatecancel", (e) => {
	$(".commentboardmodal").css("display","none");
})









