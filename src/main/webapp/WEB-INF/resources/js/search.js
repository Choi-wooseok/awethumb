//		검색기능
		$("#search").keyup(() => {
			let searchWord = $("#search").val().replace(/ /g, '');
				console.log("srch", searchWord)
			if (searchWord != '') {
				if (searchWord.length == 0) return;
				$.ajax({
					url: pageContextPath + "/mainfeed/search.do",
					method: 'POST',
					data: searchWord,
					dataType: 'JSON',
					contentType: 'application/json; charset=UTF-8',
					success: result => {
							console.log("result", result)
						if (result.length > 0) {
							let str = '';
							if (searchWord.startsWith('#')){
								for (let i = 0; i < result.length; i++) {
									if (result[i].resultType == 'h'){
										str += '<div class="resultSearch" data-searchType="h" data-hashtagContent="' + result[i].hashtagAndNickname + '">' + result[i].hashtagAndNickname
										+ ' 게시물 수 : ' + result[i].hashtagCountAndUserNo + '</div>';									} 
									else {
										str = '';
									}
								}
							} else {
								for (let i = 0; i < result.length; i++) {
									if (result[i].resultType == 'u'){
										str += '<div class="resultSearch" data-searchType="u" data-userNickname="' + result[i].hashtagAndNickname + '">' + result[i].hashtagAndNickname + '</div>';
									} else if (result[i].resultType == 'h') {
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
								if (searchType == 'u'){
									let searchU = $(e.target).data("usernickname");
									location.href = pageContextPath + '/profile/' + searchU;
								} else if (searchType == 'h') {
									let searchH = $(e.target).data("hashtagcontent");
									$("#search").val(searchH);
									$("#srchForm").submit();
//									location.href = pageContextPath + "/mainfeed/mainfeed.do?hashtag=" + searchH
//									MainfeedMakeAjax(searchH)
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