/**
 * 
 */
const modal = document.querySelector(".u_mod");
const mBtn = document.querySelector(".u_modal_btn");
// 모달창 클래스 토글 기능
function hideModal() {
    $pw.html("").data("pw", "off");
    modal.classList.toggle("hidden");
}
// 취소 버튼 클릭시 모달창 닫힘
mBtn.addEventListener("click", hideModal)
// 모달창 밖에 클릭시 모달창 닫힘
$(".u_mod_ol").click(hideModal)

// 모달창이 띄어졌을 시 스크롤 방지
$(".u_mod").on('scroll touchmove mousewheel', function(event) {
    event.preventDefault();
    event.stopPropagation();
    return false;
});
// 비밀번호 변경창
const $pw = $(".pw")
$(".change_pass").click(() => {
    if($pw.data("pw") === "on"){
        $pw.html("").data("pw", "off");
        return;
    }
    $pw.html(
            `<div>
                <aside>이전 비밀번호</aside>
                <div>
                    <input class="si currPass" type="password">
                </div>
            </div>
            <div>
                <aside>변경할 비밀번호</aside>
                <div>
                    <input class="si" name="userPass" type="password">
                </div>
            </div>
            <div>
                <aside>비밀번호 확인</aside>
                <div>
                    <input class="si passCheck" type="password">
                </div>
            </div>`
    ).data("pw", "on");
    
})

// 카테고리 아이콘 변경
$(".user_cb").click((e) => {
    let target = e.target
    if (e.target.nodeName === "BUTTON") target = target.firstElementChild;
    $(target).toggleClass("fa-plus").toggleClass("fa-check");
})

// 유저의 기존 카테고리를 찾아와서 체크해준다
let cArr = new Array();
if (cList.length > 1) cArr = cList.split(":");
else cArr.push(cList);
const $cb = $(".user_cb")
for (let cb of $cb){
	for (let cNo of cArr){
		if($(cb).data("no") == cNo) $(cb).children("i").removeClass("fa-plus").addClass("fa-check");
	}    		
}

// 제출시
$(".profile_sub").click((e) => {
	let sCList = "";
	for (let cb of $cb){
		if ($(cb).children("i").hasClass("fa-check")) sCList += $(cb).data("no") + ":";
	}
	$("input[name=categoryList]").val(sCList.substring(0, sCList.length - 1));
})


// 유효성 검사
const $pSubmitBtn = $(".profile_sub")
const $userNickname = $("input[name=userNickname]")

$userNickname.keyup( e => checkUserNickAjax(e.target.value))
$(document).on("keyup", ".currPass", e => checkCurrPasswordAjax(e.target.value))
$(document).on("keyup", ".passCheck", e =>{
	const $passCheck = $(".passCheck")
	if(e.target.value === $("input[name=userPass]").val()){
		$passCheck.addClass("submit-abled")
		$passCheck.removeClass("submit-disabled")
	} else{
		$passCheck.addClass("submit-disabled")
		$passCheck.removeClass("submit-abled")
	}
})

function checkUserNickAjax(userNickname){
	$.ajax({
		url: "checkusernick.do",
		data: {userNickname, userNo}
	})
	.done((e) => {
		if(e !== 0){
			$userNickname.removeClass("submit-abled")
			$userNickname.addClass("submit-disabled")
		} else {
			$userNickname.addClass("submit-abled")
			$userNickname.removeClass("submit-disabled")
		}
	})
}

function checkCurrPasswordAjax(userPass) {
	const $currPass = $(".currPass")
	$.ajax({
		url: "checkcurruserpass.do",
		data: {userPass, userNo}
	})
	.done((e) => {
		// true => 비밀번호가 일치함
		if(e){
			$currPass.addClass("submit-abled")
			$currPass.removeClass("submit-disabled")
		} else {
			$currPass.removeClass("submit-abled")
			$currPass.addClass("submit-disabled")
		}
	})
}

$(".user_setting").submit((e) => {
	e.preventDefault();
	if($userNickname.hasClass("submit-disabled")){
		alert("닉네임이 중복됩니다.")
		return;
	}
	if($(".currPass").hasClass("submit-disabled")){
		alert("이전 비밀번호가 올바르지 않습니다.")
		return;
	}
	if($(".passCheck").hasClass("submit-disabled")){
		alert("비밀번호가 일치하지 않습니다.")
		return;
	}
	e.target.submit();
})













