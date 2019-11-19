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
	let postNo = document.querySelectorAll(".postNo");
	for (let a of postNo){
		let postnum = a.value;
		$("#insertComment" + postnum).submit(() => {
			$.ajax({
				url: "boardCommentInsert.do",
				contentType : "application/json", 
				method:"POST",
				data: JSON.stringify({postNo: postnum, cmtContent: $(".commentWriter"+ postnum).val(), userNo : $(".userNo").val() }),
				dataType: "json",
				success: list => boardCommentListAjax(list)
			});
			$(".commentWriter"+ postnum).val("");
			return false;
		});
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
					$bcla.append(
						   `<div class="commentList">
							    <div class="commentUserImg">
									<img src="./../images/test_user.jpg" alt="">
								</div>
								<div class="commentWrap">내용 : ${c.cmtContent}
									작성일자 : ${c.cmtRegDt}
									<button type="button" id="commentModal" class="commentModal${c.postNo}">
										<i class="fas fa-ellipsis-h"></i>
									</button>
								</div>
							</div>
							`
					);
					console.log("들어옴");
				}
				else {
					$bcla.append("댓글이 없습니다.");
					return;
				}
			});
			$("#boardCommentList" + postnum).html($bcla);
		};
//		$("#boardCommentList").html('');
		$(".myBoard" + postnum).click(() => {
		    $("#modalBoard" + postnum).css("display","block");
		});
		$(".boardClose" + postnum).click(() => {
		    $("#modalBoard" + postnum).css("display","none");
		});
		$(document).on("click",".commentModal" + postnum,() => {
			$("#modalComment" + postnum).css("display","block");
		});
		$(".commentModalClose").click(() => {
			$("#modalComment" + postnum).css("display","none");
		});
		
	} //for
})
























