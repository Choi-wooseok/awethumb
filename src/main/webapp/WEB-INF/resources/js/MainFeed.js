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
	   
// -------------- mainfeed 생성 -------------------------
	   
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

						<div class="feedsImgWrap ">
							<a class="detailFeed" href="javascript:;"> <img src="${pageContextURI}/images/main_bg.jpg" alt="">
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
		
// --------------- detail -------------
	$(".detailFeed").click(function() {
		$.getJSON({
			url: "detailmainfeed.do",
			data: {postNo},
			success: result => makeDetailFeed(result)
		})
	});
	function makeDetailFeed(detailList) {
		console.log("1", detailList);
		$feed = $("#detailFeedModal");
		$.each(detailList, (i, d) => {
			$feed.append(`
				<div class="modal hidden">
					<div class="modal_overlay"></div>
					<div class="modal_content_container">
						<i class="fas fa-caret-left arw-btn"></i>
						<div class="modal_content">
						<div id="boxSize">
	                    	<img id="image" src="./../images/test_img3.jpg" alt="">
						</div>
						<div id="rightBox">
	                    	<div class="ModaluserInfo">
	                        	<div class="commentUser">
	                            	<img src="./../images/test_user.jpg" alt="">
	                        	</div>
	                        	<div class="userName">
	                            	<a href="#">${d.userNickname}</a>
	                            	<button id="myBoard"><i class="fas fa-bars"></i></button>
	                        	</div>
                    		</div>
                    	`)
            $.each(d.commentList, (i, c) => {
            	$feed.append(`
            				<div class="comment">
        						<div class="commentList">
		                			<div class="commentUserImg">
		                    			<img src="./../images/test_user.jpg" alt="">
		                			</div>
		                			<div class="commentWrap">
		                   				${c.commentContent}
		                			</div>
        						</div>
            				</div>
            	 `)
            })
            $feed.append(`
			            	<div class="insertComment">
			                    <input type="text" />
			                    <button>등록</button>
			                </div>
            			</div>
            		</div>
	            <i class="fas fa-caret-right arw-btn"></i>
	        </div>
	    </div>

	    <div id="modalBoard" class="board">
	        <div class="board-modal">
	            <h4> <button id="report" type="button" onclick="doReport()">신 고<i class="fas fa-angry"></i></button></h4>
	            <h4> <a href="bb.html"><button id="share">퍼가기</button></a></h4>
	            <h4 class="boardClose">취 소 </h4>
	        </div>
	    </div>
			`)
		})
	}

// --------- detail modal -----------------
        $(document).ready(function() {
        	if (window.location.href.indexOf("detailmainfeed.do") != -1) {
        	let maxSize = 600;
        	let image = document.getElementById("image");
        	console.log(image);
        	let boxSize = document.getElementById("boxSize");
        	let imgHeight = image.height;
        	let imgWidth = image.width;
        	let rightBox = document.getElementById("rightBox");
            if (imgWidth > maxSize && imgHeight > maxSize) {
                if (imgWidth > imgHeight) {
                    boxSize.style.width = maxSize+"px";
                    boxSize.style.height = "auto";
                    image.style.width = "100%";
                } else {
                    boxSize.style.width = "auto";
                    boxSize.style.height = maxSize+"px";
                    image.style.height = "100%";                }
            } else if (imgWidth > maxSize && imgHeight < maxSize) {
                boxSize.style.width = maxSize+"px";
                image.style.width =  "100%";
            } else if (imgWidth < maxSize && imgHeight > maxSize) {
                box.Size.style.height = maxSize+"px";
                image.style.height = "100%";
            }
            rightBox.style.height = boxSize.style.height;
        

        const modal = document.querySelector(".modal");
        const mBtn = document.querySelector("#detailFeed");
        
        // 모달창 클래스 토글 기능
        function hideModal() {
            modal.classList.toggle("hidden");
        }
        // 취소 버튼 클릭시 모달창 닫힘
        mBtn.addEventListener("click", hideModal)
        // 모달창 밖에 클릭시 모달창 닫힘
        document.querySelector(".modal_overlay").addEventListener("click", hideModal)

        // 모달창이 띄워졌을 시 스크롤 방지
        $(".modal").on('scroll touchmove mousewheel', function(event) {
            event.preventDefault();
            event.stopPropagation();
            return false;
        
     // board
        // Get the modal
        var boardModal = document.getElementById('modalBoard');

        // Get the button that opens the modal
        var btn = document.getElementById("myBoard");

        // Get the <span> element that closes the modal
        var bc = document.getElementsByClassName("boardClose")[0];

        // When the user clicks on the button, open the modal
        btn.onclick = function () {
            boardModal.style.display = "block";
        }

        // When the user clicks on <span> (x), close the modal
        bc.onclick = function () {
            boardModal.style.display = "none";
        }

        // When the user clicks anywhere outside of the modal, close it
        window.onclick = function (event) {
            if (event.target == boardModal) {
                boardModal.style.display = "none";
            }
        }

        // 신고
        function doReport() {
            boardModal.style.display = "none";
        }
        })
        	}
        });
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
