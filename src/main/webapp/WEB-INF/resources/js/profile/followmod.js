/**
 * 
 */
const fModal = document.querySelector(".f_mod");
const $fCnt = $(".f_cnt");
const $followingCon = $(".following_container")
let currentFollowingPageNo = 0;
let currentFollowerPageNo = 0;
let followerListCnt;
let followingListCnt;
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
			// 만얀 리스트에 불러와진 유저 중 방문한 유저가 있을 때 스킵한다.
			if (user.userNo === subUserNo) continue;
			
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
							<img src="./../images/test_user.jpg" class="f_mod_img"/>
						</div>
						<div class="f_mod_user_con">
							<a class="f_user_nick">${user.userNickname}</a>
							<div class="f_user_name">${user.userName}</div>
						</div>
						<div class="f_mod_btn_con" id="following_user_${user.userNo}">
						</div>
					</li>`)
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
			// 만얀 리스트에 불러와진 유저 중 방문한 유저가 있을 때 스킵한다.
			if (user.userNo === subUserNo) continue;
			
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
				$(".follower_ul").append(
						`<li class="follower_li">
						<div class="f_mod_img_con">
						<img src="./../images/test_user.jpg" class="f_mod_img"/>
						</div>
						<div class="f_mod_user_con">
						<a class="f_user_nick">${user.userNickname}</a>
						<div class="f_user_name">${user.userName}</div>
						</div>
						<div class="f_mod_btn_con" id="follower_user_${user.userNo}">
						</div>
				</li>`)
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
			$(`#follower_user_${oppUserNo}`).html(fBtnEle);
			
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
    if ($(".follower_list_con").scrollTop() == $(".follower_ul").height() - $(".follower_list_con").height()) {
    	if (followerListCnt === countPerPage){
    		getFollowerListAjax();
    	}
    }
});
$(".following_list_con").scroll(function() {
	if ($(".following_list_con").scrollTop() == $(".following_ul").height() - $(".following_list_con").height()) {
		if (followingListCnt === countPerPage){
			getFollowingListAjax();
		}
	}
});

