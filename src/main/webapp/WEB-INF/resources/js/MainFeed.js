let pageIndex = 0;
const pageCount = 5;
let scrollTop = 0;

// back to top

	   $(document).ready(function(){ 
		   console.log("시작")
		
		    $(window).scroll(function(){ 
		    	console.log("씨밯")
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
	   
// infinity scroll
	
	   $(window).on('scroll', function() {
	  	 
	  	 scrollTop = $(window).scrollTop();
	  
	  	 if ($(window).height() == $(document).height() - Math.ceil($(window).scrollTop())) {
	  		 pageIndex += pageCount;
	  		 MainfeedMakeAjax();
	  	 }
	   });
	   
// masonry 형식 만들기 ----------------------------------------- 
	   function masonry() {
		 //init
		   $('.msrItems').msrItems({
				'colums' : 3, //columns number
				'margin' : 20
			//right and bottom margin
			});
		   
	   }
	   
	 
// mainfeed 생성 및 페이징 --------------------------------------
	   function MainfeedMakeAjax() {
			$.getJSON({
				url: "mainfeedList.do",
				data: {
					pageIndex
				},
				success: list => {
					if(list.length == 0) {
						$(window).off('scroll');
						return;
					}
					makeMainFeedList(list);

//					setTimeout(() => {masonry()}, 100);
//					masonry();
				}
			})
//			masonry();
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
							<a class="detailFeed" href="javascript:;" > <img src="${pageContextURI}/images/main_bg.jpg" alt="" data-postno="${c.postNo}">
							</a>
						</div>

						<div class="feedsPlay" id="feedsPlay${c.postNo}">
							<div class="feedsContWrap">${c.postContent}</div>
							<br>
						<br>
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
			console.log($(window).scrollTop());
			setTimeout(() => {masonry(); 
			console.log($(window).scrollTop());
			$(window).scrollTop(scrollTop)}, 100);
			 // Make jQuery object from HTML string

		    // Append new blocks to container

		    // Have Masonry position new blocks
			
//		    $('#feedsWrap').masonry( 'appended', $moreBlocks );  
//			setTimeout(() => {masonry()}, 100);
		}
		
// --------------- detail -------------
	$(document).on('click', '.detailFeed', (e) => {
		$.ajax({
			url: "detailmainfeed.do",
			data: {
				postNo:$(e.target).data("postno")
			},
			dataType: "JSON",
			success: result => {
				makeDetailFeed(result);
				setTimeout(() => {
					makemodalattribute({
						w: $("#image").width(),
						h: $("#image").height()
					})
				}, 100);
			}
		})
//		let postNum = $(e.target).data("postno");
//		$("#modal" + postNum).css('display', 'block');
	});
	 
	function makeDetailFeed(detail) {
		$feed = $("#detailFeedModal");
			$feed.html(`
				<div class="modal" id="modal${detail.postNo}">
					<div class="modal_content_container">
						<div class="modal_content">
		                	<div class="modalTitle" >
								<button class="modalClose" id="modalClose">
									<i class="fas fa-times" data-postnum="${detail.postNo}"></i>
								</button>	
		                		<div class="ModaluserInfo">
									<div class="commentUser">
										<img src="./../images/test_user.jpg" alt="">
									</div>
							        <div class="userName">
							            <a href="#">userName</a>
							            <button id="myBoard"><i class="fas fa-bars"></i></button>
							        </div>
								</div>
							</div>
							<div class="modalContWrap">
								<div id="boxSize">
									<img id="image" src="./../images/test_img3.jpg" alt="">
							    </div>
								<div id="rightBox">
									<div class="modalCont">
										${detail.postContent}
									</div>
	                    	`)
            $.each(detail.commentList, (i, c) => {
            	$(`#rightBox`).append(`
            						<div class="comment">
        								<div class="commentList">
		                			<div class="commentUserImg">
		                    			<img src="./../images/test_user.jpg" alt="">
		                			</div>
		                			<div class="commentWrap">
		                			`)
		                			if (`${c.cmtContent}` != 'null'){
		                			$(`.commentWrap`).append(`${c.cmtContent}
		                			</div>
        						</div>
            				</div>
		                			`)
		                			} else {
		                			$(`.commentWrap`).append(`
		                				<span>등록된 댓글이 없습니다.</span>
	                				</div>
        						</div>
            				</div>
		                			`)
		                			}
            })
            $(`#rightBox`).append(`
			            	<div class="insertComment">
			                    <input type="text" />
			                    <button>등록</button>
			                </div>
            			</div>
            		</div>
        		</div>
            		`)
    		$(`.modal_content_container`).append(`
    				<i class="fas fa-caret-left arw-btn"></i>
    				<i class="fas fa-caret-right arw-btn"></i>
    		`)
            $(`#modal${detail.postNo}`).append(`
	            </div>
	        </div>

	    <div id="modalBoard" class="board">
	        <div class="board-modal">
	            <h4> <button id="report" type="button" onclick="doReport()">신 고<i class="fas fa-angry"></i></button></h4>
	            <h4> <a href="bb.html"><button id="share">퍼가기</button></a></h4>
	            <h4 class="boardClose">취 소 </h4>
	        </div>
	    </div>
			`);
	}
	// x버튼 클릭시 모달창 닫힘
	$(document).on('click', '.modalClose', (e) => {
		let postNum = $(e.target).data("postnum");
		$("#modal" + postNum).css('display', 'none');
	})
	
//	모달창 밖 클릭 시 모달창 종료
//	window.onclick = function (e) {
//		let modal = document.querySelector('#detailFeedModal');
//            if (e.target == modal) {
//            	modal.style.display = "none";
//            }
//        }


// --------- detail modal -----------------
        function makemodalattribute({w, h}) {
        	let maxSize = 550;
        	let boxSize = document.getElementById("boxSize");
        	let rightBox = document.getElementById("rightBox");
            if (w > maxSize && h > maxSize) {
                if (w > h) {
                    boxSize.style.width = maxSize+"px";
                    boxSize.style.height = "auto";
                    image.style.width = "100%";
                } else {
                    boxSize.style.width = "auto";
                    boxSize.style.height = maxSize+"px";
                    image.style.height = "100%";                
                }
            } else if (w > maxSize && h < maxSize) {
                boxSize.style.width = maxSize+"px";
                image.style.width =  "100%";
            } else if (w < maxSize && h > maxSize) {
                box.Size.style.height = maxSize+"px";
                image.style.height = "100%";
            }
            rightBox.style.height = boxSize.style.height;
        
            $(".comment").height(
           		$("#rightBox").height()-$(".modalCont").height()-66
            )
         // makemodalattribute() 끝
		
        // 모달창이 띄워졌을 시 스크롤 방지
        $("#detailFeedModal").on('scroll touchmove mousewheel', function(event) {
            event.preventDefault();
            event.stopPropagation();
            return false;
        });
        
//     // board
//        // Get the modal
        var boardModal = document.getElementById('modalBoard');

        // Get the button that opens the modal
        var btn = document.getElementById("myBoard");

        // Get the <span> element that closes the modal
        var bc = document.getElementsByClassName("boardClose")[0];

        // When the user clicks on the button, open the modal
        $("#myBoard").click(() => {
        	boardModal.style.display = "block";
        }) 

        // When the user clicks on <span> (x), close the modal
        $(".boardClose").click(() => {
            boardModal.style.display = "none";
        })

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
        }
