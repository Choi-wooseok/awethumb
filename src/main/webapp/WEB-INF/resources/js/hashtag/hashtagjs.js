//		댓글 중 #이 존재할 시 해시태그 링크로 변경
	function renderHashtag(cmtContent){
		if (cmtContent.indexOf('<p>') != -1){
			cmtContent = $(cmtContent).text();
		}
		let newContent = "";
		let ht = cmtContent.split(' ');
		for (let i = 0; i < ht.length; i++) {
			if ((ht[i]).includes('#')){
				ht[i] = `
				<span class="ht" data-ht="${ht[i]}">${ht[i]}</span>
			`}
		}
		for (let j = 0; j < ht.length; j++) {
			newContent += ht[j] + " "
		}
		return newContent
	}
//	해시태그 클릭시 검색화면으로 이동
		$(document).on("click", ".ht", (e) => {
			let hashSrch = $(e.target).data("ht")
			let hashTg = hashSrch.split('#');
			let searchWord = hashTg[1];
//			console.log("htg", searchWord)
			$(".hashInput").val(searchWord);
//			console.log("1", $(".hashInput").val());
			$.ajax({
				url: pageContextPath + "/mainfeed/search.do",
				method: 'post',
				data: JSON.stringify({"searchWord" : searchWord, "resultType" : 'h'}),
				dataType: 'JSON',
				contentType: 'application/json; charset=UTF-8',
				success: result => {
					for (let i = 0; i < result.length; i++){
						if (result[i].hashtagAndNickname == '#'+ hashTg[1]){
							console.log("11", searchWord)
//							MainfeedMakeAjax('#'+hashTg[1])
//							$(".hashForm").submit();
//							$("#detailFeedModal").css("display", "none")
						}
					}
					location.href = pageContextPath + '/mainfeed/mainfeed.do?hashtag=' + searchWord;
//					MainfeedMakeAjax(searchWord)
				}
			})
		});
	// 해시태그 스플릿
	function hashSplitFn(postNoAndCmtNo, cmtAndPostContent, hashType) {
//			let cmtVal= $("#cmtContent").val(); // 댓글 해시태그
//			let cmtVal= $("#postContent").val(); // 게시글 해시태그
//			let pN = $("#postNo").val();
			let hashSplit = new Array();  // 처음 띄어쓰기로 스플릿
			let hashSp = new Array();	  // 띄어쓰기로 자른걸 담아서 #을 포함하는지 검사
			let hash = new Array();		  // #포함된걸 담는 배열
			let hashWord = new Array();  // #포함된걸 담은 배열 중 2번째(#뒤의 단어)를 담음
			let hashT  = {}; 
//			console.log(postNoAndCmtNo);
//			console.log(cmtAndPostContent);
			if (cmtAndPostContent.includes('#')) {
				hashSplit = cmtAndPostContent.split(' ');
				for (let i = 0; i < hashSplit.length; i++) {
					hashSp = hashSplit[i];
					if (hashSp.includes('#')){
						hash = hashSp.split('#');
						hashT = {
								'postNoAndCmtNo' : postNoAndCmtNo, 
								'hashtagContent': '#' + hash[1],
								'hashType' : hashType}
						hashWord.push(hashT);
					}
//					console.log("hashSp", hashSp)
				}
			}
//			console.log("hashT", hashT);
			console.log("hashWord", hashWord);
			
			return hashWord;
	};
	
	// 해시태그 인서트
//	$.ajax({
//		url: pageContextURI + "/mainfeed/insertHashtag.do",
//		method:"POST",
//		contentType: "application/json; charset=UTF-8",
//		data: JSON.stringify({
//			'cmtContent': cmtContent, 
//			'userNo': connectedUserNo, 
//			'postNo': $("#postNo").val(),
//			}),
//		dataType: "JSON",
//		success: result => {
//			$.ajax({
//                  url: pageContextURI + "/mainfeed/detailmainfeed.do",
//                  data: {
//                     postNo:$("#postNo").val()
//                  },
//                  dataType: "JSON",
//                  success: result => {
////		                	 console.log("12", hashWord)
//                     makeDetailFeed(result);
//                     setTimeout(() => {
//                        makemodalattribute({
//                           w: $("#image").width(),
//                           h: $("#image").height()
//                        })
//                     }, 100);
//                  }
//               })
//		}
//	});
//	