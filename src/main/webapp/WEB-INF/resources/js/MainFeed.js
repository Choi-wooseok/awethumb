let pageIndex = 0;
const pageCount = 6;
let scrollTop = 0;

// back to top

	   $(document).ready(function(){ 
//		   console.log("시작")
		    $(window).scroll(function(){ 
//		    	console.log("씨밯")
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
	   function MainfeedMakeAjax(searchWord) {
			$.getJSON({
				url: "mainfeedList.do",
				data: {
					pageIndex,
					searchWord
				},
				success: list => {
					if(list.length == 0) {
						$(window).off('scroll');
						return;
					}
//					console.log("1", searchWord);
					if (searchWord === undefined) {
						
						makeMainFeedList(list);
					}
					else {
						$.getJSON({
							url: "mainfeedList.do?hashtag=" + searchWord,
							data: {
								pageIndex,
								searchWord
							},
							success: list => {
								console.log("ab", list)
								makeMainFeedList(list, searchWord);
								console.log("ac", searchWord)
							}
						});
					}
				}
			})
		}
	   
		function makeMainFeedList(list, resultType) {
			function mainList() {
				$.each(list, (i, c) => {
//					console.log("1", searchWord)
//					console.log("2", c.searchWord)
//					console.log(c.commentList);
					$("#feedsWrap").append(`
						<div class="feedsList msrItem" id="feedsList">
							<div class="feedsInfo">
								<div class="feedUserImg">
									<img src="${pageContextURI}/images/test_user.jpg" alt="">
								</div>
								<div>
									<a href="#">${c.userNickname}</a>
								</div>
							</div>

							<div class="feedsImgWrap">
								<a class="detailFeed" href="javascript:;" > <img src="${pageContextURI}/images/main_bg.jpg" alt="" data-postno="${c.postNo}">
								</a>
							</div>

							<div class="feedsPlay" id="feedsPlay${c.postNo}">
								<div class="feedsContWrap">${c.postContent}</div>
								`)
							$.each(c.hashtagList, (i, h) => {
								if (`${h.hashtagContent}` != 'null') {
									$(`#feedsPlay${c.postNo}`).append(`
									<span class="hashTag">
										<a href="#">#${h.hashtagContent}</a>
									</span>`)
								}
							});
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
//			console.log("3", list);
			if (resultType) {
				$(".feedsList").remove()
//				console.log("0", resultType);
				mainList();
			} else {
				mainList();
			};
//			console.log($(window).scrollTop());
			setTimeout(() => {masonry(); 
//			console.log($(window).scrollTop());
			$(window).scrollTop(scrollTop)}, 100);
		}
		
// --------------- detail -------------
	function detailListAjax() {
	$(document).on('click', '.detailFeed', (e) => {
		$.ajax({
			url: "detailmainfeed.do",
			data: {
				postNo:$(e.target).data("postno"),
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
	});
	}
	
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
							            <a href="#">${detail.userNickname}</a>
							            `)
							   if(typeof userNo !== 'undefined'){
							         $(`.userName`).append(`
								            <button id="myBoard">
								            	<i class="fas fa-bars"></i>
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
									<img id="image" src="./../images/test_img3.jpg" alt="">
							    </div>
								<div id="rightBox">
									<div class="modalCont">
										${detail.postContent}
									</div>
									`)
						$.each(detail.hashtagList, (i, h) => {
							if (`${h.hashtagContent}` !=  'null') {
								$(`.modalCont`).append(`
									<span class="hashtag">
									<a href="#">#${h.hashtagContent}</a>
									</span>
								`)
							}		
						});
							$(`#rightBox`).append(`
									<div class="comment">
	                    	`)
            $.each(detail.commentList, (i, c) => {
            			if (`${c.cmtContent}` != 'null'){
//            				console.log(c.agoRegDt);
            				$(`.comment`).append(`
    									<div class="commentList">
			                				<div class="commentUserImg">
			                    				<img src="./../images/test_user.jpg" alt="">
		                					</div>
		                					<div class="commentWrap" id="commentWrap${c.cmtNo}">
		                						<div class="cmtModal${c.cmtNo}">
		                							<div class="cmtInfo">
			                							<span>${c.cmtUserNickname}</span>
			                							<span>${c.agoRegDt}</span>
			                							`)
			                						if(typeof userNo !== 'undefined'){	
	            										$(`.cmtInfo`).append(`
				                							<button class="commentModal" id="${c.cmtNo}" data-cmtContent="${c.cmtContent}" data-cmtNo="${c.cmtNo}">
				                								<i class="fas fa-ellipsis-h"></i>
				                							</button>
	            										</div>`)
			                						}
            										$(`.cmtModal${c.cmtNo}`).append(`
			                						<div class="cmtContent">
			                							${c.cmtContent}
	            									</div>
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
            if (typeof userNo !== 'undefined'){
	            $(`#rightBox`).append(`
				            	<div class="insertComment">
					            	<form id="crForm" method="post" action="insertComment.do" >
		            					<input type="hidden" id="postNo" value="${detail.postNo}" />	
					                    <input type="text" id="cmtContent"/>
					                    <input type="submit" value="등록" class="cmtRegist" data-postNo="${c.postNo}"/>
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
	}
	detailListAjax();
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
		
// 		모달창이 띄워졌을 시 스크롤 방지
        $("#detailFeedModal").on('scroll touchmove mousewheel', function(event) {
            event.preventDefault();
            event.stopPropagation();
            return false;
        });
// 		댓글부분 모달창 띄우기 / 끄기
        let cmtModalDetail = document.getElementById('cmtModalDetail')
        $(".commentModal").click((e) => {
//        	console.dir($(e.target).parents("button").attr("id"));
        	const cmtNo = $(e.target).parents("button").data("cmtno");
        	let cmtContent = $(e.target).parents("button").data("cmtcontent");
        	cmtModalDetail.style.display = "block";
        	$(".cmtUpdateBtn").data("cmtno", cmtNo);
        	$(".cmtUpdateBtn").data("cmtcontent", cmtContent);
        	$(".cmtDeleteBtn").data("cmtno", cmtNo);
//        	console.log($(".cmtDeleteBtn").data("cmtno"))
//        	console.log($(".cmtUpdateBtn").data("cmtcontent"))
        })
        $(".detailModalClose").click(() => {
        	cmtModalDetail.style.display = "none";
        })
// 		댓글 수정폼
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
// 		수정 폼 취소
        $(document).on("click", ".updateCancel", (e) => {
        	let cmtNo = $(e.target).data("cmtno");
        	$(updateComment).css("display", "none");
        	$(".cmtModal" + cmtNo).css("display", "block")
        })
//      댓글 수정
		$(".commentList").on("click", ".updateSubmit", (e) => {
			$.ajax({
				url: "updateComment.do",
				type: "POST",
				data: {
					postNo : $("#postNo").val(),
					cmtContent: $("#contentUpdate").val(), 
					cmtNo :  $(e.target).data("cmtno")
				},
				dataType: "json",
				success: result => {
					makeDetailFeed(result);
					setTimeout(() => {
						makemodalattribute({
							w: $("#image").width(),
							h: $("#image").height()
						})
					}, 100);
				}
			});
		});	
// 		댓글 삭제폼
        let deleteComment = document.querySelector(".deleteComment");
        $(".cmtModalDetail").on("click", ".cmtDeleteBtn", (e) => {
        	$(".cmtModalDetail").css("display","none");
			let cmtNo = $(e.target).data("cmtno");
        	$(".deleteComment").css("display","block");
//        	$(".cmtModal" + cmtNo).css("display", "none");
        	$("#commentWrap" + cmtNo).append(deleteComment);
    		$(".deleteSubmit").data("cmtno", cmtNo);
        	$(".deleteCancel").data("cmtno", cmtNo);
        })
// 		삭제 폼 취소
        $(document).on("click", ".deleteCancel", (e) => {
        	let cmtNo = $(e.target).data("cmtno");
        	$(deleteComment).css("display", "none");
        	$(".cmtModal" + cmtNo).css("display", "block")
        })
//      댓글 삭제
        $(".commentList").on("click", ".deleteSubmit", (e) => {
//        	console.log($(e.target).data("cmtno"));
			$.ajax({
				url: "deleteComment.do",
				type: "POST",
				data: {
					cmtNo: $(e.target).data("cmtno"),
					postNo: $("#postNo").val()
				},
				dataType:"json",
				success: result => {
					makeDetailFeed(result);
					setTimeout(() => {
						makemodalattribute({
							w: $("#image").width(),
							h: $("#image").height()
						})
					}, 100);
				}
			});
//        	$(".cmtModalDetail").css("display","none");
		});
        
        // Get the modal
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
	
	// 댓글 등록
//		$(document).on('submit', '#crForm', () => {
//			$.ajax({
//				url: "insertComment.do",
//				method:"POST",
//				data: {cmtContent: $("#cmtContent").val(), 
//					userNo: userNo, 
//					postNo:$("#postNo").val()},
//				dataType: "JSON",
//				success: result => {
//					$.ajax({
//		                  url: "detailmainfeed.do",
//		                  data: {
//		                     postNo:$("#postNo").val()
//		                  },
//		                  dataType: "JSON",
//		                  success: result => {
////		                	 makeAlarm(3, cmtNo)
//		                     makeDetailFeed(result);
//		                     setTimeout(() => {
//		                        makemodalattribute({
//		                           w: $("#image").width(),
//		                           h: $("#image").height()
//		                        })
//		                     }, 100);
//		                  }
//		               })
//		        }
//			});
//			$("#cmtContent").val("");
//			return false;
//		});
		
//		해시태그 등록
//		$(document).on('keyup', '#cmtContent', () => {
				$(document).on('submit', '#crForm', () => {
					let hashWord = $("#cmtContent").val();
					let hashSplit = new Array();
					let hash = new Array();
					let saveHash = new Array();
					if (hashWord.includes('#')) {
//				console.log("#", hashWord);
						let hashSplit = hashWord.split(' ');
						for (let i = 0; i < hashSplit.length; i++) {
//					console.log(hashSplit[i]);
							if (hashSplit[i].includes('#')){
								hash.push(hashSplit[i]);
								let hashSp = hash.split(' ');
								for (let j = 0; j < hashSp.length; j++) {
									saveHash.push(hashSp[i]);
								}
							}
						}
//						console.log(hash);
						console.log(saveHash);
//						console.log(hashSplit);
					}
//					console.log(hash);
					$.ajax({
						url: "insertComment.do",
						method:"POST",
						data: {cmtContent: $("#cmtContent").val(), 
							userNo: userNo, 
							postNo:$("#postNo").val(),
							hashtagContent: saveHash},
						dataType: "JSON",
						success: result => {
							$.ajax({
				                  url: "detailmainfeed.do",
				                  data: {
				                     postNo:$("#postNo").val()
				                  },
				                  dataType: "JSON",
				                  success: result => {
//				                	 makeAlarm(3, cmtNo)
				                     makeDetailFeed(result);
				                     setTimeout(() => {
				                        makemodalattribute({
				                           w: $("#image").width(),
				                           h: $("#image").height()
				                        })
				                     }, 100);
				                  }
				               })
				        }
					});
					$("#cmtContent").val("");
					return false;
			});
//		})
//		}
//		검색기능
		$("#search").keyup(() => {
			let searchWord = $("#search").val().replace(/ /g, '');
//				console.log(searchWord)
			if (searchWord != '') {
				let tempSearchWord = searchWord;
				if (tempSearchWord.charAt(0) == '#') {
					tempSearchWord = searchWord.substring(1);
				}
				if (tempSearchWord.length == 0) return;
//					alert(tempsearchWord)
				$.ajax({
					url: "search.do",
					method: 'POST',
					data: tempSearchWord,
					dataType: 'JSON',
					contentType: 'application/json; charset=UTF-8',
					success: result => {
							console.log(result)
//							console.log(result.length)
//							console.log("search", searchWord, searchWord.startsWith('#'));
						if (result.length > 0) {
							let str = '';
							if (searchWord.startsWith('#')){
									console.log("search", searchWord);
								for (let i = 0; i < result.length; i++) {
									console.log(result[i].hashtagAndNickname);
									if (result[i].resultType == 'h'){
										console.log("aa", result[i].resultType);
										str += '<div class="resultSearch" data-searchType="h" data-hashtagContent="' + result[i].hashtagAndNickname + '">' + '#' + result[i].hashtagAndNickname
										+ ' 게시물 수 : ' + result[i].hashtagCountAndUserNo + '</div>';									} 
									else {
										str = '';
									}
								}
							} else {
								for (let i = 0; i < result.length; i++) {
									if (result[i].resultType == 'u'){
										str += '<div class="resultSearch" data-searchType="u" data-userNickname="' + result[i].hashtagAndNickname + '">' + result[i].hashtagAndNickname + '</div>';
										console.log("ab", result[i].resultType)
									} else if (result[i].resultType == 'h') {
										console.log("ac", result[i].resultType)
										str += '<div class="resultSearch" data-searchType="h" data-hashtagContent="' + result[i].hashtagAndNickname + '">' + '#' + result[i].hashtagAndNickname
												+ ' 게시물 수 : ' + result[i].hashtagCountAndUserNo + '</div>';
									}
								}
							}
							$("#searchResults").css("display", "block")
							$("#searchResults").html(str);
							window.onclick = () => {
								$("#searchResults").css("display", "none")
					        }
							$(".resultSearch").click((e) => {
								let searchType = $(e.target).data("searchtype")
								console.log("b", searchType);
								if (searchType == 'u'){
									let searchU = $(e.target).data("usernickname");
									location.href = pageContextURI + '/profile/' + searchU;
								} else if (searchType == 'h') {
									let searchH = $(e.target).data("hashtagcontent");
									console.log("a", searchH);
//									location.href = pageContextURI + '/mainfeed/mainfeed.do?hashtagContent=' + searchH;
									MainfeedMakeAjax(searchH)
								}
							});
						} else {
							$("#searchResults").html("");
							$("#searchResults").css("display", "none")
						}
					}
				});
			} else {
				$("#searchResults").html("");
				$("#searchResults").css("display", "none")
			}
		});
		

		
		
