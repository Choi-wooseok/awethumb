let pageIndex = 0;
const pageCount = 6;
let scrollTop = 0;

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
		    MainfeedMakeAjax(hashtag);
		});
	   
// infinity scroll
	
	   $(window).on('scroll', function() {
	  	 
	  	 scrollTop = $(window).scrollTop();
	  
	  	 if ($(window).height() == $(document).height() - Math.ceil($(window).scrollTop())) {
	  		 pageIndex += pageCount;
	  		 MainfeedMakeAjax(hashtag);
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
	   function MainfeedMakeAjax(searchWord) {
		   if (searchWord) searchWord = "#" + searchWord;
//		   console.log("2", searchWord)
			$.getJSON({
				url: pageContextPath + "/mainfeed/mainfeedList.do",
				data: {
					pageIndex,
					searchWord
				},
				success: list => {
//					console.log("3", searchWord)
//					console.log("4", list)
					if(list.length == 0) {
						$(window).off('scroll');
						return;
					}
					if(searchWord) {
						makeMainFeedList(list, searchWord);
					} else {
						makeMainFeedList(list);
					}
				}
			})
		}
	   
		function makeMainFeedList(list, searchWord) {
//			console.log("lt", list);
//			console.log("reT", searchWord);
			function mainList() {
				if (searchWord) {
					$("#searchWord").css("display", "block");
					$("#searchWord").append(`
						검색어  <span>${searchWord}</span>
					`)
					mainContent()
				} else {
					mainContent()
				}
				function mainContent (){
				$.each(list, (i, c) => {
//					console.log("boardFilePath : " , c.boardFile);
					let like = '';
					if (typeof(connectedUserNo) !== 'undefined') {
						like = likeAdmin(c.postNo, connectedUserNo, 1);
					}
					const newContent = renderHashtag(`${c.postContent}`);
					let userImgData = userImg(c.userNo);
					$("#feedsWrap").append(`
						<div class="feedsList msrItem" id="feedsList">
							<div class="feedsInfo">
								<div class="userImg${c.userNo}">
									<img src="${userImgData}" alt="">
								</div>
								<div>
									<a href="${pageContextPath}/profile/${c.userNickname}">${c.userNickname}</a>
								</div>
							</div>

							<div id="feedImgWrap"class="feedImgWrap${c.postNo}">
								<img id="feedImg" class="feedImg ${c.postNo}" src="${pageContextPath}/image/${c.boardFile}"alt="" data-postNo="${c.postNo}"/>
							</div>

							<div class="feedsPlay" id="feedsPlay${c.postNo}">
								<div class="feedsContWrap">${newContent}</div>
								`)
							$.each(c.hashtagList, (i, h) => {
								if (`${h.hashtagContent}` != 'null') {
									$(`#feedsPlay${c.postNo}`).append(`
									<span class="hashTag">
										<a href="javascript:;">${h.hashtagContent}</a>
									</span>`)
								}
							});
								$(`#feedsPlay${c.postNo}`).append(`
								<div class="playInfo">
									댓글 <span>${c.commentCount}</span>개
									${like}
								</div>
							</div>
						</div>
					`);
					});
				}
				}
			if (searchWord) {
				$(".feedsList").remove()
				mainList();
			} else {
				mainList();
			};
			setTimeout(() => {masonry(); 
			$(window).scrollTop(scrollTop)}, 300);
		}
		

		// userImg
		function userImg(userNo){
			$.get({
				url:pageContextPath + "/api/user/"+ userNo + "/thumb",
				success: (src) => {
					$(".userImg" + userNo + " > img").attr("src", src);
				}
			})
		} 
		
// --------------- detail -------------
		
	function detailFeed(postNo) {
		$.ajax({
			url: pageContextPath + "/mainfeed/detailmainfeed.do",
			data: {
				postNo: postNo,
			},
			dataType: "JSON",
			success: result => {
				makeDetailFeed(result);
				$('.comment').height(
						$(".modalContWrap").height()-($(".modalCont").height() + 69)
				)
				setTimeout(function() {
				      let images = $(".images");
				      $.each(images, function(i, e) {
				         let maxSize = $(".slick-slide").width();
				         imgReSize($(this), maxSize);
				      })
					$(".comment").scrollTop($(".comment").prop('scrollHeight'));
				}, 100);
			}
		})
	}
		
	function detailListAjax() {
	$(document).on('click', '.feedImg', (e) => {
		$("#detailFeedModal").css("display", "block")
//		console.log("post?" , $(e.target).data("postno"));
		detailFeed($(e.target).data("postno"));
	});
	}
	
	function makeDetailFeed(detail) {
		let postContent = renderHashtag(`${detail.postContent}`);
//		console.log("dL", detail);
		let imgProfileURL = ""; 
		$.get( {
				url:pageContextPath + "/api/user/"+ detail.userNo + "/thumb",
				async:false,
				success: (src) => {
					imgProfileURL = src;
				}
		});
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
										<img src="${imgProfileURL}" alt="">
									</div>
							        <div class="userName">
							            <a href="${pageContextPath}/profile/${detail.userNickname}">${detail.userNickname}</a>
							            `)
							   if(typeof connectedUserNo !== 'undefined'){
							         $(`.userName`).append(`
								            <button id="myBoard" >
								            	<i class="fas fa-bars" data-postUserNo="${detail.userNo}"></i>
								            </button>
							        </div>
								</div>
							</div>`)
							   } else {
								   $(`.userName`).append(`
										   </div>
									   </div>
								   </div>
								   `)
							   }
						$(`.modal_content`).append(`
							<div class="modalContWrap">
							<div id="boxSize">
								`)
						for (let i = 0; i < detail.boardfileList.length; i++) {
							$(`#boxSize`).append(`
									<img class="images" src="${detail.boardfileList[i]}" alt="" />
									`)
							if (i == detail.boardfileList.length-1) {
								$("#boxSize").slick();
							}
						}
						if (detail.boardfileList.length > 1) {
							$(`.slick-slide`).append(`
								<span class="moreImg">
									<i class="far fa-clone"></i>
								</span>
							`)
						}
							$(`.modalContWrap`).append(`
							</div>
								<div id="rightBox">
									<div class="modalCont">
										${postContent}
									</div>
									`)
							$(`#rightBox`).append(`
									<div class="comment">
	                    	`)
            $.each(detail.commentList, (i, c) => {
            	let imgProfileURL = ""; 
        		$.get( {
        				url:pageContextPath + "/api/user/"+ c.userNo + "/thumb",
        				async:false,
        				success: (src) => {
        					imgProfileURL = src;
        				}
        		});
            	// 해시태그 js를 사용하기 위해 커멘트 받아옴
            	const newContent = renderHashtag(c.cmtContent);
            			if (`${c.cmtContent}` != 'null'){
            				let like = '';
        					if (typeof(connectedUserNo) !== 'undefined') {
        						like = likeAdmin(c.cmtNo, connectedUserNo, 2);
        					}
            				$(`.comment`).append(`
    									<div class="commentList">
			                				<div class="commentUserImg">
			                    				<img src="${imgProfileURL}" alt="">
		                					</div>
		                					<div class="commentWrap" id="commentWrap${c.cmtNo}">
		                						<div class="cmtModal${c.cmtNo}">
		                							<div class="cmtInfo">
			                							<span><a href="${pageContextPath}/profile/${c.cmtUserNickname}">${c.cmtUserNickname}</a></span>
			                							<span>${c.agoRegDt}</span>
			                							${like}
			                							`)
			                						if(typeof connectedUserNo !== 'undefined'){	
	            										$(`#commentWrap${c.cmtNo}`).append(`
				                							<button class="commentModal" id="${c.cmtNo}" data-cmtContent="${c.cmtContent}" data-userNo="${c.userNo}" data-cmtNo="${c.cmtNo}">
				                								<i class="fas fa-ellipsis-h"></i>
				                							</button>
	            										</div>`)
			                						}
            										$(`.cmtModal${c.cmtNo}`).append(`
            										<form method="post" action="${pageContextPath}/mainfeed/mainfeed.do">
            											<input class="cmtHash" name="cmtHash" type="hidden" value="" />
            											<div class="cmtContent">${newContent} </div>
			                						</form>
					                			</div>
			        						</div>
			            				</div>
		            				</div>`)
		                } else {
		                	$(`.comment`).append(`
		                				<span>등록된 댓글이 없습니다.</span>
                				</div>  // rightbox 끝
    						</div>  // modalcontwrap
        				</div> // modalcontent
        			</div> // modal_content_container
		                			`)
		                		}
            })
            if (typeof connectedUserNo !== 'undefined'){
	            $(`#rightBox`).append(`
					            <div class="insertComment">
					            	<form id="crForm" method="post" action="insertComment.do" >
				         				<input type="hidden" id="postNo" value="${detail.postNo}"/>	
					                    <textarea id="cmtContent"></textarea>
					                    <input type="submit" value="등록" class="cmtRegist"/>
				    				</form>
				                </div>	
	            			</div>
	            		</div>
	        		</div>
	            `)
            } else {
            	$(`#rightBox`).append(`
	            			</div>
	        			</div>
	    			</div>
            	`)
            }
    		$(`.modal_content_container`).append(`
    				<i class="fas fa-caret-left arw-btn"></i>
    				<i class="fas fa-caret-right arw-btn"></i>
    		`)
            $(`#modal${detail.postNo}`).append(`
	            </div>
	        </div>
	    `)
	    
	    
	    
//	         게시글 수정/삭제 클릭시 detailProject로 이동
    	$("#updateBtn").click(() => {
    		location.href = pageContextPath + "/detailProject/updateListForm.do?projectNo=" + `${detail.projectNo}`
    	})

//		비로그인시 댓글등록창 숨김
	    if (typeof connectedUserNo === 'undefined'){
	    	$(".insertComment").css("display", "none");
	    }
	    
//	          로그인시 해시태그.js파일 호출
        if (typeof connectedUserNo !== 'undefined'){
        	$("textarea").hashtags();
        }
	}
	detailListAjax();
	
	// x버튼 클릭시 모달창 닫힘
	$(document).on('click', '.modalClose', (e) => {
		let postNum = $(e.target).data("postnum");
		$("#modal" + postNum).css('display', 'none');
	})
	
// --------- detail modal -----------------
//            로그인시
            if (typeof connectedUserNo !== 'undefined'){
            	$(".comment").height( 
            			$("#rightBox").height()-$(".modalCont").height()-118)
            } else {
            	$(".comment").height(
            			$("#rightBox").height()-$(".modalCont").height()-68)
            	
            }
         // makemodalattribute() 끝
// 		모달창이 띄워졌을 시 스크롤 방지
        $('.form-control').focus(function() {  
            $('#detailFeedModal').on('scroll touchmove mousewheel', function(e){
                e.preventDefault();
                e.stopPropagation();
	        })
		});	
            
//      모달창에서 댓글부분 클릭시 스크롤 활성화
        $('.form-control').focusout(function() {
            $('#detailFeedModal').unbind();
        });
            
// 		댓글부분 모달창 띄우기 / 끄기
        let cmtModalDetail = document.getElementById('cmtModalDetail');
        let unLoginCmtModal = document.getElementById('unLoginCmtModal');
        $(document).on('click', '.commentModal', (e) => {
        	let cmtNo = $(e.target).parents("button").data("cmtno");
        	let cmtContent = $(e.target).parents("button").data("cmtcontent");
        	
        	if (connectedUserNo == $(e.target).parents("button").data("userno")){
        		$(cmtModalDetail).css("display", "block");
        	} else {
        		$(unLoginCmtModal).css("display", "block");
        	}
        	$(".cmtUpdateBtn").data("cmtno", cmtNo);
        	$(".cmtUpdateBtn").data("cmtcontent", cmtContent);
        	$(".cmtDeleteBtn").data("cmtno", cmtNo);
        })
        $(".detailModalClose").click(() => {
        	cmtModalDetail.style.display = "none";
        	unLoginCmtModal.style.display = "none";
        })
        
        // Get the modal
        var boardModal = document.getElementById('modalBoard');
        var LoginModalBoard = document.getElementById('LoginModalBoard');

        // Get the button that opens the modal
        var btn = document.getElementById("myBoard");

        // Get the <span> element that closes the modal
        var bc = document.getElementsByClassName("boardClose")[0];

        // When the user clicks on the button, open the modal
        $(document).on('click', "#myBoard", (e) => {
        	if (connectedUserNo == $(e.target).data("postuserno")){
        		$(LoginModalBoard).css("display", "block");
        	} else {
        		boardModal.style.display = "block";
        	}
        })

        // When the user clicks on <span> (x), close the modal
        $(".boardClose").click(() => {
            boardModal.style.display = "none";
        })
        
        $(".LoginBoardClose").click(() => {
        	$(LoginModalBoard).css("display", "none");
        })

        // When the user clicks anywhere outside of the modal, close it
        window.onclick = function (event) {
            if (event.target == boardModal) {
                boardModal.style.display = "none";
            } else if (event.target == LoginModalBoard)
            	LoginModalBoard.style.display = "none";
        }
        
        // 신고 모달창
	        function doReport() {
	            boardModal.style.display = "none";
	        }
	        
	     // 신고
	        $(document).on("click", ".report", (e) => {
	           let postNo = $("#postNo").val();
	           let cmtNo = $(e.target).data("cmtno");
	           if (cmtNo == null) { // 게시글 신고
	              let newWindow = window.open("about:blank");
	              newWindow.location.href = `${pageContextPath}/report/insertReportForm.do?postNo=${postNo}`;
	           }
	           else { // 댓글 신고
	              let newWindow = window.open("about:blank");
	              newWindow.location.href = `${pageContextPath}/report/insertReportForm.do?postNo=${postNo}&commentNo=${cmtNo}`;
	           }
	        });
	        
//	 		댓글 수정폼
	        let updateComment = document.querySelector(".updateComment");
	        $(".cmtModalDetail").on("click", ".cmtUpdateBtn", (e) => {
	        	$(".cmtModalDetail").css("display","none");
				let cmtNo = $(e.target).data("cmtno");
				let cmtContent = $(e.target).data("cmtcontent");
				$(".updateComment").css("display","block");
	        	$(".cmtModal" + cmtNo).css("display", "none");
	        	$("#commentWrap" + cmtNo).append(updateComment);
	        	$('#contentUpdate').val(cmtContent);
	    		$(".updateSubmit").data("cmtno", cmtNo);
	        	$(".updateCancel").data("cmtno", cmtNo);
	        })
//	 		수정 폼 취소
	        $(document).on("click", ".updateCancel", (e) => {
	        	let cmtNo = $(e.target).data("cmtno");
	        	$(updateComment).css("display", "none");
	        	$(".cmtModal" + cmtNo).css("display", "block")
	        })
	        // 		댓글 삭제폼
	        let deleteComment = document.querySelector(".deleteComment");
	        $(".cmtModalDetail").on("click", ".cmtDeleteBtn", (e) => {
	        	$(".cmtModalDetail").css("display","none");
				let cmtNo = $(e.target).data("cmtno");
//				console.log("cmtNo", cmtNo)
	        	$(".deleteComment").css("display","block");
	        	$("#commentWrap" + cmtNo).append(deleteComment);
	    		$(".deleteSubmit").data("cmtno", cmtNo);
	        	$(".deleteCancel").data("cmtno", cmtNo);
	        })
//	 		삭제 폼 취소
	        $(document).on("click", ".deleteCancel", (e) => {
	        	let cmtNo = $(e.target).data("cmtno");
	        	$(deleteComment).css("display", "none");
	        	$(".cmtModal" + cmtNo).css("display", "block")
	        })
        
    
    // 댓글 등록
//   	----------------------
   		$(document).on('submit', '#crForm', () => {
   			let cmtContent = $("#cmtContent").val();
//   			console.log(cmtContent);
   			let postNo = $("#postNo").val();
   			
   			$.ajax({
   				url: pageContextPath + "/mainfeed/insertComment.do",
   				method:"POST",
   				contentType: "application/json; charset=UTF-8",
   				data: JSON.stringify({
   					'cmtContent': cmtContent, 
   					'userNo': connectedUserNo, 
   					'postNo': postNo,
   					}),
   				dataType: "JSON",
   			}).done(result => {
//   				console.log("result", result);
   				$.ajax({
   					url: pageContextPath + "/mainfeed/insertHashtag.do",
   					method:"POST",
   					contentType: "application/json; charset=UTF-8",
   					data: JSON.stringify(hashSplitFn(result, cmtContent, 2)),
   					dataType: "JSON",
   					tranditional: true,
   				})
   			}).done((result) => {
//   				console.log("postNo", postNo)
   				detailFeed(postNo);
   			})
   			$("#cmtContent").val("");
   			$(".hashtag").remove();
   			
   			return false;
   		});
//      댓글 수정
		$(document).on("click", ".updateSubmit", (e) => {
			let cmtNo = $(e.target).data("cmtno");
			let postNo = $("#postNo").val();
			let cmtContent = $("#contentUpdate").val();
			$.ajax({
				url: pageContextPath + "/mainfeed/updateComment.do",
				type: "POST",
				data: {
					'postNo' : postNo,
					'cmtContent' : cmtContent, 
					'cmtNo' : cmtNo
				},
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
//				console.log('3', result);
//				console.log('4', cmtContent);
				$.ajax({
					url: pageContextPath + "/mainfeed/insertHashtag.do",
					method:"POST",
					contentType: "application/json; charset=UTF-8",
					data: JSON.stringify(hashSplitFn(cmtNo, cmtContent, 2)),
					dataType: "JSON",
					tranditional: true,
				})
			}).done(() => {
				$(".cmtModal" + cmtNo).css("display", "block")
				detailFeed(postNo);
			})
		});	

//      댓글 삭제
        $(document).on("click", ".deleteSubmit", (e) => {
        	let cmtNo = $(e.target).data("cmtno");
        	let postNo = $("#postNo").val();
//        	console.log("1", cmtNo);
//        	console.log("2", postNo);
			$.ajax({
				url: pageContextPath + "/mainfeed/deleteComment.do",
				type: "POST",
				contentType: "application/json; charset=UTF-8",
				data: JSON.stringify({
					'cmtNo': cmtNo,
				}),
				dataType:"json",
			}).done(result => {
//				console.log('5', result)
//	        	console.log("cmtNo", cmtNo)
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
//				console.log("8", result)
//				console.log("postNo", postNo)
				detailFeed(postNo);
			})
		});

$(document).on("click", ".likeBtn",(e) => {
	if (typeof(connectedUserNo) !== 'undefined') {
		likeClick(connectedUserNo, e.target);
	}
 }) // 좋아요버튼클릭이벤트
