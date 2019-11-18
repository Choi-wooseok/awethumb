/**
 * 
 */
const fModal = document.querySelector(".f_mod");
const $fCnt = $(".f_cnt");
const $followingCon = $(".following_container")

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