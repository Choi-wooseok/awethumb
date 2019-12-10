/**
 * 
 */
const pModal = document.querySelector(".proj_mod");
const $pmBtn = $(".proj_mod_btn");
// 모달창 클래스 토글 기능
function hidePModal() {
    pModal.classList.toggle("hidden");
}
let projType;
$pmBtn.click((e) => {
    // 프로젝트 이름 설정
    let pName = "";
    projType = $(e.target).data("proj-type");
    switch(projType){
    case 1 :
        pName = "Progress Project"; 
        break;
    case 2 :
        pName = "Shared Project";
        // 공유 유저 선택창 추가
        $(".shared-user-cont").removeClass('myHidden');
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
document.querySelector(".proj_mod_ol").addEventListener("click", ()=>{
	hidePModal();
	// 공유 유저 선택창을 숨긴다
	$(".shared-user-cont").addClass("myHidden");
})

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

// token input
var tokenInput = document.querySelector(".tokenInput"),
    tagify = new Tagify(tokenInput, {
	enforceWhitelist: true,
	whitelist: []
    }),
    controller; // for aborting the call

// listen to any keystrokes which modify tagify's input
tagify.on('input', onTokenInput)
function onTokenInput( e ){
	var tokenValue = e.detail.value;
	tagify.settings.whitelist.length = 0; // reset the whitelist
	
	controller && controller.abort();
	controller = new AbortController();
	
	$.ajax({
		url: "gettokenusers.do",
		data: {userNickname: tokenValue}
	})
	.done((whitelist) => {
		tagify.settings.whitelist = whitelist;
		tagify.dropdown.show.call(tagify, tokenValue);
	})
}

// submit 하기 전에 인풋 태그를 만들어줘서 닉네임을 보내준다
$(".add_proj_form").submit((e) => {
	if(projType === 2){
		e.preventDefault();
			for (let val of JSON.parse(tokenInput.value)){
				$(".add_proj_form").prepend(`<input name="sharedUserNoList" hidden="hidden" value="${val.no}">`)
			}
		e.target.submit();
		$.ajax({
			url: "currentsharedprojectno.do",
			data: {userNo},
			success: e => makeAlarm(4, e)
		})
	}
})
