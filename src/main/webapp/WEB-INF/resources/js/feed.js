//let pageIndex = 0; // 시작
//const pageCount = 5; // 개수
//let scrollTop = 0;
//let loginUserNo = $(".loginUserNo").val();
//let loginUserId = $(".loginUserId").val();
//let sidePageIndex = 0; // 시작
//const sidePageCount = 4; // 범위
//let followCount = $(".userFollowMeCount").val(); // 맞팔안한 나를 전체팔로우하는사람 count
//let followAddCount = 0; // 더보기 창 없애기위한 변수
//let boardListState = false; // 게시글 list 끝체크유무
//let likecount = 0;  // 좋아요개수
//let boardFileSrc = ``; // 파일경로
//let fileCheck = 0; // 파일체크
//let imageState = $(".imageState").val();
let item = {
		pageIndex : 0, // 게시글 시작
		pageCount : 5, // 게시글 개수
		loginUserNo : $(".loginUserNo").val(),
		loginUserId : $(".loginUserId").val(),
		sidePageIndex : 0, // 시작
		sidePageCount : 4, // 범위
		followCount : $(".userFollowMeCount").val(), // 맞팔안한 나를 전체팔로우하는사람
		followAddCount : 0, // 더보기 창 없애기위한 변수
		boardListState : false, // 게시글 list 끝체크유무
		likecount : 0,  // 좋아요개수
		boardFileSrc : ``, // 파일경로
		fileCheck : 0, // 파일체크
		imageState : 0, // 이미지체크
		left : 0, // 사이드바 X좌표
		top : 0, // 사이드바1 Y좌표
		top2 : 0, // 사이드바2 Y좌표
		feedSideGap : 0 // feed와 side바 사이 값
}
$(document).ready(function(){
	    boardList(); // 게시글
	    sideFollowList(); // 사이드바
	    categoryList() // 팔로워추천 사이드바
	    item.feedSideGap = $(".feedSide").offset().left - ($(".feedContWrap").offset().left + 600);
	    item.left = $(".feedContWrap").offset().left + 600 + item.feedSideGap;
	    item.top = $(".feedSide").offset().top;
	    item.top2 = $(".feedSide2").offset().top;
	    $("#commentWriter").hashtags(); // 해시태그 js 호출 
});
$(document).on('click', '.addBtn', () => {
	item.sidePageIndex += item.sidePageCount;
	$(".addBtn").remove();
	sideFollowList();
});
$(window).resize(() => { // 반응형 웹
	item.left = $(".feedContWrap").offset().left + 635;
	item.top = 105;
	item.top2 = 405;
	$(".feedSide").css({"position":"fiexd", "left":item.left, "top":item.top});
	$(".feedSide2").css({"position":"fiexd", "left":item.left, "top":item.top2});
});

// infinity scroll
$(window).scroll(() => {
	if($(window).scrollTop() !== 0) {
		item.left = $(".feedContWrap").offset().left + 635;
		$(".feedSide").css({"position":"fixed", "left":item.left, "top":item.top});
		$(".feedSide2").css({"position":"fixed", "left":item.left, "top":item.top2});
	}
	if ($(window).height() == $(document).height() - Math.ceil($(window).scrollTop())) {
		item.pageIndex += item.pageCount;
  		boardList();
  	}
 });
