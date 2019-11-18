/**
 * 
 */
//	// 모달창 클래스 토글 기능
//    function hideModal() {
//        modal.classList.toggle("hidden");
//    }
//    // 취소 버튼 클릭시 모달창 닫힘
//    mBtn.addEventListener("click", hideModal)
//    // 모달창 밖에 클릭시 모달창 닫힘
//    document.querySelector(".modal_overlay").addEventListener("click", hideModal)
//
//    // 모달창이 띄어졌을 시 스크롤 방지
//    $(".modal").on('scroll touchmove mousewheel', function (event) {
//        event.preventDefault();
//        event.stopPropagation();
//        return false;
//    });

// back to top
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
		    
		    
		    MainfeedMakeAjax();
		});
	   
	   
	   
	   function MainfeedMakeAjax() {
			$.getJSON({
				url: "mainfeedList.do",
				success: list =>  {
					makeMainFeedList(list)
				}
				
			})
		}
		function makeMainFeedList(list) {
			$.each(list, (i, c) => {
				$("#feedsWrap").append(`
					<div class="feedsList msrItem" id="feedsList">
						<div class="feedsInfo">
							<div class="feedUserImg">
								<img src="${pageContextURI}/images/test_user.jpg" alt="">
							</div>
							<div>
								<a href="#">${c.userNickname}</a>
								<button type="button">
									<i class="fas fa-ellipsis-h"></i>
								</button>
							</div>
						</div>

						<div class="feedsImgWrap">
							<a href="${pageContextURI}/mainfeed/detailmainfeed?postNo=${c.postNo}"> <img src="${pageContextURI}/images/main_bg.jpg" alt="">
							</a>
						</div>

						<div class="feedsPlay" id="feedsPlay${c.postNo}">
							<div class="feedsContWrap">${c.postContent}</div>
							`)
							if (`${c.hashtagContent}` != 'null') {
								$(`#feedsPlay${c.postNo}`).append(`<div class="hashTag">
								<a href="#">${c.hashtagContent}</a>
								</div>`)
							}
							$(`#feedsPlay${c.postNo}`).append(`
							<div class="playInfo">
								댓글 <span>${c.commentCount}</span>개
								<button>
									<i class="far fa-heart"></i> <span>${c.likeCount}</span>
								</button>
							</div>
						</div>
					</div>
				`);
			});
		}
	   
 // infinity scroll
		
// let html = "<div>";
// for (let i = 0; i < list.length; i++) {
// let tList = list[i];
// let a = document.createElement("span");
// a.innerHTML = "";
//				
// if ('${sessionScope.user.userNo}' == tList.userNo ||
// '${sessionScope.user.userGrade}' == 3){
// a.innerHTML =
// `<form method="post"
// action="${pageContext.request.contextPath}/team/teamBoardDelete.do" />
// <button onclick="return confirmDel();" style="margin-top:-100px; cursor:
// pointer; float: right; background: none; border: none">
// <i class="fa fa-trash-o fa-2x" aria-hidden="true"></i>
// </button>
// <input type="hidden" name="teamBoardNo" value="\${tList.teamBoardNo}"/>
// <input type="hidden" name="projectNo" value="\${tList.projectNo}"/>
// <input type="hidden" name="teamNo" value="\${tList.teamNo}"/>
// </form>`
// };
// html += `<ul class="board_cws">
// <li style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis;
// height: 35px; padding-top: 10px;">
// <a
// href="${pageContext.request.contextPath}/team/teamBoardDetail.do?teamBoardNo=\${tList.teamBoardNo}"
// style="font-size: 35px; ">
// \${tList.teamBoardTitle}</a></li>
// <br>
// <li><h6 style="margin: 0 auto">작성자: \${tList.userId}</h6></li>
// <li><h6 style="margin: 0 auto">작성일: \${tList.teamBoardRegDt}</h6></li>
// \${a.innerHTML}
// </ul>
// <br><br><br>
//			 		
// `;
// }
// html += "</div>";
// teamList.innerHTML += html;
//			
// }
//		
// $(window).on('scroll', function() {
// let scrollHeight = $(document).height();
// let scrollPosition = $(window).height() + $(window).scrollTop();
//
// $("#scrollHeight").text(scrollHeight);
// $("#scrollPosition").text(scrollPosition);
// $("#bottom").text(scrollHeight - scrollPosition);
//
// if (scrollPosition > scrollHeight - 300) {
// page++;
// teamListAjax();
// }
// });
