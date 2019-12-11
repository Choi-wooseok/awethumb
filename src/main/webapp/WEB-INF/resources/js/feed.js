let pageIndex = 0; // 시작
const pageCount = 5; // 범위 5개
let scrollTop = 0;
let loginUserNo = $(".loginUserNo").val();
let loginUserNickName = $(".loginUserNickName").val();
let loginUserId = $(".loginUserId").val();
let sidePageIndex = 0; // 시작
const sidePageCount = 4; // 범위 5개
$(document).ready(function(){ 
	    boardList();
	    sideFollowList();
});
$(document).on('click', '.addBtn', () => {
	sideFollowList();
});
//$(document).on('click', ".aaa",() => {
//	alert("asdf");
//	sideFollowList();
//});
// infinity scroll
$(window).scroll(() => {
//	scrollTop = $(window).scrollTop();
	if ($(window).height() == $(document).height() - Math.ceil($(window).scrollTop())) {
		pageIndex += pageCount;
  		boardList();
  		sideFollowList();
  	}
 });
function boardList(){
	$.getJSON({
		url: "feedlist.do",
		dataType:"JSON",
		data:{
			pageIndex : pageIndex,
			pageCount : pageCount,
			subUserNo:loginUserNo
		},
		success: list =>{
			if(list.length === 0) {
				$(window).off('scroll');
				$("#feedWrap").append(`
					<div style="min-height: 615px;">
						<h2>구독중인 사람이 없거나 게시글이 없습니다.</h2>
					</div>
				`);
				return;
			}
			feedList(list);
		}
	})
}; // boardList
function sideFollowList(){
	$.getJSON({
		url: "feedsidelist.do",
		dataType:"JSON",
		data:{
			sidePageIndex : sidePageIndex,
			sidePageCount : sidePageCount,
			userId:loginUserId
		},
		success: list => {
//			if(Object.keys(list).length === 0) {
			if(list.length === 0) {
				alert("list 0개")
//				$(".addBtn").off('click');
//				$("#addBtn").css("display","none");
				feedSideFollowMe(list);
				return;
			}
			sidePageIndex += sidePageCount;
			feedSideFollowMe(list);
		}
	})
};
function feedSideFollowMe(list) {
	$.each(list, (i, sl) => {
		$("#feedSideUserList").append(`
			<div class="feedInfo">
				<div class="feedUserImg">
					<img src="./../images/test_user.jpg" alt="">
				</div>
				<div class="feedUserName">
					<a href="/awethumb/profile/${sl.userNickname}">${sl.userNickname}</a>
				</div>
			</div>
		`);
	})
	if (list.length !== 0){
		$("#feedSideUserList").append(`
			<div class="addBtn" id="addBtn" style="text-align:center;">
				<button type="button" style=" border:none; background-color:transparent">
					더보기
				</button>
			</div>
		`);
	}
}
let imageState = $(".imageState").val();
function feedList(list){
//	if(list) {
//	if(Object.keys(list).length === 0) {
//		alert("씨바왜나와")
//		$("#feedWrap").append(`
//				<div style="min-height: 615px;">
//					<h2>구독중인 사람이 없거나 게시글이 없습니다.</h2>
//				</div>
//		`);
//	}
	if (list.length !== 0) {
		$.each(list, (i, bl) => {
			if (imageState !== 0) { // 값이 없을때 사진이 존재 값이 있다면 사진X
				$("#feedWrap").append(`
						<div class="feedList">
							<div class="feedInfo">
								<div class="feedUserImg">
									<img src="./../images/test_user.jpg" alt="">
								</div>
								<div>
									<a href="/awethumb/profile/${bl.userNickName}"><span>${bl.userNickName}</span></a>
									<button type="button" class="boardModal" data-postNo="${bl.postNo}">
										<i class="fas fa-ellipsis-h"></i>
									</button>
								</div>
							</div>
							<!-- 사진존재 -->
							<div id="feedImgWrap">
									<img id="feedImg" src="./../images/test_img1.jpg" alt="">
								<div class="hoverWrap">
									<div>
										<button class="like">
											<i class="far fa-heart"></i><span>4</span>
										</button>
									</div>
								</div>
							</div>
							<div class="feedText">
								<div>
								게시판 내용 : ${bl.postContent}
								게시판 번호 : ${bl.postNo}
								</div>
							</div>
							<div class="feedPlay">
								<div id="commentList">
									<div id="boardCommentList${bl.postNo}"></div>
								</div>
								<div class="insertComment">
									<input id="commentWriter" class="commentWriter${bl.postNo}" type="text" />
									<button type="button" class="commentInsertBtn" data-postNumber="${bl.postNo}">등록</button>
								</div>
							</div>
						</div>
				`)
			}
			else {
				$("#feedWrap").append(`
						<div class="feedList">
							<div class="feedInfo">
								<div class="feedUserImg">
									<img src="./../images/test_user.jpg" alt="">
								</div>
								<div>
									<a href="/awethumb/profile/${bl.userNickName}"><span>${bl.userNickName}</span></a>
									<button type="button" class="boardModal" data-postNo="${bl.postNo}">
									<i class="fas fa-ellipsis-h"></i>
									</button>
								</div>
							</div>
							<!--  이미지없고 텍스트만 -->
							<div class="feedText">
								<div>
								게시판 내용 : ${bl.postContent}
								게시판 번호 : ${bl.postNo}
								</div>
							</div>
							<div class="feedPlay">
								<div id="commentList">
									<div id="boardCommentList${bl.postNo}"></div>
								</div>
								<div class="insertComment">
									<input id="commentWriter" class="commentWriter${bl.postNo}" type="text" />
									<button type="button" class="commentInsertBtn" data-postNumber="${bl.postNo}">등록</button>
								</div>
							</div>
						</div>
					`);
			}; // else
		}); // each
	} // else
}; // feedList