function boardList(){
	$.getJSON({
		url: "feedlist.do",
		dataType:"JSON",
		data:{
			pageIndex : item.pageIndex,
			pageCount : item.pageCount,
			subUserNo: item.loginUserNo
		},
		success: (list) => {
			if(list.length == 0 && !item.boardListState) {
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
	$.getJSON({
		url: "feedsidelist.do",
		dataType:"JSON",
		data:{
			sidePageIndex : item.sidePageIndex,
			sidePageCount : item.sidePageCount,
			userId:item.loginUserId
		},
		success: list => {
			if (list.length == 0) {
				$(".feedSideUserList").append("<h2>팔로우중인 사람이 없습니다.</h2>");
			}
			$(".addBtn").css("display","none");
			feedSideFollowMe(list);
		}
	})
}; // sideFollowList
function feedSideFollowMe(list) {
	let count = 0 ;
	$.each(list, (i, sl) => {
		item.followAddCount += 1;
		$("#feedSideUserList").append(`
				<div class="feedInfo">
					<div class="feedUserImg">
						<img class="userCmtImg${sl.userNo}" alt="">
					</div>
					<div class="feedUserName">
						<a href="${pageContextPath}/profile/${sl.userNickname}">${sl.userNickname}</a>
					</div>
				</div>`);
		count = item.followAddCount;
		let un = sl.userNo;
		userImg(un);
	}) // each
	$("#feedSideUserList").append(`
			<div class="addBtn" id="addBtn" style="text-align:center;">
				<button type="button" style=" border:none; background-color:transparent">
				더보기
				</button>
			</div>
	`); // 더보기 
	if(count == item.followCount) {
		$(".addBtn").remove();
	}
} // feedSideFollowMe
function feedList(list){
	item.boardListState = true;
	console.log(list);
	let count = 0;
		$.each(list, (i, bl) => {
			let postAndCmtNo = bl.postNo;
			let like = ``;
			let code = 1;
			let image = ``;
			item.fileCheck = boardFileCheck(bl.postNo);
			if (item.fileCheck > 0) {
				image = `<div id="feedImgWrap" class="feedImgWrap${bl.postNo}">
						</div>`;
			}
			item.boardFileSrc = boardFile(bl.postNo);
			like = likeAdmin(postAndCmtNo, item.loginUserNo, code);
			imgstate = imageState(bl.postNo);
			if (imgstate !== 0) { // 값이 없을때 사진이 존재 값이 있다면 사진X
				$("#feedWrap").append(`
						<div class="feedList">
							<div class="feedInfo">
								<div class="feedUserImg${bl.postNo}">
									<img class="userCmtImg${bl.userNo}" alt="">
								</div>
								<div>
									<a href="${pageContextPath}/profile/${bl.userNickName}"><span>${bl.userNickName}</span></a>
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
								<div id="commentList${bl.postNo}">
									<div id="boardCommentList${bl.postNo}"></div>
								</div>
								<div class="insertComment">
									<input id="commentWriter" class="commentWriter${bl.postNo}" type="text" />
									<button type="button" class="commentInsertBtn" data-postNumber="${bl.postNo}">등록</button>
								</div>
							</div>
						</div>
				`) // postAndCmtNo
				commentListAjax(postAndCmtNo)
				item.likecount = likeCount(postAndCmtNo);
				if(item.likecount > 0) {
					$("#countLike" + postAndCmtNo).html(`
					<span id="countSpan${postAndCmtNo}">${item.likecount}회 좋아요</span>`);
				}
			} // if
			let un = bl.userNo;
			userImg(un);
		}); // each
}; // feedList
// 댓글 등록 
$(document).on( "click",".commentInsertBtn", (e) => {
	let postNo = $(e.target).data("postnumber");
	let commentWriter = $(".commentWriter" + postNo).val();
	if(!commentWriter) {
		alert("댓글 내용을 입력해주세요.")
	}
	else {
		$.ajax({
			url: "boardCommentInsert.do",
			contentType : "application/json", 
			method:"POST",
			data: JSON.stringify({
				postNo: postNo,
				cmtContent: commentWriter,
				userNo : item.loginUserNo
			}),
//			success: (no) =>  {
//				makeAlarm(3, no)
//				commentListAjax(postNo);
//			},
//			error : (e) => {
//			}
		}).done(no => {
			$.ajax({ 
				url: pageContextPath + "/mainfeed/insertHashtag.do",
				method:"POST",
				contentType: "application/json; charset=UTF-8",
				data: JSON.stringify(hashSplitFn(no, commentWriter, 2)),
				dataType: "JSON",
				tranditional: true,
			})
		}).done((no) => {
			makeAlarm(3, no);
			commentListAjax(postNo);
		})
		$(".commentWriter"+ postNo).val("");
		return false;
	}
}); // 댓글 등록
// 댓글 삭제
$(".commentDelete").on("click", (e) => {
	let cmtNo = $(e.target).data("commentno");
	let postNo = $(e.target).data("postno");
	$(".updateText").css("display","none");
	$.ajax({
		url: "boardCommentDelete.do",
		data: {
			cmtNo: cmtNo
		},
		dateType:"json",
//		success: (list) => {
//			commentListAjax(postNo);
//		},
//		error: (e) => {
//			console.log
//		}
	}).done(result => {
		$.ajax({
			url: pageContextPath + "/mainfeed/deleteHashtag.do",
			type: "POST",
			contentType: "application/json; charset=UTF-8",
			data: JSON.stringify({
				'postNoAndCmtNo' : cmtNo,
				'hashType' : 2
			}),
			dataType:"JSON",
			tranditional: true,
		})
	}).done((result) => {
		commentListAjax(postNo);
	})
	$(".commentboardmodal").css("display","none");
}); // 댓글 삭제
// 댓글 수정취소
$(document).on("click", ".commentCancel",() => {
	$(".updateText").css("display","none");
});
// 댓글 수정
$(document).on("click", ".commentUpdate",(e) => {
	let cmtNo = $(e.target).data("cmtno");
	let postNo = $(e.target).data("postno");
	let cmtContent = $("#contentUpdate").val();
	if(!cmtContent) {
		alert("댓글내용을 입력해주세요.")
	}
	else {
		$.ajax({
			url: "boardCommentUpdate.do",
			type: "POST",
			data: {
				postNo : postNo,
				cmtContent: cmtContent, 
				cmtNo :  cmtNo
			},
			dataType: "json",
//			success: (list) => {
//				commentListAjax(postNo);
//			},
//			error: (e) => {console.log(e)}
		}).done(() => {
			$.ajax({
				url: pageContextPath + "/mainfeed/deleteHashtag.do",
				type: "POST",
				contentType: "application/json; charset=UTF-8",
				data: JSON.stringify({
					'postNoAndCmtNo' : cmtNo,
					'hashType' : 2
				}),
				dataType:"JSON",
			})
		}).done((result) => {
			$.ajax({
				url: pageContextPath + "/mainfeed/insertHashtag.do",
				method:"POST",
				contentType: "application/json; charset=UTF-8",
				data: JSON.stringify(hashSplitFn(cmtNo, cmtContent, 2)),
				dataType: "JSON",
				tranditional: true,
			})
		}).done(() => {
			commentListAjax(postNo);
		})
	}
	
}); // 댓글 수정 
// 댓글 수정폼
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
}); // 댓글 수정폼 
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
	if(cmtuserno != item.loginUserNo) { // 내가쓴 댓글이 아니면 신고만 나오게하는 조건문
		$(".commentDelete").css("display", "none");
		$(".commentModify").css("display", "none");
	}else {
		$(".commentDelete").css("display", "block");
		$(".commentModify").css("display", "block");
	}
	$(".commentboardmodal").css("display","block");
	
	$(".commentDelete").data("commentno", cmtNo);
	$(".commentDelete").data("postno", postNo);
	
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
		newWindow.location.href = `${pageContextPath}/report/insertReportForm.do?postNo=${postNo}`;
	}
	else { // 댓글 신고
		let newWindow = window.open("about:blank");
		newWindow.location.href = `${pageContextPath}/report/insertReportForm.do?postNo=${postNo}&commentNo=${cmtNo}`;
	}
});
// 좋아요 이벤트
$(document).on("click", ".likeBtn",(e) => {
	 likeClick(item.loginUserNo, e.target);
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
} // userImg
function boardFile(no) {
	$.get({
		url: "boardFileRead.do",
		data: {postNo : no},
		success: (list) => {
			for(let i = 0; i < list.length; i++){
				$(".feedImgWrap" + no).append(
						`<img id="feedImg" src="${list[i]}"alt="" />`);
					if (i == list.length - 1) {
						$(".feedImgWrap" + no).slick();
					}; // 이미지 슬라이드 
			}
		},
		error:(error) => {
			console.log(error);
		}
	})
} // boardFile
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
} // boardFileCheck
function categoryList(){
	$.get({
		url:"categoryListSideBar.do",
		data:{
			userId : item.loginUserId
		},
		success: (list) => {
			if(list.length == 0) { // list가 없다면
				$(".categoryListSide").append(
				"<h2>팔로워 추천이 없습니다.</h2>");
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
} // categoryList
function commentListAjax(postNo) {
	// boardCommentListAjax(bl.commentList, postAndCmtNo)
	$("#boardCommentList" + postNo).remove();
	$("#commentList" + postNo).html(`<div id="boardCommentList${postNo}"></div>`)
	$.getJSON({
		url: "boardCommentList.do",
		data: {postNo: postNo},
		success: list => boardCommentListAjax(list, postNo)
	})
} // commentListAjax
function boardCommentListAjax(list, postNo) {
	$.each(list, (i	, c) => {
		const newContent = renderHashtag(c.cmtContent);
		let postAndCmtNo = c.cmtNo;
		let code = 2;
		let like =``;
		like = likeAdmin(postAndCmtNo, item.loginUserNo, code);
				$("#boardCommentList" + postNo).append(
						`<div id="comment${c.cmtNo}">
							<div class="commentList${c.cmtNo} commentList" id="commentList">
								<div class="commentUserImg">
									<img class="userCmtImg${c.userNo}" alt="">
								</div>
								<div id="commentWrap${c.cmtNo}" class="commentWrap">
									<div class="cmtInfo">
										<a href="${pageContextPath}/profile/${c.cmtUserNickname}">
										<span>${c.cmtUserNickname}</span>
										</a>
										<span>작성시간 : ${c.cmtRegDt}</span>
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
									<div class="cmtContent">
										내용 : ${newContent} 
									</div>
								</div>
							</div>
						</div>`);
		// 유저사진
		let un = c.userNo;
		userImg(un);
		// 해스태그 클릭시 이동
		hashClickFn();
	}); // each
}; // boardCommentListAjax
// 게시글 사진 확인
function imageState(postNo) {
	$.ajax({
		url: "feedimg.do",
		data: {postNo: postNo},
		async: false,
		success: (no) => {
			item.imageState = no;
		}
	})
	return item.imageState;
};





