let pageIndex = 0;
const pageCount = 5;
let scrollTop = 0;
$(document).ready(function(){ 
	$(window).scroll(function(){ 
			if ($(this).scrollTop() > 100) { 
				$('#mainfeed-scroll').fadeIn(); 
		    } else { 
		    	$('#mainfeed-scroll').fadeOut(); 
		    } 
	    }); 
	    $('#mainfeed-scroll').click(function(){ 
	        $("html, body").animate({ scrollTop: 0 }, 600); 
	        return false; 
	    	}); 
	    boardList();
});
// infinity scroll
$(window).on('scroll', function() {
	scrollTop = $(window).scrollTop();
	if ($(window).height() == $(document).height() - Math.ceil($(window).scrollTop())) {
		pageIndex += pageCount;
  		boardList();
  	}
 });
function boardList(){
	$.getJSON({
		url: "feedlist.do",
		data:{
			pageIndex
		},
		success: list =>{
			if(list.length == 0) {
				$(window).off('scroll');
				console.log("sdf")
				return;
			}
			feedList(list);
		}
	})
}; // boardList
let aa = $(".aa").val();
console.log("이미지 : " + aa);
function feedList(list){
	$.each(list, (i, bl) => {
		if (aa === 1) { // 1사진 O 1이아닐때 사진X
			$("#feedWrap").append(`
					<div class="feedList">
						<div class="feedInfo">
							<div class="feedUserImg">
								<img src="./../images/test_user.jpg" alt="">
							</div>
							<div>
								<span>${bl.userNickName}</span>
								<button type="button" class="myBoard${bl.postNo}">
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
						<!--  modal -->
						<!-- boardModal -->
						<div id="modalBoard${bl.postNo}" class="board">
							<!-- boardModal content -->
							<div class="board-modal">
								<div>
									<button id="report" class="report" type="button">
									부적절한 컨텐츠 신고</button>
								</div>
							<div>
								<button id="share">퍼가기</button>
							</div>
							<div id="boardCancel "class="boardClose${bl.postNo}">취 소 - 게시글 번호 : ${bl.postNo}</div>
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
								<span>${bl.userNickName}</span>
								<button type="button" class="myBoard${bl.postNo}">
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
						<!--  modal -->
						<!-- boardModal -->
						<div id="modalBoard${bl.postNo}" class="board">
							<!-- boardModal content -->
							<div class="board-modal">
								<div>
									<button id="report" class="report" type="button">
									부적절한 컨텐츠 신고</button>
								</div>
							<div>
								<button id="share">퍼가기</button>
							</div>
							<div id="boardCancel "class="boardClose${bl.postNo}">취 소 - 게시글 번호 : ${bl.postNo}</div>
						</div>
					</div>
				`);
		}; // else
		
	}); // each
}; // feedList
   
   
let loginUserNo = $(".loginUserNo").val();
let loginUserNickName = $(".loginUserNickName").val();
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
		$(".myBoard" + postnum).click(() => {
			$("#modalBoard" + postnum).css("display","block");
		});
		$(".boardClose" + postnum).click(() => {
			$("#modalBoard" + postnum).css("display","none");
		});
		
	} //for
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
//수정취소
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
	
	$(".modalCancel").on("click", () => {
		$(".commentboardmodal").css("display","none");
	});
	
});