// 댓글
function aaa () {
	let postNo = document.querySelectorAll(".postNo");
	for (let a of postNo){
		let postnum = a.value; // 게시글 번호받기 걷기
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
										<div class="cmtInfo">
											<span style="color:green;">글작성자 : ${c.cmtUserNickname}</span>
											<span style="color:red;">작성시간 : ${c.cmtRegDt}</span>
											<button type="button" 
												id="commentModal${c.cmtNo}"
												class="commentModal"
												data-commentContent="${c.cmtContent}"
												data-commentNo="${c.cmtNo}"
												data-postNo="${c.postNo}">
												<i class="fas fa-ellipsis-h"></i>
											</button>
										</div>
										<div class="cmtContent" style="color:blue;">
											내용 : ${c.cmtContent} 
										</div>
									</div>
								</div>`
						);
					}
					else {
						$bcla.append(
								`<div class="commentList${c.cmtNo} commentList" id="commentList">
									<div class="commentUserImg">
										<img src="./../images/test_user.jpg" alt="">
									</div>
									<div id="commentWrap${c.cmtNo}" class="commentWrap">
										<div class="cmtInfo">
											<span style="color:green;">글작성자 : ${c.cmtUserNickname}</span>
											<span style="color:red;">작성시간 : ${c.cmtRegDt}</span>
										</div>
										<div class="cmtContent" style="color:blue;">
											내용 : ${c.cmtContent} 
										</div>
									</div>
								</div>`
						);
					} // else
				}// if
				
			}); // each
			$("#boardCommentList" + postnum).html($bcla);
		};
	} // for
}
aaa();
// 입력
$(document).on( "click",".commentInsertBtn", (e) => {
	let postNumber = $(e.target).data("postnumber");
	let commentWriter = $(".commentWriter" + postNumber).val();
	$.ajax({
		url: "boardCommentInsert.do",
		contentType : "application/json", 
		method:"POST",
		data: JSON.stringify({
			postNo: postNumber,
			cmtContent: commentWriter,
			userNo : loginUserNo,
			cmtUserNickname : loginUserNickName
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
// 수정취소
$(document).on("click", ".commentCancel",() => {
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
// 수정폼
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
});
// click 이벤트
$(document).on( "click",".commentModal", (e) => {
	let obj = e.target
	if (obj.nodeName == "I") {
		obj = obj.parentNode;
	}
	let cmtNo = $(obj).data("commentno");
	let postNo = $(obj).data("postno");
	let cmtContent = $(obj).data("commentcontent");
	$(".commentboardmodal").css("display","block");
	
	$(".commentDelete").data("commentno", cmtNo);
	
	$(".commentModify").data("commentno", cmtNo);
	$(".commentModify").data("postNo", postNo);
	$(".commentModify").data("commentcontent", cmtContent);
	
	$(".report").data("postNo", postNo);
	$(".report").data("commentno", cmtNo);
	
	
	$(".modalCancel").on("click", () => {
		$(".commentboardmodal").css("display","none");
	});
	
});
$(document).on("click", ".boardModal", (e) => {
	let obj = e.target;
	if (obj.nodeName == "I") {
		obj = obj.parentNode;
	}
	let postNo = $(obj).data("postno");
	$(".report").data("postNo", postNo);
	$(".board").css("display","block");
	$(".boardCancel").click(() => {
		$(".board").css("display","none");
	});
});
// 신고
$(document).on("click", ".report",(e) => {
	let postNo = $(e.target).data("postNo");
	let cmtNo = $(e.target).data("commentno");
	if (cmtNo == null) { // 게시글 신고
		let newWindow = window.open("about:blank");
		newWindow.location.href = `/awethumb/report/insertReportForm.do?postNo=${postNo}`;
	}
	else { // 댓글 신고
		let newWindow = window.open("about:blank");
		newWindow.location.href = `/awethumb/report/insertReportForm.do?postNo=${postNo}&commentNo=${cmtNo}`;
	}
});










