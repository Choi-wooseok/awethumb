/**
 * 
 */
const fModal = document.querySelector(".f_mod");
const $fCnt = $(".f_cnt");
const $followingCon = $(".following_container")
let currentFollowingPageNo = 0;
let currentFollowerPageNo = 0;
let currentSearchFollowingPageNo = 0;
let currentSearchFollowerPageNo = 0;
let followerListCnt;
let followingListCnt;
let searchFollowerListCnt;
let searchFollowingListCnt;
let searchFollowerName;
let searchFollowingName;
const countPerPage = 6;

// 모달창 클래스 토글 기능
function hideFModal() {
    fModal.classList.toggle("hidden");
}

$fCnt.click((e) => {
    // 누른 이름에 따라 모달창을 띄움
    switch($(e.target).data("type")){
        case "Following":
            $followingCon.css("z-index", "21");
            break;
        case "Followers":
            $followingCon.css("z-index", "20");
            break;
    }
    hideFModal();    
})
        // 모달창 밖에 클릭시 모달창 닫힘
document.querySelector(".f_mod_ol").addEventListener("click", hideFModal)

// 모달창이 띄어졌을 시 스크롤 방지
$(".f_mod").on('scroll touchmove mousewheel', function(event) {
    event.preventDefault();
    event.stopPropagation();
    return false;
});

// 모달창이 띄워진 이후 누르는 것에 따라 z-index 변경
$(".following_follower_btn").click(() => {
    $followingCon.css("z-index", "20");
})
$(".follower_following_btn").click(() => {
    $followingCon.css("z-index", "21");
})

