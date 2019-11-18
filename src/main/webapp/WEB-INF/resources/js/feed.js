let maxSize = 598;
let boxSize = document.getElementById("feedImgWrap")
let image = document.getElementById("feedImg");
let imgHeight = image.height;
let imgWidth = image.width;
$(document).ready(function() {
	if (imgWidth > maxSize && imgHeight > maxSize) {
		if (imgWidth > imgHeight) {
			boxSize.style.width = maxSize + "px";
			boxSize.style.height = "auto";
			image.style.width = "100%";
		} else {
			boxSize.style.width = "auto";
			boxSize.style.height = maxSize + "px";
			image.style.height = "100%";
		}
	} else if (imgWidth > maxSize && imgHeight < maxSize) {
		boxSize.style.width = maxSize + "px";
		image.style.width = "100%";
	} else if (imgWidth < maxSize && imgHeight > maxSize) {
		boxSize.style.height = maxSize + "px";
		image.style.height = "100%";
	}
})

$(".myBoard").click(() => {
    $("#modalBoard").css("display","block");
});
$(".boardClose").click(() => {
    $("#modalBoard").css("display","none");
    // $("#modalBoard").addClass("aaaa");
});

// comment
//	// 댓글 등록
//	$("#insertComment").submit(() => {
//		console.log("insertpostNo : " + postNo); // 1 2 3 4 5
//		$.post({
//			url: "boardCommentInsert.do",
//			dataType: "json",
//			contentType: 'application/json',
//			data: JSON.stringify({postNo, cmtContent: $(".commentWriter").val(), userNo : $(".userNo").val()}),
//			success: (list) => boardCommentListAjax(list)
//		});
//		$(".commentWriter").val("");
//		return false;
//	});
//});
//commentListAjax();
//function commentListAjax() {
//	console.log("postNo : " + postNo); // 1 2 3 4 5
//	$.getJSON({
//		url: "boardCommentList.do",
//		data: {postNo},
//		async: false,
//		success: list => boardCommentListAjax(list)
//		
//	});
//}
//function boardCommentListAjax(list) {
//	$bcla = $(`<div class="commentList"></div>`);
//	$.each(list, (i	, c) => {
//		console.log("아ㅡㅡ : " + c.postNo); // 
//		console.log("아오씨 : " + postNo); // 6 ?
//		if(c.postNo == postNo){
//			console.log("들어옴?");
//			$bcla.append(
//					`<div class="commentList">
//						<div class="commentUserImg">
//							<img src="./../images/test_user.jpg" alt="">
//						</div>
//						<div class="commentWrap">${c.cmtContent}
//						작성일자 : ${c.cmtRegDt} js체크용
//						게시판번호 : ${c.postNo}
//						</div>
//					</div>`		
//			);
//			return;
//		}
//		else {
//			console.log("이씨 : " + postNo); // 6 ?
//			$bcla.append("댓글이 없습니다.");
//			return;
//		}
//	});
//	$("#boardCommentList").html($bcla);
//	return;
//};
let postNo = document.querySelectorAll("#postNo");
console.log("postNo^^ㅣ발 : " + postNo);
commentListAjax();
function commentListAjax() {
	$.getJSON({
		url: "boardCommentList.do",
		data: {postNo},
		async: false,
		success: list => boardCommentListAjax(list)
		
	});
}
function boardCommentListAjax(list) {
	$bcla = $(`<div class="commentList"></div>`);
	postNo.forEach(function(val, i ) {
	    if(c.postNo == postNo){
	    	$bcla.append(
	    	`<div class="commentList">
				<div class="commentUserImg">
					<img src="./../images/test_user.jpg" alt="">
				</div>
				<div class="commentWrap">${c.cmtContent}
				작성일자 : ${c.cmtRegDt} js체크용
				게시판번호 : ${c.postNo}
				</div>
			</div>`	
	    	);
			return;
	    }
	});
	$("#boardCommentList").html($bcla);
	return;
}
//	$.each(list, (i	, c) => {
//		if(c.postNo == postNo){
//			$bcla.append(
//					`<div class="commentList">
//						<div class="commentUserImg">
//							<img src="./../images/test_user.jpg" alt="">
//						</div>
//						<div class="commentWrap">${c.cmtContent}
//						작성일자 : ${c.cmtRegDt} js체크용
//						게시판번호 : ${c.postNo}
//						</div>
//					</div>`		
//			);
//			return;
//		}
//		else {
//			$bcla.append("댓글이 없습니다.");
//			return;
//		}
//	});
//	$("#boardCommentList").html($bcla);
//	return;
//};









