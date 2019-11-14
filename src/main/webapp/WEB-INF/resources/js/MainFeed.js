/**
 * 
 */
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
		});
	   
 // infinity scroll
//	   let page = 1;
//		function MainfeedMakeAjax() {
//			let xhr = new XMLHttpRequest();
//			
//			xhr.onreadystatechange = () => {
//				
//				if (xhr.readyState === 4) {
//					
//					if (xhr.status === 200) {
//						let teamList = JSON.parse(xhr.responseText);
//						if (teamList.length == 0) {
//							page--;
//						} else {
//							makeMainFeedList(teamList)
//						}
//					}
//				}
//			};
//			xhr.open("GET", "teamBoardList_ajax.do?projectNo=${projectNo}&teamNo=${teamNo}&pageNo=" + page, true);
//			xhr.send();
//		}
//		feedListAjax();
//		
//		function makeMainFeedList(list) {
//			let teamList = document.getElementById("boardList_cws");
//			let html = "<div>";
//			for (let i = 0; i < list.length; i++) {
//				let tList = list[i];
//				let a = document.createElement("span");
//				a.innerHTML = "";
//				
//		 		if ('${sessionScope.user.userNo}' == tList.userNo || '${sessionScope.user.userGrade}' == 3){
//		 			a.innerHTML = 
//		 			`<form method="post" action="${pageContext.request.contextPath}/team/teamBoardDelete.do" />
//		 			<button onclick="return confirmDel();" style="margin-top:-100px; cursor: pointer; float: right; background: none; border: none">
//		 			<i class="fa fa-trash-o fa-2x" aria-hidden="true"></i>
//		 			</button>
//		 			<input type="hidden" name="teamBoardNo" value="\${tList.teamBoardNo}"/>
//		 			<input type="hidden" name="projectNo" value="\${tList.projectNo}"/>
//		 			<input type="hidden" name="teamNo" value="\${tList.teamNo}"/>
//		 			</form>`
//			 	};
//			html += `<ul class="board_cws">
//					<li style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis; height: 35px; padding-top: 10px;">
//					<a href="${pageContext.request.contextPath}/team/teamBoardDetail.do?teamBoardNo=\${tList.teamBoardNo}" style="font-size: 35px; ">
//					\${tList.teamBoardTitle}</a></li>
//			 		<br>
//			 		<li><h6 style="margin: 0 auto">작성자: \${tList.userId}</h6></li>
//			 		<li><h6 style="margin: 0 auto">작성일: \${tList.teamBoardRegDt}</h6></li>
//			 		\${a.innerHTML}
//			 		</ul>
//			 		<br><br><br>
//			 		
//				`;
//			}
//			html += "</div>";
//			teamList.innerHTML += html;
//			
//		}
//		
//	   $(window).on('scroll', function() {
//			let scrollHeight = $(document).height();
//			let scrollPosition = $(window).height() + $(window).scrollTop();		
//
//			$("#scrollHeight").text(scrollHeight);
//			$("#scrollPosition").text(scrollPosition);
//			$("#bottom").text(scrollHeight - scrollPosition);
//
//			if (scrollPosition > scrollHeight - 300) {			
//				page++;
//				teamListAjax();
//			}  
//		});