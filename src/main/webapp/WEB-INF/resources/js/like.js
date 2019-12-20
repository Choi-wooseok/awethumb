function likeAdmin(no, user, type) {
	let postAndCmtNo = no;// 댓글이나 글번호
	let userNo = user; // 로그인유저
	let state = type; // 1 - > 게시글, 2-> 댓글
	let ck = 0; // 0 -> 좋아요안누름 , 1-> 좋아요 누름
	let likecount; // 좋아요수 state가 1일때만 값이존재
	let like = ``; // return할녀석
	$.ajax({ // async , await
//		url: "likeCheck.do",
		url: pageContextPath + "/api/like",
		async:false,
		data: {
			postAndCmtNo: postAndCmtNo,
			userNo : userNo, 
			codeValue : state 
		},
		dataType:"json",
		success: (no) =>  {
			ck = no;
		}
	});
	let likestate = ``;
	if(ck === 1){ // 좋아요누른상태
		likestate = `data-likestate="2"
					 class="fas fa-heart likeBtn"`
	}
	else { // 좋아요안누름
		likestate = `data-likestate="1"
					 class="far fa-heart likeBtn"`
	}
	let dataType= "data-postno";
	if (state !== 1) {
		dataType="data-commentno";
	} 
	let likebtn = `<i
					id="like${postAndCmtNo}"
					${dataType}="${postAndCmtNo}"
					data-type="${state}"
					${likestate}>
				   </i>`;
	if (state === 1) {
		like = `<div>
					<button
						type="button"
						style="border:none; background-color:transparent;">
						${likebtn}
					</button>
					<div id="countLike${postAndCmtNo}" style="display: inline;">
						<span id="countSpan${postAndCmtNo}"></span>
					</div>
				</div>`;
	} else {
		like = `<span
					style="background-color:transparent; padding-right:40px;cursor:pointer">
					${likebtn}
				</span>`;
	}
	return like;
};
function likeCount(postNo) {
	let count = 0;
	$.ajax({ 
//		url: "boardLikeCount.do",
		url: pageContextPath + "/api/like/count",
		async:false,
		data: {
			postAndCmtNo: postNo,
		},
		dataType:"json",
		success: (no) =>  {
			count = no;
		}
	});
	return count;
};
function likeClick(user, e) {
	 let userNo = user; // 로그인유저 43
	 let btnState = $(e).data("likestate"); // 1-> 빈하트 2-> 꽉찬하트
	 let type = $(e).data("type"); // -> 1 게시글 2-> 댓글 
	 let pnum = $(e).data("postno"); // 게시글번호
	 let cnum = $(e).data("commentno"); // 댓글번호
	 
	 if(btnState == 1){ // 빈하트인상태
		 if(pnum) { // 게시글 좋아요 일때
			 makeAlarm(2, pnum); // 알람
			 $(e).data("likestate", 2).removeClass("far").addClass("fas"); // 태그자체바꿈
			 let count = parseInt($("#countSpan" + pnum).html()); // count - 1 \
			 if(!count) { // 좋아요를 아무도 안눌렀을시
				 $("#countSpan" + pnum).remove();
				 $("#countLike" + pnum).append(`<span id="countSpan${pnum}">1회 좋아요</span>`);
			 }
			 else { // 좋아요가 눌러져있을때
				 count += 1;
				 $("#countSpan" + pnum).remove();
				 $("#countLike" + pnum).append(`<span id="countSpan${pnum}">${count}회 좋아요</span>`);
			 }
			 $.ajax({
				 url: pageContextPath + "/api/like",
				 type:"POST",
				 data: {
					 postAndCmtNo: pnum,
					 userNo : userNo,
					 codeValue : type
				 },
				 success: () =>  {},
				 error : (asd) => {
					 console.log(asd);
				 }
			 });
			 return;
		 } //if
		 else {// 댓글좋아요
			 console.log("1 : " + cnum);
			 console.log("2 : " + userNo);
			 console.log("3 : " + type);
			 $(e).data("likestate", 2).removeClass("far").addClass("fas");
			 $.ajax({
				 url: pageContextPath + "/api/like",
				 type:"POST",
				 data: {
					 postAndCmtNo: cnum,
					 userNo : userNo,
					 codeValue : type
				 },
				 success: () =>  {},
				 error : (asd) => {
					 console.log(asd);
				 }
			 });
		 }
	 } // if
	 else { // 꽉찬 하트일때 
		 if(pnum) { // 게시글일때
			 $(e).data("likestate", 1).removeClass("fas").addClass("far"); // 태그자체바꿈
			 let count = parseInt($("#countSpan" + pnum).html()) - 1; // count - 1 
			 if (count == 0){ // 좋아요가 0이될때 
				 $("#countSpan" + pnum).remove();
			 } else { 
				 $("#countSpan" + pnum).html(`${count}회 좋아요`);
			 }
			 $.ajax({
				 url: pageContextPath + `/api/like?postAndCmtNo=${pnum}&userNo=${userNo}&codeValue=${type}`,
				 type: "Delete",
				 success: () => {},
				 error : (asd) => {console.log(asd);}
			 });
			 return;
		 }// if
		 else {// 댓글일때
			 $(e).data("likestate", 1).removeClass("fas").addClass("far");
			 $.ajax({
				 url: pageContextPath + `/api/like?postAndCmtNo=${cnum}&userNo=${userNo}&codeValue=${type}`,
				 type: "delete",
		
				 success: () =>  {},
				 error : (asd) => {
					 console.log(asd);
				 }
			 });
		 } // else
	 } // else
}



















