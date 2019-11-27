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
const $pc = $("#private_check")
const $pcInput = $("input[name=projectPublicEnabled]")
$pc.click(()=>{
	
    if($pc.is(":checked")){
    	$pcInput.val("N")
        return;
    }
    $pcInput.val("Y")
})

// 프로젝트 생성 카테고리 버튼 클릭
$(".proj_cb").click((e) => {
    let target = e.target
    if (e.target.nodeName === "BUTTON") target = target.firstElementChild;
    
    const $input = $("input[name=categoryNo]");
    const categoryNo = $(target).parents("button").data("no")
    
    // 다수의 카테고리를 체크하는 경우
    if($input.val() != categoryNo && $input.val()) {
    	alert("카테고리는 한개만 선택 가능합니다.");
    	return;
    }
    
    $(target).toggleClass("fa-plus").toggleClass("fa-check");
    
    // 이미 선택된 카테고리를 해지하는 경우
    if ($input.val() == categoryNo) {
    	$input.val("");
    	return;
    }
    
    $input.val(categoryNo);
})