// 팔로잉 리스트를 불러오는 ajax
function getFollowingListAjax() {
	$.ajax({
		url: "getfollowinglist.do",
		type: "POST",
		contentType: "application/json",
		data: JSON.stringify({
			userNo,
			currentPageNo : currentFollowingPageNo
		})
	})
	.done((e) => {
		currentFollowingPageNo += countPerPage;
		followingListCnt = e.length;
		
		for (let user of e){
			const oppUserNo = user.userNo;
			$.ajax({
				url: "checksub.do",
				type: "POST",
				contentType: "application/json",
				data: JSON.stringify({
					subUserNo,
					oppUserNo
				})
			})
			.done((e) => {
				$(".following_ul").append(
					`<li class="following_li">
						<div class="f_mod_img_con">
							<img id="following_user_img_${user.userNo}" class="f_mod_img"/>
						</div>
						<div class="f_mod_user_con">
							<a class="f_user_nick" href="${user.userNickname}">${user.userNickname}</a>
							<div class="f_user_name">${user.userName}</div>
						</div>
						<div class="f_mod_btn_con" id="following_user_${user.userNo}">
						</div>
					</li>`)
				// 팔로잉 이미지를 설정해준다.
				getFollowProfileImgAjax("following", user.userNo);
				// 만얀 리스트에 불러와진 유저 중 방문한 유저가 있을 때 버튼을 스킵한다.
				if (user.userNo === subUserNo) return;
				if(e == 1) {
					$(`#following_user_${user.userNo}`).append(`
						<button class="mod_unsub_btn unsub_btn" type="button" data-userNo="${user.userNo}">
							구독중
							<i class="fas fa-check"></i>
						</button>`)
				} else{
					$(`#following_user_${user.userNo}`).append(`
						<button class="mod_sub_btn sub_btn" type="button" data-userno="${user.userNo}">
							구독
							<i class="fas fa-plus"></i>
						</button>`)
				}
				
			})
		}
	})
}
// 팔로워 리스트를 불러오는 ajax
function getFollowerListAjax() {
	$.ajax({
		url: "getfollowerlist.do",
		type: "POST",
		contentType: "application/json",
		data: JSON.stringify({
			userNo,
			currentPageNo: currentFollowerPageNo
		})
	})
	.done((e) => {
		currentFollowerPageNo += countPerPage;
		followerListCnt = e.length;
		
		for (let user of e){
			const oppUserNo = user.userNo;
			$.ajax({
				url: "checksub.do",
				type: "POST",
				contentType: "application/json",
				data: JSON.stringify({
					subUserNo,
					oppUserNo
				})
			})
			.done((e) => {
				// 팔로워 이미지를 설정해준다.
				const img = getFollowProfileImgAjax("follower", user.userNo);
				$(".follower_ul").append(
						`<li class="follower_li">
						<div class="f_mod_img_con">
						<img id="follower_user_img_${user.userNo}" class="f_mod_img"/>
						</div>
						<div class="f_mod_user_con">
						<a class="f_user_nick" href="${user.userNickname}">${user.userNickname}</a>
						<div class="f_user_name">${user.userName}</div>
						</div>
						<div class="f_mod_btn_con" id="follower_user_${user.userNo}">
						</div>
				</li>`)
				// 팔로잉 이미지를 설정해준다.
				getFollowProfileImgAjax("follower", user.userNo);
				// 만얀 리스트에 불러와진 유저 중 방문한 유저가 있을 때 버튼을 스킵한다.
				if (user.userNo === subUserNo) return;
				if(e == 1) {
					$(`#follower_user_${user.userNo}`).append(`
							<button class="mod_unsub_btn unsub_btn" type="button" data-userNo="${user.userNo}">
							구독중
							<i class="fas fa-check"></i>
					</button>`)
				} else{
					$(`#follower_user_${user.userNo}`).append(`
							<button class="mod_sub_btn sub_btn" type="button" data-userno="${user.userNo}">
							구독
							<i class="fas fa-plus"></i>
					</button>`)
				}
			})
		}
	})
}
// 구독 해지 버튼 이벤트 추가 
function addUnsubEvent() {
	$(document).on("click", ".mod_unsub_btn", (e) => {
		const oppUserNo = $(e.target).data("userno");
		$.ajax({
    		url: "deletesub.do",
    		type: "POST",
			contentType: "application/json",
    		data: JSON.stringify({
    			subUserNo,
    			oppUserNo
    		})
    	})
    	.done(() => {
    		const fBtnEle = `<button class="mod_sub_btn sub_btn" type="button" data-userno="${oppUserNo}">
							구독
							<i class="fas fa-plus"></i>`
			$(`#following_user_${oppUserNo}`).html(fBtnEle)
    		$(`#follower_user_${oppUserNo}`).html(fBtnEle)
    		$(`#srch_following_user_${oppUserNo}`).html(fBtnEle)
    		$(`#srch_follower_user_${oppUserNo}`).html(fBtnEle)
    		
    		// 방문자 유저 번호가 페이지 주인 유저의 번호와 같을 때만
    		if(subUserNo === userNo) $(".following_count_btn").text(--followingCnt);
    	})
	})
}
// 구독 버튼 이벤트 추가
function addSubEvent() {
	$(document).on("click", ".mod_sub_btn",(e) => {
		const oppUserNo = $(e.target).data("userno");
		$.ajax({
    		url: "insertsub.do",
    		type: "POST",
			contentType: "application/json",
    		data: JSON.stringify({
    			subUserNo,
    			oppUserNo
    		})
    	}).done(() => {
    		const fBtnEle = `<button class="mod_unsub_btn unsub_btn" type="button" data-userNo="${oppUserNo}">
							구독중
							<i class="fas fa-check"></i>`
			
			// 방문자 유저 번호가 페이지 주인 유저의 번호와 같을 때만
			if(subUserNo === userNo){
				$(".following_count_btn").text(++followingCnt);
				// 구독을 했는데 해당 유저의 팔로잉 리스트에 없다면 복사해서 넣어준다.
				if ($(`#following_user_${oppUserNo}`).length === 0){
					$(".following_ul").prepend(
							$(`#follower_user_${oppUserNo}`).parent("li").clone()
							.children(`#follower_user_${oppUserNo}`)
							.attr("id", `following_user_${oppUserNo}`)
							.parent("li")
					);
				} 
			}
			$(`#following_user_${oppUserNo}`).html(fBtnEle);
			$(`#srch_following_user_${oppUserNo}`).html(fBtnEle);
			$(`#follower_user_${oppUserNo}`).html(fBtnEle);
			$(`#srch_follower_user_${oppUserNo}`).html(fBtnEle);
			
    	})
	})
}

