/**
 * 
 */
const fModal = document.querySelector(".f_mod");
const $fCnt = $(".f_cnt");
const $followingCon = $(".following_container")
let currentPageNo = 0;
const countPerPage = 2;

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
    console.log("in");
    $followingCon.css("z-index", "20");
})
$(".follower_following_btn").click(() => {
    $followingCon.css("z-index", "21");
})

function getFollowingListAjax() {
	// 팔로잉 리스트를 불러오는 ajax
	$.ajax({
		url: "getfollowinglist.do",
		type: "POST",
		contentType: "application/json",
		data: JSON.stringify({
			userNo,
			currentPageNo
		})
	})
	.done((e) => {
		currentPageNo += countPerPage;
		for (let user of e){
			$(".following_ul").append(
				`<li class="following_li">
					<div class="f_mod_img_con">
						<img src="./../images/test_user.jpg" class="f_mod_img"/>
					</div>
					<div class="f_mod_user_con">
						<a class="f_user_nick">${user.userNickname}</a>
					<div class="f_user_name">${user.userName}</div>
					</div>
					<div class="f_mod_btn_con" data-userno="${user.userNo}">
						<button class="sub_btn" type="button">
						구독
						<i class="fas fa-plus"></i>
						</button>
					</div>
				</li>`)
		}
	})
}

// 최초 1회 실행
getFollowingListAjax()




