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
$(document).ready(function(){
		aaa(); // 댓글
	    boardList(); // 게시글
	    sideFollowList(); // 사이드바
	    categoryList() // 팔로워추천 사이드바
});
$(document).on('click', '.addBtn', () => {
	sidePageIndex += sidePageCount;
	$(".addBtn").remove();
	sideFollowList();
});
// infinity scroll
$(window).scroll(() => {
	if ($(window).height() == $(document).height() - Math.ceil($(window).scrollTop())) {
		pageIndex += pageCount;
  		boardList();
  	}
 });
let boardListState = false; // 게시글 상태 
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
			if(list.length == 0 && !boardListState) {
				$(window).off('scroll');
				$(".feedWrap").append(
						`<div style="min-height: 615px;">
							<h2 class="noBoard">구독중인 사람이 없거나 게시글이 없습니다.</h2>
						</div>`);
				return;
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
						<img class="userCmtImg${sl.userNo}" alt="">
					</div>
					<div class="feedUserName">
						<a href="/awethumb/profile/${sl.userNickname}">${sl.userNickname}</a>
					</div>
				</div>`);
		count = followAddCount;
		let un = sl.userNo;
		userImg(un);
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
let likecount = 0;  // 좋아요개수 
let likecheck = 0;	// 좋아요 누른상태
let boardFileSrc = ``; // 파일경로 
let fileCheck = 0; // 파일체크 
let imageState = $(".imageState").val();
function feedList(list){
	boardListState = true;
	let count = 0;
		$.each(list, (i, bl) => {
			let postAndCmtNo = bl.postNo;
			let like = ``;
			let code = 1;
			let image = ``;
			fileCheck = boardFileCheck(bl.postNo);
			if (fileCheck > 0) {
				image = `<div id="feedImgWrap" class="feedImgWrap${bl.postNo}">
								<img id="feedImg"
									 alt="" />
							<div class="hoverWrap">
								<div>
									<button class="like">
										<i class="far fa-heart"></i><span>4</span>
									</button>
								</div>
							</div>
						</div>`;
			}
			boardFileSrc = boardFile(bl.postNo);
			like = likeAdmin(postAndCmtNo, loginUserNo, code);
			if (imageState !== 0) { // 값이 없을때 사진이 존재 값이 있다면 사진X
				$("#feedWrap").append(`
						<div class="feedList">
							<div class="feedInfo">
								<div class="feedUserImg${bl.postNo}">
									<img class="userCmtImg${bl.userNo}" alt="">
								</div>
								<div>
									<a href="/awethumb/profile/${bl.userNickName}"><span>${bl.userNickName}</span></a>
									<button type="button" class="boardModal" data-postNo="${bl.postNo}">
										<i class="fas fa-ellipsis-h"></i>
									</button>
								</div>
							</div>
							${image}
							<div class="feedText">
								${like}
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
				`) // postAndCmtNo
				likecount = likeCount(postAndCmtNo);
				if(likecount > 0) {
					$("#countLike" + postAndCmtNo).html(`
					<span id="countSpan${postAndCmtNo}">${likecount}회 좋아요</span>`);
				}
			} // if
			let un = bl.userNo;
			userImg(un);
		}); // each
}; // feedList

// 댓글
function aaa () {
	let postNo = document.querySelectorAll(".postNo");
//	console.log(postNo.value)
//	let postNo;
//	$.get({
//		url:"feedboardNo.do",
//		success: (list) => { 
//			postNo : list
//			console.log("dsdfds");
//		}
//	})
//	console.log(postNo)
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
				let postAndCmtNo = c.cmtNo;
				let code = 2;
				let like =``;
				like = likeAdmin(postAndCmtNo, loginUserNo, code);
				if(c.postNo == postnum){
						$bcla.append(
								`<div class="commentList${c.cmtNo} commentList" id="commentList">
									<div class="commentUserImg">
										<img class="userCmtImg${c.userNo}" alt="">
									</div>
									<div id="commentWrap${c.cmtNo}" class="commentWrap">
										<div class="cmtInfo">
											<span style="color:green;">글작성자 : ${c.cmtUserNickname}</span>
											<span style="color:red;">작성시간 : ${c.cmtRegDt}</span>
											<button type="button" 
												id="commentModal${c.cmtNo}"
												class="commentModal"
												data-cmtuserno="${c.userNo}"
												data-commentContent="${c.cmtContent}"
												data-commentNo="${c.cmtNo}"
												data-postNo="${c.postNo}">
												<i class="fas fa-ellipsis-h"></i>
											</button>
											${like}
										</div>
										<div class="cmtContent" style="color:blue;">
											내용 : ${c.cmtContent} 
										</div>
									</div>
								</div>`
						);
				}// if
				// 유저사진
				let un = c.userNo;
				userImg(un);
			}); // each
			$("#boardCommentList" + postnum).html($bcla);
		};
	} // for
} // aaa
// 입력
$(document).on( "click",".commentInsertBtn", (e) => {
	let postNumber = $(e.target).data("postnumber");
	let commentWriter = $(".commentWriter" + postNumber).val();
	$.ajax({
		url: "boardCommentInsert.do",
		contentType : "application/json", 
		method:"POST",
		async:false,
		data: JSON.stringify({
			postNo: postNumber,
			cmtContent: commentWriter,
			userNo : loginUserNo,
			cmtUserNickname : loginUserNickName
		}),
		success: (no) =>  {
			makeAlarm(3, no)
			aaa()
		}
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
		success: list => aaa()
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
	let cmtuserno = $(obj).data("cmtuserno");
	if(cmtuserno != loginUserNo) { // 내가쓴 댓글이 아니면 신고만 나오게하는 조건문 
		$(".commentDelete").css("display", "none");
		$(".commentModify").css("display", "none");
	}else {
		$(".commentDelete").css("display", "block");
		$(".commentModify").css("display", "block");
	}
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
$(document).on("click", ".report", (e) => {
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
// 좋아요 이벤트 
$(document).on("click", ".likeBtn",(e) => {
	 likeClick(loginUserNo, e.target);
 }) // 좋아요버튼클릭이벤트
 
// 유저 이미지
function userImg(userNo){
	// url : pageContextPath + "/api/user/유저번호/thumb"
	// .done((e) => { $("img").attr("src", e)})
	$.get({
		url:pageContextPath + "/api/user/"+ userNo + "/thumb",
		success: (src) => {
			$(".userCmtImg" + userNo).attr("src", src);
		}
		
	})
}
function boardFile(no) {
	$.get({
		url: "boardFileRead.do",
		data: {postNo : no},
		success: (list) => {
			for(let i = 0; i < list.length; i++){
				$(".feedImgWrap" + no).append(
						`<img id="feedImg" src="${list[i]}"alt="" />`);
				
			}
		},
		error:(error) => {
			console.log(error);
		}
			
	})
}
function boardFileCheck(no) {
	let fileCk = 0;
	$.get({
		url: "boardFileCheck.do",
		data: {postNo : no},
		async: false,
		success: (fno) => {
			fileCk = fno
		}
	});
	return fileCk;
}
function categoryList(){
	$.get({
		url:"categoryListSideBar.do",
		data:{
			userId : loginUserId
		},
		success: (list) => {
			if(!list) { // list가 없다면
				$(".categoryListSide").append(
				`<h2>팔로워 추천이 없습니다.</h2>`);
			}
			for(let i = 0; i < list.length; i++) {
				$(".categoryListSide").append(
						`<div class="feedSideUserList">
							<div class="feedInfo">
								<div class="feedUserImg">
									<img class="userCmtImg${list[i].userNo}" alt="">
								</div>
								<div class="feedUserName">
									<a href="${pageContextPath}/profile/${list[i].userNickName}">
										${list[i].userNickName}
									</a>
									<span style="color:#6dd5bc">${list[i].categoryTitle}</span>
								</div>
							</div>
						</div>`
				);
				let un = list[i].userNo;
				userImg(un);
			} // for
		} // success 
	}) 
}


function error(){
	return "에러";
}





