/**
 * 
 */
const pModal = document.querySelector(".proj_mod");
const $pmBtn = $(".proj_mod_btn");
// 모달창 클래스 토글 기능
function hidePModal() {
    pModal.classList.toggle("hidden");
}

$pmBtn.click((e) => {
    // 프로젝트 이름 설정
    let pName = "";
    const projType = $(e.target).data("proj-type");
    switch(projType){
    case 1 :
        pName = "Progress Project"; 
        break;
    case 2 : 
        pName = "Shared Project";
        break;
    case 3 : 
        pName = "Saved Project";
        break;
    }
    // 모달창에 프로젝트 이름 추가
    $(".proj_name").text(pName); 
    // 프로젝트 타입 번호를 삽입
    $("input[name=projectType]").attr("value", projType);
    hidePModal();
})
// 모달창 밖에 클릭시 모달창 닫힘
document.querySelector(".proj_mod_ol").addEventListener("click", hidePModal)

// 모달창이 띄어졌을 시 스크롤 방지
$(".proj_mod").on('scroll touchmove mousewheel', function(event) {
    event.preventDefault();
    event.stopPropagation();
    return false;
});
// 프로젝트 비밀 여부
const $privateCb = $("input[name=projectPublicEnabled]")
$privateCb.click(()=>{
    if($privateCb.is(":checked")){
        $privateCb.val("N")
        return;
    }
    $privateCb.val("Y")
})