addUnsubEvent();
addSubEvent();

// 최초 1회 실행
getFollowingListAjax()
getFollowerListAjax()

// 팔로우 인피니트 스크롤
$(".follower_list_con").scroll(function() {
	// follower_ul 이 숨겨져있지 않을때, 즉  검색된 ul로 대체되었을 때
	if ($(".follower_ul").hasClass("myHidden") == false){
		if ($(".follower_list_con").scrollTop() == $(".follower_ul").height() - $(".follower_list_con").height()) {
			if (followerListCnt === countPerPage){
				getFollowerListAjax();
			}
		}
	}
	// 검색된 리스트의 인피니트 스크롤
	else{
		if ($(".follower_list_con").scrollTop() == $(".srch_follower_ul").height() - $(".follower_list_con").height()) {
			if (searchFollowerListCnt === countPerPage){
				getSearchFollowerListAjax();
			}
		}
	}
});
$(".following_list_con").scroll(function() {
	// following_ul 이 숨겨져있지 않을때, 즉  검색된 ul로 대체되었을 때
	if ($(".following_ul").hasClass("myHidden") == false){
		if ($(".following_list_con").scrollTop() == $(".following_ul").height() - $(".following_list_con").height()) {
			if (followingListCnt === countPerPage){
				getFollowingListAjax();
			}
		}
	}
	// 검색된 리스트의 인피니트 스크롤
	else{
		if ($(".following_list_con").scrollTop() == $(".srch_following_ul").height() - $(".following_list_con").height()) {
			if (searchFollowingListCnt === countPerPage){
				getSearchFollowingListAjax();
			}
		}
	}
});


// 팔로워 리스트 검색 이벤트
$(".follower_srch").keyup(() => {
	searchFollowerName = $(".follower_srch").val();
	// 검색 내용을 지워준다
	$(".srch_follower_ul").empty();
	if(searchFollowerName === "") {
		// 빈칸일 때는 기존의 있던 리스트를 다시 보여준다.
		$(".follower_ul").removeClass("myHidden");
		return;
	}
	// 빈칸이 아닐 때는 기존의 있던 리스트를 숨긴다.
	$(".follower_ul").addClass("myHidden");
	
	// 목록을 다시 검색할 시 페이지 번호를 재설정해준다
	currentSearchFollowerPageNo = 0;
	
	getSearchFollowerListAjax();
})

$(".following_srch").keyup(() => {
	searchFollowingName = $(".following_srch").val();
	// 검색 내용을 지워준다
	$(".srch_following_ul").empty();
	
	if(searchFollowingName === "") {
		// 빈칸일 때는 기존의 있던 리스트를 다시 보여준다.
		$(".following_ul").removeClass("myHidden");
		return;
	}
	// 빈칸이 아닐 때는 기존의 있던 리스트를 숨긴다.
	$(".following_ul").addClass("myHidden");
	
	// 목록을 다시 검색할 시 페이지 번호를 재설정해준다
	currentSearchFollowingPageNo = 0;
	
	getSearchFollowingListAjax();
})

// 팔로우 리스트의 프로필 이미지를 가져오는 함수
function getFollowProfileImgAjax(type, userNo){
	$.ajax({
		url: "getprofileimg.do",
		dataType: "text",
		data: {userNo},
		success: function(data) {$(`#${type}_user_img_${userNo}`).attr("src", data)}
	})
}

