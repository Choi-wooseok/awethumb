let loginUserNo = $(".loginUserNo").val();
function aaa () {
	let postNo = document.querySelectorAll(".postNo");
	for (let a of postNo){
		let postnum = a.value; // 게시글 번호받기 걷기
		$(function () {
			commentListAjax();
		}); // 수정삭제등록
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
								<button type="button" 
								id="commentModal${c.cmtNo}"
								class="commentModal"
								data-commentContent="${c.cmtContent}"
								data-commentNo="${c.cmtNo}"
								data-postNo="${c.postNo}">
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
}
aaa();
//등록
//$(".insertComment").submit( (e) => {
//	let postNo = $(e.target).data("postNo");
//	let cmtCont = $(".commentWriter" + postNo).val();
//	console.log("들어옴 글번호  : "  + postNo);
//	console.log("내용 : " + cmtCont)
//	$.ajax({
//		url: "boardCommentInsert.do",
//		contentType : "application/json", 
//		method:"POST",
//		data: JSON.stringify({
//			postNo: postNo,
//			cmtContent: cmtCont,
//			userNo : loginUserNo
//		}),
//		dataType: "json",
//		success: list => aaa()
//	});
//	$(".commentWriter"+ postnum).val("");
//	console.log("댓글등록 : " + 123);
//	return false;
//});
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
		success: list =>  aaa()
		
	});
	$(".commentWriter"+ postNumber).val("");
	return false;
});
// 삭제
$(".commentDelete").on("click", (e) => {
	let cmtNo = $(e.target).data("commentno");
	console.log("delete");
	console.log(cmtNo);
	$(".updateText").css("display","none");
	$.ajax({
		url: "boardCommentDelete.do",
		data: {
			cmtNo: cmtNo,
		},
		dateType:"json",
		success: () => aaa()
	});
	$(".commentboardmodal").css("display","none");
});
//수정취소
$(document).on("click", ".commentCancel",() => {
	console.log("asd");
	$(".updateText").css("display","none");
});
// 댓글 수정
$(document).on("click", ".commentUpdate",(e) => {
	let cmtNo = $(e.target).data("cmtno");
	let postNo = $(e.target).data("postno");
	$.ajax({
		url: "boardCommentUpdate.do",
		type: "POST",
		data: {
			postNo : postNo,
			cmtContent: $("#contentUpdate").val(), 
			cmtNo :  cmtNo
		},
		dataType: "json",
		success: list => aaa()
	});
	
});
// 수정
$(".commentModify").on("click", (e) => {
	let cmtNo = $(e.target).data("commentno");
	let postNo = $(e.target).data("postNo");
	let cmtCon = $(e.target).data("commentcontent");
	$("#commentWrap" +  cmtNo).append(
			`<div id="updateText" class="updateText">
				<input type="text" id="contentUpdate" value="${cmtCon}"/>
				<div class="commentUpdateForm">
					<button class="commentUpdate" data-cmtNo=${cmtNo} data-postNo=${postNo}>수정</button>
					<button class="commentCancel">취소</button>
				</div>
			</div>`
	);
	$(".commentboardmodal").css("display","none");
}); // 댓글수정폼
// click 이벤트
$(document).on( "click",".commentModal", (e) => {
	let obj = e.target
	if (obj.nodeName == "I") {
		obj = obj.parentNode;
	}
	let cmtNo = $(obj).data("commentno");
	let postNo = $(obj).data("postno");
	let cmtContent = $(obj).data("commentcontent");
	console.log("댓 : " + cmtNo);
	$(".commentboardmodal").css("display","block");
	
	$(".commentDelete").data("commentno", cmtNo);
	console.log("in :", $(".commentDelete").data("commentno"))
	
	$(".commentModify").data("commentno", cmtNo);
	$(".commentModify").data("postNo", postNo);
	$(".commentModify").data("commentcontent", cmtContent);
	
	$(".modalCancel").on("click", () => {
		$(".commentboardmodal").css("display","none");
	});
	
});
//$(document).on( "click",".commentModal", (e) => {
//	let obj = e.target
//	if (obj.nodeName == "I") {
//		obj = obj.parentNode;
//	}
//	let abc = $(obj).data("commentno");
//	$("#modalComment" + abc).css("display","block");
//	$(document).on("click",".commentModalClose", () => {
//		$("#modalComment" + abc).css("display","none");
//	});
//});










