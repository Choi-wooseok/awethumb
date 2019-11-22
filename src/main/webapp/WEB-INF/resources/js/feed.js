let loginUserNo = $(".loginUserNo").val();
	let postNo = document.querySelectorAll(".postNo");
	for (let a of postNo){
		let postnum = a.value; // 게시글번호받기 걷기
		$(function () {
			// 등록
			$("#insertComment" + postnum).submit(() => {
				$.ajax({
					url: "boardCommentInsert.do",
					contentType : "application/json", 
					method:"POST",
					data: JSON.stringify({
						postNo: postnum,
						cmtContent: $(".commentWriter"+ postnum).val(),
						userNo : loginUserNo
					}),
					dataType: "json",
					success: list => boardCommentListAjax(list)
				});
				$(".commentWriter"+ postnum).val("");
				return false;
			});
			// 삭제
			$("#boardCommentList" + postnum).on("click", "button.delete", (e) => {
				$(".updateText").css("display","none");
				$.ajax({
					url: "boardCommentDelete.do",
					data: {
						cmtNo: $(e.target).data("no"),
						postNo: postnum
						},
					dateType:"json",
					success: (list) => boardCommentListAjax(list)
				});
			});
			// 수정취소
			$("#boardCommentList" + postnum).on("click", "button.cancel", (e) => {
				$("#updateText").css("display","none");
			});
			// 댓글 수정
			$("#boardCommentList" + postnum).on("click", "button.update", (e) => {
				let cmtNo = $(e.target).data("no");
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
			$("#boardCommentList" + postnum).on("click", "button.modify", (e) => {
				$(".commentboard").css("display","none");
				let cmtNo = $(e.target).data("no");
				let cmtCon = $(e.target).data("context");
				$("#commentWrap" +  cmtNo).append(
						`<div id="updateText" class="updateText">
							<input type="text" id="contentUpdate" value="${cmtCon}"/>
							<button class="update" data-no=${cmtNo}>수정</button>
							<button class="cancel" data-no=${cmtNo}>취소</button>
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
									<button type="button" id="commentModal"
									 class="commentModal${c.cmtNo}"
									 data-commentNo="${c.cmtNo}">
										<i class="fas fa-ellipsis-h"></i>
									</button>
								</div>
							</div>
							<div id="modalComment${c.cmtNo}" class="commentboard">
								<div class="comment-modal">
									<h4>
										<button
										data-no="${c.cmtNo}"
										data-context="${c.cmtContent}"
										class="modify">
										수정 - 댓글번호 :  ${c.cmtNo} 
										</button>
									</h4>
									<h4>
										<button
										 data-no="${c.cmtNo}"
										 class="delete"> 
										삭제  - 댓글번호 :  ${c.cmtNo}
										</button>	
									</h4>
									<h4 class="commentModalClose">취 소</h4>
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
		let cno = document.querySelectorAll(".commentNo");
		for (let cn of cno){
			let cmtNo = cn.value;
			$(document).on( "click",".commentModal" + cmtNo, (e) => {
				let abc = $(e.target).parent("button").data("commentno");
				if(cmtNo == abc){
					$("#modalComment" + abc).css("display","block");
					$(document).on("click",".commentModalClose", () => {
						$("#modalComment" + abc).css("display","none");
					});
				} // if
			});
		} // for
//		function divreload(postNo){
//		      $("#commentInsertBtn").click(() => {
//		    	  alert("클릭댐");
//		    	  window.location.href + ("#commentList")
//		      });
//		      alert("새로고침함");
//		}
//		function refresh(href) {
//			jQuery('#commentList').load(href);
////			alert("새로고침함");
//			}
	} //for