// 팔로워 리스트 검색 ajax
function getSearchFollowerListAjax(){
	$.ajax({
		url: "getsearchfollowerlist.do",
		type: "POST",
		contentType: "application/json",
		data: JSON.stringify({
			userNo,
			searchName: searchFollowerName,
			currentPageNo: currentSearchFollowerPageNo
		})
	})
	.done((e) => {
		currentSearchFollowerPageNo += countPerPage;
		searchFollowerListCnt = e.length;
		
		for (let user of e){
			const oppUserNo = user.userNo;
			$.ajax({
				url: "checksub.do",
				type: "POST",
				contentType: "application/json",
				data: JSON.stringify({
					subUserNo,
					oppUserNo
				})
			})
			.done((e) => {
				$(".srch_follower_ul").append(
						`<li class="follower_li">
						<div class="f_mod_img_con">
						<img id="srch_follower_user_img_${user.userNo}" class="f_mod_img"/>
						</div>
						<div class="f_mod_user_con">
						<a class="f_user_nick" href="${user.userNickname}">${user.userNickname}</a>
						<div class="f_user_name">${user.userName}</div>
						</div>
						<div class="f_mod_btn_con" id="srch_follower_user_${user.userNo}">
						</div>
				</li>`)
				// 팔로워 이미지를 설정해준다.
				getFollowProfileImgAjax("srch_follower", user.userNo);
				// 만얀 리스트에 불러와진 유저 중 방문한 유저가 있을 때 버튼을 스킵한다.
				if (user.userNo === subUserNo) return;
				if(e == 1) {
					$(`#srch_follower_user_${user.userNo}`).append(`
							<button class="mod_unsub_btn unsub_btn" type="button" data-userNo="${user.userNo}">
							구독중
							<i class="fas fa-check"></i>
					</button>`)
				} else{
					$(`#srch_follower_user_${user.userNo}`).append(`
							<button class="mod_sub_btn sub_btn" type="button" data-userno="${user.userNo}">
							구독
							<i class="fas fa-plus"></i>
					</button>`)
				}
			})
		}
	})
}
// 팔로잉 리스트 검색 ajax
function getSearchFollowingListAjax(){
	$.ajax({
		url: "getsearchfollowinglist.do",
		type: "POST",
		contentType: "application/json",
		data: JSON.stringify({
			userNo,
			searchName: searchFollowingName,
			currentPageNo: currentSearchFollowingPageNo
		})
	})
	.done((e) => {
		currentSearchFollowingPageNo += countPerPage;
		searchFollowingListCnt = e.length;
		
		for (let user of e){
			const oppUserNo = user.userNo;
			$.ajax({
				url: "checksub.do",
				type: "POST",
				contentType: "application/json",
				data: JSON.stringify({
					subUserNo,
					oppUserNo
				})
			})
			.done((e) => {
				$(".srch_following_ul").append(
					`<li class="following_li">
						<div class="f_mod_img_con">
							<img id="srch_following_user_img_${user.userNo}" class="f_mod_img"/>
						</div>
						<div class="f_mod_user_con">
							<a class="f_user_nick" href="${user.userNickname}">${user.userNickname}</a>
							<div class="f_user_name">${user.userName}</div>
						</div>
						<div class="f_mod_btn_con" id="srch_following_user_${user.userNo}">
						</div>
					</li>`)
				getFollowProfileImgAjax("srch_following", user.userNo);
				// 만얀 리스트에 불러와진 유저 중 방문한 유저가 있을 때 버튼을 스킵한다.
				if (user.userNo === subUserNo) return;
				if(e == 1) {
					$(`#srch_following_user_${user.userNo}`).append(`
						<button class="mod_unsub_btn unsub_btn" type="button" data-userNo="${user.userNo}">
							구독중
							<i class="fas fa-check"></i>
						</button>`)
				} else{
					$(`#srch_following_user_${user.userNo}`).append(`
						<button class="mod_sub_btn sub_btn" type="button" data-userno="${user.userNo}">
							구독
							<i class="fas fa-plus"></i>
						</button>`)
				}
			})
		}
	})
}

