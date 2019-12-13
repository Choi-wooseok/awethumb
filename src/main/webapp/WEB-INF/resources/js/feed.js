let pageIndex = 0; // 시작
const pageCount = 5; // 개수
let scrollTop = 0;
let loginUserNo = $(".loginUserNo").val();
let loginUserNickName = $(".loginUserNickName").val();
let loginUserId = $(".loginUserId").val();
let sidePageIndex = 0; // 시작
const sidePageCount = 3; // 범위

let followCount = $(".userFollowMeCount").val(); // 맞팔안한 나를 전체팔로우하는사람 count
let followAddCount = 0; // 더보기 창 없애기위한 변수 
let boardCount = $(".boardCount").val();
let boardAddCount = 0;
$(document).ready(function(){ 
	    boardList();
	    sideFollowList();
});
$(document).on('click', '.addBtn', () => {
	sidePageIndex += sidePageCount;
	$(".addBtn").remove();
	sideFollowList();
});
// infinity scroll
//$("#feedSideUserList").scroll(() => {
//	console.log("1 : " + $("#feedSideUserList").height()); 280 235 
//	console.log("2 : " + $("#side").height());
//	console.log("22 : " + $(document).height());
//	console.log("3 : " + Math.ceil($("#feedSideUserList").scrollTop()));
//	console.log("4 : " + $("#feedSideUserList").scrollTop());
//	console.log("5 : " + $("#followa").height());
//	scrollz = Math.ceil($("#feedSideUserList").scrollTop());
////	console.log("7 : " + $("#feedSideUserList").scrollTop($("#feedSideUserList").height()));
////	if ($("#feedSideUserList").height() === $("#side").height() - Math.ceil($("#followa").height())) {
//	if(scrollz == 20) {
//		sidePageIndex += sidePageCount;
//		console.log("---------------------------------")
//		sideFollowList();
//	}
//});
$(window).scroll(() => {
//	console.log("1 : " + $(window).height()); // 757
//	console.log("2 : " + $(document).height()); // 7027 13000
//	console.log("3 : " + Math.ceil($(window).scrollTop())); //6100
//	console.log("4 : " + $(window).scrollTop()); //6100
	if ($(window).height() == $(document).height() - Math.ceil($(window).scrollTop())) {
		pageIndex += pageCount;
  		boardList();
  	}
 });
function boardList(){
	scrollz = 0;
	$.getJSON({
		url: "feedlist.do",
		dataType:"JSON",
		data:{
			pageIndex : pageIndex,
			pageCount : pageCount,
			subUserNo: loginUserNo
		},
		success: list =>{
			if(list.length === 0) {
				$(window).off('scroll');
				$("#feedWrap").append(`
						<div class="noBoard" style="min-height: 615px;">
							<h2>구독중인 사람이 없거나 게시글이 없습니다.</h2>
						</div>
				`);
//				feedList(list);
			}
			feedList(list);
		}
	})
}; // boardList
function sideFollowList(){
//	alert("사이드바")
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
//			if(list.length === 0) {
////				$("#feedSideUserList").off('scroll');
//				$("#addBtn").css("display","none");
////				alert("list 0개")
//				$(".addBtn").remove();
////				$(".addBtn").off('click');
//				feedSideFollowMe(list);
////				sideFollowList();
//				return;
//			}
			$(".addBtn").css("display","none");
			feedSideFollowMe(list);
		}
	})
};
function feedSideFollowMe(list) {
	let count = 0 ;
	$.each(list, (i, sl) => {
		followAddCount += 1;
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
		count = followAddCount;
	})
	$("#feedSideUserList").append(`
			<div class="addBtn" id="addBtn" style="text-align:center;">
				<button type="button" style=" border:none; background-color:transparent">
				더보기
				</button>
			</div>
	`);
	if(count == followCount) {
		$(".addBtn").remove();
	}
}
let imageState = $(".imageState").val();
function feedList(list){
	let count = 0;
	if (list.length !== 0) {
		$.each(list, (i, bl) => {
			boardAddCount += 1;
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
								<div id="testdiv">
									<button 
									type="button"
									class="likeBtn"
									id="unlike"
									data-postNo="${bl.postNo}"
									data-likestate="1"
									data-type="1"
									style="border:none; background-color:transparent;">
									<i class="far fa-heart"></i>
									</button>
								</div>
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
				count = boardAddCount ;
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
									<button type="button" class="boardModal" data-postNo="${bl.postNo}" style="border:none; background-color: transparent;">
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
				count = boardAddCount ;
			}; // else
		}); // each
	} // else
	console.log("co : " + count);
	console.log("bc : " + boardCount);
//	if (count == boardCount) {
//		$(".noBoard").css("display","none");
//		alert("??");
//	}
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
					if (loginUserNo == c.userNo){ // 로그인유저랑 댓글유저 같을경우
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
$(document).on("click", ".likeBtn",(e) => {
	// <i class="far fa-heart"></i> 좋아요누르기전
	// <i class="fas fa-heart"></i> 좋아요 누른 후

	 let likestate = $(".likeBtn").data("likestate"); // 1-> 안클릭 2-> 클릭
	 let type = $(".likeBtn").data("type");
	 let pnum = $(".likeBtn").data("postno");
	 
	 console.log("글번호 : " + pnum);
	 console.log("좋아요상태 : " + likestate);
	 console.log("타입 : " + type);
	 
	 if(likestate === 1){ // 누름
		 $("#unlike").remove();
		 $("#testdiv").append(`
				 <button 
				 type="button"
				 class="likeBtn"
				 id="like"
				 data-postNo="${pnum}"
				 data-likestate="2"
				 data-type="${type}"
				 style="border:none; background-color:transparent;">
				 <i class="fas fa-heart"></i>
				 </button>
		 `)
		 $.ajax({
				url: "boardLike.do",
				contentType : "application/json", 
				method:"POST",
				data: JSON.stringify({
					postAndCmtNo: pnum,
					userNo : loginUserNo,
					codeValue : type
				}),
				dataType: "json",
				success: list =>  aaa()
				
			});
	 }
	 else {
		 $("#like").remove();
		 $("#testdiv").append(`
				 <button 
				 type="button"
				 class="likeBtn"
				 id="unlike"
				 data-postNo="${pnum}"
				 data-type="${type}"
				 data-likestate="2"
				 style="border:none; background-color:transparent;">
				 	<i class="far fa-heart"></i>
				</button>
		 `);
		 $.ajax({
				url: "boardLikeDelete.do",
				contentType : "application/json", 
				method:"POST",
				data: JSON.stringify({
					postAndCmtNo: pnum,
					userNo : loginUserNo,
					codeValue : type
				}),
				dataType: "json",
				success: list =>  aaa()
			});
	 }

})








